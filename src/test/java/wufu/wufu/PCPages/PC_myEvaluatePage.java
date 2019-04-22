/**
 * @author helen
 * @date 2018年9月18日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:PC商城－评价中心
 */
public class PC_myEvaluatePage extends BasePage{
	AutoitRun autoitRun = new AutoitRun();
	BaseData baseData = new BaseData();

	public PC_myEvaluatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/*****************************/
	//星级
	@FindBy(xpath="//table/tbody/tr/td[2]/div/form/div[1]/div/div/img[@title='1']")
	private WebElement start1;//星级1
	@FindBy(xpath="//table/tbody/tr/td[2]/div/form/div[1]/div/div/img[@title='2']")
	private WebElement start2;//星级2
	@FindBy(xpath="//table/tbody/tr/td[2]/div/form/div[1]/div/div/img[@title='3']")
	private WebElement start3;//星级3
	@FindBy(xpath="//table/tbody/tr/td[2]/div/form/div[1]/div/div/img[@title='4']")
	private WebElement start4;//星级4
	@FindBy(xpath="//table/tbody/tr/td[2]/div/form/div[1]/div/div/img[@title='5']")
	private WebElement start5;//星级5
	public void click_star(int stars) {
		if (stars==1) {
			this.click(start1);
		}
		else if (stars==2) {
			this.click(start2);
		}
		else if (stars==3) {
			this.click(start3);
		}
		else if (stars==4) {
			this.click(start4);
		}
		else if (stars==5) {
			this.click(start5);
		}
		else {
			System.out.println("选择星级失败");
		}
	}
	
	@FindBy(name="content")
	private WebElement content;//评论内容
	public void set_content(String s) {
		this.sendkeys(content, s);
	}
	
	@FindBy(xpath="//a[@class='uploadBtn']")
	private WebElement uploadBtn;//上传按钮
	public void uploadJPG() {//上传图片
		this.click(uploadBtn);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\sku5.jpg"));
	}
	public void uploadMV() {//上传视频
		this.click(uploadBtn);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\mv1.mp4"));
	}
	
	@FindBy(xpath="//a[@class='a-submit']")
	private WebElement submitBTN;//提交按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}
}
