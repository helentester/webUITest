/**
 * @author helen
 * @date 2018年7月24日
 */
package wufu.wufu;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import common.MyRedis;
import data.GoodData;
import data.MemberData;
import data.OrderData;
import wufu.wufu.PCPages.PC_carInfoPage;
import wufu.wufu.PCPages.PC_goodsInfoPage;
import wufu.wufu.PCPages.PC_orderInfoEditPage;
import wufu.wufu.PCPages.PC_orderInfoSummarizePage;

/**
 * @Description:PC商城下订单操作
 */
public class PC_orderTest {
	LoginUITest login = new LoginUITest();
	BaseData baseData = new BaseData();
	BaseWinUI baseWinUI = new BaseWinUI();
	MyConfig myConfig = new MyConfig();
	MyRedis myRedis = new MyRedis();
	MemberData memberData = new MemberData();
	OrderData orderData = new OrderData();
	GoodData goodData = new GoodData();
	PC_goodsInfoPage goodsInfoPage;
	PC_orderInfoEditPage orderEditPage;
	PC_orderInfoSummarizePage orderInfoSummarizePage;
	PC_carInfoPage carInfoPage;
	String username =myConfig.getKeys("username2");// "13825464584";// 会员账号d
	String goodsURL = myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id=";
	private HashMap<String, String> pageData = new HashMap<String, String>();
	PC_couponAndBeanInOrderTest pc_couponAndBeanInOrderTest = new PC_couponAndBeanInOrderTest();

	@BeforeClass(description = "检查测试数据")
	public void test_checkData() {
		List<String> goodIds = new ArrayList<String>(Arrays.asList("7181","7182","7183","7192","7228","7090","7215","7786"));
		this.check_goods(goodIds);
		this.check_addrAndCart(username);
	}
	
	@BeforeMethod(description="把pageData清空")
	public void data_init() {
		pageData.clear();
	}
	
	@Test
	public void test_SeveralGoods_couponAndBeanUsed() {
		Reporter.log("多个商品下订单，使用优惠券+积分+现金支付，校验优惠券和积分的使用情况");

		pc_couponAndBeanInOrderTest.buySeveralGoods(username, "100", "133");
		assertEquals(Double.valueOf(memberData.get_ableScore(username)), Double.valueOf("1.27"),"检查用户福豆使用情况");//积分使用情况
		assertEquals(memberData.get_lastCouponUseStatus(username), "1","检查优惠券是否已经是使用状态");
	}
	
	@Test
	public void test_service() {
		Reporter.log("单个虚拟商品，立即购买");
		WebDriver driver = this.order_immediately("7183");
		this.CreatOrder(driver, "service");
		// 设置期望值
		HashMap<String, String> targetData = new HashMap<String, String>();
		targetData.put("goodsTotal", "0.01");// 商品金额
		targetData.put("amount", "0.01");// 应付金额
		targetData.put("allDiscount_page1", "0.00");// 优惠金额
		targetData.put("orderNBsCount","1"); //生成的订单数
		//targetData.put("orderNBs", "same");// 生成订单页面，判断订单生成是否一至
		targetData.put("goodNames", "same");// 商品列表
		targetData.put("totalPay", "0.01");// 生成订单页面，应付金额
		//targetData.put("codeDisplay", "display");// 收款码是否出现(注释原因，模拟支付不再出现二维码)

		// 断言
		assertEquals(pageData, targetData);
	}

	@Test
	public void test_bigHealth() {
		Reporter.log("单个乐豆商品，立即购买（支付成功）");
		
		Double HScore_beforPay =50.00;
		memberData.update_HScore(username, HScore_beforPay.toString());//设置当前用户的乐豆
		// 执行页面操作
		WebDriver driver = this.order_immediately("7215");
		this.CreatOrder(driver, "bigHealth");
		// 设置期望输出值
		HashMap<String, String> targetData = new HashMap<String, String>();
		targetData.put("HScore_beforPay", HScore_beforPay.toString());// 支付前乐豆
		targetData.put("goodsTotal", "5");// 商品金额
		targetData.put("freight", "0.01");// 运费
		targetData.put("amount", "5.01");// 应付金额
		targetData.put("receiver_page1", "收货人：helenli 13825464584");// 收货人
		targetData.put("addrDetail_page1", "寄送至：广东省 广州市 越秀区 小北");// 收货地址
		targetData.put("payStatus", "hadPay");//支付状态，已支付
		targetData.put("HScore_afterPay", String.valueOf((BigDecimal.valueOf(HScore_beforPay)).subtract(BigDecimal.valueOf(5.01))));//检查支付后剩余乐豆
		// 检查订单数据
		assertEquals(pageData, targetData);
	}

	@Test
	public void test_nomal() {
		Reporter.log("单个普通商品，立即购买");
		WebDriver driver = this.order_immediately("7182");
		
		this.CreatOrder(driver, "nomal");
		// 设置期望值
		HashMap<String, String> targetData = new HashMap<String, String>();
		targetData.put("goodsTotal", "0.01");// 商品金额
		targetData.put("freight", "0.01");// 运费
		targetData.put("amount", "0.02");// 应付金额
		targetData.put("receiver_page1", "收货人：helenli 13825464584" );// 收货人
		targetData.put("addrDetail_page1", "寄送至：广东省 广州市 越秀区 小北");// 收货地址
		targetData.put("allDiscount_page1", "0.00");// 优惠金额
		targetData.put("orderNBsCount","1"); //生成的订单数
		//targetData.put("orderNBs", "same");// 生成订单页面，判断订单生成是否一至
		targetData.put("goodNames", "same");// 商品列表
		targetData.put("totalPay", "0.02");// 生成订单页面，应付金额
		targetData.put("address_page2", "广东省广州市越秀区小北");// 生成订单页面，收货地址
		targetData.put("contact", "13825464584");// 收货人电话
		//targetData.put("codeDisplay", "display");// 收款码是否出现(注释原因，模拟支付不再出现二维码)

		// 断言
		assertEquals(pageData, targetData);
	}

	@Test
	public void test_nomal_moreSunDaoGoods() {
		Reporter.log("多个顺道天下的商品，根据商品的不同生成多个订单");
		//把用户的福豆去掉
		memberData.update_AbleScore(username, "0");
		//下单
		WebDriver driver = login.PCLogin(username, "123456li");
		try {
			driver.get(goodsURL + "7090");
			goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			goodsInfoPage.click_addCartBtn();// 把第1个顺道天下的商品加入购物车
			driver.get(goodsURL + "7093");
			goodsInfoPage.click_plusNumberBTN();// 点击“+”，商品数量加1
			goodsInfoPage.click_addCartBtn();// 把第2个顺道天下的商品加入购物车
			goodsInfoPage.click_cartLink();// 点击购物车，打开购物车页面
			//购物车页面
			baseWinUI.changeWindow(driver);// 因为购物车是另起标签页打开，所以要切换
			carInfoPage = PageFactory.initElements(driver, PC_carInfoPage.class);
			carInfoPage.click_checkAllTop();// 全选
			assertEquals(carInfoPage.get_cartTotal(), "¥3.00", "校验购物车的商品总价");
			carInfoPage.click_gotoPayBTN();// 去结算

			//生成订单
			this.CreatOrder(driver, "nomal");
			// 设置期望值
			HashMap<String, String> targetData = new HashMap<String, String>();
			targetData.put("goodsTotal", "3.00");// 商品金额
			targetData.put("freight", "0.04");// 运费
			targetData.put("amount", "3.04");// 应付金额
			targetData.put("receiver_page1", "收货人：helenli 13825464584" );// 收货人
			targetData.put("addrDetail_page1", "寄送至：广东省 广州市 越秀区 小北");// 收货地址
			targetData.put("allDiscount_page1", "0.00");// 优惠金额
			targetData.put("orderNBsCount","2"); //生成的订单数，两个商品对应两个订单
			//targetData.put("orderNBs", "same");// 生成订单页面，判断订单生成是否一至
			targetData.put("goodNames", "same");// 商品列表
			targetData.put("totalPay", "3.04");// 生成订单页面，应付金额
			targetData.put("address_page2", "广东省广州市越秀区小北");// 生成订单页面，收货地址
			targetData.put("contact", "13825464584");// 收货人电话
			//targetData.put("codeDisplay", "display");// 收款码是否出现(注释原因，模拟支付不再出现二维码)

			// 断言
			assertEquals(pageData, targetData);
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
	}

	@Test
	public void test_order_moreNomal() {
		Reporter.log("综合订单(包含所在类别商品)，包含：3个商家，4个商品，其中包含顺道天下的商品");
		
		//把用户的福豆清空
		memberData.update_AbleScore(username, "0");

		// 把当前用户的购物车先清空（避免影响数据，因为购物车是全选的）
		memberData.clear_cart(username);

		WebDriver driver = login.PCLogin(username, "123456li");
		try {
			//把商品加入购物车
			driver.get(goodsURL + "7182");
			goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			goodsInfoPage.click_addCartBtn();// 把第一个普通商品加入购物车
			driver.get(goodsURL + "7192");
			goodsInfoPage.click_addCartBtn();// 把第二个普通商品加入购物车
			driver.get(goodsURL + "7228");
			goodsInfoPage.click_addCartBtn();// 把第三个普通商品加入购物车
			driver.get(goodsURL + "7090");
			goodsInfoPage.click_addCartBtn();// 把第四个商品加入购物车（顺道天下的商品）
			goodsInfoPage.click_cartLink();// 点击购物车，打开购物车页面

			//购物车页面 
			Thread.sleep(1000);
			baseWinUI.changeWindow(driver);// 因为购物车是另起标签页打开，所以要切换
			carInfoPage = PageFactory.initElements(driver, PC_carInfoPage.class);
			carInfoPage.click_checkAllTop();// 全选
			carInfoPage.click_gotoPayBTN();// 去结算

			//页面操作
			this.CreatOrder(driver, "nomal");
			// 设置期望值
			HashMap<String, String> targetData = new HashMap<String, String>();
			targetData.put("goodsTotal", "1.03");// 商品金额
			targetData.put("freight", "0.05");// 运费
			targetData.put("amount", "1.08");// 应付金额
			targetData.put("receiver_page1", "收货人：helenli 13825464584" );// 收货人
			targetData.put("addrDetail_page1", "寄送至：广东省 广州市 越秀区 小北");// 收货地址
			targetData.put("allDiscount_page1", "0.00");// 优惠金额
			targetData.put("orderNBsCount","3"); //生成的订单数
		//	targetData.put("orderNBs", "same");// 生成订单页面，判断订单生成是否一至
			targetData.put("goodNames", "same");// 商品列表
			targetData.put("totalPay", "1.08");// 生成订单页面，应付金额
			targetData.put("address_page2", "广东省广州市越秀区小北");// 生成订单页面，收货地址
			targetData.put("contact", "13825464584");// 收货人电话
			//targetData.put("codeDisplay", "display");// 收款码是否出现(注释原因，模拟支付不再出现二维码)

			// 断言
			assertEquals(pageData, targetData);
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}

	/*生成订单*/
	public HashMap<String, String> CreatOrder(WebDriver driver, String goodType) {
		try {
			Thread.sleep(5000);
			/** 订单信息页面,校验订单数据 **/
			orderEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
			if (goodType.equals("service")) {// 虚拟商品需要填写联系人信息
				orderEditPage.set_memberAccount("消费者" + baseData.getNum(1, 1000));// 真实姓名
				orderEditPage.set_mobile(baseData.getPhoneNumber());// 手机号码
				orderEditPage.set_idCardInfo(baseData.getIdCard());// 身份证号
			}
			//orderEditPage.set_remark("auto test,goodType:" + goodType);// 订单备注   20190130去掉，改为给每个商家留言
			
			// 获取订单信息
			if (goodType.equals("bigHealth")) {//
				pageData.put("HScore_beforPay", String.valueOf(Double.valueOf(orderEditPage.get_hscoreC())));// 消费前账户乐豆余额
			} else {
				orderEditPage.set_balancePrice("0");//使用余额支付的值
				Thread.sleep(1000);
				pageData.put("allDiscount_page1", orderEditPage.get_allDiscount());// 优惠金额
			}
			if (goodType.equals("service")) {// 虚拟商品没有配送信息
				System.out.println("虚拟商品没有配送信息");
			} else {// 乐豆商品和普通商品有配送信息
				pageData.put("freight", orderEditPage.get_freight());// 配送费用
				pageData.put("addrDetail_page1", orderEditPage.get_addrDetail());// 收货地址
				pageData.put("receiver_page1", orderEditPage.get_receiver());// 收货人
			}
			pageData.put("goodsTotal", orderEditPage.get_total());// 总商品金额
			pageData.put("amount", orderEditPage.get_amount());// 应付金额

			orderEditPage.click_gotoPay();// 点击提交订单按钮

			/** 订单生成页面，如果是乐豆商品则是支付成功页面 **/
			Thread.sleep(5000);
			orderInfoSummarizePage = PageFactory.initElements(driver, PC_orderInfoSummarizePage.class);
			if (goodType.equals("bigHealth")) {
				pageData.put("payStatus", orderInfoSummarizePage.get_payStatus());// 乐豆商品生成订单，就是支付状态
				double HScore_after = Double.valueOf(memberData.get_HScore(username));
				pageData.put("HScore_afterPay", String.valueOf(HScore_after));// 获取用户支付成功后剩余的乐豆
				
			} else {// 普通订单

				/** 订单生成页面，检验生成的订单信息 **/
				if (goodType.equals("service")) {
					System.out.println("虚拟商品没有配送信息");
				} else {// 大建康和普通商品才有配送信息
					pageData.put("address_page2", orderInfoSummarizePage.get_address());// 收货地址
					pageData.put("contact", orderInfoSummarizePage.get_contact());// 收货人电话
				}
				// 生成的订单
				Set<String> orderNBs = orderInfoSummarizePage.get_orderNBs();
				pageData.put("orderNBsCount", String.valueOf(orderNBs.size()));//获取订单生成数量

				// 判断商品是否一至
				String Str_orderNBs = orderNBs.toString().replace("[", "").replace("]", "");
				Set<String> goodsName = orderData.get_goodsName(Str_orderNBs);// 数据库中获取商品名称列表
				if (goodsName.equals(orderInfoSummarizePage.get_goodNames())) {
					pageData.put("goodNames", "same");
				} else {
					pageData.put("goodNames", "diffrent");
					System.out.println(goodsName);
					System.out.println(orderInfoSummarizePage.get_goodNames());
				}
				pageData.put("totalPay", orderInfoSummarizePage.get_totalPay());
				orderInfoSummarizePage.click_payBtn();

				/** 检查支付页面 **//*模拟支付不会展示二维码
				Thread.sleep(7000);
				pageData.put("codeDisplay", orderInfoSummarizePage.code_isDisplay());// 判断微信二维码是否展示出来
*/			}
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return pageData;
	}

	/*
	 * 单个商品，立即购买
	 * 
	 * @goodId 商品ID
	 * 
	 * @goodType 商品类型：普通商品nomal、乐豆bigHealth、虚拟商品service
	 */
	private WebDriver order_immediately(String goodId) {
		WebDriver driver = login.PCLogin(username, "123456li");
		try {
			Thread.sleep(1000);
			driver.get(goodsURL + goodId);
			/** 商品详情页 **/
			Thread.sleep(1000);
			goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			goodsInfoPage.click_buyBtn();// 点击立即购买
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return driver;
	}

	/*
	 * 测试前检查数据
	 * 
	 * @goodIds 若干商品ID
	 * 
	 * @username 当前用户账号
	 */
	public void check_goods(List<String> goodIds) {
		for (int i = 0; i < goodIds.size(); i++) {
			// 检查商品是否是上架状态
			String onliveStatus = goodData.getGoodValue_goodId(goodIds.get(i),"is_onlive");
			if (onliveStatus.equals("0")) {
				goodData.update_onliveStatus(goodIds.get(i), "1");
			}
			
			// 把库存为0 的商品库存增加10个库存
			List<HashMap<String, String>> goodSKU = goodData.get_SKU(goodIds.get(i));
			for(int h=0;h<goodSKU.size();h++) {
				if (goodSKU.get(h).get("bought").equals("0")) {
					goodData.update_bought(goodSKU.get(h).get("id"), "10");
				}
			}
		}		
	}
	
	/*1、清空购物车；2、如果用户没有默认地址则设置一个默认的地址
	 * @username	用户账号
	 * */
	public void check_addrAndCart(String account) {
		// 从数据库和缓存中把当前用户的购物车先清空（避免影响数据，因为购物车是全选的）
		String user_id = memberData.get_Id(account);
		memberData.clear_cart(account);;// 从mysql中删除
		myRedis.redis_del(user_id + "-CART");// 从redis中删除

		// 如果用户没有默认地址，设置一个
		if (memberData.defaultAddressExit(user_id)) {
			memberData.insert_defaultAddress(user_id, account);
		}
	}
}
