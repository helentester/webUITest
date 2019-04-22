/**
 * @author helen
 * @date 2018年6月21日
 */
package wufu.wufu.sysPages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:管理系统模版：主页
 */
@SuppressWarnings("rawtypes")
public class SysIndexPage extends BasePage {

	public SysIndexPage(WebDriver driver) {
		super(driver);
	}

	// 登录后显示的用户名
	@FindBy(className = "username")
	private WebElement username;
	public String getUsername() {
		return this.findMyElement(username).getText();
	}

	/************* 菜单栏 ****************/
	/* 获取所有一级管理菜单 */
	//@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li/a/span[1]")
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li/a/span[1]")
	private List<WebElement> MainMenuList;
	public List<String> getMainMenuList() {
		List<String> MainMenuNameList = new ArrayList<String>();
		try {
			Thread.sleep(3000);
			List<WebElement> MainMenuElements = (List<WebElement>) this.findMyElements(MainMenuList);
			Iterator iterator = MainMenuElements.iterator();
			while (iterator.hasNext()) {
				WebElement wb = (WebElement) iterator.next();
				MainMenuNameList.add(wb.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MainMenuNameList;
	}

	/* 获取子菜单列表 */
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='A']/ul/li/a") 
	private List<WebElement> goodsSubMenuList;// 商品管理子菜单列表
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[8]/ul/li/a")
	private List<WebElement> healthCardMenuList;
	@FindBy(xpath=  "/html/body/section/aside/div/div[1]/ul/li[@id='W']/ul/li/a")
	private List<WebElement> marketingActivitiesList;//营销活动子菜单列表
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='P']/ul/li/a")
	private List<WebElement> dataCenterSubMenuList;//数据中心子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='J']/ul/li/a")
	private List<WebElement> commentSumMenuList;//评论系统子菜单
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='B']/ul/li/a")
	private List<WebElement> orderManageSubMenuList;//订单管理子菜单
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='C']/ul/li/a") 
	private List<WebElement> storeSubMenuList;// 商家管理子菜单列表
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='D']/ul/li[@style='display: block;']/a") 
	private List<WebElement> agentSubMenuList;// 代理管理子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='R']/ul/li/a")
	private List<WebElement> creditCardMenuList;//信用卡业务子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='O']/ul/li/a")
	private List<WebElement> healthHomeSubMenuList;//健康家采购子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='Q']/ul/li/a")
	private List<WebElement> signManageSubMenuList;//签到管理子菜单
	@FindBy(xpath = "/html/body/section/aside/div/div[1]/ul/li[@id='E']/ul/li/a") 
	private List<WebElement> memberSubMenuList;// 会员管理子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='F']/ul/li/a")
	private List<WebElement> authoritySubMenuList;//权限管理子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='G']/ul/li[@style='display: block;']/a")
	private List<WebElement> websiteSubMenuList;//站点管理
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='I']/ul/li/a")
	private List<WebElement> couponSubMenuList;//优惠券管理子菜单
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='L']/ul/li[@style='display: block;']/a")
	private List<WebElement> finaceSubMenuList;//财务管理
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='M']/ul/li/a")
	private List<WebElement> payManageSubMenuList;//线下支付管理子菜单列表
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='N']/ul/li/a")
	private List<WebElement> sysManageSubMenuList;//系统管理
	@FindBy(xpath="/html/body/section/aside/div/div[1]/ul/li[@id='S']/ul/li/a")
	private List<WebElement> feedbackList;//用户反馈子菜单列表

	public List<String> getSubMenuList(String mainMenuName){
		List<String> subMenuNameList = new ArrayList<String>();
		List<WebElement> subMenuElements = null;
		
		try {
			if (mainMenuName.equals("商品管理")) {
				this.clickMainMenu("商品管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(goodsSubMenuList);
			}
			else if (mainMenuName.equals("康养卡业务")) {
				this.clickMainMenu("康养卡业务");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(healthCardMenuList);
			}
			else if (mainMenuName.equals("营销活动")) {
				this.clickMainMenu("营销活动");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(marketingActivitiesList);
			}
			else if (mainMenuName.equals("数据中心")) {
				this.clickMainMenu("数据中心");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(dataCenterSubMenuList);
			}
			else if (mainMenuName.equals("评论管理")) {
				this.clickMainMenu("评论管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(commentSumMenuList);
			}
			else if (mainMenuName.equals("订单管理")) {
				this.clickMainMenu("订单管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(orderManageSubMenuList);
			}
			else if (mainMenuName.equals("商家管理")) {
				this.clickMainMenu("商家管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(storeSubMenuList);
			}
			else if (mainMenuName.equals("代理管理")) {
				this.clickMainMenu("代理管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(agentSubMenuList);
			}
			else if (mainMenuName.equals("信用卡业务")) {
				this.clickMainMenu("信用卡业务");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(creditCardMenuList);
			}
			else if (mainMenuName.equals("健康家")) {
				this.clickMainMenu("健康家");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(healthHomeSubMenuList);
			}
			else if (mainMenuName.equals("签到管理")) {
				this.clickMainMenu("签到管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(signManageSubMenuList);
			}
			else if (mainMenuName.equals("会员管理")) {
				this.clickMainMenu("会员管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(memberSubMenuList);
			}
			else if (mainMenuName.endsWith("权限管理")) {
				this.clickMainMenu("权限管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(authoritySubMenuList);
			}
			else if (mainMenuName.equals("站点管理")) {
				this.clickMainMenu("站点管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(websiteSubMenuList);
			}
			else if (mainMenuName.equals("优惠券管理")) {
				this.clickMainMenu("优惠券管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(couponSubMenuList);
			}
			else if (mainMenuName.equals("财务管理")) {
				this.clickMainMenu("财务管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(finaceSubMenuList);
			}
			else if (mainMenuName.equals("线下支付")) {
				this.clickMainMenu("线下支付");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(payManageSubMenuList);
			}
			else if (mainMenuName.equals("系统管理")) {
				this.clickMainMenu("系统管理");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(sysManageSubMenuList);
			}
			else if (mainMenuName.equals("用户反馈")) {
				this.clickMainMenu("用户反馈");
				Thread.sleep(1000);
				subMenuElements = this.findMyElements(feedbackList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Iterator iterator = subMenuElements.iterator();
		while (iterator.hasNext()) {
			WebElement wb = (WebElement) iterator.next();
			subMenuNameList.add(wb.getText());
			//System.out.println(wb.getText());
		}
		return subMenuNameList;
	}

	/********** 点击主菜单 **********/
	@FindBy(linkText = "商品管理")
	private WebElement goodsManageLink;
	@FindBy(linkText = "康养卡业务")
	private WebElement healthCardLink;
	@FindBy(linkText = "营销活动")
	private WebElement marketingActivitiesLink;
	@FindBy(linkText = "数据中心")
	private WebElement dataCenterLink;
	@FindBy(linkText = "评论管理")
	private WebElement commentSysLink;
	@FindBy(linkText = "订单管理")
	private WebElement orderManageLink;
	@FindBy(linkText = "商家管理")
	private WebElement storeManageLink;
	@FindBy(linkText = "代理管理")
	private WebElement agentManageLink;
	@FindBy(linkText= "信用卡业务")
	private WebElement creditCardLink;
	@FindBy(linkText = "健康家")
	private WebElement healthHomeLink;
	@FindBy(linkText= "签到管理")
	private WebElement signManageLink;
	@FindBy(linkText = "会员管理")
	private WebElement memberManageLink;
	@FindBy(linkText = "权限管理")
	private WebElement authorityManageLink;
	@FindBy(linkText = "站点管理")
	private WebElement websiteManageLink;
	@FindBy(linkText = "优惠券管理")
	private WebElement couponManageLink;
	@FindBy(linkText = "财务管理")
	private WebElement finaceManageLink;
	@FindBy(linkText = "线下支付")
	private WebElement payManageLink;
	@FindBy(linkText = "系统管理")
	private WebElement sysManageLink;
	@FindBy(linkText = "用户反馈")
	private WebElement feedbackLink;
	
	public void clickMainMenu(String MainMenuName) {
		if (MainMenuName.equals("商品管理")) {
			this.click(goodsManageLink);
		}
		else if (MainMenuName.equals("康养卡业务")) {
			this.click(healthCardLink);
		}
		else if (MainMenuName.equals("营销活动")) {
			this.click(marketingActivitiesLink);
		}
		else if (MainMenuName.equals("数据中心")) {
			this.click(dataCenterLink);
		}
		else if (MainMenuName.equals("评论管理")) {
			this.click(commentSysLink);
		}
		else if (MainMenuName.equals("订单管理")) {
			this.click(orderManageLink);
		}
		else if (MainMenuName.equals("商家管理")) {
			this.click(storeManageLink);			
		}
		else if(MainMenuName.equals("代理管理")){
			this.click(agentManageLink);
		}
		else if (MainMenuName.equals("信用卡业务")) {
			this.click(creditCardLink);
		}
		else if (MainMenuName.equals("健康家")) {
			this.click(healthHomeLink);
		}
		else if (MainMenuName.equals("签到管理")) {
			this.click(signManageLink);
		}
		else if (MainMenuName.equals("会员管理")) {
			this.click(memberManageLink);
		}
		else if (MainMenuName.equals("权限管理")) {
			this.click(authorityManageLink);
		}
		else if (MainMenuName.equals("站点管理")) {
			this.click(websiteManageLink);
		}
		else if (MainMenuName.equals("优惠券管理")) {
			this.click(couponManageLink);
		}
		else if (MainMenuName.equals("财务管理")) {
			this.click(finaceManageLink);
		}
		else if (MainMenuName.equals("线下支付")) {
			this.click(payManageLink);
		}
		else if (MainMenuName.equals("系统管理")) {
			this.click(sysManageLink);
		}
		else if (MainMenuName.equals("用户反馈")) {
			this.click(feedbackLink);
		}
		else {
			System.err.println("获取菜单失败");
		}
	}
	
	/*********点击子菜单************/
	//代理商管理子菜单
	@FindBy(linkText="健康家列表")
	private WebElement superAgentListMenuLink;
	@FindBy(linkText="商城代理列表")
	private WebElement personalAgentMenuLink;
	@FindBy(linkText="商城代理审核")
	private WebElement newAgentAuditMenuLink;
	@FindBy(linkText="代理修改审核")
	private WebElement agentUpdateAuditMenuLink;
	@FindBy(linkText="健康家代理审核")
	private WebElement superAgentAuditMenuLink;
	//商家管理子菜单
	@FindBy(linkText="商家列表")
	private WebElement sellerList;
	@FindBy(linkText="商家入驻审核")
	private WebElement sellerAudit;
	@FindBy(linkText="商家修改审核")
	private WebElement sellerUpdateAudit;
	//商品管理子菜单
	@FindBy(linkText="商品列表")
	private WebElement goodList;
	//订单管理子菜单
	@FindBy(linkText="订单列表")
	private WebElement orderList;
	@FindBy(linkText="退货退款管理")
	private WebElement refundList;
	//评论管理子菜单
	@FindBy(linkText="评论列表")
	private WebElement evaluateListLink;//评论列表
	public void clickSubMenu(String subMenuName) {
		if (subMenuName.equals("商品列表")) {
			this.click(goodList);
		}
		else if (subMenuName.equals("商家列表")) {
			this.click(sellerList);
		}
		else if (subMenuName.equals("商家入驻审核")) {
			this.click(sellerAudit);
		}
		else if (subMenuName.equals("商家修改审核")) {
			this.click(sellerUpdateAudit);
		}
		else if(subMenuName.equals("商城代理列表")) {
			this.click(personalAgentMenuLink);
		}
		else if (subMenuName.equals("商城代理审核")) {
			this.click(newAgentAuditMenuLink);
		}
		else if (subMenuName.equals("代理修改审核")) {
			this.click(agentUpdateAuditMenuLink);
		}
		else if (subMenuName.equals("健康家代理审核")) {
			this.click(superAgentAuditMenuLink);
		}
		else if (subMenuName.equals("健康家列表")) {
			this.click(superAgentListMenuLink);
		}
		else if (subMenuName.equals("订单列表")) {
			this.click(orderList);
		}
		else if (subMenuName.equals("退货退款管理")) {
			this.click(refundList);
		}
		else if (subMenuName.equals("评论列表")) {
			this.click(evaluateListLink);
		}
		else {
			System.out.println("获取子菜单失败");
		}
	}

}
