/**
 * @author helen
 * @date 2018年7月11日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:管理后台：新代理商审核+审核页面
 */
public class SysNewAgentAuditPage extends BasePage{

	public SysNewAgentAuditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/***************新代理审核列表－查询版块**************************/
	@FindBy(id="account")
	private WebElement account;//联系电话
	public void setAccount(String s) {
		this.sendkeys(account, s);
	}
	
	@FindBy(id="search")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}

	/****************新代理商审核列表－列表数据***********************/
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr[1]/td[2]")
	private WebElement agentAuditList_name;//新代理商审核列表第一行：代理商名称
	public String getAgentAuditList_name() {
		return this.findMyElement(agentAuditList_name).getText();
	}
	
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr[1]/td[5]")
	private WebElement agentAuditList_phone;//新代理商审核列表第一行：联系电话
	public String getAgentAuditList_phone() {
		return this.findMyElement(agentAuditList_phone).getText();
	}
	
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr[1]/td[7]")
	private WebElement agentAuditList_auditStatues;//新代理商审核列表第一行：审核状态
	public String getAgentAuditList_auditStatues() {
		return this.findMyElement(agentAuditList_auditStatues).getText();
	}

	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr[1]/td[9]/a")
	private WebElement agentAuditList_operationLink;//新代理商审核列表第一行：操作按钮
	public void click_agentAuditList_operationLink() {
		this.click(agentAuditList_operationLink);
	}
	
	/*****************新代理商审核页面********************/
	@FindBy(id="account")
	private WebElement agentAudit_account;//登录手机号
	public String get_agentAudit_account() {
		return this.findMyElement(agentAudit_account).getText();
	}
	
	@FindBy(id="save")
	private WebElement agentAuditSaveBTN;//审核通过按钮
	public void click_agentAuditSaveBTN() {
		this.click(agentAuditSaveBTN);
	}
	
	@FindBy(id="confirm")
	private WebElement agentAuditSaveConfirmBTN;//审核通过－确认按钮 
	public void click_agentAuditSaveConfirmBTN() {
		this.click(agentAuditSaveConfirmBTN);
	}
}
