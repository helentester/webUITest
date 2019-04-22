/**
 * @author helen
 * @date 2018年7月13日
 */
package wufu.wufu.agentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:代理间管理系统－申请成为超市
 */
public class AgentSuperAgentApplicationPage extends BasePage{
	BaseData baseData = new BaseData();
	AutoitRun autoitRun = new AutoitRun();

	public AgentSuperAgentApplicationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/***********资产中心－邀请奖励-申请成为超市************/
	@FindBy(xpath="/html/body/div[1]/div/div[3]/div[2]/div/div/form/div[1]/div[2]/div[1]/div/div/div/div/div[2]/button")
	private WebElement businessLicense;//营业执照
	public void setBusinessLicense(){
		this.click(businessLicense);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\businessLicense.jpg"));
	}
	
	@FindBy(xpath="/html/body/div/div/div[3]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/div/div[1]/div/div[2]/button")
	private WebElement idCardFront;//法人身份证正面
	public void setIdCardFront(){
		this.click(idCardFront);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardFront.jpg"));
	}
	
	@FindBy(xpath="/html/body/div/div/div[3]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div/div[1]/div/div[2]/button")
	private WebElement idCardReverse;//法人身份证反面
	public void setIdCardReverse() {
		this.click(idCardReverse);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardReverse.jpg"));
	}
	
	@FindBy(xpath="/html/body/div/div/div[3]/div[2]/div/div/form/div[2]/button")
	private WebElement saveBTN;//保存按钮
	public void click_saveBTN() {
		this.click(saveBTN);
	}

}
