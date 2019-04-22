/**
 * @author helen
 * @date 2018年9月10日
 */
package data;

import java.util.HashMap;
import java.util.List;

import common.BaseData;
import common.MysqlConnect;

/**
 * @Description:获取验证码公共类
 */
public class CommonData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	BaseData baseData = new BaseData();
	List<HashMap<String, String>> resultSet;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*获取运营中心用户最后登录IP*/
	public String getMUserValue(String username,String key) {
		String value = "";
		try {
			resultSet = mysqlConnect.selectSql("SELECT "+key+" from m_user where login_account='"+username+"'");
			if (resultSet.size()>0) {
				value = resultSet.get(0).get(key);;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/*通过手机获取验证码*/
	public String getVerificationCode_ByPhone(String phone) {
		String code = "";
		try {
			Thread.sleep(5000);
			resultSet=mysqlConnect.selectSql("SELECT message FROM `pub_sms_send_record` where mobile='"+phone+"' ORDER BY id DESC");
			if (resultSet.size()==0) {
				code="-1";
			}
			else {
				code = baseData.getTargetList(resultSet.get(0).get("message"), "\\d+").get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
	/*通过邮箱获取验证码*/
	public String getVerificationCode_ByEmail(String email) {
		String code = "";
		try {
			Thread.sleep(5000);
			resultSet=mysqlConnect.selectSql("SELECT message FROM `pub_email_send_record` where email='"+email+"' ORDER BY id DESC");
			code = baseData.getTargetList(resultSet.get(0).get("message"), "\\d+").get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
	/*获取登录密码
	 * @account 账号
	 * */
	public String get_password(String account) {
		String password="";
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT `password` from m_member where account='"+account+"'");
			password = resultSet.get(0).get("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

}
