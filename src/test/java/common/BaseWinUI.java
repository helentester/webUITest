/**
 * @author helen
 * @date 2018年6月21日
 */
package common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @Description:窗口界面操作
 */
public class BaseWinUI {
	MyConfig myConfig = new MyConfig();
	
	/*获取浏览器驱动*/
	public WebDriver getDrive() {
		String driverName = myConfig.getPropertyValue("browserName");
		WebDriver driver = null;
		if (driverName.equals("chrome")) {//加载chrome浏览器
			System.setProperty("webdriver.chrome.driver", "browserDriver\\chromedriver.exe"); 
			driver = new ChromeDriver();
		}
		else if (driverName.equals("firefox")) {//加载firefox浏览器
			System.setProperty("webdriver.gecko.driver", "browserDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (driverName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "browserDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().setSize(new Dimension(1920, 1280));//设置窗口分辨率
		//driver.manage().window().maximize();//窗口最大化1
		return driver;
	}
	
	/* 切换窗口操作 */
	public void changeWindow(WebDriver driver) {
		System.out.println(driver.getCurrentUrl());
		// 获取当前窗口的句柄
		String handle = driver.getWindowHandle();
		System.out.println("当前句："+handle.toString());
		// 获取所有页面的句柄，并循环判断不是当前的句柄
		for (String handles : driver.getWindowHandles()) {
			if (handles.equals(handle))
				continue;
			driver.switchTo().window(handles);
		}
	}

}
