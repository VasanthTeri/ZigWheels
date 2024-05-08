package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import POM.HomePagePOM;
import main.Base;
 
public class ExcelUtility extends Base{
/*	public void excelsheet(String tc) {
	try {
		File file2 =new File(System.getProperty("user.dir")+"\\output\\OutputData.xlsx");
        FileOutputStream file = new FileOutputStream(file2);
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet();
        XSSFRow r1 = sheet.createRow(0);
        XSSFRow r2 = sheet.createRow(1);
    //    HomePagePOM hp2 = new HomePagePOM(driver);

        r1.createCell(0).setCellValue("TestCase-01");
        r2.createCell(0).setCellValue(tc);
     
        System.out.println("Data entered into excel");
        workbook.write(file);
        workbook.close();
        file.close();
      } 
		catch (IOException e) {
    	  System.out.println(e.getMessage());
      }
	}
}

//
//
//r1.createCell(1).setCellValue("TestCase-02");
//r1.createCell(2).setCellValue("TestCase-03");
//r1.createCell(3).setCellValue("TestCase-04");
//r1.createCell(4).setCellValue("TestCase-05");
//r1.createCell(5).setCellValue("TestCase-06");
//r1.createCell(6).setCellValue("TestCase-07");
//r1.createCell(7).setCellValue("TestCase-08");
//r1.createCell(8).setCellValue("TestCase-09");
//
//r2.createCell(2).setCellValue(alert2);
//r2.createCell(3).setCellValue(title);
//
//,String TC_02,String Tc_03,String TC_04,String TC_05,String Tc_06,String TC_07,String TC_08,String Tc_09
 */
	
	public static File file2;
	public static FileOutputStream file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow r1;
 
	public static void excelInit() throws FileNotFoundException {
		file2 = new File(System.getProperty("user.dir") + "\\output\\OutputData1.xlsx");
		file = new FileOutputStream(file2);
		workbook = new XSSFWorkbook();
 
	}
 
	public static XSSFSheet CreateSheet(String name) {
		sheet = workbook.createSheet(name);
		return sheet;
	}
 
	public static XSSFRow createRow(int j, XSSFSheet sheet1) {
		return sheet1.createRow(j);
	}
	
	public static void setData(XSSFRow r1, int j, String data) {
		r1.createCell(j).setCellValue(data);
	}
	public static void setDataForNews(List<WebElement> li,XSSFSheet K1)
	{
		for(int i=1;i<=3;i++)
		{
			XSSFRow r1=createRow(i,K1);
			setData(r1,0,li.get(i).getText());
		}
	}
	
	public static void setDataUsedNews(List<WebElement> li,XSSFSheet S8)
	{
		for(int i=1;i<=3;i++)
		{
			XSSFRow r1=createRow(i,S8);
			setData(r1,0,li.get(i).getText());
		}
	}
	public static void setDataSimilarNews(List<WebElement> li,XSSFSheet S12)
	{
		for(int i=1;i<=3;i++)
		{
			XSSFRow r1=createRow(i,S12);
			setData(r1,0,li.get(i).getText());
		}
	}
	public static void closeExcel() throws IOException {
		workbook.write(file);
		workbook.close();
		file.close();
	}	
}	