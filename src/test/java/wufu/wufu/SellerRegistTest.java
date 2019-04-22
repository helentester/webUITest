/**
 * @author helen
 * @date 2018年7月11日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import data.CommonData;
import data.MemberData;
import data.SellerData;
import dataProvider.PCProvider;
import wufu.wufu.sellerPages.SellerLoginPage;
import wufu.wufu.sellerPages.SellerRegistBusinessCategoryPage;
import wufu.wufu.sellerPages.SellerRegistBusinessInfoPage;
import wufu.wufu.sellerPages.SellerRegistPage;

/**
 * @Description:商家注册流程
 */
public class SellerRegistTest {
	BaseWinUI winUI = new BaseWinUI();
	BaseData baseData = new BaseData();
	SysSellerTest sellerTest = new SysSellerTest();
	MyConfig myConfig = new MyConfig();
	SellerData sellerData = new SellerData();
	MemberData memberData = new MemberData();
	CommonData data = new CommonData();
	SellerLoginPage loginPage;// 商家登录面
	SellerRegistPage registPage;// 商家注册页面
	SellerRegistBusinessCategoryPage categoryPage;// 商家注册－经营类目页面
	SellerRegistBusinessInfoPage businessInfoPage;// 商家注册－商家信息完善
	//String phone;// 注册手机号
	
	@Test(dataProvider="sellerType",dataProviderClass=PCProvider.class)
	public void test_Regist(String sellerType){
		Reporter.log("商家注册并审核通过－"+sellerType);
		String seller = baseData.getPhoneNumber();
		String sellerName = sellerType + "seller" + baseData.getNum(1, 10000);
		Reporter.log("1）商家提交注册资料："+seller);
		this.sellerRegist(seller,sellerName, sellerType, myConfig.getKeys("agent2"));// 在商家管理系统提交申请
		assertTrue(sellerData.sellerExit(seller),"校验资料提交是否成功");

		Reporter.log("2）运营中心审核商家：审核通过");
		sellerTest.seller_audit(sellerName);//在运营管理后台审核通过
		assertEquals(sellerData.get_auditorStatusByLegalMobile(seller),"1","商家注册并审核，被审核商家的法人号码为："+seller);
		assertEquals(Double.valueOf(memberData.get_ableScore(seller)), Double.valueOf("100"));//注册的商家也是会员，送100福豆
	}
	
	/*商家注册并审核通过
	 * @seller 商家注册手机号
	 * @category	经营项目
	 * @agentAccount	邀请人
	 * return	法人手机号
	 * */
	public String sellerRegistAndAuditPass(String seller,String sellerName,String category,String agentAccount) {
		this.sellerRegist(seller,sellerName, category, agentAccount);// 在商家管理系统提交申请
		assertTrue(sellerData.sellerExit(seller),"商家注册,校验资料提交是否成功");
		sellerTest.seller_audit(sellerName);//在运营管理后台审核通过
		return seller;
	}

	/*
	 * 商家注册
	 * @seller 商家注册手机号
	 * @category	经营项目
	 * @agentAccount	邀请人
	 * return	法人手机号
	 */
	public void sellerRegist(String seller,String sellerName,String category,String agentAccount){
		WebDriver driver = winUI.getDrive();
		driver.get(myConfig.getKeys("sj_domainName"));

		try {
			/* 登录页面 */
			loginPage = PageFactory.initElements(driver, SellerLoginPage.class);
			loginPage.click_registLink();// 点击注册链接

			/* 注册页面 */
			registPage = PageFactory.initElements(driver, SellerRegistPage.class);
			registPage.setPhone(seller);// 输入手机号
			registPage.click_sendCodeBTN();// 点击“获取验证码”按钮
			String verifCode = data.getVerificationCode_ByPhone(seller);
			registPage.setSendCode(verifCode);// 输入验证码
			registPage.setPassword("123456li");// 设置密码
			registPage.click_submitBTN();// 提交
			
			// 经营类目选择页面 
			Thread.sleep(3000);
			categoryPage = PageFactory.initElements(driver, SellerRegistBusinessCategoryPage.class);
			categoryPage.select_category(category);
			categoryPage.click_nextBTN();
			// 基本信息
			Thread.sleep(3000);
			businessInfoPage = PageFactory.initElements(driver, SellerRegistBusinessInfoPage.class);
			businessInfoPage.setPhone(seller);// 法人手机号与账号一至
			businessInfoPage.setContactName("contactPerson" + baseData.getNum(1, 10000));// 联系人姓名
			businessInfoPage.setContactPhone(baseData.getPhoneNumber());// 联系人电话
			int r = baseData.getNum(0, 2);//随机设置[企业性质]
			int b = 0;//[企业性质]-[个体工商户]:[已办理对公账户]，0没有该项；1构选；2不构选
			if (r==1) {//0为默认[法人企业]，1为[个体工商户]
				businessInfoPage.click_bussiness_status();
				b = baseData.getNum(1, 3);
				if (b==2) {
					businessInfoPage.click_account_done();
				}
			}
			businessInfoPage.setSellerName(sellerName);// 商家名称
			businessInfoPage.setCompany("company" + baseData.getNum(1, 10000));// 公司名称
			businessInfoPage.selectProvince(baseData.getNum(1, 6));
			// 资质信息
			businessInfoPage.setIdCardFront();// 身份证正面
			businessInfoPage.setIdCardBack();// 身份证反面
			businessInfoPage.setBusinessLicense();// 营业执照
			businessInfoPage.setBankPermitCertification();// 银行开户许可证
			this.uploadLicense(category);// 资质文件上传

			// 商标信息
			int trademarkTypeIndex = baseData.getNum(1, 2);
			businessInfoPage.selectTrademarkType(trademarkTypeIndex);// 商标注册类型
			businessInfoPage.setTrademarkNB("sellerTrademarkNB" + baseData.getNum(1, 1000000));// 商标注册号
			businessInfoPage.setTradeLicense();// 商标注册证明
			if (trademarkTypeIndex == 2) {// 如果选择了授权商标，则要填写授权信息
				businessInfoPage.setAuthorizeLicense();// 品牌授权证明
				businessInfoPage.setAuthorEndDate(baseData.getTime(24, 0,0,"yyyy-MM-dd"));// 品牌授权截止日期
			}
			// 店铺信息
			businessInfoPage.setLogo();// 店铺LOGO
			businessInfoPage.setShopDescription("用于测试的商家");// 店铺描述
			// 银行卡信息
			//银行信息
			if (b==2) {
				businessInfoPage.setBankUser("bankUserR" + baseData.getNum(1, 1000));//银行户名
			}
			businessInfoPage.selectBankName(baseData.getNum(1, 6));// 开户行
			businessInfoPage.setBankBrance("番禺支行");// 支行名称
			businessInfoPage.setBankNB("78954545454545");// 银行卡号
			
			// 邀请信息
			businessInfoPage.setInvitCode(agentAccount);// 代理商邀请码
			businessInfoPage.selectMerchants(baseData.getNum(1, 3));// 随机选择招商人
			// 确认入驻合同并提交
			businessInfoPage.click_agreeCheck();
			businessInfoPage.click_submitBTN();
			driver.quit();
			
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		//return seller;//法人手机号与账号一至,所以账号即是法人手机号
	}

	/* 根据经营类目，选择资质文件上传 
	 * 	20190408运营中心v3.3版优化，经营类目＝商品一级分类，只有以下三个分类有资质上传
	 * 	食品酒水：食品生产许可证、食品经营许可证
		美妆个护：全国工业产品生产许可证
		家电汽车：3C认证书
	 * */
	private void uploadLicense(String category){
		if (category.equals("家电汽车")) {
			businessInfoPage.setFirsetLicense(category);// 3C认证证书
		}
		else if (category.equals("美妆个护")) {
			businessInfoPage.setFirsetLicense(category);// 全国工业产品生产许可证
		}
		else if (category.equals("食品酒水")){
			businessInfoPage.setFirsetLicense(category);// 食品生产许可证
			businessInfoPage.setSecondLicense(category);// 食品经营许可证
		}
		else {
			System.out.println(category+"无特殊资质上传");
		}
		
		/*if (category.equals("签证代理")) {
			businessInfoPage.setFirsetLicense(category);
			;// 因私出入境中介机构经营许可证
		} else if (category.equals("服装配饰")) {
			System.out.println("服装配饰没有其他资质文件需要上传");
		} else if (category.equals("日常生活")) {
			businessInfoPage.setFirsetLicense(category);
			;// 全国工业产品生产许可证
		} else if (category.equals("运动户外")) {
			businessInfoPage.setFirsetLicense(category);// 生产许可证
		} else if (category.equals("精选食品")) {
			businessInfoPage.setFirsetLicense(category);// 食品生产许可证
			businessInfoPage.setSecondLicense(category);// 食品经营许可证
			businessInfoPage.setThirdLicense(category);// 酒类流通许可证/备案登记表
		} else if (category.equals("养老院")) {
			businessInfoPage.setFirsetLicense(category);// 社会福利机构执业证书
			businessInfoPage.setSecondLicense(category);// 消防和安全检查合格证
			businessInfoPage.setThirdLicense(category);// 特种经营许可证
		} else if (category.equals("酒店")) {
			businessInfoPage.setFirsetLicense(category);// 特种经营许可证
			businessInfoPage.setSecondLicense(category);// 消防和安全检查合格证
		} else if (category.equals("门票")) {
			businessInfoPage.setFirsetLicense(category);// 旅游景区质量等级证书
		} else if (category.equals("旅游线路")) {
			businessInfoPage.setFirsetLicense(category);// 旅行社业务经营许可证
		} else if (category.equals("数码办公")) {
			businessInfoPage.setFirsetLicense(category);// 3C认证证书
		} else if (category.equals("家用电器")) {
			businessInfoPage.setFirsetLicense(category);// 3C认证证书
		} else if (category.equals("保健品")) {
			businessInfoPage.setFirsetLicense(category);// 食品经营许可证
		} else if (category.equals("休闲娱乐")) {
			businessInfoPage.setFirsetLicense(category);// 消防和安全检查合格证
		} else if (category.equals("汽车")) {
			businessInfoPage.setFirsetLicense(category);// 汽车销售准入证
		} else if (category.equals("医疗")) {
			businessInfoPage.setFirsetLicense(category);// 3C认证证书
		} else {
			System.out.println("nothing to do in sellerRegistTest, category is" + category);
		}*/
	}
}
