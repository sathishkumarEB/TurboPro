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

public class SalesMenu {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public WebDriver driver;

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		//		driver.manage().window().maximize(); 
	}

	@Test
	public void login() throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(3000);
	}


	@Test(enabled = true)
	public void salesBidList() throws InterruptedException 
	{
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(".//*[@id='1']/td[2]"))).doubleClick().build().perform();
		driver.findElement(By.xpath(".//*[@id='viewBLButton']")).click();
		driver.findElement(By.xpath("//span[contains(.,'OK')]")).click();
		driver.findElement(By.xpath(".//*[@id='bidListFromDate']")).clear();
		driver.findElement(By.xpath(".//*[@id='bidListFromDate']")).sendKeys("01/01/2017");
		driver.findElement(By.xpath(".//*[@id='bidListToDate']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "02/02/2017");
		driver.findElement(By.xpath("//*[@id='viewBLButton']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='cancelBLButton']")).click();
	}

	@Test(enabled = true)
	public void upcomingBids() throws InterruptedException 
	{
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody/tr[2]/td[1]/li/div/span/input")).click();
		Thread.sleep(3000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[6]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[7]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='engineer']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[9]/input")).click();
		}
		driver.findElement(By.xpath("//*[@id='myUpcomingCloseIcon']")).click();
	}


	@Test(enabled = true)
	public void pendingJobs() throws InterruptedException 
	{
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody/tr[2]/td[2]//li/div/span/input")).click();
		Thread.sleep(3000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
		}
		driver.findElement(By.xpath("//*[@id='myPendingCloseIcon']")).click();
	}


	@Test(enabled = true)
	public void quotedJobs() throws InterruptedException 
	{
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody/tr[3]/td[2]//li/div/span/input")).click();
		Thread.sleep(5000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
		}
		driver.findElement(By.xpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[6]/input")).click();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.SHIFT,Keys.TAB);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='myQuotedCloseIcon']")).click();
	}


	@Test(enabled = false)
	public void awardedContractors() throws InterruptedException 
	{
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody/tr[3]/td[3]//li/div/span/input")).click();
		Thread.sleep(5000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='displayAwardedContractorsDialog']/div[2]/table/tbody/tr/td[3]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayAwardedContractorsDialog']/div[2]/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayAwardedContractorsDialog']/div[2]/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayAwardedContractorsDialog']/div[2]/table/tbody/tr/td[6]/input")).click();
			Thread.sleep(3000);
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='myAwardedCloseIcon']")).click();
	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}

//Script for navigating to Users module and searching a user 
