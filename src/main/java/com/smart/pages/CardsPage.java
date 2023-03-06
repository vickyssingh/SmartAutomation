package com.smart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.smart.config.CommonComponent;

public class CardsPage extends CommonComponent{

	WebDriver driver;
	
	public CardsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(.,'Cashback')]")
	public WebElement cashBack_tab;
	
	@FindBy(xpath = "//div[contains(@class,'item-name')]//h4")
	public WebElement cardTitle;
	
	@FindBy(xpath = "//span[contains(@class,'fee')]//span[contains(.,'Joining Fee')]//span")
	public WebElement joiningFee;
	
	@FindBy(xpath = "//span[contains(@class,'fee')]//span[contains(.,'Annual Fee')]//span")
	public WebElement annualFee;
	
	public void tapOnCashbackTab() {
		cashBack_tab.click();
	}
	
	public String getCardTitle() {
		return cardTitle.getText();
	}
	
	public String getJoiningFee() {
		return joiningFee.getText();
	}
	
	public String getAnnualFee() {
		return annualFee.getText();
	}
}
