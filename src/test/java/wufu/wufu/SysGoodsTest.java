/**
 * @author helen
 * @date 2018年7月16日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.MyConfig;
import data.GoodData;
import data.SellerData;
import wufu.wufu.sysPages.SysGoodsListPage;
import wufu.wufu.sysPages.SysIndexPage;

/**
 * @Description:后台管理系统－商品管理测试（未完成）
 */
public class SysGoodsTest {
	MyConfig myConfig = new MyConfig();
	SellerData sellerData = new SellerData();
	GoodData goodData = new GoodData();
	LoginUITest loginUITest = new LoginUITest();
	SysIndexPage indexPage;
	SysGoodsListPage goodsListPage;
	
	@Test
	public void test_goodsCheckAuditByName() {
		Reporter.log("商品审核通过-通过商品名称查询");
		try {
			//找出未审核、未上架的商品
			String supplier_id = sellerData.get_sellerId(myConfig.getKeys("seller1"));
			String goodsName = goodData.get_unauditGoodsName(supplier_id);
			Reporter.log("商品所属商家ID＝"+supplier_id);
			Reporter.log("商品名称="+goodsName);
			if (goodsName!="") {
				this.goodsCheckByName(goodsName);
			}
			Thread.sleep(3000);
			assertEquals(goodData.getGoodValue_goodName(goodsName, "publish_wait"), "1","检查商品审核结果,商品名称为："+goodsName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test_goodsCheck_notPass() {
		Reporter.log("商品审核不通过");
		String goodsId = this.goodsAudit("审核不通过");
		assertEquals(goodData.getGoodValue_goodId(goodsId, "publish_wait"), "2","查询商品审核结果,商品ID为："+goodsId);
	}
	
	@Test
	public void test_goodsCheck_Pass() {
		Reporter.log("商品审核通过");
		String goodsId = this.goodsAudit("审核通过");
		assertEquals(goodData.getGoodValue_goodId(goodsId, "publish_wait"), "1","查询商品审核结果,商品ID为："+goodsId);
	}
	
	@Test
	public void test_goodsSellUp() {
		Reporter.log("商品上架");
		String goodsId = this.goodSellAudit("已下架");
		assertEquals(goodData.getGoodValue_goodId(goodsId, "is_onlive"), "1","上架商品ID："+goodsId);
	}
	
	@Test
	public void test_goodsSellDown() {
		Reporter.log("商品下架");
		String goodsId = this.goodSellAudit("已上架");
		assertEquals(goodData.getGoodValue_goodId(goodsId, "is_onlive"), "0","上架商品ID："+goodsId);
	}
	
	/*商品上下架
	 * @Onlive	上架状态查询条件
	 * */
	public String goodSellAudit(String sellStatu) {
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		String goodsId="";
		try {
			/*主页*/
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("商品管理");
			indexPage.clickSubMenu("商品列表");
			/*商品列表页面*/
			driver.switchTo().frame("iframepage");
			goodsListPage = PageFactory.initElements(driver, SysGoodsListPage.class);
			goodsListPage.select_isOnlive(sellStatu);
			goodsListPage.click_searchBTN();
			Thread.sleep(2000);
			goodsId = goodsListPage.get_TGoodsId();//获取商品ID、
			if (sellStatu.equals("已上架")) {//下架操作
				goodsListPage.click_undercarriage();//点击下架按钮
			}
			else if (sellStatu.equals("已下架")) {//上架操作
				goodsListPage.click_undercarriageUP();//点击上架按钮
			}
			Thread.sleep(2000);
			goodsListPage.alerter(driver, "确定");//弹出框点击“确定”
			goodsListPage.click_closeMassage();//关闭提示信息
			
			Thread.sleep(2000);
			driver.quit();
			
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		return goodsId;
	}
	
	/*商品审核
	 * @auditValue 审核操作：审核通过、审核不通过
	 * */
	public String goodsAudit(String auditValue) {
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		String goodsId="";
		try {
			/*主页*/
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("商品管理");
			indexPage.clickSubMenu("商品列表");
			/*商品列表页面*/
			driver.switchTo().frame("iframepage");
			goodsListPage = PageFactory.initElements(driver, SysGoodsListPage.class);
			goodsListPage.select_state("待审核");//查询待审核的商品
			//输入商家
			String sellerName = sellerData.get_sellerName(myConfig.getKeys("seller1"));
			goodsListPage.click_supplierId();
			Thread.sleep(1000);
			goodsListPage.set_searchKey(sellerName);
			goodsListPage.click_search_supplier_button();
			goodsListPage.select_supplier();
			goodsListPage.click_addButton();
			Thread.sleep(3000);
			goodsListPage.click_searchBTN();//[搜索]
			Thread.sleep(2000);
			goodsId = goodsListPage.get_TGoodsId();//获取商品ID、
			if (auditValue.equals("审核通过")) {
				goodsListPage.click_CheckPast();//点击审核通过按钮
				Thread.sleep(2000);
				goodsListPage.alerter(driver, "确定");//点击确定通过审核
			}
			else if(auditValue.equals("审核不通过")){
				goodsListPage.click_CheckNotPast();//点击审核不通过按钮
				goodsListPage.set_suppliernoDesc("just test");//输入审核不通过原因
				goodsListPage.click_confirm1();//保存审核不通过原因
			}
			goodsListPage.click_closeMassage();//关闭提示信息
			
			Thread.sleep(2000);
			driver.quit();
			
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		return goodsId;
	}
	
	/*商品审核
	 * @goodsName	商品名称
	 * */
	public void goodsCheckByName(String goodsName) {
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		try {
			/*主页*/
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("商品管理");
			indexPage.clickSubMenu("商品列表");
			/*商品列表页面*/
			driver.switchTo().frame("iframepage");
			goodsListPage = PageFactory.initElements(driver, SysGoodsListPage.class);
			goodsListPage.set_goodsName(goodsName);//商品名称
			goodsListPage.click_searchBTN();//点击查询按钮
			goodsListPage.click_CheckPast();//点击审核通过按钮
			Thread.sleep(2000);
			goodsListPage.alerter(driver, "确定");//点击确定通过审核
			goodsListPage.click_closeMassage();//关闭提示信息
			
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
	}

}
