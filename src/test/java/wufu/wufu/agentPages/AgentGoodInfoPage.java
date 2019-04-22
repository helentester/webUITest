/**
 * @author helen
 * @date 2018年9月13日
 */
package wufu.wufu.agentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:代理中心－健康家商品明细页面
 */
public class AgentGoodInfoPage extends BasePage{

	public AgentGoodInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[1]")
	private WebElement sku1;
	public void click_sku1() {
		this.click(sku1);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[2]")
	private WebElement sku2;
	public void click_sku2() {
		this.click(sku2);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[3]")
	private WebElement sku3;
	public void click_sku3() {
		this.click(sku1);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[4]")
	private WebElement sku4;
	public void click_sku4() {
		this.click(sku4);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[5]")
	private WebElement sku5;
	public void click_sku5() {
		this.click(sku5);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[6]")
	private WebElement sku6;
	public void click_sku6() {
		this.click(sku6);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[7]")
	private WebElement sku7;
	public void click_sku7() {
		this.click(sku7);
	}
	
	@FindBy(xpath="//div[@class='specification']/div/span[8]")
	private WebElement sku8;
	public void click_sku8() {
		this.click(sku8);
	}
	
	@FindBy(className="buy-now")
	private WebElement buyNowBTN;//立即购买
	public void click_buyNowBTN() {
		this.click(buyNowBTN);
	}

	@FindBy(className="btn-pay")
	private WebElement payBTN;//确认付款按钮
	public void click_payBTN() {
		this.click(payBTN);
	}
	
	@FindBy(xpath="//div[@class='pay-order']/div/p[1]/em")
	private WebElement orderAmount;
	public String get_orderAmount() {
		return this.findMyElement(orderAmount).getText();
	}

	@FindBy(id="pd-codebox")
	private WebElement codebox;//微信支付二维码
	public String codebox_display() {
		String display = "N";
		if (this.findMyElement(codebox).isDisplayed()) {
			display = "Y";
		} 
		return display;
	}
}
