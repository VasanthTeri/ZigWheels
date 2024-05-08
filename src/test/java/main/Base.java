package main;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utility.ExcelUtility;


public class Base {

		 public WebDriver driver;
		 public static XSSFSheet S1;
		  @BeforeClass
		  @Parameters({"url","browser"})
		  	public void beforeClass(String URL,String browser) throws InterruptedException, IOException {
			 if(browser.equalsIgnoreCase("chrome"))
			 {
				 ChromeOptions option = new ChromeOptions();
					option.addArguments("--disable-blink-features=AutomationControlled");
					option.addArguments("--disable-notifications");
					 driver = new ChromeDriver(option);
					 ExcelUtility.excelInit();
			 }
			 else  
			 {
				 driver=new EdgeDriver(); 
			 }
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get(URL);
			 driver.manage().window().maximize();
			 driver.manage().deleteAllCookies();
		     }
		  public void just() throws Exception {
				 Thread.sleep(1500);
			 }

		  @AfterClass
		  public void afterClass() throws IOException {
			 ExcelUtility.closeExcel();
			  driver.quit();
		  }
	}


