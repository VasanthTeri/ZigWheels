package POM;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Utility.ExcelUtility;

public class HomePagePOM 
{
	public  WebDriver driver;
	public static JavascriptExecutor js;
	public  Actions act;
	public SoftAssert softAssert;
	public static WebDriverWait mywait;
	public static ExcelUtility excel;
	public static String path;
	public static XSSFSheet S1;
	public static XSSFRow R1;


	public HomePagePOM(WebDriver driver) 
	{
		this.driver=driver;
		js=(JavascriptExecutor)driver;
		act=new Actions(driver);
		softAssert = new SoftAssert();
		excel=new ExcelUtility();
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	//Locaters
	//ForTESTCASE1
		@FindBy(xpath="//*[@id='forum_login_wrap_lg']")
		WebElement LoginBtn;
		@FindBy(xpath="//*[@id='myModal3-modal-content']/div[1]/div/div[3]/div[6]/div")
		WebElement google;
		@FindBy(id="identifierId")
		WebElement input;
		@FindBy(id="ca")
		WebElement input2;
		@FindBy(xpath="//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div[1]/form/span/section/div/div/div[4]/div[2]/div/div[2]/div[2]/div")
		WebElement emsg2;
		@FindBy(xpath="//*[@id=\"identifierNext\"]/div/button/span")
		WebElement NextBtn;
		@FindBy(xpath= "//img[@data-track-label='zw-header-logo']")
		WebElement zigWheelsLogo;
		@FindBy(xpath="//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div[1]/form/span/section/div/div/div[1]/div/div[2]/div[2]/div")
		WebElement emsg;
		@FindBy(xpath="//*[@id='report_submit_close_login']")
		WebElement Cancelbtn;
		public void ClickLoginBtn()
		{
		    mywait.until(ExpectedConditions.visibilityOf(LoginBtn));
			LoginBtn.click();
					//Validating Login Button Is Visible Or not Not
		    boolean login=LoginBtn.isDisplayed();
		    Assert.assertEquals(login, true,"Login Button is not Visible");
		}
		public void ClickgoogleBtn() 
		{
			//String url=driver.getCurrentUrl();
			try {
		    mywait.until(ExpectedConditions.visibilityOf(google));
		    		//Validating Whether Google button is Visible or not
		    Assert.assertEquals(google.isDisplayed(), true);
			js.executeScript("arguments[0].click();", google);
			}
			catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
			
		}
		
		public boolean  Logo() {
			return zigWheelsLogo.isDisplayed();
		}
		public void InvalidCredentials() throws IOException
		{
			Set <String> Handles=driver.getWindowHandles();
			List <String> Id=new ArrayList<>(Handles);
				//WindowHandles are Stored in a List[Id]
			
			driver.switchTo().window(Id.getLast());
			mywait.until(ExpectedConditions.visibilityOf(input));
			Assert.assertEquals(input.isDisplayed(), true,"Google Login Is not Visible");
				//Passing the String into the Email Box
		    act.sendKeys(input, "fhdszkjk@gmaii.com").perform();
			Assert.assertEquals(NextBtn.isDisplayed(), true,"Next Button is not Clickable");
				//Clicking the Next Button
			js.executeScript("arguments[0].click();", NextBtn);

		}
		public String excelerror() {
			return emsg.getText();
		}
		public void ErrorMessage() {
			try {
				
			System.out.println(emsg.getText());
			String errormsg=emsg.getText();
			//print the data into excel 
			 S1=ExcelUtility.CreateSheet("Sheet1");

		  R1=ExcelUtility.createRow(0,S1);
		  ExcelUtility.setData(R1, 0, "TestCase-01");
		  R1=ExcelUtility.createRow(1,S1);
		  ExcelUtility.setData(R1, 0, errormsg);
			}
			catch(Exception e){
				//Capcha is Required For Sometimes
				act.sendKeys(input2, "capcha").perform();
				Assert.assertEquals(NextBtn.isDisplayed(), true,"Next Button is not Clickable");
				js.executeScript("arguments[0].click();", NextBtn);
				System.out.println(emsg2.getText());

			}
				
			driver.close();
			Set <String> Handles=driver.getWindowHandles();
			List <String> Id=new ArrayList<>(Handles);
			//Getting the Handles for closing the windows
			driver.switchTo().window(Id.getLast());
			mywait.until(ExpectedConditions.visibilityOf(Cancelbtn));
			js.executeScript("arguments[0].click();", Cancelbtn);

		}

	//ForTESTCASE2	

	@FindBy(xpath="//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[1]/a")
	WebElement News;
	@FindBy(xpath="//*[@class=\"clr-bl b fnt-16 pt-10 pl-0 pr-0\"]")
	List<WebElement> ListNews;
	@FindBy(xpath="//*[@id='newsSearch']")
	WebElement LatestScroll;
	//ForTESTCASE2	
	public void ClickNews()	{
		
		News.click();
		js.executeScript("arguments[0].scrollIntoView();", LatestScroll);
		Assert.assertEquals(LatestScroll.isDisplayed(), true,"Not IN News Page");
	}
	public void PrintNews() throws IOException {
		for(int i=0;i<=2;i++)
		{
			System.out.println(ListNews.get(i).getText()+"\n");
		}
		
		 XSSFSheet S2=ExcelUtility.CreateSheet("TC-02");
		  R1=ExcelUtility.createRow(0,S2);
		ExcelUtility.setData(R1, 0, "TestCase-02");
		ExcelUtility.setDataForNews(ListNews, S2);

	}
	//ForTESTCASE3	

	@FindBy(xpath="//a[text()='New Cars']")
	WebElement Cars;
	@FindBy(xpath="//*[@id='main-tabs']/li[2]")
	WebElement LatestBtn;
	@FindBy(xpath="//*[@id='main-tabs']/li[2]")
	WebElement ScrollIntoLatest;
	@FindBy(xpath="//img[@title='Aston Martin Vantage']")
	WebElement AstonImg;
	@FindBy(xpath="//*[text()='Aston Martin Vantage Specifications']")
	WebElement AstonScroll;	
	@FindBy(xpath="(//img[@title='Mahindra XUV 3XO'])[1]")
	WebElement Mahindra;
	@FindBy(xpath="(//*[text()='Mahindra XUV 3XO'])[2]")
	WebElement MahindraTextValidation;
	@FindBy(xpath="//*[text()='Mahindra XUV 3XO Specifications']")
	WebElement MahindraSpecsScroll;
	@FindBy(xpath="//*[@id=\"seoContSpecs\"]/div[1]")
	WebElement MahindraSpecs;
	@FindBy(xpath="//*[@id='modelList']/li[1]/div/div[2]/a")
	WebElement ClickOnRoadPrice;
	@FindBy(xpath="//*[@id=\"mNav\"]/nav/div/ul/li[6]/a")
	WebElement ClickSpecs;
	@FindBy(xpath="//*[text()='Aston Martin Vantage Specifications']")
	WebElement AstonSpecsValidation;
	@FindBy(xpath="//*[@id='seoContSpecs']/div")
	WebElement specifications;
	//ForTESTCASE3	
		public WebElement ElementAston() {
			return AstonScroll;
		}
		public void Launches() {
			
			Assert.assertEquals(Cars.isDisplayed(), true,"Not in home page");

			js.executeScript("arguments[0].click();", Cars);
			Assert.assertEquals(LatestBtn.isDisplayed(), true,"Not IN New Cars Page");
			js.executeScript("arguments[0].click();", LatestBtn);
			
			try {			
				js.executeScript("arguments[0].click();", Mahindra);
			mywait.until(ExpectedConditions.visibilityOf(MahindraTextValidation));
			Assert.assertEquals(MahindraTextValidation.isDisplayed(), true, "Mahindra text not found");
			js.executeScript("arguments[0].scrollIntoView();", MahindraSpecsScroll);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());			}
			}
		public void printSpecifications() throws IOException {	
			try {
				Assert.assertEquals(MahindraSpecs.isDisplayed(), true, "Maindra specifications not found");
				System.out.println(MahindraSpecs.getText());
				XSSFSheet S3=ExcelUtility.CreateSheet("TC-03");
				  R1=ExcelUtility.createRow(0,S3);
				  ExcelUtility.setData(R1, 0, "TestCase-03");
				  R1=ExcelUtility.createRow(1,S3);
				  ExcelUtility.setData(R1, 0, MahindraSpecs.getText());
			}
			
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			}
	//ForTESTCASE4
	@FindBy(xpath="//img[@alt='ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, QnA']")
	WebElement img;
	@FindBy(xpath="//a[normalize-space()='New Bikes']")
	WebElement new_bikes_tag;
	@FindBy(xpath="//span[contains(text(),'Upcoming Bikes')]")
	WebElement upcoming_bikes;
	@FindBy(id="makeId")
	WebElement dropdown;
	@FindBy(xpath="//*[@id=\"carModels\"]/div[1]/h2")
	WebElement Alertme;
	@FindBy(xpath="//option[@value='53' and @data-url='honda']")
	WebElement dropdown_honda;
	//LIST COLLECTIONS
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']")       //CLICKING VIEW MORE BIKES
	WebElement view_more_button;
	@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']")     //LIST OF HONDA BIKES
	List <WebElement> Honda_bike_names_list;
	@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']")     //LIST OF HONDA BIKES
	WebElement Honda_bike_name;
	@FindBy(xpath="//div[contains(@title,'Ex-Showroom Price')] ")   //COST OF THE BIKES
	List <WebElement> cost_of_bikes;
	@FindBy(xpath="//div[contains(text(),'Launch')]")
	List <WebElement> launch_dates;
	//ForTESTCASE4
	public void newbikes_tag() 
	{
		try {
	Assert.assertEquals(new_bikes_tag.isDisplayed(), true,"Not in home page");

	 act.moveToElement(new_bikes_tag).perform();
	}
		catch(Exception e){
			driver.navigate().back();
			Assert.assertEquals(new_bikes_tag.isDisplayed(), true,"Not in home page");

			 act.moveToElement(new_bikes_tag).perform();
		}
	}
	public void select_upcomingbikes() 
	{
		js.executeScript("arguments[0].click();", upcoming_bikes);
		Assert.assertEquals(driver.getTitle(), "Upcoming Bikes in India - Check Price, Launch Date, Images and Latest News", "Not in Bike Page");

	}
	public void dropdown() 
	{
		Assert.assertEquals(dropdown.isDisplayed(), true, "DropDown is Not Visible");
	
		js.executeScript("arguments[0].click();", dropdown);
		//dropdown.click();
	}
	public void select_brand() 
	{
		try {
			mywait.until(ExpectedConditions.visibilityOf(dropdown));
			Select sc = new Select(dropdown);
			sc.selectByIndex(3);
			js.executeScript("window.scrollBy(0,5550)", "");
			js.executeScript("arguments[0].scrollIntoView();", view_more_button);

		}
		catch(Exception e){
			Select sc = new Select(dropdown);
			sc.selectByIndex(3);
			js.executeScript("window.scrollBy(0,5550)", "");
			js.executeScript("arguments[0].scrollIntoView();", view_more_button);

		}
	}

	public void select_viewmorebikes_tag()
	{
		mywait.until(ExpectedConditions.visibilityOf(view_more_button));
		Assert.assertEquals(view_more_button.isDisplayed(), true, "View More Button isnot visible");
		js.executeScript("arguments[0].click();", view_more_button);

	}
	public void scrollup()
	{
		js.executeScript("window.scrollBy(0,-5000)"," ");
	}
	public void names() throws IOException
	{
		int row=0;
		for(int i=0; i< cost_of_bikes.size(); i++)
		{
			String [] cost = cost_of_bikes.get(i).getText().split(" ");
			if(Double.parseDouble(cost[1].replace(",", "."))<=4.0) 
			{   
				System.out.println(Honda_bike_names_list.get(i).getText());
				System.out.println(cost_of_bikes.get(i).getText());
				System.out.println(launch_dates.get(i).getText()+"\n");
			}
			if(cost[1].contains(","))
			{
				if(Integer.parseInt(cost[1].replace(",", ""))<400000)
				{
					System.out.println(Honda_bike_names_list.get(i).getText());
					System.out.println(cost_of_bikes.get(i).getText());
					System.out.println(launch_dates.get(i).getText()+"\n");
				}
				}		
			}
		
		Assert.assertEquals(Honda_bike_name.isDisplayed(), true);


		}

	//ForTESTCASE5	
	
	@FindBy(xpath = "//a[normalize-space()='Scooters']")
	WebElement Scooter;
	@FindBy(xpath="(//a[@title='Best Scooters'])[2]")
	WebElement BestScooter;
	@FindBy(xpath="//*[@id='main-tabs']/li[1]")
	WebElement BestScooterScroll;
	@FindBy(xpath="//*[@id='carModels']/div[1]/h2")
	WebElement ScooterScroll;
	@FindBy(xpath="//*[@id=\"modelList\"]/li[1]/div/div[2]/a")
	WebElement Onroad;
	@FindBy(xpath="//*[@id=\"tab-container\"]/div[1]/div[2]/div[1]/ul")
	WebElement summary;
	@FindBy(xpath="//*[@id=\"tab-container\"]/div[1]/div[1]/span[1]")
	WebElement Activa;
	//ForTESTCASE5	
		public void SelectScooter() {
			try {
		    mywait.until(ExpectedConditions.visibilityOf(Scooter));
			Assert.assertEquals(Scooter.isDisplayed(), true, "Scooter is Clickable");
		    Scooter.click();}
			catch(Exception e) {
				driver.navigate().back();
				mywait.until(ExpectedConditions.visibilityOf(Scooter));
				Assert.assertEquals(Scooter.isDisplayed(), true, "Scooter is Clickable");
			    Scooter.click();
			}
		}
		public void SelectBestScooter()
		{
	    	js.executeScript(" window.scrollBy(0,450)","");
		    mywait.until(ExpectedConditions.visibilityOf(BestScooter));
			Assert.assertEquals(BestScooter.isDisplayed(), true);
		    js.executeScript("arguments[0].click();", BestScooter);
		    Assert.assertEquals(driver.getCurrentUrl(), "https://www.zigwheels.com/newbikes/best-scooters", "Not in Sccoter Page");
		    js.executeScript(" window.scrollBy(0,250)","");  
		    mywait.until(ExpectedConditions.visibilityOf(Onroad));
		    Assert.assertEquals(Onroad.isDisplayed(), true, "Not in Best Scooter Page");
		    js.executeScript("arguments[0].click();", Onroad);
	    	js.executeScript(" window.scrollBy(0,350)","");

		}
		public void printsummary() throws IOException {

	    	Assert.assertEquals(Activa.isDisplayed(), true, "Activa Scooty is Visible");
			System.out.println(summary.getText());
			XSSFSheet S4=ExcelUtility.CreateSheet("TC-05");
			  R1=ExcelUtility.createRow(0,S4);
			  ExcelUtility.setData(R1, 0, "TestCase-05");
			  R1=ExcelUtility.createRow(1,S4);
			  ExcelUtility.setData(R1, 0, summary.getText());
			}
		public String ScootyName() {
			return Activa.getText();
		}
		
	
	//ForTESTCASE6
	@FindBy(xpath="//a[@class='c-p clr-ev']")
	WebElement evdrop;
	@FindBy(xpath="//*[text()='Electric Bike Charging Stations']")
	WebElement bikecharge;
	@FindBy(xpath="//*[@href=\"https://www.zigwheels.com/bikes/electric-charging-station/Chennai\"]")
	WebElement chennai;
	@FindBy(xpath="//*[@id=\"zwn-search\"]/div/div/div[1]/div[5]/div[1]/h2")
	WebElement TotalBikes;
	@FindBy(xpath="(//div[@class=\"zw-dealertext\"])[1]")
	WebElement Add1;
	@FindBy(xpath="(//div[@class=\"zw-dealertext\"])[3]")
	WebElement Add2;
	@FindBy(xpath="//*[@id=\"zwn-search\"]/div/div/div[1]/div[3]/h2")
	WebElement SearchScroll;
	//ForTESTCASE6
		public void hoverToElectricVehicles()
		{try {
			Assert.assertEquals(evdrop.isDisplayed(), true, "Electric Bike is clickable");

			act.moveToElement(evdrop).perform();
		}
		catch(Exception e) {
		    mywait.until(ExpectedConditions.visibilityOf(evdrop));

			Assert.assertEquals(evdrop.isDisplayed(), true, "Electric Bike is clickable");

			act.moveToElement(evdrop).perform();
		}
		}
	
		public void clickchennai()
		{	js.executeScript("arguments[0].click()",bikecharge);

			Assert.assertEquals(driver.getTitle(), "Electric Bike Charging Stations- Find out Electric Vehicle Charging Stations in Your City @ ZigWheels", "Page Title is not Matching");
			js.executeScript("arguments[0].scrollIntoView();", chennai);
			js.executeScript("arguments[0].click()",chennai);
		}
		public void printaddress() throws IOException 
		{
		Assert.assertEquals(TotalBikes.isDisplayed(), true, "Chennai is not visible");
		js.executeScript("arguments[0].scrollIntoView();", SearchScroll);

		js.executeScript("arguments[0].click()",Add1);
		System.out.println(Add1.getText());
		System.out.print(Add2.getText());
		XSSFSheet S5=ExcelUtility.CreateSheet("TC-06");
		  R1=ExcelUtility.createRow(0,S5);
		  ExcelUtility.setData(R1, 0, "TestCase-06");
		  R1=ExcelUtility.createRow(1,S5);
		  ExcelUtility.setData(R1, 0, Add1.getText());
		 R1=ExcelUtility.createRow(2,S5);
		  ExcelUtility.setData(R1, 0, Add2.getText());
		}
	
	//ForTESTCASE7
	@FindBy(xpath="//*[text()='Electric Car Charging Stations']")
	WebElement CarCharge;
	@FindBy(xpath="//*[@id=\"zwn-search\"]/div/div/div[1]/div[4]/div/div/ul/li[7]/a/span")
	WebElement ChennaiCity;
	@FindBy(xpath="//*[@id=\"zwn-search\"]/div/div/div[1]/div[4]/div/h2")
	WebElement ChennaiScroll;
	@FindBy(xpath="//*[@id=\"zwn-search\"]/div/div/div[1]/div[5]/div[1]/h2")
	WebElement NoOfStations;
	//ForTESTCASE7
		public void ClickingElectricCar() {
			try {
				js.executeScript("scroll(0, -5000)");

			    mywait.until(ExpectedConditions.visibilityOf(evdrop));
				Assert.assertEquals(evdrop.isDisplayed(), true, "Electric Car is not visible");

			act.moveToElement(evdrop).perform();
			
			js.executeScript("arguments[0].click()",CarCharge);
			js.executeScript("arguments[0].scrollIntoView();", ChennaiScroll);			
		}
			catch(Exception e) {
				System.out.println(e.getMessage());
				}
			}
		public void ClickingChennai() {
			Assert.assertEquals(ChennaiCity.isDisplayed(), true, "Not in Electric Car Page" );
			js.executeScript("arguments[0].click()",ChennaiCity);
		}
		public void PrintStations() throws IOException {
			js.executeScript("arguments[0].scrollIntoView();", SearchScroll);			
		    Assert.assertEquals(NoOfStations.isDisplayed(), true, "Stations is not Displayed");
		    System.out.println(NoOfStations.getText());
		    XSSFSheet S7=ExcelUtility.CreateSheet("TC-07");
			  R1=ExcelUtility.createRow(0,S7);
			  ExcelUtility.setData(R1, 0, "TestCase-07");
			  R1=ExcelUtility.createRow(1,S7);
			  ExcelUtility.setData(R1, 0, NoOfStations.getText());
		}
		public boolean Station() {
			return NoOfStations.isDisplayed();
		}		
		
		@FindBy(xpath = "//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[7]/a")
		WebElement usedCarsSection;
		@FindBy(xpath = "//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[7]/ul/li/div[2]/ul/li[4]/a")
		WebElement selectCHN;
		@FindBy(xpath = "(//span[@class='ft-link'])[1]")
		WebElement emiBtn;
		@FindBy(xpath="//*[@id=\"usedcarttlID\"]")
		WebElement ChennaiUsedcar;
		@FindBy(xpath = "//select[@id='makeId']")
		WebElement make;

	 
		public void moveToUsedCars() throws Exception {
			try {
				js.executeScript("scroll(0, -5000)");

			    mywait.until(ExpectedConditions.visibilityOf(usedCarsSection));
		    Assert.assertEquals(usedCarsSection.isDisplayed(), true,"Failed in first Step");
		    act.moveToElement(usedCarsSection).perform();
			}
			catch(Exception e){
				System.out.println(e.getMessage());

			}
			}
		public void selectChennaiUsedCars() {
			try {
			mywait.until(ExpectedConditions.visibilityOf(selectCHN));

			selectCHN.click();
		    Assert.assertEquals(ChennaiUsedcar.isDisplayed(), true, "Chennai si not displayed");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				
			}
		}
	 
		public void usedCars() throws Exception {
			List<WebElement> carName = driver.findElements(By.xpath("//a[@data-track-label='Car-name']"));

			List<WebElement> carPrice = driver.findElements(By.xpath("//span[@class='zw-cmn-price n pull-left mt-3']"));
	 
			List<WebElement> carLocation = driver.findElements(By.xpath("//span[@class='zc-7 fnt-14 uLm block']"));
	 
			System.out.println("The List of Used Cars:\n");
			
			for (int i = 0; i <=5; i++) {
				String Name = carName.get(i).getText();
				String Price = carPrice.get(i).getText();
				String loction = carLocation.get(i).getText();
				System.out.println(Name + " - " + Price + "-" + loction);
			}

			 XSSFSheet S8=ExcelUtility.CreateSheet("TC-08");
			  R1=ExcelUtility.createRow(0,S8);
			ExcelUtility.setData(R1, 0, "TestCase-08");
			ExcelUtility.setDataUsedNews(carName, S8);
			
		}
	 
			public void clickEmi() 
			{			

			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			mywait.until(ExpectedConditions.visibilityOf(emiBtn));
			Assert.assertEquals(emiBtn.isDisplayed(), true,"EMI button is not found");
			emiBtn.click();
     		}
	 
		
	 
		public void clickMake() {
			js.executeScript("arguments[0].scrollIntoView();", EmiStartScroll);			

			Select sc = new Select(make);
			sc.selectByIndex(4);
			Assert.assertEquals(make.isDisplayed(), true);

		}
	 
		@FindBy(xpath = "(//span[@class='select-wrapper'])[2]")
		WebElement model;
		@FindBy(xpath = "//*[@id='variantId']")
		WebElement varient;
		@FindBy(xpath = "//input[@data-track-label='search-button']")
		WebElement checkBtn;
		@FindBy(xpath = "//div[@data-year='2']")
		WebElement year;
		@FindBy(xpath = "(//span[@class='emi_div_mobile'])[1]")
		WebElement MOnthlyPrice;
		@FindBy(xpath="/html/body/div[8]/div[2]/div[1]/div[1]/h1")
		WebElement EmiStartScroll;
		@FindBy(xpath="/html/body/div[8]/div[2]/div[1]/div[6]/div[2]/a")
		WebElement EmiEndScroll;
		public void ClickModel() {
			try {
				
			//js.executeScript(" window.scrollBy(0,250)", "");			

			Select se = new Select(driver.findElement(By.id("modelId")));
			mywait.until(ExpectedConditions.visibilityOf(model));
			se.selectByIndex(3);

			Assert.assertEquals(varient.isDisplayed(), true,"varient is not displayed");		

			}
			catch(Exception e) {
				System.out.println("dfghj");

			}

		}
		public void clickvarient() {
			try {
				js.executeScript("arguments[0].scrollIntoView();", EmiStartScroll);			

			Select sc = new Select(varient);
			mywait.until(ExpectedConditions.visibilityOf(varient));
			sc.selectByIndex(2);
			Assert.assertEquals(varient.isDisplayed(), true,"varient is not displayed");
					
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	 
		
		public void PrintMOnthlyEMI() throws InterruptedException, IOException {
			Assert.assertEquals(checkBtn.isDisplayed(), true,"Check button is not clickable");

			checkBtn.click();
	 
			js.executeScript(" window.scrollBy(0,850)", "");
			year.click();
			WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
			mywait.until(ExpectedConditions.visibilityOf(MOnthlyPrice));
			System.out.println("");
			System.out.println("Your Monthly EMI= " + MOnthlyPrice.getText());
			XSSFSheet S9=ExcelUtility.CreateSheet("TC-09");
			  R1=ExcelUtility.createRow(0,S9);
			  ExcelUtility.setData(R1, 0, "TestCase-09");
			  R1=ExcelUtility.createRow(1,S9);
			  ExcelUtility.setData(R1, 0, MOnthlyPrice.getText());
	 
		}
	 
		@FindBy(xpath = "//*[@id=\"headerSearch\"]")
		WebElement Search_Box;
		
	 
		public void clickingOnSearchBox() throws InterruptedException {
			Assert.assertEquals(Search_Box.isDisplayed(), true,"Search button is not clickable");
			Search_Box.sendKeys("Ola S1 Pro");
			Search_Box.sendKeys(Keys.ENTER);	 
		}
	 @FindBy(xpath="//a[@title=\"Ola S1 Pro\"]")
	 WebElement KeyAssert;
	 @FindBy(xpath="//*[@id=\"mNav\"]/nav/div/ul/li[5]/a")
	 WebElement SpecsBtn;
		public void cickResult() {
			try {
				mywait.until(ExpectedConditions.visibilityOf(KeyAssert));
				Assert.assertEquals(KeyAssert.isDisplayed(), true,"Search page is not loaded");

				js.executeScript("arguments[0].click()",KeyAssert);

				
			}
			catch(Exception e) {
				mywait.until(ExpectedConditions.visibilityOf(SpecsBtn));

				Assert.assertEquals(SpecsBtn.isDisplayed(), true,"Search page is not loaded");
				js.executeScript("arguments[0].click()",SpecsBtn);
			}
		}
	 
		@FindBy(xpath = "//*[@id=\"mNav\"]/nav/div/ul/li[5]/a")
		WebElement Key_SpecBtn;
		@FindBy(xpath = "//table[@class='topSpec']")
		WebElement Key_Specs;
	 
		public void printSpecs() throws IOException {
			mywait.until(ExpectedConditions.visibilityOf(Key_SpecBtn));
			Key_SpecBtn.click();
			//Assert.assertEquals(Key_SpecBtn.isDisplayed(), true,"Specifications is not there");
			String Olaimg = driver.findElement(By.xpath("//img[@title='Ola S1 Pro Images']")).getAttribute("title");
			String Olaval = "Ola S1 Pro Images";
			Assert.assertEquals(Olaimg, Olaval);
			js.executeScript(" window.scrollBy(0,250)", "");
			mywait.until(ExpectedConditions.visibilityOf(Key_Specs));
			System.out.println(Key_Specs.getText());
			XSSFSheet S10=ExcelUtility.CreateSheet("TC-10");
			  R1=ExcelUtility.createRow(0,S10);
			  ExcelUtility.setData(R1, 0, "TestCase-10");
			  R1=ExcelUtility.createRow(1,S10);
			  ExcelUtility.setData(R1, 0, Key_Specs.getText());
		}
	 
		public void getImg() {
			WebElement img = driver.findElement(By.xpath("//img[@title='Ola S1 Pro Images']"));
			String imgPath = img.getAttribute("src");
	 
			try {
				URL imageURL = new URL(imgPath);
	 
				ImageIO.write(ImageIO.read(imageURL), "PNG", new File(
						"C:\\Users\\2303813\\eclipse-workspace\\Zigwheels\\Imagefolder"));
				System.out.println("File Successfully Saved in the Folder");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
		}
	 
		public void SimilarBikes() throws InterruptedException, IOException 
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("//a[@title='Bikes Similar to S1 Pro']")).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(" window.scroll(0,500)", "");
			String Tvsimg = driver.findElement(By.xpath("//img[@title='TVS iQube']")).getAttribute("title");
			String Tvsval = "TVS iQube";
			Assert.assertEquals(Tvsimg, Tvsval);
			System.out.println("The Similar bikes are");
			List<WebElement> bikeNames = driver.findElements(By.xpath("//a[@itemprop='relatedLink']"));	 
			System.out.println("The List of Similar bikes:\n");
			for (int i = 0; i < 3; i++) {
				String Name = bikeNames.get(i).getText();
				//String Price = BikePrices.get(i).getText();
	 
				System.out.println(Name+"\n");
			}

			 XSSFSheet S12=ExcelUtility.CreateSheet("TC-12");
			  R1=ExcelUtility.createRow(0,S12);
			ExcelUtility.setData(R1, 0, "TestCase-12");
			ExcelUtility.setDataSimilarNews(bikeNames, S12);
			}
		
		
}
	
	
	

	
	

