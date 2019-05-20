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

public class InventoryWarehouses extends Methods{

	private String Url, UName, Password, Description, State, City, Company, Email;
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
		Email= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Email")).toString();
		State= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"State")).toString();
		City= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"City")).toString();
		Company= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Company")).toString();
		Description= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Description")).toString();
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


	//logging into the application and navigate to Inventory warehouse
	@Test(enabled = true, priority = 1)	
	public void inventoryWarehouse() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateInventoryWarehouses();
		Thread.sleep(2000);
	}

	// view warehouse details
	@Test(enabled = true, priority = 2)	
	public void viewwarehousedetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='2']/td[3]");
			getxpath("//*[@id='2']/td[3]").click();	

			waitforxpath("//*[@id='4']/td[3]");
			getxpath("//*[@id='4']/td[3]").click();	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	// Add new warehouse
	@Test(enabled = true, priority = 2)	
	public void addWarehouse() throws InterruptedException, Exception
	{
		try{
			waitforxpath(inventoryWarehouseAddButton);	
			getxpath(inventoryWarehouseAddButton).click();		// click Add button

			waitforxpath(inventoryWarehouseAddDescription);
			getxpath(inventoryWarehouseAddDescription).click();
			Thread.sleep(2000);
			getxpath(inventoryWarehouseAddDescription).sendKeys(Description);// Enter description
			Thread.sleep(3000);
			waitforxpath(inventoryWarehouseAddCity);
			getxpath(inventoryWarehouseAddCity).click();
			Thread.sleep(2000);
			getxpath(inventoryWarehouseAddCity).sendKeys(City);// Enter city
			waitforxpath(inventoryWarehouseAddState);
			getxpath(inventoryWarehouseAddState).click();
			Thread.sleep(2000);
			getxpath(inventoryWarehouseAddState).sendKeys(State);// Enter city
			waitforxpath(inventoryWarehouseAddAsset);
			Thread.sleep(2000);
			Select GLAccounts = new Select(getxpath(inventoryWarehouseAddAsset));
			Thread.sleep(2000);
			GLAccounts.selectByVisibleText("A/D MACHINERY AND EQUIPMENT");// Select asset

			waitforxpath(inventoryWarehouseAddAdjustCOG);
			Thread.sleep(2000);
			Select adjustmentCOG = new Select(getxpath(inventoryWarehouseAddAdjustCOG));
			Thread.sleep(2000);
			adjustmentCOG.selectByValue("151");// Select COG

			waitforxpath(inventoryWarehouseAddTaxTerritory);
			Thread.sleep(2000);
			Select pickupTaxTerritory = new Select(getxpath(inventoryWarehouseAddTaxTerritory));
			Thread.sleep(2000);
			pickupTaxTerritory.selectByValue("127");// Select pickup tax territory

			getxpath(inventoryWarehouseAddEmail).click();
			Thread.sleep(2000);
			getxpath(inventoryWarehouseAddEmail).sendKeys(Email);	// Enter Email
			getxpath(inventoryWarehouseAddSaveAndClose).click();	// Click save& close
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	// Edit warehouse details
	@Test(enabled = true, priority = 3)	
	public void editWarehouseDetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='5']/td[3]");
			getxpath("//*[@id='5']/td[3]").click();	// select an entry
			waitforxpath(inventoryWarehouseCompany);
			getxpath(inventoryWarehouseCompany).click();
			Thread.sleep(2000);
			getxpath(inventoryWarehouseCompany).sendKeys(Company);// Enter company name
			getxpath(inventoryWarehouseSaveButton).click(); 	// click save
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	// Delete warehouse
	@Test(enabled = true, priority = 4)	
	public void deleteWarehouse() throws InterruptedException, Exception
	{
		try{
			
			waitforxpath("//*[@id='5']/td[3]");
			getxpath("//*[@id='5']/td[3]").click();	// select an entry
			getxpath(inventoryWarehouseDeleteButton).click(); 	// click delete
			Thread.sleep(2000);
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// click Yes button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Inactivate warehouse
	@Test(enabled = true, priority = 5)	
	public void inactivateWarehouse() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='4']/td[3]");
			getxpath("//*[@id='4']/td[3]").click();	// select an entry
			getxpath(inventoryWarehouseInactiveCheckbox).click();	// select inactive check box
			getxpath(inventoryWarehouseSaveButton).click(); 	// click save
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
