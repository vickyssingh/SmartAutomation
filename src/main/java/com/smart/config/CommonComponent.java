package com.smart.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;


public class CommonComponent extends BaseComponent{

	static AppiumDriver mobDriver;
	static WebDriver driver;

	public CommonComponent(AppiumDriver mobDriver) {
		CommonComponent.mobDriver = mobDriver;
	}

	public CommonComponent(WebDriver driver) {
		CommonComponent.driver = driver;
	}

	public static void waitForElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void scrollToElement(WebElement element) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded()", element);
	}

	@SuppressWarnings("deprecation")
	public static void implicitlyWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,750)", "");
	}

	public static void switchToWebView() {
		((SupportsContextSwitching) mobDriver).context("WEBVIEW_com.sap.ariba.procurement");

	}
	
	
}
