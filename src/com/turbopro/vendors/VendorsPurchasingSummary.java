package com.turbopro.vendors;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VendorsPurchasingSummary {
	static String driverPath = "C:/Users/harini_shankar/workspace/";
	public WebDriver driver;

	public String baseurl= "http://qa.tt.eb.local/turbotracker/turbo/login";
	public WebDriverWait getWait()
	{
		return new WebDriverWait(driver, 60);
	}

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging


		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// Create a new proxy object and set the proxy
		Proxy proxy = new Proxy();
		proxy.setSslProxy("sysvines006.sysvine.local:3128");
		proxy.setSocksUsername("sathish_kumar");
		proxy.setSocksPassword("vine@2017");
		//Add the proxy to our capabilities 
		capabilities.setCapability("proxy", proxy);
		driver = new ChromeDriver(capabilities);

		// driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}


	@Test( enabled = true, priority = 1)
	public void login() throws InterruptedException 

	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");

		driver.findElement(By.className("login")).click();

		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//input[@value = 'Login']")).click();

		Thread.sleep(4000);
	}

	@Test( enabled = true, priority = 2)
	public void navigateVendorInvoices() throws InterruptedException
	{

		WebElement company = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"));
		WebElement vendor = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"));
		Actions action = new Actions(driver);
		action.moveToElement(company).perform();
		action.moveToElement(vendor).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorAccountList")));
		driver.findElement(By.id("vendorAccountList")).click();
		Thread.sleep(2000);
		Select purchasingsummary = new Select(driver.findElement(By.id("vendorAccountList")));
		Thread.sleep(2000);
		purchasingsummary.selectByVisibleText("Purchasing Summary");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id= 'goSearchButtonID']")).click();
	}

}

