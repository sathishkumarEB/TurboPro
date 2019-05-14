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


public class SortFunctionality extends Variables {
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

	//navigating to upcoming bids under sales and applying sort
	@Test(enabled = true, priority = 2)
	public void upcomingbidsort() throws InterruptedException 
	{
		salesTab();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[2]/td[1]/li/div/span/input")));
		driver.findElement(By.xpath("//tbody/tr[2]/td[1]/li/div/span/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[4]/input")));
		//for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//input[@value='Assigned Salesman']")).click();
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





		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_bidDate")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_jobName")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_jobNo")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_assignedSalesman")).click();

			driver.findElement(By.id("UpcomingBidsGridDialog_assignedCustomers")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_allCustomer")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_architect")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_engineer")).click();

			driver.findElement(By.id("jqgh_UpcomingBidsGridDialog_generalContractor")).click();
		}

		{
			driver.findElement(By.xpath("//input[@value='Assigned Salesman']")).click();
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

	//navigating to pending jobs under sales and applying sort
	@Test(enabled = true, priority = 3)
	public void pendingJobSort() throws InterruptedException 

	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[2]/td[2]//li/div/span/input")));
		driver.findElement(By.xpath("//tbody/tr[2]/td[2]//li/div/span/input")).click();
		Thread.sleep(3000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
		}

		{
			driver.findElement(By.id("jqgh_PendingQuoteGridDialog_bidDate")).click();
			driver.findElement(By.id("jqgh_PendingQuoteGridDialog_jobName")).click();
			driver.findElement(By.id("jqgh_PendingQuoteGridDialog_jobNo")).click();
			driver.findElement(By.id("jqgh_PendingQuoteGridDialog_assignedCustomers")).click();
			driver.findElement(By.id("jqgh_PendingQuoteGridDialog_quoteAmount")).click();
		}



		driver.findElement(By.xpath("//*[@id='myPendingCloseIcon']")).click();	
	}

	//navigating to quoted job under sales and applying sort
	@Test(enabled = true, priority = 4)
	public void quotedJobSort() throws InterruptedException 

	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[3]/td[2]//li/div/span/input")));
		driver.findElement(By.xpath("//tbody/tr[3]/td[2]//li/div/span/input")).click();
		Thread.sleep(5000);
		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.xpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[4]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[5]/input")).click();
			Thread.sleep(3000);
		}

		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_quotedJobGridDialog_bidDate")).click();
			driver.findElement(By.id("jqgh_quotedJobGridDialog_jobName")).click();
			driver.findElement(By.id("jqgh_quotedJobGridDialog_jobNo")).click();
			driver.findElement(By.id("jqgh_quotedJobGridDialog_assignedCustomers")).click();
			driver.findElement(By.id("jqgh_quotedJobGridDialog_quoteAmount")).click();

		}


		driver.findElement(By.xpath("//*[@id='myQuotedCloseIcon']")).click();

	}

	//navigating to awarded contractors under sales and applying sort
	@Test(enabled = true, priority = 5)
	public void awardedContractorsSort() throws InterruptedException 

	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[3]/td[3]//li/div/span/input")));

		driver.findElement(By.xpath("//tbody/tr[3]/td[3]//li/div/span/input")).click();
		Thread.sleep(5000);
		//	for (int i=1; i<=2; i++)
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

		for (int i=1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_jobNo")).click();	
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_jobName")).click();	
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_generalContractor")).click();	
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_engineer")).click();	
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_architect")).click();	
			driver.findElement(By.id("jqgh_AwardedContractorsDialog_lowbidder")).click();	
		}

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='myAwardedCloseIcon']")).click();

	}

	//navigating to open jobs under projects and applying sort 
	@Test(enabled = true, priority = 5)
	public void openJobsProjectSort() throws InterruptedException 

	{
		driver.findElement(By.id("mainMenuProjectsPage")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));

		driver.findElement(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")).click();

		for (int i =1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_openJobsGridpop_customerName")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_jobName")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_jobNumber")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_contractAmount")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_allocated")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_invoiced")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_openJobsGridpop")));
			driver.findElement(By.id("jqgh_openJobsGridpop_unreleased")).click();
		}

		driver.findElement(By.xpath("//*[@id='closedialog']")).click();

	}

	//navigating to open purchase order popup and applying sort 
	@Test(enabled = true, priority = 6)
	public void openPOSort() throws InterruptedException 

	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		driver.findElement(By.xpath("//*[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_poSoGrid")));
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

	//navigating to customer profit margin popup and applying sort 
	@Test(enabled = true, priority = 7)
	public void customerProfitMarginSort() throws InterruptedException 

	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")));
		driver.findElement(By.xpath("//*[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")).click();

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_customerName")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_sales")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_profit")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_margin")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_margin1")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CustomerMarginGirdpop_margin2")).click();
		}
		driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-CustomerMarginGirdpopup']/div/div/span[@id='closedialog']")).click(); 
	}

	//navigating to accounts receivable popup and applying sort
	@Test(enabled = true, priority = 8)
	public void accountsReceivableSort() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='ui-jqgrid-bdiv']//table[@id='viewsGrid']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Thread.sleep(9000);

		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_AccountReceivableGrid_customerName")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));
			driver.findElement(By.id("jqgh_AccountReceivableGrid_currentAmt")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));
			driver.findElement(By.id("jqgh_AccountReceivableGrid_thirtyDays")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));
			driver.findElement(By.id("jqgh_AccountReceivableGrid_sixtyDays")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));
			driver.findElement(By.id("jqgh_AccountReceivableGrid_ninetyDays")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("load_AccountReceivableGrid")));
			driver.findElement(By.id("jqgh_AccountReceivableGrid_totalDaysAmt")).click();
		}

		driver.findElement(By.xpath("//*[@id='closear']")).click();
	}

	//navigating to inventory list and applying sort 
	@Test(enabled = true, priority = 9)
	public void inventorySort() throws InterruptedException
	{
		Inventory();
		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_inventoryGrid_itemCode")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_description")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_prDepartment")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_prCategory")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_vendorName")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_inventoryOnHand")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_inventoryAllocated")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_inventoryAvailable")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_inventoryOnOrder")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryGrid_submitted")).click();
		}
	}

	//navigating to inventory categories and applying sort 
	@Test(enabled = true, priority = 9)
	public void inventoryCategoriesSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[1]/a").click(); 		

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_inventoryCategoriesGrid_description")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryCategoriesGrid_inActive")).click();
		}

	}

	//navigating to receive inventory and applying sort 
	@Test(enabled = true, priority = 10)
	public void receiveInventorySort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[3]/a").click(); 		

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_lineItemGrid_createdOn")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_lineItemGrid_ponumber")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_lineItemGrid_vendorName")).click();
		}

	}

	//navigating to inventory transfer list and applying sort
	@Test(enabled = true, priority = 11)
	public void inventoryTransferSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[4]/a").click(); 		

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_transferGrid_transferDate")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_transferGrid_transactionNumber")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_transferGrid_desc")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_transferGrid_from")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_transferGrid_to")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_transferGrid_receivedDate")).click();
		}

	}

	//navigating to inventory orders list and applying sort
	@Test(enabled = true, priority = 11)
	public void inventoryOrderPointSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[5]/a").click(); 		

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_itemCode")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_description")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_prDepartment")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_prCategory")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_inventoryAvailable")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_inventoryOnOrder")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_inventoryOrderPoint")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_inventoryOrderQuantity")).click();
		}

	}

	//navigating to inventory values list and apply sort
	@Test(enabled = true, priority = 11)
	public void inventoryValueSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[6]/a").click(); 		

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_inventoryValueGrid_itemCode")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_description")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_prDepartment")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_prCategory")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_vendorName")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_inventoryOnHand")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_averageCost")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("jqgh_inventoryValueGrid_totalCost")).click();
		}
	}

	//navigating to inventory counts list and applying sort
	@Test(enabled = true, priority = 11)
	public void inventoryCountSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[7]/a").click(); 		

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouseListID")));

		driver.findElement(By.id("warehouseListID")).click();
		driver.findElement(By.xpath("//*[@id='warehouseListID']/option[@value='1']")).click();

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_CountInventoryGrid_itemCode")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_description")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_primaryVendorName")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_prDepartment")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_prCategory")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_inventoryOnHand")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_CountInventoryGrid_Counted")).click();
		}
	}

	//navigating to inventory transactions list and applying sort
	@Test(enabled = true, priority = 12)
	public void inventoryTransactionsSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("mainmenuInventoryPage"))).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[8]/a").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));

		driver.findElement(By.id("searchJob")).sendKeys("DMRR0604");
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		Thread.sleep(3000);

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_createdOn")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_ponumber")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_jobName")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_quantityOrdered")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_difference")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_inventoryOnHand")).click();
			driver.findElement(By.id("jqgh_InventoryTransactionGrid_subtotal")).click();
		}

	}

	//navigating to customer list and applying sort
	@Test(enabled = true, priority = 13)
	public void customerSort() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"))).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]")).click();
		Thread.sleep(5000);

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_customersGrid_name")).click();
			driver.findElement(By.id("jqgh_customersGrid_phone1")).click();
			driver.findElement(By.id("jqgh_customersGrid_address1")).click();
			driver.findElement(By.id("jqgh_customersGrid_city")).click();
			driver.findElement(By.id("jqgh_customersGrid_state")).click();
		}
	}

	//navigating to customer payments list and applying sort
	@Test(enabled = true, priority = 14)
	public void customerPaymentsSort() throws InterruptedException
	{
		customerPayments();
		Thread.sleep(5000);
		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_customerpaymentlist_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_customer")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_reference")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_memo")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_amount")).click();
		}

		//sort inside add customer payments 
		driver.findElement(By.id("addpaymentlist")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("payCustomer")));

		driver.findElement(By.id("payCustomer")).clear();
		driver.findElement(By.id("payCustomer")).sendKeys("Credit Card Sales");
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		Thread.sleep(3000);

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_customerPaymets_payBill")).click();
			driver.findElement(By.id("jqgh_customerPaymets_invoiceNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_customerPoNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerPaymets_invoiceBalance")).click();
			driver.findElement(By.id("jqgh_customerPaymets_preDiscountUsed")).click();
			driver.findElement(By.id("jqgh_customerPaymets_paymentApplied")).click();
			driver.findElement(By.id("jqgh_customerPaymets_remaining")).click();
			driver.findElement(By.id("jqgh_customerPaymets_datePaids")).click();
		}

		driver.findElement(By.id("cancelPaymentId")).click();
	}


	//navigating to customer unapplied payments and applying sort
	public void customerUnappliedPaymentSort() throws InterruptedException	
	{
		customerUnappliedPayments();
		Thread.sleep(3000);
		for(int i=1; i<=2; i++)		
		{
			driver.findElement(By.id("jqgh_customerpaymentlist_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_customer")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_reference")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_memo")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_paymentApplied")).click();
		}

	}

	//navigating to sales order under customer and applying sort
	public void salesOrderSort() throws InterruptedException
	{
		salesOrder();	
		Thread.sleep(3000);
		for(int i=1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_SalesOrdersGrid_createdOn")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_ponumber")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_vendorName")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_jobName")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_costtotal")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_subtotal")).click();
			driver.findElement(By.id("jqgh_SalesOrdersGrid_difference")).click();
		}
	}

	//navigate to customer invoices and applying sort 
	public void customerInvoicesSort() throws InterruptedException
	{
		customerInvoices();
		Thread.sleep(3000);
		
		for(int i=0; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_createdOn")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_customerPonumber")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_invoiceNumber")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_jobname")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_quickJobName")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_invoiceAmount")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_chkNo")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_datePaid")).click();
			driver.findElement(By.id("jqgh_CustomerInvoiceGrid_sentEmailDate")).click();
		}
	}
	
	//navigate to credit debit memo and applying sort 
	public void creditDebitMemoSort() throws InterruptedException
	{
		creditDebitMemo();
		Thread.sleep(3000);
		
		for(int i=0; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_creditDebitMemosGrid_reference")).click();
			driver.findElement(By.id("jqgh_creditDebitMemosGrid_customer")).click();
			driver.findElement(By.id("jqgh_creditDebitMemosGrid_receiptDate")).click();
			driver.findElement(By.id("jqgh_creditDebitMemosGrid_amount")).click();
		}
	}
	
	//navigate to vendors list and apply sort 
//	public void vendorsListSort() throws InterruptedException
//	{
//		vendors();
//		
//	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
