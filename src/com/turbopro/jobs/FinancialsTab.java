package com.turbopro.jobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class FinancialsTab extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, JobName, soquantity, SalesRep, TaxTerritory, CustomerName, Allocated, soproductname, Notes, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
		//	baseUrl=srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		JobName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		SalesRep = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SalesRep")).toString();
		TaxTerritory= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		CustomerName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		soproductname= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"soproductname")).toString();
		Notes= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		soquantity= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"soquantity")).toString();
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


	//TP_JFT_0001 - logging into the application and navigate to credit tab
	@Test(enabled = true, priority = 1)	
	public void financialTab() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		createNewJob(JobName, SalesRep, TaxTerritory);
		changeStatusToBooked(CustomerName);
		driver.findElement(By.id("financialtab")).click();	
	}

	//TP_JFT_0002 - view financial report
	@Test(enabled = true, priority = 2)
	public void viewFinancialReport() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.linkText("Financial Report")).click();
			parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JFT_0003 - Add invoices entry
	@Test(enabled = true, priority = 3)
	public void addInvoice() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));	
			driver.findElement(By.linkText("Home")).click();// navigate to Home
			createNewJob(JobName, SalesRep, TaxTerritory);
			changeStatusToBooked(CustomerName);
			driver.findElement(By.linkText("Releases")).click();
			releaseStockOrder(Notes, Allocated);
			addLineItemsForStockorder(soproductname, soquantity);
			cusInvoiceForRelease();
			driver.findElement(By.id("financialtab")).click();	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JFT_0004 - View invoices
	@Test(enabled = true, priority = 3)
	public void viewInvoice() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.linkText("Invoice")).click();
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

			if(driver.findElement(By.id("ui-dialog-title-2")).isDisplayed())// warning popup is displayed
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();// click ok
			}

			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JFT_0005 - View Outstanding Invoices Statement
	@Test(enabled = true, priority = 4)
	public void viewOutstandingInvoiceStatement() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.linkText("Oustanding Invoices Statement")).click();
			parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JFT_0006 - View Invoice Summary statement
	@Test(enabled = true, priority = 5)
	public void viewInvoiceSummary() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.linkText("Invoice Summary Statement")).click();
			parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JFT_0007 - Edit Include tax check box
	@Test(enabled = true, priority = 6)
	public void editIncludeTax() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.id("includeTaxID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	//TP_JFT_0008 - View the project dashboard
	@Test(enabled = true, priority = 7)
	public void viewProjectDashboard() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.linkText("Project Dashboard")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}



	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}


