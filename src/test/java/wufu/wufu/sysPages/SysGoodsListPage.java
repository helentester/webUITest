/**
 * @author helen
 * @date 2018年8月14日
 */
package wufu.wufu.sysPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:运营后台：商品列表页面
 */
public class SysGoodsListPage extends BasePage{

	public SysGoodsListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id="state")
	private WebElement state;//审核状态
	public void select_state(String s) {
		this.selectValue(state, s, "ByVisibleText");
	}
	
	@FindBy(id="isOnlive")
	private WebElement isOnlive;//上架状态
	public void select_isOnlive(String s) {
		this.selectValue(isOnlive, s, "ByVisibleText");
	}
	
	@FindBy(id="supplierId")
	private WebElement supplierId;//商家输入框
	public void click_supplierId() {
		this.click(supplierId);
	}
	
	@FindBy(id="search_key")
	private WebElement search_key;//商家搜索弹出框的关键词
	public void set_searchKey(String s) {
		this.sendkeys(search_key, s);
	}
	
	@FindBy(id="search_supplier_button")
	private WebElement search_supplier_button;//商家搜索弹出框[搜索]
	public void click_search_supplier_button() {
		this.click(search_supplier_button);
	}
	
	@FindBy(id="supplier_select")
	private  WebElement supplier_select;//商家搜索弹出框的选择
	public void select_supplier() {
		this.selectValue(supplier_select, "1", "ByIndex");
	}
	
	@FindBy(id="addButton")
	private WebElement addButton;//商家搜索弹出框[提交]
	public void click_addButton() {
		this.click(addButton);
	}
	
	@FindBy(id="name")
	private WebElement goodsName;//商品名称
	public void set_goodsName(String s) {
		this.sendkeys(goodsName, s);
	}
	
	@FindBy(id="search")
	private WebElement searchBTN;//查询按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	@FindBy(linkText="添加商品")
	private WebElement addGoodsBTN;//添加商品按钮
	public void click_addGoodsBTN() {
		this.click(addGoodsBTN);
	}
	
	/*******************表格第一行内容***************/
	
	
	@FindBy(xpath="//table/tbody/tr[1]/td[2]")
	private WebElement TGoodsId;//商品ID
	public String get_TGoodsId() {
		return this.findMyElement(TGoodsId).getText();
	}
	
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr/td[6]")
	private WebElement auditStatus;//审核状态
	public String get_auditStatus() {
		return this.findMyElement(auditStatus).getText();
	}
	
	@FindBy(xpath="/html/body/section/div[3]/div/section/div/table/tbody/tr/td[13]/a[@class='check_past']")//此处不能定位到下一个元素i，否则取不到值
	private WebElement check_past;//审核通过
	public void click_CheckPast() {
		this.click(check_past);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[13]/a[@class='check_not_past']")
	private WebElement check_not_past;//审核不通过
	public void click_CheckNotPast() {
		this.click(check_not_past);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[13]/a[@class='undercarriage']")
	private WebElement undercarriageBTN;//下架按钮
	public void click_undercarriage() {
		this.click(undercarriageBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[13]/a[@class='undercarriage_up']")
	private WebElement undercarriageUPBTN;//上架按钮
	public void click_undercarriageUP() {
		this.click(undercarriageUPBTN);
	}
	
	/*************审核不通过弹出框***********/
	
	@FindBy(id="suppliernoDesc")
	private WebElement suppliernoDesc;//审核不通过描述
	public void set_suppliernoDesc(String s) {
		this.sendkeys(suppliernoDesc, s);
	}
	
	@FindBy(id="confirm1")
	private WebElement confirm1;//保存按钮
	public void click_confirm1() {
		this.click(confirm1);
	}
	
	/************提示信息弹出框******************/
	@FindBy(xpath="//*[@id=\"myModal2\"]/div/div/div[3]/button")
	private WebElement closeMassage;//系统提示的窗口的关闭按钮
	public void click_closeMassage() {
		this.click(closeMassage);
	}
	
	public void alerter(WebDriver driver,String s) {//alert处理事件
		this.alertSubmit(driver, s);
	}
	

}
