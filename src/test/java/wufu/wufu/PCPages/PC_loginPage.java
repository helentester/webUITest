/**
 * @author helen
 * @date 2018年7月24日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:PC商城登录页面
 */
public class PC_loginPage extends BasePage{

	public PC_loginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="account")
	private WebElement account;//登录账号
	public void set_account(String s) {
		this.sendkeys(account, s);
	}
	
	@FindBy(id="password")
	private WebElement password;//登录密码
	public void set_password(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(className="loginBtn")
	private WebElement loginBtn;//登录按钮
	public void click_loginBtn() {
		this.click(loginBtn);
	}

}
