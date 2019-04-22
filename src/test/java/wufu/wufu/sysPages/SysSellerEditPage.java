/**
 * @author helen
 * @date 2018年9月7日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:运营中心－商家编辑页面
 */
public class SysSellerEditPage extends BasePage{
	BaseData baseData = new BaseData();
	AutoitRun autoitRun = new AutoitRun();

	public SysSellerEditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*************经营类目页面************/
	@FindBy(name="visa_agent")
	private WebElement category_visa_agent;//签证代理
	@FindBy(name="clothing_accessory")
	private WebElement category_clothing_accessory;//服装配饰
	@FindBy(name="daily_life")
	private WebElement category_daily_life;//日常生活
	@FindBy(name="exercise_outdoors")
	private WebElement category_exercise_outdoors;//运动户外
	@FindBy(name="winnow_food")
	private WebElement category_winnow_food;//精选食品
	@FindBy(name="rest_home")
	private WebElement category_rest_home;//养老院
	@FindBy(name="hotel")
	private WebElement category_hotel;//酒店
	@FindBy(name="ticket")
	private WebElement category_ticket;//门票
	@FindBy(name="travel_route")
	private WebElement category_travel_route;//旅游路线
	@FindBy(name="digital_office")
	private WebElement category_digital_office;//数码办公
	@FindBy(name="household_appliances")
	private WebElement category_household_appliances;//家用电器
	@FindBy(name="house")
	private WebElement category_house;//家居
	@FindBy(name="health_product")
	private WebElement category_health_product;//保健品
	@FindBy(name="entertainment")
	private WebElement category_entertainment;//休闲娱乐
	@FindBy(name="cars")
	private WebElement category_cars;//汽车
	@FindBy(name="medical")
	private WebElement category_medical;//医疗
	public void select_category(String categoryName) {//选择经营类目
		if(categoryName.equals("签证代理")) {
			this.click(category_visa_agent);
		}
		else if (categoryName.equals("服装配饰")) {
			this.click(category_clothing_accessory);
		}
		else if (categoryName.equals("日常生活")) {
			this.click(category_daily_life);
		}
		else if (categoryName.equals("运动户外")) {
			this.click(category_exercise_outdoors);
		}
		else if (categoryName.equals("精选食品")) {
			this.click(category_winnow_food);
		}
		else if (categoryName.equals("养老院")) {
			this.click(category_rest_home);
		}
		else if (categoryName.equals("酒店")) {
			this.click(category_hotel);
		}
		else if (categoryName.equals("门票")) {
			this.click(category_ticket);
		}
		else if (categoryName.equals("旅游线路")) {
			this.click(category_travel_route);
		}
		else if (categoryName.equals("数码办公")) {
			this.click(category_digital_office);
		}
		else if (categoryName.equals("家用电器")) {
			this.click(category_household_appliances);
		}
		else if (categoryName.equals("家居")) {
			this.click(category_house);
		}
		else if (categoryName.equals("保健品")) {
			this.click(category_health_product);
		}
		else if (categoryName.equals("休闲娱乐")) {
			this.click(category_entertainment);
		}
		else if (categoryName.equals("汽车")) {
			this.click(category_cars);
		}
		else if (categoryName.equals("医疗")) {
			this.click(category_medical);
		}
		else {
			System.out.println("没有选择经营类目");
		}
	}
	
	@FindBy(id="next")
	private WebElement nextBTN;//下一步按钮
	public void click_nextBTN() {
		this.click(nextBTN);
	}
	
	/***************商家信息版块************/
	@FindBy(id="account")
	private WebElement account;//登录手机号
	public void set_account(String s) {
		this.sendkeys(account, s);
	}
	
	@FindBy(id="password")
	private WebElement password;//登录密码
	public void set_password(String s) {
		this.sendkeys(password, s);
	}
	
	@FindBy(id="legalMobile")
	private WebElement legalMobile;//法人手机号
	public void set_legalMobile(String s) {
		this.sendkeys(legalMobile, s);
	}
	
	@FindBy(id="supplierPerson")
	private WebElement supplierPerson;//联系人姓名
	public void set_supplierPerson(String s) {
		this.sendkeys(supplierPerson, s);
	}
	
	@FindBy(id="personMobile")
	private WebElement personMobile;//联系人手机号码
	public void set_personMobile(String s) {
		this.sendkeys(personMobile, s);
	}
	
	@FindBy(xpath="//form[2]/table[1]/tbody/tr[6]/td[2]/label[2]")
	private WebElement bussiness_status;//企业性质-个体工商户 
	public void click_bussiness_status() {
		this.click(bussiness_status);
	}
	
	@FindBy(id="account-done")
	private WebElement account_done;//[企业性质]-[个体工商户]:[已办理对公账户]
	public void click_account_done() {
		this.click(account_done);
	}
	
	@FindBy(id="supplierName")
	private WebElement supplierName;//商家名称
	public void set_supplierName(String s) {
		this.sendkeys(supplierName, s);
	}
	
	@FindBy(id="supplierOrgName")
	private WebElement supplierOrgName;//公司名称
	public void set_supplierOrgName(String s) {
		this.sendkeys(supplierOrgName, s);
	}
	
	/*************商家资质版块**************/
	@FindBy(xpath="/html/body/div[5]/div/div/div[3]/button")
	private WebElement closeMessageBTN;//提示信息关闭按钮
	public void click_closeMessageBTN() {
		try {
			this.click(closeMessageBTN);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(xpath="//label[@for='idCardCopiesFront']")
	private WebElement idCardCopiesFront;//上传身份证正面
	public void set_idCardCopiesFront() {
		this.click(idCardCopiesFront);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardFront.jpg"));
		this.click_closeMessageBTN();
	}
	
	@FindBy(xpath="//label[@for='idCardCopiesReverse']")
	private WebElement idCardCopiesReverse;//上传身份证反面
	public void set_idCardCopiesReverse(){
		this.click(idCardCopiesReverse);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardReverse.jpg"));
		this.click_closeMessageBTN();
	}
	@FindBy(xpath="//label[@for='supplierOrgLicense']")
	private WebElement supplierOrgLicense;//上传营业执照
	public void set_supplierOrgLicense(){
		this.click(supplierOrgLicense);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\businessLicense.jpg"));
		this.click_closeMessageBTN();
	}
	@FindBy(xpath="//label[@for='bankLicense']")
	private WebElement bankLicense;//银行开户许可证
	public void set_bankLicense(){
		this.click(bankLicense);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\bankPermitCertification.jpg"));
		this.click_closeMessageBTN();
	}
	@FindBy(xpath="//table[2]/tbody/tr[4]/td[2]/label")
	private WebElement FirstLicenseBTN;//第一个资质
	public void setFirsetLicense(String category){
		this.click(FirstLicenseBTN);
		if (category.equals("签证代理")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\exportLicense.jpg"));//签证代理－因私出入境中介机构经营许可证
		}
		else if (category.equals("日常生活")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\industryLicense.jpg"));//日常生活－全国工业产品生产许可证
		}
		else if (category.equals("运动户外")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\productionLicense.jpg"));//运动户外－生产许可证
		}
		else if (category.equals("精选食品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodProductionLicense.jpg"));//精选食品－食品生产许可证
		}
		else if (category.equals("养老院")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\welfareBusinessLicense.jpg"));//养老院-社会福利机构执业证书
		}
		else if (category.equals("酒店")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\SpecialBusinessLicense.jpg"));//特种经营许可证
		}
		else if (category.equals("门票")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\ScenicGrade.jpg"));//旅游景区质量等级证书
		}
		else if (category.equals("旅游线路")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\agencyBusinessLicense.jpg"));//旅行社业务经营许可证
		}
		else if (category.equals("数码办公")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\3CLicense.jpg"));//3C认证证书
		}
		else if (category.equals("家用电器")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\3CLicense.jpg"));//3C认证证书
		}
		else if (category.equals("保健品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodBusinessLicense.jpg"));//食品经营许可证
		}
		else if (category.equals("休闲娱乐")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\securityLicense.jpg"));//消防和安全检查合格证
		}
		else if (category.equals("汽车")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\carTradeLicense.jpg"));//汽车销售准入证
		}
		else if (category.equals("医疗")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\3CLicense.jpg"));//3C认证证书
		}
		else {
			System.out.println("nothing to do in sellerRegistBusinessInfoPage,category is"+category);
		}
		this.click_closeMessageBTN();
	}
	@FindBy(xpath="//table[2]/tbody/tr[5]/td[2]/label")
	private WebElement secondLicenseBTN;//第2个资质
	public void setSecondLicense(String category){
		this.click(secondLicenseBTN);
		if (category.equals("精选食品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodBusinessLicense.jpg"));//精选食品－食品经营许可证
		}
		else if (category.equals("养老院")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\securityLicense.jpg"));//养老院-消防和安全检查合格证
		}
		else if (category.equals("酒店")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\securityLicense.jpg"));//消防和安全检查合格证
		}
		this.click_closeMessageBTN();
	}
	@FindBy(xpath="//table[2]/tbody/tr[6]/td[2]/label")
	private WebElement ThirdLicenseBTN;//第3个资质
	public void setThirdLicense(String category){
		this.click(ThirdLicenseBTN);
		if (category.equals("精选食品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\liquorRegistForm.jpg"));//精选食品－酒类流通许可证/备案登记表
		}
		else if (category.equals("养老院")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\SpecialBusinessLicense.jpg"));//养老院-特种经营许可证
		}
		this.click_closeMessageBTN();
	}
	
	/**********************商标信息***********************/
	@FindBy(xpath="//form[2]/table[3]/tbody/tr[1]/td[2]/label[1]/input")
	private WebElement trademarkType_self;//自有商标
	@FindBy(xpath="//form[2]/table[3]/tbody/tr[1]/td[2]/label[2]/input")
	private WebElement trademarkType_authorize;//商标注册类型－授权商标
	public void selectTrademarkType(int trademardTypeIndex) {
		if (trademardTypeIndex==1) {
			this.click(trademarkType_self);
		}
		else {
			this.click(trademarkType_authorize);
		}
	}
	
	@FindBy(id="brandNumber")
	private WebElement brandNumber;//注册商标号
	public void set_brandNumber(String s) {
		this.sendkeys(brandNumber, s);
	}
	
	@FindBy(xpath="//label[@for='brandCertifying']")
	private WebElement brandCertifying;//商标注册证明
	public void set_brandCertifying(){
		this.click(brandCertifying);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\tradeLicense.jpg"));
		this.click_closeMessageBTN();
	}
	
	@FindBy(xpath="//label[@for='brandAccreditedCertifying']")
	private WebElement brandAccreditedCertifying;//品牌授权证明
	public void set_brandAccreditedCertifying(){
		this.click(brandAccreditedCertifying);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\authorizeLicense.jpg"));
		this.click_closeMessageBTN();
	}
	
	@FindBy(id="accreditEnd")
	private WebElement accreditEnd;//品牌授权截止日期
	public void set_accreditEnd(String s) {
		this.sendkeys(accreditEnd, s);
		this.Keyboard_event(accreditEnd, Keys.ENTER);
	}
	
	/*******************店铺信息*******************/
	@FindBy(xpath="//label[@for='supplierLogo']")
	private WebElement supplierLogo;//店铺logo
	public void set_supplierLogo(){
		this.click(supplierLogo);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\logo.jpg"));
		this.click_closeMessageBTN();
	}
	
	@FindBy(id="supplierDesc")
	private WebElement supplierDesc;//店铺描述
	public void set_supplierDesc(String s) {
		this.sendkeys(supplierDesc, s);
	}
	
	/************************银行信息**********************/
	@FindBy(id="bankAccountName")
	private WebElement bankAccountName;//持卡人
	public void set_bankAccountName(String s) {
		this.sendkeys(bankAccountName, s);
	}
	
	@FindBy(id="bankAccount")
	private WebElement bankAccount;//卡号
	public void set_bankAccount(String s) {
		this.sendkeys(bankAccount, s);
	}
	
	@FindBy(id="bankName1")
	private WebElement bankName1;//开户行
	public void select_bankName1(String s) {
		this.selectValue(bankName1, s, "ByIndex");
	}
	
	@FindBy(id="bankBranch")
	private WebElement bankBranch;//支行名称
	public void set_bankBranch(String s) {
		this.sendkeys(bankBranch, s);
	}
	
	/*********************所在地区***********************/
	@FindBy(id="province1")
	private WebElement province1;//省份
	public void select_province1(String s) {
		this.selectValue(province1, s, "ByIndex");
	}
	
	/********************邀请信息***************/
	@FindBy(id="recommendCode")
	private WebElement recommendCode;//代理商邀请人
	public void set_recommendCode(String s) {
		this.sendkeys(recommendCode, s);
	}
	
	@FindBy(id="zsman")
	private WebElement zsman;//招商人员
	public void select_zsman(String s) {
		this.selectValue(zsman, s, "ByIndex");
	}
	
	@FindBy(id="contractStartDate")
	private WebElement contractStartDate;//合同有效期－开始
	public void set_contractStartDate(String s) {
		this.sendkeys(contractStartDate, s);
		this.Keyboard_event(contractStartDate, Keys.ENTER);
	}
	
	@FindBy(id="contractEndDate")
	private WebElement contractEndDate;//合同有效期－结束
	public void set_contractEndDate(String s) {
		this.sendkeys(contractEndDate, s);
		this.Keyboard_event(contractEndDate, Keys.ENTER);
	}
	
	/*********************************/
	@FindBy(id="save")
	private WebElement saveBTN;//保存按钮
	public void click_saveBTN() {
		this.click(saveBTN);
	}

}
