/**
 * @author helen
 * @date 2018年9月10日
 */
package data;

import java.util.HashMap;
import java.util.List;

import common.MysqlConnect;

/**
 * @Description:代理商的数据
 */
public class AgentData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	List<HashMap<String, String>> resultSet;
	
	/*根据代理商ID查询代理商信息
	 * @agentId
	 * @key
	 * */
	public String getValue_agentId(String agentId,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from u_supermarket_agent_user where agent_id="+agentId);
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}

	/*设置代理商的邀请人,u_supermarket_agent_user
	 * @account  代理商账号
	 * @inviter  邀请人账号,只能是代理商
	 * */
	public void update_inviter(String inviter,String account) {
		resultSet = mysqlConnect.selectSql("SELECT * from u_supermarket_agent_user where account='"+account+"' AND recommend_name='"+inviter+"'");
		if (resultSet.size()==0) {
			String inviterId = this.getAgentValue(inviter,"agent_id");
			mysqlConnect.updateData("UPDATE u_supermarket_agent_user set invite='"+inviter+"',recommend_id="+inviterId+" ,recommend_name='"+inviter+"',recommend_code='"+inviter+"',recommend_type='agent' where account='"+account+"'");
		}
		
	}
	
	/*添加健康家收货地址
	 * @agentID  健康家的ID
	 * */
	public void insert_healthyAddr(String agentID) {
		mysqlConnect.updateData("INSERT INTO `wufuo2o`.`cg_purchase_delivery_address` (`agent_id`, `province_name`, `province_code`, `city_name`, `city_code`, `district_name`, `district_code`, `detail_address`, `area_code`,  `mobile`, `delivery_name`, `default_address`) VALUES ('"+agentID+"','吉林省', '220000', '长春市', '220100', '南关区', '220102',  '亚洲国际大酒店', '51000',  '13852456985', 'helen', '1');");
	}

	/*获取第一个审核状态为通过的代理商*/
	public String get_FirstAuditAgent() {
		resultSet = mysqlConnect.selectSql("SELECT account from u_supermarket_agent_user where auditor_status=1 ORDER BY agent_id desc LIMIT 1");
		return resultSet.get(0).get("account");
	}
	
	/*根据代理商账号获取代理商信息,u_supermarket_agent_user
	 * @account 代理商账号
	 * @key 字段名称
	 * */
	public String getAgentValue(String account,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from u_supermarket_agent_user where account='"+account+"'");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*判定代理商是否存在,u_supermarket_agent_user
	 * @account 账号
	 * */
	public Boolean agentExist(String account) {
		Boolean r = true;
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT * from u_supermarket_agent_user where account='"+account+"'");
			if (resultSet.size()==0) {
				r = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/*判定健康家收货地址是否存在,cg_purchase_delivery_address
	 * @account
	 * */
	public boolean healthyHomeAddrExist(String account) {
		boolean addrExit = true;
		resultSet = mysqlConnect.selectSql("SELECT * from cg_purchase_delivery_address where default_address=1 AND agent_id=(SELECT agent_id from u_supermarket_agent_user where account ='"+account+"')");
		if (resultSet.size()==0) {
			addrExit=false;
		}
		return addrExit;
	}

	/*获取代理商邀请的商家数量（商家必须为审核通过）
	 * @agent  代理商账号
	 * */
	public String get_inviteSelerCount(String agent) {
		String inviteSellerCount = "";
		String agentId = this.getAgentValue(agent, "agent_id");//获取代理商ID
		resultSet = mysqlConnect.selectSql("SELECT COUNT(*) as c from u_supplier_user where auditor_status=1 and recommend_id="+agentId);
		if (resultSet.size()>0) {
			inviteSellerCount = resultSet.get(0).get("c");
		}
		
		return inviteSellerCount;
	}

}
