/**
 * @author helen
 * @date 2018年7月26日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import common.MysqlConnect;
import data.AgentData;
import data.GoodData;
import data.MemberData;
import data.OrderData;
import data.SellerData;
import dataProvider.PCProvider;
import net.sf.json.JSONObject;
import wufu.wufu.PCPages.PC_carInfoPage;
import wufu.wufu.PCPages.PC_goodsInfoPage;
import wufu.wufu.PCPages.PC_orderInfoEditPage;
import wufu.wufu.PCPages.PC_orderInfoSummarizePage;

/**
 * @Description:分润体系测试
 * 备注：会员体系改版后，代理商和商家注册后会同时成为会员，所以会员的邀请人只能是会员，代理商、商家的邀请人只能是代理商
 */
public class ProfitTest {
	BaseWinUI baseWinUI = new BaseWinUI();
	BaseData baseData = new BaseData();
	AgentData agentData = new AgentData();
	SellerData sellerData = new SellerData();
	MemberData memberData = new MemberData();
	GoodData goodData = new GoodData();
	OrderData orderData = new OrderData();
	MysqlConnect mysqlConnect = new MysqlConnect();
	LoginUITest login = new LoginUITest();
	PC_orderTest orderTest = new PC_orderTest();
	SysOrderTest sysOrderTest = new SysOrderTest();
	PC_myOrderTest myOrderTest = new PC_myOrderTest();
	MyConfig myConfig = new MyConfig();

	HashMap<String, String> AS = new HashMap<String, String>();
	String member = "13524569458";//会员
	String inviter = "13825464584";//会员的邀请人

	/*检查商代体系
	 * return	用于测试的商代及商品信息
	 * 备注：
	 * 1、消费者分润获益计算方法：假设商品金额：A+B，其中可用现金A，可用积分B
	 * 1）每个商品的SKU2对应测试用例：B/(A+B) < 25% 	养老积分：0；消费积分：B
	 * 2）每个商品的SKU3对应测试用例：B/(A+B) = 25% 	养老积分： (A+B)*15%；消费积分： (A+B)*10% 
	 * 3）每个商品的SKU4对应测试用例：B/(A+B) 〉 25% 	养老积分： (A+B)*15%；消费积分： (A+B)*10% 
	 * 2、会员分润获益计算方法：只有直接邀请人（）有份，金额＝（订单现金部分-运费）*1%
	 * 3、商代分润获益计算方法：同级最多分两级，低级不能分高级，最多分三级，金额＝（订单现金部分-运费）*2%/每节点
	 * */
	@Test
	public void test_checkAllData() {
		Reporter.log("检查商代会员体系");
		//会员关系
		memberData.update_Inviter(member, inviter);
		
		//商代关系
		String testService= myConfig.getPropertyValue("testService");//获取当前的测试环境
		if (testService.equals("237")) {
			AS = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("A1", "13302723392");
				put("A2", "15308474090");
				put("A4", "13006584441");
				put("A5", "17105040795");
				put("A6", "17008852971");
				put("A6_2", "15807441502");
				put("H3", "18208873865");
				put("H4", "18204983331");
				put("H5", "18507835187");
				put("H7", "18702876997");
				put("S7", "18003901151");
				put("S6", "15700677945");
				put("S5", "17707461396");
				put("S8", "14501365807");
				put("S7_2", "18507042598");
				put("S6_2", "17705764379");
				put("S5_2", "15100662485");
				put("S4", "13205502874");
				put("v2S1", "18308258102");
				put("v2S2", "18502766235");
				put("v2S3", "13806151074");
				put("v2S4", "15902851694");
				put("v2S5", "17101793717");
				put("v2S6", "17305297437");
				put("v2S7", "14503501781");
				put("v2S8", "13701976548");			
				put("G1", "7778");//分润G1普通商品
				put("G2", "7779");//分润G2普通商品
				put("G3", "7780");//分润G3普通商品
				put("G4", "7781");//分润G4虚拟商品
				put("G5", "7782");//分润G5虚拟商品
				put("G6", "7785");//分润G6虚拟商品
				put("G7", "7783");//分润G7海外购商品
				put("G8", "7784");//分润G8海外购商品
				//设置多一个普通商品
				put("G11", "7789");//分润G11普通商品
				put("G22", "7790");//分润G22普通商品
				put("G33", "7791");//分润G33普通商品
				//v2版本分润规则所用的商品
				put("v2G1", "8028");//普通商品
				put("v2G2", "8022");//普通商品
				put("v2G3", "8085");//虚拟商品
				put("v2G4", "8073");//虚拟商品
				put("v2G5", "7990");//普通商品
				put("v2G6", "7821");//普通商品
				put("v2G7", "7825");//普通商品
				put("v2G8", "7828");//普通商品
				put("v2G9", "8125");//满折普通商品
				//设置多个v2版本分润规则所用的商品
				put("v2G11", "8011");//普通商品
				put("v2G22", "8002");//普通商品
				}
			};
		}
		else if (testService.equals("236")) {
			AS = new HashMap<String, String>(){
				private static final long serialVersionUID = 1L;
				{
					
					put("A1", "13302723392");
					put("A2", "15308474090");
					put("A4", "13006584441");
					put("A5", "17105040795");
					put("A6", "17008852971");
					put("A6_2", "15807441502");
					put("H3", "18208873865");
					put("H4", "18204983331");
					put("H5", "18507835187");
					put("H7", "18702876997");
					put("S7", "18003901151");
					put("S6", "15700677945");
					put("S5", "17707461396");
					put("S8", "14501365807");
					put("S7_2", "18507042598");
					put("S6_2", "17705764379");
					put("S5_2", "15100662485");
					put("S4", "13205502874");
					put("v2S1", "18308258102");
					put("v2S2", "18502766235");
					put("v2S3", "13806151074");
					put("v2S4", "15902851694");
					put("v2S5", "17101793717");
					put("v2S6", "17305297437");
					put("v2S7", "14503501781");
					put("v2S8", "13701976548");			
					put("G1", "7778");//分润G1普通商品
					put("G2", "7779");//分润G2普通商品
					put("G3", "7780");//分润G3普通商品
					put("G4", "7781");//分润G4虚拟商品
					put("G5", "7782");//分润G5虚拟商品
					put("G6", "7785");//分润G6虚拟商品
					put("G7", "7783");//分润G7海外购商品
					put("G8", "7784");//分润G8海外购商品
					//设置多一个普通商品
					put("G11", "7789");//分润G11普通商品
					put("G22", "7790");//分润G22普通商品
					put("G33", "7791");//分润G33普通商品
					//v2版本分润规则所用的商品
					put("v2G1", "8028");//普通商品
					put("v2G2", "8022");//普通商品
					put("v2G3", "8085");//虚拟商品
					put("v2G4", "8073");//虚拟商品
					put("v2G5", "7990");//普通商品
					put("v2G6", "7821");//普通商品
					put("v2G7", "7825");//普通商品
					put("v2G8", "7828");//普通商品
					put("v2G9", "8125");//满折普通商品
					//设置多个v2版本分润规则所用的商品
					put("v2G11", "8011");//普通商品
					put("v2G22", "8002");//普通商品
					
					/*put("A1", "15207466927");
					put("A2", "15807482990");
					put("A4", "13908175345");
					put("A5", "17503087859");
					put("A6", "14500199002");
					put("A6_2", "17708286029");
					put("H3", "18801904664");
					put("H4", "17305508966");
					put("H5", "18207486993");
					put("H7", "17708618018");
					put("S7", "17708477898");
					put("S6", "17705716937");
					put("S5", "15708645656");
					put("S8", "13203354586");
					put("S7_2", "15904171935");
					put("S6_2", "17707562547");
					put("S5_2", "17302441929");
					put("S4", "13902183042");
					put("v2S1", "18905339026");
					put("v2S2", "18707403890");
					put("v2S3", "18802838989");
					put("v2S4", "18808100407");
					put("v2S5", "18606982427");
					put("v2S6", "13108857972");
					put("v2S7", "15902020661");
					put("v2S8", "18104526811");
   				    put("G1", "7778");//分润G1普通商品
					put("G2", "7779");//分润G2普通商品
					put("G3", "7780");//分润G3普通商品
					put("G4", "7781");//分润G4虚拟商品
					put("G5", "7782");//分润G5虚拟商品
					put("G6", "7785");//分润G6虚拟商品
					put("G7", "7783");//分润G7海外购商品
					put("G8", "7784");//分润G8海外购商品
					//设置多一个普通商品
					put("G11", "7789");//分润G11普通商品
					put("G22", "7790");//分润G22普通商品
					put("G33", "7791");//分润G33普通商品
					//v2版本分润规则所用的商品
					put("v2G1", "7991");//普通商品
					put("v2G2", "7995");//普通商品
					put("v2G3", "7998");//虚拟商品
					put("v2G4", "7999");//虚拟商品
					put("v2G5", "8000");//普通商品
					put("v2G6", "8001");//普通商品
					put("v2G7", "8002");//普通商品
					put("v2G8", "8003");//普通商品
					//设置多个v2版本分润规则所用的商品
					put("v2G11", "7994");//普通商品
					put("v2G22", "7996");//普通商品*/
					}
				};
		}
		else {
			System.out.println(testService+"测试环境没有该商代会员体系");
		}
		//个代与健康家
		agentData.update_inviter(AS.get("A1"), AS.get("A2"));
		agentData.update_inviter(AS.get("A2"), AS.get("H3"));
		agentData.update_inviter(AS.get("H3"), AS.get("A4"));
		agentData.update_inviter(AS.get("A4"), AS.get("A5"));
		agentData.update_inviter(AS.get("A5"), AS.get("A6"));
		agentData.update_inviter(AS.get("H3"), AS.get("H4"));
		agentData.update_inviter(AS.get("H4"), AS.get("H5"));
		agentData.update_inviter(AS.get("H5"), AS.get("A6_2"));
		agentData.update_inviter(AS.get("A6_2"), AS.get("H7"));
		//商家与代理商的关系
		sellerData.update_inviter(AS.get("A6"), AS.get("S7"));
		sellerData.update_inviter(AS.get("A5"), AS.get("S6"));
		sellerData.update_inviter(AS.get("A4"), AS.get("S5"));
		sellerData.update_inviter(AS.get("H7"), AS.get("S8"));
		sellerData.update_inviter(AS.get("A6_2"), AS.get("S7_2"));
		sellerData.update_inviter(AS.get("H5"), AS.get("S6_2"));
		sellerData.update_inviter(AS.get("H4"), AS.get("S5_2"));
		sellerData.update_inviter(AS.get("H3"), AS.get("S4"));
		sellerData.update_inviter(AS.get("H5"),AS.get("v2S1"));
		sellerData.update_inviter(AS.get("H7"),AS.get("v2S2"));
		sellerData.update_inviter(AS.get("A6_2"),AS.get("v2S3"));
		sellerData.update_inviter(AS.get("A5"),AS.get("v2S4") );
		sellerData.update_inviter(AS.get("H5"),AS.get("v2S5"));
		sellerData.update_inviter(AS.get("H7"),AS.get("v2S7"));
		sellerData.update_inviter(AS.get("A6_2"),AS.get("v2S6"));
		sellerData.update_inviter(AS.get("A5"),AS.get("v2S8") );
		//设置商家的分润比例
		sellerData.update_profitPercentage(AS.get("S7"), "0.1");
		sellerData.update_profitPercentage(AS.get("S6"), "0.1");
		sellerData.update_profitPercentage(AS.get("S5"), "0.1");
		sellerData.update_profitPercentage(AS.get("S8"), "0.1");
		sellerData.update_profitPercentage(AS.get("S7_2"), "0.1");
		sellerData.update_profitPercentage(AS.get("S6_2"), "0.1");
		sellerData.update_profitPercentage(AS.get("S5_2"), "0.1");
		sellerData.update_profitPercentage(AS.get("S4"), "0.1");
		sellerData.update_profitPercentage(AS.get("v2S1"), "0.1");
		sellerData.update_profitPercentage(AS.get("v2S2"), "0.1");
		sellerData.update_profitPercentage(AS.get("v2S3"), "0.1");
		sellerData.update_profitPercentage(AS.get("v2S4"), "0.1");
		sellerData.update_profitPercentage(AS.get("v2S5"), "0.0655");
		sellerData.update_profitPercentage(AS.get("v2S6"), "0.8999");
		sellerData.update_profitPercentage(AS.get("v2S7"), "0.0");
		sellerData.update_profitPercentage(AS.get("v2S8"), "1");
		//检查商家下的商品
		this.checkAS_goods(AS.get("S7"), AS.get("G1"));
		this.checkAS_goods(AS.get("S6"), AS.get("G2"));
		this.checkAS_goods(AS.get("S5"), AS.get("G3"));
		this.checkAS_goods(AS.get("S7"), AS.get("G11"));
		this.checkAS_goods(AS.get("S6"), AS.get("G22"));
		this.checkAS_goods(AS.get("S5"), AS.get("G33"));
		this.checkAS_goods(AS.get("S7_2"), AS.get("G4"));
		this.checkAS_goods(AS.get("S4"), AS.get("G5"));
		this.checkAS_goods(AS.get("S8"), AS.get("G6"));
		this.checkAS_goods(AS.get("S5_2"), AS.get("G7"));
		this.checkAS_goods(AS.get("S6_2"), AS.get("G8"));
		this.checkAS_goods(AS.get("v2S1"), AS.get("v2G1"));
		this.checkAS_goods(AS.get("v2S1"), AS.get("v2G11"));
		this.checkAS_goods(AS.get("v2S2"), AS.get("v2G2"));
		this.checkAS_goods(AS.get("v2S2"), AS.get("v2G22"));
		this.checkAS_goods(AS.get("v2S3"), AS.get("v2G3"));
		this.checkAS_goods(AS.get("v2S4"), AS.get("v2G4"));
		this.checkAS_goods(AS.get("v2S5"), AS.get("v2G5"));
		this.checkAS_goods(AS.get("v2S6"), AS.get("v2G6"));
		this.checkAS_goods(AS.get("v2S7"), AS.get("v2G7"));
		this.checkAS_goods(AS.get("v2S8"), AS.get("v2G8"));
		this.checkAS_goods(AS.get("v2S8"), AS.get("v2G9"));
		
		Reporter.log("**************商代体系*****************");
		Reporter.log("第一种情况：商品G1（"+AS.get("G1")+"、"+AS.get("G11")+"）－商家S7（"+AS.get("S7")+"）－个代A6（"+AS.get("A6")+"）－个代A5（"+AS.get("A5")+"）－个代A4（"+AS.get("A4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第二种情况：商品G2（"+AS.get("G2")+"、"+AS.get("G22")+"）－商家S6（"+AS.get("S6")+"）－个代A5（"+AS.get("A5")+"）－个代A4（"+AS.get("A4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第三种情况：商品G3（"+AS.get("G3")+"、"+AS.get("G33")+"）－商家S5（"+AS.get("S5")+"）－个代A4（"+AS.get("A4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第四种情况：商品G4（"+AS.get("G4")+"）－商家S7_2（"+AS.get("S7_2")+"）－个代A6_2（"+AS.get("A6_2")+"）－健康家H5（"+AS.get("H5")+"）－健康家H4（"+AS.get("H4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第五种情况：商品G5（"+AS.get("G5")+"）－商家S4（"+AS.get("S4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第六种情况：商品G6（"+AS.get("G6")+"）－商家S8（"+AS.get("S8")+"）－健康家H7（"+AS.get("H7")+"）－个代A6_2（"+AS.get("A6_2")+"）－健康家H5（"+AS.get("H5")+"）－健康家H4（"+AS.get("H4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第七种情况：商品G7（"+AS.get("G7")+"）－商家S5_2（"+AS.get("S5_2")+"）－健康家H4（"+AS.get("H4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("第八种情况：商品G8（"+AS.get("G8")+"）－商家S6_2（"+AS.get("S6_2")+"）－健康家H5（"+AS.get("H5")+"）－健康家H4（"+AS.get("H4")+"）－健康家H3（"+AS.get("H3")+"）－个代A2（"+AS.get("A2")+"）－个代A1（"+AS.get("A1")+"）");
		Reporter.log("************************************");
	}
	
	@Test(dependsOnMethods="test_checkAllData",dataProvider="profitScore",dataProviderClass=PCProvider.class)
	public void test_memberScore(String remark,String goodId,String sku,String consume_coupon,String annuity_coupon,String Contacts) {
		Reporter.log("消费者分润："+remark);
		String[] goodsId=goodId.split(",");
		boolean FillInContacts = Boolean.valueOf(Contacts);
		Reporter.log("是否填写联系人:"+FillInContacts);
		String orderSn = this.createOrder_nomalGoods(member, goodsId, "2", sku, false, FillInContacts);
		assertNotEquals(orderSn, "","检查订单是否生成成功");
		
		//数据处理
		BigDecimal Bconsume = new BigDecimal(consume_coupon);//福豆
		Bconsume = Bconsume.setScale(2,BigDecimal.ROUND_DOWN);//福豆截取两位小数
		BigDecimal Bannuity = new BigDecimal(annuity_coupon);//享豆
		Bannuity = Bannuity.setScale(2,BigDecimal.ROUND_DOWN);
		
		if (Float.valueOf(consume_coupon)!=0) {//分润为0时，没有冻结记录
			//校验[会员福豆冻结记录表]m_bean_bless_freeze_record里面会员的福豆记录：operate_type＝1  冻结入账
			assertEquals(Double.valueOf(orderData.getBlessTradeBean_orderSn(orderSn, "1")), Double.valueOf(Bconsume.toString()),"表m_bean_bless_freeze_record福豆较验");
		}
		if (Float.valueOf(annuity_coupon) !=0) {//分润为0时，没有冻结记录
			//校验[会员享豆冻结记录表]m_bean_enjoy_freeze_record里面会员的享豆记录：operate_type＝1  冻结入账
			assertEquals(Double.valueOf(orderData.getEnjoyTradeBean_orderSn(orderSn, "1")), Double.valueOf(Bannuity.toString()),"表m_bean_enjoy_freeze_record享豆较验");
		}
		
		/* 会员评价且订单完结后，才会有流水记录，所以此校验暂时注释掉
		//校验[会员福豆流水记录表]m_bean_bless_record里面会员的福豆记录：detail_type＝5  评价商品发放
		assertEquals(Double.valueOf(orderData.getBessRecord_orderSn(orderSn, "6")), Double.valueOf(Bconsume.toString()),"表m_bean_bless_record福豆较验");
		//校验[会员享豆流水记录表]m_bean_enjoy_record里面会员的享豆记录：detail_type＝5  评价商品发放
		assertEquals(Double.valueOf(orderData.getEnjoyRecord_orderSn(orderSn, "5")), Double.valueOf(Bannuity.toString()),"表m_bean_enjoy_record享豆较验");
		*/
		//校验[评价返积分记录表]t_order_item_evaluate_coupon里面的福豆、享豆数据
		assertEquals(Double.valueOf(orderData.getEvaluateCoupon_orderSN(orderSn, "consume_coupon")), Double.valueOf(Bconsume.toString()),"表t_order_item_evaluate_coupon福豆校验："+orderSn);
		assertEquals(Double.valueOf(orderData.getEvaluateCoupon_orderSN(orderSn, "annuity_coupon")), Double.valueOf(Bannuity.toString()),"表t_order_item_evaluate_coupon享豆校验："+orderSn);
		
		//校验任务表t_order_evaluate_task里面的福豆、享豆数据
		assertEquals(Double.valueOf(orderData.getEvaluateTaskValue_byOrderSN(orderSn,"consume_coupon")),Double.valueOf(Bconsume.toString()),"表t_order_evaluate_task福豆校验："+orderSn);
		assertEquals(Double.valueOf(orderData.getEvaluateTaskValue_byOrderSN(orderSn,"annuity_coupon")),Double.valueOf(Bannuity.toString()),"表t_order_evaluate_task享豆校验"+orderSn);
	}
	
	@Test(dependsOnMethods="test_checkAllData",dataProvider="profitMoney",dataProviderClass=PCProvider.class)
	public void test_profitMoney(String goodId,String Contacts,String profit_percentage,String pp,String pr,String pm,String mr,String m,String a1r,
			String a1,String a2r,String a2,String a3r,String a3) {
		Reporter.log("会员分润、商代分润检查：下单商品都是[10现金+40福豆]，每个商品会使用30福豆");
		try {
			String[] goodsId=goodId.split(",");
			boolean FillInContacts = Boolean.valueOf(Contacts);
			String orderSn = this.createOrder_nomalGoods(member, goodsId, "2", "sku4", true, FillInContacts);
			assertNotEquals(orderSn, "","检查订单是否生成成功");
			
			//数据处理
			BigDecimal Bpp = new BigDecimal(pp);
			Bpp = Bpp.setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal Bpr = new BigDecimal(pr);
			Bpr = Bpr.setScale(4,BigDecimal.ROUND_DOWN);
			BigDecimal Bpm = new BigDecimal(pm);
			Bpm = Bpm.setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal Bmr = new BigDecimal(mr);
			Bmr = Bmr.setScale(4,BigDecimal.ROUND_DOWN);
			BigDecimal Bm = new BigDecimal(m);
			Bm = Bm.setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal Ba1r = new BigDecimal(a1r);
			Ba1r = Ba1r.setScale(4,BigDecimal.ROUND_DOWN);
			BigDecimal Ba1 = new BigDecimal(a1);
			Ba1 = Ba1.setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal Ba2r = new BigDecimal(a2r);
			Ba2r = Ba2r.setScale(4,BigDecimal.ROUND_DOWN);
			BigDecimal Ba2 = new BigDecimal(a2);
			Ba2 = Ba2.setScale(2,BigDecimal.ROUND_DOWN);
			BigDecimal Ba3r = new BigDecimal(a3r);
			Ba3r = Ba3r.setScale(4,BigDecimal.ROUND_DOWN);
			BigDecimal Ba3 = new BigDecimal(a3);
			Ba3 = Ba3.setScale(2,BigDecimal.ROUND_DOWN);
			
			//期望邀请分润
			JSONObject expProfit = new JSONObject();			
			expProfit.put("profitable_price", Double.valueOf(Bpp.toString()));//1.0版本存的是订单支付的现金值，2.0版本存的是商品的现金之和
			expProfit.put("platform_ratio", Double.valueOf(Bpr.toString()));//平台分润比例＝0.1-邀请人分润比例-1级推荐人分润比例-2级推荐人分润比例-3级推荐人分润比例
			expProfit.put("platform_money", Double.valueOf(Bpm.toString()));//平台分润＝（订单现金部分-运费）* 平台分润比例
			expProfit.put("mb_recomond1_ratio", Double.valueOf(Bmr.toString()));//邀请人分润比例＝0.01
			expProfit.put("mb_recomond1_money", Double.valueOf(Bm.toString()));//邀请人分润=（订单现金部分-运费）*1%
			expProfit.put("sj_recomond1_rate", Double.valueOf(Ba1r.toString()));//一级推荐人分润比例＝0.02
			expProfit.put("sj_recomond1_money", Double.valueOf(Ba1.toString()));//一级推荐人津贴金额=（订单现金部分-运费）*2%
			expProfit.put("sj_recomond2_rate", Double.valueOf(Ba2r.toString()));//二级推荐人分润比例＝0.02
			expProfit.put("sj_recomond2_money", Double.valueOf(Ba2.toString()));//二级推荐人津贴金额=（订单现金部分-运费）*2%
			expProfit.put("sj_recomond3_rate", Double.valueOf(Ba3r.toString()));//三级推荐人分润比例＝0.02
			expProfit.put("sj_recomond3_money", Double.valueOf(Ba3.toString()));//三级推荐人津贴金额=（订单现金部分-运费）*2%
			//实际邀请分润
			Thread.sleep(1000);
			JSONObject actProfit = orderData.getProfitDetail_byOrderSN(orderSn);
			assertEquals(actProfit.toString(), expProfit.toString(),"会员及商代分润校验"+orderSn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*商家下的商品检查
	 * @seller 商家账号
	 * @goodId 商品Id
	 * return	商品ID
	 * */
	public void checkAS_goods(String seller,String goodId) {
		
			/****通过update的方法保证商品数据的正确性*****/
			// 保存商家与商品的关联
			mysqlConnect.updateData("UPDATE p_goods set supplier_id=(SELECT supplier_id from u_supplier_user WHERE account='"+seller+"') where id="+goodId);
			// 保证商品与商家的“全国模板”关联：非免费模板
			String templateId = sellerData.getTemplateId_name(seller, "全国模板");
			mysqlConnect.updateData("UPDATE p_goods set freight_template_id="+templateId+" where id="+goodId);
			// 设置商品的上架状态
			mysqlConnect.updateData("UPDATE p_goods set is_onlive=1,publish_wait=1 where id="+goodId);
			// 把库存为0 的商品库存增加10个库存
			mysqlConnect.updateData("UPDATE p_goods_sku SET bought=10  where bought=0 and goods_id in(" +goodId+ ")");
			// 设置SKU1(现金100，会员应得养老积分15%、消费积分10%)
			mysqlConnect.updateData("UPDATE p_goods_sku set current_price=100,min_buy_count=1,use_score=0,integral_use_ratio=0,is_delete=0 WHERE goods_id="+goodId+" and sku_title='s1 k1'");
			// 设置SKU2(现金40+福豆10，会员应当养老积分0、消费积分10)
			mysqlConnect.updateData("UPDATE p_goods_sku set current_price=50,min_buy_count=1,use_score=1,integral_use_ratio=10,is_delete=0 WHERE goods_id="+goodId+" and sku_title='s1 k2'");
			// 设置SKU3(现金30+福豆10，会员应当养老积分6、消费积分4)
			mysqlConnect.updateData("UPDATE p_goods_sku set current_price=40,min_buy_count=1,use_score=1,integral_use_ratio=10,is_delete=0 WHERE goods_id="+goodId+" and sku_title='s2 k1'");
			// 设置SKU4（现金10+福豆40）
			mysqlConnect.updateData("UPDATE p_goods_sku set current_price=50,min_buy_count=1,use_score=1,integral_use_ratio=40,is_delete=0 WHERE goods_id="+goodId+" and sku_title='s2 k2'");
		
	}
	
	/*普通商品下订单
	 * @goodsIds	普通商品ID
	 * @goodsCount	每个商品需要下单的数量
	 * @userScore  是否使用积分
	 * @FillInContacts	是否需要填写联系人信息
	 * */
	public String createOrder_nomalGoods(String Consumer,String[] goodsIds,String goodsCount,String skuName,boolean userScore,boolean FillInContacts) {
		// 检查消费数的数据，避免影响下单
		orderTest.check_addrAndCart(Consumer);
		//如果使用福豆则给用户200福豆，否则清空用户福豆
		if (userScore) {
			int n=goodsIds.length;
			int count = Integer.valueOf(goodsCount);
			String ableScore = String.valueOf(n*count*30);
			memberData.update_AbleScore(Consumer, ableScore);//设置刚好每个商品使用30福豆
		} else {
			memberData.update_AbleScore(Consumer, "0");
		}
		
		Reporter.log("-------[关系网]-------");
		Reporter.log("会员账号："+Consumer);
		String Consumer_inviter = memberData.getValue_account(Consumer, "recommend_name");
		Reporter.log("会员的邀请人账号："+Consumer_inviter);
		//Reporter.log("下单商品为："+goodsIds);
		String seller_id = goodData.getGoodValue_goodId(AS.get(goodsIds[0]), "supplier_id");
		String seller = sellerData.getValue_sellerId(seller_id, "account");
		Reporter.log("商品所属商家："+seller);
		String profitPercentage = sellerData.getValue_sellerId(seller_id, "profit_percentage");
		Reporter.log("商家分润比例："+profitPercentage);
		String submit_date = sellerData.getValue_sellerId(seller_id, "submit_date");
		Reporter.log("商家提交审核资料时间："+submit_date);
		String agent_inviter1 = sellerData.getValue_sellerId(seller_id, "recommend_id");
		String agent1 = agentData.getValue_agentId(agent_inviter1, "account");
		Reporter.log("一级代理商："+agent1);
		String agent_inviter2 = agentData.getValue_agentId(agent_inviter1, "recommend_id");
		String agent2 = agentData.getValue_agentId(agent_inviter2, "account");
		Reporter.log("二级代理商："+agent2);
		String agent_inviter3 = agentData.getValue_agentId(agent_inviter2, "recommend_id");
		String agent3 = agentData.getValue_agentId(agent_inviter3, "account");
		Reporter.log("三级代理商："+agent3);
		
		WebDriver driver = login.PCLogin(Consumer, "123456li");
		String orderNB = "";
		//下订单
		try {
			Thread.sleep(1000);
			PC_goodsInfoPage goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
			for (String goodId:goodsIds) {
				driver.get(myConfig.getKeys("PC_domainName") + "/goods/goodsInfo?id="+AS.get(goodId));
				Thread.sleep(1000);
				/*商品信息页面*/
				if (skuName.equals("sku1")) {
					goodsInfoPage.click_sku1();
				}
				else if (skuName.equals("sku2")) {
					goodsInfoPage.click_sku2();
				}
				else if (skuName.equals("sku3")) {
					goodsInfoPage.click_sku3();
				}
				else {
					goodsInfoPage.click_sku4();//选择第4个SKU
				}
				goodsInfoPage.set_goodsNumber(goodsCount);//购买商品个数
				
				if (FillInContacts) {
					goodsInfoPage.click_buyBtn();//立即购买
				}
				else {//普通商品通过购物车下单
					goodsInfoPage.click_addCartBtn();//加入购物车
				}
			}
			
			//购物车操作
			if (FillInContacts==false) {
				goodsInfoPage.click_cartLink();// 点击购物车，打开购物车页面
				Thread.sleep(1000);
				/** 购物车页面 **/
				baseWinUI.changeWindow(driver);// 因为购物车是另起标签页打开，所以要切换
				Thread.sleep(3000);
				PC_carInfoPage carInfoPage = PageFactory.initElements(driver, PC_carInfoPage.class);
				carInfoPage.click_checkAllTop();// 全选
				carInfoPage.click_gotoPayBTN();// 去结算
			}
			
			/**订单信息页面**/
			Thread.sleep(3000);
			PC_orderInfoEditPage orderInfoEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
			if (FillInContacts) {//需要实名，填写实名信息
				orderInfoEditPage.set_memberAccount("张三");//真实姓名	
				orderInfoEditPage.set_mobile(baseData.getPhoneNumber());//手机号
				orderInfoEditPage.set_idCardInfo(baseData.getIdCard());//身份证号
			}
			orderInfoEditPage.click_gotoPay();//提交订单
			
			/*订单成功页面*/
			PC_orderInfoSummarizePage orderInfoSummarizePage = PageFactory.initElements(driver, PC_orderInfoSummarizePage.class);
			Thread.sleep(6000);
			Iterator<String> orders = orderInfoSummarizePage.get_orderNBs().iterator();
			if (orders.hasNext()) {
				orderInfoSummarizePage.click_payBtn();//去支付（模拟支付状态下，点击后是支付成功状态）
				driver.quit();
				orderNB = orders.next();
				Reporter.log("订单号："+orderNB);
				if (goodsIds[0].equals("G4")||goodsIds[0].equals("G5")||goodsIds[0].equals("G6")) {
					System.out.println("虚拟商品不需要发货");
					Thread.sleep(15000);//等待分润生成
				}
				else {
					// 后台发货
					sysOrderTest.deliverGoods(orderNB);
					// 前台确认收货
					myOrderTest.confirmDeliver(Consumer, orderNB);
					//评论
					myOrderTest.evaluate(Consumer, orderNB);
				}
			}
			
		} catch (Exception e) {
			//driver.quit();
			e.printStackTrace();
		}
		
		return orderNB;
	}
}
