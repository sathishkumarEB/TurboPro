package com.turbopro.modules;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.webdriven.commands.Click;
import com.turbopro.basepackages.*;


public class RID761_RestrictDifferentUsersToEditSameQuote extends Variables {

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


	@Test(enabled = true, priority = 1)
	public void login1() throws InterruptedException 
	{
		login();		
	}

	@Test(enabled = false, priority = 2)
	public void useCase_1() throws InterruptedException 
	{
		driver.findElement(By.id("jobsearch")).sendKeys("165065");
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		
		Thread.sleep(3000);
		get("//*[@id='jobquotesid']").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[2]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		get("//*[@id='BidDialogCustom']/table/tbody/tr/td[2]/input").click();
		Thread.sleep(3000);
		
		get("//*[@id='ui-tabs-2']/table[2]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[2]/input").click();
		Thread.sleep(3000);
		get("//body/div[37]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[2]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[3]/input").click();
		Thread.sleep(3000);
		get("//body/div[37]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//*[@id='CloseQuoteButtonID']").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[2]/input").click();
		Thread.sleep(3000);
		get("//body/div[37]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[3]/input").click();
		Thread.sleep(3000);
		get("//body/div[37]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-2']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//body/div[37]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
	}
	
	@Test(enabled = true, priority = 3)
	public void useCase_2() throws InterruptedException 
	{
		
	}
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
	
}
