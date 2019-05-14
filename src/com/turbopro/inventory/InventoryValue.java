package com.turbopro.inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
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

public class InventoryValue extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, LineItem;
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
	public void inventoryValue() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateInventoryValue();	// navigating to inventory value
	}

	//Sort column headers
	@Test(enabled = true, priority = 2)	
	public void sortHeaders() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgh_inventoryValueGrid_itemCode']/span/span[2]")));
			driver.findElement(By.xpath("//*[@id='jqgh_inventoryValueGrid_itemCode']/span/span[2]")).click(); // Click sort icon
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//View CSV report
	@Test(enabled = true, priority = 3)	
	public void viewCSV() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@onclick = 'exportInventoryList()']")));
			driver.findElement(By.xpath("//img[@onclick = 'exportInventoryList()']")).click(); // Click to view CSV report
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Search inventory items
	@Test(enabled = true, priority = 4)	
	public void searchItems() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(LineItem);// Enter Item code in search field

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			driver.findElement(By.id("goSearchButtonID")).click();// click Go button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Reset inventory grid
	@Test(enabled = true, priority = 5)	
	public void resetInventory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resetbutton")));
			driver.findElement(By.id("resetbutton")).click();// click reset button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Select warehouses
	@Test(enabled = true, priority = 6)	
	public void selectWarehouse() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("bankAccountsID")));
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			act.moveToElement(driver.findElement(By.id("bankAccountsID"))).click().build().perform();
			Thread.sleep(2000);
			Select memotype = new Select(driver.findElement(By.id("bankAccountsID")));
			Thread.sleep(2000);
			memotype.selectByValue("1");// Select forth worth Warehouse
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
