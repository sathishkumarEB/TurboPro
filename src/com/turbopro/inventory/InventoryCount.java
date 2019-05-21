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

public class InventoryCount extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Count;
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
		Count= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Count")).toString();
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
	public void inventoryCount() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateInventoryCount();
	}

	//Select warehouse
	@Test(enabled = true, priority = 2)	
	public void selectWarehouse() throws InterruptedException, Exception
	{
		try{
			getxpath(inventoryCountWarehouseDropdown).click();
			Thread.sleep(2000);
			Select warehouse = new Select(getxpath(inventoryCountWarehouseDropdown));
			Thread.sleep(2000);
			warehouse.selectByVisibleText("FT WORTH");
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Select warehouse
	@Test(enabled = true, priority = 3)	
	public void selectSort() throws InterruptedException, Exception
	{
		try{
			waitforxpath(inventoryCountSortDropdown);
			getxpath(inventoryCountSortDropdown).click();
			Thread.sleep(2000);
			Select sort = new Select(getxpath(inventoryCountSortDropdown));
			Thread.sleep(2000);
			sort.selectByVisibleText(" Product Description ");
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//Edit Counted value
	@Test(enabled = true, priority = 4)	
	public void countUpdate() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='1']/td[13]");
			getxpath("//*[@id='1']/td[13]").click();
			waitforid("1_Counted");
			getid("1_Counted").click();
			getid("1_Counted").clear();
			getid("1_Counted").sendKeys(Count + Keys.ENTER);
			waitforxpath(inventoryCountSaveButton);
			getxpath(inventoryCountSaveButton).click();		// click save
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//view PDF
	@Test(enabled = true, priority = 5)	
	public void viewPDF() throws InterruptedException, Exception
	{
		try{
			waitforxpath(inventoryCountPdfButton);
			getxpath(inventoryCountPdfButton).click();
			parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//view CSV report
	@Test(enabled = true, priority = 6)	
	public void viewCSV() throws InterruptedException, Exception
	{
		try{
			waitforxpath(inventoryCountCSVIcon);
			getxpath(inventoryCountCSVIcon).click();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	@AfterTest
	public void teardown() 
	{
//		driver.quit();
	}

}
