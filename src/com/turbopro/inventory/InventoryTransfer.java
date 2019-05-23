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
			waitforxpath("//*[@id='jqgh_transferGrid_transferDate']");
			getxpath("//*[@id='jqgh_transferGrid_transferDate']").click();
			waitforxpath("//*[@id='jqgh_transferGrid_transferDate']");
			getxpath("//*[@id='jqgh_transferGrid_transferDate']/span/span[2]").click();
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
			waitforxpath(inventoryTransferAddButton);
			getxpath(inventoryTransferAddButton).click();
			waitforxpath(inventoryTransferDate);
			getxpath(inventoryTransferDate).click();
			Thread.sleep(2000);
			waitforlinktext("10");
			getlinktext("10").click();
			Thread.sleep(3000);
			waitforxpath(inventoryTransferEstRecdDate);
			getxpath(inventoryTransferEstRecdDate).click();
			waitforlinktext("11");
			getlinktext("11").click();
			Thread.sleep(2000);
			getxpath(inventoryTransferReference).click();
			getxpath(inventoryTransferReference).clear();
			getxpath(inventoryTransferReference).sendKeys(Reference);	// Enter reference
			Thread.sleep(2000);
			Select warehouseFrom = new Select(getxpath(inventoryTransferFrom));
			Thread.sleep(2000);
			warehouseFrom.selectByVisibleText("Fort Worth");
			Thread.sleep(3000);
			Select warehouseTo = new Select(getxpath(inventoryTransferTo));
			Thread.sleep(3000);
			warehouseTo.selectByVisibleText("Houston");
			Thread.sleep(3000);
			waitforxpath(inventoryTransferAddIcon);
			getxpath(inventoryTransferAddIcon).click();
			waitforxpath(inventoryTransferProductNo);
			getxpath(inventoryTransferProductNo).click();
			Thread.sleep(2000);
			getxpath(inventoryTransferProductNo).sendKeys(LineItem);
			getxpath(inventoryTransferProductNo).sendKeys(Keys.ARROW_DOWN);
			getxpath(inventoryTransferProductNo).sendKeys(Keys.RETURN);
			Thread.sleep(3000);
			waitforxpath("//body/ul[14]/li/a");	
			getxpath("//body/ul[14]/li/a").click();
			waitforxpath(inventoryTransferQtyTransfer);
			getxpath(inventoryTransferQtyTransfer).click();
			Thread.sleep(2000);
			getxpath(inventoryTransferQtyTransfer).sendKeys(Quantity);
			waitforxpath(inventoryTransferSubmit);
			getxpath(inventoryTransferSubmit).click();
			Thread.sleep(2000);
			waitforxpath(inventoryTransferSaveAndClose);
			getxpath(inventoryTransferSaveAndClose).click();
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
			waitforxpath(inventoryTransferSearch);
			getxpath(inventoryTransferSearch).click();
			getxpath(inventoryTransferSearch).clear();
			getxpath(inventoryTransferSearch).sendKeys(TransNo);
			waitforxpath("//*[@id='1']/td[4]");	
			getxpath("//*[@id='1']/td[4]").click();
			waitforxpath(inventoryTransferGo);
			getxpath(inventoryTransferGo).click();
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
			waitforxpath("//*[@id='1']/td[3]");
			getxpath("//*[@id='1']/td[3]").click();
			waitforxpath(inventoryTransferEditButton);	
			getxpath(inventoryTransferEditButton).click();
			waitforxpath(inventoryTransferEditIcon);
			getxpath(inventoryTransferEditIcon).click();
			waitforxpath(inventoryTransferQtyTransfer);
			getxpath(inventoryTransferQtyTransfer).click();
			getxpath(inventoryTransferQtyTransfer).clear();
			Thread.sleep(2000);
			getxpath(inventoryTransferQtyTransfer).sendKeys(Quantity1);
			waitforxpath(inventoryTransferSubmit);
			getxpath(inventoryTransferSubmit).click();
			Thread.sleep(2000);
			getxpath(inventoryTransferSaveAndClose).click();
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
