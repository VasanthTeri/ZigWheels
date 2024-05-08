package main;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import POM.HomePagePOM;
import Utility.Screenshot;

public class TestCases extends Base{
/*
 * Team-4
 * Teammates-Vasanth T, Keerthivarman S
 * Website- Zigwheels(https://www.zigwheels.com/)
 * Date Started:- 20/04/2024
 * Last_Modified_Date:-02/05/2024
 * Total_TestCase-12
 * Passed_TC-12
 * Failed_TC-00
 * 
 * TC_01-Author(Vasanth T)
 * To Enter invalid Login Credentials in Google login and capture the error message
 
 * TC_02-Author(Vasanth T)
 * Print the Top 3 Latest news in the console
 
 * TC_03-Author(Vasanth T)
 * Navigate to the Cars and Print the specifications of latest car
 
 * TC_04-Author(Vasanth T)
 * Print the UPcomming bikes under 4Lakhs
 
 *TC_05-Author(Vasanth T) 
 * Navigate to Best Scooty and print the Specifications
 
 * TC_06-Author(Vasanth T)
 * Navigate to Electric Bike and print the address
 * 
 * TC_07-Author(Keerthivarman S)
 * Navigate to Electric Car and print the total no.of Stations in Chennai

 * TC_08-Author(Keerthivarman S)
 * Navigate to used cars and print the details of the car
 * 
 * TC_09-Author(Keerthivarman S)
 * Print the monthly EMI of 2years
 * 
 * TC_10-Author(Keerthivarman S)
 * Print the Key Specifications of OLa S1 Pro
 * 
 * TC_11-Author(Keerthivarman S)
 * Save the Scooter Image in the Local repository
 * 
 * TC_12-Author(Keerthivarman S)
 * Print the names of the similar bikes 
 * 
 * 
 * */

	//Declaring Global Variable TO access 
	public static HomePagePOM hp;
	public static String path;
	public static JavascriptExecutor js;
	public static Actions act;
	
	//Passed 1,2,3,4,5,6,7,8,9,10,11,12
	
	//Author Vasanth T
	@Test(priority=1)
	public void Invalid_login_TC_01() throws IOException  {
		hp=new HomePagePOM(driver);
		hp.ClickLoginBtn();        
		hp.ClickgoogleBtn();	
		hp.InvalidCredentials();	
		path=Screenshot.screenshot(driver,"Invalid_Credential_TC_01");
		hp.ErrorMessage();
		boolean Logo=hp.Logo();
        Assert.assertEquals(Logo, true,"Not found");
    	System.out.println("TESTCASE NO:01 GOT PASSED\n");

		
	}
	
//	
//	//Author Vasanth T
	@Test(priority=2)
	public void Navigate_to_news_and_reviews_TC_02() throws IOException {
		 hp=new HomePagePOM(driver);

		hp.ClickNews();
		hp.PrintNews();
      	path=Screenshot.screenshot(driver,"Latest_News_TC_02");
		String act_title="Latest Car & Bike News - Automobile Industry News @ Zigwheels";
		String exp_title=driver.getTitle();
        Assert.assertEquals(act_title, exp_title);
		driver.navigate().back();
		System.out.println("TESTCASE NO:02 GOT PASSED\n");

	}
	
	
	
//	//Author Vasanth T
	@Test(priority=3)
	public void Navigate_to_new_launches_TC_03() throws Exception
	{
			hp=new HomePagePOM(driver);
			driver.navigate().refresh();
			hp.Launches();
			hp.printSpecifications();
	      	path=Screenshot.screenshot(driver,"Car_Specifications_TC_03");

			try {
				String Aston_act_title="Mahindra XUV 3XO Price, Images, colours, Reviews & Specs";
				String Aston_exp_title=driver.getTitle();
		        Assert.assertEquals(Aston_act_title, Aston_exp_title);

			}
				
			
			catch(Exception e2) {
			String Aston_act_title="Aston Martin Vantage Price, Images, colours, Reviews & Specs";
			String Aston_exp_title=driver.getTitle();
	        Assert.assertEquals(Aston_act_title, Aston_exp_title);
			}
			
			System.out.println("TESTCASE NO:03 GOT PASSED\n");
	
	}
//	
//	
	
//	//Author Vasanth T
	@Test(priority=4)
	 
	public void Navigate_to_new_bikes_TC_04() throws IOException 
		{
			HomePagePOM hp=new HomePagePOM(driver);
			hp.newbikes_tag();
			hp.select_upcomingbikes();
			hp.dropdown();
			hp.select_brand();
			hp.select_viewmorebikes_tag();
			hp.scrollup();
			hp.names();	
	      	path=Screenshot.screenshot(driver,"Honda_Bikes_TC_04");
			String url=driver.getCurrentUrl();
			Assert.assertEquals(url, "https://www.zigwheels.com/upcoming-honda-bikes");			
			System.out.println("TESTCASE NO:04 GOT PASSED\n");
	
		}
	
	
	
//	//Author Vasanth T
    @Test(priority=5)
	public void Navigate_to_best_scooter_TC_05() throws IOException{
		 hp=new HomePagePOM(driver);
		hp.SelectScooter();
		hp.SelectBestScooter();
		hp.printsummary();
      	path=Screenshot.screenshot(driver,"Activa_Summary_TC_05");

		String Scooty_act_title="Activa 6G STD";
		//String Scooty_exp_title=driver.getTitle();
        Assert.assertEquals(Scooty_act_title, hp.ScootyName());
    	System.out.println("TESTCASE NO:05 GOT PASSED\n");

    }
    
    
//  //Author Vasanth T
	@Test(priority=6)
	public void Navigate_to_electric_bike_TC_06() throws IOException 
	{
     hp = new HomePagePOM(driver);
     hp.hoverToElectricVehicles();
     hp.clickchennai();
     hp.printaddress(); 
   	path=Screenshot.screenshot(driver,"Address_Of_Electric_Bike_TC_06");

 	System.out.println("TESTCASE NO:06 GOT PASSED\n");

	}
	
	
//	//Author Keerthivarman S
	@Test(priority=7)
	public void Navigate_to_electric_car_TC_07() throws IOException 
	{
	     hp = new HomePagePOM(driver);
	   //  hp.hoverToElectricVehicles();
	     hp.ClickingElectricCar();
	     hp.ClickingChennai();
	     hp.PrintStations();
	     path=Screenshot.screenshot(driver,"No_Of_Electric_Car_TC_07");
	     Assert.assertEquals(hp.Station(), true);
	 	System.out.println("TESTCASE NO:07 GOT PASSED\n");

	}
	
	
	
//	//Author Keerthivarman S
	@Test(priority = 8)
	public void getusedList_TC_08() throws Exception{
		hp = new HomePagePOM(driver);
		hp.moveToUsedCars();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		hp.selectChennaiUsedCars();
		js.executeScript("scroll(0, 500)");
		hp.usedCars();
      	path=Screenshot.screenshot(driver,"Used_Cars_TC_08");

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.zigwheels.com/used-car/Chennai"); 
	 	System.out.println("\nTESTCASE NO:08 GOT PASSED\n");

	}
//	//Author Keerthivarman S
	@Test(priority = 9)
	public void EMI_Calculator_TC_09() throws Exception {
		hp = new HomePagePOM(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 
		hp.clickEmi();
		//js.executeScript(" window.scrollBy(0,250)", "");
		hp.clickMake();
		just();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		hp.ClickModel();
		just();

		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		hp.clickvarient();
		//just();

		hp.PrintMOnthlyEMI();
      	path=Screenshot.screenshot(driver,"Monthly_Emi_TC_09");
		System.out.println("TESTCASE NO:9 GOT PASSED\n");
		
 
	}
//	//Author Keerthivarman S
	@Test(priority = 10)
	public void Key_Specifications_TC_10() throws Exception {
		hp = new HomePagePOM(driver);

		hp.clickingOnSearchBox();
		Thread.sleep(2000);
 
		hp.cickResult();
		hp.printSpecs();
      	path=Screenshot.screenshot(driver,"Key_Specifications_TC_10");

	System.out.println("TESTCASE NO:10 GOT PASSED\n");
	}
//	//Author Keerthivarman S
	@Test(priority = 11)
	public void getImg_TC_11() throws Exception {
		hp = new HomePagePOM(driver);

		hp.getImg();
      	path=Screenshot.screenshot(driver,"Image_TC_11");

	System.out.println("TESTCASE NO:11 GOT PASSED\n");
	}
//	//Author Keerthivarman S
	@Test(priority = 12)
	public void getSimilarBikes_TC_12() throws InterruptedException, IOException {
		hp = new HomePagePOM(driver);
		hp.SimilarBikes();
      	path=Screenshot.screenshot(driver,"Similar_Bikes_TC_12");

	System.out.println("TESTCASE NO:12 GOT PASSED\n");
	}

	
//	
	
	 }

