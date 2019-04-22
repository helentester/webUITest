/**
 * @author helen
 * @date 2018年8月23日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseData;
import common.MyConfig;
import data.GoodData;
import data.MemberData;
import data.OrderData;
import wufu.wufu.PCPages.PC_goodsInfoPage;
import wufu.wufu.PCPages.PC_myEvaluatePage;
import wufu.wufu.PCPages.PC_myOrderPage;
import wufu.wufu.PCPages.PC_mySaledServicePage;
import wufu.wufu.PCPages.PC_orderInfoEditPage;

/**
 * @Description:PC商城，个人中心－我的订单相关测试
 */
public class PC_myOrderTest {
	BaseData baseData = new BaseData();
	MyConfig myConfig = new MyConfig();
	MemberData memberData = new MemberData();
	GoodData goodData = new GoodData();
	OrderData orderData = new OrderData();
	LoginUITest loginUITest = new LoginUITest();
	PC_registTest pc_registTest = new PC_registTest();
	PC_orderTest pc_orderTest = new PC_orderTest();
	PC_couponAndBeanInOrderTest pc_couponAndBeanInOrderTest = new PC_couponAndBeanInOrderTest();
	SellerOrderTest sellerOrderTest = new SellerOrderTest();
	SysOrderTest sysOrderTest = new SysOrderTest();
	SysEvaluateTest sysEvaluateTest = new SysEvaluateTest();
	
	@BeforeClass
	public void checkTestData() {
		Reporter.log("保证商品是上架状态");
		goodData.update_onliveStatus("7813", "1");
		goodData.update_onliveStatus("7215", "1");
	}

	@Test
	public void test_BeanGood_couponAndBeanRefund_1() {
		Reporter.log("纯积分商品下订单，使用优惠券+积分支付完成，申请退款成功，校验积分和优惠券的退还情况");

		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新用户："+username);
		pc_registTest.regist("phone", username);
		assertTrue(memberData.memberExit(username),"校验用户是否新增成功");
		
		pc_couponAndBeanInOrderTest.buyOneGood(username, "7813", "sku2", "1", 200, 80);
		String orderSN = memberData.get_orderSN(username, "3");
		Reporter.log("2）会员下订单，订单号："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");
		
		Reporter.log("3）会员申请退款");
		this.afterSaleService("money", username, orderSN, 0);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		Reporter.log("4）商家审核退款：审核通过");// 商家审核
		sellerOrderTest.auditRefund(myConfig.getKeys("seller2"), orderSN);// 237seller=15206215781;236seller=18605693936
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");
		
		Reporter.log("5）运营中心审核退款：审核通过");// 后台审核
		sysOrderTest.refundAudit(orderSN,"通过");
		
		Reporter.log("6）校验福豆的返还：全退");
		assertEquals(Double.valueOf(memberData.get_ableScore(username)), Double.valueOf("80"),"退款审核通过后，校验福豆的返还：全退");// 退款后，积分被退还
		
		Reporter.log("7）验优惠券的返还：不退");
		assertEquals(memberData.get_lastCouponUseStatus(username), "1","退款审核通过后，校验优惠券的返还：不退");// 优惠券不退
	}

	@Test
	public void test_BeanGood_couponAndBeanRefund_3() {
		Reporter.log("(部份退)纯积分商品下订单，使用优惠券+积分支付完成－后台发货－前台收货－申请退货－商家审核通过－后台审核通过，校验积分和优惠券的退还情况");

		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新用户："+username);
		pc_registTest.regist("phone", username);

		pc_couponAndBeanInOrderTest.buyOneGood(username, "7813", "sku1", "3", 5, 4);
		String orderSN = memberData.get_orderSN(username, "3");
		Reporter.log("2）会员生成订单："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");
		
		Reporter.log("3）运营中心发货");// 后台发货
		sysOrderTest.deliverGoods(orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "4", "检验是否发货成功");
		
		Reporter.log("4）会员确认收货");// 前台确认收货
		this.confirmDeliver(username, orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "5", "检验确认发货是否成功");
		
		Reporter.log("5）会员申请售后");// 前台申请售后
		this.afterSaleService("goods", username, orderSN, 2);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		Reporter.log("6）商家审核退货申请：审核通过");// 商家审核
		sellerOrderTest.auditRefund(myConfig.getKeys("seller2"), orderSN);// 237seller=15206215781;236seller=18605693936
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");
		
		Reporter.log("7）运营中心审核退货申请：审核通过");// 后台审核
		sysOrderTest.refundAudit(orderSN,"通过");
		assertEquals(Double.valueOf(memberData.get_ableScore(username)), Double.valueOf("2.66"));// 退款后，积分被退还
		assertEquals(memberData.get_lastCouponUseStatus(username), "1");// 优惠券不退
	}

	@Test
	public void test_BeanGood_couponAndBeanRefund_2() {
		Reporter.log("(全退)纯积分商品下订单，使用优惠券+积分支付完成－后台发货－前台收货－申请退货－商家审核通过－后台审核通过，校验积分和优惠券的退还情况");
		try {
			// 注册新用户
			String username = baseData.getPhoneNumber();
			Reporter.log("1）注册新用户："+username);
			pc_registTest.regist("phone", username);
			
			// 下订单
			pc_couponAndBeanInOrderTest.buyOneGood(username, "7813", "sku2", "1", 200, 80);
			String orderSN = memberData.get_orderSN(username, "3");
			Reporter.log("2）会员下订单，订单号："+orderSN);
			assertNotEquals(orderSN, "", "检查订单是否新增成功");
			
			Reporter.log("3）运营中心发货");// 后台发货
			sysOrderTest.deliverGoods(orderSN);
			Thread.sleep(1000);
			assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "4", "检验是否发货成功");
			
			Reporter.log("4）会员确认收货");// 前台确认收货
			this.confirmDeliver(username, orderSN);
			assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "5", "检验确认发货是否成功");
			
			Reporter.log("5）会员申请售后：退货退款");// 前台申请售后
			this.afterSaleService("goods", username, orderSN, 0);
			assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
			
			Reporter.log("6）商家审核退货退款：审核通过");// 商家审核
			sellerOrderTest.auditRefund(myConfig.getKeys("seller2"), orderSN);// 237seller=15206215781;236seller=18605693936
			assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");
			
			Reporter.log("7）运营中心审核退货退款：审核通过");// 后台审核
			sysOrderTest.refundAudit(orderSN,"通过");
			
			Reporter.log("8）校验会员福豆的退还结果：全部退还");
			assertEquals(Double.valueOf(memberData.get_ableScore(username)), Double.valueOf("80"));// 退款后，积分被退还
			
			Reporter.log("9）校验会员优惠券退还结果：不退");
			assertEquals(memberData.get_lastCouponUseStatus(username), "1");// 优惠券不退
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void test_deliver_evaluate() {
		Reporter.log("购买乐豆商品－后台确认发货－前台确认收货－前台评价-后台审核评价通过");

		// 注册新用户
		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新会员："+username);
		pc_registTest.regist("phone", username);
		assertTrue(memberData.memberExit(username), "校验用户是否注册成功：" + username);
		
		Reporter.log("2）数据库中设置会员的收货地址");// 检查用户账号
		pc_orderTest.check_addrAndCart(username);
		
		Reporter.log("3）数据库中设置会员的乐豆为100");// 设置乐豆
		memberData.update_HScore(username, "100");
		
		// 下订单
		this.buy(username, "7215", "1");
		String orderSN = memberData.get_orderSN(username, "3");// 获取已支付待发货的订单号
		Reporter.log("4）会员下订单，订单号："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");
		
		Reporter.log("5）运营后台发货");// 后台发货
		sysOrderTest.deliverGoods(orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "4", "检验是否发货成功");
		
		Reporter.log("6）会员确认收货");// 前台确认收货
		this.confirmDeliver(username, orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "5", "检验确认发货是否成功");
		
		Reporter.log("7）会员进行商品评论");// 评论商品
		this.evaluate(username, orderSN);
		assertTrue(memberData.evaluateExit(username, orderSN), "检验评论是否成功");
		
		Reporter.log("8）运营中心审核评论：审核通过");// 后台审核评论
		sysEvaluateTest.auditEvaluate(orderSN);
		assertEquals(orderData.get_EvaluateAuditStatus(orderSN), "1", "订单号：" + orderSN);
	}

	@Test
	public void test_refund_money() {
		Reporter.log("购买纯积分商品－申请售后（仅退款）-商家审核通过－后台审核通过(积分归还给用户");

		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新会员："+username);
		pc_registTest.regist("phone", username);
		assertTrue(memberData.memberExit(username), "校验用户是否注册成功：" + username);

		Reporter.log("2）通过数据库给会员添加默认收货地址和清空购物车");
		pc_orderTest.check_addrAndCart(username);
		
		String ableScore_buyBefore = memberData.get_ableScore(username);
		Reporter.log("3）通过数据库获得会员的现有福豆为："+ableScore_buyBefore);

		this.buy(username, "7813", "3");
		String orderSN = memberData.get_orderSN(username, "3");// 获取已支付待发货的订单号
		Reporter.log("4）购买纯积分商品，订单号为："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");

		Reporter.log("5）申请售后：仅退款");
		this.afterSaleService("money", username, orderSN, 0);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		String seller=myConfig.getKeys("seller2");
		Reporter.log("6）商家("+seller+")审核退款申请：审核通过");
		sellerOrderTest.auditRefund(seller, orderSN);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");

		Reporter.log("7）后台审核退款：审核通过");
		sysOrderTest.refundAudit(orderSN,"通过");
		assertEquals(memberData.get_ableScore(username), ableScore_buyBefore, "检查福豆是否退还,订单号：" + orderSN);
	}

	@Test
	public void test_refund_goods() {
		Reporter.log("新用户注册－购买纯积分商品（支付成功）－后台发货－前台确认收货－前台申请售后－商家审核通过－后台审核通过(积分返还)");

		Reporter.log("[会员操作]－－－－－");
		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新会员："+username);
		pc_registTest.regist("phone", username);
		assertTrue(memberData.memberExit(username), "校验用户是否注册成功：" + username);
		
		Reporter.log("2）数据库中操作，给会员设置默认地址");
		pc_orderTest.check_addrAndCart(username);

		String ableScore_buyBefore = memberData.get_ableScore(username);
		Reporter.log("3）数据库中取得当前会员的福豆数为："+ableScore_buyBefore);
		
		this.buy(username, "7813", "3");
		String orderSN = memberData.get_orderSN(username, "3");// 获取已支付待发货的订单号
		Reporter.log("4）购买纯积分商品，订单号为："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");
		
		Reporter.log("[运营中心操作]－－－－－－");
		Reporter.log("1）发货");// 后台发货
		sysOrderTest.deliverGoods(orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "4", "检验是否发货成功");
		
		Reporter.log("[会员操作]－－－－－");
		Reporter.log("1）会员确认收货");// 前台确认收货
		this.confirmDeliver(username, orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "5", "检验确认发货是否成功");
		
		Reporter.log("2）会员前台申请售后");// 前台申请售后
		this.afterSaleService("goods", username, orderSN, 2);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		Reporter.log("[商家中心操作]－－－－－－");
		Reporter.log("1）审核退货退款申请：审核通过");// 商家审核
		sellerOrderTest.auditRefund(myConfig.getKeys("seller2"), orderSN);// 237seller=15206215781;236seller=18605693936
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");
		
		Reporter.log("[运营中心操作]－－－－－－");
		Reporter.log("1）退货退款审核：审核通过");// 后台审核
		sysOrderTest.refundAudit(orderSN,"通过");
		
		Reporter.log("2）数据库中检查福豆是否退还到会员账号中");
		assertEquals(Float.valueOf(memberData.get_ableScore(username)), Float.valueOf(ableScore_buyBefore) - 3,
				"订单号：" + orderSN);

	}
	
	@Test
	public void test_refund2() {
		Reporter.log("（PC1.2版本新优化）主要校验下单后退款被拒绝，然后收货后再次发起退货退款的情况");
		// 注册新用户
		String username = baseData.getPhoneNumber();
		Reporter.log("1）注册新用户："+username);
		pc_registTest.regist("phone", username);
		assertTrue(memberData.memberExit(username), "校验用户是否注册成功：" + username);
		// 检查账号:主要设置会员的收货地址
		pc_orderTest.check_addrAndCart(username);
		
		// 获取购买前福豆数量
		String ableScore_buyBefore = memberData.get_ableScore(username);
		Reporter.log("2）下订单前，会员的福豆数量："+ableScore_buyBefore);
		assertEquals(Float.valueOf(memberData.get_ableScore(username)), Float.valueOf("100"),"新注册用户应当送100福豆");
		
		// 购买纯积分商品
		this.buy(username, "7813", "3");
		String orderSN = memberData.get_orderSN(username, "3");// 获取已支付待发货的订单号
		Reporter.log("3）会员下订单，订单号："+orderSN);
		assertNotEquals(orderSN, "", "检查订单是否新增成功");
		
		Reporter.log("4）会员申请售后：退款");// 前台申请售后
		this.afterSaleService("money", username, orderSN, 0);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		Reporter.log("5）运营中心退款审核：拒绝退款");//后台拒绝退款申请
		sysOrderTest.refundAudit(orderSN,"拒绝");
		
		Reporter.log("6）运营中心发货");// 后台发货
		sysOrderTest.deliverGoods(orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "4", "检验是否发货成功");
		
		Reporter.log("7）会员确认收货");// 前台确认收货
		this.confirmDeliver(username, orderSN);
		assertEquals(orderData.getKeyValue_byOrderSN(orderSN, "order_status"), "5", "检验确认发货是否成功");
		
		Reporter.log("8）会员申请售后：退货退款");// 前台申请售后
		this.afterSaleService("goods", username, orderSN, 2);
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "0", "校验申请售后是否成功");
		
		Reporter.log("9）商家审核退货退款：审核通过");// 商家审核
		sellerOrderTest.auditRefund(myConfig.getKeys("seller2"), orderSN);// 237seller=15206215781;236seller=18605693936
		assertEquals(orderData.getKey_byOrderSN_RefundT(orderSN, "supplier_status"), "1", "校验商家是否审核成功");
		
		Reporter.log("10）运营中心审核退货退款：审核通过");// 后台审核
		sysOrderTest.refundAudit(orderSN,"通过");
		assertEquals(Float.valueOf(memberData.get_ableScore(username)), Float.valueOf(ableScore_buyBefore) - 3,
				"订单号：" + orderSN);
	}

	/*
	 * 申请售后
	 * 
	 * @refundType 售后类型 ：money、goods
	 * 
	 * @account 用户账号
	 * 
	 * @orderSN 订单号
	 * 
	 * @goodsCount 退货数量
	 */
	public void afterSaleService(String refundType, String account, String orderSN, int goodsCount) {
		WebDriver driver = loginUITest.PCLogin(account, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(myConfig.getKeys("PC_domainName") + "/personalCenter/presonalMyorder");
			Thread.sleep(1000);
			PC_myOrderPage myOrderPage = PageFactory.initElements(driver, PC_myOrderPage.class);
			myOrderPage.set_querygoodandsn(orderSN);// 输入订单编号
			myOrderPage.click_orderSearchBTN();// 点击查询按钮
			Thread.sleep(3000);
			myOrderPage.click_afterSaleBTN();// 点击[申请售后]按钮

			// 申请页面
			Thread.sleep(3000);
			PC_mySaledServicePage mySaledServicePage = PageFactory.initElements(driver, PC_mySaledServicePage.class);

			if (refundType.equals("goods")) {
				// 提交数量
				for (int i = 1; i < goodsCount; i++) {
					mySaledServicePage.click_plusNB();
				}
				mySaledServicePage.set_description("测试退货退款");// 问题描述
				mySaledServicePage.set_deliveryName("test快递名称");
				mySaledServicePage.set_deliveryNo("8886666");// 快递单号
				mySaledServicePage.set_refundName("helen");// 退货人
				mySaledServicePage.set_mobile(baseData.getPhoneNumber());// 退货人联系电话
				mySaledServicePage.click_submitBTN();// [提交]按钮
			} else {
				mySaledServicePage.set_description2("测试仅退款");
				mySaledServicePage.click_submitBTNb();// [提交]
			}

			driver.quit();

		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}

	/*
	 * 确认收货
	 * 
	 * @account 用户账号
	 * 
	 * @orderSN 订单号
	 */
	public void confirmDeliver(String account, String orderSN) {
		WebDriver driver = loginUITest.PCLogin(account, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(myConfig.getKeys("PC_domainName") + "/personalCenter/presonalMyorder");
			Thread.sleep(1000);
			PC_myOrderPage myOrderPage = PageFactory.initElements(driver, PC_myOrderPage.class);
			myOrderPage.set_querygoodandsn(orderSN);// 输入订单编号
			myOrderPage.click_orderSearchBTN();// 点击查询按钮
			Thread.sleep(3000);
			myOrderPage.click_confirmReceiptBTN();// 确认收货按钮
			myOrderPage.click_confirmBTN();// 确认按钮
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}

	/*
	 * 评论商品
	 * 
	 * @account 用户账号
	 * 
	 * @orderSN
	 */
	public void evaluate(String account, String orderSN) {
		WebDriver driver = loginUITest.PCLogin(account, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(myConfig.getKeys("PC_domainName") + "/personalCenter/presonalMyorder");
			Thread.sleep(1000);
			PC_myOrderPage myOrderPage = PageFactory.initElements(driver, PC_myOrderPage.class);
			myOrderPage.set_querygoodandsn(orderSN);// 输入订单编号
			myOrderPage.click_orderSearchBTN();// 点击查询按钮
			Thread.sleep(3000);
			myOrderPage.click_gotoEvaluate();// 点击[去评价]按钮

			// 评价页面
			PC_myEvaluatePage myEvaluatePage = PageFactory.initElements(driver, PC_myEvaluatePage.class);
			int stars = baseData.getNum(1, 5);
			myEvaluatePage.click_star(stars);// 选择星级
			myEvaluatePage.set_content("测试数据，评价商品，星级为：" + String.valueOf(stars));// 评价内容
			int a = baseData.getNum(0, 2);
			if (a==0) {
				myEvaluatePage.uploadJPG();//上传图片
			}
			else if (a==1) {
				myEvaluatePage.uploadMV();//上传视频
			}
			myEvaluatePage.click_submitBTN();// [评价]按钮
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}

	/*
	 * 下订单,单个商品，支付成功
	 * 
	 * @account 用户账号
	 * 
	 * @goodId 商品ID
	 * 
	 * @count 购买商品数量
	 */
	public void buy(String account, String goodId, String count) {
		WebDriver driver = loginUITest.PCLogin(account, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=" + goodId);

			// 商品详情页
			Thread.sleep(1000);
			PC_goodsInfoPage goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			goodsInfoPage.set_goodsNumber(count);// 输入购买数量
			goodsInfoPage.click_buyBtn();// 点击立即购买

			// 订单信息页面
			Thread.sleep(3000);
			PC_orderInfoEditPage orderInfoEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
			orderInfoEditPage.click_gotoPay();// 提交订单
			Thread.sleep(1000);
			driver.quit();
		} catch (Exception e) {
			//driver.quit();
			e.printStackTrace();
		}
	}

}
