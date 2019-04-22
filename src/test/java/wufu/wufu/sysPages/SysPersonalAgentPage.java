/**
 * @author helen
 * @date 2018年6月22日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:个人代理商模块
 */
public class SysPersonalAgentPage extends BasePage{
	AutoitRun autoitRun = new AutoitRun();
	BaseData baseData = new BaseData();

	public SysPersonalAgentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/****************个人代理商列表******************/
	
	/*新增代理商按钮*/
	@FindBy(xpath="/html/body/section/div[3]/div/section/header/a")
	private WebElement addAgentBTN;
	public void clickAddAgentBTN() {
		this.click(addAgentBTN);
	}
	
	/***************新增代理商页面********************/
	@FindBy(id="account")
	private WebElement account;//登录手机号
	public void setAccount(String s) {
		this.sendkeys(account, s);
	}
	
	@FindBy(id="password")
	private WebElement password;//密码
	public void setPassword(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(id="realName")
	private WebElement realName;//真实姓名
	public void setRealName(String s) {
		this.sendkeys(realName, s);
	}
	
	@FindBy(name="province")
	private WebElement province;//所在地区：省
	public void selectProvince(int provinceIndex) {
		this.selectValue(province, String.valueOf(provinceIndex), "ByIndex");
	}
	
	@FindBy(id="idCard")
	private WebElement idCard;//身份证
	public void setIdCard(String s) {
		this.sendkeys(idCard, s);
	}
	
	@FindBy(id="email")
	private WebElement email;//邮箱
	public void setEmail(String s) {
		this.sendkeys(email, s);
	}
	
	@FindBy(xpath="/html/body/section/div/div/div/div/form/table[1]/tbody/tr[7]/td[2]/label[1]")
	private WebElement idCardCopiesFront;//身份证正面
	public void setIdCardCopiesFront(){
		this.click(idCardCopiesFront);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardFront.jpg"));
	}

	@FindBy(xpath="/html/body/section/div/div/div/div/form/table[1]/tbody/tr[7]/td[2]/label[2]")
	private WebElement idCardCopiesReverse;//身份证反面
	public void setIdCardCopiesReverse(){
		this.click(idCardCopiesReverse);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardReverse.jpg"));
	}
	
	@FindBy(name="bankAccountName")
	private WebElement bankAccountName;//持卡人
	public void setBankAccountName(String s) {
		this.sendkeys(bankAccountName, s);
	}
	
	@FindBy(id="bankAccount")
	private WebElement bankAccount;//卡号
	public void setBankAccount(String s) {
		this.sendkeys(bankAccount, s);
	}
	
	@FindBy(name="bankType")
	private WebElement bankType;//开户行
	public void selectBankType(int bankIndex) {
		this.selectValue(bankType, String.valueOf(bankIndex), "ByIndex");
	}
	
	@FindBy(name="bankBranch")
	private WebElement bankBranch;//支开名称
	public void setBankBranch(String s) {
		this.sendkeys(bankBranch, s);
	}
	
	@FindBy(id="invite")
	private WebElement inviter;//邀请人
	public void set_inviter(String s) {
		this.sendkeys(inviter, s);
	}
	
	@FindBy(id="save")
	private WebElement saveNewAgentBTN;//保存新增代理商按钮
	public void clickSaveNewAgentBTN() {
		this.click(saveNewAgentBTN);
	}
	
}
