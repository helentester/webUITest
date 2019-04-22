/**
 * @author helen
 * @date 2018年8月27日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseWinUI;
import common.MyConfig;
import common.MyRedis;
import data.ApiData;
import data.CouponData;
import data.GoodData;
import data.MemberData;
import data.OrderData;
import dataModel.SeveralGoodsShareModel;
import dataProvider.PCProvider;
import wufu.wufu.PCPages.PC_carInfoPage;
import wufu.wufu.PCPages.PC_goodsInfoPage;
import wufu.wufu.PCPages.PC_orderInfoEditPage;
import wufu.wufu.PCPages.PC_orderInfoSummarizePage;

/**
 * @Description:福豆和优惠券在订单中的使用
 */
public class PC_couponAndBeanInOrderTest {
	BaseWinUI baseWinUI = new BaseWinUI();
	ApiData apiData = new ApiData();
	MemberData memberData = new MemberData();
	GoodData goodData = new GoodData();
	OrderData orderData = new OrderData();
	CouponData couponData = new CouponData();
	MyRedis myRedis = new MyRedis();
	MyConfig myConfig = new MyConfig();
	LoginUITest login = new LoginUITest();
	
	@BeforeClass
	public void checkGoodStatus() {
		Reporter.log("保证商品是上架状态");
		goodData.update_onliveStatus(myConfig.getKeys("goods1"), "1");
		goodData.update_onliveStatus(myConfig.getKeys("goods2"), "1");
		goodData.update_onliveStatus(myConfig.getKeys("goods3"), "1");
		goodData.update_onliveStatus(myConfig.getKeys("goods4"), "1");
	}

	@Test(dataProvider = "oneGoodsShare", dataProviderClass = PCProvider.class)
	public void test_oneGoodsShare(String testcaseNB,String log, String sku, String price, String coupon, String bean, String usedCoupon,
			String usedBean, String money) {
		Reporter.log("测试用例编号："+testcaseNB);
		Reporter.log(log);
		String orderSN = this.buyOneGood(myConfig.getKeys("username1"), myConfig.getKeys("goods1"), sku, "1",
				Integer.valueOf(coupon), Double.valueOf(bean));// 237Good=7842;236Good=7773
		assertNotEquals(orderSN, "", "检查订单是否新增成功");

		// 订单表中分摊
		Reporter.log("订单号："+ orderSN);
		HashMap<String, String> ShareInOrder = orderData.get_PayShare(orderSN);
		assertNotEquals(ShareInOrder.get("resulSize"), "0", "检查是否生成订单分摊信息");
		assertEquals(Double.valueOf(ShareInOrder.get("score")), Double.valueOf(usedBean), "福豆分摊校验，订单号：" + orderSN);
		assertEquals(Double.valueOf(ShareInOrder.get("coupon")), Double.valueOf(usedCoupon), "优惠券分摊校验，订单号：" + orderSN);
		assertEquals(Double.valueOf(ShareInOrder.get("pay_price")), Double.valueOf(money) + 0.01,
				"现金分摊校验" );// 现金支付部份包含运费0.01

		// 订单统计表中分摊
		HashMap<String, String> ShareInStatic = orderData.get_statisticsPayShare(orderSN);
		assertNotEquals(ShareInStatic.get("resulSize"), "0", "检查是否生成订单统计表中的分摊信息");
		assertEquals(Double.valueOf(ShareInStatic.get("score")), Double.valueOf(usedBean), "福豆，订单号：" + orderSN);
		assertEquals(Double.valueOf(ShareInStatic.get("coupon")), Double.valueOf(usedCoupon), "优惠券，订单号：" + orderSN);
		assertEquals(Double.valueOf(ShareInStatic.get("pay_price")), Double.valueOf(money) + 0.01, "现金，订单号：" + orderSN);// 现金支付部份包含运费0.01

	}

	@Test(dataProvider = "severalGoodsShare", dataProviderClass = PCProvider.class)
	public void test_severalGoodsShare(SeveralGoodsShareModel shareModel) {
		Reporter.log("测试用例编号：" + shareModel.getTestCaseNB());
		String orderSNs = this.buySeveralGoods(myConfig.getKeys("username2"), shareModel.getCoupon(),
				shareModel.getBean());
		Reporter.log("订单号："+orderSNs);
		assertNotEquals(orderSNs, "", "检查订单是否新增成功");

		// 订单商品中的分摊
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 1; i < 10; i++) {
			map = orderData.get_ShareInOrderItems(orderSNs, "g" + i);
			assertTrue(Integer.valueOf(map.get("resultSize")) > 0, "校验是否生成订单商品中的分摊");
			assertEquals(Double.valueOf(map.get("coupon")), Double.valueOf(shareModel.getGoodsCoupon(i)), "gc" + i);// 分摊的优惠券
			//注意：APPV6.7.00上线后，福豆分摊规则更改，后台按SKU计算出抵扣的福豆后，以商家为维度，福豆先抵扣一个商家的所需福豆，再抵扣下一个商家的所需福豆（以所需福豆从小到大开始抵扣）
			assertEquals(Double.valueOf(map.get("score")), Double.valueOf(shareModel.getGoodsBean(i)), "gb" + i);// 分摊积分
			assertEquals(Double.valueOf(map.get("pay_price")), Double.valueOf(shareModel.getGoodsMoney(i)), "gm" + i);// 分摊现金
			
			Reporter.log("g"+i+"在订单商品表中的分摊"+map);
		}
		

		// 商品统计表中分摊
		for (int i = 1; i < 10; i++) {
			map = orderData.get_statisticsGoodsPayShare(orderSNs, "g" + i);
			assertTrue(Integer.valueOf(map.get("resultSize")) > 0, "校验是否生成订单商品中的分摊");
			assertEquals(Double.valueOf(map.get("coupon")), Double.valueOf(shareModel.getGoodsCoupon(i)), "sgc" + i);// 分摊的优惠券
			assertEquals(Double.valueOf(map.get("score")), Double.valueOf(shareModel.getGoodsBean(i)), "sgb" + i);// 分摊积分
			assertEquals(Double.valueOf(map.get("pay_price")), Double.valueOf(shareModel.getGoodsMoney(i)), "sgm" + i);// 分摊现金
			
			Reporter.log("g"+i+"在商品统计表中的分摊"+map);
		}
		
	}

	

	/*
	 * 购买多个商品
	 * 
	 * @account 用户账号
	 * 
	 * @coupon 有优惠券
	 * 
	 * @bean 福豆
	 */
	public String buySeveralGoods(String username, String useCoupon, String useBean) {
		String orderSNs = "";
		// 检查用户购物车及默认地址
		this.checkData(username);
		// 送优惠券
		if (Integer.valueOf(useCoupon) > 0) {
			apiData.getCoupon(username, Integer.valueOf(useCoupon));
		}
		
		// 修改福豆
		memberData.update_AbleScore(username, String.valueOf(useBean));

		// 下订单
		WebDriver driver = login.PCLogin(username, "123456li");
		try {
			Thread.sleep(1000);
			// 把第一个商品的3个SKU加入购物车
			driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=" + myConfig.getKeys("goods2"));// 237、236同样的ID7786
			Thread.sleep(1000);
			PC_goodsInfoPage goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			// 第一个SKU 3个
			goodsInfoPage.set_goodsNumber("3");
			goodsInfoPage.click_addCartBtn();
			// 第二个SKU
			goodsInfoPage.click_sku2();
			goodsInfoPage.click_addCartBtn();
			// 第三个SKU
			goodsInfoPage.click_sku3();
			goodsInfoPage.set_goodsNumber("2");
			goodsInfoPage.click_addCartBtn();

			// 第二个商品
			driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=" + myConfig.getKeys("goods3"));// 237Id=7833;236Id=7803
			Thread.sleep(2000);
			// SKU1
			goodsInfoPage.click_addCartBtn();
			// SKU2
			goodsInfoPage.click_sku2();
			goodsInfoPage.click_addCartBtn();
			// SKU3
			goodsInfoPage.click_sku3();
			goodsInfoPage.set_goodsNumber("2");
			goodsInfoPage.click_addCartBtn();

			// 第三个商品
			driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=" + myConfig.getKeys("goods4"));// 237Id=7848;236Id=7818
			Thread.sleep(2000);
			// SKU1
			goodsInfoPage.click_addCartBtn();
			// SKU2
			goodsInfoPage.click_sku2();
			goodsInfoPage.set_goodsNumber("5");
			goodsInfoPage.click_addCartBtn();
			// SKU3
			goodsInfoPage.click_sku3();
			goodsInfoPage.click_addCartBtn();

			goodsInfoPage.click_cartLink();
			Thread.sleep(3000);
			// 购物车页面
			baseWinUI.changeWindow(driver);
			PC_carInfoPage carInfoPage = PageFactory.initElements(driver, PC_carInfoPage.class);
			carInfoPage.click_checkAllTop();
			carInfoPage.click_gotoPayBTN();

			// 订单页面
			Thread.sleep(1000);
			PC_orderInfoEditPage pc_orderInfoEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
			if (Integer.valueOf(useCoupon) > 0) {
				pc_orderInfoEditPage.click_coupon1();// 点击第一个优惠券
			}
			Thread.sleep(1000);
			pc_orderInfoEditPage.set_balancePrice("0");//余额支付
			Thread.sleep(1000);
			pc_orderInfoEditPage.click_gotoPay();// 提交订单

			Thread.sleep(6000);
			PC_orderInfoSummarizePage pc_orderInfoSummarizePage = PageFactory.initElements(driver,
					PC_orderInfoSummarizePage.class);
			Set<String> orderList = pc_orderInfoSummarizePage.get_orderNBs();
			String str = String.valueOf(orderList);
			orderSNs = str.substring(1, str.length() - 1);
			
			pc_orderInfoSummarizePage.click_payBtn();
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		return orderSNs;
	}

	/*
	 * 购买单个商品
	 * 
	 * @SUK SKU
	 * 
	 * @useCoupon 是否使用优惠券true false
	 * 
	 * @useBean 是否使用福豆true false
	 */
	public String buyOneGood(String username, String goodsId, String SKU, String count, int useCoupon, double useBean) {
		Reporter.log("会员账号："+username);
		// 检查用户购物车及默认地址
		this.checkData(username);

		String orderSN = "";
		// 送优惠券
		if (useCoupon > 0) {
			apiData.getCoupon(username, useCoupon);
		}
		// 修改福豆
		memberData.update_AbleScore(username, String.valueOf(useBean));

		// 下订单
		WebDriver driver = login.PCLogin(username, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=" + goodsId);

			// 商品详情页面
			Thread.sleep(1000);
			PC_goodsInfoPage goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			if (SKU.equals("sku1")) {
				goodsInfoPage.click_sku1();
			} else if (SKU.equals("sku2")) {
				goodsInfoPage.click_sku2();
			} else if (SKU.equals("sku3")) {
				goodsInfoPage.click_sku3();
			} else {
				System.out.println("选择SKU失败");
			}
			goodsInfoPage.set_goodsNumber(count);// 商品个数
			goodsInfoPage.click_buyBtn();// 点击立即购买

			Thread.sleep(3000);
			PC_orderInfoEditPage pc_orderInfoEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
			if (useCoupon > 0) {
				pc_orderInfoEditPage.click_coupon1();// 点击第一个优惠券
			}
			Thread.sleep(2000);
			pc_orderInfoEditPage.click_gotoPay();// 提交订单

			Thread.sleep(3000);
			PC_orderInfoSummarizePage pc_orderInfoSummarizePage = PageFactory.initElements(driver,
					PC_orderInfoSummarizePage.class);
			Set<String> orderList = pc_orderInfoSummarizePage.get_orderNBs();
			orderSN = orderList.iterator().next();

			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		return orderSN;

	}

	/*
	 * 检查数据
	 * 
	 * @account
	 */
	public void checkData(String account) {
		// 从数据库和缓存中把当前用户的购物车先清空（避免影响数据，因为购物车是全选的）
		String user_id = memberData.get_Id(account);
		memberData.clear_cart(account);
		// 从mysql中删除
		myRedis.redis_del(user_id + "-CART");// 从redis中删除

		// 如果用户没有默认地址，设置一个
		if (memberData.defaultAddressExit(user_id)) {
			memberData.insert_defaultAddress(user_id, account);
		}

		// 删除用户已有优惠券
		couponData.update_deleteStatus(account);
	}

}
