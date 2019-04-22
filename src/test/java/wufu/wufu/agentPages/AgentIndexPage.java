/**
 * @author helen
 * @date 2018年7月13日
 */
package wufu.wufu.agentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import common.BasePage;

/**
 * @Description:代理商管理系统－首页
 */
public class AgentIndexPage extends BasePage{

	public AgentIndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(className="avator")
	private WebElement headImg;// 头像
	public Boolean headImg_isDisplay() {
		return this.findMyElement(headImg).isDisplayed();
	}
	
	@FindBy(className="top-username")
	private WebElement topUserName;//版头用户名
	public String get_TopUserName() {
		return this.findMyElement(topUserName).getText();
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div/div[2]/section/div[2]/div[4]/div/div[1]/div[2]")
	private WebElement subAgentCount;//代理商加盟总数
	public String getSubAgentCount() {
		return this.findMyElement(subAgentCount).getText();
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[1]/div/div[2]/div[1]/span")
	private WebElement gotoInvitingAwardsBTN;//
	public void click_gotoInvitingAwardsBTN() {
		this.click(gotoInvitingAwardsBTN);
	}
	
	/**********************主菜单***********************/
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[1]/div")
	private WebElement menuIndex;//首页
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[2]/div")
	private WebElement menuOrderManage;//订单管理
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[3]/div")
	private WebElement menuAssetCenter;//资产中心
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[4]/div")
	private WebElement menuSellerManage;//商家管理
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[5]/div")
	private WebElement menuMyInfo;//我的信息	
	
	public void click_menu(String menuName) {
		if (menuName.equals("首页")) {
			this.click(menuIndex);
		}
		else if (menuName.equals("订单管理")) {
			this.click(menuOrderManage);
		}
		else if (menuName.equals("资产中心")) {
			this.click(menuAssetCenter);
		}
		else if (menuName.equals("商家管理")) {
			this.click(menuSellerManage);
		}
		else if (menuName.equals("我的信息")) {
			this.click(menuMyInfo);
		}
		else {
			Reporter.log("代理商管理后台，没有相应的主菜单");
		}
		this.click(menuIndex);
	}
	
	/*************************子菜单***********************/
	/*资产中心的子菜单*/
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[3]/ul/li[1]")
	private WebElement subMenu_cashOrderManage;//提现管理
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[3]/ul/li[2]")
	private WebElement subMenu_cashFlow;//资金流水
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[3]/ul/li[3]")
	private WebElement subMenu_cashUnused;//不可用余额
	@FindBy(xpath="/html/body/div/div/div/div[1]/ul/li[3]/ul/li[4]")
	private WebElement subMenu_InvitingAwards;//邀请奖励
	
	public void click_subMenu(String subMenuName) {
		if (subMenuName.equals("提现管理")) {
			this.click(subMenu_cashOrderManage);
		}
		else if (subMenuName.equals("资金流水")) {
			this.click(subMenu_cashFlow);
		}
		else if (subMenuName.equals("不可用余额")) {
			this.click(subMenu_cashUnused);
		}
		else if (subMenuName.equals("邀请奖励")) {
			this.click(subMenu_InvitingAwards);
		}
		else {
			Reporter.log("代理商管理系统，没有相应的子菜单");
		}
	}

}
