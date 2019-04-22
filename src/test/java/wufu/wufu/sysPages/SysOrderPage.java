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
 * @Description:运营中心－订单列表页面
 */
public class SysOrderPage extends BasePage{

	public SysOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/********订单列表页面*********/
	@FindBy(xpath="//form/div[6]/div/div/span/input")
	private WebElement orderNo;//订单号
	public void set_orderNO(String s) {
		this.sendkeys(orderNo, s);
	}
	
	@FindBy(xpath="//form/div[15]/div/div/span/button")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}

	@FindBy(xpath="//table/tbody/tr/td[16]/span/button")
	private WebElement detailLink;//第一行查询链接
	public void click_detailLink() {
		this.click(detailLink);
	}
	
	/************订单详情页面************/
	@FindBy(id="sendDelivery")
	private WebElement sendDelivery;//确认发货按钮
	public void click_sendDelivery() {
		this.click(sendDelivery);
	}
	
	/*********发货操作界面********************/
	@FindBy(id="deliveryId")
	private WebElement deliveryId;
	public void select_deliveryId(String s) {
		this.selectValue(deliveryId, s, "ByIndex");
	}
	
	@FindBy(id="deliveryNo")
	private WebElement deliveryNo;//物流单号
	public void set_deliveryNo(String s) {
		this.sendkeys(deliveryNo, s);
	}
	
	@FindBy(id="msg")
	private WebElement msg;//物流备注
	public void set_msg(String s) {
		this.sendkeys(msg, s);
	}
	
	@FindBy(id="sendDeliveryBtn")
	private WebElement sendDeliveryBtn;//发货按钮 
	public void click_sendDeliveryBtn() {
		this.click(sendDeliveryBtn);;
	}

}
