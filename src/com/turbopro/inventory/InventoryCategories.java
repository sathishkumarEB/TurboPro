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

public class InventoryCategories extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Description;
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

	/*logging into the application and navigate to inventory transaction*/
	@Test(enabled = true, priority = 1)	
	public void inventoryCategory() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateInventoryCategories();// navigating to inventory categories
	}

	/*Add new category*/
	@Test(enabled = true, priority = 2)	
	public void addCategory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("categoryDescription")));
			driver.findElement(By.id("categoryDescription")).click();
			driver.findElement(By.id("categoryDescription")).clear();
			driver.findElement(By.id("categoryDescription")).sendKeys(Description);// Enter description

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")));
			driver.findElement(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")).click();// click save
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Select and view categories*/
	@Test(enabled = true, priority = 3)	
	public void viewCategoryDetails() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'Four']")));
			driver.findElement(By.xpath("//td[@title = 'Four']")).click();// select category Four
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'Eleven']")));
			driver.findElement(By.xpath("//td[@title = 'Eleven']")).click();//Select category Eleven
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Select and delete category*/
	@Test(enabled = true, priority = 4)	
	public void deleteCategory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title = 'Testing']")));
			driver.findElement(By.xpath("//td[@title = 'Testing']")).click();// select category Testing
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'deleteCategoryDetails()']")));
			driver.findElement(By.xpath("//input[@onclick = 'deleteCategoryDetails()']")).click();//Select delete
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Inactive a category*/
	@Test(enabled = true, priority = 5)	
	public void inactivateCategory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='4']/td[3]")));
			driver.findElement(By.xpath("//*[@id='4']/td[3]")).click();// select category Five
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("categoryInactive")));
			driver.findElement(By.id("categoryInactive")).click();//Select inactive check box

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")));
			driver.findElement(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")).click();//click save
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Activate a category*/
	@Test(enabled = true, priority = 6)	
	public void activateCategory() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='4']/td[3]")));
			driver.findElement(By.xpath("//*[@id='4']/td[3]")).click();// select category Five
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("categoryInactive")));
			driver.findElement(By.id("categoryInactive")).click();//Select inactive check box

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")));
			driver.findElement(By.xpath("//input[@onclick = 'SaveCategoryDetails()']")).click();//click save
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Sort Category table headers*/
	@Test(enabled = true, priority = 7)	
	public void sortHeaders() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqgh_inventoryCategoriesGrid_description")));
			driver.findElement(By.id("jqgh_inventoryCategoriesGrid_description")).click(); // select category header
			Thread.sleep(2000);

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='jqgh_inventoryCategoriesGrid_description']/span/span[2]")));
			driver.findElement(By.xpath("//*[@id='jqgh_inventoryCategoriesGrid_description']/span/span[2]")).click(); //Sort category header
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
