/**
 * @author helen
 * @date 2018年9月19日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:PC商城－申请售后页面
 */
public class PC_mySaledServicePage extends BasePage{

	public PC_mySaledServicePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*******仅退款的页面***********/
	@FindBy(xpath="//form/ul/li[1]/dl/dd/div[2]/textarea")
	private WebElement description2;//退款类型下的[问题描述]
	public void set_description2(String s) {
		this.sendkeys(description2, s);
	}
	
	@FindBy(xpath="//input[@class='input-submit-b']")
	private WebElement submitBTNb;//[提交]按钮
	public void click_submitBTNb() {
		this.click(submitBTNb);
	}
	
	
	/*******退货退款的页面**********/
	
	@FindBy(name="number")
	private WebElement number;//提交数量
	public void set_number(String s) {
		this.sendkeys(number, s);
	}
	
	@FindBy(xpath="//label[@class='increase']")
	private WebElement plusNB;//数量+
	public void click_plusNB() {
		this.click(plusNB);
	}
	
	@FindBy(xpath="//textarea[@class='description']")
	private WebElement description;//退货退款类型下的：问题描述
	public void set_description(String s) {
		this.sendkeys(description, s);
	}
	
	@FindBy(name="deliveryName")
	private WebElement deliveryName;//快递名称
	public void set_deliveryName(String s) {
		this.sendkeys(deliveryName, s);
	}
	
	@FindBy(name="deliveryNo")
	private WebElement deliveryNo;//快递单号
	public void set_deliveryNo(String s) {
		this.sendkeys(deliveryNo, s);
	}
	
	@FindBy(name="refundName")
	private WebElement refundName;//退货人
	public void set_refundName(String s) {
		this.sendkeys(refundName, s);
	}
	
	@FindBy(name="mobile")
	private WebElement mobile;//退货人手机号
	public void set_mobile(String s) {
		this.sendkeys(mobile, s);
	}
	
	@FindBy(xpath="//input[@class='input-submit-a']")
	private WebElement submitBTN;//[提交]按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}

}
