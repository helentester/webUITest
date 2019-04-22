/**
 * @author helen
 * @date 2018年7月11日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:商家注册页面
 */
public class SellerRegistPage extends BasePage{

	public SellerRegistPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//form/div[1]/div/div[2]/div/input")
	private WebElement phone;//手机号
	public void setPhone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(xpath="//form/div[3]/div/div[2]/span/button")
	private WebElement sendCodeBTN;//获取验证码按钮
	public void click_sendCodeBTN() {
		this.click(sendCodeBTN);
	}
	
	@FindBy(xpath="//form/div[3]/div/div[1]/div/input")
	private WebElement sendCode;//短信验证码
	public void setSendCode(String s) {
		this.sendkeys(sendCode, s);
	}
	
	@FindBy(xpath="//form/div[2]/div/div/input")
	private WebElement password;//设置密码
	public void setPassword(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(xpath="//form/div[4]/div/div/input")
	private WebElement inviter;//邀请人
	public void set_inviter(String s) {
		this.sendkeys(inviter, s);
	}
	
	@FindBy(xpath="//form/div[5]/div/button")
	private WebElement submitBTN;//注册按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}

}
