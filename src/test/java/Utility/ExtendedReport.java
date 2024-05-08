package Utility;

import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import main.TestCases;


public class ExtendedReport implements  ITestListener{

	
	public ExtentSparkReporter SparkReport;
	public ExtentReports Report;
	public ExtentTest Test;
	public void onStart(ITestContext context) {
		SparkReport=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myreport.html");
		SparkReport.config().setDocumentTitle("Florida Power and Light");
		SparkReport.config().setReportName("TestNG Reports");
		SparkReport.config().setTheme(Theme.DARK);
		Report= new ExtentReports();
		Report.attachReporter(SparkReport);
		
		Report.setSystemInfo("Computer Name","DELL");
		Report.setSystemInfo("Environment","QA");
		Report.setSystemInfo("Tester Name","Vasanth");
		Report.setSystemInfo("OS","Windows10");
		Report.setSystemInfo("Browser name","Chrome");

	}
	public void onTestSuccess(ITestResult result) {
		String path=TestCases.path;
		Test=Report.createTest(result.getName()).addScreenCaptureFromPath(path,result.getName());
		Test.log(Status.PASS, "Test Is Passed: "+ result.getName());
	}
 
	public void onTestFailure(ITestResult result) {
		String path=TestCases.path;

		Test=Report.createTest(result.getName()).addScreenCaptureFromPath(path,result.getName());
		Test.log(Status.FAIL, "Test Is Failed: " +result.getName());
		Test.log(Status.FAIL,"Failed Because: "+result.getName());
	}
	public void onTestSkipped(ITestResult result) {
		Test=Report.createTest(result.getName());
		Test.log(Status.SKIP," Test Skipped: "+result.getName() );
	}
	public void onFinish(ITestContext context) {
		Report.flush();
	}

}
