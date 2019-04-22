/**
 * @author helen
 * @date 2018年6月22日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:商家登录页面
 */
public class SellerLoginPage extends BasePage{

	public SellerLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//form/div[1]/div/div/input")
	private WebElement mobile;//手机号
	public void setMobile(String s) {
		this.sendkeys(mobile, s);
	}
	
	@FindBy(xpath="//form/div[2]/div/div/input")
	private WebElement password;//密码
	public void setPassword(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(xpath="//form/div[3]/div/div[1]/div/input")
	private WebElement verificationCode;//验证码
	public void setVerificationCode(String s) {
		this.sendkeys(verificationCode, s);
	}
	
	@FindBy(xpath="//form/div[4]/div/button")
	private WebElement sellerLoginBTN;//登录按钮
	public void clickSellerLoginBTN() {
		this.click(sellerLoginBTN);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/div/div/button")
	private WebElement registLink;//注册链接
	public void click_registLink() {
		this.click(registLink);
	}
	
	@FindBy(className="forgetPassword")
	private WebElement forgetPasswordLink;//忘记密码
	public void click_forgetPassword() {
		this.click(forgetPasswordLink);
	}

}
