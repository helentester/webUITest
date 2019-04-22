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
 * @Description:代理商管理系统：注册页面
 */
public class AgentRegistPage extends BasePage{

	public AgentRegistPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/***************注册页面**********************/
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[1]/div/div[2]/div/input")
	private WebElement phone;//手机号
	public void setPhone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[3]/div/div[2]/span/button")
	private WebElement sendCodeBTN;//获取验证码按钮
	public void click_sendCodeBTN() {
		try {
			//Thread.sleep(6000);
			this.click(sendCodeBTN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[3]/div/div[1]/div/input")
	private WebElement sendCode;//短信验证码
	public void setSendCode(String s) {
		this.sendkeys(sendCode, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[2]/div/div/input")
	private WebElement password;//设置密码
	public void setPassword(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[4]/div/div/input")
	private WebElement inviterAccount;//邀请人账号
	public void set_inviterAccount(String s) {
		this.sendkeys(inviterAccount, s);
	}
	
	@FindBy(xpath="/html/body/div/div/section/div[2]/div/div/form/div[5]/div/button")
	private WebElement submitBTN;//提交按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}

}
