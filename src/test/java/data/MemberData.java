/**
 * @author helen
 * @date 2018年9月11日
 */
package data;

import java.util.HashMap;
import java.util.List;
import common.MysqlConnect;

/**
 * @Description:会员相关数据
 */
public class MemberData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	List<HashMap<String, String>> resultSet;

	/*根据会员账号查询会员信息
	 * @account 会员账号
	 * @key 字段名称
	 * */
	public String getValue_account(String account,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from m_member where account='"+account+"'");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}

	/*设置会员邀请关系，m_member
	 * @member  会员账号
	 * @inviter 邀请人账号
	 * 备注：会员体系改版后，m_member中的邀请类型只有会员邀请会员
	 * */
	public void update_Inviter(String member,String inviter) {
		resultSet = mysqlConnect.selectSql("SELECT recommend_name from m_member where account='"+member+"'");
		if (resultSet.size()==0) {//如果消费者不存在返回false
			System.out.println("消费者账号不存在");
		}
		else {//消费者存在，检查邀请人
			String recommend_name=resultSet.get(0).get("recommend_name");
			if (recommend_name!=inviter) {
				String inviterId = this.get_Id(inviter);
				mysqlConnect.updateData("UPDATE m_member SET recommend_id="+inviterId+",recommend_name='"+inviter+"',recommend_type='member' where account='"+member+"'");
				
			}
		}
	}
	
	/*根据订单号和会员账号，查看评论是否存在
	 * @orderSN 订单号
	 * @account 会员账号
	 * */
	public boolean evaluateExit(String account,String orderSN) {
		boolean exit = true;
		resultSet = mysqlConnect.selectSql("SELECT * from t_order_item_evaluate where order_id=(SELECT id from t_orders where order_sn='"+orderSN+"') and user_id=(SELECT user_id from m_member where account='"+account+"')");
		if (resultSet.size()==0) {
			exit=false;
		}
		return exit;
	}

	/*
	 * 添加用户的默认地址
	 * @telephone 联系人电话
	 * @userId 用户ID
	 */
	public void insert_defaultAddress(String userId,String telephone) {
		mysqlConnect.updateData(
				"INSERT INTO `wufuo2o`.`m_member_delivery_address` (`user_id`, `province_name`, `province_code`, `city_name`, `city_code`, `district_name`, `district_code`, `address`, `detail_address`, `area_code`, `telephone`, `mobile`, `delivery_name`, `default_address`, `address_tag`, `create_date`, `update_date`, `remark`, `is_foreign`, `telephone_code_id`) VALUES ('"
						+ userId
						+ "', '广东省', '440000', '广州市', '440100', '越秀区', '440104', '小北', '广东省广州市越秀区小北', '00000', '13825464584', '13825464584', 'helenli', '1', '公司', '2018-07-26 17:24:15', '2018-07-26 17:24:15', '', '0', '0')");
	}

	/*
	 * 清空用户的购物车
	 * 
	 * @account 账号
	 */
	public void clear_cart(String account) {
		mysqlConnect
				.updateData("UPDATE t_cart set is_delete=1 where user_id=(SELECT user_id FROM m_member where mobile='"
						+ account + "')AND is_delete=0");
	}

	/*修改会员的订单支付时间
	 * @account 账号
	 * */
	public void update_payTime(String account,String time) {
		mysqlConnect.updateData("SELECT t_order_pay SET add_time=UNIX_TIMESTAMP('"+time+"') where order_id in(SELECT id from t_orders where user_id=(SELECT id from m_member where account ='"+account+"'))");
	}
	
	/*更新会员的福豆
	 * @account 账号
	 * @abled_score  福豆
	 * */
	public void update_AbleScore(String account,String abledScore) {
		mysqlConnect.updateData("UPDATE m_member_score SET abled_score=" + abledScore + " where user_id=(SELECT user_id from m_member where account='"+account+"')");
	}

	/*
	 * 更新会员的大健康积分
	 * 
	 * @account 账号
	 * 
	 * @HScore 大健康积分
	 */
	public void update_HScore(String account, String HSCore) {
		mysqlConnect.updateData("UPDATE m_member_score SET h_score='" + HSCore + "' where user_id=(SELECT user_id from m_member where account='"+account+"')");
	}
	
	/*获取用户最新一条优惠券的使用状态
	 * @account 账号名称
	 * */
	public String get_lastCouponUseStatus(String account) {
		String useStatus = "";
		resultSet = mysqlConnect.selectSql("SELECT use_status from m_coupons_get_use_record WHERE user_account='"+account+"' ORDER BY id desc LIMIT 1");
		if (resultSet.size()==0) {
			useStatus="-1";
		}
		else {
			useStatus = resultSet.get(0).get("use_status");
		}
		return useStatus;
	}

	/*获取订单号
	 * @account	账号名称
	 * @orderStatus 订单状态：-1 已取消（关闭） 0未支付(待付款) 1 已超时 2 支付中 3支付成功（待发货）4已发货（待收货）5确认收货 6完结  '
	 * */
	public String get_orderSN(String account,String orderStatus) {
		String orderSN="";
		resultSet = mysqlConnect.selectSql("SELECT order_sn FROM t_orders where order_status="+orderStatus+" and user_id=(SELECT user_id from m_member where account='"+account+"') ORDER BY id desc LIMIT 1");
		if (resultSet.size()>0) {
			orderSN = resultSet.get(0).get("order_sn");
		}
		System.out.println(orderSN);
		return orderSN;
	}
	
	/*获取会员等级
	 * @account 账号
	 * */
	public String get_memberGrade(String account) {
		String grade = "";
		resultSet = mysqlConnect.selectSql("SELECT memeber_grade FROM m_member where account = '"+account+"'");
		if (resultSet.size()==0) {
			grade="0";
		}
		else {
			grade = resultSet.get(0).get("memeber_grade");
		}
		return grade;
	}
	
	/*
	 * 获取用户ID
	 * 
	 * @account 账号
	 */
	public String get_Id(String account) {
		String id = "";
		resultSet = mysqlConnect.selectSql("SELECT user_id FROM m_member where account='" + account + "'");
		if (resultSet.size() == 0) {
			id = "0";
		} else {
			id = resultSet.get(0).get("user_id");
		}
		return id;
	}
	
	/*
	 * 获取用户的大健康积分
	 * 
	 * @account 账号
	 */
	public String get_HScore(String account) {
		String HSCore = "";
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql(
					"SELECT 0+CAST(h_score AS CHAR) AS HScore from m_member_score where user_id=(SELECT user_id FROM m_member where account='"
							+ account + "')");
			if (resultSet.size() == 0) {
				HSCore = "0";
			} else {
				HSCore = resultSet.get(0).get("HScore");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HSCore;
	}

	/*获取用户的福豆
	 * @account 账号
	 * */
	public String get_ableScore(String account) {
		String ableScore = "";
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect
					.selectSql("select abled_score from m_member_score where account = '" + account + "'");
			if (resultSet.size()==0) {
				ableScore="0";
			}
			else {
				ableScore = resultSet.get(0).get("abled_score");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ableScore;
	}

	/*会员是否存在
	 * @account 账号
	 * */
	public boolean memberExit(String account) {
		boolean MExit = true;
		resultSet = mysqlConnect.selectSql("SELECT * FROM m_member where account = '"+account+"'");
		if (resultSet.size()==0) {
			MExit = false;
		}
		return MExit;
	}


	/*
	 * 查询用户的默认地址  m_member_delivery_address
	 * 
	 * @userId 用户ID
	 */
	public boolean defaultAddressExit(String userId) {
		boolean exit = false;
		resultSet = mysqlConnect
				.selectSql("SELECT * from m_member_delivery_address WHERE default_address=1 and user_id=" + userId);
		if (resultSet.size() == 0) {
			exit = true;
		}
		return exit;
	}

}
