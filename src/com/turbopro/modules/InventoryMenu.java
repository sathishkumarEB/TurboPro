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

public class InventoryMenu extends Variables {
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
	public void Inventorymenu() throws InterruptedException 
	{
		login();
		driver.findElement(By.id("mainmenuInventoryPage")).click();
		Thread.sleep(3000);

		for (int i=1; i<=4; i++)
		{
			driver.findElement(By.id("bankAccountsID")).click();
			Thread.sleep(3000);
			get("//*[@id='bankAccountsID']/option["+i+"]").click();
			Thread.sleep(6000);
		}

		for(int i = 1; i<=2; i++)
		{
			get("//*[@id='jqgh_inventoryGrid_itemCode']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_description']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_prDepartment']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_prCategory']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_vendorName']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_inventoryOnHand']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_inventoryAllocated']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_inventoryAvailable']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_inventoryOnOrder']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryGrid_submitted']").click();
			Thread.sleep(2000);
		}

		driver.findElement(By.id("searchJob")).sendKeys("DMRR0604");
		get("//*[@id='search']/table/tbody/tr/td[2]/input[1]").click();
		Thread.sleep(4000);
	}


	@Test( enabled = true, priority = 2)
	public void inventoryCategories() throws InterruptedException 
	{
		WebElement inventory = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(inventory).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[1]/a").click(); 	//navigating to inventory categories

		Thread.sleep(4000);
		get("//*[@id='1']/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='categoryInactive']").click();
		Thread.sleep(2000);
		get("//*[@id='inventoryCategoriesDetails']/fieldset/div[2]/input[3]").click();
		Thread.sleep(2000);
		get("//*[@id='categoryInactive']").click();
		Thread.sleep(2000);
		get("//*[@id='inventoryCategoriesDetails']/fieldset/div[2]/input[3]").click();
		Thread.sleep(2000);
		get("//*[@id='inventoryCategoriesDetails']/fieldset/div[2]/input[1]").click();
		Thread.sleep(2000);
		get("//*[@id='inventoryCategoriesDetails']/fieldset/div[2]/input[2]").click();
	}

	@Test( enabled = true, priority = 3)
	public void inventoryWarehouses() throws InterruptedException 
	{
		driver.navigate().refresh();
		WebElement inventory1 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action1 = new Actions(driver);
		action1.moveToElement(inventory1).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[2]/a").click(); 					//navigating to inventory warehouses

		get("//*[@id='1']/td[2]").click();
		Thread.sleep(2000);
		get("//*[@id='2']/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='3']/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='4']/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='warehouseDetails']/fieldset/input[2]").click();
		Thread.sleep(2000);
		get("//*[@id='warehouseDlg']").click();
		Thread.sleep(2000);
		get("//*[@id='saveTermsButton']").click();
		Thread.sleep(2000);
		get("//body/div[9]/div[1]/a").click();
		Thread.sleep(2000);
	}


	@Test( enabled = true, priority = 4)
	public void inventoryReceiveInventory() throws InterruptedException 
	{

		WebElement inventory2 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action2 = new Actions(driver);
		action2.moveToElement(inventory2).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[3]/a").click();		//navigating to receive inventory
		get("//*[@id='searchJob']").sendKeys("101770");
		get("//*[@id='search']/table/tbody/tr/td[2]/input[1]").click();
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().build().perform();
		Thread.sleep(2000);
		get("//*[@id='POCloseID']").click();
		Thread.sleep(2000);
		get("//*[@id='dateRange']").click();
		get("//*[@id='fromDate']").sendKeys("01/01/2017");
		get("//*[@id='toDate']").sendKeys("05/31/2017");
		Thread.sleep(4000);
		get("//*[@id='resetbutton']").click();

	}


	@Test( enabled = true, priority = 5)
	public void inventoryTransfer() throws InterruptedException 
	{
		WebElement inventory3 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action3 = new Actions(driver);
		action3.moveToElement(inventory3).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[4]/a").click();		//navigating to inventory transfer
		get("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[1]/input").click();
		get("//*[@id='transferDateId']").click();
		get("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a").click();
		Thread.sleep(2000);
		get("//*[@id='estDateId']").click();
		get("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[6]/a").click();
		Thread.sleep(2000);
		get("//*[@id='ref']").sendKeys("testing");
		get("//*[@id='warehouseFrom']").click();
		get("//*[@id='warehouseFrom']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='warehouseTo']").click();
		get("//*[@id='warehouseTo']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='WarehouseTransferReceiveID']").click();
		get("//*[@id='add_addtransferGrid']/div/span").click();
		Thread.sleep(3000);
		get("//*[@id='itemCode']").sendKeys("DMSR1010");
		Thread.sleep(5000);
		get("//body/ul[14]/li").click();
		Thread.sleep(2000);
		get("//*[@id='quantityTransfered']").sendKeys("1");
		get("//*[@id='sData']").click();
		Thread.sleep(3000);
		//get("//div[contains(text(), 'Save & Close')]")).click();
		//Thread.sleep(3000);

		//get("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[4]/input")).click();


	}

	@Test( enabled = true, priority = 6)
	public void inventoryOrderPoints() throws InterruptedException 
	{
		WebElement inventory4 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action4 = new Actions(driver);
		action4.moveToElement(inventory4).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[5]/a").click();		//navigating to inventory order points
		get("//*[@id='searchJob']").sendKeys("DMRR0605");
		get("//*[@id='search']/table/tbody/tr/td[2]/input[1]").click();
		Thread.sleep(3000);
		Actions act1 = new Actions(driver);
		act1.moveToElement(get("//*[@id='chartsOfOrderPointsGrid']/tbody/tr[2]/td[9]")).doubleClick().build().perform();
		Thread.sleep(2000);
		get("//*[@id='chartsOfOrderPointsGrid']/tbody/tr[2]/td[9]/input").clear();
		get("//*[@id='chartsOfOrderPointsGrid']/tbody/tr[2]/td[9]/input").sendKeys("3");
		Thread.sleep(2000);
		get("//*[@id='chartsOfOrderPointsGrid']/tbody/tr[2]/td[9]/input").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		get("//*[@id='resetbutton']").click();


		//suggested reorder
		get("//*[@id='suggestedButton']").click();
		Thread.sleep(4000);
		get("//*[@id='orderPointPrint']").click(); //new tab will open, manually navigate to previous tab
		Thread.sleep(2000);
		get("//body/div[9]/div[1]/a/span").click();

		//checking different warehouse 
		get("//*[@id='chartsOfWarehouseGrid']/tbody/tr[3]/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='chartsOfWarehouseGrid']/tbody/tr[4]/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='chartsOfWarehouseGrid']/tbody/tr[5]/td[3]").click();
		Thread.sleep(2000);
		get("//*[@id='resetbutton']").click();
	}


	@Test( enabled = true, priority = 7)
	public void inventoryValue() throws InterruptedException 
	{
		WebElement inventory5 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action5 = new Actions(driver);
		action5.moveToElement(inventory5).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[6]/a").click();		//navigating to inventory value

		for(int i = 1; i<=2; i++)
		{
			get("//*[@id='jqgh_inventoryValueGrid_itemCode']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_description']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_prDepartment']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_prCategory']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_vendorName']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_inventoryOnHand']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_averageCost']").click();
			Thread.sleep(2000);
			get("//*[@id='jqgh_inventoryValueGrid_totalCost']").click();
			Thread.sleep(2000);
		}

		get("//body/div[1]/table[1]/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/img").click();		
	}	

	@Test( enabled = true, priority = 8)
	public void inventoryCount() throws InterruptedException 
	{
		WebElement inventory6 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action6 = new Actions(driver);
		action6.moveToElement(inventory6).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[7]/a").click();		//navigating to inventory count
		get("//*[@id='warehouseListID']").click();
		get("//*[@id='warehouseListID']/option[2]").click();
		Thread.sleep(4000);
		get("//*[@id='sortId']").click();
		get("//*[@id='sortId']/option[2]").click();
		Thread.sleep(4000);

		Actions act2 = new Actions(driver);
		act2.moveToElement(get("//*[@id='2']/td[13]")).doubleClick().build().perform();
		Thread.sleep(2000);
		get("//*[@id='2_Counted']").clear();
		get("//*[@id='2_Counted']").sendKeys("3");
		Thread.sleep(2000);
		get("//*[@id='2_Counted']").sendKeys(Keys.ENTER);
		get("//*[@id='saveCountInventoryButtonID']").click();
		Thread.sleep(5000);
		get("//*[@id='getPdfButton']").click();
		Thread.sleep(5000);
		get("//*[@id='accountsPayableImgID']/img").click();
	}

	@Test( enabled = true, priority = 9)
	public void inventoryAdjustments() throws InterruptedException 
	{
		WebElement inventory7 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action7 = new Actions(driver);
		action7.moveToElement(inventory7).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[9]/a").click();		//navigating to inventory adjustments
		get("//*[@id='transferDateID']").click();
		get("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[3]/a").click();
		Thread.sleep(2000);		
		get("//*[@id='warehouseListID']").click();
		Thread.sleep(2000);		
		get("//*[@id='warehouseListID']/option[2]").click();
		Thread.sleep(2000);		
		get("//*[@id='referenceID']").sendKeys("auto");
		Thread.sleep(2000);		
		get("//*[@id='reasonCodeID']").click();
		Thread.sleep(2000);		
		get("//*[@id='reasonCodeID']/option[3]").click();
		Thread.sleep(2000);		
		get("//*[@id='chartsOfTransferListGrid_iladd']/div/span").click();
		Thread.sleep(2000);		
		get("//*[@id='new_row_itemCode']").sendKeys("MA3100A08");
		Thread.sleep(2000);		
		get("//body/ul[13]/li[1]").click();
		get("//*[@id='new_row_quantityTransfered']").sendKeys("13");
		get("//*[@id='new_row_quantityTransfered']").sendKeys(Keys.ENTER);
		get("//*[@id='chartsOfTransferListGrid_ilsave']/div").click();
		Thread.sleep(4000);		
		get("//*[@id='saveIAButtonID']").click();
	}

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
