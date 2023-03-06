package com.smart.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.smart.pages.CardsPage;


public class BaseComponent extends Utils {

	public WebDriver driver;
	public CardsPage cardsPage;
	public HashMap<String, LinkedHashMap<String, String>> testcaseData;
	

	public WebDriver initializeBrowser() throws IOException {
		Properties prop = readPropertiesFile();
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("safari")) {
			 driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		testcaseData = readExcel();
		return driver;
	}
	
	@BeforeClass
	public void launchApplication() throws IOException {
		driver = initializeBrowser();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
}
