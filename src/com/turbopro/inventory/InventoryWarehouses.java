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
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='2']/td[3]")));
		driver.findElement(By.xpath("//*[@id='2']/td[3]")).click();	

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='4']/td[3]")));
		driver.findElement(By.xpath("//*[@id='4']/td[3]")).click();	
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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouseDlg")));
		driver.findElement(By.id("warehouseDlg")).click();	// click Add button

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("adddescription")));
		driver.findElement(By.id("adddescription")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("adddescription")).sendKeys(Description);// Enter description
		Thread.sleep(3000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addcity")));
		driver.findElement(By.id("addcity")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addcity")).sendKeys(City);// Enter city

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addstate")));
		driver.findElement(By.id("addstate")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addstate")).sendKeys(State);// Enter city

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addasset")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("addasset"))).click().build().perform();
		Thread.sleep(2000);
		Select GLAccounts = new Select(driver.findElement(By.id("addasset")));
		Thread.sleep(2000);
		GLAccounts.selectByVisibleText("A/D MACHINERY AND EQUIPMENT");// Select asset

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addAdjustcog")));
		act.moveToElement(driver.findElement(By.id("addAdjustcog"))).click().build().perform();
		Thread.sleep(2000);
		Select adjustmentCOG = new Select(driver.findElement(By.id("addAdjustcog")));
		Thread.sleep(2000);
		adjustmentCOG.selectByValue("151");// Select COG

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addtaxTerritory")));
		act.moveToElement(driver.findElement(By.id("addtaxTerritory"))).click().build().perform();
		Thread.sleep(2000);
		Select pickupTaxTerritory = new Select(driver.findElement(By.id("addtaxTerritory")));
		Thread.sleep(2000);
		pickupTaxTerritory.selectByValue("127");// Select pickup tax territory

		driver.findElement(By.id("addemailPickUp")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addemailPickUp")).sendKeys(Email);// Enter Email

		driver.findElement(By.id("saveTermsButton")).click();// Click save& close
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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='5']/td[3]")));
		driver.findElement(By.xpath("//*[@id='5']/td[3]")).click();	// select an entry

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("companyName")));
		driver.findElement(By.id("companyName")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("companyName")).sendKeys(Company);// Enter company name

		driver.findElement(By.xpath("//*[@id='warehouseDetails']/fieldset/input[2]")).click();	// click save
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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='5']/td[3]")));
		driver.findElement(By.xpath("//*[@id='5']/td[3]")).click();	// select an entry

		driver.findElement(By.xpath("//*[@id='warehouseDetails']/fieldset/input[1]")).click();	// click delete
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();// click Yes button
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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='4']/td[3]")));
		driver.findElement(By.xpath("//*[@id='4']/td[3]")).click();	// select an entry

		driver.findElement(By.id("warehouseInactive")).click();	// select inactive check box

		driver.findElement(By.xpath("//*[@id='warehouseDetails']/fieldset/input[2]")).click();// click save
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
