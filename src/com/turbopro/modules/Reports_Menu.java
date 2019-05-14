package com.turbopro.modules;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class Reports_Menu extends Variables{


	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}

	@Test(enabled = true)
	public void Reports() throws InterruptedException 
	{
		login();
		
		//open jobs
		WebElement reports = get("//*[@id='mainmenuReportsPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(reports).perform();
		get("//*[@id='mainmenuReportsPage']/ul/li[1]").click();
		Thread.sleep(5000);
		
		get("//*[@id='opeJobsCriteriaform']/table/tbody/tr[6]/td[2]/input").click();
		Thread.sleep(2000);
		get("//*[@id='opeJobsCriteriaform']/table/tbody/tr[6]/td[3]/input").click();
		Thread.sleep(2000);
		get("//*[@id='Customer1']").sendKeys("Venture Mechanical - *CSY");
		Thread.sleep(2000);
		get("//body/ul[14]/li/a").click();
		Thread.sleep(2000);
		get("//*[@id='opeJobsCriteriaform']/table/tbody/tr[6]/td[2]/input").click();
		Thread.sleep(2000);
		get("//*[@id='opeJobsCriteriaform']/table/tbody/tr[6]/td[3]/input").click();
		
		
		// open PO
		WebElement reports1 = get("//*[@id='mainmenuReportsPage']/a");
		Actions action1 = new Actions(driver);
		action1.moveToElement(reports1).perform();
		get("//*[@id='mainmenuReportsPage']/ul/li[2]").click();
		Thread.sleep(5000);
		
	
	/*	
		//positive pay 
		WebElement reports2 = get("//*[@id='mainmenuReportsPage']/a");
		Actions action2 = new Actions(driver);
		action2.moveToElement(reports2).perform();
		get("//*[@id='mainmenuReportsPage']/ul/li[3]").click();
		Thread.sleep(5000);
		
		driver.findElement(By.linkText("1")).click();
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[5]/div[11]/div/button")));
		Thread.sleep(2000);
		get("//body/div[5]/div[11]/div/button").click();
		
		*/		
		
		
		
		
		
	}
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
	
}

