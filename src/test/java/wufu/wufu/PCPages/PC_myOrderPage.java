/**
 * @author helen
 * @date 2018年8月23日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:pc商城，我的订单页面
 */
public class PC_myOrderPage extends BasePage{

	public PC_myOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	/********各状态订单数汇总版块***********/
	
	@FindBy(xpath="//div[@class='pre-myorder-state']/ul/li[1]/a/div[2]/b")
	private WebElement PayingCount;//待付款订单数
	public String get_PayingCount() {
		return this.findMyElement(PayingCount).getText();
	}
	
	@FindBy(xpath="//div[@class='pre-myorder-state']/ul/li[2]/a/div[2]/b")
	private WebElement receivingCount;//待收货订单数
	public String get_receivintCount() {
		return this.findMyElement(receivingCount).getText();
	}
	
	@FindBy(xpath="//div[@class='pre-myorder-state']/ul/li[3]/a/div[2]/b")
	private WebElement evaluateCount;//待评价订单数
	public String get_evaluateCount() {
		return this.findMyElement(evaluateCount).getText();
	}
	
	@FindBy(xpath="//div[@class='pre-myorder-state']/ul/li[4]/a/div[2]/b")
	private WebElement finishedCount;//已完成订单数
	public String get_finishedCount() {
		return this.findMyElement(finishedCount).getText();
	}
	
	/************查询版块*********************/
	@FindBy(name="querygoodandsn")
	private WebElement querygoodandsn;//订单编号
	public void set_querygoodandsn(String s) {
		this.sendkeys(querygoodandsn, s);
	}
	
	@FindBy(className="order-search")
	private WebElement orderSearchBTN;//查询按钮
	public void click_orderSearchBTN() {
		this.click(orderSearchBTN);
	}
	
	/**************列表******************/
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='border']")
	private WebElement toPayBTN;//付款链接
	public void click_toPayBTN() {
		this.click(toPayBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='order-cancel']")
	private WebElement orderCancelBTN;//取消订单按钮
	public void click_cancelBTN() {
		this.click(orderCancelBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='again']")
	private WebElement buyAgainBTN;//再次购买
	public void click_buyAgainBTN() {
		this.click(buyAgainBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='border confirm-receipt']")
	private WebElement confirmReceiptBTN;//确认收货按钮
	public void click_confirmReceiptBTN() {
		this.click(confirmReceiptBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='border']")
	private WebElement gotoEvaluate;//去评价按钮
	public void click_gotoEvaluate() {
		this.click(gotoEvaluate);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]/div/a[@class='border apply-for-after-sales-a']")
	private WebElement afterSaleBTN;//[申请售后]按钮
	public void click_afterSaleBTN() {
		this.click(afterSaleBTN);
	}
	
	/************弹出框****************/
	@FindBy(xpath="//*[@id='layui-layer1']/div[2]/a[1]")
	private WebElement confirmBTN;//确认按钮 
	public void click_confirmBTN() {
		this.click(confirmBTN);
	}

}
