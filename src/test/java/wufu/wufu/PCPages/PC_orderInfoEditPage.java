/**
 * @author helen
 * @date 2018年7月24日
 */
package wufu.wufu.PCPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BaseData;
import common.BasePage;

/**
 * @Description:PC商城，订单信息填写页面：http://pctest.wufu360.com:8031/cart/contact?type=virtual&goodsId=7191&number=1&isVirtual=1&skuNumber=201807180000001472&useCoupon=1&useIntegration=1
 */
public class PC_orderInfoEditPage extends BasePage{
	BaseData baseData = new BaseData();

	public PC_orderInfoEditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/****************收货地址版块*/
	@FindBy(id="addbtn")
	private WebElement addbtn;//添加收货地址按钮
	public void click_addbtn() {
		this.click(addbtn);
	}
	
	@FindBy(id="deliveryName")
	private WebElement deliveryName;//收货人名称
	public void set_deliveryName(String s) {
		this.sendkeys(deliveryName, s);
	}
	
	@FindBy(id="phone")
	private WebElement phone;//收货人手机号
	public void set_phone(String s) {
		this.sendkeys(phone, s);
	}
	
	@FindBy(className="province-text")
	private WebElement province;//省份
	@FindBy(xpath="//*[@id=\"province\"]/li[19]")
	private WebElement province_guangdong;//广东省
	public void select_province() {
		this.click(province);
		this.click(province_guangdong);
	}
	
	@FindBy(id="detailAddress")
	private WebElement detailAddress;//详细地址
	public void set_detailAddress(String s) {
		this.sendkeys(detailAddress, s);
	}
	
	@FindBy(id="save")
	private WebElement save;//保存按钮
	public void click_save() {
		this.click(save);
	}
	
	/***************虚拟商品中的联系人信息**/
	@FindBy(id="memberAccount")
	private WebElement memberAccount;//姓名
	public void set_memberAccount(String s) {
		this.sendkeys(memberAccount, s);
	}
	
	@FindBy(id="cmobile")
	private WebElement mobile;//手机号码
	public void set_mobile(String s) {
		this.sendkeys(mobile, s);
	}
	
	@FindBy(id="idCardInfo")
	private WebElement idCardInfo;//身份证号
	public void set_idCardInfo(String s) {
		this.sendkeys(idCardInfo, s);
	}
	
	@FindBy(id="remark")
	private WebElement remark;//备注
	public void set_remark(String s) {
		this.sendkeys(remark, s);
	}
	
	/**************结算数据***/
	@FindBy(id="hscoreC")
	private WebElement hscoreC;//大健康积分账号余额
	public String get_hscoreC() {
		return baseData.getTargetList(this.findMyElement(hscoreC).getText(), "(\\d+(\\.\\d+)?)").get(0); 
	}
	
	@FindBy(id="total")
	private WebElement total;//总商品金额
	public String get_total() {
		String totalMoney = null;
		try {
			Thread.sleep(3);
			totalMoney = baseData.getTargetList(this.findMyElement(total).getText(), "(\\d+(\\.\\d+)?)").get(0); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return totalMoney;
	}
	
	@FindBy(id="freight")
	private WebElement freight;//配送费用
	public String get_freight() {
		return baseData.getTargetList(this.findMyElement(freight).getText(), "(\\d+(\\.\\d+)?)").get(0); 
	}
	
	@FindBy(id="allDiscount")
	private WebElement allDiscount;//优惠金额
	public String get_allDiscount() {
		return baseData.getTargetList(this.findMyElement(allDiscount).getText(), "(\\d+(\\.\\d+)?)").get(0); 
	}
	
	@FindBy(id="amount")
	private WebElement amount;//应付总额
	public String get_amount() {
		return baseData.getTargetList(this.findMyElement(amount).getText(), "(\\d+(\\.\\d+)?)").get(0);
	}
	
	@FindBy(id="addrDetail")
	private WebElement addrDetail;//邮寄地址
	public String get_addrDetail() {
		return this.findMyElement(addrDetail).getText();
	}
	
	@FindBy(id="receiver")
	private WebElement receiver;//收货人地址
	public String  get_receiver() {
		return this.findMyElement(receiver).getText();
	}
	
	@FindBy(xpath="/html/body/div[3]/div[7]/div/ul/li[1]/ul/li[1]")
	private WebElement coupon1;//第一个优惠券
	public void click_coupon1() {
		this.click(coupon1);
	}
	
	@FindBy(id="balancePrice")
	private WebElement balancePrice;//余额支付
	public void set_balancePrice(String s) {
		this.sendkeys(balancePrice, s);
	}
	
	@FindBy(id="orderSubmitBtn")
	private WebElement gotoPay;//提交订单按钮
	public void click_gotoPay() {
		this.click(gotoPay);
	}

}
