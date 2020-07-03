package com.tieto.test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtilis;

public class LoginTest extends WebDriverWrapper
{	
	
	@DataProvider
	public Object[][] validCredentialData() throws IOException
	{
		return ExcelUtilis.getSheetIntoObject("testdata/OpenEMRData.xlsx", "validCredentialTestData");
	}
	
	@Test(priority = 1, dataProvider = "validCredentialData")
	public void validCredentialTest(String username, String password, String language, String expectedValue) 
	{		
		LoginPage loginPage= new LoginPage(driver);		
		loginPage.enterUsername("admin");
		loginPage.enterPassword("pass");
		loginPage.selectLanguage("English (Indian)");
		loginPage.clickLoginButton();
		
		DashboardPage dashBoard = new DashboardPage(driver);
		dashBoard.waitForPresenceOfMessageCenter();	
		
		Assert.assertEquals(dashBoard.getCurrentTitle(), "OpenEMR");
	}
	
	@DataProvider
	public Object[][] invalidCredentialsData(){
		Object[][] main = new Object[2][4];
		//i = 3 ---> number of test cases
		//j = 2 --->number of parameters
		
		main[0][0]="john";
		main[0][1]="john123";		
		main[0][2]="English (Indian)";
		main[0][3]="Invalid username or password";
		
		main[1][0]="paul";
		main[1][1]="paul123";
		main[1][2]="French (Standard)";
		main[1][3]="Invalid username or password";
		
		return main;
	}
	
	@Test(priority = 2, dataProvider = "invalidCredentialsData" )
	public void invalidCredentialTest(String username, String password, String language, String expectedValue) 
	{
		LoginPage loginPage= new LoginPage(driver);		
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.selectLanguage(language);
		loginPage.clickLoginButton();		
		String errorMessage = loginPage.getErrorMessage();	
		
		Assert.assertEquals(errorMessage.trim(), expectedValue);
		//Assert.assertTrue(errorMessage.contains(expectedValue));
	}
}
