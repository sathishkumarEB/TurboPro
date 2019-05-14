package com.turbopro.customermenu;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class TaxAdjustments extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, JobName, SalesRep, TxTerritory, TaxTerritory, customername, soquantity, Allocated, soproductname, Notes;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		soproductname= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"soproductname")).toString();
		JobName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		SalesRep = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SalesRep")).toString();
		TaxTerritory= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		TxTerritory= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"TxTerritory")).toString();
		customername= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		soquantity= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"soquantity")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		Notes= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();

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


	//logging into the application and navigate to credit tab
	@Test(enabled = true, priority = 1)	
	public void taxAdjustments() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(1000);
		navigateCustomerTaxAdjustments();
		Thread.sleep(2000);
	}

	//Tax adjustments for customer invoice inside job
	@Test(enabled = true, priority = 2)	
	public void taxAdjustmentsInsideJob() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[9]/div[1]/a/span")));
			driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();// Click close icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
			driver.findElement(By.linkText("Home")).click();// navigate to home
			createNewJob(JobName, SalesRep, TaxTerritory);
			changeStatusToBooked(customername);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Releases")));
			driver.findElement(By.linkText("Releases")).click();// navigate to Releases
			releaseStockOrder(Notes, Allocated);// Create a stock order
			addLineItemsForStockorder(soproductname, soquantity);// add line items
			cusInvoiceForRelease(); // create customer invoice

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='shiping']/tbody/tr[@id='1']/td[8]")));
			driver.findElement(By.xpath("//table[@id='shiping']/tbody/tr[@id='1']/td[8]")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoicebtnID")));
			driver.findElement(By.id("customerInvoicebtnID")).click();
			Thread.sleep(3000);

			String cuInvoiceNumber1;
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'customerInvoice_invoiceNumberId']")));
			cuInvoiceNumber1 = driver.findElement(By.xpath("//input[@id = 'customerInvoice_invoiceNumberId']")).getText();
			System.out.println(cuInvoiceNumber1);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'CuInvoiceSaveCloseID']")));
			driver.findElement(By.xpath("//input[@id = 'CuInvoiceSaveCloseID']")).click();

			navigateCustomerTaxAdjustments(); // navigate to tax adjustments
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("cuInvNoid")));
			driver.findElement(By.id("cuInvNoid")).click();
			driver.findElement(By.id("cuInvNoid")).clear();
			driver.findElement(By.id("cuInvNoid")).sendKeys(cuInvoiceNumber1);// Enter invoice number
			driver.findElement(By.xpath("//input[@value = 'OK']")).click();// Click ok
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ogtaxterritoryoriginal")));
			driver.findElement(By.id("ogtaxterritoryoriginal")).click();
			driver.findElement(By.id("ogtaxterritoryoriginal")).clear();
			driver.findElement(By.id("ogtaxterritoryoriginal")).sendKeys(TxTerritory);// Edit tax territory
			driver.findElement(By.xpath("//input[@value = 'Update']")).click();// Click update
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Tax adjustments for customer invoice outside job
	@Test(enabled = false, priority = 3)	
	public void taxAdjustmentsOutsideJob() throws InterruptedException, Exception
	{

		//customerInvoices();// navigate to customer invoice 
		//createCustomerInvoices();// create customer invoice

	}


	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
	}

}
