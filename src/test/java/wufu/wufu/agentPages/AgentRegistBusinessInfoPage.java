/**
 * @author helen
 * @date 2018年7月10日
 */
package wufu.wufu.agentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:代理商管理系统：注册后跳转到的“代理商信息完善”页面
 */
public class AgentRegistBusinessInfoPage extends BasePage{
	AutoitRun autoitRun = new AutoitRun();
	BaseData baseData = new BaseData();

	public AgentRegistBusinessInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(className="top-username")
	private WebElement topUseranme;//版头的用户名
	public String getTopUsername() {
		return this.findMyElement(topUseranme).getText();
	}
	
	//代理身份
	@FindBy(xpath="//form/div[1]/div/div[1]/label[1]")
	private WebElement mallAgent;//商城代理
	public void click_mallAgent() {
		this.click(mallAgent);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[1]/label[2]")
	private WebElement creditCardAgent;//信用卡代理
	public void click_creditCardAgent() {
		this.click(creditCardAgent);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[1]/label[3]")
	private WebElement insuranceAgent;//保险代理
	public void click_insuranceAgent() {
		this.click(insuranceAgent);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[2]/div[1]/div/div/input")
	private WebElement realName;//真实姓名
	public void setRealName(String s) {
		this.sendkeys(realName, s);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[2]/div[2]/div/div/input")
	private WebElement IdCard;//身份证号
	public void setIdCard(String s) {
		this.sendkeys(IdCard, s);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[2]/div[3]/div/div/input")
	private WebElement email;//email
	public void setEmail(String s) {
		this.sendkeys(email, s);
	}
	
	@FindBy(xpath="//form/div[1]/div/div[3]/div/div/div[1]/div[1]/input")
	private WebElement provinceInput;//省份input假下拉框
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[1]")
	private WebElement province_beijingshi;//北京
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[2]")
	private WebElement province_tianjin;//天津
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[3]")
	private WebElement province_hebei;//河北
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[4]")
	private WebElement province_shanxi;//山西省
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[5]")
	private WebElement province_neimenggu;//内蒙古自治区
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown']/div/div[1]/ul/li[6]")
	private WebElement province_liaoning;//辽宁省
	public void selectProvince(int provinceIndex) {
		this.click(provinceInput);
		if (provinceIndex==1) {//北京
			this.click(province_beijingshi);
		}
		else if (provinceIndex==2) {//天津
			this.click(province_tianjin);
		}
		else if (provinceIndex==3) {//河北
			this.click(province_hebei);
		}
		else if (provinceIndex==4) {//山西省
			this.click(province_shanxi);
		}
		else if (provinceIndex==5) {//内蒙古
			this.click(province_neimenggu);
		}
		else if (provinceIndex==6) {//辽宁省
			this.click(province_liaoning);
		}
		else {
			System.out.println("选择省份失败");
		}
	}
	
	@FindBy(xpath="//form/div[1]/div/div[4]/div[1]/div/div/div/div/div/img")
	private WebElement IdCardFrontBTN;//身份证正面,上传按钮
	public void setIdCardFront(){//上传身份证正面，通过autoit上传
		this.click(IdCardFrontBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardFront.jpg"));
	}
	
	@FindBy(xpath="//form/div[1]/div/div[4]/div[2]/div/div/div/div/div/img")
	private WebElement IdCardBackBTN;//身份证反面，上传按钮
	public void setIdCardBack(){
		this.click(IdCardBackBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardReverse.jpg"));
	}
	
	@FindBy(xpath="//form/div[2]/div/div/div[1]/div/div/input")
	private WebElement bankUseranme;//开户名
	public void setBankUsername(String s) {
		this.sendkeys(bankUseranme, s);
	}
	
	@FindBy(xpath="//form/div[2]/div/div/div[2]/div/div[1]/input")
	private WebElement bankNUM;//银行卡号
	public void setBankNUM(String s) {
		this.sendkeys(bankNUM, s);
	}
	
	@FindBy(xpath="//form/div[2]/div/div/div[3]/div/div/div[1]/input")
	private WebElement bankNameInput;//开户银行input假下拉框
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[1]")
	private WebElement bankNameBOC;//中国银行
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[2]")
	private WebElement bankNameICBC;//中国工商银行
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[3]")
	private WebElement bankNameCGB;//广发银行
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[4]")
	private WebElement bankNameCCB;//中国建设银行
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[5]")
	private WebElement bankNameABC;//中国农业银行
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bank-type-select']/div/div[1]/ul/li[6]")
	private WebElement bankNameBCM;//交通银行
	public void selectBankName(int bankIndex) {
		this.click(bankNameInput);
		if(bankIndex==1) {
			this.click(bankNameBOC);
		}
		else if (bankIndex==2) {
			this.click(bankNameICBC);
		}
		else if (bankIndex==3) {
			this.click(bankNameCGB);
		}
		else if (bankIndex==4) {
			this.click(bankNameCCB);
		}
		else if (bankIndex==5) {
			this.click(bankNameABC);
		}
		else if (bankIndex==6) {
			this.click(bankNameBCM);
		}
		else {
			System.out.println("选择银行失败");
		}
	}
	
	@FindBy(xpath="//form/div[2]/div/div/div[4]/div/div/input")
	private WebElement branchBankName;//支行名称
	public void setBranchBankName(String s) {
		this.sendkeys(branchBankName, s);
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/form/div[3]/button")
	private WebElement submitBTN;//提交审核按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}
}
