/**
 * @author helen
 * @date 2018年9月10日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:商家中心，忘记密码页面
 */
public class SellerForgetPasswordPage extends BasePage{

	public SellerForgetPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//form/div[1]/div/div/input")
	private WebElement account;//手机号
	public void set_account(String s) {
		this.sendkeys(account, s);
	}
	
	@FindBy(xpath="//form/div[3]/div/div[2]/span/button")
	private WebElement getVerifCode;//获取验证码按钮
	public void click_getVerifCode() {
		this.click(getVerifCode);
	}
	
	@FindBy(xpath="//form/div[3]/div/div[1]/div/input")
	private WebElement verifCode;//验证码
	public void set_verifCode(String s) {
		this.sendkeys(verifCode, s);
	}
	
	@FindBy(xpath="//form/div[2]/div/div/input")
	private WebElement password;//密码
	public void set_password(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(xpath="//form/div[4]/div/button")
	private WebElement submitBTN;//提交按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}

}
