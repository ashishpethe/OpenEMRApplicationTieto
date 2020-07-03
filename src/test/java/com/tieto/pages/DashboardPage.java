package com.tieto.pages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	By messageCenterLoc =  By.xpath("//span[text()='Message Center']");
	By patientClientLoc =  By.xpath("//div[contains(text(),'Patient/Client')]");
	By patientLoc = By.xpath("//div[contains(text(),'Patients')]");	
	By finFrameLoc = By.xpath("//iframe[@name='fin']");	
	By addNewPatientButtonLoc = By.id("create_patient_btn1");
	By patFrameLoc = By.xpath("//iframe[@name='pat']");
	By titleLoc = By.id("form_title");	
	By firstNameLoc = By.id("form_fname");
	By lastNameLoc = By.id("form_lname");
	By dobLoc = By.id("form_DOB");
	By sexLoc = By.id("form_sex");	
	By createCustomerButtonLoc = By.id("create");
	By modelFrameLoc = By.xpath("//iframe[@id='modalframe']");
	By confirmCreateNewPatientButtonLoc = By.xpath("//input[@value='Confirm Create New Patient']");
	By birthdayWishMessageCloseLoc = By.xpath("//div[@class='closeDlgIframe']");
	
	private WebDriver driver;
	
	public DashboardPage(WebDriver driver) 
	{
		this.driver= driver;
	}
	
	public String getCurrentTitle() 
	{
		return driver.getTitle();		
	}	
	
	public void waitForPresenceOfMessageCenter()
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.presenceOfElementLocated(messageCenterLoc));
	}
	
	/* Patient finder Tab */	
	
	public void mouseOverOnPatients() 
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(patientClientLoc)).build().perform();		
	}	
	
	public void clickOnPatients() 
	{		
		driver.findElement(patientLoc).click();	
	}	
	
	public void switchToFrameAddNewPatient() throws InterruptedException 
	{
		switchToFrameInWindow(finFrameLoc);			
	}
	
	public void clickOnCreateNewPatientButton()	
	{			
		driver.findElement(addNewPatientButtonLoc).click();
		driver.switchTo().defaultContent();
	}	
	
	/* Search or Add Patient Tab */	
	public void switchToFramePatientDetails() throws InterruptedException 
	{
		switchToFrameInWindow(patFrameLoc);			
	}
	
	public void selectTitle(String title)
	{
		Select updateTitle = new Select(driver.findElement(titleLoc));
		updateTitle.selectByVisibleText(title);
	}	
	
	public void enterPatientFirstName(String fName)
	{
		driver.findElement(firstNameLoc).sendKeys(fName); 
	}
	
	public void enterPatientLastName(String LName)
	{
		driver.findElement(lastNameLoc).sendKeys(LName); 
	}
	
	public void enterDOB()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  		  
		driver.findElement(dobLoc).sendKeys(dtf.format(now));
	}
	
	public void selectSex() 
	{
		Select updateSex = new Select(driver.findElement(sexLoc));
		updateSex.selectByVisibleText("Male");
	}	
	
	public void clickOnCreateCustomerButton()
	{
		driver.findElement(createCustomerButtonLoc).click();
		driver.switchTo().defaultContent();
	}	
	
	/*model dialog window*/		
	public void switchToFrameConfirmNewCreationDialog() throws InterruptedException 
	{
		switchToFrameInWindow(modelFrameLoc);			
	}
	
	public void clickOnConfirmCreateNewPatientButton()
	{
		driver.findElement(confirmCreateNewPatientButtonLoc).click();
	}	
	
	public void acceptAlert() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.alertIsPresent());				
		Alert alert= driver.switchTo().alert();
		alert.accept();	
		driver.switchTo().defaultContent();	
	}
	
	public void closeBirthdayWishMessage() 
	{
		driver.findElement(birthdayWishMessageCloseLoc).click();
	}
	
	/*Common utility*/
	public void switchToFrameInWindow(By finFrameLoc) throws InterruptedException 
	{
		/*
		 * String addPatientFinderWindow = driver.getWindowHandle();
		 * driver.switchTo().window(addPatientFinderWindow);
		 */	
		Thread.sleep(5000);
		/*WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(finFrameLoc)));*/
		driver.switchTo().frame(driver.findElement(finFrameLoc));
	}
}
