/**
 * @author helen
 * @date 2018年7月11日
 */
package wufu.wufu.sellerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import common.BasePage;

/**
 * @Description:商家管理后台：商家注册流程中的“经营类目”页面
 */
public class SellerRegistBusinessCategoryPage extends BasePage{

	public SellerRegistBusinessCategoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(className="top-username")
	private WebElement topUsername;//版头的注册手机号
	public String get_topUsername() {
		return this.findMyElement(topUsername).getText();
	}
	
	@FindBy(xpath="/html/body/div/div/div[3]/button")
	private WebElement nextBTN;//下一步按钮
	public void click_nextBTN() {
		this.click(nextBTN);
	}
	
	/*经营类目
	 * 	20190408运营中心v3.3版优化，经营类目＝商品一级分类，只有以下三个分类有资质上传
	 * 	食品酒水：食品生产许可证、食品经营许可证
		美妆个护：全国工业产品生产许可证
		家电汽车：3C认证书
	 * */
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[1]/span")
	private WebElement beautyMakeup;//美妆个护
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[5]/span")
	private WebElement clothing;//服装配饰 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[7]/span")
	private WebElement electric;//家电汽车
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[13]/span")
	private WebElement food;//食品酒水
	public void select_category(String categoryName) {//选择经营类目
		if(categoryName.equals("美妆个护")) {
			this.click(beautyMakeup);
		}
		else if (categoryName.equals("服装配饰")) {
			this.click(clothing);
		}
		else if (categoryName.equals("家电汽车")) {
			this.click(electric);
		}
		else if (categoryName.equals("食品酒水")) {
			this.click(food);
		}
	}
	
	
	
	/*@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[1]/span")
	private WebElement category_visa_agent;//签证代理 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[2]/span")
	private WebElement category_clothing_accessory;//服装配饰 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[3]/span")
	private WebElement category_daily_life;//日常生活 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[4]/span")
	private WebElement category_exercise_outdoors;//运动户外 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[5]/span")
	private WebElement category_winnow_food;//精选食品 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[6]/span")
	private WebElement category_rest_home;//养老院 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[7]/span")
	private WebElement category_hotel;//酒店 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[8]/span")
	private WebElement category_ticket;//门票 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[9]/span")
	private WebElement category_travel_route;//旅游路线 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[10]/span")
	private WebElement category_digital_office;//数码办公 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[11]/span")
	private WebElement category_household_appliances;//家用电器 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[12]/span")
	private WebElement category_house;//家居 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[13]/span")
	private WebElement category_health_product;//保健品 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[14]/span")
	private WebElement category_entertainment;//休闲娱乐 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[15]/span")
	private WebElement category_cars;//汽车 
	@FindBy(xpath="/html/body/div/div/div[3]/form/div/div/div/label[16]/span")
	private WebElement category_medical;//医疗 
	
	public void select_category(String categoryName) {//选择经营类目
		if(categoryName.equals("签证代理")) {
			System.out.println(categoryName);
			this.click(category_visa_agent);
		}
		else if (categoryName.equals("服装配饰")) {
			this.click(category_clothing_accessory);
		}
		else if (categoryName.equals("日常生活")) {
			this.click(category_daily_life);
		}
		else if (categoryName.equals("运动户外")) {
			this.click(category_exercise_outdoors);
		}
		else if (categoryName.equals("精选食品")) {
			this.click(category_winnow_food);
		}
		else if (categoryName.equals("养老院")) {
			this.click(category_rest_home);
		}
		else if (categoryName.equals("酒店")) {
			this.click(category_hotel);
		}
		else if (categoryName.equals("门票")) {
			this.click(category_ticket);
		}
		else if (categoryName.equals("旅游线路")) {
			this.click(category_travel_route);
		}
		else if (categoryName.equals("数码办公")) {
			this.click(category_digital_office);
		}
		else if (categoryName.equals("家用电器")) {
			this.click(category_household_appliances);
		}
		else if (categoryName.equals("家居")) {
			this.click(category_house);
		}
		else if (categoryName.equals("保健品")) {
			this.click(category_health_product);
		}
		else if (categoryName.equals("休闲娱乐")) {
			this.click(category_entertainment);
		}
		else if (categoryName.equals("汽车")) {
			this.click(category_cars);
		}
		else if (categoryName.equals("医疗")) {
			this.click(category_medical);
		}
		else {
			System.out.println("没有选择经营类目");
		}
	}*/

}
