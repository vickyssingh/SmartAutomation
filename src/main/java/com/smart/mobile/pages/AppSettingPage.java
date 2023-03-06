package com.smart.mobile.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.smart.config.CommonComponent;

import io.appium.java_client.AppiumDriver;

public class AppSettingPage extends CommonComponent{

	AppiumDriver mobDriver;
	
	public AppSettingPage(AppiumDriver mobDriver) 
	{
		super(mobDriver);
		this.mobDriver = mobDriver;
	}
	
	public String  btn_ok = "//ion-alert//span[text()='OK']/ancestor::button";
	public String  btn_save = "//button[contains(text(),'Save')]";
	
	public void setAppSettings(HashMap<String, String>  data) {
		switchToWebView();
		for(String key :data.keySet()) {
			WebElement appSettingDropdown = mobDriver.findElement(By.xpath("//ion-select[@aria-label='"+key+"']"));
			appSettingDropdown.click();
			WebElement selectOption = mobDriver.findElement(By.xpath("//ion-alert//button//div[text()='"+data.get(key)+"']"));
			selectOption.click();
			mobDriver.findElement(By.xpath(btn_ok)).click();
		}
		mobDriver.findElement(By.xpath(btn_save)).click();
	}
	
	public HashMap<String, String> intiData() {
		HashMap<String, String> intiData = new HashMap<String, String>();
		intiData.put("Gateway", "scdev1-MobileGateway");
		intiData.put("BuyerService", "GCP Man 07 (QA Dev)");
		intiData.put("Client-Secret", "Development");
		
		return intiData;
	}
}
