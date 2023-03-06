package com.smart.webUi.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.smart.config.BaseComponent;
import com.smart.config.CommonComponent;
import com.smart.pages.CardsPage;

public class CardsPageTest extends BaseComponent{

	CardsPage cardPage;
	
	
	@Test(priority = 1)
	public void verifyCardTitile() throws Exception {
		cardPage = new CardsPage(driver);
		CommonComponent.waitForElementVisible(cardPage.cashBack_tab);
		CommonComponent.scrollToElement(cardPage.cashBack_tab);
		cardPage.tapOnCashbackTab();
		CommonComponent.implicitlyWait();
		CommonComponent.waitForElementVisible(cardPage.cardTitle);
		CommonComponent.scrollDown();
		CommonComponent.scrollToElement(cardPage.cardTitle);
		String title = cardPage.getCardTitle();
		Assert.assertTrue(title.equalsIgnoreCase(testcaseData.get("TC_01 Card Title").get("Validation")));
	}
	
	@Test(priority = 2)
	public void verifyJoiningFee() {
		cardPage = new CardsPage(driver);
		CommonComponent.implicitlyWait();
		CommonComponent.waitForElementVisible(cardPage.joiningFee);
		String joiningFee = cardPage.getJoiningFee();
		Assert.assertTrue(joiningFee.equalsIgnoreCase(testcaseData.get("TC_02 Joining Fee").get("Validation")));
	}
	
	@Test(priority = 3)
	public void verifyAnnualFee() {
		cardPage = new CardsPage(driver);
		CommonComponent.implicitlyWait();
		CommonComponent.waitForElementVisible(cardPage.annualFee);
		String annualFee = cardPage.getAnnualFee();
		Assert.assertTrue(annualFee.equalsIgnoreCase(testcaseData.get("TC_03 Annual Fee").get("Validation")));
	}
}
