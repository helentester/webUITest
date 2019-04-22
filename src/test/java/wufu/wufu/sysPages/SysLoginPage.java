/**
 * @author helen
 * @date 2018年6月21日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:管理系统模板登录页面
 */
public class SysLoginPage extends BasePage{

	public SysLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//登录账号
	@FindBy(id="acount")
	private WebElement acount;
	public void setAcount(String s) {
		this.sendkeys(acount, s);
	}
	
	//登录密码
	@FindBy(id="password")
	private WebElement password;
	public void setPassWord(String s) {
		this.sendkeys(password, s);
	}
	
	//登录按钮
	@FindBy(id="submit")
	private WebElement LoginSubmit;
	public void clickLoginSubmit() {
		this.click(LoginSubmit);
	}
	
	//获取验证码按钮
	@FindBy(id="getCode")
	private WebElement getCode;
	public void clickGetCode() {
		this.click(getCode);
	}
	public boolean CodeDisplay() {
		return this.findMyElement(getCode).isDisplayed();
	}
	
	//短信验证码
	@FindBy(id="mobileCode")
	private WebElement mobileCode;
	public void set_mobileCode(String s) {
		this.sendkeys(mobileCode, s);
	}

}
