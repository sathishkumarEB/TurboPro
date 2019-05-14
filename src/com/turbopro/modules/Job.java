package com.turbopro.modules;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class Job extends Variables{

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

	@Test(enabled = false, priority = 1)
	public void CreateNewJob() throws InterruptedException 
	{
		login();

		get(newJob).click();
		//		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[5]/td[2]/label[1]/a")).click();

		get("//*[@id='mainsave']").click();
		Thread.sleep(2000);
		get("//*[@id='jobHeader_JobName_id']").sendKeys("tester1");
		Thread.sleep(2000);
		get("//*[@id='mainsave']").click();
		Thread.sleep(2000);
		get("//*[@id='jobStatusList']").click();
		get("//*[@id='jobStatusList']/option[6]").click();
		get("//body/div[13]/div[11]/div/button").click();
		get("//*[@id='jobMain_TaxTerritory']").sendKeys("Austin");
		Thread.sleep(2000);
		get("//body/ul[26]/li/a").click();
		Thread.sleep(4000);
		get("//*[@id='mainsave']").click();
		get("//*[@id='jobStatusList']").click();
		get("//*[@id='jobStatusList']/option[6]").click();
		Thread.sleep(3000);
		get("//div[14]//button[contains(.,'OK')]").click();

		Thread.sleep(3000);
		get("//*[@id='jobMain_salesRepsList']").sendKeys("sam");
		Thread.sleep(2000);
		get("//body/ul[16]/li[1]/a").click();
		get("//*[@id='jobStatusList']").click();
		get("//*[@id='jobStatusList']/option[6]").click();
		Thread.sleep(3000);
		get("//div[15]//button[contains(.,'OK')]").click();
		get("//*[@id='customerNameFieldID']").sendKeys("venture mechanical");
		Thread.sleep(3000);
		get("//body/ul[14]/li[1]/a").click();
		get("//*[@id='jobStatusList']").click();
		get("//*[@id='jobStatusList']/option[6]").click();

	}


	@Test(enabled = false, priority = 2)
	public void CreateJobUsingQuickBook() throws InterruptedException 
	{

		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[5]/td[2]/label[3]/a").click();
		get("//*[@id='QBSaveID']").click();
		get("//*[@id='Quickbookprojectid']").sendKeys("test");

		get("//*[@id='QBSaveID']").click();
		get("//*[@id='QuickbookDivisionid']").click();
		get("//*[@id='QuickbookDivisionid']/option[2]").click();
		get("//*[@id='QBSaveID']").click();
		get("//*[@id='QuickbookCustomer_name']").sendKeys("venture mechanical");
		Thread.sleep(3000);
		get("//body/ul[18]/li[1]/a").click();
		get("//*[@id='QBSaveID']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-1']/table[2]/tbody/tr[2]/td/input[2]").click();
		Thread.sleep(2000);
		get("//body/div[13]/div[11]/div/button").click();  //deleting the job


	}


	@Test(enabled = false, priority = 3)
	public void SearchingSpecificJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("jobsearch")).sendKeys(Keys.chord("feb20"));
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();

	}

	@Test(enabled = false, priority = 4)
	public void SearchingBookedJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='bookednameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}

	@Test(enabled = false, priority = 5)
	public void SearchingClosedJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='closednameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}

	@Test(enabled = true, priority = 6)
	public void SearchingQuotedJob() throws InterruptedException 
	{
		login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='quotenameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	@Test(enabled = true, priority = 7)
	public void SearchingBidJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='bidnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	@Test(enabled = true, priority = 8)
	public void SearchingSubmittedJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='submittednameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	
	
	@Test(enabled = true, priority = 9)
	public void SearchingBudgetJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='budgetnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	
	@Test(enabled = true, priority = 10)
	public void SearchingPlanningJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='planningnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	
	@Test(enabled = true, priority = 11)
	public void SearchingLostJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='lostnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);

	}
	
	@Test(enabled = true, priority = 12)
	public void SearchingAbondonedJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='abondonednameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);
	}

	@Test(enabled = true, priority = 13)
	public void SearchingRejectJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='rejectnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);
	}
	
	
	@Test(enabled = true, priority = 14)
	public void SearchingOverBudgetJob() throws InterruptedException 
	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();
		get("//*[@id='overBudgetnameID']").click();
		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click();
		Thread.sleep(5000);
	}
	
	
	
	
	
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}



}
