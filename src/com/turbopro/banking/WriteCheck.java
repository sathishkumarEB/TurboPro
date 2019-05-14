package com.turbopro.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class WriteCheck extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Vendorname, Amount, Vendor, JobName, Description, LineItem, VendorName, Count, Quantity1, TransNo, PO,  Reference, Quantity, Address, SalesRep, TaxTerritory, CustomerName, Bondagent, State, City, Company, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Amount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Amount")).toString();
		Vendorname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Vendorname")).toString();
		Vendor= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Vendor")).toString();
		CustomerName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
		Reference= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reference")).toString();
		Description= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Description")).toString();
		VendorName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();
	}

	private int ColumnNumber(HSSFWorkbook Hwb,int sheetNum, int RowCount,String ColumnHeader) throws Exception
	{			
		int patchColumn = -1;
		for (int cn=0; cn<Hwb.getSheetAt(sheetNum).getRow(RowCount).getLastCellNum(); cn++) {
			Cell c = Hwb.getSheetAt(sheetNum).getRow(RowCount).getCell(cn);
			if (c.toString() == null) {
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

	/*TP_WC_0001 - logging into the application and navigate to Write checks*/
	@Test(enabled = true, priority = 1)	
	public void navigateToWriteChecks() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateBankWriteChecks();
	}

	/*TP_WC_0002 - Write checks for existing pay bills*/
	@Test(enabled = true, priority = 2)	
	public void writeChecksForExistingBill() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//span[text()='Information']").isDisplayed())
			{
				getxpath("//span[text()='OK']").click();
				getxpath("//*[@id='vendorBillsghead_0_0']/td/span").click();
				getxpath("//*[@id='1']/td[1]/img").click();
				Thread.sleep(1000);
				navigateBankWriteChecks();
				getxpath("//input[@value='Print']").click();
				if(getxpath("//span[text()='Warning']").isDisplayed())
				{
					getxpath("//span[text()='Yes']").click();
				}
				else
				{
					System.out.println("Selected entry is a Vendor Bill");
				}
			}
		}
		catch (NoSuchElementException e){
			getxpath("//input[@value='Print']").click();
		}
		Thread.sleep(2000);
		driver.navigate().refresh();
	}

	/*TP_WC_0003 - Create new vendor invoice and write check*/
	@Test(enabled = true, priority = 3)	
	public void writeCheckForNewVendorInvoice() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//span[text()='Information']").isDisplayed())
			{
				getxpath("//span[text()='OK']").click();
			}
		}
		catch (NoSuchElementException e)
		{
			getxpath("//input[@value='Print']").click();
		}
		Thread.sleep(2000);
		navigateVendorInvoices();
		getxpath("//input[@onclick='addNewVendorInvoice()']").click();
		getid("saveTermscancelButton").click();
		getid("payable").click();
		getid("payable").clear();
		getid("payable").sendKeys(VendorName);
		getxpath("//body/ul[13]/li/a").click();
		getid("new_row_expenseAmount").click();
		getid("new_row_expenseAmount").clear();
		getid("new_row_expenseAmount").sendKeys(Amount + Keys.ENTER);
		getxpath("//td/input[@id = 'saveTermsButton']").click();
		Thread.sleep(3000);
		getxpath("//td/input[@id = 'addNewVeInvFmDlgclsbuttonwithoutpo']").click();
		navigateVendorPayBills();
		getid("vendorBillPay_groupID").click();
		getid("vendorBillPay_groupID").clear();
		getid("vendorBillPay_groupID").sendKeys(VendorName);
		getxpath("//body/ul[13]/li[1]/a").click();
		getxpath("//*[@id='vendorBillsghead_0_0']/td/span").click();
		getxpath("//*[@id='1']/td[1]/img").click();
		Thread.sleep(2000);
		navigateBankWriteChecks();
		getxpath("//input[@value='Print']").click();
		try {
			if((getxpath("//span[text()='Warning']")).isDisplayed()==true)
			{
				getxpath("//span[text()='Yes']").click();
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("Warning popup does not appear");
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
	}

	/*TP_WC_0004 - Pay for multiple invoices*/
	@Test(enabled = true, priority = 4)	
	public void payMultipleInvoices() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//span[text()='Information']").isDisplayed())
			{
				getxpath("//span[text()='OK']").click();
			}
		}
		catch (NoSuchElementException e)
		{
			getxpath("//input[@value='Print']").click();
		}
		Thread.sleep(2000);
		navigateVendorInvoices();
		getxpath("//input[@onclick='addNewVendorInvoice()']").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveTermscancelButton")));
		getid("saveTermscancelButton").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("payable")));
		getid("payable").click();
		getid("payable").clear();
		getid("payable").sendKeys(VendorName);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
		getxpath("//body/ul[13]/li/a").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_expenseAmount")));
		getid("new_row_expenseAmount").click();
		getid("new_row_expenseAmount").clear();
		getid("new_row_expenseAmount").sendKeys(Amount + Keys.ENTER);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/input[@id = 'saveTermsButton']")));
		getxpath("//td/input[@id = 'saveTermsButton']").click();
		Thread.sleep(3000);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/input[@id = 'addNewVeInvFmDlgclsbuttonwithoutpo']")));
		getxpath("//td/input[@id = 'addNewVeInvFmDlgclsbuttonwithoutpo']").click();
		//		Thread.sleep(3000);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewVendorInvoice()']")));
		getxpath("//input[@onclick='addNewVendorInvoice()']").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveTermscancelButton")));
		getid("saveTermscancelButton").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("payable")));
		getid("payable").click();
		getid("payable").clear();
		getid("payable").sendKeys(VendorName);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
		getxpath("//body/ul[13]/li/a").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_expenseAmount")));
		getid("new_row_expenseAmount").click();
		getid("new_row_expenseAmount").clear();
		getid("new_row_expenseAmount").sendKeys(Amount + Keys.ENTER);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/input[@id = 'saveTermsButton']")));
		getxpath("//td/input[@id = 'saveTermsButton']").click();
		//		Thread.sleep(3000);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/input[@id = 'addNewVeInvFmDlgclsbuttonwithoutpo']")));
		getxpath("//td/input[@id = 'addNewVeInvFmDlgclsbuttonwithoutpo']").click();
		//		Thread.sleep(3000);
		navigateVendorPayBills();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorBillPay_groupID")));
		getid("vendorBillPay_groupID").click();
		getid("vendorBillPay_groupID").clear();
		getid("vendorBillPay_groupID").sendKeys(VendorName);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li[1]/a")));	
		getxpath("//body/ul[13]/li[1]/a").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='vendorBillsghead_0_0']/td/span")));
		getxpath("//*[@id='vendorBillsghead_0_0']/td/span").click();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[1]/img")));
		getxpath("//*[@id='1']/td[1]/img").click();
		//		Thread.sleep(3000);
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='2']/td[1]/img")));
		getxpath("//*[@id='2']/td[1]/img").click();
		Thread.sleep(3000);
		navigateBankWriteChecks();
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Print']")));
		getxpath("//input[@value='Print']").click();
		//		Thread.sleep(2000);
		driver.navigate().refresh();
	}

	/*TP_WC_0005 - Pay for invoice created from Purchase order*/
	@Test(enabled = false, priority = 5)	
	public void writeCheckForInvoiceFromPO() throws InterruptedException, Exception
	{
		if(getxpath("//span[text()='Information']").isDisplayed())
		{
			getxpath("//span[text()='OK']").click();
		}
		else
		{
			System.out.println("Information popup does not appear ");
		}
		navigateVendorPurchaseOrders(); 

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")));
		getxpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorsearch")));
		getid("vendorsearch").click();
		getid("vendorsearch").clear();
		getid("vendorsearch").sendKeys(VendorName);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
		getxpath("//body/ul[13]/li/a").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'addPurchaseOrder()' ]")));
		getxpath("//input[@onclick = 'addPurchaseOrder()' ]").click();
		Thread.sleep(3000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lineTab")));
		getid("lineTab").click();
		Thread.sleep(3000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
		getid("new_row_note").click();
		getid("new_row_note").clear();
		getid("new_row_note").sendKeys(LineItem);// Enter product no
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[16]/li[1]/a")));	
		getxpath("//body/ul[16]/li[1]/a").click();// Select specific product no

		getid("new_row_quantityOrdered").click();
		getid("new_row_quantityOrdered").sendKeys("1" + Keys.ENTER);// Hit Enter

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveLinesPOButton")));
		getid("SaveLinesPOButton").click();// click save
		Thread.sleep(3000);

		getid("ourPoLineId").click();// click save
		String ourPO = getid("ourPoLineId").getAttribute("value"); 
		System.out.println(ourPO);

		WebElement Inventory = getxpath("//*[@id='mainmenuInventoryPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(Inventory).perform();
		getxpath("//*[@id='mainmenuInventoryPage']/ul/li[3]").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input")));
		getxpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
		getid("ponumber").click();
		getid("ponumber").clear();
		getid("ponumber").sendKeys(ourPO);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[10]/div[11]/div/button[1]/span")));
		getxpath("//body/div[10]/div[11]/div/button[1]/span").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ReceiveAllSaveID")));
		getid("ReceiveAllSaveID").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POSaveID")));
		getid("POSaveID").click();
		Thread.sleep(5000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POCloseID")));
		getid("POCloseID").click();

		navigateVendorInvoices();


		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value = '   Add']")));
		getxpath("//input[@value = '   Add']").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("po")));
		getid("po").click();
		getid("po").clear();
		getid("po").sendKeys(ourPO);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("saveTermscancelButton")));
		getid("saveTermscancelButton").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='addNewVeInvFmDlgbuttonsave']")));
		getxpath("//input[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(3000);

		//		if(getxpath("//span[text() = 'Success']")).isDisplayed())
		//		{
		//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
		//		}

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='addNewVeInvFmDlgclsbutton']")));
		getxpath("//input[@id='addNewVeInvFmDlgclsbutton']").click();
		Thread.sleep(3000);

		navigateVendorPayBills();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorBillPay_groupID")));
		getid("vendorBillPay_groupID").click();
		getid("vendorBillPay_groupID").clear();
		getid("vendorBillPay_groupID").sendKeys(VendorName);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li[1]/a")));	
		getxpath("//body/ul[13]/li[1]/a").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='vendorBillsghead_0_0']/td/span")));
		getxpath("//*[@id='vendorBillsghead_0_0']/td/span").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[1]/img")));
		getxpath("//*[@id='1']/td[1]/img").click();
		Thread.sleep(3000);

		navigateBankWriteChecks();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Print']")));
		getxpath("//input[@value='Print']").click();
		Thread.sleep(4000);

	}
	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}