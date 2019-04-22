/**
 * @author helen
 * @date 2018年7月11日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:商家注册“商家信息完善”页面
 */
public class SellerRegistBusinessInfoPage extends BasePage{
	BaseData baseData = new BaseData();
	AutoitRun autoitRun = new AutoitRun();

	public SellerRegistBusinessInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*************基本信息******************/
	
	@FindBy(xpath="//form/div[1]/div[2]/div[1]/div/div/input")
	private WebElement phone;//手机号
	public void setPhone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[2]/div/div/input")
	private WebElement contactName;//联系人姓名
	public void setContactName(String s) {
		this.sendkeys(contactName, s);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[3]/div/div/input")
	private WebElement contactPhone;//联系人电话
	public void setContactPhone(String s){
		this.sendkeys(contactPhone, s);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[4]/div/div/label[2]")
	private WebElement bussiness_status;//企业性质－个体工商户
	public void click_bussiness_status() {
		this.click(bussiness_status);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[4]/div/label")
	private WebElement account_done;//[企业性质]-[个体工商户]:[已办理对公账户]
	public void click_account_done() {
		this.click(account_done);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[5]/div/div/input")
	private WebElement sellerName;//商家名称
	public void setSellerName(String s) {
		this.sendkeys(sellerName, s);
	}
	
	@FindBy(xpath="//form/div[1]/div[2]/div[6]/div/div/input")
	private WebElement company;//公司名称
	public void setCompany(String s) {
		this.sendkeys(company, s);
	}

	@FindBy(xpath="//form/div[1]/div[2]/div[7]/div/div[1]/div[1]/input")
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
	
	/**********************资质信息***********************/
	
	@FindBy(xpath="//form/div[2]/div[2]/div[1]/div/div[1]/div/div/div/div/div/img")
	private WebElement IdCardFrontBTN;//身份证正面
	public void setIdCardFront(){
		this.click(IdCardFrontBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardFront.jpg"));
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[1]/div/div[2]/div/div/div/div/div/img")
	private WebElement IdCardBackBTN;//身份证反面
	public void setIdCardBack(){
		this.click(IdCardBackBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\idCardReverse.jpg"));
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[2]/div/div/div/div/div/div")
	private WebElement BusinessLicenseBTN;//营业执照
	public void setBusinessLicense(){
		this.click(BusinessLicenseBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\businessLicense.jpg"));
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[3]/div/div/div/div/div/div")
	private WebElement bankPermitCertificationBTN;//银行开户许可证
	public void setBankPermitCertification(){
		this.click(bankPermitCertificationBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\bankPermitCertification.jpg"));
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[4]/div/div/div/div/div/div")
	private WebElement FirstLicenseBTN;//资质第一个证书
	public void setFirsetLicense(String category){
		this.click(FirstLicenseBTN);
		/** 	20190408运营中心v3.3版优化，经营类目＝商品一级分类，只有以下三个分类有资质上传
	 * 	食品酒水：食品生产许可证、食品经营许可证
		美妆个护：全国工业产品生产许可证
		家电汽车：3C认证书*/
		if (category.equals("家电汽车")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\3CLicense.jpg"));//3C认证证书
		}
		else if (category.equals("美妆个护")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\industrialLicense.jpg"));// 全国工业产品生产许可证industrialLicense.jpg
		}
		else if (category.equals("食品酒水")){
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodProductionLicense.jpg"));//精选食品－食品生产许可证
		}
		/*
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
		}*/
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[5]/div/div/div/div/div/div")
	private WebElement secondLicenseBTN;//资质第二个证书
	public void setSecondLicense(String category){
		this.click(secondLicenseBTN);
		if (category.equals("食品酒水")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodBusinessLicense.jpg"));//食品经营许可证
		}
		/*if (category.equals("精选食品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\FoodBusinessLicense.jpg"));//精选食品－食品经营许可证
		}
		else if (category.equals("养老院")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\securityLicense.jpg"));//养老院-消防和安全检查合格证
		}
		else if (category.equals("酒店")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\securityLicense.jpg"));//消防和安全检查合格证
		}*/
	}
	
	@FindBy(xpath="//form/div[2]/div[2]/div[6]/div/div/div/div/div/div")
	private WebElement ThirdLicenseBTN;//资质第三个证书
	public void setThirdLicense(String category){
		this.click(ThirdLicenseBTN);
		if (category.equals("精选食品")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\liquorRegistForm.jpg"));//精选食品－酒类流通许可证/备案登记表
		}
		else if (category.equals("养老院")) {
			autoitRun.uploadFile(baseData.getFilePath("testFiles\\SpecialBusinessLicense.jpg"));//养老院-特种经营许可证
		}
	}

	/***************商标信息**********************/
	
	@FindBy(xpath="//form/div[3]/div[2]/div[1]/div/div/div/label[1]")
	private WebElement trademarkType_self;//商标注册类型－自有商标
	@FindBy(xpath="//form/div[3]/div[2]/div[1]/div/div/div/label[2]")
	private WebElement trademarkType_authorize;//商标注册类型－授权商标
	public void selectTrademarkType(int trademardTypeIndex) {
		if (trademardTypeIndex==1) {
			this.click(trademarkType_self);
		}
		else {
			this.click(trademarkType_authorize);
		}
	}
	
	@FindBy(xpath="//form/div[3]/div[2]/div[2]/div/div/div/input")
	private WebElement trademarkNB;//商标注册号
	public void setTrademarkNB(String s) {
		this.sendkeys(trademarkNB, s);
	}
	
	@FindBy(xpath="//form/div[3]/div[2]/div[3]/div/div/div/div/div/div")
	private WebElement tradeLicenseBTN;//商标注册证明
	public void setTradeLicense(){
		this.click(tradeLicenseBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\tradeLicense.jpg"));
	}
	
	@FindBy(xpath="//form/div[3]/div[2]/div[4]/div[1]/div/div/div/div/div/div")
	private WebElement authorizeLicense;//品牌授权证明(商标类型选择“授权商标”才会出现)
	public void setAuthorizeLicense(){
		this.click(authorizeLicense);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\authorizeLicense.jpg"));
	}
	
	@FindBy(xpath="//form/div[3]/div[2]/div[4]/div[2]/div/div/div/div/input")
	private WebElement authorEndDate;//品牌授权截止日期(商标类型选择“授权商标”才会出现)
	public void setAuthorEndDate(String s) {
		this.sendkeys(authorEndDate, s);
		this.Keyboard_event(authorEndDate, Keys.ENTER);
	}
	
	/******************店铺信息*******************/
	
	@FindBy(xpath="//form/div[4]/div[2]/div[1]/div/div/div/div/div/div")
	private WebElement logoBTN;//店铺logo
	public void setLogo(){
		this.click(logoBTN);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\logo.jpg"));
	}
	
	@FindBy(xpath="//form/div[4]/div[2]/div[2]/div/div/div/textarea")
	private WebElement shopDescription;//店铺描述
	public void setShopDescription(String s) {
		this.sendkeys(shopDescription, s);
	}
	
	/****************银行卡信息***************/
	
	@FindBy(xpath="//form/div[5]/div[2]/div[1]/div/div/div/input")
	private WebElement bankUser;//持卡人
	public void setBankUser(String s) {
		this.sendkeys(bankUser, s);
	}
	
	@FindBy(xpath="//form/div[5]/div[2]/div[3]/div/div/div[1]/input")
	private WebElement bankNB;//银行卡号
	public void setBankNB(String s) {
		this.sendkeys(bankNB, s);
	}

	@FindBy(xpath="//form/div[5]/div[2]/div[2]/div/div/div[1]/div[1]/input")
	private WebElement bankNameInput;//开户银行input假下拉框
	@FindBy(name="1")
	private WebElement bankNameBOC;//中国银行
	@FindBy(name="2")
	private WebElement bankNameICBC;//中国工商银行
	@FindBy(name="3")
	private WebElement bankNameCGB;//广发银行
	@FindBy(name="4")
	private WebElement bankNameCCB;//中国建设银行
	@FindBy(name="5")
	private WebElement bankNameABC;//中国农业银行
	@FindBy(name="6")
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
	
	@FindBy(xpath="//form/div[5]/div[2]/div[2]/div/div/div[2]/input")
	private WebElement bankBranch;//支行名称
	public void setBankBrance(String s) {
		this.sendkeys(bankBranch, s);
	}
	
	/******************邀请信息********************/
	@FindBy(xpath="//form/div[6]/div[2]/div[1]/div/div/div/input")
	private WebElement invitCode;//代理商邀请码
	public void setInvitCode(String s) {
		this.sendkeys(invitCode, s);
	}
	
	@FindBy(xpath="//form/div[6]/div[2]/div[2]/div/div/div/div[1]/input")
	private WebElement merchantsInput;//招商人员假下拉框
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bottom-business-select']/div/div[1]/ul/li[1]")
	private WebElement mechants_liguihua;//李桂华
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bottom-business-select']/div/div[1]/ul/li[2]")
	private WebElement mechants_lvshili;//吕师丽
	@FindBy(xpath="/html/body/div[@class='el-select-dropdown bottom-business-select']/div/div[1]/ul/li[3]")
	private WebElement mechants_chenxiyin;//陈稀莹
	public void selectMerchants(int merchants) {
		this.click(merchantsInput);
		if (merchants==1) {
			this.click(mechants_liguihua);
		}
		else if (merchants==2) {
			this.click(mechants_lvshili);
		}
		else if (merchants==3) {
			this.click(mechants_chenxiyin);
		}
		else {
			System.out.println("选择招商人员失败");
		}
	}
	
	/*********************/
	@FindBy(xpath="//form/div[7]/label/span[1]")
	private WebElement agreeCheck;//同意协议多选框
	public void click_agreeCheck() {
		this.click(agreeCheck);
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/form/div[8]/button[1]")
	private WebElement submitBTN;//提交审核按钮
	public void click_submitBTN() {
		this.click(submitBTN);
	}
}
