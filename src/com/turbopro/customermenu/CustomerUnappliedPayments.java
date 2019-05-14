package com.turbopro.customermenu;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class CustomerUnappliedPayments extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, Customername, Cusnameunapp;
	FileInputStream fis;
	HSSFWorkbook srcBook ;


	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/CustomerPaymentsData.xls")));
		openChromeBrowser();
		
		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Cusnameunapp = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustNameUnapp")).toString();


	}

	//code for initialising the workbook or excel sheet
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

	/*TC_CUP_0001 - logging into the application, navigate to customer unapplied payments*/
	@Test(enabled = true, priority = 1)	
	public void customerUnappliedPayments() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateCusUnappliedPayments();
	}

	/*TC_CUP_0002 - searching unapplied payment for specific customer*/
	@Test(enabled = true, priority = 2)	
	public void searchCusBasedUnappliedPayments() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));
		driver.findElement(By.id("searchpaymentstext")).sendKeys(Cusnameunapp);
		driver.findElement(By.xpath("//input[@onclick='searchcustomerpayments()']")).click();
		driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TC_CUP_0003 - searching unapplied payment by date range*/
	@Test(enabled = true, priority = 3)	
	public void searchUnappliedPaymentByDate() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.id("fromdate")).click();
		driver.findElement(By.linkText("1")).click();

		driver.findElement(By.id("todate")).click();
		driver.findElement(By.linkText("28")).click();

		driver.findElement(By.xpath("//input[@onclick='searchcustomerpayments()']")).click();
		driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*TC_CUP_0004 - apply sorting in the headers of unapplied payments table*/
	@Test(enabled = true, priority = 4)	
	public void sortUnappliedPayments() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));
		for(int i=1; i<=2; i++)		
		{
			driver.findElement(By.id("jqgh_customerpaymentlist_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_customer")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_reference")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_memo")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_paymentApplied")).click();
		}
		driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TC_CUP_0005 - pay single invoice using unapplied payments*/
	@Test(enabled = true, priority = 5)	
	public void paySingleInvoice() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().perform();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[2]/img")));

		driver.findElement(By.xpath("//*[@id='1']/td[2]/img")).click();
		driver.findElement(By.id("saveInvoiceId")).click();
		driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*TC_CUP_0006 - Apply sort in inside unapplied payments for invoice list*/
	@Test(enabled = true, priority = 6)	
	public void sortUnappliedPaymentInvoices() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().perform();

		for(int i=1; i<=2; i++)		
		{
			driver.findElement(By.id("customerPaymets_invoiceNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_customerPoNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerPaymets_invoiceBalance")).click();
			driver.findElement(By.id("customerPaymets_preDiscountUsed")).click();
			driver.findElement(By.id("jqgh_customerPaymets_paymentApplied")).click();
			driver.findElement(By.id("jqgh_customerPaymets_remaining")).click();
		}

		driver.findElement(By.id("saveInvoiceId")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*pay multiple invoice using unapplied payments*/
	@Test(enabled = false, priority = 7)	
	public void payMultipleInvoice() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().perform();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[2]/img")));

		driver.findElement(By.xpath("//*[@id='1']/td[2]/img")).click();

		driver.findElement(By.id("saveInvoiceId")).click();
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}
