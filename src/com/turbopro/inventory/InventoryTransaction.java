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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.turbopro.MethodsLibrary.Methods;

public class InventoryTransaction extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, LineItem;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/SalesOrderInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
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

	/*logging into the application and navigate to inventory transaction*/
	@Test(enabled = true, priority = 1)	
	public void inventoryTransaction() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateInventoryTransactions();
	}

	/*view transactions of an inventory item*/
	@Test(enabled = true, priority = 2)	
	public void viewTransaction() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(LineItem);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*view transactions indifferent warehouses*/
	@Test(enabled = true, priority = 3)	
	public void viewTransactionInDifferentWarehouse() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(LineItem);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
			driver.findElement(By.id("warehouseListID")).click();
			Thread.sleep(2000);
			Select memotype = new Select(driver.findElement(By.id("warehouseListID")));
			Thread.sleep(2000);
			memotype.selectByVisibleText("FT WORTH");
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table[3]/tbody/tr/td[3]/input[1]")));
			driver.findElement(By.xpath("//body/div[1]/div[2]/table[3]/tbody/tr/td[3]/input[1]")).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*view transactions with specific range*/
	@Test(enabled = true, priority = 4)	
	public void setDateRange() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("fromDateID")));
			driver.findElement(By.id("fromDateID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("fromDateID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("10")));
			driver.findElement(By.linkText("10")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("toDateID")));
			driver.findElement(By.id("toDateID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("toDateID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("20")));
			driver.findElement(By.linkText("20")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[2]/table[3]/tbody/tr/td[3]/input[1]")));
			driver.findElement(By.xpath("//body/div[1]/div[2]/table[3]/tbody/tr/td[3]/input[1]")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*clear transaction details with respect to Date range and warehouse*/
	@Test(enabled = true, priority = 5)	
	public void clearTransactions() throws InterruptedException, Exception
	{
		try{
			viewTransactionInDifferentWarehouse();
			setDateRange();
			Thread.sleep(3000);
			//		WebDriverWait wait = new WebDriverWait(driver, 30);
			//		wait.until(ExpectedConditions.elementToBeClickable(By.id("resetbutton")));
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value = 'Clear']")));
			driver.findElement(By.xpath("//input[@value = 'Clear']")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//print transaction details
	@Test(enabled = true, priority = 6)	
	public void printTransactionDetails() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(LineItem);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/div[4]/table/tbody/tr/td/input")));
			driver.findElement(By.xpath("//body/div[1]/div[4]/table/tbody/tr/td/input")).click();
			parentWindow();
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
