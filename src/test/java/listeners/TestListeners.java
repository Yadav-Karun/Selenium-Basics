package listeners;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import testbase.BaseTest;
import utils.ExtentManager;

public class TestListeners implements ITestListener{
	ExtentReports extentReports = ExtentManager.reportHTML();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = extentReports.createTest(methodName);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		
		String screenshotPath = null;
		String screenshotPathForExtentReport = null;
		try {
			screenshotPath = BaseTest.getScreenshot(result.getMethod().getMethodName());
			screenshotPathForExtentReport = new File(screenshotPath).toURI().toString();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPathForExtentReport).build());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
}
