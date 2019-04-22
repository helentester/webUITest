/**
 * @author helen
 * @date 2018年8月10日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:PC商城首页
 */
public class PC_indexPage extends BasePage{

	public PC_indexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/span/a")
	private WebElement account;///用户账号
	public String get_account() {
		return this.findMyElement(account).getText();
	}

}
