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


public class RID467_TaxTerritoryInActiveFunctionality extends Variables {


	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	String PO;


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

	@Test(enabled = false, priority = 1)
	public void taxTerritory_1() throws InterruptedException 
	{
		taxTerritory();	
		Thread.sleep(3000);
		get("//*[@id='addChartlist']").click();
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/table/tbody/tr/td/*[@id='cancelUserButton']").click();
		Thread.sleep(3000);
		get("//*[@id='addChartlist']").click();
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr/td[2]/*[@id='stateID']").sendKeys("TD");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr/td[4]/*[@id='stateCodeID']").sendKeys("TD");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr[2]/td[2]//*[@id='decriptionID']").sendKeys("TESTED");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/table/tbody/tr/td/*[@id='saveUserButton']").click();
		Thread.sleep(3000);
		get("//body/div[14]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//td[@title='TESTED']").click();
		Thread.sleep(3000);
		get("//*[@id='deleteChartOfAccountID']").click();
		Thread.sleep(3000);
		get("//body/div[15]/div[11]/div/button[2]").click();
		Thread.sleep(3000);
		get("//*[@id='deleteChartOfAccountID']").click();
		Thread.sleep(3000);
		get("//body/div[16]/div[11]/div/button[1]").click();
		Thread.sleep(3000);
		get("//body/div[14]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[3]").click();
		get("//*[@id='2']/td[3]").click();
	}


	@Test(enabled = false, priority = 1)
	public void taxTerritory_2() throws InterruptedException  // when tax territory is not InActive
	{
		taxTerritory();	
		Thread.sleep(3000);
		get("//td[@title='Tamil Nadu']").click();
		Thread.sleep(3000);
		if((get("//*[@id='inActiveChkbx']").isSelected()))
		{
			get("//*[@id='inActiveChkbx']").click();
		}
		get("//*[@id='taxTerritoryDetailsDiv']/table/tbody/tr/td[2]/input[@id='saveUserButton']").click();


		//searching specific job and checking the tax territory is populating or not
		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("jobsearch")).sendKeys(Keys.chord("RID 762"));
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(5000);
		get("//*[@id='jobMain_TaxTerritory']").clear();
		get("//*[@id='jobMain_TaxTerritory']").sendKeys("Tamil");
		Thread.sleep(2000);
		get("//body/ul[27]/li/a").click();

		/*		if(get("//body/ul[27]/li/a").isDisplayed())
		{
			System.out.println("TaxTerritory suggestion are displaying");
			get("//body/ul[27]/li/a").click();
		}
		else
		{
			System.out.println("TaxTerritory suggestion is not displaying");
		}
		 */		
	}


	//searching specific job and checking tax territory in stock order
	@Test(enabled = false, priority = 2)
	public void taxTerritory_3() throws InterruptedException  // when tax territory is not InActive
	{

		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("jobsearch")).sendKeys(Keys.chord("EJS170605"));
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(5000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[3]").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("tax");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='taxID]").clear();
		get("//*[@id='taxID]").sendKeys("Tamil");
		Thread.sleep(3000);
		get("//body/ul[27]/li/a[contains(.,'Tamil Nadu')]").click();


	}


	// to check whether tax territory is present or not in Warehouse
	@Test(enabled = false, priority = 2)
	public void taxTerritory_4() throws InterruptedException  
	{
		inventoryWarehouse();
		get("//*[@id='taxTerritory']").click();
		if (get("//*[@id='taxTerritory']").getText().contains("Tamil Nadu"))
		{
			System.out.println("Tax territory is present");
		}
		else
		{
			System.out.println("Tax territory is not present");
		}

	}


	// to check whether tax territory is present or not customer invoice[outside job]
	@Test(enabled = false, priority = 2)
	public void taxTerritory_5() throws InterruptedException  
	{
		customerInvoices();
		get("//*[@id='searchJob']").sendKeys("SSR161227Q1");
		get("//*[@id='goSearchButtonID']").click();


		WebDriverWait wait2 = new WebDriverWait(driver, 15);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));
		Actions act3 = new Actions(driver);
		act3.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().build().perform();
		Thread.sleep(3000);

		get("//*[@id='customerInvoice_TaxTerritory']").clear();
		get("//*[@id='customerInvoice_TaxTerritory']").sendKeys("Tamil");
		Thread.sleep(2000);

		if(get("//body/ul[22]/li/a").getText().contains("Tamil Nadu"))
		{
			System.out.println("Suggestion for Tax territory is showing");	
		}
		else
		{
			System.out.println("Suggestion for Tax territory is not showing");	
		}
	}



	// to check whether tax territory is present or not in customer invoice[inside job]
	@Test(enabled = true, priority = 2)
	public void taxTerritory_6() throws InterruptedException  
	{
		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("jobsearch")).sendKeys(Keys.chord("SSR161227"));
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(5000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(3000);

		
		Actions clickAction = new Actions(driver);
        WebElement scrollablePane = get("//*[@id='gview_release']/div[3]");
        clickAction.moveToElement(scrollablePane).click().build().perform();
        Thread.sleep(2000);
        Actions scrollAction = new Actions(driver);
        scrollAction.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        Thread.sleep(3000);
		
		
		get("//*[@id='release']/tbody/tr[contains(.,'Q')]/td[4]").click();
		Thread.sleep(3000);

		//		get("//*[@id='17']/td[4]").click();
		get("//*[@id='shiping']/tbody/tr[2]/td[9]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_TaxTerritory']").clear();
		get("//*[@id='customerInvoice_TaxTerritory']").sendKeys("Tamil");
		Thread.sleep(3000);
		
		if(get("//body/ul[38]/li/a[contains(.,'Tamil Nadu')]").isDisplayed())
		{
			System.out.println("Suggestions are showing");
		}
		else
		{
			System.out.println("Suggestions are not showing");
		}

	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}





}
