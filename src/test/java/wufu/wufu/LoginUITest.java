/**
 * @author helen
 * @date 2018年6月21日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import data.CommonData;
import wufu.wufu.PCPages.PC_indexPage;
import wufu.wufu.PCPages.PC_loginPage;
import wufu.wufu.agentPages.AgentIndexPage;
import wufu.wufu.agentPages.AgentLoginPage;
import wufu.wufu.sellerPages.SellerIndexPage;
import wufu.wufu.sellerPages.SellerLoginPage;
import wufu.wufu.sysPages.SysIndexPage;
import wufu.wufu.sysPages.SysLoginPage;

/**
 * @Description:登录模块UI测试
 */
public class LoginUITest {
	BaseData baseData = new BaseData();
	CommonData commonData = new CommonData();
	BaseWinUI winUI = new BaseWinUI();
	MyConfig myConfig = new MyConfig();
	WebDriver driver;
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}

	@Test
	public void test_sysLogin() {
		Reporter.log("管理系统登录");
		driver = this.sysLogin("helen", "123456li");
		// 管理系统主页
		SysIndexPage sysIndex = PageFactory.initElements(driver, SysIndexPage.class);
		assertEquals(sysIndex.getUsername(), "helen");
	}

	@Test
	public void test_agentLogin() {
		Reporter.log("代理商中心登录");
		driver = this.agentLogin("18702876997", "123456li");
		// 代理商管理系统主页 
		AgentIndexPage agentIndexPage = PageFactory.initElements(driver, AgentIndexPage.class);
		assertTrue(agentIndexPage.headImg_isDisplay());
	}

	@Test
	public void test_sellerLogin() throws InterruptedException {
		Reporter.log("商家管理后台登录");
		driver = sellerLogin("18602835867", "123456li");
		//商家中心主页
		Thread.sleep(2000);
		SellerIndexPage sellerIndexPage = PageFactory.initElements(driver, SellerIndexPage.class);
		assertEquals(sellerIndexPage.getUserName(), "18602835867");
	}
	
	@Test
	public void test_PCLogin() {
		driver = PCLogin("13825464584", "123456li");
		//PC商城首页
		PC_indexPage pc_indexPage = PageFactory.initElements(driver, PC_indexPage.class);
		assertEquals(pc_indexPage.get_account(), "13825464584");
	}

	/* 登录运营管理中心业务 */
	public WebDriver sysLogin(String username, String password) {
		WebDriver driver = winUI.getDrive();
		try {
			driver.get(myConfig.getKeys("YY_domainName"));
			// 登录页面
			SysLoginPage sysLoginPage = PageFactory.initElements(driver, SysLoginPage.class);
			sysLoginPage.setAcount(username);// 账号
			sysLoginPage.setPassWord(password);// 密码
			sysLoginPage.clickLoginSubmit();// 登录按钮
			
			//判断是否需要验证码
			String ip = "120.197.7.246";
			if (myConfig.getPropertyValue("testService").equals("237")) {
				ip = baseData.getLocalIP();
			}
			String lastIP = commonData.getMUserValue(username, "login_ip");
			if (ip.equals(lastIP)) {
				
			}
			else {
				Thread.sleep(1000);
				String mobile = commonData.getMUserValue(username, "mobile");
				sysLoginPage.clickGetCode();//获取验证码
				Thread.sleep(1000);
				String code = commonData.getVerificationCode_ByPhone(mobile);
				sysLoginPage.set_mobileCode(code);//输入短信验证码
				sysLoginPage.clickLoginSubmit();
			}
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		return driver;
	}
	
	/*代理中心登录*/
	public WebDriver agentLogin(String agentAccount, String password) {
		WebDriver driver = winUI.getDrive();
		try {
			driver.get(myConfig.getKeys("DL_domainName"));
			/* 登录页面 */
			AgentLoginPage agentLoginPage = PageFactory.initElements(driver, AgentLoginPage.class);
			agentLoginPage.setPhone(agentAccount);// 手机号
			agentLoginPage.setPassword(password);// 密码
			agentLoginPage.setVerificationCode("!@#$");// 验证码
			agentLoginPage.click_loginBTN();// 点击登录按钮
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return driver;
	}

	/* 商家管理后台登录业务 */
	public WebDriver sellerLogin(String account, String password) {
		WebDriver driver = winUI.getDrive();
		
		try {
			driver.get(myConfig.getKeys("sj_domainName"));
			/* 登录页 */
			SellerLoginPage sellerLoginPage = PageFactory.initElements(driver, SellerLoginPage.class);
			sellerLoginPage.setMobile(account);// 手机号
			sellerLoginPage.setPassword(password);// 密码
			sellerLoginPage.setVerificationCode("!@#$");// 验证码
			sellerLoginPage.clickSellerLoginBTN();// 登录按钮
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return driver;
	}

	/* PC商城登录 */
	public WebDriver PCLogin(String account, String password) {
		WebDriver driver = winUI.getDrive();
		try {
			driver.get(myConfig.getKeys("PC_domainName") + "/login");
			// 登录页
			PC_loginPage pcLoginPage = PageFactory.initElements(driver, PC_loginPage.class);
			pcLoginPage.set_account(account);
			pcLoginPage.set_password(password);
			pcLoginPage.click_loginBtn();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
			System.out.println("PC商城登录失败");
		}

		return driver;
	}

}
