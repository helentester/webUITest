/**
 * @author helen
 * @date 2018年9月11日
 */
package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.MysqlConnect;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description:订单相关的数据
 */
public class OrderData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	List<HashMap<String, String>> resultSet;
	JSONArray arrayResult = new JSONArray();

	/*获取订单中的分润信息
	 * @orderSN  订单编号
	 * */
	public JSONObject getProfitDetail_byOrderSN(String orderSN) {
		arrayResult = mysqlConnect.getData("SELECT profitable_price,platform_ratio,platform_money,mb_recomond1_ratio,mb_recomond1_money,sj_recomond1_rate,sj_recomond1_money,sj_recomond2_rate,sj_recomond2_money,sj_recomond3_rate,sj_recomond3_money  from ps_profit_details WHERE ps_type=1 and order_id=(SELECT id from t_orders where order_sn='"+orderSN+"')");
		JSONObject jsonObject = null;
		if (arrayResult.size()>0) {
			jsonObject = arrayResult.getJSONObject(0);
			for(Object j:jsonObject.keySet()) {
				if (jsonObject.get(j)=="") {//把没有值的字段给一个0
					jsonObject.put(j, 0);
				}
			}
		}
		
		return jsonObject;
	}
	
	/*查询会员福豆流水记录表m_bean_bless_record
	 * @orderSn 订单号
	 * @detail_type  '明细类型：0.系统、1.充值、2.线上下单、3.订单取消、4.订单超时、5.订单退款、6.评价、7.线下扫码下单、8.线下扫码单评价、9.转账、10.签到奖励、11.抽奖奖励、12.补签、13.会员注册',
	 * */
	public String getBessRecord_orderSn(String orderSN,String operate_type) {
		String trade_bean = "";
		resultSet = mysqlConnect.selectSql("select trade_bean from m_bean_bless_record where resource_key=(SELECT id from t_orders where order_sn='"+orderSN+"') and detail_type ="+operate_type);
		if (resultSet.size()>0) {
			trade_bean = resultSet.get(0).get("trade_bean");
		}
		return trade_bean;
	}
	
	/*查询会员享豆流水记录表 m_bean_enjoy_record
	 * @orderSn 订单号
	 * @detail_type  '明细类型：0.系统、1. 线上下单、2.订单取消、3.订单超时、4.订单退款、5.评价、6.线下扫码单评价、7.下单失败',
	 * */
	public String getEnjoyRecord_orderSn(String orderSN,String operate_type) {
		String trade_bean = "";
		resultSet = mysqlConnect.selectSql("select trade_bean from m_bean_enjoy_record where resource_key=(SELECT id from t_orders where order_sn='"+orderSN+"') and detail_type ="+operate_type);
		if (resultSet.size()>0) {
			trade_bean = resultSet.get(0).get("trade_bean");
		}
		return trade_bean;
	}
	
	/*查询会员福豆冻结记录表m_bean_enjoy_freeze_record
	 * @orderSn 订单号
	 * @operate_type  '操作类型：1.冻结入账、2.冻结入账退还、3.解冻入账、4.冻结出账、5.冻结出账退还、6.解冻出账',
	 * */
	public String getEnjoyTradeBean_orderSn(String orderSN,String operate_type) {
		String trade_bean = "";
		resultSet = mysqlConnect.selectSql("select trade_bean from m_bean_enjoy_freeze_record where resource_key=(SELECT id from t_orders where order_sn='"+orderSN+"') and operate_type ="+operate_type);
		if (resultSet.size()>0) {
			trade_bean = resultSet.get(0).get("trade_bean");
		}
		return trade_bean;
	}
	
	/*查询会员福豆冻结记录表m_bean_bless_freeze_record
	 * @orderSn 订单号
	 * @operate_type  '操作类型：1.冻结入账、2.冻结入账退还、3.解冻入账、4.冻结出账、5.冻结出账退还、6.解冻出账',
	 * */
	public String getBlessTradeBean_orderSn(String orderSN,String operate_type) {
		String trade_bean = "";
		resultSet = mysqlConnect.selectSql("select trade_bean from m_bean_bless_freeze_record where resource_key=(SELECT id from t_orders where order_sn='"+orderSN+"') and operate_type ="+operate_type);
		if (resultSet.size()>0) {
			trade_bean = resultSet.get(0).get("trade_bean");
		}
		return trade_bean;
	}
	
	/*分润所得乐豆和福豆，表t_order_item_evaluate_coupon
	 * @orderSN 订单号
	 * @key  字段名称
	 * */
	public String getEvaluateCoupon_orderSN(String orderSN,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from t_order_item_evaluate_coupon where order_id=(SELECT id from t_orders where order_sn='"+orderSN+"')");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}

	/*分润所得乐豆和福豆，表t_order_evaluate_task
	 * @orderSN 订单号
	 * @key  字段名称
	 * */
	public String getEvaluateTaskValue_byOrderSN(String orderSN,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from t_order_evaluate_task where status=2 and type=2 and order_id=(SELECT id from t_orders where order_sn='"+orderSN+"')");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	
	/*根据订单号查询退单信息，表t_order_refund
	 * @orderSN 订单号
	 * @key 字段名称
	 * */
	public String getKey_byOrderSN_RefundT(String orderSN,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from t_order_refund where order_sn='"+orderSN+"' ORDER BY id DESC LIMIT 1");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*获取订单的评论审核状态，表t_order_item_evaluate_detail
	 * @orderSN  订单号
	 * */
	public String get_EvaluateAuditStatus(String orderSN) {
		String auditStatus = "";
		resultSet = mysqlConnect.selectSql("SELECT audit_status from t_order_item_evaluate_detail where evaluate_id=(SELECT id from t_order_item_evaluate where order_sn='"+orderSN+"')");
		if (resultSet.size()==0) {
			auditStatus="-1";
		}
		else {
			auditStatus = resultSet.get(0).get("audit_status");
		}
		return auditStatus;
	}
	
	/*订单统计表（t_order_statistics）获取订单的优惠券、积分、现金的分摊
	 * @orderSN  订单号
	 * */
	public HashMap<String, String> get_statisticsPayShare(String orderSN) {
		HashMap<String, String> map = new HashMap<String, String>();
		resultSet =mysqlConnect.selectSql("SELECT score,coupon,pay_price from t_order_statistics where order_no='"+orderSN+"'");
		map.put("resulSize", String.valueOf(resultSet.size()));
		if (resultSet.size()>0) {
			map.put("score", resultSet.get(0).get("score"));
			map.put("coupon", resultSet.get(0).get("coupon"));
			map.put("pay_price", resultSet.get(0).get("pay_price"));
		}
		return map;
	}
	
	/*根据订单号查询订单的单个字段内容 表：t_orders
	 * @orderSN 订单号
	 * @key 字段名称
	 * */
	public String getKeyValue_byOrderSN(String orderSN,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from t_orders where order_sn='"+orderSN+"'");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*（t_orders）订单维度的优惠券、福豆、现金的分摊
	 * @orderSN 订单号
	 * */
	public HashMap<String, String> get_PayShare(String orderSN) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT coupon_discount_money,discount_price,pay_price from t_orders where order_sn='"+orderSN+"'");
			map.put("resulSize", String.valueOf(resultSet.size()));
			if (resultSet.size()>0) {
				map.put("score", resultSet.get(0).get("discount_price"));
				map.put("coupon", resultSet.get(0).get("coupon_discount_money"));
				map.put("pay_price", resultSet.get(0).get("pay_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/*订单商品统计表（t_order_item_statistics）获取订单下商品的优惠券、福豆、现金的分摊
	 * @orderSN  订单号
	 * @SKU sku名称
	 * */
	public HashMap<String, String> get_statisticsGoodsPayShare(String orderSN,String sku) {
		HashMap<String, String> map =new HashMap<String, String>();
		resultSet = mysqlConnect.selectSql("SELECT score,coupon,pay_price from t_order_item_statistics where sku_name='"+sku+"' and order_no in("+orderSN+")");
		map.put("resultSize", String.valueOf(resultSet.size()));
		if (resultSet.size()>0) {
			map.put("score", resultSet.get(0).get("score"));
			map.put("coupon", resultSet.get(0).get("coupon"));
			map.put("pay_price", resultSet.get(0).get("pay_price"));
		}
		return map;
	}
	
	/*订单商品表分摊优惠券、福豆、现金,表：t_order_items
	 * @orderSN  订单号
	 * @SKU sku名称
	 * */
	public HashMap<String, String> get_ShareInOrderItems(String orderSN,String sku) {
		HashMap<String, String> map = new HashMap<String, String>();
		//订单商品表
		resultSet = mysqlConnect.selectSql("SELECT id,number,coupon_discount,pay_price from t_order_items where pdt_dest='"+sku+"' and order_id in(SELECT id from t_orders where order_sn in ("+orderSN+"))");
		map.put("resultSize", String.valueOf(resultSet.size()));
		if (resultSet.size()>0) {
			map.put("coupon", resultSet.get(0).get("coupon_discount"));
			map.put("pay_price", resultSet.get(0).get("pay_price"));
			map.put("number", resultSet.get(0).get("number"));
			
			//获取福豆
			String order_item = resultSet.get(0).get("id");
			resultSet = mysqlConnect.selectSql("SELECT consume from t_order_item_coupon where order_item_id ="+order_item);
			if (resultSet.size()>0) {
				map.put("score", resultSet.get(0).get("consume"));
			}
			else {
				map.put("score", "0");
			}
			
		}
		return map;
	}


	/*获取若干订单中的所有商品名称，表t_order_items
	 * @orderSNs 若干订单号
	 * */
	public Set<String> get_goodsName(String orderSNs) {
		Set<String> goodsName = new HashSet<String>();
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect
					.selectSql("SELECT name from t_order_items where order_id in(SELECT id from t_orders WHERE order_sn in("+orderSNs+"))");
			for (int i = 0; i < resultSet.size(); i++) {
				goodsName.add(resultSet.get(i).get("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsName;
	}
	
	/*根据若干订单ID，获取若干订单号，表t_orders
	 * @orderIds 若干订单ID
	 * */
	public Set<String> get_orderSN(String orderIds) {
		Set<String> orderSN_list = new HashSet<String>();
		try {
			resultSet = mysqlConnect.selectSql("SELECT order_sn from t_orders where id in(" + orderIds + ")");
			for (int i = 0; i < resultSet.size(); i++) {
				orderSN_list.add(resultSet.get(i).get("order_sn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderSN_list;
	}

	/*查看订单是否已评论，t_order_item_evaluate
	 * @orderSN 订单号
	 * */
	public boolean evaluateExist(String orderSN) {
		boolean exist = true;
		resultSet = mysqlConnect.selectSql("SELECT * from t_order_item_evaluate WHERE order_sn='"+orderSN+"'");
		if (resultSet.size()==0) {
			exist=false;
		}
		return exist;
	}
}
