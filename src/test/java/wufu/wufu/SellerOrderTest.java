/**
 * @author helen
 * @date 2018年9月19日
 */
package wufu.wufu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import wufu.wufu.sellerPages.SellerIndexPage;
import wufu.wufu.sellerPages.SellerRefundListPage;

/**
 * @Description:商家中心－订单管理中的功能
 */
public class SellerOrderTest {
	LoginUITest loginUITest = new LoginUITest();
	
	/*退货退款审核
	 * @account 商家账号
	 * @orderSN 订单号
	 * */
	public void auditRefund(String account,String orderSN) {
		WebDriver driver = loginUITest.sellerLogin(account, "123456li");
		try {
			Thread.sleep(3000);
			SellerIndexPage indexPage = PageFactory.initElements(driver, SellerIndexPage.class);
			indexPage.click_MainMenu("订单管理");
			indexPage.click_subMenu("退货/退款单");
			
			//列表页面
			SellerRefundListPage refundListPage = PageFactory.initElements(driver, SellerRefundListPage.class);
			refundListPage.set_orderSN(orderSN);//输入订单号
			//refundListPage.clear_startTime(driver);//清空申请时间
			//refundListPage.clear_endTime(driver);//清空申请时间
			refundListPage.click_searchBTN();//点击[搜索]
			Thread.sleep(3000);
			refundListPage.click_detailLink();//点击[查看]
			
			//审核页面
			Thread.sleep(3000);
			refundListPage.click_canAuditBTN();//点击[待审核]按钮
			refundListPage.click_confirmBTN();//[确认]审核通过
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}

}
