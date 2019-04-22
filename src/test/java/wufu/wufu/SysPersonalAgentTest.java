/**
 * @author helen
 * @date 2018年7月5日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.MyConfig;
import data.AgentData;
import data.CouponData;
import data.MemberData;
import wufu.wufu.sysPages.SysIndexPage;
import wufu.wufu.sysPages.SysNewAgentAuditPage;
import wufu.wufu.sysPages.SysPersonalAgentPage;

/**
 * @Description:个人代理商管理
 */
public class SysPersonalAgentTest {
	MyConfig myConfig = new MyConfig();
	BaseData baseData = new BaseData();
	AgentData agentData = new AgentData();
	MemberData memberData = new MemberData();
	CouponData couponData = new CouponData();
	LoginUITest loginUITest = new LoginUITest();
	SysIndexPage indexPage;// 管理系统主页
	SysPersonalAgentPage personalAgentPage;// 个人代理商列表+新增代理商页面
	SysNewAgentAuditPage auditPage;//代理商审核列表+审核页面
	
	String phoneNB;

	@Test
	public void test_addAgent_noinvite(){
		Reporter.log("运营管理中心新增代理商（无邀请人），并审核通过");
		
		try {
			phoneNB = baseData.getPhoneNumber();// 手机号
			Reporter.log("1）新增代理商"+phoneNB);
			this.addAgent(phoneNB,"");
			assertTrue(agentData.agentExist(phoneNB), "校验新增代理商是否成功");
			
			Thread.sleep(1000);
			Reporter.log("2）代理商注册，同时也是会员注册成功后，应当获得100福豆");
			assertEquals(Double.valueOf(memberData.get_ableScore(phoneNB)), Double.valueOf("100"));//代理商也是会员，送100积分
			
			Reporter.log("3）代理商注册，同时也是会员注册成功后，应当获得注册券");
			assertTrue(couponData.hadCoupon(phoneNB, myConfig.getKeys("couponId")),"校验是否成功获得注册券");//237Id=920,236Id=785
			
			Reporter.log("4）代理商审核[通过]");
			this.mallAgentAudit(phoneNB);
			Thread.sleep(1000);
			assertEquals(agentData.getAgentValue(phoneNB,"auditor_status"),"1", "新代理商审核,被审核账号为："+phoneNB);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void test_addAgent_inviteByPhone(){
		Reporter.log("运营管理中心新增代理商（邀请人输入手机号）");
		phoneNB = baseData.getPhoneNumber();// 手机号
		this.addAgent(phoneNB,agentData.get_FirstAuditAgent());

		assertEquals(agentData.getAgentValue(phoneNB,"auditor_status"),"2", "新代理账号为："+phoneNB);
		assertEquals(Double.valueOf(memberData.get_ableScore(phoneNB)), Double.valueOf("100"));//代理商也是会员，送100积分
	}
	
	/*后台新增代理商方法
	 * @agentPhone  新增的代理商账号
	 * @inviterAccount	邀请人账号
	 * */
	public String addAgent(String agentPhone,String inviterAccount){
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		try {
			/* 管理系统主页 */
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("代理管理");
			indexPage.clickSubMenu("商城代理列表");

			/* 个人代理商列表页面 */
			driver.switchTo().frame("iframepage");// 因为主版面是通过iframe嵌入的，所以要切换driver到iframe
			personalAgentPage = PageFactory.initElements(driver, SysPersonalAgentPage.class);
			personalAgentPage.clickAddAgentBTN();// 点击新增代理商页面

			/* 新增代理商页面 */
			Thread.sleep(2000);
			// 基本信息
			String agentName = "agent" + baseData.getNum(1, 1000);
			personalAgentPage.setAccount(agentPhone);// 手机号
			personalAgentPage.setPassword("123456li");// 密码
			personalAgentPage.setRealName(agentName);// 真实姓名
			personalAgentPage.selectProvince(baseData.getNum(2, 34));// 所在地区－省份,市区级默认取第一个
			personalAgentPage.setIdCard(baseData.getIdCard());// 身份证号
			personalAgentPage.setEmail(agentName + "@foisonagel.com");// email
			personalAgentPage.setIdCardCopiesFront();// 身份证正面
			personalAgentPage.setIdCardCopiesReverse();// 身份证反面
			// 银行信息
			personalAgentPage.setBankAccountName(agentName);// 持卡人
			personalAgentPage.setBankAccount("44254154844411");// 银行卡号
			personalAgentPage.selectBankType(baseData.getNum(1, 41));// 开户行
			personalAgentPage.setBankBranch("越秀支行");// 支行名称
			// 邀请人
			personalAgentPage.set_inviter(inviterAccount);
			personalAgentPage.clickSaveNewAgentBTN();// 保存按钮
			
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		
		return agentPhone;
		
	}
	
	/*商城代理审核业务*/
	public void mallAgentAudit(String account){
		/*登录*/
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		
		try {
			/* 管理系统主页 */
			Thread.sleep(5000);
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("代理管理");
			indexPage.clickSubMenu("商城代理审核");
			
			/*新代理商审核列表页面*/
			Thread.sleep(5000);
			driver.switchTo().frame("iframepage");
			auditPage = PageFactory.initElements(driver, SysNewAgentAuditPage.class);
			auditPage.setAccount(account);//输入联系电话
			auditPage.click_searchBTN();//点击搜索按钮
			auditPage.click_agentAuditList_operationLink();//点击新代理商审核列表第一行操作：审核
			
			/*新代理商审核页面*/
			Thread.sleep(5000);
			auditPage.click_agentAuditSaveBTN();//点击审核通过按钮
			auditPage.click_agentAuditSaveConfirmBTN();//审核通过－确认按钮
			driver.quit();
		} catch (Exception e) {
			Reporter.log("新代理商审核失败，代理商账号为："+account);
			driver.quit();
			e.printStackTrace();
		}	
	}
}
