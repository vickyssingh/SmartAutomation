package com.smart.config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends Utils implements ITestListener{

	ExtentTest test;
	ExtentReports extent = Utils.getReporterObject();
	AppiumDriver mobDriver;
	WebDriver driver;
	
	public void onTestStart(ITestResult result) {
	test = extent.createTest(result.getMethod().getMethodName());
	System.out.println("Test started"+ result.getMethod().getMethodName());
	}
	
	public void onStart(ITestContext contextStart) {
	System.out.println("onStart method started");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	System.out.println("Method failed with certain success percentage"+ result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		try {
			mobDriver = (AppiumDriver) result.getTestClass().getRealClass().getField("mobDriver").get(result.getInstance());
		} catch (Exception e) {
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception f) {
			}
		}
		try {
			test.addScreenCaptureFromPath(getScrenshotPath(mobDriver,result.getMethod().getMethodName()), result.getMethod().getMethodName());
			} catch (Exception e) {
				try {
					test.addScreenCaptureFromPath(getScrenshotPath(driver,result.getMethod().getMethodName()), result.getMethod().getMethodName());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	System.out.println("Method failed"+ result.getName());

	}

	public void onTestSkipped(ITestResult result) {
	System.out.println("Method skipped"+ result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Pass");
		try {
			mobDriver = (AppiumDriver) result.getTestClass().getRealClass().getField("mobDriver").get(result.getInstance());
		} catch (Exception e) {
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception f) {
			}
		}
		try {
			test.addScreenCaptureFromPath(getScrenshotPath(mobDriver,result.getMethod().getMethodName()), result.getMethod().getMethodName());
		} catch (Exception e) {
			try {
				test.addScreenCaptureFromPath(getScrenshotPath(driver,result.getMethod().getMethodName()), result.getMethod().getMethodName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	 System.out.println("Method passed"+ result.getName());

	}
	
	public void onFinish(ITestContext contextFinish) {
		extent.flush();
		System.out.println("onFinish method finished");
		}

}
