package kowsalyaLearning.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import kowsalyaLearning.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getExtentObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	    // not implemented
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	  }

	@Override
	  public void onTestSuccess(ITestResult result) {
	    // not implemented
		  extentTest.get().log(Status.PASS,"test passed");
	  }

	@Override
	  public void onTestFailure(ITestResult result) {
	    // not implemented
		extentTest.get().fail(result.getThrowable());
		  String filePath = null;
		  try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		 try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		  // Generate screenshot and attach to extent report
	  }
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }

	
	
	
}
