package com.turbopro.modules;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class CustomerStatements extends Variables{

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
	public void usecase1() throws InterruptedException 
	{
		login();
		Thread.sleep(3000);
		customerStatements();

		get("//*[@id='stCustomerID']").click();


		get("//option[contains(.,'Venture Mechanical - *CSY')]").click();

		if(get("//*[@id='jobBasedSorting']").isEnabled())
		{
			System.out.println("jobenabled");
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='dateSorting']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='customerPO']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='jobBasedSorting']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		}

		else
		{
			System.out.println("jobdisabled");
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='dateSorting']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='customerPO']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
			get("//*[@id='jobBasedSorting']").click();
			get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		}
	}

	
	@Test(enabled = true, priority = 2)
	public void usecase2() throws InterruptedException 
	{
		companySettings();
		
		get("//*[@id='settingsCustmerDetails']/a").click();
		
		get("//*[@id='chk_cusStGpbyJobYes'][contains(@value,'yes')]").click();
		Thread.sleep(2000);
		get("//*[@id='settingsCustmerDetailsBlock']/table/tbody/tr[5]/td/fieldset/div/input").click();
		Thread.sleep(2000);
		customerStatements();
		Thread.sleep(2000);
		get("//*[@id='stCustomerID']").click();
		Thread.sleep(2000);
		get("//option[contains(.,'Venture Mechanical - *CSY')]").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		Thread.sleep(2000);
		get("//*[@id='dateSorting']").click();
		Thread.sleep(2000);get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		get("//*[@id='customerPO']").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		
		
		
		
	}
	
	
	@Test(enabled = true, priority = 3)
	public void usecase3() throws InterruptedException 
	{
		companySettings();
		
		get("//*[@id='settingsCustmerDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='chk_cusStGpbyJobYes'][contains(@value,'no')]").click();
		Thread.sleep(2000);
		get("//*[@id='settingsCustmerDetailsBlock']/table/tbody/tr[5]/td/fieldset/div/input").click();
		Thread.sleep(2000);
		customerStatements();
		Thread.sleep(2000);
		get("//*[@id='stCustomerID']").click();
		Thread.sleep(2000);
		get("//option[contains(.,'Venture Mechanical - *CSY')]").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		Thread.sleep(2000);
		get("//*[@id='dateSorting']").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		Thread.sleep(2000);
		get("//*[@id='customerPO']").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
		Thread.sleep(2000);
		get("//*[@id='jobBasedSorting']").click();
		Thread.sleep(2000);
		get("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]").click();
	}
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}
