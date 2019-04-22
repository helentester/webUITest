/**
 * @author helen
 * @date 2018年9月11日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseData;
import common.MyConfig;
import data.AgentData;
import data.GoodData;
import data.MemberData;
import dataProvider.PCProvider;
import wufu.wufu.PCPages.PC_goodsInfoPage;
import wufu.wufu.PCPages.PC_indexPage;
import wufu.wufu.PCPages.PC_orderInfoEditPage;
import wufu.wufu.PCPages.PC_orderInfoSummarizePage;

/**
 * @Description:会员体系测试
 */
public class MemberSystemTest {
	MyConfig myConfig = new MyConfig();
	BaseData baseData = new BaseData();
	GoodData goodData = new GoodData();
	AgentData agentData = new AgentData();
	MemberData memberData = new MemberData();

	AgentRegistTest agentRegistTest = new AgentRegistTest();
	SellerRegistTest sellerRegistTest = new SellerRegistTest();
	PC_registTest pc_registTest = new PC_registTest();
	LoginUITest loginUITest = new LoginUITest();
	PC_orderTest pc_orderTest = new PC_orderTest();
	
	@BeforeClass
	public void checkTestData() {
		Reporter.log("保证商品是上架状态");
		goodData.update_onliveStatus("7735", "1");
	}
	
	@Test
	public void member_grade_new() {
		Reporter.log("普通会员：新注册默认为普通会员");
		String member = baseData.getPhoneNumber();
		pc_registTest.regist("phone", member);
		assertTrue(memberData.memberExit(member),"检验会员注册成功");
		assertEquals(memberData.get_memberGrade(member), "1","会员账号为："+member);
	}
	
	@Test(dataProvider="memberGrade",dataProviderClass=PCProvider.class)
	public void member_grade_spended(String gradeName,String spended,String grade) {
		Reporter.log(gradeName+"：总订单消费满"+spended);
		String member = baseData.getPhoneNumber();
		this.memberGradeUp(member, Integer.valueOf(spended));
		assertEquals(memberData.get_memberGrade(member), grade,"会员账号为："+member);
	}

	@Test
	public void agent_grade_new() {
		Reporter.log("普通代理：新注册默认为普通代理");
		String agent = baseData.getPhoneNumber();
		agentRegistTest.regist(agent, "");
		assertEquals(agentData.getAgentValue(agent,"person_invite_grade"), "1","代理商为："+agent);
	}
	
	@Test
	public void agent_grade_inviteAgent() {
		Reporter.log("普通代理：邀请代理商不能升级");
		String agent = baseData.getPhoneNumber();
		agentRegistTest.registAndAudit(agent, "");//新注册代理商
		
		try {
			agentRegistTest.registAndAudit(baseData.getPhoneNumber(), agent);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(agentData.getAgentValue(agent,"person_invite_grade"), "1","代理商为："+agent);
	}
	
	@Test
	public void agent_grade_sellerNoAudit() {
		Reporter.log("普通代理：邀请商家未审核不能升级");
		String agent = baseData.getPhoneNumber();
		agentRegistTest.registAndAudit(agent, "");//新注册代理商
		
		try {
			sellerRegistTest.sellerRegist(baseData.getPhoneNumber(),"会员体系seller" + baseData.getNum(1, 10000), "汽车", agent);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(agentData.getAgentValue(agent,"person_invite_grade"), "1","代理商为："+agent);
	}
	
	@Test(dataProvider="agentGrade",dataProviderClass=PCProvider.class)
	public void agent_grade_inviteSeller(String gradeName,String inviteCount,String grade) {
		Reporter.log(gradeName+"：代理"+inviteCount+"商家");
		try {
			String agent = baseData.getPhoneNumber();
			this.agentGradeUp(agent, Integer.valueOf(inviteCount));
			Thread.sleep(1000);
			assertEquals(Integer.valueOf(agentData.get_inviteSelerCount(agent)), Integer.valueOf(inviteCount),"校验邀请的商家数量是否达标");
			assertEquals(agentData.getAgentValue(agent,"person_invite_grade"), grade,"校验代理商等级,代理商为："+agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void member_loginTest() {
		Reporter.log("密码机制：注册会员－登录代理商中心－登录商家中心－登录PC商城");
		String member = baseData.getPhoneNumber();
		pc_registTest.regist("phone", member);

		// 登录代理商中心
		WebDriver driver = loginUITest.agentLogin(member, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("DL_domainName") + "/#/registBusinessInfo?tag=0",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录商家中心
		driver = loginUITest.sellerLogin(member, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("sj_domainName") + "/#/businessSettledCategory?tag=0",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录PC商城
		driver = loginUITest.PCLogin(member, "123456li");
		try {
			PC_indexPage pc_indexPage = PageFactory.initElements(driver, PC_indexPage.class);
			assertEquals(pc_indexPage.get_account(), member);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

	}

	@Test
	public void seller_loginTest() {
		Reporter.log("密码机制：注册商家－登录商家中心－登录代理中心－登录PC商城");
		String seller = baseData.getPhoneNumber();
		System.out.println(seller);
		sellerRegistTest.sellerRegist(seller,"会员体系seller" + baseData.getNum(1, 10000), "汽车", "");

		// 登录代理商中心
		WebDriver driver = loginUITest.agentLogin(seller, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("DL_domainName") + "/#/registBusinessInfo?tag=0",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录商家中心
		driver = loginUITest.sellerLogin(seller, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("sj_domainName") + "/#/registBusinessChecking",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录PC商城
		driver = loginUITest.PCLogin(seller, "123456li");
		try {
			PC_indexPage pc_indexPage = PageFactory.initElements(driver, PC_indexPage.class);
			assertEquals(pc_indexPage.get_account(), seller);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

	}

	@Test
	public void agent_loginTest() {
		Reporter.log("密码机制：新增代理商－登录代理中心－登录商家中心－登录PC商城");
		String agent = baseData.getPhoneNumber();
		agentRegistTest.regist(agent, "");

		// 登录商家中心
		WebDriver driver = loginUITest.sellerLogin(agent, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("sj_domainName") + "/#/businessSettledCategory?tag=0",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录代理商中心
		driver = loginUITest.agentLogin(agent, "123456li");
		try {
			Thread.sleep(3000);
			assertEquals(driver.getCurrentUrl(),
					myConfig.getKeys("DL_domainName") + "/#/registBusinessChecking",
					"当前URL为" + driver.getCurrentUrl());
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

		// 登录PC商城
		driver = loginUITest.PCLogin(agent, "123456li");
		try {
			PC_indexPage pc_indexPage = PageFactory.initElements(driver, PC_indexPage.class);
			assertEquals(pc_indexPage.get_account(), agent);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

	}

	/*会员升级测试
	 * @account 账号
	 * @price 售价
	 * */
	public void memberGradeUp(String account,int price) {
		Reporter.log("注册新会员："+account);//新注册用户
		pc_registTest.regist("phone", account);
		assertTrue(memberData.memberExit(account),"检验会员注册成功");
		
		if (memberData.memberExit(account)) {
			WebDriver driver = loginUITest.PCLogin(account, "123456li");
			pc_orderTest.check_addrAndCart(account);//检查购物车、收货地址等
			//memberData.update_AbleScore(account, String.valueOf(price));//更新福豆，使得能满足购买
			//下订单
			try {
				Thread.sleep(1000);
				driver.get(myConfig.getKeys("PC_domainName")+"/goods/goodsInfo?id=7735");
				
				Thread.sleep(1000);
				PC_goodsInfoPage goodsInfoPage = PageFactory.initElements(driver, PC_goodsInfoPage.class);
				if (price==99) {
					goodsInfoPage.click_sku1();
				}
				else if (price==100) {
					goodsInfoPage.click_sku2();
				}
				else if (price==999) {
					goodsInfoPage.click_sku3();
				}
				else if (price==1000) {
					goodsInfoPage.click_sku4();
				}
				else if (price==4999) {
					goodsInfoPage.click_sku5();
				}
				else if (price==5000) {
					goodsInfoPage.click_sku6();
				}
				else if (price==9999) {
					goodsInfoPage.click_sku7();
				}
				else if (price==10000) {
					goodsInfoPage.click_sku8();
				}
				else if (price==99999) {
					goodsInfoPage.click_sku9();
				}
				else {
					System.out.println("选择SKU失败");
				}
				goodsInfoPage.click_buyBtn();//立即购买
				
				Thread.sleep(3000);
				PC_orderInfoEditPage orderInfoEditPage = PageFactory.initElements(driver, PC_orderInfoEditPage.class);
				orderInfoEditPage.click_gotoPay();//提交订单
				
				Thread.sleep(3000);
				PC_orderInfoSummarizePage orderInfoSummarizePage = PageFactory.initElements(driver, PC_orderInfoSummarizePage.class);
				orderInfoSummarizePage.click_payBtn();
				Thread.sleep(5000);
				driver.quit();
			} catch (Exception e) {
				driver.quit();
				e.printStackTrace();
			}
			
		}
	}
	
	/*代理商升级
	 * @account 账号
	 * @sellerNB 邀请的商家数
	 * */
	public void agentGradeUp(String account,int sellerNB) {
		agentRegistTest.registAndAudit(account, "");//注册新的代理商
		
		//邀请商家
		try {
			if (agentData.agentExist(account)) {
				for(int i=0;i<sellerNB;i++) {
					String seller = baseData.getPhoneNumber();
					sellerRegistTest.sellerRegistAndAuditPass(seller,"会员体系seller" + baseData.getNum(1, 10000), "汽车", account);
				}
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
