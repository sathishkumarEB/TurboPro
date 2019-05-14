package com.turbopro.customermenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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

import com.turbopro.basepackages.*;

public class dummyCustomerInvoice extends Variables {

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
	public void loggingIn() throws InterruptedException 
	{
		login();		
	}

	//creating a customer invoice outside the job, by clicking new invoice button, with single line items  
	@Test(enabled = true, priority = 2)
	public void navigatingCustomerInvoice() throws InterruptedException 
	{
		customerInvoices();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
	}		

	@Test(enabled = true, priority = 3)
	public void clickNewInvoice() throws InterruptedException
	{
		Thread.sleep(3000);
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//body/div[18]/div[11]/div/button[1]/span").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='customerInvoice_customerInvoiceID']")));
	}		

	@Test(enabled = true, priority = 4)
	public void enterCustomerAndTaxTerritory() throws InterruptedException
	{

		Thread.sleep(3000);
		get("//*[@id='customerInvoice_customerInvoiceID']").sendKeys("Advantage Mechanical - CRL");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[24]/li/a")));
		get("//body/ul[24]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_TaxTerritory']").clear();
		get("//*[@id='customerInvoice_TaxTerritory']").sendKeys("Dallas");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[22]/li/a")));
		get("//body/ul[22]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cICheckTab2']/a")));
	}		

	@Test(enabled = true, priority = 5)
	public void addLineItemsAndSaving() throws InterruptedException
	{
		Thread.sleep(3000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(3000);
		get("//*[@id='new_row_itemCode']").sendKeys("MARH11610");
		Thread.sleep(2000);
		get("//body/ul[26]/li/a").click();
		Thread.sleep(2000);
		get("//*[@id='new_row_description']").click();
		get("//*[@id='new_row_description']").sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();


	}		

	@Test(enabled = true, priority = 6)
	public void closeCustomerInvoice() throws InterruptedException
	{	
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='CuInvoiceSaveCloseID']")));
		get("//*[@id='CuInvoiceSaveCloseID']").click();
	}



	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
