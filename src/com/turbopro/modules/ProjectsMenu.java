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

public class ProjectsMenu {
	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	public WebDriver driver;

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}

	@Test(enabled = true, priority =1)
	public void login() throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(3000);
	}


	@Test(enabled = true, priority = 2)
	public void customerList() throws InterruptedException 
	{
		Thread.sleep(5000);
		driver.findElement(By.id("mainMenuProjectsPage")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[@value='Customer List']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='myCloseIcon']")).click();
		Thread.sleep(2000);
	}

	@Test(enabled = true, priority =3)
	public void accountsReceivable() throws InterruptedException 
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='ui-jqgrid-bdiv']//table[@id='viewsGrid']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//*[@id='closear']")).click();
	}	


	@Test(enabled = true, priority = 4)
	public void openJobs() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@id='mainMenuProjectsPage']/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='openJobscustomerName']")).sendKeys("Vixxo");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/ul[14]/li[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//body/div[15]/div[1]/div/input[6]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='daterange']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rangefromdate']")).sendKeys("01/01/2017");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='todate']")).sendKeys("05/31/2017");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='openJobscustomerName']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[15]/div[1]/div/input[6]")).click();
		Thread.sleep(2000);
		
	for(int i = 1; i<=2; i++)
	{
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_customerName']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_jobName']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_jobNumber']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_contractAmount']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_allocated']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_invoiced']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='jqgh_openJobsGridpop_unreleased']")).click();
		Thread.sleep(3000);
	}
	
	driver.findElement(By.xpath("//*[@id='closedialog']")).click();
	
	}


	@Test(enabled = true, priority =5)
	public void openPO_SO() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@id='mainMenuProjectsPage']/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='daterangePoSo']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rangefromdatePoSo']")).sendKeys("01/01/2017");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='todatePoSo']")).sendKeys("05/31/2017");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[16]/div[1]/div/input[6]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='openJobscustomerNamePoSo']")).sendKeys("greenheck");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/ul[15]/li[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//body/div[16]/div[1]/div/input[6]")).click();
		
		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='jqgh_poSoGrid_dateWanted']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='jqgh_poSoGrid_ponumber']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='jqgh_poSoGrid_vendorName']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='jqgh_poSoGrid_vendorOrderNumber']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='jqgh_poSoGrid_shipDate']")).click();
			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//*[@id='closedialogPoSo']/font")).click();
		
	}
	
	
	@Test(enabled = true, priority = 6)
	public void customerProfitMargin() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@id='mainMenuProjectsPage']/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='daterange']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rangefromdate']")).sendKeys("01/01/2017");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='todate']")).sendKeys("05/31/2017");
		Thread.sleep(2000);
		
		
	}
	

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}

//Script for Projects menu 
