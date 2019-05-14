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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class InventoryTransfer extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, LineItem, Quantity1, TransNo,  Reference, Quantity;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	/*accessing the chrome driver*/
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
		Quantity1= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Quantity1")).toString();
		Reference= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Reference")).toString();
		TransNo= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"TransNo")).toString();
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


	/*logging into the application and navigate to Inventory transfer*/
	@Test(enabled = true, priority = 1)	
	public void inventoryTransfer() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateInventoryTransfer();
	}

	/*sorting headers in Inventory table*/
	@Test(enabled = true, priority = 2)	
	public void sortInventory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgh_transferGrid_transferDate']")));
			driver.findElement(By.xpath("//*[@id='jqgh_transferGrid_transferDate']")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgh_transferGrid_transferDate']")));
			driver.findElement(By.xpath("//*[@id='jqgh_transferGrid_transferDate']/span/span[2]")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Add new transfer*/
	@Test(enabled = true, priority = 3)	
	public void addTransfer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[1]/input")));
			driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[1]/input")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("transferDateId")));
			driver.findElement(By.id("transferDateId")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("transferDateId")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("10")));
			driver.findElement(By.linkText("10")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("estDateId")));
			driver.findElement(By.id("estDateId")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("estDateId")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("11")));
			driver.findElement(By.linkText("11")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("ref")).click();
			driver.findElement(By.id("ref")).clear();
			driver.findElement(By.id("ref")).sendKeys(Reference);// Enter reference
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouseFrom")));
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			act.moveToElement(driver.findElement(By.id("warehouseFrom"))).click().build().perform();
			Thread.sleep(2000);
			Select warehouseFrom = new Select(driver.findElement(By.id("warehouseFrom")));
			Thread.sleep(2000);
			warehouseFrom.selectByVisibleText("Fort Worth");
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouseTo")));
			act.moveToElement(driver.findElement(By.id("warehouseTo"))).click().build().perform();
			Thread.sleep(2000);
			Select warehouseTo = new Select(driver.findElement(By.id("warehouseTo")));
			Thread.sleep(3000);
			warehouseTo.selectByValue("2");
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add_addtransferGrid']/div/span")));
			driver.findElement(By.xpath("//*[@id='add_addtransferGrid']/div/span")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemCode")));
			driver.findElement(By.id("itemCode")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("itemCode")).sendKeys(LineItem);
			driver.findElement(By.id("itemCode")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("itemCode")).sendKeys(Keys.RETURN);
			Thread.sleep(3000);
//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[14]/li/a")));	
//			driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("quantityTransfered")));
			driver.findElement(By.id("quantityTransfered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("quantityTransfered")).sendKeys(Quantity);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sData")));
			driver.findElement(By.id("sData")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("WarehouseTransferID")));
			driver.findElement(By.id("WarehouseTransferID")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Search transfer details*/
	@Test(enabled = true, priority = 4)	
	public void searchTransfer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys(TransNo);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));	
			driver.findElement(By.xpath("//*[@id='1']/td[4]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			driver.findElement(By.id("goSearchButtonID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Edit transfer details*/
	@Test(enabled = true, priority = 5)	
	public void editTransfer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			driver.findElement(By.xpath("//*[@id='1']/td[3]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[2]/input")));	
			driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[2]/input")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit_addtransferGrid']/div/span")));
			driver.findElement(By.xpath("//*[@id='edit_addtransferGrid']/div/span")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("quantityTransfered")));
			driver.findElement(By.id("quantityTransfered")).click();
			driver.findElement(By.id("quantityTransfered")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("quantityTransfered")).sendKeys(Quantity1);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sData")));
			driver.findElement(By.id("sData")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@onclick='saveTransfer()']")).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*receive transfer items*/
	@Test(enabled = true, priority = 6)	
	public void receiveItems() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			driver.findElement(By.xpath("//*[@id='1']/td[3]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[2]/input")));	
			driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[2]/input")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("WarehouseTransferReceiveID")));
			driver.findElement(By.id("WarehouseTransferReceiveID")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'saveTransfer()']")));
			driver.findElement(By.xpath("//input[@onclick = 'saveTransfer()']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*copy transfer items*/
	@Test(enabled = true, priority = 7)	
	public void copyTransfer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='transferGrid']/tbody/tr[@id='3']/td[3]")));
			driver.findElement(By.xpath("//table[@id='transferGrid']/tbody/tr[@id='3']/td[3]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[3]/input")));	
			driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[3]/input")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("WarehouseTransferSaveID")));
			driver.findElement(By.id("WarehouseTransferSaveID")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("WarehouseTransferID")));
			driver.findElement(By.id("WarehouseTransferID")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*view PDF*/
	@Test(enabled = true, priority = 8)	
	public void viewPDF() throws InterruptedException, Exception
	{
		try{
			WebElement Inventory = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
			Actions action = new Actions(driver);
			action.moveToElement(Inventory).perform();
			driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[4]")).click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[4]/input")));
			driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[4]/input")).click();
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
