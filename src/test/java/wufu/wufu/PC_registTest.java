/**
 * @author helen
 * @date 2018年8月8日
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
import data.CouponData;
import data.MemberData;
import wufu.wufu.PCPages.PC_registPage;

/**
 * @Description:PC商城注册功能
 */
public class PC_registTest {
	BaseWinUI winUI = new BaseWinUI();
	BaseData baseData = new BaseData();
	MyConfig myConfig = new MyConfig();
	CommonData commonData = new CommonData();
	MemberData memberData = new MemberData();
	CouponData couponData = new CouponData();
	PC_registPage registPage;

	@Test
	public void test_registByPhone() {
		Reporter.log("手机注册送福豆");
		String phoneNB = baseData.getPhoneNumber();
		Reporter.log("注册账号："+phoneNB);
		this.regist("phone",phoneNB);
		assertEquals(memberData.get_ableScore(phoneNB), "100.00", "注册的手机号为：" +phoneNB);
		assertTrue(couponData.hadCoupon(phoneNB, myConfig.getKeys("couponId")),"校验是否成功获得注册券");
	}

	@Test
	public void test_registByEmail(){
		Reporter.log("邮箱注册，送福豆");
		String email = String.valueOf(baseData.getNum(0, 10000)+"@test.com");
		Reporter.log("注册账号："+email);
		this.regist("email",email);
		assertEquals(memberData.get_ableScore(email), "100.00", "注册的邮箱为：" + email);
		assertTrue(couponData.hadCoupon(email, myConfig.getKeys("couponId")),"校验是否成功获得注册券");
	}

	/* 使用手机注册 
	 * @registType	注册类型，可选值：email、phone
	 * @account 账号
	 * */
	public void regist(String registType,String account) {
		WebDriver driver = winUI.getDrive();
		try {
			driver.get(myConfig.getKeys("PC_domainName")+"/register");
			registPage = PageFactory.initElements(driver, PC_registPage.class);
			if (registType.equals("email")) {// 邮箱注册
				registPage.click_emailRegistLink();
				registPage.set_email(account);
			} else {// 手机注册
				registPage.set_mobile(account);// 手机号
			}

			registPage.click_getCodeBTN();// 点击获取验证码按钮
			String VerificationCode;
			if (registType.equals("email")) {//邮箱注册
				VerificationCode = commonData.getVerificationCode_ByEmail(account);
			}
			else {//手机注册
				VerificationCode = commonData.getVerificationCode_ByPhone("+86"+account);
			}
			registPage.set_code(driver, VerificationCode);// 验证码
			registPage.set_password("123456li");// 密码
			registPage.click_agree();// 同意条款
			registPage.click_registBTN();// 立即注册
			driver.quit();
			Thread.sleep(13000);
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}

	}
}
