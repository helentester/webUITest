/**
 * @author helen
 * @date 2018年6月22日
 */
package wufu.wufu;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wufu.wufu.sysPages.SysIndexPage;

/**
 * @Description:运营中心的菜单检查
 */
public class SysIndexUITest {
	LoginUITest loginUITest = new LoginUITest();
	WebDriver driver;
	SysIndexPage sysIndexPage;
	List<String> subMenuList;
	List<String> targetSubList = new ArrayList<String>();
	
	@BeforeClass
	public void loginSys() {
		driver = loginUITest.sysLogin("helen","123456li");
		sysIndexPage = PageFactory.initElements(driver, SysIndexPage.class);
	}
	
	@BeforeMethod(description="清空targetSubList")
	public void clearList() {
		targetSubList.clear();
	}

	@Test(priority=1)
	public void test_MainMenu() {
		Reporter.log("检查一级菜单");
		List<String> MainMenuList = sysIndexPage.getMainMenuList();
		List<String> targetMainList = new ArrayList<String>();
		targetMainList.add("首页");
		targetMainList.add("站点管理");
		targetMainList.add("会员管理");
		targetMainList.add("代理管理");//原[代理商管理]20181206改
		targetMainList.add("信用卡业务");//20181212新加
		targetMainList.add("商家管理");
		targetMainList.add("商品管理");
		targetMainList.add("康养卡业务");//20190329 新增 3.3版本
		targetMainList.add("康养公寓业务");//20190415新增3.4版本
		targetMainList.add("营销活动");//2019.1.2新增
		targetMainList.add("订单管理");
		targetMainList.add("评论管理");//20180831改，原：评论系统
		targetMainList.add("优惠券管理");
		targetMainList.add("健康家");//20180904改，原：健康家采购
		targetMainList.add("签到管理");//20181026新增
		targetMainList.add("线下支付");//20180831改，原：线下支付管理
		targetMainList.add("财务管理");
		targetMainList.add("数据中心");//2018年8月30日新加
		targetMainList.add("权限管理");
		targetMainList.add("");
		targetMainList.add("系统管理");
		targetMainList.add("用户反馈");//20181026新增
		targetMainList.add("");
		/*20180831去除下面两个菜单
		targetMainList.add("礼品管理");
		targetMainList.add("字典管理");*/
		assertEquals(MainMenuList.toString(), targetMainList.toString(),"主菜单检查");
	}
	
	@Test(priority=2)
	public void test_websiteManage() {
		Reporter.log("站点管理子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("站点管理");
		targetSubList.add("文章分类");//20180831改，原:文章分类管理
		targetSubList.add("文章管理");
		targetSubList.add("帮助文档管理");//20190312新增
		targetSubList.add("热搜词管理");//20180904改，原：搜索热词管理
		targetSubList.add("App闪屏管理");//20190118新增
		targetSubList.add("广告设置");//20190118新增
		targetSubList.add("首页广告组件");//20180831改，原：首页广告组件管理
		targetSubList.add("首页栏目位管理");
		targetSubList.add("首页推广栏目内页");//20180831n改，原：首页推广栏目内页管理
		targetSubList.add("首页轮播图");
		targetSubList.add("首页浮窗管理");//20181115新增
		targetSubList.add("APP消息推送");//20180831改，原：消息推送
		targetSubList.add("代理/商家通知");
		targetSubList.add("推荐商品设置");//20190118新增
		assertEquals(subMenuList.toString(), targetSubList.toString(),"站点管理子菜单检查");
	}
	
	@Test(priority=3)
	public void test_memberManage() {
		Reporter.log("会员管理子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("会员管理");
		targetSubList.add("会员列表");
		targetSubList.add("会员回收站"); //20180918加入，20180904去除
		targetSubList.add("报表管理");//2018/08/30新加
		targetSubList.add("会员标签");//20181115新增
		targetSubList.add("实名认证列表");//20181212新增
		assertEquals(subMenuList.toString(), targetSubList.toString(),"会员管理子菜单检查");
	}
	
	@Test(priority=4)
	public void test_agentManage() {
		Reporter.log("代理管理子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("代理管理");
		targetSubList.add("健康家列表");
		targetSubList.add("商城代理列表");//原[个人代理商列表]20181205改
		targetSubList.add("商城代理审核");//原[代理商修改审核]20181205改
		targetSubList.add("代理修改审核");//原[新代理商审核]20181205改
		targetSubList.add("健康家代理审核");//原[健康家审核]20181205改
		targetSubList.add("信用卡代理列表");//20181205新加
		targetSubList.add("康养卡代理列表");//20181205新加
		assertEquals(subMenuList.toString(), targetSubList.toString(),"代理商管理子单检查：");
	}
	
	@Test(priority=5)
	public void test_creditCardManage() {
		Reporter.log("信用卡业务子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("信用卡业务");
	}
	
	@Test(priority=6)
	public void test_storeManage() {
		Reporter.log("商家管理子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("商家管理");
		targetSubList.add("商家列表");
		targetSubList.add("商家入驻审核");
		targetSubList.add("商家修改审核");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"商家管理子菜单检查：");
	}
	
	@Test(priority=7)
	public void test_goodsManage() {
		Reporter.log("商品管理子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("商品管理");
		targetSubList.add("商品列表");
		targetSubList.add("品牌管理");
		targetSubList.add("商品分类");//20180831改，原：商品分类列表
		targetSubList.add("商品标签");//20180831改，原：商品标签列表
		targetSubList.add("商品ATTR");//20180831改，原：商品ATTR列表
		//targetSubList.add("商品信息变更记录");//20180904新增
		assertEquals(subMenuList.toString(), targetSubList.toString(),"商品管理子菜单检查：");
	}
	
	@Test(priority=7)
	public void test_healthCardManage() {//20190329加入
		Reporter.log("康养卡业务子菜单管理");
		subMenuList = sysIndexPage.getSubMenuList("康养卡业务");
		targetSubList.add("康养卡列表");
		targetSubList.add("康养卡购买列表");
		targetSubList.add("康养卡升级列表");
		targetSubList.add("康养卡转赠列表");
		targetSubList.add("康养卡赠送配置");
		targetSubList.add("康养卡分润列表");
		targetSubList.add("体检机构列表");
		targetSubList.add("体检次数列表");
		targetSubList.add("体检预约列表");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"康养卡业务子菜单检查：");
	}
	
	@Test(priority=7)
	public void test_marketingActivities() {//2019.1.2新加
		Reporter.log("营销活动子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("营销活动");
		targetSubList.add("满折满减列表");//20190312新增
		targetSubList.add("闪购列表");
		targetSubList.add("赠送抽奖设置");//20190312新增
		assertEquals(subMenuList.toString(), targetSubList.toString(),"营销活动子菜单检查");
	}
	
	@Test(priority=8)
	public void test_orderManage() {
		Reporter.log("订单管理子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("订单管理");
		targetSubList.add("订单列表");
		targetSubList.add("发货单管理");
		targetSubList.add("退货退款管理");//20180831改，原：退货/退款单
		assertEquals(subMenuList.toString(), targetSubList.toString(),"订单管理子菜单检查");
	}
	
	@Test(priority=9)
	public void test_commentSYS() {
		Reporter.log("评论管理子菜单");
		subMenuList = sysIndexPage.getSubMenuList("评论管理");
		targetSubList.add("评论列表");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"评论管理子菜单检查");
	}
	
	@Test(priority=10)
	public void test_giftManage() {
		Reporter.log("优惠券管理子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("优惠券管理");
		targetSubList.add("优惠券列表");
		targetSubList.add("查看优惠券");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"优惠券管理子菜单检查");
	}
	
	@Test(priority=11)
	public void test_healthHome() {
		Reporter.log("健康家子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("健康家");
		targetSubList.add("采购商品分类");
		targetSubList.add("采购商品列表");
		targetSubList.add("采购订单管理");
		targetSubList.add("采购发货单");
/*		targetSubList.add("会员订单列表");//20180904新增
		targetSubList.add("健康家020店");//20180904新增
		targetSubList.add("会员分润列表");//20180904新增
*/		assertEquals(subMenuList.toString(), targetSubList.toString(),"健康家子菜单检查,实际输出：");
	}
	
	@Test(priority=12)
	public void test_signManager() {
		//20181026新增
		Reporter.log("签到管理");
		subMenuList = sysIndexPage.getSubMenuList("签到管理");
		targetSubList.add("签到记录");
		targetSubList.add("签到百日中奖统计");
		targetSubList.add("奖品设置");
		targetSubList.add("中奖列表");
		targetSubList.add("中奖统计");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"签到管理子菜单检查，实际输出：");
	}
	
	@Test(priority=13)
	public void test_payManage() {
		Reporter.log("线下支付子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("线下支付");
		targetSubList.add("支付单号管理");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"线下支付子菜单检查");
	}
	
	@Test(priority=14)
	public void test_finaceManage() {
		Reporter.log("财务管理子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("财务管理");
		//targetSubList.add("商家、代理商申请提现列表");20190220去除
		targetSubList.add("会员提现管理");//20180831改，原：会员申请提现列表
		targetSubList.add("商家提现管理");//20190220新增
		targetSubList.add("代理提现管理");//20190220新增
		targetSubList.add("会员账户信息");
		targetSubList.add("商家账户信息");//20190220新增
		targetSubList.add("代理账户信息");//2019.1.2
		targetSubList.add("订单分润列表");//20180929新增，2.60版本中添加；20190220改，原：会员分润列表
		assertEquals(subMenuList.toString(), targetSubList.toString(),"财务管理子菜单检查,实际输出：");
	}
	
	@Test(priority=15)
	public void test_dataCenter() {//20180830新加
		Reporter.log("数据中心子菜单检查");
		subMenuList = sysIndexPage.getSubMenuList("数据中心");
		targetSubList.add("关键词搜索");
		targetSubList.add("商品购买排行");
		targetSubList.add("每日购买人数");
		targetSubList.add("每日销售数据");//20181026新增
		targetSubList.add("商品转化率统计");//20181026新增
		targetSubList.add("商品跳出率统计");//20181026新增
		targetSubList.add("数据概览");//20181115新增
		assertEquals(subMenuList.toString(), targetSubList.toString(),"数据中心子菜单检查");
	}
	
	@Test(priority=16)
	public void test_authorityManage() {
		Reporter.log("权限管理子菜单检查");
		
		subMenuList = sysIndexPage.getSubMenuList("权限管理");
		targetSubList.add("角色管理");
		targetSubList.add("账号管理");
		targetSubList.add("权限管理");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"权限管理子菜单检查");
	}

	@Test(priority=17)
	public void test_sysManage() {
		Reporter.log("系统管理的子菜单列表");
		
		subMenuList = sysIndexPage.getSubMenuList("系统管理");
		targetSubList.add("系统日志");
		targetSubList.add("APP版本升级");//20180831改，原：版本升级管理
		targetSubList.add("字典列表");//20180831加入
		assertEquals(subMenuList.toString(), targetSubList.toString(),"系统管理子菜单检查");
	}
	
	@Test(priority=18)
	public void test_feedbackManage() {
		Reporter.log("用户反馈的子菜单列表");
		subMenuList = sysIndexPage.getSubMenuList("用户反馈");
		targetSubList.add("用户反馈列表");
		assertEquals(subMenuList.toString(), targetSubList.toString(),"用户反馈子菜单检查，实际输出：");
	}
	
	@AfterClass
	public void browserQuit() {
		driver.quit();
	}
}
