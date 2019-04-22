/**
 * @author helen
 * @date 2018年7月10日
 */
package wufu.wufu.agentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:代理商管理系统登录页
 */
public class AgentLoginPage extends BasePage{

	public AgentLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[1]/div/div/input")
	private WebElement phone;//手机号
	public void setPhone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[2]/div/div/input")
	private WebElement password;//密码
	public void setPassword(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[3]/div/div[1]/div/input")
	private WebElement verificationCode;//验证码
	public void setVerificationCode(String s) {
		this.sendkeys(verificationCode, s);
	}
	
	@FindBy(className="submit_btn")
	private WebElement loginBTN;//登录按钮
	public void click_loginBTN() {
		this.click(loginBTN);
	}
	
	@FindBy(className="regist")
	private WebElement registLink;//注册链接
	public void click_registLink() {
		this.click(registLink);
	}
	
	@FindBy(className="forgetPassword")
	private WebElement forgetPasswordLink;//忘记密码链接
	public void click_forgetPasswordLink() {
		this.click(forgetPasswordLink);
	}

}
