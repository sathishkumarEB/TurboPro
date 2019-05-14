package com.turbopro.vendors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class VendorInvoicesAndBills extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, PayableTo, VendorName, LineItem, Quantity, Freight;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	public VendorInvoicesAndBills(String VendorName, String LineItem, String Quantity, String Freight) {
		this.VendorName = VendorName;
		this.LineItem = LineItem;
		this.Quantity = Quantity;
		this.Freight = Freight;

	}

	public VendorInvoicesAndBills(String PayableTo) {
		this.PayableTo = PayableTo;
	}

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/VendorInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		PayableTo= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"PayableTo")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		VendorName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();
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

	//TC_VI_0001 - logging into the application and navigate to vendor invoice
	@Test(enabled = true, priority = 1)	
	public void vendorInvoices() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateVendorInvoices();
	}

	//TC_VI_0002 - sort the vendor invoices list by clicking the headers 
	@Test(enabled = true, priority = 2)	
	public void sortVendorInvoices() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_invoicesGrid_billDate")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_dueDate")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_ponumber")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_veInvoiceNumber")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_payableTo")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_billAmount")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_appliedAmount")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_chkNo")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_invoicesGrid_datePaid")).click();
			Thread.sleep(2000);
		}

		driver.findElement(By.id("resetbutton")).click();
	}

	//TC_VI_0003 - create vendor invoice without po number  
	@Test(enabled = true, priority = 3)	
	public void createVendorInvoice() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewVendorInvoice()']")));
		driver.findElement(By.xpath("//input[@onclick='addNewVendorInvoice()']")).click();
		if(driver.findElement(By.xpath("//span[text()='Enter PO']")).isDisplayed())
		{
			driver.findElement(By.id("saveTermscancelButton")).click();
		}
		if(driver.findElement(By.id("payable")).isDisplayed())
		{
			driver.findElement(By.id("payable")).click();
			driver.findElement(By.id("payable")).clear();
			System.out.println(PayableTo);
			driver.findElement(By.id("payable")).sendKeys(PayableTo);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		}
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_expenseAmount")));
		driver.findElement(By.id("new_row_expenseAmount")).click();
		driver.findElement(By.id("new_row_expenseAmount")).clear();
		driver.findElement(By.id("new_row_expenseAmount")).sendKeys("1234" + Keys.ENTER);
		driver.findElement(By.id("saveTermsButton")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("addNewVeInvFmDlgclsbuttonwithoutpo")).click();
	}


	//TC_VI_0004 - opening the created vendor invoice and updating the vendor invoice number 
	@Test(enabled = false, priority = 4)	//some issue is there on closing the vendor invoice due to application issue
	public void updateVendorInvoiceNumber() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")));

		if(driver.findElement(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")).isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]"))).doubleClick().perform();
		}

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorInvoice")));

		Random Number = new Random();
		Integer ranNumber = Number.nextInt(1000);
		String randomNumber = ranNumber.toString();
		System.out.println("Random number for vendor invoice:" + randomNumber);
		driver.findElement(By.id("vendorInvoice")).sendKeys(randomNumber);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='savehoverbutton turbo-blue addinv'][1]")).click();

		if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}

		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
		{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("invreasonttextid")));
			driver.findElement(By.id("invreasonttextid")).click();
			driver.findElement(By.id("invreasonttextid")).clear();
			driver.findElement(By.id("invreasonttextid")).sendKeys("test");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}

		Thread.sleep(4000);
		driver.findElement(By.id("addNewVeInvFmDlgclsbuttonwithoutpo")).click();

		Thread.sleep(3000);

		if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
		}

	}

	//TC_VI_0005 - Open and close enter PO popup in vendor invoice list 
	@Test(enabled = true, priority = 5)	
	public void openAndCloseEnterPOPopup() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewVendorInvoice()']")));
		driver.findElement(By.xpath("//input[@onclick='addNewVendorInvoice()']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveTermsokButton")));
		driver.findElement(By.id("saveTermsokButton")).click();
	}

	//TC_VI_0006 - searching a specific vendor invoice
	@Test(enabled = true, priority = 6)	
	public void searchVendorInvoice() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")));
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys("SSR180603D");
		driver.findElement(By.id("goSearchButtonID")).click();
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='SSR180603D']")));
		driver.findElement(By.xpath("//td[@title='SSR180603D']"));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//td[@title='SSR180603D']"))).doubleClick().perform();

		//		if((driver.findElement(By.id("payablePO"))).isDisplayed())
		//		{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addNewVeInvFmDlgclsbutton")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("addNewVeInvFmDlgclsbutton")));
		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();
		//		}
	}


	//TC_VI_0007 - reset the searched invoice 
	@Test(enabled = true, priority = 7)	
	public void resetVendorInvoiceSearch() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resetbutton")));
		driver.findElement(By.id("resetbutton")).click();
		Thread.sleep(4000);
		driver.navigate().refresh();
	}

	//TC_VI_0008 - view vendor invoice by date range
	@Test(enabled = true, priority = 8)
	public void viewVendorInvoiceByDateRange() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("dateRange")));
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("fromDate")).click();
		driver.findElement(By.linkText("1")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("toDate")).click();
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("resetbutton")).click();
	}


	//TC_VI_0009 - view accounts payable in the vendor invoices list 
	@Test(enabled = true, priority = 9) //issue exists in retrieving acc payable, application issue
	public void viewAccountsPayable() throws InterruptedException, Exception
	{
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")));
		driver.findElement(By.id("vendorAccountList")).click();
		Select AccPay = new Select(driver.findElement(By.id("vendorAccountList")));
		AccPay.selectByVisibleText("Accounts Payable");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[2]/td[6]")));
		Thread.sleep(5000);
	}


	//Download CSV report with Vendor Sub Total (RID 478)
	@Test(enabled = true, priority = 10) 
	public void dwldAccPayCsvWithVendorSubtotal() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[2]/td[6]")));
		driver.findElement(By.xpath("//span/img[@onclick='printAccountsPayable()']")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
	}

	//Download CSV report without Vendor Sub Total (RID 478)
	@Test(enabled = true, priority = 11) 
	public void dwldAccPayCsvWithoutVendorSubtotal() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[2]/td[6]")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span/img[@onclick='printAccountsPayable()']")).click();
		Thread.sleep(3000);
//		if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
//		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
//		}
			driver.switchTo().defaultContent();
//			switchToTab();
		Thread.sleep(5000);
	}



	

//	//TC_VI_0010 - download csv for the accounts payable 
//	@Test(enabled = false, priority = 10) //issue exists in retrieving acc payable, application issue
//	public void viewAccountsPayableCSV() throws InterruptedException, Exception
//	{
//		Thread.sleep(4000);
//		//		driver.findElement(By.id("vendorAccountList")).click();
//		//		Select AccPay = new Select(driver.findElement(By.id("vendorAccountList")));
//		//		AccPay.selectByVisibleText("Accounts Payable");
//		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[2]/td[6]")));
//		driver.findElement(By.xpath("//span/img[@onclick='printAccountsPayable()']")).click();
//
//	}


	//TC_VI_0011 - view accounts payable based on date range 
	@Test(enabled = true, priority = 12) //issue exists in retrieving acc payable, application issue
	public void viewAccountsPayableByDateRange() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		driver.findElement(By.id("resetbutton")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[@id='1']/td[6]")));
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("dateRange")));
		driver.findElement(By.id("dateRange")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("fromDate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("1")));
		driver.findElement(By.linkText("1")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("toDate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("28")));
		driver.findElement(By.linkText("28")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountsPayableGrid']/tbody/tr[@id='1']/td[6]")));
		driver.findElement(By.id("resetbutton")).click();
		driver.navigate().refresh();
	}


	//TC_VI_0012 - Edit and close (without saving) vendor invoice with PO number
	@Test(enabled = false, priority = 13)
	public void editVendorInvoiceAndClose() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")));
		driver.findElement(By.id("searchJob")).sendKeys("102701");
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")));
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]")).isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='invoicesGrid']/tbody/tr[@id='1']/td[6]"))).doubleClick().perform();
		}

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorInvoicePO")));
		driver.findElement(By.id("vendorInvoicePO")).click();
		driver.findElement(By.id("vendorInvoicePO")).clear();
		driver.findElement(By.id("vendorInvoicePO")).sendKeys("1");

		driver.findElement(By.id("freightGeneralId")).click();
		driver.findElement(By.id("freightGeneralId")).clear();
		driver.findElement(By.id("freightGeneralId")).sendKeys("2");

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("addNewVeInvFmDlgclsbutton")));

		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();	
		}
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Reason']")).isDisplayed())
		{
			driver.findElement(By.id("invreasonttextid")).click();
			driver.findElement(By.id("invreasonttextid")).clear();
			driver.findElement(By.id("invreasonttextid")).sendKeys("test");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();	
		}
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//b[text()='Do You want to close the PO transaction Status?']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();

	}


	//TC_VI_0013 - create vendor invoice for the purchase order and received inventory
	@Test(enabled = false, priority = 14)
	public void createVendorInvoiceForPurchaseOrder() throws InterruptedException 
	{
		driver.navigate().refresh();
		Thread.sleep(3000);
		navigateVendorPurchaseOrders();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")));
		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorsearch")));
		driver.findElement(By.id("vendorsearch")).sendKeys(VendorName);
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("POSaveID")));

		driver.findElement(By.id("POSaveID")).click();

		Thread.sleep(4000);

		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));		
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		String ourPO = driver.findElement(By.id("ourPoLineId")).getAttribute("value"); //getting PO number
		System.out.println(ourPO);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
		driver.findElement(By.id("new_row_note")).sendKeys(LineItem);
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//body/ul[16]/li/a")).click();
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Quantity + Keys.ENTER);
		driver.findElement(By.id("SaveLinesPOButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("CancelLinesPOButton")).click();

		receiveInventory();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input")));
		driver.findElement(By.xpath("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
		driver.findElement(By.id("ponumber")).sendKeys(ourPO);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[10]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ReceiveAllSaveID")));
		driver.findElement(By.id("ReceiveAllSaveID")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("POSaveID")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("priorreceipts")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='priortable']/tbody/tr[2]/td[3]/img")));
		driver.findElement(By.xpath("//*[@id='priortable']/tbody/tr[2]/td[3]/img")).click();

		parentWindow();

		driver.findElement(By.xpath("//body/div[8]/div[1]/a/span")).click();
		driver.findElement(By.id("POCloseID")).click();

		navigateVendorInvoices();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")).click();
		driver.findElement(By.id("po")).sendKeys(ourPO);
		driver.findElement(By.id("saveTermscancelButton")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("freightGeneralId")).clear();
		driver.findElement(By.id("freightGeneralId")).sendKeys(Freight);
		Thread.sleep(3000);

		driver.findElement(By.id("addNewVeInvFmDlgbuttonsave")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//body/div[17]/div[11]/div/button[2]")).isDisplayed())
		{
			driver.findElement(By.xpath("//body/div[17]/div[11]/div/button[2]")).click();
		}
		Thread.sleep(4000);
		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();

	}

	//TC_VI_0015 - view no customer invoices
	@Test(enabled = true, priority = 15)
	public void viewNoCustomerInvoice() throws InterruptedException 
	{
		driver.findElement(By.id("vendorAccountList")).click();
		Select NoCusInv = new Select(driver.findElement(By.id("vendorAccountList")));
		NoCusInv.selectByVisibleText("No Customer Invoice");

		if(driver.findElement(By.xpath("//span[text()= 'No Customer Invoice']")).isDisplayed())
		{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]")));
			driver.findElement(By.xpath("//body/div[15]/div[1]/a/span")).click();
			//			driver.findElement(By.linkText("close")).click();
		}
	}

	//TC_VI_0017 - view no customer invoice by date range
	@Test(enabled = true, priority = 16)
	public void viewNoCustomerInvoiceByDate() throws InterruptedException 
	{
		driver.findElement(By.id("vendorAccountList")).click();
		Select NoCusInv = new Select(driver.findElement(By.id("vendorAccountList")));
		NoCusInv.selectByVisibleText("No Customer Invoice");
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//span[text()= 'No Customer Invoice']")).isDisplayed())
		{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]")));
			driver.findElement(By.id("uninvoicedateRange")).click();
			driver.findElement(By.id("uninvoicefromDate")).click();
			driver.findElement(By.linkText("1")).click();
			driver.findElement(By.id("uninvoicetoDate")).click();
			driver.findElement(By.linkText("28")).click();
			Thread.sleep(3000);
			//driver.findElement(By.linkText("close")).click();
			driver.findElement(By.xpath("//body/div[15]/div[1]/a/span")).click();
		}
	}


	//TC_VI_0016 - view print of the no customer invoice 
	@Test(enabled = true, priority = 17)
	public void printNoCustomerInvoice() throws InterruptedException 
	{
		driver.navigate().refresh();
		driver.findElement(By.id("vendorAccountList")).click();
		Select NoCusInv = new Select(driver.findElement(By.id("vendorAccountList")));
		NoCusInv.selectByVisibleText("No Customer Invoice");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]")));
		driver.findElement(By.xpath("//input[@onclick='printuninvoiceList()']")).click();

		parentWindow();
		driver.findElement(By.xpath("//body/div[15]/div[1]/a/span")).click();
		//		driver.findElement(By.linkText("close")).click();
	}


	//TC_VI_0018 - Access invoice from the no customer invoice popup
	@Test(enabled = true, priority = 18)
	public void accessInvoiceFromNoCustomerInvoice() throws InterruptedException 
	{
		driver.findElement(By.id("vendorAccountList")).click();
		Select NoCusInv = new Select(driver.findElement(By.id("vendorAccountList")));
		NoCusInv.selectByVisibleText("No Customer Invoice");
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]")));

		if(driver.findElement(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]")).isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='uninvoicesGrid']/tbody/tr[2]/td[8]"))).doubleClick().perform();
		}

		Thread.sleep(4000);
		driver.findElement(By.xpath("//body/div[8]/div[1]/a/span")).click();
		//		driver.findElement(By.linkText("close")).click();
	}


	//TC_VI_0014 - selecting the purchasing summary in vendor invoices
	@Test(enabled = false, priority = 19)
	public void viewPurchasingSummary() throws InterruptedException 
	{
		driver.findElement(By.id("vendorAccountList")).click();
		Select NoCusInv = new Select(driver.findElement(By.id("vendorAccountList")));
		NoCusInv.selectByVisibleText("Purchasing Summary");
		driver.navigate().refresh();
	}


	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
	}
}
