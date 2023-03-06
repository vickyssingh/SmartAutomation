package com.smart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.smart.config.CommonComponent;

public class HomePage extends CommonComponent {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(.,'Cashback')]")
	WebElement cashBack_tab;
	
	@FindBy(xpath = "//ul[@class='main-nav']//a[contains(.,'Explore Products')]")
	WebElement exploreProducts;
	
	
	public void selectExploreProducts() {
		exploreProducts.click();
	}
	
	
	public CardsPage selectCashBackTab() {
		cashBack_tab.click();
		return  new CardsPage(driver);
	}
}
