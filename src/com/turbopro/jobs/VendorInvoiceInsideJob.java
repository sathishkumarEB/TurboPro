package com.turbopro.jobs;


import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class VendorInvoiceInsideJob extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername, Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email, Vendorname;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/JobInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
		Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Dropshipmanufacturer= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DropshipManufacturer")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		SO_Productname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		Email= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Email")).toString();
		Vendorname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();
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

	//logging into the application
	@Test(enabled = true, priority = 1)		
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	@Test(enabled = true, priority = 2)	
	public void createJob() throws InterruptedException, Exception
	{

		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername);	//book a job with customer name
	}

	//TP_CII_0002
	@Test(enabled = true, priority = 3)	
	public void dropship() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); //dropship release
	}


	@Test(enabled = true, priority = 4)	//false
	public void VIforDropship() throws InterruptedException
	{
		selectDropshipRelease();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Yes')]")));
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Cancel')]")));
		driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();
	}

	@Test(enabled = false, priority = 5) //can remove this
	public void dropship1() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); //dropship release
	}


	//changing the vendor name while creating vendor invoice 
	@Test(enabled = true, priority = 6) //false
	public void changeVendorName() throws InterruptedException, Exception
	{
		this.dropship();
		selectDropshipRelease();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Yes')]")));
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();

		driver.findElement(By.id("manufacurterNameID")).click();
		driver.findElement(By.id("manufacurterNameID")).clear();
		driver.findElement(By.id("manufacurterNameID")).sendKeys(Vendorname);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., '"+ Vendorname +"')]"))); //some issue is there on clicking 
		driver.findElement(By.xpath("//li//a[contains(., '"+ Vendorname +"')]")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Cancel')]")));
		driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();

	}


	//create partial vendor invoice
	@Test(enabled = true, priority = 7) //false
	public void partialVendorInvoice() throws InterruptedException, Exception
	{
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addMultiLineItemsForDropship();		//dropship with multiple line items	

		//first invoice
		selectDropshipRelease();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Yes')]")));
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();

		driver.findElement(By.id("canDoVIID_4")).click();
		driver.findElement(By.id("canDoVIID_3")).click();

		Random Number = new Random();
		Integer ranNumber = Number.nextInt(1000);
		String randomNumber = ranNumber.toString();
		System.out.println("Random number for vendor invoice:" + randomNumber);

		driver.findElement(By.id("vendorInvoiceNum")).sendKeys(randomNumber);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();

		//second invoice
		selectDropshipRelease();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Yes')]")));
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();

		Random Number1 = new Random();
		Integer ranNumber1 = Number1.nextInt(1000);
		String randomNumber1 = ranNumber1.toString();
		driver.findElement(By.id("vendorInvoiceNum")).sendKeys(randomNumber1);

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Cancel')]")));
		driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();

	}


	//updating the existing purchase order(dropship) with multiple line items and create vendor invoice for the same
	@Test(enabled = true, priority = 8)
	public void updateLineItems() throws InterruptedException, Exception
	{
		this.dropship(); //calling the dropship method again
		selectDropshipRelease();
		driver.findElement(By.linkText("Order")).click();
		addMultiLineItemsForDropship();
		selectDropshipRelease();
		venInvoiceForDropship();		//some issue is there on clicking 
	}


	//import lineitems from xml and create vendor invoice
	@Test(enabled = false, priority = 9) //false
	public void VIforImportedDropship() throws InterruptedException, Exception
	{
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		importXmlForDropship();				//dropship by adding line items through importing xml
		selectDropshipRelease();
		venInvoiceForDropship();
	}


	//update vendor invoice details for dropship release 
	@Test(enabled = true, priority = 10)  //false
	public void updateVI() throws InterruptedException, Exception
	{
		selectDropshipRelease();
		driver.findElement(By.xpath("//table[@id='shiping']/tbody/tr[2]/td[6]")).click();
		driver.findElement(By.id("vendorInvoicebtnID")).click();
		driver.findElement(By.id("vendorInvoiceNum")).sendKeys("testing3");
		driver.findElement(By.id("proNumberID")).sendKeys("3testing");
		driver.findElement(By.id("shipViaSelectID")).click();
		driver.findElement(By.xpath("//*[@id='shipViaSelectID']/option[4]")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();

		Thread.sleep(4000);
		if(driver.findElement(By.id("ui-dialog-title-jobinvreasondialog")).isDisplayed())
		{
			driver.findElement(By.id("jobinvreasonttextid")).click();
			driver.findElement(By.id("jobinvreasonttextid")).sendKeys("test");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//			driver.findElement(By.xpath("//body/div[33]/div[11]/div/button/span")).click();
			//			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();
		}

		if (driver.findElement(By.id("ui-dialog-title-msgDlg")).isDisplayed())
		{
			driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();		//cancel button is not clicking
		}
		Thread.sleep(3000);
	}

	//add freight to vendor invoice
	@Test(enabled = true, priority = 11) //false
	public void addFreightToVI() throws InterruptedException, Exception
	{
		selectDropshipRelease();
		driver.findElement(By.xpath("//table[@id='shiping']/tbody/tr[2]/td[6]")).click();
		driver.findElement(By.id("vendorInvoicebtnID")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("freight_ID")));
		driver.findElement(By.id("freight_ID")).click();
		driver.findElement(By.id("freight_ID")).clear();
		driver.findElement(By.id("freight_ID")).sendKeys("240");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("vendorinvoiceidbutton")));
		driver.findElement(By.id("vendorinvoiceidbutton")).click();
		if(driver.findElement(By.id("ui-dialog-title-jobinvreasondialog")).isDisplayed())
		{
			driver.findElement(By.id("jobinvreasonttextid")).sendKeys("test");
			driver.findElement(By.xpath("//body/div[33]/div[11]/div/button/span")).click();
		}

		if (driver.findElement(By.id("ui-dialog-title-msgDlg")).isDisplayed())
		{
			driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();		//cancel button is not clicking
		}
	}


	//vendor invoice for commission order 
	@Test(enabled = false, priority = 12)
	public void VIforCommissionOrder() throws InterruptedException, Exception
	{
		releaseCommission(Dropshipmanufacturer, Notes, Allocated); //commission order
		addLineItemsForDropship(); //reusing the method which is used for adding line items in dropship
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Commission']")));
		driver.findElement(By.xpath("//td[@title='Commission']")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input")).click();

		if(driver.findElement(By.id("ui-dialog-title-commissionDialogBox")).isDisplayed())
		{
			driver.findElement(By.id("commissionDialogShipVIAVal")).click();
			driver.findElement(By.xpath("//*[@id='commissionDialogShipVIAVal']/option[4]")).click();

			driver.findElement(By.id("commissionDialogTrackingPROVal")).sendKeys("123");
			driver.findElement(By.id("commissionDialogVendorInvoiceVal")).sendKeys("123");
			driver.findElement(By.id("commissionDialogInvoiceAmtVal")).clear();
			driver.findElement(By.id("commissionDialogInvoiceAmtVal")).sendKeys("234");

			driver.findElement(By.id("commissionDialogCommissionsAmtVal")).clear();
			driver.findElement(By.id("commissionDialogCommissionsAmtVal")).sendKeys("100");

			driver.findElement(By.xpath("//input[@value='Submit']")).click();

		}

	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}