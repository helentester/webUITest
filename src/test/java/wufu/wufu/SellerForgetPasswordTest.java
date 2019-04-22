/**
 * @author helen
 * @date 2018年9月10日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.BaseData;
import common.BaseWinUI;
import common.MyConfig;
import data.CommonData;
import data.SellerData;
import wufu.wufu.sellerPages.SellerForgetPasswordPage;
import wufu.wufu.sellerPages.SellerLoginPage;

/**
 * @Description:商家中心，忘记密码业务测试
 */
public class SellerForgetPasswordTest {
	BaseWinUI baseWinUI = new BaseWinUI();
	BaseData baseData = new BaseData();
	MyConfig myConfig = new MyConfig();
	CommonData data = new CommonData();
	SellerData supplierData = new SellerData();
	SellerLoginPage sellerLoginPage;//
	SellerForgetPasswordPage sellerForgetPasswordPage;
	
	@Test
	public void forgetPassword() {
		Reporter.log("忘记密码");
		String account="18504106409";
		String Password_last = data.get_password(account);
		String Password_new = "";
		if (Password_last.equals("2d572ee9061abe31bb1e81fa2f654067")) {
			Password_new = "12345678li";
		}
		else if (Password_last.equals("2002bb380806ed22e636c6b222395b0b")) {
			Password_new = "123456li";
		}
		
		WebDriver driver = baseWinUI.getDrive();
		try {
			driver.get(myConfig.getKeys("sj_domainName"));
			
			/*登录页面*/
			sellerLoginPage = PageFactory.initElements(driver, SellerLoginPage.class);
			sellerLoginPage.click_forgetPassword();
			
			/*忘记密码页面*/
			Thread.sleep(3000);
			sellerForgetPasswordPage = PageFactory.initElements(driver, SellerForgetPasswordPage.class);
			sellerForgetPasswordPage.set_account(account);
			sellerForgetPasswordPage.click_getVerifCode();
			sellerForgetPasswordPage.set_verifCode(data.getVerificationCode_ByPhone(account));
			sellerForgetPasswordPage.set_password(Password_new);
			sellerForgetPasswordPage.click_submitBTN();
			Thread.sleep(15000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		assertEquals(supplierData.get_password(account), baseData.getMD5(Password_new));
	}

}
