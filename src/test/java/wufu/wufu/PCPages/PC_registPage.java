/**
 * @author helen
 * @date 2018年8月8日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:PC商城注册页面
 */
public class PC_registPage extends BasePage{

	public PC_registPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(linkText="邮箱注册")
	private WebElement emailRegistLink;//邮箱注册链接
	public void click_emailRegistLink() {
		this.click(emailRegistLink);
	}
	
	@FindBy(id="email")
	private WebElement email;//注册邮箱号
	public void set_email(String s) {
		this.sendkeys(email, s);
	}
	
	@FindBy(id="mobile")
	private WebElement mobile;//手机号
	public void set_mobile(String s) {
		this.sendkeys(mobile, s);
	}
	
	@FindBy(id="getCode")
	private WebElement getCodeBTN;//获取验证码按钮
	public void click_getCodeBTN() {
		this.click(getCodeBTN);
	}
	
	@FindBy(id="code")
	private WebElement code;//验证码
	public void set_code(WebDriver driver,String s) {
		this.JS(driver, code, "arguments[0].removeAttribute('readonly');");
		this.sendkeys(code, s);
	}
	
	@FindBy(id="password")
	private WebElement password;//密码
	public void set_password(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(id="agree")
	private WebElement agree;//同意条款
	public void click_agree() {
		this.click(agree);
	}
	
	@FindBy(xpath="//*[@id=\"registerForm\"]/dl/dd[7]/button")
	private WebElement registBTN;//立即注册按钮
	public void click_registBTN() {
		this.click(registBTN);
	}
}
