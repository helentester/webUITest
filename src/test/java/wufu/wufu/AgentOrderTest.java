/**
 * @author helen
 * @date 2018年9月13日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.MyConfig;
import data.AgentData;
import wufu.wufu.agentPages.AgentGoodInfoPage;

/**
 * @Description:健康家采购测试（支除健康家业务，弃用）
 */
public class AgentOrderTest {
	MyConfig myConfig = new MyConfig();
	AgentData agentData = new AgentData();
	LoginUITest loginUITest = new LoginUITest();
	
	/*健康家业务弃用*/
	@Test(enabled=false)
	public void test_order() {
		Reporter.log("健康家采购，下订单");
		String account = myConfig.getKeys("agent2");
		HashMap<String, String> orderExpect = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{
				put("orderAmount", "19999.00");
				put("codebox_display", "Y");
			}
			};
			
		assertEquals(this.order(account, "35", "sku1"), orderExpect);
	}
	
	/*下订单
	 * @account 健康家账号
	 * @goodId 商品ID
	 * @sku  可输入：sku1、sku2、sku3、sku4、sku5、sku6、sku7、sku8
	 * */
	public HashMap<String, String> order(String account,String goodId,String sku) {
		HashMap<String, String> orderDetail = new HashMap<String, String>();
		
		this.check_UserData(account);
		
		WebDriver driver = loginUITest.agentLogin(account, "123456li");
		try {
			String goodURL = myConfig.getKeys("DL_domainName")+"/#/product_list_detail/"+goodId;
			Thread.sleep(3000);
			driver.get(goodURL);
			
			//商品页面
			Thread.sleep(3000);
			AgentGoodInfoPage goodInfoPage = PageFactory.initElements(driver, AgentGoodInfoPage.class);
			if (sku.equals("sku1")) {
				goodInfoPage.click_sku1();
			}
			else if (sku.equals("sku2")) {
				goodInfoPage.click_sku2();
			}
			else if (sku.equals("sku3")) {
				goodInfoPage.click_sku3();
			}
			else if (sku.equals("sku4")) {
				goodInfoPage.click_sku4();
			}
			else if (sku.equals("sku5")) {
				goodInfoPage.click_sku5();
			}
			else if (sku.equals("sku6")) {
				goodInfoPage.click_sku6();
			}
			else if (sku.equals("sku7")) {
				goodInfoPage.click_sku7();
			}
			else if (sku.equals("sku8")) {
				goodInfoPage.click_sku8();
			}
			else {
				System.out.println("选择SKU失败");
			}
			goodInfoPage.click_buyNowBTN();//立即购买
			goodInfoPage.click_payBTN();//确认付款
			
			Thread.sleep(3000);
			orderDetail.put("orderAmount", goodInfoPage.get_orderAmount());
			orderDetail.put("codebox_display",goodInfoPage.codebox_display());
			
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
		
		return orderDetail;
	}
	
	/*检查健康家的数据环境
	 * @account 健康家账号
	 * */
	public void check_UserData(String account) {
		//如果默认收货地址不存在，新增一个
		if (agentData.healthyHomeAddrExist(account)==false) {
			String agentId = agentData.getAgentValue(account,"agent_id");
			agentData.insert_healthyAddr(agentId);
		}
	}

}
