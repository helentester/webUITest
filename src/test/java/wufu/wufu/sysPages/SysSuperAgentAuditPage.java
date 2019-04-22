/**
 * @author helen
 * @date 2018年7月13日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:后台管理系统－超市代理商审核管理
 */
public class SysSuperAgentAuditPage extends BasePage{

	public SysSuperAgentAuditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/****************查询版块****************/
	@FindBy(id="account")
	private WebElement phone;//联系电话
	public void setPhone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(id="search")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	/*************数据列表***************/
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr[1]/td[8]/a")
	private WebElement operationBTN;
	public void click_operationBTN() {
		this.click(operationBTN);
	}
	
	/**********审核页面**************/
	@FindBy(id="save")
	private WebElement saveBTN;//审核通过按钮
	public void click_saveBTN() {
		this.click(saveBTN);
	}
	
	@FindBy(id="confirm")
	private WebElement confirmBTN;//确认审核按钮
	public void click_confirmBTN() {
		this.click(confirmBTN);
	}

}
