package com.turbopro.customermenu;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class CustomerInvoiceOutside extends Methods {






	//	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();

	private String cuInvoiceNumber, Url, UName, Password, ProductNo, Taxterritory, Customername, Quantity, Freight;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/SalesOrderInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Customername")).toString();
		ProductNo= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"ProductNo")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Taxterritory = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		//		ProductNo = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"ProductNo")).toString();
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


	//TP_CI_0001 - logging into the application
	@Test(enabled = true, priority = 1)
	public void customerInvoices() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateCustomerInvoice();
	}		

	//TP_CI_0002 - creating a new customer invoice 
	@Test(enabled = true, priority = 2)
	public void createCustomerInvoice() throws InterruptedException
	{

		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewInvoice()']")));
			getxpath(addNewInvoice_CustInv).click();
			driver.findElement(By.xpath("//span[text()='New Invoice']")).click();


			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New Invoice']")));


			//adding customer name and tax territory to save
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_customerInvoiceID")));
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).clear();
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Customername);
			Thread.sleep(3000);
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Keys.RETURN);
			
//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[24]/li/a")));
//			driver.findElement(By.xpath("//body/ul[24]/li/a")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_TaxTerritory")));
			driver.findElement(By.id("customerInvoice_TaxTerritory")).click();
			driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Taxterritory);
			Thread.sleep(2000);
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Keys.RETURN);
			
//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[22]/li/a")));
//			driver.findElement(By.xpath("//body/ul[22]/li/a")).click();
			driver.findElement(By.id("customerInvoice_frightIDcu")).clear();
			driver.findElement(By.id("customerInvoice_frightIDcu")).sendKeys(Freight);
			driver.findElement(By.id("CuInvoiceSaveID")).click();

			//adding line items
			Thread.sleep(20000);
			driver.findElement(By.xpath("//a[@href='#soreleaselineitem']")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
			driver.findElement(By.id("new_row_itemCode")).click();
			driver.findElement(By.id("new_row_itemCode")).clear();
			driver.findElement(By.id("new_row_itemCode")).sendKeys("*");
			driver.findElement(By.id("new_row_itemCode")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("new_row_itemCode")).sendKeys(Keys.RETURN);
			
//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
//			driver.findElement(By.xpath("//body/ul[26]/li/a")).click();

			driver.findElement(By.id("new_row_unitCost")).click();
			driver.findElement(By.id("new_row_unitCost")).clear();
			driver.findElement(By.id("new_row_unitCost")).sendKeys("3");

			driver.findElement(By.id("new_row_quantityBilled")).click();
			driver.findElement(By.id("new_row_quantityBilled")).clear();
			driver.findElement(By.id("new_row_quantityBilled")).sendKeys(Quantity + Keys.ENTER);
			Thread.sleep(3000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
			driver.findElement(By.id("CuInvoiceSaveID")).click();
			Thread.sleep(10000);

			//close customer invoice - fetching customer invoice number
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
			cuInvoiceNumber = driver.findElement(By.xpath("//*[@id='1']/td[5]")).getText();
			System.out.println(cuInvoiceNumber);
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CI_0003 - Adding line items to the existing customer invoice*/
	@Test(enabled = true, priority = 3)
	public void addLineItemsToExistingInvoice() throws InterruptedException
	{
		try{
			driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cICheckTab2']/a")));
			driver.findElement(By.xpath("//*[@id='cICheckTab2']/a")).click();
			Thread.sleep(3000);

			for(int addline=0; addline<5; addline++)
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
				driver.findElement(By.id("new_row_itemCode")).click();
				driver.findElement(By.id("new_row_itemCode")).clear();
				driver.findElement(By.id("new_row_itemCode")).sendKeys("*");
				
				driver.findElement(By.id("new_row_itemCode")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.id("new_row_itemCode")).sendKeys(Keys.RETURN);
				
//				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
//				driver.findElement(By.xpath("//body/ul[26]/li/a")).click();

				driver.findElement(By.id("new_row_unitCost")).click();
				driver.findElement(By.id("new_row_unitCost")).clear();
				driver.findElement(By.id("new_row_unitCost")).sendKeys("4.5");

				driver.findElement(By.id("new_row_quantityBilled")).click();
				driver.findElement(By.id("new_row_quantityBilled")).clear();
				driver.findElement(By.id("new_row_quantityBilled")).sendKeys(Quantity + Keys.ENTER);
				Thread.sleep(3000);
			}

			Thread.sleep(3000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
			driver.findElement(By.id("CuInvoiceSaveID")).click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
			{
				driver.findElement(By.id("invreasonttextid")).sendKeys("Test");
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(10000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CI_0004 - Deleting the line items from existing customer invoice and updating changes*/
	@Test(enabled = true, priority = 4)
	public void deleteLineItemsToExistingCusInvoice() throws InterruptedException
	{
		try{
			driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cICheckTab2']/a")));
			driver.findElement(By.xpath("//*[@id='cICheckTab2']/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("canDeleteCuInvID_6")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("canDeleteCuInvID_5")).click();
			Thread.sleep(3000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
			driver.findElement(By.id("CuInvoiceSaveID")).click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
			{
				driver.findElement(By.id("invreasonttextid")).sendKeys("Test");
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(10000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Updating Pro# in customer invoice
	@Test(enabled = true, priority = 5)  //issue is there in this method
	public void updateProOfCI() throws InterruptedException
	{
		try{
			driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
			Thread.sleep(5000);

			/*code to generate a random number for Pro*/
			Random Number = new Random();
			Integer ranNumber = Number.nextInt(1000);
			String randomNumber = ranNumber.toString();
			System.out.println("Random number for vendor invoice:" + randomNumber);

			//		randomNumber();
			driver.findElement(By.id("customerInvoice_proNumberID")).click();
			driver.findElement(By.id("customerInvoice_proNumberID")).sendKeys(randomNumber);
			Thread.sleep(2000);
			driver.findElement(By.id("CuInvoiceSaveID")).click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
			{
				driver.findElement(By.id("invreasonttextid")).sendKeys("Test");
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(10000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_CI_0005
	//checking sorting in customer invoice list
	@Test(enabled = true, priority = 6)
	public void sortCusInvoice() throws InterruptedException
	{
		try{
			navigateCustomerInvoice();
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
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*View customer invoices based on date range*/
	@Test(enabled = true, priority = 7)
	public void viewCusInvoiceByDateRange() throws InterruptedException
	{
		try{
			driver.findElement(By.id("dateRange")).click();
			driver.findElement(By.id("fromDate")).click();
			driver.findElement(By.linkText("1")).click();
			driver.findElement(By.id("toDate")).click();
			driver.findElement(By.linkText("28")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("resetbutton")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Search specific customer invoice*/
	@Test(enabled = true, priority = 8)
	public void searchCusInvoice() throws InterruptedException
	{
		try{
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).sendKeys("228509");
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_CustomerInvoiceGrid")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().perform();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//body/div[12]/div[1]/a/span")).click();
			driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Attempt to create customer invoice for customer invoice without SO number*/
	@Test(enabled = true, priority = 9)
	public void cusInvForSalOrdPopup() throws InterruptedException
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@onclick='addNewInvoice()']")));
			driver.findElement(By.xpath("//input[@onclick='addNewInvoice()']")).click();
			if(driver.findElement(By.xpath("//span[text()= 'Information']")).isDisplayed())
			{
				driver.findElement(By.xpath("//span[text()= 'New Invoice from Sales Order']")).click();
				if(driver.findElement(By.xpath("//span[text()= 'SO Number']")).isDisplayed())
				{
					driver.findElement(By.xpath("//span[text()= 'Ok']")).click();
				}
				Thread.sleep(3000);
			}

			if(driver.findElement(By.xpath("//span[text()= 'SO Number']")).isDisplayed())
			{
				driver.findElement(By.xpath("//span[text()= 'Cancel']")).click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Search invoice based on job name*/
	@Test(enabled = true, priority = 10)
	public void searchCusInvByJobName() throws InterruptedException
	{
		try{
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).sendKeys("Test");
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_CustomerInvoiceGrid")));
			Thread.sleep(4000);
			driver.findElement(By.id("resetbutton")).click();
			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_CustomerInvoiceGrid")));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*View pdf of the customer invoice*/
	@Test(enabled = true, priority = 11)
	public void viewCusInvPdf() throws InterruptedException
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().perform();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@title='View CuInvoice']")).click();
			Thread.sleep(3000);
			parentWindow();
			Thread.sleep(2000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Email customer invoice*/
	@Test(enabled = true, priority = 12)
	public void emailCustomerInv() throws InterruptedException
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().perform();
			Thread.sleep(4000);
			driver.findElement(By.id("contactEmailID")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@onclick='CancelDialog()']")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

			//		if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Create sales order and then create customer invoice for the same*/
	@Test(enabled = false, priority = 13)
	public void cusInvForSalesOrder() throws InterruptedException
	{
		try{
			navigateCustomerSalesOrder();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewSO()']")));
			driver.findElement(By.xpath("//input[@onclick='addNewSO()']")).click();
			driver.findElement(By.id("CustomerNameGeneral")).sendKeys("Aramark");	//enter from sheet here
			driver.findElement(By.xpath("//body/ul[21]/li/a")).click();
			driver.findElement(By.id("promisedIDwz")).click();
			driver.findElement(By.linkText("10")).click();
			driver.findElement(By.id("SavePOReleaseID")).click();
			Thread.sleep(9000);
			driver.findElement(By.linkText("Line Items")).click();
			Thread.sleep(9000);
			for(int i=0; i<2; i++)			
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
				driver.findElement(By.id("new_row_itemCode")).sendKeys(ProductNo);// Enter Product No.
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[23]/li/a")));	
				driver.findElement(By.xpath("//body/ul[23]/li/a")).click();// select specific entry
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));	
				driver.findElement(By.id("new_row_itemCode")).click();// click line item
				driver.findElement(By.id("new_row_itemCode")).sendKeys(Keys.ENTER);// Hit Enter
			}
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveLineSOReleaseID")));	
			driver.findElement(By.id("SaveLineSOReleaseID")).click();// Click save
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closeLineSOReleaseID")));
			driver.findElement(By.id("closeLineSOReleaseID")).click();// click close	



			//unable to fetch the sales order number through automation; hence can't create customer invoice for that as of now		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Remove the salesrep in the existing customer invoice and save it*/
	@Test(enabled = true, priority = 14)
	public void removeSalesrepInCustomerInvoice() throws InterruptedException
	{
		try{
			navigateCustomerInvoice();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));

			driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
			Thread.sleep(5000);


			driver.findElement(By.id("customerInvoice_salesRepsList")).click();
			driver.findElement(By.id("customerInvoice_salesRepsList")).clear();
			driver.findElement(By.id("CuInvoiceSaveID")).click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
			{
				driver.findElement(By.id("invreasonttextid")).sendKeys("Remove Sales Rep");
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			Thread.sleep(10000);
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*view the batch invoice view */
	@Test(enabled = false, priority = 15)
	public void batchCustomerInvoice() throws InterruptedException
	{
		try{
			navigateCustomerInvoice();
			driver.findElement(By.xpath("//input[@value='Batch']")).click();
			if(driver.findElement(By.xpath("//span[text()='Batch Invoice Report']")).isDisplayed())
			{
				driver.findElement(By.id("batchInvoiceFromDate")).click();
				driver.findElement(By.linkText("1")).click();
				driver.findElement(By.id("batchInvoiceToDate")).click();
				driver.findElement(By.linkText("5")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//input[@onclick='generateBatchInvoicePdf()']")).click();
			}
			parentWindow();
			driver.findElement(By.xpath("//input[@onclick='clearBatchInvoice()']")).click();
			driver.findElement(By.id("batchInvoiceCuID")).click();
			driver.findElement(By.id("batchInvoiceCuID")).clear();
			driver.findElement(By.id("batchInvoiceCuID")).sendKeys("Air One");
			driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
			driver.findElement(By.xpath("//input[@onclick='generateBatchInvoicePdf()']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("batchInvoiceFromDate")).click();
			driver.findElement(By.linkText("1")).click();
			driver.findElement(By.id("batchInvoiceToDate")).click();
			driver.findElement(By.linkText("5")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@onclick='generateBatchInvoicePdf()']")).click();
			parentWindow();
			driver.findElement(By.id("cancelBIButton")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Try to edit the customer invoice after its paid - RID 981*/
	@Test(enabled = true, priority = 16)
	public void editCusInvoiceAfterPayment() throws InterruptedException
	{
		try{
			navigateCustomerPayments();

			driver.findElement(By.id("addpaymentlist")).click();

			driver.findElement(By.id("payCustomer")).click();
			driver.findElement(By.id("payCustomer")).sendKeys(Customername);
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

			Thread.sleep(5000);

			driver.findElement(By.id("payAmountId")).click();
			driver.findElement(By.id("payAmountId")).clear();
			driver.findElement(By.id("payAmountId")).sendKeys("100");

			Select type = new Select(driver.findElement(By.id("paymentReciptTypeId")));
			type.selectByVisibleText("Other");

			driver.findElement(By.id("payCheckId")).sendKeys("003");
			driver.findElement(By.id("payMemoId")).sendKeys("Dummy Memo");

			WebElement table = driver.findElement(By.id("customerPaymets"));
			List<WebElement> row = table.findElements(By.tagName("tr"));
			System.out.println("Total Number of Rows = " + row.size());
			int a = row.size(); a = a-1;
			System.out.println("reduced row number" + a);
			driver.findElement(By.xpath("//*[@id='"+a+"']/td[2]/img")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("saveInvoiceId")).click();

			driver.navigate().refresh();

			navigateCustomerInvoice();

			driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
			driver.findElement(By.id("goSearchButtonID")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
			Actions dblclk = new Actions (driver);
			dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
			Thread.sleep(4000);
			if(!driver.findElement(By.id("CuInvoiceSaveID")).isEnabled())
			{
				driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
			}

			else
				return;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	@AfterTest
	public void teardown() 
	{
//		driver.quit();
	}




}
