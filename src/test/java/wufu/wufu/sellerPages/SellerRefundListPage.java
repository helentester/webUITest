/**
 * @author helen
 * @date 2018年9月19日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:商家中心－退货退款单列表页面
 */
public class SellerRefundListPage extends BasePage{

	public SellerRefundListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*****查询版块************/
	@FindBy(xpath="//form/div[1]/div[1]/div/div/input")
	private WebElement orderSN;//订单号
	public void set_orderSN(String s) {
		this.sendkeys(orderSN, s);
	}
	
	@FindBy(xpath="//form/div[3]/div[1]/div/div/div/input")
	private WebElement startTime;//申请时间－开始
	public void clear_startTime(WebDriver driver) {
		this.JS(driver, startTime, "arguments[0].removeAttribute('readonly');");
		this.findMyElement(startTime).clear();
	}
	
	@FindBy(xpath="//form/div[3]/div[3]/div/div/div/input")
	private WebElement endTime;//申请时间－结束
	public void clear_endTime(WebDriver driver) {
		this.JS(driver, endTime, "arguments[0].removeAttribute('readonly');");
		this.findMyElement(endTime).clear();
	}
	
	@FindBy(xpath="//form/div[3]/button")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	/*********列表************/
	@FindBy(xpath="//table/tbody/tr[1]/td[9]/div/button")
	private WebElement detailLink;//[查看]
	public void click_detailLink() {
		this.click(detailLink);
	}
	
	/********审核页面*********/
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div[2]/div[1]/button")
	private WebElement canAuditBTN;//[待审核]按钮
	public void click_canAuditBTN() {
		this.click(canAuditBTN);
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div[2]/div[4]/div/div[3]/div/button[2]")
	private WebElement confirmBTN;//[确认]
	public void click_confirmBTN() {
		this.click(confirmBTN);
	}

}
