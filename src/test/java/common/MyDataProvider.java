/**
 * @author helen
 * @date 2018年8月27日
 */
package common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

/**
 * @Description:读取参数的类
 */
public class MyDataProvider {
	MyExcel myExcel = new MyExcel();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@DataProvider(name="order1")
	public Iterator<HashMap<String, String>> getOrderData(){
		
		
		/*Iterator<HashMap<String, String>> data = new Iterator<HashMap<String,String>>() {

			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			public HashMap<String, String> next() {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("a", "b");
				map.put("c", "d");
				return map;
			}
		};*/
		List<HashMap<String, String>> data = myExcel.getData("testFiles\\testData.xlsx","order");
		System.out.println(data.iterator().next());
		return data.iterator();
	}
	
	@DataProvider(name="order2")
	public Object[][] getOrder2Data(){
		Object[][] data = myExcel.readExcel("testFiles\\testData.xlsx","order");
		System.out.println(data);
		return data;
	}

}
