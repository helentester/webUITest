/**
 * @author helen
 * @date 2018年7月31日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:PC商城购物车页面 
 */
public class PC_carInfoPage extends BasePage{

	public PC_carInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="/html/body/div[3]/div[3]/table/tbody/tr[2]/td[5]/span/input")
	private WebElement firstNumber;//购物车第一个商品的数量
	public void set_firstNumber(String s) {
		this.sendkeys(firstNumber, s);
	}
	
	@FindBy(id="checkAllTop")
	private WebElement checkAllTop;//全选框
	public void click_checkAllTop() {
		this.click(checkAllTop);
	}
	
	@FindBy(id="checkstandButton")
	private WebElement gotoPayBTN;//去结算按钮
	public void click_gotoPayBTN() {
		this.click(gotoPayBTN);
	}
	
	@FindBy(id="cartTotal")
	private WebElement cartTotal;//总价
	public String get_cartTotal() {
		return this.findMyElement(cartTotal).getText();
	}

}
