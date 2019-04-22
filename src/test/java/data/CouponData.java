/**
 * @author helen
 * @date 2018年9月20日
 */
package data;

import java.util.HashMap;
import java.util.List;

import common.MysqlConnect;

/**
 * @Description:优惠券相关数据
 */
public class CouponData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	List<HashMap<String, String>> resultSet;
	
	/*检查会员account是否获得优惠券
	 * @account 会员账号
	 * @couponId 优惠券ID
	 * */
	public boolean hadCoupon(String account,String couponId) {
		boolean hadCoupon = true;
		resultSet = mysqlConnect.selectSql("SELECT * from m_coupons_get_use_record WHERE coupon_id="+couponId+" and user_id=(SELECT user_id from m_member WHERE account='"+account+"')");
		if (resultSet.size()==0) {
			hadCoupon = false;
		}
		return hadCoupon;
	}
	
	/*获取最新一条优惠券记录的ID*/
	public String getLastCouponId() {
		resultSet = mysqlConnect.selectSql("SELECT id from m_marketing_coupon ORDER BY id DESC LIMIT 1;");
		return resultSet.get(0).get("id");
	}
	
	/*把用户名下的未使用的优惠券都删除
	 * @account 用户账号
	 * */
	public void update_deleteStatus(String account) {
		mysqlConnect.updateData("UPDATE m_coupons_get_use_record set is_delete=0 where use_status=0 and user_account='"+account+"'");
	}
	
	/*获取优惠券ID
	 * @couponName 优惠券名称
	 * */
	public String get_id(String couponName) {
		String id = "";
		resultSet = mysqlConnect.selectSql("SELECT id from m_marketing_coupon where name='"+couponName+"'");
		if (resultSet.size()==0) {
			id="0";
		}
		else {
			id = resultSet.get(0).get("id");
		}
		return id;
	}
	
	/*获取优惠券发放状态
	 * @couponId 优惠券ID
	 * */
	public String get_sentStatus(String couponId) {
		String sentStatus = "";
		resultSet = mysqlConnect.selectSql("SELECT sent_status from m_marketing_coupon where id="+couponId);
		if (resultSet.size()==0) {
			sentStatus="-1";
		}
		else {
			sentStatus = resultSet.get(0).get("sent_status");
		}
		
		return sentStatus;
	}

}
