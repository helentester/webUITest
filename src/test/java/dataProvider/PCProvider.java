/**
 * @author helen
 * @date 2018年9月25日
 */
package dataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import common.BaseData;
import common.MyExcel;
import dataModel.SeveralGoodsShareModel;

/**
 * @Description:PC商城变量
 */
public class PCProvider {
	MyExcel myExcel = new MyExcel();
	BaseData baseData = new BaseData();
	String filePath = baseData.getFilePath("ExcelData\\testdata.xlsx");
	
	@DataProvider(name="profitMoney")
	public Object[][] get_profitMoney(){
		return myExcel.readExcel(filePath, "profitMoney");
	}
	
	@DataProvider(name="profitScore")
	public Object[][] get_profitScore(){
		return myExcel.readExcel(filePath, "profitScore");
	}
	
	@DataProvider(name="agentGrade")
	public Object[][] get_agentGrade(){
		return myExcel.readExcel(filePath, "agentGrade");
	}
	
	@DataProvider(name="memberGrade")
	public Object[][] get_memberGrade(){
		return myExcel.readExcel(filePath, "memberGrade");
	}
	
	@DataProvider(name="sellerType")
	public Object[][] get_sellerType(){
		return myExcel.readExcel(filePath, "sellerType");
	}
	
	@DataProvider(name="oneGoodsShare")
	public Object[][] get_oneGoodsShare(){
		return myExcel.readExcel(filePath, "oneGoodsShare");
	}
	
	@DataProvider(name="severalGoodsShare")
	public Iterator<Object[]> get_severalGoodsShare(){
		List<Object> item = new ArrayList<Object>();
		Object[][] excelData = myExcel.readExcel(filePath, "severalGoodsShare");
		for (int i = 0; i < excelData.length; i++) {
			SeveralGoodsShareModel shareModel = new SeveralGoodsShareModel();
			shareModel.setTestCaseNB(String.valueOf(excelData[i][0]));
			shareModel.setCoupon(String.valueOf(excelData[i][1]));
			shareModel.setBean(String.valueOf(excelData[i][2]));
			shareModel.setG1c(String.valueOf(excelData[i][3]));
			shareModel.setG1b(String.valueOf(excelData[i][4]));
			shareModel.setG1m(String.valueOf(excelData[i][5]));
			shareModel.setG2c(String.valueOf(excelData[i][6]));
			shareModel.setG2b(String.valueOf(excelData[i][7]));
			shareModel.setG2m(String.valueOf(excelData[i][8]));
			shareModel.setG3c(String.valueOf(excelData[i][9]));
			shareModel.setG3b(String.valueOf(excelData[i][10]));
			shareModel.setG3m(String.valueOf(excelData[i][11]));
			shareModel.setG4c(String.valueOf(excelData[i][12]));
			shareModel.setG4b(String.valueOf(excelData[i][13]));
			shareModel.setG4m(String.valueOf(excelData[i][14]));
			shareModel.setG5c(String.valueOf(excelData[i][15]));
			shareModel.setG5b(String.valueOf(excelData[i][16]));
			shareModel.setG5m(String.valueOf(excelData[i][17]));
			shareModel.setG6c(String.valueOf(excelData[i][18]));
			shareModel.setG6b(String.valueOf(excelData[i][19]));
			shareModel.setG6m(String.valueOf(excelData[i][20]));
			shareModel.setG7c(String.valueOf(excelData[i][21]));
			shareModel.setG7b(String.valueOf(excelData[i][22]));
			shareModel.setG7m(String.valueOf(excelData[i][23]));
			shareModel.setG8c(String.valueOf(excelData[i][24]));
			shareModel.setG8b(String.valueOf(excelData[i][25]));
			shareModel.setG8m(String.valueOf(excelData[i][26]));
			shareModel.setG9c(String.valueOf(excelData[i][27]));
			shareModel.setG9b(String.valueOf(excelData[i][28]));
			shareModel.setG9m(String.valueOf(excelData[i][29]));
			item.add(shareModel);
		}
		
		
		List<Object[]> severalGoodsShare = new ArrayList<Object[]>();
		for (Object u : item) {
            //做一个形式转换
			severalGoodsShare.add(new Object[] { u });
        }
		
		return severalGoodsShare.iterator();
	}

}
