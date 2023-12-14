package com.lambdatest.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lambdatest.pages.HeaderSection;
import com.lambdatest.pages.LoginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;

import baseplaywright.Driverlt;
import baseplaywright.PlaywrightConnection;

public class LoginUserTC extends PlaywrightConnection{
	Driverlt driver;
	@BeforeMethod
	public void setUp() throws Exception {
			driver = super.createConnection();
	}
	@AfterMethod
	public void tearDown() {
		super.closeConnection(driver);
	}
	
	@Test
	public void registerUSer() {
		Page page = driver.getPage();
		HeaderSection header = new HeaderSection(page);
		LoginPage login = new LoginPage(page);
		try {
			page.navigate("https://ecommerce-playground.lambdatest.io/");
			page.waitForLoadState(LoadState.LOAD);
			header.clickLogin();
			login.loginAsUser("koushik350@gmail.com", "Pass123$");
			String title = page.title();
			if(title.equals("My Account")) {
				super.setTestStatus("passed","Login success", page);
			}else {
				super.setTestStatus("failed", "Login failed", page);
			}
		} catch (PlaywrightException err) {
			super.setTestStatus("failed", err.getMessage(), page);
			err.printStackTrace();
		}
	}

}
