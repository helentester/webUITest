/**
 * @author helen
 * @date 2018年7月13日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.MysqlConnect;
import wufu.wufu.sysPages.SysIndexPage;
import wufu.wufu.sysPages.SysSuperAgentAuditPage;

/**
 * @Description:后台管理系统－超市代理商(后面超代改为健康家)
 */
public class SysSuperAgentTest {
	MysqlConnect mysqlConnect = new MysqlConnect();
	LoginUITest loginUITest = new LoginUITest();
	
	SysSuperAgentAuditPage superAgentAuditPage;
	SysIndexPage indexPage;
	WebDriver driver;
	List<HashMap<String, String>> resultSet;
	
	/*健康家业务放弃使用*/
	@Test(enabled=false)
	public void test_healthHomeAudit() {
		resultSet = mysqlConnect.selectSql("SELECT * from u_supermarket_agent_user where super_auditor_status=2 ORDER BY agent_id desc LIMIT 1");
		if (resultSet.size()==1) {
			Reporter.log("健康家审核");
			
			String superAgent = resultSet.get(0).get("account");
			this.healthHomeAudit(superAgent);
			
			//断言
			try {
				Thread.sleep(3000);
				resultSet = mysqlConnect.selectSql("SELECT * from u_supermarket_agent_user where account='"+superAgent+"'");
				assertEquals("1", resultSet.get(0).get("super_auditor_status"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Reporter.log("没有未审核的健康家");
		}
		
	}
	
	public void healthHomeAudit(String superAgentPhone){
		try {
			driver = loginUITest.sysLogin("helen","123456li");
			/*后台主页*/
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("代理商管理");
			indexPage.clickSubMenu("健康家审核");
			/*超市代理商审核列表*/
			driver.switchTo().frame("iframepage");
			superAgentAuditPage = PageFactory.initElements(driver, SysSuperAgentAuditPage.class);
			superAgentAuditPage.setPhone(superAgentPhone);//输入联系电话
			superAgentAuditPage.click_searchBTN();//点击搜索按钮
			Thread.sleep(3000);
			superAgentAuditPage.click_operationBTN();//点击第一行的审核按钮
			/*审核页面*/
			superAgentAuditPage.click_saveBTN();//点击“审核通过”按钮
			superAgentAuditPage.click_confirmBTN();//确认审核通过按钮
			driver.quit();
			
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		
	}

}
