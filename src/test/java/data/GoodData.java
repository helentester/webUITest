/**
 * @author helen
 * @date 2018年9月11日
 */
package data;

import java.util.HashMap;
import java.util.List;

import common.MysqlConnect;

/**
 * @Description:商品相关数据
 */
public class GoodData {
	MysqlConnect mysqlConnect = new MysqlConnect();
	List<HashMap<String, String>> resultSet;
	
	/*修改SKU的最大购买数
	 * @SKU_id  SKUID
	 * @maxBuy	最大购买数
	 * */
	public void updateSKU_maxBuy(String SKU_id,String maxBuy) {
		mysqlConnect.updateData("UPDATE p_goods_sku set max_buy_count="+maxBuy+" where id="+SKU_id);
	}
	
	/*更新SKU库存
	 * @skuId 商品SKU的ID
	 * */
	public void update_bought(String skuId,String bought) {
		mysqlConnect.updateData("UPDATE p_goods_sku SET bought="+bought+"  where id=" + skuId + "");
	}
	
	/*获取商品的SKU信息
	 * @goodId	商品ID
	 * */
	public List<HashMap<String, String>> get_SKU(String goodId) {
		List<HashMap<String, String>> SKU_list = mysqlConnect.selectSql("SELECT id,bought from p_goods_sku WHERE goods_id ="+goodId);
		return SKU_list;
	}
	
	/*修改商品的最大购买数
	 * @goodId 商品ID
	 * @maxBuy 最大购买数
	 * */
	public void update_maxButCount(String goodId,String maxBuy) {
		mysqlConnect.updateData("UPDATE p_goods set max_buy_count="+maxBuy+" where id="+goodId);
	}
	
	/*修改商品的上架状态
	 * @goodId	商品ID
	 * @onliveStatus 上架状态
	 * */
	public void update_onliveStatus(String goodId,String onliveStatus) {
		mysqlConnect.updateData("UPDATE p_goods set is_onlive="+onliveStatus+" where id="+goodId);
	}
	
	/*根据商品名称，获取各类商品的区别属性
	 * @goodName 商品名称
	 * */
	public HashMap<String, String> get_goodDifferDetail(String goodName) {
		HashMap<String, String> goodDetail = new HashMap<String, String>();
		try {
			Thread.sleep(3000);
			resultSet = mysqlConnect.selectSql("SELECT is_delivery,is_real_name,is_health,is_onlive,publish_wait,shelves_type from p_goods where `name`='"+goodName+"'");
			if (resultSet.size()==0) {
				goodDetail.put("goodAdd", "失败");
			}
			else {
				goodDetail.put("is_delivery", resultSet.get(0).get("is_delivery"));//
				goodDetail.put("is_real_name", resultSet.get(0).get("is_real_name"));//
				goodDetail.put("is_health", resultSet.get(0).get("is_health"));//
				goodDetail.put("is_onlive", resultSet.get(0).get("is_onlive"));//
				goodDetail.put("publish_wait", resultSet.get(0).get("publish_wait"));//
				goodDetail.put("shelves_type", resultSet.get(0).get("shelves_type"));//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodDetail;
	}
	
	/*根据商品名称获取商品ID
	 * @goodName 商品名称
	 * */
	public String get_Id(String goodName) {
		String id="";
		resultSet = mysqlConnect.selectSql("SELECT id from p_goods where name ='"+goodName+"'");
		if (resultSet.size()>0) {
			id=resultSet.get(0).get("id");
		}
		return id;
	}
	
	/*根据商品ID获取商品信息，p_goods
	 * @goodId	商品ID
	 * @key  字段名称
	 * */
	public String getGoodValue_goodId(String goodId,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from p_goods where id=" + goodId + "");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*根据商品名称获取商品信息，p_goods
	 * @goodId	商品ID
	 * @key  字段名称
	 * */
	public String getGoodValue_goodName(String goodName,String key) {
		String value = "";
		resultSet = mysqlConnect.selectSql("SELECT "+key+" from p_goods where `name`='"+goodName+"'");
		if (resultSet.size()>0) {
			value = resultSet.get(0).get(key);
		}
		return value;
	}
	
	/*获取商品名称
	 * @supplier_id  商家Id
	 * */
	public String get_unauditGoodsName(String supplier_id) {
		String goodName = "";
		resultSet = mysqlConnect.selectSql("SELECT `name` from p_goods where supplier_id="+supplier_id+" and is_onlive=2 and publish_wait=0 and is_delete=0 ORDER BY id DESC LIMIT 1");
		if (resultSet.size()>0) {
			goodName = resultSet.get(0).get("name");
		}
		return goodName;
	}
}
