/**
 * @author helen
 * @date 2018年9月10日
 */
package data;

import java.util.HashMap;
import java.util.List;
import common.MysqlConnect;

/**
 * @Description:商家数据
 */
public class SellerData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	AgentData agentData = new AgentData();
	List<HashMap<String, String>> resultSet;

	/*根据商家ID查询商家信息
	 * @sellerId 商家ID
	 * @key  
	 * */
	public String getValue_sellerId(String sellerId,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from u_supplier_user where supplier_id="+sellerId);
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*设置商家的分润比例
	 * @seller  商家账号
	 * @profit_percentage  分润比例
	 * */
	public void update_profitPercentage(String seller,String profit_percentage) {
		String sellerId = this.get_sellerId(seller);
		mysqlConnect.updateData("UPDATE u_supplier_user set profit_percentage="+profit_percentage+" where supplier_id="+sellerId);
	}
	
	/*设置商家的邀请人
	 * @account 商家账号
	 * @inviter 邀请人，只能是代理商
	 * */
	public void update_inviter(String inviter,String account) {
		String inviterId = agentData.getAgentValue(inviter,"agent_id");
		resultSet = mysqlConnect.selectSql("SELECT * from u_supplier_user where account='"+account+"' AND recommend_id='"+inviterId+"'");
		if (resultSet.size()==0) {
			String inviterName = agentData.getAgentValue(inviter, "real_name");
			mysqlConnect.updateData("UPDATE u_supplier_user SET recommend_id='"+inviterId+"',recommend_code='"+inviter+"',recommend_name='"+inviterName+"' WHERE account='"+account+"'");
		}
	}
	
	/*根据模板名称获取商家的动费模板ID
	 * @seller 商家名称
	 * @TemplateName
	 * */
	public String getTemplateId_name(String seller,String TemplateName) {
		String TemplateId = "";
		String sellerId = this.get_sellerId(seller);
		resultSet = mysqlConnect.selectSql("SELECT template_id from p_freight_template WHERE supplier_id="+sellerId+" and name like'%"+TemplateName+"%' ORDER BY template_id DESC LIMIT 1");
		if (resultSet.size()>0) {
			TemplateId = resultSet.get(0).get("template_id");
		}
		return TemplateId;
	}
	
	/*添加商家的运费模板
	 * @sellerId 商家ID
	 * */
	public void insert_template(String sellerId) {
		mysqlConnect.updateData("INSERT INTO `wufuo2o`.`p_freight_template` (`name`, `supplier_id`, `billing_method`, `status`, `create_time`, `update_time`) VALUES ('normal', '"+sellerId+"', '1', '1', '2018-07-17 13:26:16', '2018-08-02 17:07:41')");
		resultSet = mysqlConnect.selectSql("SELECT template_id FROM p_freight_template where supplier_id="+sellerId);
		mysqlConnect.updateData("INSERT INTO `wufuo2o`.`p_freight_template_detail` (`template_id`, `distribution_area`, `distribution_title`, `first_piece`, `first_fee`, `renew_piece`, `renew_fee`) VALUES ('"+resultSet.get(0).get("template_id")+"', '440000', '广东省', '1', '0.01', '1', '0.01');");
	}

	/*根据商家审核状态，获取一个商家的法人手机号
	 * @auditorStatus  审核状态
	 * */
	public String get_oneSellerByAuditorStatus(String auditorStatus) {
		resultSet = mysqlConnect.selectSql("SELECT * from u_supplier_user where auditor_status="+auditorStatus+" ORDER BY supplier_id desc LIMIT 1");
		return resultSet.get(0).get("legal_mobile");
	}
	
	
	/*根据商家账号，获取商家ID
	 * @account 商家账号
	 * */
	public String get_sellerId(String account) {
		String id="";
		resultSet = mysqlConnect.selectSql("SELECT supplier_id from u_supplier_user where account='"+account+"'");
		if (resultSet.size()==0) {
			id="0";
		}
		else {
			id = resultSet.get(0).get("supplier_id");
		}
		return id;
	}
	
	/*根据账号查询商家名称
	 * @account 商家账号
	 * */
	public String  get_sellerName(String account) {
		String sellerName="";
		resultSet = mysqlConnect.selectSql("SELECT supplier_name from u_supplier_user where account='"+account+"'");
		if(resultSet.size()>0) {
			sellerName=resultSet.get(0).get("supplier_name");
		}
		return sellerName;
	}
	
	/*获取商家的审核状态
	 * @legalMobile 法人手机号
	 * */
	public String get_auditorStatusByLegalMobile(String legalMobile) {
		String auditorStatus = "";
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT auditor_status from u_supplier_user where legal_mobile='"+legalMobile+"'");
			if(resultSet.size()==0) {
				auditorStatus = "-1";
			}
			else {
				auditorStatus = resultSet.get(0).get("auditor_status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auditorStatus;
	}
	
	/*获取商家的登录密码
	 * @account 账号名称
	 * */
	public String get_password(String account) {
		String password = "";
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT password from u_supplier_user where account='"+account+"'");
			password = resultSet.get(0).get("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return password;
	}

	/*根据商家ID，检查运费模版是否存在
	 * @sellerId 商家ID
	 * */
	public boolean templateExit(String sellerId) {
		Boolean TExit = false;
		resultSet = mysqlConnect.selectSql("SELECT * FROM p_freight_template where supplier_id="+sellerId);
		if (resultSet.size()==0) {
			TExit = true;
		}
		return TExit;
	}

	/*根据商家账号，检查商家是否存在
	 * @account 商家账号
	 * */
	public boolean sellerExit(String account) {
		boolean SExit = true;
		try {
			Thread.sleep(5000);
			resultSet = mysqlConnect.selectSql("SELECT * from u_supplier_user where account='"+account+"'");
			if (resultSet.size()==0) {
				SExit = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SExit;
	}
}
