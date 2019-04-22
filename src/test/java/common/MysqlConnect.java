/**
 * @author helen
 * @date 2018年7月5日
 */
package common;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Reporter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description:mysql操作
 */
public class MysqlConnect {
	private Connection connection;
	private Statement statement;
	MyConfig myConfig = new MyConfig();

	public static void main(String[] args) {
		MysqlConnect mysqlConnect = new MysqlConnect();
		//mysqlConnect.updateData("UPDATE t_cart set is_delete=1 where user_id=(SELECT user_id FROM m_member where account='13825464584')AND is_delete=0");
		List<HashMap<String, String>> resultSet;
		resultSet = (List<HashMap<String, String>>) mysqlConnect.selectSql("SELECT code from m_member_code where mobile='13006991229'");
		System.out.println(resultSet.get(0).get("code"));//获取表中account的值

	}

	/* 创建数据库连接 */
	private void createConn() {
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://192.168.1.236:3306/wufuo2o", "system","Wufu@201768");
			connection = DriverManager.getConnection(myConfig.getKeys("mysql_jdbc"), myConfig.getKeys("mysql_username"),myConfig.getKeys("mysql_password"));
		} catch (Exception e) {
			Reporter.log("数据库链接失败");
			System.out.println("数据库链接失败");
			e.printStackTrace();
		}
	}

	/* 创建statement对象，用于将SQL语句发送到数据库 */
	private Statement createStatement() {
		if (connection == null) {
			this.createConn();
		}
		try {
			statement = (Statement) connection.createStatement();
		} catch (Exception e) {
			Reporter.log("创建statement对象失败");
			e.printStackTrace();
		}
		return statement;
	}

	/* 获取结果集 */
	private ResultSet getResultSet(String sql) {
		ResultSet resultSet = null;
		if (statement == null) {
			this.createStatement();
		}
		try {
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			Reporter.log("创建结果集失败");
			e.printStackTrace();
		}
		return resultSet;
	}

	/* 执行查询语句，并返回结果 */
	public List<HashMap<String, String>> selectSql(String sql) {
		ResultSet rs = this.getResultSet(sql);
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			
			int rscc = metaData.getColumnCount();// 返回 ResultSet 中的列数
			while (rs.next()) {
				HashMap<String, String> columnMap = new HashMap<String, String>();
				for (int i = 1; i <= rscc; i++) {
					columnMap.put(metaData.getColumnName(i), rs.getString(i));
				}
				result.add(columnMap);

			}
		} catch (Exception e) {
			System.out.println("执行查询语句失败");
			e.printStackTrace();
		}
		this.close_conn();//关闭数据库链接
		return result;
	}
	
	/* 执行查询语句，并返回结果 */
	public JSONArray getData(String sql) {
		ResultSet rs = this.getResultSet(sql);

		JSONArray result = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			
			int rscc = metaData.getColumnCount();// 返回 ResultSet 中的列数
			while (rs.next()) {
				JSONObject columnMap = new JSONObject();
				for (int i = 1; i <= rscc; i++) {
					if (rs.getObject(i)==null) {//把结果为null的值转为空字符串
						columnMap.put(metaData.getColumnLabel(i), "");
					}
					else {
						columnMap.put(metaData.getColumnLabel(i), rs.getObject(i));//别名
					}
				}
				result.add(columnMap);
				
			}
		} catch (Exception e) {
			System.out.println("执行查询语句失败");
			e.printStackTrace();
		}
		this.close_conn();//关闭数据库链接
		return result;
	}
	
	
	/* 修改数据	*/
	public void updateData(String sql) {
		try {
			Statement statement = this.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 关闭数据库链接 */
	private void close_conn() {
		try {
			if (statement!=null) {
				statement.close();
				statement=null;
			}
			if (connection!=null) {
				connection.close();
				connection=null;
			}
		} catch (Exception e) {
			Reporter.log("关闭数据库失败");
			e.printStackTrace();
		}
	}

}
