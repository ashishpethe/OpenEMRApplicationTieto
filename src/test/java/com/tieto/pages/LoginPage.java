package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
	
	private By userLoc = By.id("authUser"); 
	private By passwordLoc = By.id("clearPass"); 
	private By languageLoc = By.name("languageChoice"); 
	private By loginButtonLoc = By.xpath("//button[@class='btn btn-default btn-lg']");
	private By message = By.xpath("//div[contains(text(),'Invalid')]");

	private WebDriver driver;

	public LoginPage(WebDriver driver) 
	{
		this.driver= driver;
	}

	public void enterUsername(String userName)
	{
		driver.findElement(userLoc).sendKeys(userName); 
	}

	public void enterPassword(String password) 
	{
		driver.findElement(passwordLoc).sendKeys(password); }

	public void selectLanguage(String language)
	{
		Select selectLanguage = new Select(driver.findElement(languageLoc));
		selectLanguage.selectByVisibleText(language); 
	}

	public void clickLoginButton() 
	{
		driver.findElement(loginButtonLoc).click(); 
	}
	
	public String getErrorMessage() {
		return driver.findElement(message).getText();
	}

}
