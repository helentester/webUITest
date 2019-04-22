/**
 * @author helen
 * @date 2018年7月16日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.MyConfig;
import data.GoodData;
import data.SellerData;
import wufu.wufu.sellerPages.SellerGoodsPage;
import wufu.wufu.sellerPages.SellerIndexPage;

/**
 * @Description:商家后台管理－商品列表（添加商品）
 */
public class SellerGoodsTest {
	MyConfig myConfig = new MyConfig();
	BaseData baseData = new BaseData();
	LoginUITest loginUITest = new LoginUITest();
	SellerData sellerData = new SellerData();
	GoodData goodData = new GoodData();

	SellerIndexPage indexPage;
	SellerGoodsPage goodsPage;
	String sellerAccount =myConfig.getKeys("seller1");// "17304081510";//"15206215781";//用于测试的商家
	
	@Test
	public void test_addNomalGoods(){
		Reporter.log(sellerAccount+":添加普通商品");
		HashMap<String, String> goodsInfo = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;

			{
				put("goodsName", "普通商品auto"+baseData.getNum(1, 10000));
				put("showOnIndex", "否");
				put("goodType", "实物商品");
				put("infoReal", "否");
				put("own", "否");
				put("belongHealth", "否");
				put("submitType", "audit");
			}
		};
		String goodsName =  this.addGoods(sellerAccount, goodsInfo);
		
		HashMap<String, String> expectGoodDetail = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("is_delivery", "0");//商品类型
				put("is_real_name", "0");//是否需要实名
				put("is_health", "0");//是否乐豆商品
				put("is_onlive", "2");//上架状态
				put("publish_wait", "0");//商品状态
				put("shelves_type", "1");//上架类型
			}
		};
		assertEquals(goodData.get_goodDifferDetail(goodsName), expectGoodDetail);
	}
	
	@Test
	public void test_addServiceGoods() {
		Reporter.log(sellerAccount+":添加虚拟商品");
		HashMap<String, String> goodsInfo = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("goodsName", "虚拟商品auto"+baseData.getNum(1, 10000));
				put("showOnIndex", "否");
				put("goodType", "虚拟商品");
				put("infoReal", "否");
				put("own", "否");
				put("belongHealth", "否");
				put("submitType", "audit");
			}
		};
		String goodsName =  this.addGoods(sellerAccount, goodsInfo);
		
		HashMap<String, String> expectGoodDetail = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("is_delivery", "1");//商品类型
				put("is_real_name", "0");//是否需要实名
				put("is_health", "0");//是否乐豆商品
				put("is_onlive", "2");//上架状态
				put("publish_wait", "0");//商品状态
				put("shelves_type", "1");//上架类型
			}
		};
		assertEquals(goodData.get_goodDifferDetail(goodsName), expectGoodDetail);
	}
	
	@Test
	public void test_addRealnameGoods() {
		Reporter.log(sellerAccount+":添加虚拟+实名商品");
		HashMap<String, String> goodsInfo = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("goodsName", "虚拟+实名商品auto"+baseData.getNum(1, 10000));
				put("showOnIndex", "否");
				put("goodType", "虚拟商品");
				put("infoReal", "是");
				put("own", "否");
				put("belongHealth", "否");
				put("submitType", "audit");
			}
		};
		String goodsName =  this.addGoods(sellerAccount, goodsInfo);
		
		HashMap<String, String> expectGoodDetail = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("is_delivery", "1");//商品类型
				put("is_real_name", "1");//是否需要实名
				put("is_health", "0");//是否乐豆商品
				put("is_onlive", "2");//上架状态
				put("publish_wait", "0");//商品状态
				put("shelves_type", "1");//上架类型
			}
		};
		assertEquals(goodData.get_goodDifferDetail(goodsName), expectGoodDetail);
	}
	
	@Test
	public void test_addOverseasGoods() {
		Reporter.log(sellerAccount+":添加海外购商品（实物+实名）");
		HashMap<String, String> goodsInfo = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("goodsName", "海外购商品auto"+baseData.getNum(1, 10000));
				put("showOnIndex", "否");
				put("goodType", "实物商品");
				put("infoReal", "是");
				put("own", "否");
				put("belongHealth", "否");
				put("submitType", "audit");
			}
		};
		String goodsName =  this.addGoods(sellerAccount, goodsInfo);
		
		HashMap<String, String> expectGoodDetail = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("is_delivery", "0");//商品类型
				put("is_real_name", "1");//是否需要实名
				put("is_health", "0");//是否乐豆商品
				put("is_onlive", "2");//上架状态
				put("publish_wait", "0");//商品状态
				put("shelves_type", "1");//上架类型
			}
		};
		assertEquals(goodData.get_goodDifferDetail(goodsName), expectGoodDetail);
	}
	
	@Test
	public void test_addHealthGoods() {
		Reporter.log(sellerAccount+":添加乐豆商品");
		HashMap<String, String> goodsInfo = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("goodsName", "乐豆商品auto"+baseData.getNum(1, 10000));
				put("showOnIndex", "是");
				put("goodType", "实物商品");
				put("infoReal", "否");
				put("own", "是");
				put("belongHealth", "是");
				put("submitType", "audit");
			}
		};
		String goodsName =  this.addGoods(sellerAccount, goodsInfo);
		
		HashMap<String, String> expectGoodDetail = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("is_delivery", "0");//商品类型
				put("is_real_name", "0");//是否需要实名
				put("is_health", "1");//是否乐豆商品
				put("is_onlive", "2");//上架状态
				put("publish_wait", "0");//商品状态
				put("shelves_type", "1");//上架类型
			}
		};
		assertEquals(goodData.get_goodDifferDetail(goodsName), expectGoodDetail);
	}
	
	/*添加商品
	 * @seller	商家账号
	 * @goodsInfo	商品信息
	 * */
	public String addGoods(String seller,HashMap<String, String> goodsInfo) {
		//首先检查商家的运费模板是否存在
		this.check_Freight(seller);
		
		//添加商品
		WebDriver driver = loginUITest.sellerLogin(seller, "123456li");
		String goodsName = goodsInfo.get("goodsName");
		try {
			/*商家管理后台主页*/
			indexPage = PageFactory.initElements(driver, SellerIndexPage.class);
			indexPage.click_MainMenu("商品管理");
			indexPage.click_subMenu("商品列表");
			/*商品管理页面*/
			goodsPage = PageFactory.initElements(driver, SellerGoodsPage.class);
			goodsPage.click_addGoodBTN();
			/*商品添加页面*/
			Thread.sleep(2000);
			goodsPage.set_goodsName(goodsName);//商品名称
			goodsPage.set_ads("超越非凡，打造养老界的阿里巴巴");//商品介绍广告语
			goodsPage.setGoodCategory(driver);//随机选择商品分类
			goodsPage.set_storeWarning(baseData.getNum(5, 10));//库存警告
			goodsPage.set_showOnIndex(goodsInfo.get("showOnIndex"));//是否首页推荐
			goodsPage.set_goodType(goodsInfo.get("goodType"));//商品类型
			goodsPage.set_infoReal(goodsInfo.get("infoReal"));//是否需要实名认证
			goodsPage.set_own(goodsInfo.get("own"));//是否自营
			goodsPage.set_belongHealth(goodsInfo.get("belongHealth"));//是否福豆商品
			goodsPage.set_goodMainImg();//商品主图
			goodsPage.set_sellTime(driver, "立即上架");//设置上架时间
			goodsPage.set_goodsKeywords("test;helen");//设置商品关键词
			goodsPage.set_freightPay();//设置运费模版
			goodsPage.set_maxNB("0");//设置最大购买数，默认0为不限制，若填写限制数必须大于等于最小购买数
			goodsPage.set_attr();//设置商品规格属性
			goodsPage.set_SKU(goodsInfo.get("belongHealth"));//参数必须福豆商品一至
			goodsPage.set_skuImg();//设置SKU图片
			goodsPage.set_detail(driver);//设置商品详情
			
			if (goodsInfo.get("submitType").equals("audit")) {
				goodsPage.click_submitToAudit();//申请审核
			}
			else {
				goodsPage.click_saveDetailBTN();//保存为草稿
			}
			
			Thread.sleep(3000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return goodsName;
	}
	
	/*检查商家是否有运费模版，如果没有就添加
	 * @seller	商家账号
	 * */
	public void check_Freight(String seller) {
		String supplier_id = sellerData.get_sellerId(seller);
		if (sellerData.templateExit(supplier_id)) {
			sellerData.insert_template(supplier_id);
		}
	}

	/*商品上架
	 * @seller 商家账号
	 * @goodName 商品名称
	 * */
	public void good_updateShelfStatus(String seller,String goodName) {
		WebDriver driver = loginUITest.sellerLogin(seller, "123456li");
		try {
			SellerIndexPage sellerIndexPage = PageFactory.initElements(driver, SellerIndexPage.class);
			sellerIndexPage.click_MainMenu("商品管理");
			sellerIndexPage.click_subMenu("商品列表");
			SellerGoodsPage sellerGoodsPage = PageFactory.initElements(driver, SellerGoodsPage.class);
			sellerGoodsPage.set_s_goodsName(goodName);//输入商品名称
			sellerGoodsPage.click_searchBTN();//[搜索]
			
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
}
