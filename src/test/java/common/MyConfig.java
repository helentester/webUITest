/**
 * @author helen
 * @date 2018年8月2日
 */
package common;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @Description:读取配置文件中的数据
 */
public class MyConfig {
	static MyConfig configFile=new MyConfig();
	
/*	public static void main(String[] args){
		System.out.println(configFile.getPropertyValue("YY_domainName"));
	}
	*/
	/*获取配置文件*/
	public Properties LoadProperties(String fileName){
		BaseData baseData = new BaseData();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(baseData.getFilePath(fileName)));//取得文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/*根据key获取value*/
	public String getPropertyValue(String PropertyKey){
		Properties properties = configFile.LoadProperties("config.properties");
		return properties.getProperty(PropertyKey);
	}
	
	/*获取配置参数*/
	public String getKeys(String keys) {
		String testService = this.getPropertyValue("testService");
		Properties properties = null;
		if (testService.equals("236")) {
			properties = configFile.LoadProperties("236Data.properties");
		}
		else if (testService.equals("237")) {
			properties = configFile.LoadProperties("237Data.properties");
		}
		else {
			System.out.println("配置参数文件不存在");
		}
		
		return properties.getProperty(keys);
	}
}
