package com.turbopro.customermenu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesOrderOutsideJob extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, Amount, VendorName, LineItem, Quantity, Freight, Notes, CustomerID, Customername, ProductNo, SO, Allocated, Pro, Reason, Email;
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
		Amount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Amount")).toString();
		Notes = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		CustomerID= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerID")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Customername")).toString();
		ProductNo= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"ProductNo")).toString();
		SO= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
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
			return patchColumn
					;
	}


	//TC_CSO_0001 - logging into the application and navigate to vendor purchase orders
	@Test(enabled = true, priority = 1)	
	public void salesOrder() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateCustomerSalesOrder();
		Thread.sleep(3000);
	}

	//TC_CSO_0002 - Creating Sales Order
	@Test( enabled = true, priority = 2)
	public void createSalesOrder() throws InterruptedException 
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'addNewSO()' ]")));
			driver.findElement(By.xpath("//input[@onclick = 'addNewSO()' ]")).click();// click Add button	
			driver.findElement(By.id("CustomerNameGeneral")).click();
			driver.findElement(By.id("CustomerNameGeneral")).sendKeys(Customername);// Enter Customer name

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains (.,'"+Customername+"')]")));	
			driver.findElement(By.xpath("//a[contains (.,'"+Customername+"')]")).click();// Select specific customer name



			//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[21]/li/a")));	
			//			driver.findElement(By.xpath("//body/ul[21]/li/a")).click();// Select specific customer name
			driver.findElement(By.id("promisedIDwz")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("19")));// select check date
			driver.findElement(By.linkText("19")).click();//Select promised date
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SavePOReleaseID']")));
			driver.findElement(By.xpath("//*[@id='SavePOReleaseID']")).click();// click save
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='POReleaseID']")));
			driver.findElement(By.xpath("//*[@id='POReleaseID']")).click();// click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0003 - Add line items
	@Test(enabled = true, priority = 3)
	public void addLineitems() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();
			Thread.sleep(15000);
			//		driver.findElement(By.id("SavePOReleaseID")).click();// click save	
			//		Thread.sleep(10000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));	
			driver.findElement(By.linkText("Line Items")).click();// navigate to line items tab
			Thread.sleep(5000);
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
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0004 - Delete line items
	@Test(enabled = true, priority = 4)	
	public void deleteLineItems() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order
			Thread.sleep(2000);
			driver.findElement(By.id("SavePOReleaseID")).click();// click saveThread.sleep(5000);
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));	
			driver.findElement(By.linkText("Line Items")).click();// navigate to line items tab
			Thread.sleep(4000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("canDeleteSOID_1")));
			driver.findElement(By.id("canDeleteSOID_1")).click();// click line item
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveLineSOReleaseID")));	
			driver.findElement(By.id("SaveLineSOReleaseID")).click();// Click save
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closeLineSOReleaseID")));
			driver.findElement(By.id("closeLineSOReleaseID")).click();// click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0005 - Change status
	@Test(enabled = true, priority = 5)	
	public void changeStatus() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order
			Thread.sleep(2000);
			driver.findElement(By.id("soStatusButton")).click();// Click status
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Close")));
			driver.findElement(By.id("Close")).click();//change status to close
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SavePOReleaseID")));	
			driver.findElement(By.id("SavePOReleaseID")).click();// Click save
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POReleaseID")));
			driver.findElement(By.id("POReleaseID")).click();// click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0006 - Add Freight
	@Test(enabled = true, priority = 6)	
	public void editSalesOrder() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order
			Thread.sleep(2000);
			driver.findElement(By.id("SOGeneral_frightID")).click();
			driver.findElement(By.id("SOGeneral_frightID")).clear();
			driver.findElement(By.id("SOGeneral_frightID")).sendKeys(Freight);// Enter amount
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SavePOReleaseID")));	
			driver.findElement(By.id("SavePOReleaseID")).click();// Click save
			//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));	
			//		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();// Click yes
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POReleaseID")));
			driver.findElement(By.id("POReleaseID")).click();// click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0007 - Search using SO#
	@Test(enabled = true, priority = 7)	
	public void searchUsingSO() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(SO);// Enter SO# in Search field
			Thread.sleep(2000);
			driver.findElement(By.id("goSearchButtonID")).click();// Click Go
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0008 - Search using customer name
	@Test(enabled = true, priority = 8)	
	public void searchUsingCustomerName() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(Customername);// Enter Customer name in Search field
			Thread.sleep(2000);
			driver.findElement(By.id("goSearchButtonID")).click();// Click Go
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0009 - Select Date Range
	@Test(enabled = true, priority = 9)	
	public void selectDateRange() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			driver.findElement(By.id("dateRange")).click();// click date range checkbox
			driver.findElement(By.id("fromDate")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("1")));// Select check date
			driver.findElement(By.linkText("1")).click();// select from date
			driver.findElement(By.id("toDate")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("28")));// Select check date
			driver.findElement(By.linkText("28")).click();// select from date
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0010 - View PDF 
	@Test(enabled = true, priority = 10)	
	public void viewPDF() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			Thread.sleep(3000);
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order
			Thread.sleep(2000);
			driver.findElement(By.id("generalSOTabPDF")).click();// view PDF
			// Close PDF
			parentWindow();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POReleaseID")));
			driver.findElement(By.id("POReleaseID")).click();// click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_CSO_0011 - Open Email compose popup
	@Test(enabled = true, priority = 11)	
	public void viewEmailComposePopup() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order
			Thread.sleep(10000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("contactEmailID_general")));
			driver.findElement(By.id("contactEmailID_general")).click();// click Email
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id= 'emailpopup']/table/tbody/tr/td[2]/input")));
			driver.findElement(By.xpath("//*[@id= 'emailpopup']/table/tbody/tr/td[2]/input")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("POReleaseID")).click();// Click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*RID 695 - Ability to change bill to address in the sales order*/
	@Test(enabled = true, priority = 12)	
	public void changeBillToInSalesOrder() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id= '1']/td[2]"))).doubleClick().perform();// open sales order

		driver.findElement(By.id("SOlocationbillToAddressname")).click();
		driver.findElement(By.id("SOlocationbillToAddressname")).clear();
		driver.findElement(By.id("SOlocationbillToAddressname")).sendKeys("Finisar - DMW");

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains (.,'Finisar - DMW')]")));	
		driver.findElement(By.xpath("//a[contains (.,'Finisar - DMW')]")).click();// Select specific customer name
		Thread.sleep(2000);
		Select division = new Select( driver.findElement(By.id("customer_Divisions")));
		division.selectByVisibleText("Bartos Air Solutions");
		Thread.sleep(2000);

		driver.findElement(By.id("CuInvoiceSaveID")).click();

		if(driver.findElement(By.xpath("//span[contains (.,'Success')]")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}

		if(driver.findElement(By.xpath("//span[contains (.,'Reason')]")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			driver.findElement(By.id("invreasonttextid")).sendKeys("test");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}

		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		
	}



	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}





