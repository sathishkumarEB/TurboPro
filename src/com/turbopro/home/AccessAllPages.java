package com.turbopro.home;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.openqa.selenium.htmlunit;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;	
import com.gargoylesoftware.htmlunit.BrowserVersion;

public class AccessAllPages extends Methods {


	//	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();

	HtmlUnitDriver driver;
	//	WebDriver driver=null;
	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{

		try
		{
			srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));

			
			//			driver = openHtmlBrowser();
						openChromeBrowser();
			//			openPhantomBrowser();



			//			 driver = new HtmlUnitDriver();

			Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
			UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
			Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		}
		catch (Exception e) 
		{
			System.out.println("Exception occurred:" +e);
		}


	}

	private int ColumnNumber(HSSFWorkbook Hwb,int sheetNum, int RowCount,String ColumnHeader) throws Exception
	{			
		int patchColumn = -1;
		for (int cn=0; cn<Hwb.getSheetAt(sheetNum).getRow(RowCount).getLastCellNum(); cn++) {
			Cell c = Hwb.getSheetAt(sheetNum).getRow(RowCount).getCell(cn);
			if (c.toString() == null) {
				// Can't be this cell - it's empty
				continue;
			}
			else {
				String text = c.toString();
				if (ColumnHeader.equalsIgnoreCase(text)) {
					patchColumn = cn;
					break;
				}
			}
		}
		if (patchColumn == -1) {
			throw new Exception("None of the cells in the first row were Patch");
		} 
		else
			return patchColumn;
	}

	/*Logging into the application*/
	@Test(enabled = true, priority = 1)	
	public void accessAllPages() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sales")));
		Thread.sleep(3000);
		
		navigateSales();
//		driver.findElement(By.linkText("Sales")).click();
		Thread.sleep(5000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		driver.findElement(By.linkText("Projects")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Banking")));
		driver.findElement(By.linkText("Banking")).click();

		navigateBankWriteChecks();
		try{
			if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
			{
				driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Write checks popup not showing!");
		}

		Thread.sleep(2000);
		navigateBankReissueChecks();
		Thread.sleep(2000);
		navigateBankReconcileAccounts();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
		driver.findElement(By.linkText("Inventory")).click();
		Thread.sleep(4000);
		navigateInventoryCategories();
		Thread.sleep(3000);
		navigateInventoryWarehouses();
		Thread.sleep(3000);
		receiveInventory();
		Thread.sleep(3000);
		navigateInventoryTransfer();
		Thread.sleep(3000);
		navigateInventoryOrderPoints();
		Thread.sleep(3000);
		navigateInventoryValue();
		Thread.sleep(3000);
		navigateInventoryCount();
		Thread.sleep(3000);
		navigateInventoryTransactions();
		Thread.sleep(3000);
		navigateInventoryAdjustment();
		Thread.sleep(3000);
		navigateCustomers();
		Thread.sleep(3000);
		navigateCustomerPayments();
		Thread.sleep(3000);
		navigateCusUnappliedPayments();
		Thread.sleep(3000);
		navigateCustomerStatement();
		Thread.sleep(3000);
		navigateCustomerSalesOrder();
		Thread.sleep(3000);
		navigateCustomerInvoice();
		Thread.sleep(3000);
		navigateCustomerFinanceCharges();
		Thread.sleep(3000);
		navigateCustomerTaxAdjustments();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Tax Adjustments']")).isDisplayed())
		{
			driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		}

		Thread.sleep(3000);
		navigateCustomerCreditDebitMemo();
		Thread.sleep(3000);
		navigateCustomerAddCostImport();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Add Cost Import']")).isDisplayed())
		{
			driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		}
		Thread.sleep(3000);
		navigateCustomerSalesOrderTemplate();
		Thread.sleep(3000);
		navigateVendors();
		Thread.sleep(3000);
		navigateVendorPurchaseOrders();
		Thread.sleep(3000);
		navigateVendorPayBills();
		Thread.sleep(3000);
		navigateVendorInvoices();
		Thread.sleep(3000);
		navigateEmployeeCommission();
		Thread.sleep(3000);
		navigateRolodex();
		Thread.sleep(3000);
		navigateUsers();
		Thread.sleep(3000);
		navigateSettings();
		Thread.sleep(3000);
		navigateChartOfAccounts();
		Thread.sleep(3000);
		navigateDivisions();
		Thread.sleep(3000);
		navigateTaxTerritories();
		Thread.sleep(3000);
		navigateGeneralLedger();
		Thread.sleep(3000);
		navigateBalanceSheet();
		Thread.sleep(3000);
		navigateTrialBalance();
		Thread.sleep(3000);
		navigateIncomeStatement();
		Thread.sleep(3000);
		navigateJournalEntries();
		Thread.sleep(3000);
		navigateAccountingCycles();
		Thread.sleep(3000);
		navigateGLTransaction();
		Thread.sleep(3000);
	}

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}

