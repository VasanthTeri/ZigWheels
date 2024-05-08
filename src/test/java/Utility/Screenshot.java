package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	//Method to take Screenshot 
		public static String screenshot(WebDriver driver,String filename) throws IOException
		   {
		  TakesScreenshot ts= (TakesScreenshot)driver;
		  File src=ts.getScreenshotAs(OutputType.FILE);
	    File trg=new File(System.getProperty("user.dir")+ "/screenshots/"+filename+".png");//File path
	    FileUtils.copyFile(src,trg);
	    return trg.getAbsolutePath();
}
}