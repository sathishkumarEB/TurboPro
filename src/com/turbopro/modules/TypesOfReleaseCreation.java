package com.turbopro.modules;

import java.util.Scanner;
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

public class TypesOfReleaseCreation extends Variables {

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
	public void dropShipRelease() throws InterruptedException 
	{
		login();
		CreateJobUsingQuickBook();

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();


		Thread.sleep(3000);
		get("//*[@id='new_row_note']").sendKeys("ma310010");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);

		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]")).doubleClick().perform();

		get("//*[@id='new_row_quantityOrdered']").sendKeys("10" + Keys.ENTER);


		Thread.sleep(3000);
		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//body/div[39]/div[11]/div/button[1]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(3000);
		get("//body/div[39]/div[11]/div/button[2]").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//body/div[25]/div[1]/a/span").click();
		Thread.sleep(3000);

		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
	}

	@Test(enabled = true, priority = 1)
	public void stockOrderRelease() throws InterruptedException 
	{

		login();
		CreateJobUsingQuickBook();
		Thread.sleep(3000);
		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);


		get("//*[@id='promisedID']").click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("13")).click();
		Thread.sleep(3000);
		get("//*[@id='SavePOReleaseID']").click();
		Thread.sleep(3000);

		get("//*[@id='saleslineitems']/a").click();
		Thread.sleep(3000);
		get("//*[@id='new_row_itemCode']").sendKeys("MARH10806");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);

		get("//*[@id='new_row_description']").sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//		if(get("//body/div[43]/div[1]").isDisplayed()) {
		//			get("//button[contains(.,'Close')]").click();
		//		}

		get("//*[@id='SaveLineSOReleaseID']").click(); 
		Thread.sleep(3000);
		get("//*[@id='closeLineSOReleaseID']").click(); 
		Thread.sleep(3000);
		get("//*[contains(@title, 'Stock Order')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
		get("//button[contains(.,'Cancel')]").click();


	}

	@Test(enabled = false, priority = 1)
	public void billOnlyRelease() throws InterruptedException 
	{

		login();
		CreateJobUsingQuickBook();
		Thread.sleep(3000);
		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[4]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);

	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
