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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.turbopro.MethodsLibrary.Methods;

public class InventoryScrap extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Description, LineItem, Count, InventorySearch, InventoryWarehouse1, InventoryWarehouse2, InventoryWarehouse3,
	InventoryCode, InventoryDescription, InventoryDeparment;
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
		Description= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Description")).toString();
		LineItem = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"LineItem")).toString();
		Count= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Count")).toString();
		InventorySearch = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventorySearch")).toString();

		InventoryWarehouse1 = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryWarehouse1")).toString();
		InventoryWarehouse2 = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryWarehouse2")).toString();
		InventoryWarehouse3 = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryWarehouse3")).toString();

		
		InventoryCode = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryCode")).toString();
		InventoryDescription = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryDescription")).toString();
		InventoryDeparment= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryDeparment")).toString();

		
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

	/*logging into the application and navigate to inventory adjustments*/
	@Test(enabled = true, priority = 1)	
	public void inventoryAdjustments() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("mainmenuInventoryPage")));
		driver.findElement(By.id("mainmenuInventoryPage")).click();
	}


	/*Searching specific inventory item*/
	@Test(enabled = true, priority = 2)	
	public void searchInventory() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys(LineItem); //searched inventory item is "DMRR0604"
		driver.findElement(By.id("goSearchButtonID")).click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='updateInventoryDetails()']")));
		driver.findElement(By.xpath("//input[@onclick='updateInventoryDetails()']")).click();
	}

	/*Search related inventory items and reset*/
	@Test(enabled = true, priority = 3)	
	public void searchRelatedInventory() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.findElement(By.id("mainmenuInventoryPage")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys(InventorySearch); //inventory search term is "DMRR"
		driver.findElement(By.id("goSearchButtonID")).click();
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		driver.findElement(By.id("resetbutton")).click();
	}

	
	/*Sort inventory items by selecting warehouse*/
	@Test(enabled = true, priority = 4)	
	public void sortInventoryByWarehouse() throws InterruptedException, Exception
	{
		Select Warehouse = new Select(driver.findElement(By.id("bankAccountsID")));
		Warehouse.selectByVisibleText(InventoryWarehouse1); // to select warehouse "FT WORTH" 
		Thread.sleep(6000); 
		Warehouse.selectByVisibleText(InventoryWarehouse2);  // to select warehouse "Houston"
		Thread.sleep(6000);
		Warehouse.selectByVisibleText(InventoryWarehouse3); // to select warehouse "Austin"
		Thread.sleep(6000);
		driver.findElement(By.id("resetbutton")).click();
		driver.navigate().refresh();
	}



	/*View inactive inventory items*/
	@Test(enabled = true, priority = 5)	
	public void viewInactiveInventory() throws InterruptedException, Exception
	{
		Thread.sleep(6000);
		driver.findElement(By.id("inactivelist")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("inactivelist")).click();
	}

	/*Create a new inventory item*/
	@Test(enabled = true, priority = 6)	
	public void createNewInventory() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.id("addCustomersButton")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("codeId")));
		driver.findElement(By.id("codeId")).sendKeys(InventoryCode); // inventory code is "TestInv1"
		driver.findElement(By.id("descriptionId")).sendKeys(InventoryDescription);  //inventory description is "TestInventoryNumber1"
		Select Department = new Select(driver.findElement(By.id("departmentId")));
		Department.selectByVisibleText(InventoryDeparment); //inventory department is "ENGINEERING" 
		driver.findElement(By.id("inventoryIDBox")).click();
		if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Save & Close']")).click();
	}

	/*Edit inventory item*/
	@Test(enabled = true, priority = 7)	
	public void editInventoryItem() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys(InventoryCode); // search the inventory code is "TestInv1"
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("multiplierId")));
		driver.findElement(By.id("multiplierId")).sendKeys("1");
		driver.findElement(By.id("factoryCostId")).clear();
		driver.findElement(By.id("factoryCostId")).sendKeys("2.5");
		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Save & Close']")).click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.findElement(By.id("mainmenuInventoryPage")).click();
	}

	/*Delete the inventory item*/
	@Test(enabled = true, priority = 8)	
	public void deleteInventoryItem() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys(InventoryCode); //search the inventory code is "TestInv1"
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteInventory")));
		driver.findElement(By.id("deleteInventory")).click();
		if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		Thread.sleep(2000);
		if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		Thread.sleep(3000);
		driver.findElement(By.id("resetbutton")).click();
		Thread.sleep(3000);
	}

	/*Open any one inventory item by double clicking*/
	@Test(enabled = true, priority = 9)	
	public void openInventoryItem() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
		Actions openInv = new Actions(driver);
		openInv.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[5]"))).doubleClick().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Save & Close']")).click();
	}

	/*View inventory items using next and previous buttons*/
	@Test(enabled = true, priority = 10)	
	public void viewInventoryItemsNextAndPrevious() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
		Actions openInv = new Actions(driver);
		openInv.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[5]"))).doubleClick().perform();
		Thread.sleep(3000);
		
		for(int next = 1; next<=9; next++)
		{
			driver.findElement(By.id("nextButton")).click();
			Thread.sleep(3000);
		}
		
		for(int previous = 1; previous<=9; previous++)
		{
			driver.findElement(By.id("previousButton")).click();
			Thread.sleep(3000);
		}
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Save & Close']")).click();
		
	}

	
	
	
	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}
