/**
 * @author helen
 * @date 2018年9月18日
 */
package wufu.wufu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import wufu.wufu.sysPages.SysEvaluateListPage;
import wufu.wufu.sysPages.SysIndexPage;

/**
 * @Description:运营中心－评价管理
 */
public class SysEvaluateTest {
	LoginUITest loginUITest = new LoginUITest();

	/*审核评价内容
	 * @orderSN 订单号
	 * */
	public void auditEvaluate(String orderSN) {
		WebDriver driver = loginUITest.sysLogin("helen", "123456li");
		try {
			SysIndexPage sysIndexPage = PageFactory.initElements(driver, SysIndexPage.class);
			sysIndexPage.clickMainMenu("评论管理");
			Thread.sleep(2000);
			sysIndexPage.clickSubMenu("评论列表");
			
			//评论列表页面
			Thread.sleep(3000);
			driver.switchTo().frame("iframepage");// 因为主版面是通过iframe嵌入的，所以要切换driver到iframe
			SysEvaluateListPage sysEvaluateListPage = PageFactory.initElements(driver, SysEvaluateListPage.class);
			sysEvaluateListPage.set_orderSn(orderSN);//输入订单号
			sysEvaluateListPage.click_searchBTN();//点击[查询]按钮
			Thread.sleep(3000);
			sysEvaluateListPage.click_detailLink();
			
			//详情页面
			sysEvaluateListPage.click_auditContext();//点击[审核]按钮
			sysEvaluateListPage.click_confirm();//点击[通过]按钮
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
}
