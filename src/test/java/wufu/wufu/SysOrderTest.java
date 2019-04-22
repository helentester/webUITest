/**
 * @author helen
 * @date 2018年9月18日
 */
package wufu.wufu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.BaseData;
import wufu.wufu.sysPages.SysIndexPage;
import wufu.wufu.sysPages.SysOrderPage;
import wufu.wufu.sysPages.SysOrderRefundListPage;

/**
 * @Description:运营中心－订单相关功能测试
 */
public class SysOrderTest {
	BaseData baseData = new BaseData();
	LoginUITest loginUITest = new LoginUITest();
	
	/*退货退款审核
	 * @orderSN 订单号	
	 * @auditResult 审核结果:通过、拒绝
	 * */
	public void refundAudit(String orderSN,String auditResult) {
		WebDriver driver = loginUITest.sysLogin("helen", "123456li");
		try {
			SysIndexPage sysIndexPage = PageFactory.initElements(driver, SysIndexPage.class);
			sysIndexPage.clickMainMenu("订单管理");
			Thread.sleep(2000);
			sysIndexPage.clickSubMenu("退货退款管理");
			
			//退货退款列表页面
			Thread.sleep(3000);
			driver.switchTo().frame("iframepage");// 因为主版面是通过iframe嵌入的，所以要切换driver到iframe
			SysOrderRefundListPage orderRefundListPage = PageFactory.initElements(driver, SysOrderRefundListPage.class);
			orderRefundListPage.set_orderSn(orderSN);//输入订单号
			orderRefundListPage.clear_startTime();//清空申请时间
			orderRefundListPage.clear_endTime();//清空申请时间
			orderRefundListPage.click_search();// 点击搜索
			Thread.sleep(3000);
			orderRefundListPage.click_view();//点击[查看]

			//详情页面
			Thread.sleep(3000);
			orderRefundListPage.click_processRefund();//[处理申请]
			if (auditResult.equals("拒绝")) {
				orderRefundListPage.click_auditRefuse();
			}
			orderRefundListPage.set_desc("测试数据");//操作备注
			orderRefundListPage.click_confirm();//[确认]
			Thread.sleep(5000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
	
	/*发货操作
	 * @orderSN 订单号
	 * */
	public void deliverGoods(String orderSN) {
		WebDriver driver = loginUITest.sysLogin("helen", "123456li");
		try {
			SysIndexPage sysIndexPage = PageFactory.initElements(driver, SysIndexPage.class);
			sysIndexPage.clickMainMenu("订单管理");
			Thread.sleep(2000);
			sysIndexPage.clickSubMenu("订单列表");
			
			//订单列表页面
			Thread.sleep(3000);
			driver.switchTo().frame("iframepage");// 因为主版面是通过iframe嵌入的，所以要切换driver到iframe
			SysOrderPage sysOrderPage = PageFactory.initElements(driver, SysOrderPage.class);
			sysOrderPage.set_orderNO(orderSN);//输入订单号
			sysOrderPage.click_searchBTN();//点击查询按钮
			Thread.sleep(3000);
			sysOrderPage.click_detailLink();//点击[查看]按钮
			
			//订单详情页面
			sysOrderPage.click_sendDelivery();//点击[确认发货]
			
			//发货界面
			Thread.sleep(3000);
			sysOrderPage.select_deliveryId(String.valueOf(baseData.getNum(1, 5)));//选择物流公司
			sysOrderPage.set_deliveryNo("88542548548");//输入物流单号
			sysOrderPage.set_msg("测试数据，物流发货");//物流备注
			sysOrderPage.click_sendDeliveryBtn();//发货按钮
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		
	}

}
