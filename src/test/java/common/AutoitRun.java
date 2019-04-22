/**
 * @author helen
 * @date 2018年7月10日
 */
package common;

import java.io.IOException;

/**
 * @Description:执行autoit的.exe文件
 * 注意：
 * 1、首先要在本机安装autoit,安装文件下载地址：https://www.autoitscript.com/site/autoit/downloads/
 * 2、傻瓜式安装autoit;
 * 3、在SciTE Script Editor编辑器中编辑代码，如文件上传代码为：
WinActivate("文件上传");
ControlSetText("文件上传", "", "Edit1", $CmdLine[1] );//$CmdLine[1]为参数，此处为上传文件的路径
Sleep(2000);
ControlClick("文件上传", "", "Button1");
 * 4、保存文件，在SciTE Script Editor编辑器中Tool-run,可以执行文件，并验证文件的正确性
 * 5、在应用程序里面找到打开Compile Script to.exe工具，将刚才导出的.au3文件转化成.exe文件
 * 6、把相应的window窗口界面打开,比如在应用程序中点击打开上传文件的win窗口；
 * 7、在cmd中执行可验证
 */
public class AutoitRun {
	BaseData baseData = new BaseData();
	
	public static void main(String[] args) throws IOException {
		
		AutoitRun autoitRun = new AutoitRun();
		//autoitRun.runByCmd("E:\\uploadFile.exe","E:\\idCardFront.jpg");
		BaseData baseData = new BaseData();
		autoitRun.runByCmd(baseData.getFilePath("testFiles\\uploadFile.exe"), baseData.getFilePath("testFiles\\idCardReverse.jpg"));
	}
	
	/*上传文件操作
	 * @uploadFilePath	文件相对路径 	testFiles\\exportLicense.jpg
	 * */
	public void uploadFile(String uploadFilePath){
		try {
			this.runByCmd(baseData.getFilePath("testFiles\\uploadFile.exe"), uploadFilePath);
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*执行cmd命令*/
	public void runByCmd(String autoFilePath,String uploadFilePath){
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(autoFilePath+" \""+uploadFilePath+"\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
