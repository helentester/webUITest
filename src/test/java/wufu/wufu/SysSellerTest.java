/**
 * @author helen
 * @date 2018年7月12日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.MyConfig;
import data.MemberData;
import data.SellerData;
import dataProvider.PCProvider;
import wufu.wufu.sysPages.SysIndexPage;
import wufu.wufu.sysPages.SysSellerEditPage;
import wufu.wufu.sysPages.SysSellerRegistAuditPage;

/**
 * @Description:后台管理：商家管理业务
 */
public class SysSellerTest {
	MyConfig myConfig = new MyConfig();
	BaseData baseData = new BaseData();
	SellerData sellerData = new SellerData();
	MemberData memberData = new MemberData();
	SysIndexPage indexPage;//后台管理首页
	SysSellerRegistAuditPage auditPage;//后台：商家审核页面
	SysSellerEditPage editPage;
	
	
	@Test(dataProvider="sellerType",dataProviderClass=PCProvider.class)
	public void test_addSeller(String sellerType) {
		Reporter.log("新增商家－"+sellerType);
		String account = baseData.getPhoneNumber();
		Reporter.log("商家账号为："+account);
		this.seller_add(sellerType, account,myConfig.getKeys("agent2") );
		assertTrue(sellerData.sellerExit(account),"注册的账号为："+account);
		assertEquals(Double.valueOf(memberData.get_ableScore(account)), Double.valueOf("100"));//商家也是会员，送100积分
	}
	
	/*新增商家
	 * @categoryName 经营类目
	 * @account 账号
	 * @inviter 代理商邀请人
	 * */
	public void seller_add(String category,String account,String inviter) {
		/*登录后台*/
		LoginUITest loginUITest = new LoginUITest();
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		try {
			/*后台主页*/
			Thread.sleep(2000);
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("商家管理");//点击“商家管理”
			indexPage.clickSubMenu("商家列表");//点击商家入驻审核
			/*商家入驻审核列表页面*/
			Thread.sleep(2000);
			driver.switchTo().frame("iframepage");
			auditPage = PageFactory.initElements(driver, SysSellerRegistAuditPage.class);
			auditPage.click_addBTN();//点击“新增商家”
			/*选择经营类目页面*/
			editPage = PageFactory.initElements(driver, SysSellerEditPage.class);
			editPage.select_category(category);//选择经营类目
			editPage.click_nextBTN();//下一步
			/*商家信息*/
			editPage.set_account(account);//登录手机号
			editPage.set_password("123456li");//登录密码
			editPage.set_legalMobile(account);//手机号
			editPage.set_supplierPerson("contact"+baseData.getNum(0, 999));//联系人姓名
			editPage.set_personMobile(account);//联系人电话
			int r = baseData.getNum(0, 2);//随机设置[企业性质]
			int b = 0;//[企业性质]-[个体工商户]:[已办理对公账户]，0没有该项；1构选；2不构选
			if (r==1) {//0为默认[法人企业]，1为[个体工商户]
				editPage.click_bussiness_status();
				b = baseData.getNum(1, 3);
				if (b==2) {
					editPage.click_account_done();
				}
			}
			editPage.set_supplierName(category+"seller"+baseData.getNum(0, 999));//商家名称
			editPage.set_supplierOrgName("testcompany");//公司名称
			
			editPage.set_idCardCopiesFront();//身份证正面
			editPage.set_idCardCopiesReverse();//身份证反面
			editPage.set_supplierOrgLicense();//营业执照
			editPage.set_bankLicense();//银行开户许可证
			
			//上传资质文件
			if (category.equals("签证代理")) {
				editPage.setFirsetLicense(category);
				;// 因私出入境中介机构经营许可证
			} else if (category.equals("服装配饰")) {
				System.out.println("服装配饰没有其他资质文件需要上传");
			} else if (category.equals("日常生活")) {
				editPage.setFirsetLicense(category);
				;// 全国工业产品生产许可证
			} else if (category.equals("运动户外")) {
				editPage.setFirsetLicense(category);// 生产许可证
			} else if (category.equals("精选食品")) {
				editPage.setFirsetLicense(category);// 食品生产许可证
				editPage.setSecondLicense(category);// 食品经营许可证
				editPage.setThirdLicense(category);// 酒类流通许可证/备案登记表
			} else if (category.equals("养老院")) {
				editPage.setFirsetLicense(category);// 社会福利机构执业证书
				editPage.setSecondLicense(category);// 消防和安全检查合格证
				editPage.setThirdLicense(category);// 特种经营许可证
			} else if (category.equals("酒店")) {
				editPage.setFirsetLicense(category);// 特种经营许可证
				editPage.setSecondLicense(category);// 消防和安全检查合格证
			} else if (category.equals("门票")) {
				editPage.setFirsetLicense(category);// 旅游景区质量等级证书
			} else if (category.equals("旅游线路")) {
				editPage.setFirsetLicense(category);// 旅行社业务经营许可证
			} else if (category.equals("数码办公")) {
				editPage.setFirsetLicense(category);// 3C认证证书
			} else if (category.equals("家用电器")) {
				editPage.setFirsetLicense(category);// 3C认证证书
			} else if (category.equals("保健品")) {
				editPage.setFirsetLicense(category);// 食品经营许可证
			} else if (category.equals("休闲娱乐")) {
				editPage.setFirsetLicense(category);// 消防和安全检查合格证
			} else if (category.equals("汽车")) {
				editPage.setFirsetLicense(category);// 汽车销售准入证
			} else if (category.equals("医疗")) {
				editPage.setFirsetLicense(category);// 3C认证证书
			} else {
				System.out.println("nothing to do in sellerRegistTest, category is" + category);
			}
			
			/*商标信息*/
			int trademarkTypeIndex = baseData.getNum(1, 2);
			editPage.selectTrademarkType(trademarkTypeIndex);//注册商标类型
			editPage.set_brandNumber("brand"+baseData.getNum(1, 10000));//商标注册号
			editPage.set_brandCertifying();//商标注册证明
			if (trademarkTypeIndex==2) {
				editPage.set_brandAccreditedCertifying();//品牌授权证明
				editPage.set_accreditEnd(baseData.getTime(24, 0, 0, "yyyy-MM-dd"));//品牌授权截止日期
			}
			
			//店铺信息
			editPage.set_supplierLogo();//店铺logo
			editPage.set_supplierDesc("测试数据");//店铺描述
			
			//银行信息
			if (b==2) {
				editPage.set_bankAccountName("bankUserB" + baseData.getNum(1, 1000));//银行户名
			}
			editPage.set_bankAccount("45412474574878484");//卡号
			editPage.select_bankName1(String.valueOf(baseData.getNum(1, 10)));//银行
			editPage.set_bankBranch("越秀支行");//支行名称
			
			//所在地区
			editPage.select_province1(String.valueOf(baseData.getNum(1, 10)));//省份
			
			//邀请信息
			editPage.set_recommendCode(inviter);//代理商邀请人
			editPage.select_zsman(String.valueOf(baseData.getNum(0, 10)));//招商人员
			editPage.set_contractStartDate(baseData.getTime(0, 0, 0, "yyyy-MM-dd"));//合同有效期，开始
			editPage.set_contractEndDate(baseData.getTime(24, 0, 0, "yyyy-MM-dd"));
			editPage.click_saveBTN();//保存按钮
			
			driver.quit();

		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
	
	/*商家入驻审核*/
	public void seller_audit(String sellerName){
		/*登录后台*/
		LoginUITest loginUITest = new LoginUITest();
		WebDriver driver = loginUITest.sysLogin("helen","123456li");
		try {
			/*后台主页*/
			Thread.sleep(2000);
			indexPage = PageFactory.initElements(driver, SysIndexPage.class);
			indexPage.clickMainMenu("商家管理");//点击“商家管理”
			indexPage.clickSubMenu("商家入驻审核");//点击商家入驻审核
			/*商家入驻审核列表页面*/
			Thread.sleep(2000);
			driver.switchTo().frame("iframepage");
			auditPage = PageFactory.initElements(driver, SysSellerRegistAuditPage.class);
			//auditPage.setLegalMobile(legalMobile);//输入法人手机号,20190221界面上去掉该搜索条件
			auditPage.set_sellerName(sellerName);
			auditPage.click_searchBTN();//点击搜索按钮
			auditPage.click_operationBTN();//点击列表中第一个操作按钮：审核
			/*商家审核内容页面*/
			Thread.sleep(2000);
			auditPage.click_saveBTN();
			/*确认合同有效期弹出窗*/
			Thread.sleep(2000);
			auditPage.setContractStartDate(baseData.getTime(1, 0,0,"yyyy-MM-dd"));//合同有效期：开始
			auditPage.setContractEndDate(baseData.getTime(24, 0,0,"yyyy-MM-dd"));//合同有效期：结束
			auditPage.click_sureContractDate();//合同有效期 确认按钮
			
			Thread.sleep(3000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
}
