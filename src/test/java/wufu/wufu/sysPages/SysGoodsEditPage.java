/**
 * @author helen
 * @date 2018年7月16日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:运营管理后台，编辑商品页面
 */
public class SysGoodsEditPage extends BasePage{

	public SysGoodsEditPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*******************添加商品页面*************/
	/* ---基本信息----*/
	@FindBy(id="businessName")
	private WebElement businessName;//商品名称
	public void click_businessName() {
		this.click(businessName);
	}
	
	//商家搜索弹出框-商家名称
	@FindBy(id="search_key")
	private WebElement search_key;//商家名称
	public void setSearchKey(String s) {
		this.sendkeys(search_key, s);
	}
	
	//商家搜索弹出框－搜索的结果列表
	@FindBy(id="supplier_select")
	private WebElement supplier;//商家列表
	public void select_supplier() {
		this.selectValue(supplier, "1", "ByIndex");//默认选择搜索出来的第一个
	}
	
	//商家搜索弹出框－提交按钮
	@FindBy(id="addButton")
	private WebElement supplierConfirmBTN;//确认商家按钮
	public void click_supplierConfirmBTN() {
		this.click(supplierConfirmBTN);
	}
	
	@FindBy(id="name")
	private WebElement goodsName;//商品名称
	public void setGoodsName(String s) {
		this.sendkeys(goodsName, s);
	}
	
	@FindBy(id="goodsTypeId")
	private WebElement goodsTypeId;//商品分类
	public void click_goodsTypeId() {
		this.click(goodsTypeId);
	}
	
	@FindBy(id="adsMessage")
	private WebElement adsMessage;//商品介绍广告语
	public void setAdsMessage(String s) {
		this.sendkeys(adsMessage, s);
	}
	
	@FindBy(id="dept")
	private WebElement brand;//品牌
	public void select_brand(int brandIndex) {
		this.selectValue(brand, String.valueOf(brandIndex), "ByIndex");
	}
	
	@FindBy(id="boughtWarning")
	private WebElement boughtWarning;//库存警告
	public void setBoughtWarning(int bought) {
		this.sendkeys(boughtWarning, String.valueOf(bought));
	}

	@FindBy(xpath="//input[@name='recommend'][1]")
	private WebElement recommend_Y;//是否首页推荐－是
	@FindBy(xpath="//input[@name='recommend'][2]")
	private WebElement recommend_N;//是否首页推荐－否
	public void setRecommend(String recommend) {
		if (recommend.equals("是")) {
			this.click(recommend_Y);
		}
		else if (recommend.equals("否")) {
			this.click(recommend_N);
		}
		else {
			System.out.println("设置首页推荐失败");
		}
	}
	
	@FindBy(xpath="//input[@name='goodsState'][1]")
	private WebElement goodsState_M;//实物或者服务类虚拟商品－实物
	@FindBy(xpath="//input[@name='goodsState'][2]")
	private WebElement goodsState_S;//实物或者服务类虚拟商品－服务类
	public void setGoodsState(String goodsState) {
		if (goodsState.equals("实物")) {
			this.click(goodsState_M);
		}
		else if (goodsState.equals("服务类")) {
			this.click(goodsState_S);
		}
		else {
			System.out.println("设置实物或者服务类虚拟商品失败");
		}
	}
	
	@FindBy(xpath="//input[@name='isRealName'][1]")
	private WebElement isRealName_Y;//是否实名义证－是
	@FindBy(xpath="//input[@name='isRealName'][2]")
	private WebElement isRealName_N;//是否实名义证－是
	public void setIsRealName(String isRealNameChose) {
		if (isRealNameChose.equals("是")) {
			this.click(isRealName_Y);
		}
		else if (isRealNameChose.equals("否")) {
			this.click(isRealName_N);
		}
		else {
			System.out.println("设置是否实名认证失败");
		}
	}
	
	@FindBy(xpath="//input[@name='isOwn'][1]")
	private WebElement isOwn_Y;//是否自营
	@FindBy(xpath="//input[@name='isOwn'][2]")
	private WebElement isOwn_N;//是否自营
	public void setIsOwn(String isOwnChose) {
		if (isOwnChose.equals("是")) {
			this.click(isOwn_Y);
		}
		else if (isOwnChose.equals("否")) {
			this.click(isOwn_N);
		}
		else {
			System.out.println("设置是否自营失败");
		}
	}

}
