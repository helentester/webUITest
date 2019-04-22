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
 * @Description:PC商城，商品详情页
 */
public class PC_goodsInfoPage extends BasePage{
	BaseData baseData = new BaseData();

	public PC_goodsInfoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[2]")
	private WebElement sku1;//商品的第一个SKU
	public String get_sku1() {
		return this.findMyElement(sku1).getAttribute("data-number");
	}
	public void click_sku1() {
		this.click(sku1);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[3]")
	private WebElement sku2;//第2个SKU
	public void click_sku2() {
		this.click(sku2);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[4]")
	private WebElement sku3;//第3个sku
	public void click_sku3() {
		this.click(sku3);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[5]")
	private WebElement sku4;//第4个SKU
	public void click_sku4() {
		this.click(sku4);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[6]")
	private WebElement sku5;//第4个SKU
	public void click_sku5() {
		this.click(sku5);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[7]")
	private WebElement sku6;//第4个SKU
	public void click_sku6() {
		this.click(sku6);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[8]")
	private WebElement sku7;//第4个SKU
	public void click_sku7() {
		this.click(sku7);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[9]")
	private WebElement sku8;//第4个SKU
	public void click_sku8() {
		this.click(sku8);
	}
	
	@FindBy(xpath="//*[@id='skuType']/div[10]")
	private WebElement sku9;//第4个SKU
	public void click_sku9() {
		this.click(sku9);
	}
	
	@FindBy(xpath="//*[@id=\"is-conpon\"]/div[2]/div[1]/span[2]")
	private WebElement coupon;//第一张优惠卷
	public void click_coupon() {
		this.click(coupon);
	}
	
	@FindBy(id="goodsNumber")
	private WebElement goodsNumber;//购买数量
	public void set_goodsNumber(String s) {
		this.sendkeys(goodsNumber, s);
	}
	
	@FindBy(id="plusNumber")
	private WebElement plusNumberBTN;//添加购买数量按钮
	public void click_plusNumberBTN() {
		this.click(plusNumberBTN);
	}
	
	@FindBy(id="buyBtn")
	private WebElement buyBtn;//立即购买按钮
	public void click_buyBtn() {
		this.click(buyBtn);
	}
	
	@FindBy(id="addCartBtn")
	private WebElement addCartBtn;//加入购物车按钮
	public void click_addCartBtn() {
		this.click(addCartBtn);	
	}
	
	@FindBy(id="cartLink")
	private WebElement cartLink;//购物车链接
	public void click_cartLink() {
		this.click(cartLink);
	}
	
	@FindBy(className="layui-layer-content")
	private WebElement maxBuy;//弹出提示：最大购买数
	public String get_maxBuy() {
		return baseData.getTargetList(this.findMyElement(maxBuy).getText(), "\\d+").get(0);
	}

}
