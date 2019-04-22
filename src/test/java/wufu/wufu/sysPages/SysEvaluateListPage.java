/**
 * @author helen
 * @date 2018年9月18日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:运营中心－评论管理－评论列表页面
 */
public class SysEvaluateListPage extends BasePage{

	public SysEvaluateListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/***********查询版块************/
	
	@FindBy(id="orderSn")
	private WebElement orderSn;//订单号
	public void set_orderSn(String s) {
		this.sendkeys(orderSn, s);
	}
	
	@FindBy(id="search")
	private WebElement searchBTN;//查询按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	/***********列表****************/
	@FindBy(xpath="//table/tbody/tr/td[11]/a")
	private WebElement detailLink;//[查看]
	public void click_detailLink() {
		this.click(detailLink);
	}
	
	/***********评论详情页***************/
	@FindBy(id="auditContext")
	private WebElement auditContext;//[审核]
	public void click_auditContext() {
		this.click(auditContext);
	}
	
	@FindBy(id="confirm")
	private WebElement confirm;//确认审核通过按钮
	public void click_confirm() {
		this.click(confirm);
	}

}
