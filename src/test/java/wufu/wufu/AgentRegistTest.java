/**
 * @author helen
 * @date 2018年7月10日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import data.AgentData;
import data.CommonData;
import data.MemberData;
import wufu.wufu.agentPages.AgentIndexPage;
import wufu.wufu.agentPages.AgentLoginPage;
import wufu.wufu.agentPages.AgentRegistBusinessInfoPage;
import wufu.wufu.agentPages.AgentRegistPage;
import wufu.wufu.agentPages.AgentSuperAgentApplicationPage;

/**
 * @Description:代理商注册流程测试
 */
public class AgentRegistTest {
	BaseData baseData = new BaseData();
	BaseWinUI winUI = new BaseWinUI();
	MyConfig myConfig = new MyConfig();
	CommonData data = new CommonData();
	AgentData agentData = new AgentData();
	MemberData memberData = new MemberData();
	SysPersonalAgentTest personalAgentTest = new SysPersonalAgentTest();
	LoginUITest loginUITest = new LoginUITest();
	SysSuperAgentTest superAgentTest = new SysSuperAgentTest();

	AgentLoginPage loginPage;
	AgentRegistPage registPage;
	AgentRegistBusinessInfoPage infoPage;
	String phone;// 注册的手机号

	@Test
	public void regist() {
		Reporter.log("代理商注册(身份包含商城代理、保险代理、信用卡代理)－完善信息－审核通过");
		
		phone = baseData.getPhoneNumber();
		Reporter.log("1）提交注册资料，代理商账号："+phone);
		this.regist(phone, "");
		assertTrue(agentData.agentExist(phone),"校验代理商是否注册成功");
		
		Reporter.log("2）商城代理审核通过");
		personalAgentTest.mallAgentAudit(phone);
		assertEquals(agentData.getAgentValue(phone,"auditor_status"), "1", "商城代理审核校验：审核通过");//校验审核状态
		assertEquals(Double.valueOf(memberData.get_ableScore(phone)), Double.valueOf("100"),"校验新用户送100福豆的情况");//注册的代理商也是会员，送100福豆
		
		Reporter.log("3）信用卡代理审核通过（未完成）");
	}

	/*健康家业务已弃用，不再做回归检查*/
	@Test(enabled=false)
	public void test_healthHomeRegist() {
		Reporter.log("注册5个代理商后(含邀请码)，申请成为健康家");
		String healthHome = baseData.getPhoneNumber();
		this.healthHomeRegist(healthHome,"");
		
		assertEquals(agentData.getAgentValue(healthHome,"super_auditor_status"), "1","健康家账号为："+healthHome);
		assertEquals(Double.valueOf(memberData.get_ableScore(phone)), Double.valueOf("100"));//注册的代理商也是会员，送100福豆
	}
	
	/*健康家注册：生成代理商－发展5个代理商－申请成为健康家－审核通过
	 * @healthHomeAccount	健康家账号
	 * @inviter	邀请人账号
	 * */
	public String healthHomeRegist(String healthHomeAccount,String inviter) {
		try {
			// 即将成为健康家的账号首先注册成为代理商
			this.registAndAudit(healthHomeAccount, inviter);

			if (agentData.getAgentValue(healthHomeAccount,"auditor_status").equals("1")) {
				// 发展5个代理商
				for (int i = 1; i < 6; i++) {
					phone = baseData.getPhoneNumber();
					this.registAndAudit(phone, healthHomeAccount);
				}
				/* 申请成为健康家 */
				this.HealthHomeApply(healthHomeAccount);
				/* 进入后台审核超市代理商 */
				superAgentTest.healthHomeAudit(healthHomeAccount);
				Thread.sleep(3000);
			} else {
				assertTrue(false, "账号不存在或审核不通过：" + healthHomeAccount);
			}
		} catch (Exception e) {
			assertTrue(false);
			Reporter.log("健康家申请业务失败，账号为：" + healthHomeAccount);
			e.printStackTrace();
		}
		return healthHomeAccount;
	}

	/*普通代理商申请成为健康家
	 * @account	想申请成为健康家的账号
	 * */
	public void HealthHomeApply(String account) {
		/* 申请成为健康家 */
		WebDriver driver = loginUITest.agentLogin(account, "123456li");
		try {
			Thread.sleep(2000);
			AgentIndexPage indexPage = PageFactory.initElements(driver, AgentIndexPage.class);
			indexPage.click_gotoInvitingAwardsBTN();// 进入邀请奖励页面
			AgentSuperAgentApplicationPage applicationPage = PageFactory.initElements(driver,
					AgentSuperAgentApplicationPage.class);
			applicationPage.setBusinessLicense();// 上传营业执照
			applicationPage.setIdCardFront();// 上传法人身份证正面
			applicationPage.setIdCardReverse();// 上传法人身份证反面
			applicationPage.click_saveBTN();// 保存按钮
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
	}

	/*
	 * 代理商注册－并审核通过
	 * 
	 * @reregistPhone 代理商注册号码
	 * 
	 * @agentCode 上级代理商邀请码
	 */
	public String registAndAudit(String registPhone, String inviterAccount) {
		this.regist(registPhone, inviterAccount);
		assertTrue(agentData.agentExist(registPhone),"校验代理商是否注册成功");
		personalAgentTest.mallAgentAudit(registPhone);
		return registPhone;
	}

	/*
	 * 代理商注册－初始化信息
	 * 
	 * @reregistPhone 代理商注册号码
	 * 
	 * @agentCode 上级代理商邀请码
	 */
	public void regist(String registPhone, String inviterAccount) {
		WebDriver driver = winUI.getDrive();
		driver.get(myConfig.getKeys("DL_domainName"));
		try {
			/* 登录页面 */
			loginPage = PageFactory.initElements(driver, AgentLoginPage.class);
			loginPage.click_registLink();// 点击注册链接

			/* 注册页面 */
			Thread.sleep(5000);
			registPage = PageFactory.initElements(driver, AgentRegistPage.class);
			registPage.setPhone(registPhone);// 输入手机号
			registPage.click_sendCodeBTN();// 点击“获取验证码”
			registPage.setSendCode(data.getVerificationCode_ByPhone(registPhone));//输入验证码
			registPage.setPassword("123456li");// 设置密码
			registPage.set_inviterAccount(inviterAccount);// 填写邀请人账号
			registPage.click_submitBTN();// 提交

			/* 代理商信息完善页面 */
			Thread.sleep(5000);
			infoPage = PageFactory.initElements(driver, AgentRegistBusinessInfoPage.class);
			//代理身份
			infoPage.click_mallAgent();//商城代理
			infoPage.click_creditCardAgent();//信用卡代理
			infoPage.click_insuranceAgent();//保险代理
			// 基本信息
			String agentName = "agent" + baseData.getNum(1, 10000);
			infoPage.setRealName(agentName);// 真实姓名
			infoPage.setIdCard(baseData.getIdCard());// 身份证号
			infoPage.setEmail(agentName + "@foisonagel.com");// email
			infoPage.selectProvince(baseData.getNum(1, 6));// 省份
			infoPage.setIdCardFront();// 上传身份证正面
			infoPage.setIdCardBack();// 上传身份证反面
			// 银行信息
			infoPage.setBankUsername(agentName);// 开户名
			infoPage.setBankNUM("784214513454");// 银行卡号
			infoPage.selectBankName(baseData.getNum(1, 6));// 开户银行
			infoPage.setBranchBankName("天河支行");// 支行
			

			infoPage.click_submitBTN();// 提交审核
			driver.quit();
		} catch (Exception e) {
			Reporter.log("注册代理商失败，注册手机号为：" + registPhone);
			driver.quit();
			e.printStackTrace();
		}

	}
}
