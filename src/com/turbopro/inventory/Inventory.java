package com.turbopro.inventory;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class Inventory extends Methods {
	private String Url, UName, Password;
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
		//		LineItem= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
	}

	//code for initialising the workbook or excel sheet
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

	/*logging into the application, navigate to inventory*/
	@Test(enabled = true, priority = 1)	
	public void navigateInventory() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("mainmenuInventoryPage")));
		getid("mainmenuInventoryPage").click();
	}

	/*Searching specific inventory item*/
	@Test(enabled = true, priority = 2)	
	public void searchInventory() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys("DMRR0604"); //searched inventory item is "DMRR0604"
			getid("goSearchButtonID").click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='updateInventoryDetails()']")));
			getxpath("//input[@onclick='updateInventoryDetails()']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Search related inventory items and reset*/
	@Test(enabled = true, priority = 3)	
	public void searchRelatedInventory() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			getid("mainmenuInventoryPage").click();
			Thread.sleep(3000);
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys("DMRR");
			getid("goSearchButtonID").click();
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			getid("resetbutton").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*Sort inventory items by selecting warehouse*/
	@Test(enabled = true, priority = 4)	
	public void sortInventoryByWarehouse() throws InterruptedException, Exception
	{
		try{
			Select Warehouse = new Select(driver.findElement(By.id("bankAccountsID")));
			Warehouse.selectByVisibleText("FT WORTH");
			Thread.sleep(6000);
			Warehouse.selectByVisibleText("Houston");
			Thread.sleep(6000);
			Warehouse.selectByVisibleText("Austin");
			Thread.sleep(6000);
			getid("resetbutton").click();
			driver.navigate().refresh();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}



	/*View inactive inventory items*/
	@Test(enabled = true, priority = 5)	
	public void viewInactiveInventory() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(6000);
			getid("inactivelist").click();
			Thread.sleep(4000);
			getid("inactivelist").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Create a new inventory item*/
	@Test(enabled = true, priority = 6)	
	public void createNewInventory() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getid("addCustomersButton").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("codeId")));
			getid("codeId").sendKeys("TestInv1");
			getid("descriptionId").sendKeys("TestInventoryNumber1");
			Select Department = new Select(driver.findElement(By.id("departmentId")));
			Department.selectByVisibleText("ENGINEERING");
			getid("inventoryIDBox").click();
			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			getxpath("//input[@value='Save']").click();
			Thread.sleep(3000);
			getxpath("//input[@value='Save & Close']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Edit inventory item*/
	@Test(enabled = true, priority = 7)	
	public void editInventoryItem() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys("TestInv1");
			getid("goSearchButtonID").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("multiplierId")));
			getid("multiplierId").sendKeys("1");
			getid("factoryCostId").clear();
			getid("factoryCostId").sendKeys("2.5");
			getxpath("//input[@value='Save']").click();
			Thread.sleep(3000);
			getxpath("//input[@value='Save & Close']").click();
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			getid("mainmenuInventoryPage").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Delete the inventory item*/
	@Test(enabled = true, priority = 8)	
	public void deleteInventoryItem() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys("TestInv1");
			getid("goSearchButtonID").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteInventory")));
			getid("deleteInventory").click();
			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			Thread.sleep(2000);
			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			Thread.sleep(3000);
			getid("resetbutton").click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Open any one inventory item by double clicking*/
	@Test(enabled = true, priority = 9)	
	public void openInventoryItem() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
			Actions openInv = new Actions(driver);
			openInv.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[5]"))).doubleClick().perform();
			Thread.sleep(3000);
			getxpath("//input[@value='Save & Close']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*View inventory items using next and previous buttons*/
	@Test(enabled = true, priority = 10)	
	public void viewInventoryItemsNextAndPrevious() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
			Actions openInv = new Actions(driver);
			openInv.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[5]"))).doubleClick().perform();
			Thread.sleep(3000);

			for(int next = 1; next<=9; next++)
			{
				getid("nextButton").click();
				Thread.sleep(3000);
			}

			for(int previous = 1; previous<=9; previous++)
			{
				getid("previousButton").click();
				Thread.sleep(3000);
			}

			Thread.sleep(3000);
			getxpath("//input[@value='Save & Close']").click();
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
