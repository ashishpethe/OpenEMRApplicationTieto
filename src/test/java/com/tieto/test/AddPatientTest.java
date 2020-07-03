package com.tieto.test;

import org.testng.annotations.Test;
import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;

public class AddPatientTest extends WebDriverWrapper 
{
	@Test
	public void createPatientTest() throws InterruptedException
	{				
		LoginPage loginPage= new LoginPage(driver);		
		loginPage.enterUsername("admin");
		loginPage.enterPassword("pass");
		loginPage.selectLanguage("English (Indian)");
		loginPage.clickLoginButton();
		
		DashboardPage dashBoard = new DashboardPage(driver);
		dashBoard.mouseOverOnPatients();
		dashBoard.clickOnPatients();	
		dashBoard.switchToFrameAddNewPatient();
		dashBoard.clickOnCreateNewPatientButton();
		dashBoard.switchToFramePatientDetails();
		dashBoard.selectTitle("Mr.");		
		dashBoard.enterPatientFirstName("Robert");
		dashBoard.enterPatientLastName("Wadra");		
		dashBoard.enterDOB();
		dashBoard.selectSex();
		dashBoard.clickOnCreateCustomerButton();		
		dashBoard.switchToFrameConfirmNewCreationDialog();
		dashBoard.clickOnConfirmCreateNewPatientButton();
		dashBoard.acceptAlert();	
		dashBoard.closeBirthdayWishMessage();
	}
}