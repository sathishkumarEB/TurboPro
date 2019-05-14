package com.turbopro.vendors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
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

public class VendorPurchaseOrders extends Methods {

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO;

	private String Url, UName, Password, PayableTo, VendorName, LineItem, Quantity;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

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

	//TC_VPO_0001 - logging into the application and navigate to vendor purchase orders
	@Test(enabled = true, priority = 1)	
	public void vendorPurchaseOrder() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateVendorPurchaseOrders();
	}

	//TC_VPO_0002 - sorting the vendor purchase orders list 
	@Test(enabled = true, priority = 2)	
	public void sortVendorPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_PurchaseOrdersGrid_ponumber")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_PurchaseOrdersGrid_createdOn")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_PurchaseOrdersGrid_jobName")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_PurchaseOrdersGrid_vendorName")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("jqgh_PurchaseOrdersGrid_subtotal")).click();
			Thread.sleep(2000);
		}

		driver.findElement(By.id("resetbutton")).click();
		Thread.sleep(4000);
	}

	//TC_VPO_0003 - view purchase orders based on date range 
	@Test(enabled = true, priority = 3)	
	public void viewVendorPOByDateRange() throws InterruptedException, Exception
	{
		driver.findElement(By.id("dateRange")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("fromDate")).click();
		driver.findElement(By.linkText("1")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("toDate")).click();
		driver.findElement(By.linkText("28")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("resetbutton")).click();
	}


	//TC_VPO_0004 - create a vendor purchase order
	@Test(enabled = true, priority = 4)	
	public void createVendorPO() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//input[@onclick='addNewPO()']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorsearch")));

		driver.findElement(By.id("vendorsearch")).click();
		driver.findElement(By.id("vendorsearch")).clear();
		driver.findElement(By.id("vendorsearch")).sendKeys("Vector Industries");
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

		//		List<WebElement> Vendors = driver.findElements(By.className("ui-corner-all"));
		//		
		//		WebDriverWait wT= null;
		//		wT.until(ExpectedConditions.elementToBeClickable(By.className("ui-corner-all")));
		//		Vendors.get(73).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("POSaveID")));

		driver.findElement(By.id("POSaveID")).click();
		Thread.sleep(4000);

		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		ourPO = driver.findElement(By.id("ourPoLineId")).getAttribute("value"); //getting PO number
		System.out.println(ourPO);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
		driver.findElement(By.id("new_row_note")).sendKeys(LineItem);
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//body/ul[16]/li/a")).click();
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Quantity + Keys.ENTER);
		driver.findElement(By.id("SaveLinesPOButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("CancelLinesPOButton")).click();
		//return;

	}


	//TC_VPO_0005 - update vendor purchase order 
	@Test(enabled = true, priority = 5)	
	public void updateVendorPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
		driver.findElement(By.id("searchJob")).sendKeys(ourPO); //have to use string to give PO number here
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
		driver.findElement(By.id("new_row_note")).sendKeys(LineItem);
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//body/ul[15]/li/a")).click();
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Quantity + Keys.ENTER);
		driver.findElement(By.id("SaveLinesPOButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("CancelLinesPOButton")).click();
	}

	//TC_VPO_0006 - delete line items in purchase order and save it 
	@Test(enabled = true, priority = 6)	
	public void deleteLineItemsInPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));

		driver.findElement(By.id("searchJob")).sendKeys(ourPO); //have to use string to give PO number here
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("canDeletePOID_2")));
		driver.findElement(By.id("canDeletePOID_2")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("SaveLinesPOButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("CancelLinesPOButton")).click();
	}


	//TC_VPO_0007 - changing the status of the purchase order 
	@Test(enabled = true, priority = 7)	
	public void changeStatusInPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
		driver.findElement(By.id("searchJob")).sendKeys(ourPO); //have to use string to give PO number here
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]"))).doubleClick().perform();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("PoStatusButton")));
		driver.findElement(By.id("PoStatusButton")).click();


		if(driver.findElement(By.id("ui-dialog-title-showPOOptions")).isDisplayed())
		{
			driver.findElement(By.id("Void")).click();

			if(driver.findElement(By.id("ui-dialog-title-showPDFoptiondialog")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
			}
		}

		Thread.sleep(2000);
		driver.findElement(By.id("POSaveID")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("POSaveCloseID")).click();
	}

	//TC_VPO_0008 - searching a specific vendor PO
	@Test(enabled = true, priority = 8)	
	public void searchPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
		driver.findElement(By.id("searchJob")).sendKeys(ourPO); //have to use string to give PO number here
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]"))).doubleClick().perform();
		Thread.sleep(3000);
		driver.findElement(By.id("POSaveCloseID")).click();
		
	}
	
	//TC_VPO_0009 - view pdf of vendor purchase order
	@Test(enabled = true, priority = 9)	
	public void viewPdfOfVendorPO() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
		driver.findElement(By.id("searchJob")).sendKeys("102634"); //have to use string to give PO number here
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//table[@id='PurchaseOrdersGrid']/tbody/tr[@id='1']/td[8]"))).doubleClick().perform();
		Thread.sleep(3000);
		
		driver.findElement(By.id("generalTabPDF")).click();
		parentWindow();
		Thread.sleep(3000);
		driver.findElement(By.id("POSaveCloseID")).click();
		
	}
	
	
	
	

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
}
