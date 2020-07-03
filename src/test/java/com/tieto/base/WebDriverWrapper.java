package com.tieto.base;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tieto.utilities.PropUtils;

public class WebDriverWrapper {

	protected WebDriver driver; 
	

	@Parameters({"browser"})
	@BeforeMethod
	public void init(@Optional("chrome") String browserName) throws IOException
	{
		launchBrowser(browserName);		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String baseURL = PropUtils.getValueFromKey("testdata/data.properties","url");
		driver.get(	baseURL);
		//driver.get("https://demo.openemr.io/b/openemr/interface/login/login.php?site=default");
		//driver.get("https://demo.openemr.io/a/openemr/interface/login/login.php?site=default");
	}
	@AfterMethod
	public void end()
	{		
		driver.quit();
	}
	
	public void launchBrowser(String browserName) 
	{
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
		System.setProperty("webdriver.edge.driver", "driver/msedgedriver.exe");

		if(browserName.toLowerCase().equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.toLowerCase().equals("edge")) {
			driver = new EdgeDriver();
		}
		else{
			driver = new ChromeDriver();
		}
	}

}
