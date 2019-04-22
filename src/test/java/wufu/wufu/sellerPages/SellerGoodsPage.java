/**
 * @author helen
 * @date 2018年7月16日
 */
package wufu.wufu.sellerPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import common.AutoitRun;
import common.BaseData;
import common.BasePage;

/**
 * @Description:商家管理后台－商品管理
 */
public class SellerGoodsPage extends BasePage{
	BaseData baseData = new BaseData();
	AutoitRun autoitRun = new AutoitRun();

	public SellerGoodsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/***************商品列表*******************/
	@FindBy(xpath="//form/div[1]/div[1]/div/div/input")
	private WebElement s_goodsName;//商品名称
	public void set_s_goodsName(String s) {
		this.sendkeys(s_goodsName, s);
	}
	
	@FindBy(xpath="//form/div[2]/button")
	private WebElement searchBTN;//搜索按钮
	public void click_searchBTN() {
		this.click(searchBTN);
	}
	
	@FindBy(xpath="//button[@class='el-button button-reset add-good el-button--primary']")
	private WebElement addGoodBTN;//添加商品按钮
	public void click_addGoodBTN() {
		this.click(addGoodBTN);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[8]/div/button[3]")
	private WebElement applyAudit;//申请审核
	public void click_applyAudit() {
		this.click(applyAudit);
	}
	
	@FindBy(xpath="//table/tbody/tr[1]/td[8]/div/button[5]")
	private WebElement onShelf;//上架
	public void click_onShelf() {
		this.click(onShelf);
	}
	
	@FindBy(xpath="//table/tbody/tr[2]/td[8]/div/button[5]")
	private WebElement unShelf;//下架
	public void click_unShelf() {
		this.click(unShelf);
	}
	
	/*************添加商品页面***************/
	@FindBy(xpath="//form/div/div/div[1]/div/div/div/input")
	private WebElement goodsName;//商品名称
	public void set_goodsName(String s) {
		this.sendkeys(goodsName, s);
	}
	
	@FindBy(xpath="//form/div/div/div[1]/div[2]/div/div/input")
	private WebElement ads;//商品介绍广告语
	public void set_ads(String s) {
		this.sendkeys(ads, s);
	}
	
	/*商品分类*/
	@FindBy(xpath="//form/div/div/div[2]/div[1]/div/div[1]/input")
	private WebElement goodCategory;//商品分类,假输入框
	//商品分类－一级分类 
	@FindBy(xpath="//div[@class='one-category']/div/label[1]")
	private WebElement goodCategory_1;//食品酒水
	@FindBy(xpath="//div[@class='one-category']/div/label[2]")
	private WebElement goodCategory_2;//美妆个护
	@FindBy(xpath="//div[@class='one-category']/div/label[3]")
	private WebElement goodCategory_3;//母婴个护
	//商品分类－二级分类
	@FindBy(xpath="//div[@class='two-category']/div/label[1]")
	private WebElement goodCategory_11;//第1个二级分类
	@FindBy(xpath="//div[@class='two-category']/div/label[2]")
	private WebElement goodCategory_22;//第2个二级分类
	@FindBy(xpath="//div[@class='two-category']/div/label[3]")
	private WebElement goodCategory_33;//第3个二级分类
	//商品分类－三级分类
	@FindBy(xpath="//div[@class='three-category']/div/label[1]")
	private WebElement goodCategory_111;//第1个三级分类
	@FindBy(xpath="//div[@class='three-category']/div/label[2]")
	private WebElement goodCategory_222;//第2个三级分类
	//商品分类－确定按钮
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div/div/div[2]/div/div[3]/div/button[2]")
	private WebElement confirmGoodCategoryBTN;//确定按钮
	public void setGoodCategory(WebDriver driver) {//随机选择商品分类
		try {
			Thread.sleep(1000);
			//this.JS(driver, goodCategory, "arguments[0].click();");//点击商品分类输入框，弹出分类选择窗口
			this.click(goodCategory);//点击商品分类输入框，弹出分类选择窗口
			
			int category_one = baseData.getNum(1, 3);//一级分类随机数
			int category_two = baseData.getNum(1, 3);//二级分类随机数
			int category_three = baseData.getNum(1, 2);//三级分类随机数
			
			//一级分类
			Thread.sleep(1000);
			if (category_one==1) {
				this.click(goodCategory_1);
			}
			else if (category_one==2) {
				this.click(goodCategory_2);
			}
			else if (category_one==3) {
				this.click(goodCategory_3);
			}
			else {
				System.out.println("选择一级商品分类失败");
			}
			
			//二级分类
			Thread.sleep(1000);
			if (category_two==1) {
				this.click(goodCategory_11);
			}
			else if (category_two==2) {
				this.click(goodCategory_22);
			}
			else if (category_two==3) {
				this.click(goodCategory_33);
			}
			else {
				System.out.println("选择二级商品分类失败");
			}
			
			//三级分类
			Thread.sleep(1000);
			if (category_three==1) {
				this.click(goodCategory_111);
			}
			else if (category_three==2) {
				this.click(goodCategory_222);
			}
			else {
				System.out.println("选择三级商品分类失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//点击确认按钮
		this.click(confirmGoodCategoryBTN);
	}

	@FindBy(xpath="//div[@value='number']/input")
	private WebElement storeWarning;//库存警告
	public void set_storeWarning(int store) {
		this.sendkeys(storeWarning, String.valueOf(store));
	}
	
	@FindBy(xpath="//form/div/div/div[3]/div/div/label[1]")
	private WebElement showOnIndex_Y;//是否首页推荐－是
	@FindBy(xpath="//form/div/div/div[3]/div/div/label[2]")
	private WebElement showOnIndex_N;//是否首页推荐－否
	public void set_showOnIndex(String s) {
		if (s.equals("是")) {
			this.click(showOnIndex_Y);
		}
		else if (s.equals("否")) {
			this.click(showOnIndex_N);
		}
		else {
			System.out.println("设置是否首页推荐失败");
		}
	}
	
	@FindBy(xpath="//form/div/div/div[4]/div/div/label[1]")
	private WebElement goodType_M;//商品类型－实物商品
	@FindBy(xpath="//form/div/div/div[4]/div/div/label[2]")
	private WebElement goodType_S;//商品类型－虚拟商品
	public void set_goodType(String s) {
		if (s.equals("实物商品")) {
			this.click(goodType_M);
		}
		else if (s.equals("虚拟商品")) {
			this.click(goodType_S);
		}
		else {
			System.out.println("设置商品类型失败");
		}
	}
	
	@FindBy(xpath="//form/div/div/div[5]/div/div/label[1]")
	private WebElement infoReal_Y;//是否实名认证－是
	@FindBy(xpath="//form/div/div/div[5]/div/div/label[2]")
	private WebElement infoReal_N;//是否实名认证－否
	public void set_infoReal(String s) {
		if (s.equals("是")) {
			this.click(infoReal_Y);
		}
		else if (s.equals("否")) {
			this.click(infoReal_N);
		}
		else {
			System.out.println("设置是否实名认证失败");
		}
	}
	
	@FindBy(xpath="//form/div/div/div[6]/div/div/label[1]")
	private WebElement own_Y;//是否自营－是
	@FindBy(xpath="//form/div/div/div[6]/div/div/label[2]")
	private WebElement own_N;//是否自营－否
	public void set_own(String s) {
		if (s.equals("是")) {
			this.click(own_Y);
		}
		else if (s.equals("否")) {
			this.click(own_N);
		}
		else {
			System.out.println("设置是否自营失败");
		}
	}
	
	@FindBy(xpath="//form/div/div/div[7]/div/div/label[1]")
	private WebElement belongHealth_Y;//是否大健康H积分商品-是
	@FindBy(xpath="//form/div/div/div[7]/div/div/label[2]")
	private WebElement belongHealth_N;//是否大健康H积分商品－否
	public void set_belongHealth(String s) {
		if (s.equals("是")) {
			this.click(belongHealth_Y);
		}
		else if (s.equals("否")) {
			this.click(belongHealth_N);
		}
		else {
			System.out.println("设置是否大健康H积分商品失败");
		}
	}
	
	@FindBy(xpath="//div[@class='add-img-tag']")
	private WebElement goodMainImg;//商品主图
	public void set_goodMainImg() {
		this.click(goodMainImg);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\goodMainImage.jpg"));
	}
	
	@FindBy(xpath="//form/div/div/div[9]/div/div/div[1]/label[1]")
	private WebElement sellNow;//立即上架
	@FindBy(xpath="//form/div/div/div[9]/div/div/div[1]/label[2]")
	private WebElement sellByTime;//定时上架
	@FindBy(xpath="//form/div/div/div[9]/div/div/div[2]/input")
	private WebElement sellTime;//上架时间
	public void set_sellTime(WebDriver driver,String s) {
		if (s.equals("立即上架")) {
			this.click(sellNow);
		}
		else if (s.equals("定时上架")) {
			this.click(sellByTime);
			this.JS(driver, sellTime, "arguments[0].removeAttribute('readonly');");
			this.sendkeys(sellTime, baseData.getTime(0, 0, 1, "yyyy-MM-dd HH:mm:ss"));//设置为1小时后上架
		}
		else {
			System.out.println("设置上架时间失败");
		}
	}
	
	@FindBy(xpath="//form/div/div/div[10]/div[1]/div/div/input")
	private WebElement goodsKeywords;//商品关键词
	public void set_goodsKeywords(String s) {
		this.sendkeys(goodsKeywords, s);
	}
	
	@FindBy(xpath="//form/div/div/div[10]/div[2]/div/div/div[1]/input")
	private WebElement freightPay;//运费模版选择，假下拉框
	@FindBy(xpath="/html/body/div[3]/div/div[1]/ul/li")
	private WebElement freightPay_1;//第一个运费模版
	public void set_freightPay() {
		this.click(freightPay);
		this.click(freightPay_1);
	}
	
	@FindBy(xpath="//form/div/div/div[10]/div[3]/div/div/input")
	private WebElement maxNB;//最大购买数
	public void set_maxNB(String s) {
		this.sendkeys(maxNB, s);
	}
	
	/**属性/规格**/
	@FindBy(xpath="//form/div[1]/div/button")
	private WebElement addAttrBTN;//添加商品规则按钮
	//第一个属性
	@FindBy(xpath="//form/div[1]/div/div/div/div[1]/div/div[1]/input[@placeholder='请选择商品规格']")
	private WebElement attr_1;//第一个商品属性，假下拉框
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='颜色']")
	private WebElement attr1_colour;//第一个商品属性，选项1
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='容量']")
	private WebElement attr1_capacity;//第一个商品属性，选项2
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='重量']")
	private WebElement attr1_weight;//第一个商品属性，选项3
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='尺寸']")
	private WebElement attr1_size;//第一个商品属性，选项4
	@FindBy(xpath="//form/div[1]/div/div/ul/li")
	private WebElement attr1_addSubBTN;//第一个商品属性的规格添加按钮
	//第二个属性                     
	@FindBy(xpath="//form/div[1]/div/div[2]/div/div[1]/div/div[1]/input[@placeholder='请选择商品规格']")
	private WebElement attr_2;//第二个商品属性，假下拉框
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='口味']")
	private WebElement attr2_Flavor;//第二个商品属性，选项5
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='数量']")
	private WebElement attr2_Number;//第二个商品属性，选项6
	@FindBy(xpath="//html/body/div[4]/div/div[1]/ul/li[@value-key='尺码']")
	private WebElement attr2_Size;//第二个商品属性，选项7
	@FindBy(xpath="//form/div[1]/div/div[2]/ul/li")
	private WebElement attr2_addSubBTN;//第二个商品属性的规格添加按钮
	//添加规则窗口
	@FindBy(xpath="//div[@class='add-sku-wrapper']/div/input")
	private WebElement sku;//添加规格输入框
	@FindBy(xpath="//div[@class='add-sku-wrapper']/button")
	private WebElement sku_addBTN;//添加规格按钮
	@FindBy(xpath="//div[@id='edit-format-dialog-two']/div/div[3]/div/button[2]")
	private WebElement sku_saveBTN;//保存规格按钮
	public void set_attr(){//随机设置商品属性规格，默认两个属性－两个规格
		try {
			//第一个属性和规格
			this.click(addAttrBTN);//点击“添加商品规格”按钮
			this.click(attr_1);//点击属性选择框
			int attrIndex_1 =1;// baseData.getNum(1, 4);
			if (attrIndex_1==1) {//选择属性
				this.click(attr1_colour);
			}
			else if (attrIndex_1==2) {
				this.click(attr1_capacity);
			}
			else if (attrIndex_1==3) {
				this.click(attr1_weight);
			}
			else if (attrIndex_1==4) {
				this.click(attr1_size);
			}
			else {
				System.out.println("设置第一个商品属性失败");
			}
			this.click(attr1_addSubBTN);//打开规格添加界面
			this.sendkeys(sku, "s1");//添加第一个规格
			this.click(sku_addBTN);
			this.sendkeys(sku, "s2");//添加第二个规格
			this.click(sku_addBTN);
			this.click(sku_saveBTN);//保存第一个属性的规格
			//第二个属性和规格
			Thread.sleep(3000);
			this.click(addAttrBTN);//点击“添加商品规格”
			this.click(attr_2);//点击属性选择框
			int attrIndex_2 = baseData.getNum(1, 3);
			if (attrIndex_2==1) {
				this.click(attr2_Flavor);
			}
			else if (attrIndex_2==2) {
				this.click(attr2_Number);
			}
			else if (attrIndex_2==3) {
				this.click(attr2_Size);
			}
			this.click(attr2_addSubBTN);//打开规格添加界面
			this.sendkeys(sku, "k1");//添加第一个规格
			this.click(sku_addBTN);
			this.sendkeys(sku, "k2");//添加第二个规格
			this.click(sku_addBTN);
			this.click(sku_saveBTN);//保存第二个属性的规格
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//SKU数据
	@FindBy(xpath="//table/tbody/tr[1]/td[3]/div/input")
	private WebElement sku_price1;//售价1
	@FindBy(xpath="//table/tbody/tr[2]/td[3]/div/input")
	private WebElement sku_price2;//售价2
	@FindBy(xpath="//table/tbody/tr[3]/td[3]/div/input")
	private WebElement sku_price3;//售价3
	@FindBy(xpath="//table/tbody/tr[4]/td[3]/div/input")
	private WebElement sku_price4;//售价4
	@FindBy(xpath="//table/tbody/tr[1]/td[4]/div/div/div/label[1]")
	private WebElement sku_allowScorePay1;//允许积分支付按钮1
	@FindBy(xpath="//table/tbody/tr[2]/td[4]/div/div/div/label[1]")
	private WebElement sku_allowScorePay2;//允许积分支付按钮2
	@FindBy(xpath="//table/tbody/tr[3]/td[4]/div/div/div/label[1]")
	private WebElement sku_allowScorePay3;//允许积分支付按钮3
	@FindBy(xpath="//table/tbody/tr[4]/td[4]/div/div/div/label[1]")
	private WebElement sku_allowScorePay4;//允许积分支付按钮4
	@FindBy(xpath="//table/tbody/tr[1]/td[4]/div/div/div/div/input")
	private WebElement sku_score1;//积分1
	@FindBy(xpath="//table/tbody/tr[2]/td[4]/div/div/div/div/input")
	private WebElement sku_score2;//积分2
	@FindBy(xpath="//table/tbody/tr[3]/td[4]/div/div/div/div/input")
	private WebElement sku_score3;//积分3
	@FindBy(xpath="//table/tbody/tr[4]/td[4]/div/div/div/div/input")
	private WebElement sku_score4;//积分4
	@FindBy(xpath="//table/tbody/tr[1]/td[5]/div/div/div/input")
	private WebElement sku_HScore1;//H积分支付1
	@FindBy(xpath="//table/tbody/tr[2]/td[5]/div/div/div/input")
	private WebElement sku_HScore2;//H积分支付2
	@FindBy(xpath="//table/tbody/tr[3]/td[5]/div/div/div/input")
	private WebElement sku_HScore3;//H积分支付3
	@FindBy(xpath="//table/tbody/tr[4]/td[5]/div/div/div/input")
	private WebElement sku_HScore4;//H积分支付4
	@FindBy(xpath="//table/tbody/tr[1]/td[6]/div/input")
	private WebElement sku_settlePrice1;//结算价1
	@FindBy(xpath="//table/tbody/tr[2]/td[6]/div/input")
	private WebElement sku_settlePrice2;//结算价2
	@FindBy(xpath="//table/tbody/tr[3]/td[6]/div/input")
	private WebElement sku_settlePrice3;//结算价3
	@FindBy(xpath="//table/tbody/tr[4]/td[6]/div/input")
	private WebElement sku_settlePrice4;//结算价4
	@FindBy(xpath="//table/tbody/tr[1]/td[7]/div/input")
	private WebElement sku_stock1;//库存1
	@FindBy(xpath="//table/tbody/tr[2]/td[7]/div/input")
	private WebElement sku_stock2;//库存2
	@FindBy(xpath="//table/tbody/tr[3]/td[7]/div/input")
	private WebElement sku_stock3;//库存3
	@FindBy(xpath="//table/tbody/tr[4]/td[7]/div/input")
	private WebElement sku_stock4;//库存4
	@FindBy(xpath="//table/tbody/tr[1]/td[8]/div/input")
	private WebElement sku_min1;//最小购买数1
	@FindBy(xpath="//table/tbody/tr[2]/td[8]/div/input")
	private WebElement sku_min2;//最小购买数2
	@FindBy(xpath="//table/tbody/tr[3]/td[8]/div/input")
	private WebElement sku_min3;//最小购买数3
	@FindBy(xpath="//table/tbody/tr[4]/td[8]/div/input")
	private WebElement sku_min4;//最小购买数4
	@FindBy(xpath="//table/tbody/tr[1]/td[9]/div/input")
	private WebElement sku_max1;//最大购买数1
	@FindBy(xpath="//table/tbody/tr[2]/td[9]/div/input")
	private WebElement sku_max2;//最大购买数2
	@FindBy(xpath="//table/tbody/tr[3]/td[9]/div/input")
	private WebElement sku_max3;//最大购买数3
	@FindBy(xpath="//table/tbody/tr[4]/td[9]/div/input")
	private WebElement sku_max4;//最大购买数4
	public void set_SKU(String belongHealth) {//根据是否大健康商品设置
		HashMap<String, Integer> price = new HashMap<String, Integer>(){
			private static final long serialVersionUID = 1L;
			{
				put("price1", baseData.getNum(30, 500));
				put("price2", baseData.getNum(30, 500));
				put("price3", baseData.getNum(30, 500));
				put("price4", baseData.getNum(30, 500));
			}
		};
		
		if (belongHealth.equals("否")) {
			this.setSKU_priceScore(price);//设置售价和积分(结算价自动生成)
		}
		else {
			this.set_HScore(price);//设置H积分
		}
		//设置库存
		this.sendkeys(sku_stock1, String.valueOf(baseData.getNum(200, 2000)));
		this.sendkeys(sku_stock2, String.valueOf(baseData.getNum(200, 2000)));
		this.sendkeys(sku_stock3, String.valueOf(baseData.getNum(200, 2000)));
		this.sendkeys(sku_stock4, String.valueOf(baseData.getNum(200, 2000)));
		//设置最小购买数
		this.sendkeys(sku_min1, String.valueOf(baseData.getNum(1, 10)));
		this.sendkeys(sku_min2, String.valueOf(baseData.getNum(1, 10)));
		this.sendkeys(sku_min3, String.valueOf(baseData.getNum(1, 10)));
		this.sendkeys(sku_min4, "1");
		//设置最大购买数
		this.sendkeys(sku_max1, String.valueOf(baseData.getNum(50, 100)));
		this.sendkeys(sku_max2, String.valueOf(baseData.getNum(50, 100)));
		this.sendkeys(sku_max3, String.valueOf(baseData.getNum(50, 100)));
		this.sendkeys(sku_max4, String.valueOf(baseData.getNum(50, 100)));
	}
	public void set_HScore(HashMap<String, Integer> price) {//设置H积分
		//设置H积分
		this.sendkeys(sku_HScore1, String.valueOf(price.get("price1")));
		this.sendkeys(sku_HScore2, String.valueOf(price.get("price2")));
		this.sendkeys(sku_HScore3, String.valueOf(price.get("price3")));
		this.sendkeys(sku_HScore4, String.valueOf(price.get("price4")));
	}
	
	public void setSKU_priceScore(HashMap<String, Integer> price) {//设置售价和积分
		//设置售价
		this.sendkeys(sku_price1, String.valueOf(price.get("price1")+0.01));
		this.sendkeys(sku_price2, String.valueOf(price.get("price2")+0.02));
		this.sendkeys(sku_price3, String.valueOf(price.get("price3")+0.03));
		this.sendkeys(sku_price4, String.valueOf(price.get("price4")+1));
		//随机设置是否允许积分支付
		if ((baseData.getNum(1, 2))==1) {
			//第一个积分
			this.click(sku_allowScorePay1);
			this.sendkeys(sku_score1, String.valueOf(price.get("price1")));
			//第二个积分
			this.click(sku_allowScorePay2);
			this.sendkeys(sku_score2, String.valueOf(price.get("price2")));
			//第三个积分
			this.click(sku_allowScorePay3);
			this.sendkeys(sku_score3, String.valueOf(price.get("price3")));
			//第四个积分
			this.click(sku_allowScorePay4);
			this.sendkeys(sku_score4, String.valueOf(price.get("price4")));
		}
		else {
			System.out.println("不允许积分支付");
		}
	}
	
	@FindBy(xpath="//div[@class='sku-main']/div[1]/div/div/div")
	private WebElement sku1Img;//第1个SKU图片
	@FindBy(xpath="//div[@class='sku-main']/div[2]/div/div/div")
	private WebElement sku2Img;//第2个SKU图片
	@FindBy(xpath="//div[@class='sku-main']/div[3]/div/div/div")
	private WebElement sku3Img;//第3个SKU图片
	@FindBy(xpath="//div[@class='sku-main']/div[4]/div/div/div")
	private WebElement sku4Img;//第4个SKU图片
	public void set_skuImg() {//设置SKU图片
		this.click(sku1Img);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\sku1.jpg"));
		this.click(sku2Img);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\sku2.jpg"));
		this.click(sku3Img);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\sku3.jpg"));
		this.click(sku4Img);
		autoitRun.uploadFile(baseData.getFilePath("testFiles\\sku4.jpg"));
	}
	
	@FindBy(tagName="body")
	private WebElement detail;//详情设置Iframe
	public void set_detail(WebDriver driver) {
		driver.switchTo().frame("ueditor_0");//切换到iframe
		this.JS(driver,detail, "arguments[0].innerHTML='<img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143933_85635.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143859_12798.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_97845.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_69006.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_57022.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_76989.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_23952.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_16858.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_55863.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143900_15661.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_46974.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_83624.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_18380.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_72495.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_75346.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_87938.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143901_13466.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_20422.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_80950.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_81575.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_95844.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_61659.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_24113.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_43496.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143902_14194.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143903_94570.jpg\" alt=\"\"/><img src=\"http://statics.76sd.com/data/files/image/20180706/20180706143903_91261.jpg\" alt=\"\"/>'");
		driver.switchTo().defaultContent();//回到主窗口
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[2]/button[1]")
	private WebElement submitToAudit;//提交审核
	public void click_submitToAudit() {
		this.click(submitToAudit);
	}

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[2]/button[2]")
	private WebElement saveDetailBTN;//保存为草稿按钮
	public void click_saveDetailBTN() {
		this.click(saveDetailBTN);
	}
}
