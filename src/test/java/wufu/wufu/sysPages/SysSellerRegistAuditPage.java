/**
 * @author helen
 * @date 2018年7月12日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:后台管理－商家审核页面
 */
public class SysSellerRegistAuditPage extends BasePage{

	public SysSellerRegistAuditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div/div[3]/button")
	private WebElement addSellerBTN;//添加商家按钮
	public void click_addBTN() {
		this.click(addSellerBTN);
	}
	
	/***********列表查询版块**************/
	@FindBy(id="legalMobile")
	private WebElement legalMobile;//法人手机号
	public void setLegalMobile(String s) {
		this.sendkeys(legalMobile, s);
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div/span/input")
	private WebElement sellerName;//商家名称
	public void set_sellerName(String s) {
		this.sendkeys(sellerName, s);
	}
	
	@FindBy(xpath="//form/div[8]/div/div/span/button")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	
	/**********列表数据***************/
	@FindBy(xpath="//table/tbody/tr[1]/td[10]/button")
	private WebElement operationBTN;//列表第一行：操作按钮
	public void click_operationBTN() {
		this.click(operationBTN);
	}
	
	/**********商家审核页面***********/
	@FindBy(id="save")
	private WebElement saveBTN;//审核通过按钮
	public void click_saveBTN() {
		this.click(saveBTN);
	}
	
	@FindBy(id="reset")
	private WebElement resetBTN;//审核不通过按钮
	public void click_resetBTN() {
		this.click(resetBTN);
	}
	
	@FindBy(id="contractStartDate")
	private WebElement contractStartDate;//合同有效期：开始
	public void setContractStartDate(String s) {
		this.sendkeys(contractStartDate, s);
		this.Keyboard_event(contractStartDate, Keys.ENTER);
	}
	
	@FindBy(id="contractEndDate")
	private WebElement contractEndDate;//合同有效期：结束
	public void setContractEndDate(String s) {
		this.sendkeys(contractEndDate, s);
		this.Keyboard_event(contractEndDate, Keys.ENTER);
	}
	
	@FindBy(xpath="//button[@onclick='confirmMessage()']")
	private WebElement sureContractDate;//合同有效期确认按钮
	public void click_sureContractDate() {
		System.out.println("点击确认合同按钮");
		this.click(sureContractDate);
	}
}
