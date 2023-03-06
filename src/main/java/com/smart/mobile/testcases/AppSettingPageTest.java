package com.smart.mobile.testcases;

import org.testng.annotations.Test;

import com.smart.config.MobileBaseComponent;
import com.smart.mobile.pages.AppSettingPage;

public class AppSettingPageTest extends MobileBaseComponent{

	AppSettingPage appSettingPage;
	
	@Test
	public void setAppsetting() {
		
		appSettingPage = new AppSettingPage(mobDriver);
		appSettingPage.setAppSettings(appSettingPage.intiData());
	}
}
