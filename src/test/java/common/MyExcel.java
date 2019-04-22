/**
 * @author helen
 * @date 2018年8月27日
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @Description:读excel
 */
public class MyExcel {
	BaseData baseData = new BaseData();
	
	public static void main(String[] args) throws IOException {
		//MyExcel myExcel = new MyExcel();
		//myExcel.getData("src\\test\\java\\testFiles\\testData.xlsx","order");
	}
	
	/*读取excel文件中的数据，并生成数组*/
    @SuppressWarnings("deprecation")
    public Object[][] readExcel(String filePath,String sheetName){
        Object[][]    data =null;
        
        try {
        	File file = new File(baseData.getFilePath(filePath));//获取文件
            FileInputStream fileInputStream = new FileInputStream(file);//读数据
            
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);//读取指定标签页的数据
            int rowNum = sheet.getPhysicalNumberOfRows();//获取行数(获取的是物理行数，也就是不包括那些空行（隔行）的情况)
            int columNum = sheet.getRow(0).getPhysicalNumberOfCells();//获取列数
            
            data = new Object[rowNum-1][columNum];//因为第一行作为字段名，不需要记录，所以只有[rowNum-1]行
            for(int i=1;i<rowNum;i++) {//从第二行开始取值
                for (int h = 0; h < columNum; h++) {
                    sheet.getRow(i).getCell(h).setCellType(Cell.CELL_TYPE_STRING);//先把类型设置为string
                    data[i-1][h] = sheet.getRow(i).getCell(h).getStringCellValue();//填充数组
                }
            }
            workbook.close();
		} catch (Exception e) {
			System.out.println("读取excel数据失败");
			e.printStackTrace();
		}
        return data;
    }
    
/*    读取excel文件中的数据，并生成数组*/
    @SuppressWarnings("deprecation")
    public List<HashMap<String, String>> getData(String filePath,String sheetName) {
		List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
		
		try {
			File file = new File(baseData.getFilePath(filePath));//获取文件
            FileInputStream fileInputStream = new FileInputStream(file);//读数据
            
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);//读取指定标签页的数据
            int rowNum = sheet.getPhysicalNumberOfRows();//获取行数(获取的是物理行数，也就是不包括那些空行（隔行）的情况)
            int columNum = sheet.getRow(0).getPhysicalNumberOfCells();//获取列数
            
            for(int i=1;i<rowNum;i++) {//从第二行开始取值
                HashMap<String, String> oneRow = new HashMap<String, String>();
                for(int h=0;h<columNum;h++) {
                	sheet.getRow(i).getCell(h).setCellType(Cell.CELL_TYPE_STRING);//先把类型设置为string
                	oneRow.put(sheet.getRow(0).getCell(h).getStringCellValue(), sheet.getRow(i).getCell(h).getStringCellValue());//第一行的内容作为key,剩下的是value
                }
                data.add(oneRow);
            }
            
            workbook.close();
		} catch (Exception e) {
			System.out.println("excel读取数据失败");
			e.printStackTrace();
		}
		return data;
	}
}
