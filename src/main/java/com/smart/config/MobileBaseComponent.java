package com.smart.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.smart.mobile.pages.AppSettingPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileBaseComponent extends Utils{

	public static AppiumDriver mobDriver;
	public AppiumDriverLocalService service;
	public AppSettingPage appSettingPage;
	public String resourcepath = "//src//main//java//smart//resourcres//";
	
	@BeforeClass
	@Parameters("platform")
	public AppiumDriver configureAppium(String platform) throws InterruptedException, MalformedURLException {
		service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
			.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(200)).build();
		Properties prop = readPropertiesFile();
		if (platform.equalsIgnoreCase("Android")) {
			UiAutomator2Options option = new UiAutomator2Options();
			option.setDeviceName(prop.getProperty("androidDeviceName"));
			option.setApp(System.getProperty("user.dir")+resourcepath+"ariba.apk");
			option.setCapability("chromedriverExecutable", System.getProperty("user.dir")+resourcepath+"chromedriver");
			mobDriver = new AndroidDriver(new URL(prop.getProperty("ipUrl")), option);
		} else if (platform.equalsIgnoreCase("IOS")) {
			XCUITestOptions option = new XCUITestOptions();
			option.setDeviceName(prop.getProperty("iPhoneDeviceName"));
			option.setApp(System.getProperty("user.dir")+resourcepath+"ariba.app");
			option.setPlatformVersion(prop.getProperty("iPhoneDeviceVersion"));
			option.setWdaLaunchTimeout(Duration.ofSeconds(20));
			mobDriver = new IOSDriver(new URL(prop.getProperty("ipUrl")), option);
		} else {
			System.err.println("Please Provide Proper Platform Name in xml");
			System.exit(0);
		}
		return mobDriver;
	}

	@AfterClass
	public void mobiletearDown() {
	mobDriver.close();
	service.stop();
	}
	

}
