package com.turbopro.inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class ReceiveInventory extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, LineItem, VendorName, PO, Quantity;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/InventoryInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"password")).toString();
		LineItem = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"LineItem")).toString();
		Quantity= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Quantity")).toString();
		PO= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"PO")).toString();
		VendorName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"VendorName")).toString();
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

	//logging into the application and navigate to inventory transaction
	@Test(enabled = true, priority = 1)	
	public void inventoryReceive() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		receiveInventory();	// navigating to receive inventory
	}

	//search PO
	@Test(enabled = true, priority = 2)	
	public void searchPO() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(PO);// Enter PO in search field

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			driver.findElement(By.id("goSearchButtonID")).click();// click Go button
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Reset search field
	@Test(enabled = true, priority = 3)	
	public void resetSearch() throws InterruptedException, Exception
	{
		try{
			searchPO();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resetbutton")));
			driver.findElement(By.id("resetbutton")).click();// click reset button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Sort transactions with date range
	@Test(enabled = true, priority = 4)	
	public void selectDateRange() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("dateRange")));
			driver.findElement(By.id("dateRange")).click();// click received date check box
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("fromDate")));
			driver.findElement(By.id("fromDate")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("fromDate")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("10")));
			driver.findElement(By.linkText("10")).click();// select from date range
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("toDate")));
			driver.findElement(By.id("toDate")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("toDate")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("20")));
			driver.findElement(By.linkText("20")).click();// select to date range
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Receive line items
	@Test(enabled = true, priority = 5)	
	public void receiveLineItems() throws InterruptedException, Exception
	{
		try{
			navigateVendorPurchaseOrders();// Navigate to vendor purchase order

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")));
			driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")).click();// Click Add

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorsearch")));
			driver.findElement(By.id("vendorsearch")).click();
			driver.findElement(By.id("vendorsearch")).clear();
			driver.findElement(By.id("vendorsearch")).sendKeys(VendorName);// Enter PO in search field
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();// Select specific vendor name

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'addPurchaseOrder()' ]")));
			driver.findElement(By.xpath("//input[@onclick = 'addPurchaseOrder()' ]")).click();// click save
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lineTab")));
			driver.findElement(By.id("lineTab")).click();// navigate to line items 
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
			driver.findElement(By.id("new_row_note")).click();
			driver.findElement(By.id("new_row_note")).clear();
			driver.findElement(By.id("new_row_note")).sendKeys(LineItem);// Enter product no
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[16]/li[1]/a")));	
			driver.findElement(By.xpath("//body/ul[16]/li[1]/a")).click();// Select specific product no

			driver.findElement(By.id("new_row_quantityOrdered")).click();
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);// Hit Enter

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveLinesPOButton")));
			driver.findElement(By.id("SaveLinesPOButton")).click();// click save
			Thread.sleep(3000);

			driver.findElement(By.id("ourPoLineId")).click();// click save
			String ourPO = driver.findElement(By.id("ourPoLineId")).getAttribute("value"); //getting PO number
			System.out.println(ourPO);

			WebElement Inventory = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
			Actions action = new Actions(driver);
			action.moveToElement(Inventory).perform();
			driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[3]")).click();// navigating to receive inventory

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input")));
			driver.findElement(By.xpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input")).click();// Click New

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
			driver.findElement(By.id("ponumber")).click();
			driver.findElement(By.id("ponumber")).clear();
			driver.findElement(By.id("ponumber")).sendKeys(ourPO);// Enter PO number

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[10]/div[11]/div/button[1]/span")));
			driver.findElement(By.xpath("//body/div[10]/div[11]/div/button[1]/span")).click();// Click ok

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ReceiveAllSaveID")));
			driver.findElement(By.id("ReceiveAllSaveID")).click();// Click Receive All

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POSaveID")));
			driver.findElement(By.id("POSaveID")).click();// Click save
			Thread.sleep(5000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POCloseID")));
			driver.findElement(By.id("POCloseID")).click();// Click close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//view receive inventory details of a purchase order
	@Test(enabled = true, priority = 6)	
	public void ViewReceivedPO() throws InterruptedException, Exception
	{
		try{
			WebElement Inventory = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
			Actions action = new Actions(driver);
			action.moveToElement(Inventory).perform();
			driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[3]")).click();// navigating to receive inventory

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(PO);// Enter PO in search field

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			driver.findElement(By.id("goSearchButtonID")).click();// click Go button
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='lineItemGrid']/tbody/tr[@id='1']/td[5]")));
			driver.findElement(By.xpath("//table[@id='lineItemGrid']/tbody/tr[@id='1']/td[5]")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table[@id='lineItemGrid']/tbody/tr[@id='1']/td[5]"))).doubleClick().perform();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POCloseID")));
			driver.findElement(By.id("POCloseID")).click();	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Receive PO items partially through search field*/
	@Test(enabled = true, priority = 7)	
	public void receivePartialPOItems() throws InterruptedException, Exception
	{
		try{
			navigateVendorPurchaseOrders();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")));
			driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("vendorsearch")));
			driver.findElement(By.id("vendorsearch")).click();
			driver.findElement(By.id("vendorsearch")).clear();
			driver.findElement(By.id("vendorsearch")).sendKeys(VendorName);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'addPurchaseOrder()' ]")));
			driver.findElement(By.xpath("//input[@onclick = 'addPurchaseOrder()' ]")).click();
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lineTab")));
			driver.findElement(By.id("lineTab")).click();
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
			driver.findElement(By.id("new_row_note")).click();
			driver.findElement(By.id("new_row_note")).clear();
			driver.findElement(By.id("new_row_note")).sendKeys(LineItem);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[16]/li[1]/a")));	
			driver.findElement(By.xpath("//body/ul[16]/li[1]/a")).click();

			driver.findElement(By.id("new_row_quantityOrdered")).click();
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveLinesPOButton")));
			driver.findElement(By.id("SaveLinesPOButton")).click();
			Thread.sleep(3000);

			driver.findElement(By.id("ourPoLineId")).click();
			String ourPO = driver.findElement(By.id("ourPoLineId")).getAttribute("value"); 
			System.out.println(ourPO);

			WebElement Inventory = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
			Actions action = new Actions(driver);
			action.moveToElement(Inventory).perform();
			driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[3]")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(ourPO);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input")));
			driver.findElement(By.xpath("//body/div[1]/div[2]/table[1]/tbody/tr/td[2]/div/input")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
			driver.findElement(By.id("ponumber")).click();
			driver.findElement(By.id("ponumber")).clear();
			driver.findElement(By.id("ponumber")).sendKeys(ourPO);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[10]/div[11]/div/button[1]/span")));
			driver.findElement(By.xpath("//body/div[10]/div[11]/div/button[1]/span")).click();
			Thread.sleep(3000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='lineItemGrid']/tbody/tr[@id='1']/td[5]")));
			driver.findElement(By.xpath("//table[@id='lineItemGrid']/tbody/tr[@id='1']/td[5]")).click();
			driver.findElement(By.id("1_quantityReceived")).clear();
			driver.findElement(By.id("1_quantityReceived")).sendKeys(Quantity);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POSaveID")));
			driver.findElement(By.id("POSaveID")).click();
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POSaveID")));
			driver.findElement(By.id("POSaveID")).click();
			Thread.sleep(5000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POCloseID")));
			driver.findElement(By.id("POCloseID")).click();
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
