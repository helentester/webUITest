/**
 * @author helen
 * @date 2018年8月1日
 */
package wufu.wufu.PCPages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.BaseData;
import common.BasePage;

/**
 * @Description:订单生成页面，http://pctest.wufu360.com:8031/cart/summarize?orderIds=9339,9340,9341&orderPay=0.07
 */
public class PC_orderInfoSummarizePage extends BasePage{
	BaseData baseData = new BaseData();

	public PC_orderInfoSummarizePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*************普通商品和虚拟商品订单信息页面***************/
	@FindBy(xpath="/html/body/div[3]/div[2]/div/div/p[@class='orderNo']/span")
	private List<WebElement> orderNBs;//订单号列表
	public Set<String> get_orderNBs() {
		List<WebElement> list = this.findMyElements(orderNBs);
		Set<String> orderNBHash = new HashSet<String>();
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getText());
			orderNBHash.add(list.get(i).getText());
		}
		return orderNBHash;		
	}
	
	@FindBy(xpath="/html/body/div[3]/div[2]/div/div/div/p[@class='goods']/span")
	private List<WebElement> goodNames;//商品名称列表
	public Set<String> get_goodNames() {
		List<WebElement> list = this.findMyElements(goodNames);
		Set<String> goodNameHash = new HashSet<String>();
		for(int i=1;i<list.size();i++) {
			goodNameHash.add(list.get(i).getText());
		}
		return goodNameHash;	
	}
	
	@FindBy(xpath="/html/body/div[3]/div[2]/div/div/div/p[3]/span[2]")
	private WebElement address;//收货地址
	public String get_address() {
		return this.findMyElement(address).getText();
	}
	
	@FindBy(xpath="/html/body/div[3]/div[2]/div/div/div/p[3]/span[4]")
	private WebElement contact;//收货人联系电话
	public String get_contact() {
		return this.findMyElement(contact).getText();
	}
	
	@FindBy(xpath="/html/body/div[3]/div[2]/div/div/div/p[4]/em")
	private WebElement totalPay;//应付总金额
	public String get_totalPay() {
		return baseData.getTargetList(this.findMyElement(totalPay).getText(), "(\\d+(\\.\\d+)?)").get(0);
	}
	
	@FindBy(id="payBtn")
	private WebElement payBtn;//去支付按钮
	public void click_payBtn() {
		this.click(payBtn);
	}
	
	/*******************大健康商品订单提交成功页面************/
	@FindBy(xpath="/html/body/div[3]/div/div/p")
	private WebElement paySuccess;//付款成功提示
	public String get_payStatus() {
		String payStatu = "noPay";
		if(this.findMyElement(paySuccess).isDisplayed()) {
			payStatu="hadPay";
		}
		return payStatu;
	}
	
	/*******************订单提交成功页面********************/
	@FindBy(id="code")
	private WebElement code;//微信二维码
	public String code_isDisplay() {
		String code_isDisplay = "noDisplay";
		if (this.findMyElement(code).isDisplayed()) {
			code_isDisplay="display";
		}
		return code_isDisplay;
	}

}
