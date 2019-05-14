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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class OrderPoints extends Methods {
	private String Url, UName, Password, LineItem, Quantity;
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
	public void orderPoints() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateInventoryOrderPoints();	// navigating to order points
	}

	//Sort order points headers
	@Test(enabled = true, priority = 2)	
	public void sortHeaders() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqgh_chartsOfOrderPointsGrid_itemCode")));
			driver.findElement(By.id("jqgh_chartsOfOrderPointsGrid_itemCode")).click();// s
			driver.findElement(By.xpath("//*[@id='jqgh_chartsOfOrderPointsGrid_itemCode']/span/span[2]")).click();// sort item code
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//select warehouses
	@Test(enabled = true, priority = 3)	
	public void ViewOrderPoints() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'DALLAS']")));
			driver.findElement(By.xpath("//td[@title = 'DALLAS']")).click();// select dallas Warehouse

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'Houston']")));
			driver.findElement(By.xpath("//td[@title = 'Houston']")).click();// select houston Warehouse
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//select warehouses
	@Test(enabled = true, priority = 4)	
	public void searchItem() throws InterruptedException, Exception
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

	//Edit order points
	@Test(enabled = true, priority = 5)	
	public void editOrderPoints() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'DMRR0604' ]")));
			driver.findElement(By.xpath("//td[@title = 'DMRR0604' ]")).click();// select a line

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit_chartsOfOrderPointsGrid']/div/span")));
			driver.findElement(By.xpath("//*[@id='edit_chartsOfOrderPointsGrid']/div/span")).click();// click edit icon

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("inventoryOrderPoint")));
			driver.findElement(By.id("inventoryOrderPoint")).click();
			driver.findElement(By.id("inventoryOrderPoint")).clear();
			driver.findElement(By.id("inventoryOrderPoint")).sendKeys(Quantity);// Enter order points

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("inventoryOrderQuantity")));
			driver.findElement(By.id("inventoryOrderQuantity")).click();
			driver.findElement(By.id("inventoryOrderQuantity")).clear();
			driver.findElement(By.id("inventoryOrderQuantity")).sendKeys(Quantity);// Enter order points

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sData")));
			driver.findElement(By.id("sData")).click();// click submit
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//print Order points
	@Test(enabled = true, priority = 6)	
	public void printReorderedPoints() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("textfont")));
			driver.findElement(By.id("textfont")).click();// click suggested reorder button
			Thread.sleep(5000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("orderPointPrint")));
			driver.findElement(By.id("orderPointPrint")).click();// click print

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
