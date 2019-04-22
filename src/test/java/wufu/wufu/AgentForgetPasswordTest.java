/**
 * @author helen
 * @date 2018年9月10日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import data.CommonData;
import wufu.wufu.agentPages.AgentForgetPasswordPage;
import wufu.wufu.agentPages.AgentLoginPage;

/**
 * @Description:代理中心忘记密码业务测试
 */
public class AgentForgetPasswordTest {
	BaseWinUI baseWinUI = new BaseWinUI();
	BaseData baseData = new BaseData();
	MyConfig myConfig = new MyConfig();
	CommonData data = new CommonData();
	AgentLoginPage agentLoginPage;
	AgentForgetPasswordPage agentForgetPasswordPage;
	
	@Test
	public void forgetPassword() {
		Reporter.log("忘记密码测试");
		String account = myConfig.getKeys("agent1");
		
		//根据旧密码设置新密码
		String Password_last = data.get_password(account);
		String Password_new = "";
		if (Password_last.equals("2d572ee9061abe31bb1e81fa2f654067")) {
			Password_new = "12345678li";
		}
		else if (Password_last.equals("2002bb380806ed22e636c6b222395b0b")) {
			Password_new = "123456li";
		}
		
		//页面修改密码
		WebDriver driver = baseWinUI.getDrive();
		
		try {
			driver.get(myConfig.getKeys("DL_domainName"));
			agentLoginPage = PageFactory.initElements(driver, AgentLoginPage.class);
			agentLoginPage.click_forgetPasswordLink();
			
			/*忘记密码页面*/
			agentForgetPasswordPage = PageFactory.initElements(driver, AgentForgetPasswordPage.class);
			agentForgetPasswordPage.set_account(account);//手机号
			agentForgetPasswordPage.set_password(Password_new);//新密码
			agentForgetPasswordPage.click_getCodeBTN();//点击获取验证码按钮
			agentForgetPasswordPage.set_verifCode(data.getVerificationCode_ByPhone(account));
			agentForgetPasswordPage.click_saveBTN();
			Thread.sleep(30000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		assertEquals(data.get_password(account),baseData.getMD5(Password_new));
	}

}
