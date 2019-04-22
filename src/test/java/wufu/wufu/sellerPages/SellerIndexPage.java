/**
 * @author helen
 * @date 2018年6月22日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:
 */
public class SellerIndexPage extends BasePage{

	public SellerIndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/span")
	private WebElement username;//主面用户名
	public String getUserName() {
		return this.findMyElement(username).getText();
	}
	public WebElement getTopUsername() {
		return this.findMyElement(username);
	}
	
	//主菜单
	@FindBy(xpath="/html/body/div[1]/div/div/div[1]/ul/li[2]/div")
	private WebElement orderManage;//订单管理
	@FindBy(xpath="/html/body/div[1]/div/div/div[1]/ul/li[3]/div")
	private WebElement goodsManage;//商品管理
	public void click_MainMenu(String mainMenuName) {
		if (mainMenuName.equals("订单管理")) {
			this.click(orderManage);
		}
		else if (mainMenuName.equals("商品管理")) {
			this.click(goodsManage);
		}
		else {
			System.out.println("点击主菜单失败");
		}
	}
	
	//子菜单
	@FindBy(xpath="/html/body/div[1]/div/div/div[1]/ul/li[2]/ul/li[3]")
	private WebElement refundList;//退货退款订单列表
	@FindBy(xpath="/html/body/div[1]/div/div/div[1]/ul/li[3]/ul/li[1]")
	private WebElement goodList;//商品列表
	public void click_subMenu(String subMenuName) {
		if (subMenuName.equals("退货/退款单")) {
			this.click(refundList);
		}
		else if (subMenuName.equals("商品列表")) {
			this.click(goodList);
		}
		else {
			System.out.println("点击子菜单失败");
		}
	}

	@FindBy(xpath="/html/body/ul/li[2]")
	private WebElement exit;//退出
	public void clickExitBTN(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(this.getTopUsername()).perform();
		this.click(exit);
	}
		
	@FindBy(className="el-button--primary")
	private WebElement sureExitBTN;//确认退出
	public void clickSureExit() {
		this.click(sureExitBTN);
	}
}
