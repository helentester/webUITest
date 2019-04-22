/**
 * @author helen
 * @date 2018年6月21日
 */
package common;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Description:页面处理公共类，主要用于设置页面元素等待
 */
@SuppressWarnings("rawtypes")
public class BasePage {
	
	WebDriver driver;
	private final int timeOut = 10;//等待时间

	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	/* 重写senkeys方法 */
	public void sendkeys(WebElement element, String s) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));// 加入显式等待:判断元素(定位后)是否可见
		element.clear();// 先清空输入框
		element.sendKeys(s);// 输入数据
	}
	
	/* 键盘事件 */
	public void Keyboard_event(WebElement element,Keys key) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));// 加入显式等待:判断元素(定位后)是否可见
		element.sendKeys(key);
	}

	/* 重写click方法 */
	public void click(WebElement element) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.elementToBeClickable(element));// 加入显式等待:判断元素是否可以点击
		element.click();
	}
	
	/*重写获取对象方法*/
	public WebElement findMyElement(WebElement element) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/*重写获取一组对象的方法*/
	public List<WebElement> findMyElements(List<WebElement> elements) {
		Iterator iterator = elements.iterator();
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf( (WebElement) iterator.next()));//只等待第一个元素即可
		return (List<WebElement>) elements;
	}
	
	/*select下拉框选择*/
	public void selectValue(WebElement element,String s,String byType) {
		if(byType.equals("ByVisibleText")) {
			new Select(this.findMyElement(element)).selectByVisibleText(s);	
		}
		else if (byType.equals("ByValue")) {
			new Select(this.findMyElement(element)).selectByValue(s);	
		}
		else if (byType.equals("ByIndex")) {
			new Select(this.findMyElement(element)).selectByIndex(Integer.parseInt(s));	
		}	
	}
	
	/*执行JS
	 * 点击对象	this.JS(driver, element, "arguments[0].click();");
	 * 删除属性	this.JS(driver, element, "arguments[0].removeAttribute('readonly');");
	 * 设置属性值	this.JS(driver, element, "arguments[0].value=\"2016-08-20\"");
	 * */
	public void JS(WebDriver webDriver,WebElement element,String JS) {
		WebElement myElement = this.findMyElement(element);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript(JS, myElement);
	
}

	/*处理alert弹出框
	 * @alertBTN  alert的确认、取消按钮
	 * */
	public void alertSubmit(WebDriver driver,String alertBTN) {
		Alert alert = driver.switchTo().alert();
		if (alertBTN.equals("确定")) {
			alert.accept();
		}
		else if (alertBTN.equals("取消")) {
			alert.dismiss();
		}
		else {
			System.out.println("alert事件处理失败");
		}
	}
}
