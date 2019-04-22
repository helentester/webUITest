/**
 * @author helen
 * @date 2018年9月19日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:运营中心－退货退款管理列表页面
 */
public class SysOrderRefundListPage extends BasePage{

	public SysOrderRefundListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*****查询版块********/
	@FindBy(id="orderSn")
	private WebElement orderSn;//订单号
	public void set_orderSn(String s) {
		this.sendkeys(orderSn, s);
	}
	
	@FindBy(id="startTime")
	private WebElement startTime;//申请时间－开始
	public void clear_startTime() {
		this.findMyElement(startTime).clear();
	}
	
	@FindBy(id="endTime")
	private WebElement endTime;
	public void clear_endTime() {
		this.findMyElement(endTime).clear();
	}
	
	@FindBy(id="search")
	private WebElement search;//搜索
	public void click_search() {
		this.click(search);
	}
	
	/************列表**************/

	@FindBy(xpath="//table/tbody/tr/td[11]/a")
	private WebElement view;//[查看]
	public void click_view() {
		this.click(view);
	}
	
	/**********详情页面************/
	@FindBy(id="processRefund")
	private WebElement processRefund;//[处理审核]
	public void click_processRefund() {
		this.click(processRefund);
	}
	
	/*******审核弹出框*******/
	@FindBy(xpath="//form/div[1]/div/div/label[1]/input")
	private WebElement auditRefuse;//拒绝单选按钮
	public void  click_auditRefuse() {
		this.click(auditRefuse);
	}
	
	@FindBy(id= "admin_desc")
	private WebElement admin_desc;//操作备注
	public void set_desc(String s) {
		this.sendkeys(admin_desc, s);
	}
	
	@FindBy(id="sendDeliveryBtn")
	private WebElement confirm;//[确定]
	public void click_confirm() {
		this.click(confirm);
	}

}
