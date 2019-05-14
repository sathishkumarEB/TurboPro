package com.turbopro.modules;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;


public class Release_StockOrder extends Variables {

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

	//Creating a stock order release, adding single line items and saving it; Creating customer invoice for that
	@Test(enabled = false, priority = 2)
	public void use_case1() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("stockorder");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("33");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='promisedID']").click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("3")).click();
		Thread.sleep(2000);
		get("//*[@id='SavePOReleaseID']").click();
		Thread.sleep(3000);
		get("//*[@id='saleslineitems']/a").click();
		Thread.sleep(3000);

		get("//*[@id='new_row_itemCode']").sendKeys("DMRTR221008");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("new_row_quantityOrdered")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
		Thread.sleep(2000);
		get("//*[@id='SaveLineSOReleaseID']").click();
		Thread.sleep(3000);
		get("//*[@id='closeLineSOReleaseID']").click();
		Thread.sleep(2000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//body/div[40]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("5");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Cancel')]").click();

	}


	//Creating a stock order release, adding multiple line items and saving it; Creating customer invoice for that
	@Test(enabled = false, priority = 3)
	public void use_case2() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("stockorder");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("33");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='promisedID']").click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("3")).click();
		Thread.sleep(2000);
		get("//*[@id='SavePOReleaseID']").click();
		Thread.sleep(6000);
		get("//*[@id='saleslineitems']/a").click();
		Thread.sleep(3000);

		for(int i=0;i<5;i++)
		{
			get("//*[@id='new_row_itemCode']").sendKeys("DMRTR221008");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
			Thread.sleep(2000);
		}


		Thread.sleep(3000);
		get("//*[@id='SaveLineSOReleaseID']").click();
		Thread.sleep(3000);
		get("//*[@id='closeLineSOReleaseID']").click();
		Thread.sleep(6000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//body/div[40]/div[11]/div/button/span").click();
		//get("//button[contains(.,'OK')]").click();
		Thread.sleep(4000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("5");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Cancel')]").click();

	}

	//Trying to create vendor  invoice for the stock order release and handling the popup
	@Test(enabled = true, priority = 4)
	public void use_case3() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);
		
		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("stockorder");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("33");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='promisedID']").click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("3")).click();
		Thread.sleep(2000);
		get("//*[@id='SavePOReleaseID']").click();
		Thread.sleep(6000);
		get("//*[@id='saleslineitems']/a").click();
		Thread.sleep(3000);

		get("//*[@id='new_row_itemCode']").sendKeys("DMRTR221008");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("new_row_quantityOrdered")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
		Thread.sleep(2000);
		get("//*[@id='SaveLineSOReleaseID']").click();
		Thread.sleep(3000);
		get("//*[@id='closeLineSOReleaseID']").click();
		Thread.sleep(2000);
		get("//*[@id='1']/td[12]").click();
		
		Thread.sleep(2000);
		
		get("//*[@id='vendorInvoicebtnID']").click();
		Thread.sleep(2000);
		get("//body/div[40]/div[11]/div/button/span").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(2000);
		get("//body/div[40]/div[11]/div/button/span").click();
	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
