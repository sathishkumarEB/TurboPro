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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.turbopro.MethodsLibrary.Methods;

public class InventoryAdjustments extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Description, LineItem, Count, InventoryWarehouse1;
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
		InventoryWarehouse1 = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"InventoryWarehouse1")).toString();
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
		Thread.sleep(3000);
		navigateInventoryAdjustment();
	}

	/*Sort header in Transfer table*/
	@Test(enabled = true, priority = 2)	
	public void sortHeader() throws InterruptedException, Exception
	{
		try{
			getxpath(inventoryAdjustTransferDateHeader).click();
			waitforxpath("//*[@id='jqgh_chartsOfTransferInventoryGrid_transferDate']/span/span[2]");
			getxpath(inventoryAdjustTransferDateHeader).click();
			//			driver.findElement(By.xpath("//*[@id='jqgh_chartsOfTransferInventoryGrid_transferDate']/span/span[2]")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Create new adjustment*/
	@Test(enabled = true, priority = 3)	
	public void createNewAdjustment() throws InterruptedException, Exception
	{
		try{
			getxpath(inventoryAdjustDate).click();
			getxpath(inventoryAdjustDate).click();
			getlinktext("12").click();



			//			Actions act = new Actions(driver);
			//			act.moveToElement(getxpath(inventoryAdjustWarehouse)).click().build().perform();
			Select warehouse = new Select(getxpath(inventoryAdjustWarehouse));
			warehouse.selectByVisibleText(InventoryWarehouse1); //select the warehouse as "FT WORTH"
			Thread.sleep(1000);
			getxpath(inventoryAdjustReference).click();
			getxpath(inventoryAdjustReference).clear();
			getxpath(inventoryAdjustReference).sendKeys(Description);
			//			Actions act1 = new Actions(driver);
			//			act1.moveToElement(getxpath(inventoryAdjustReasonCode)).click().build().perform();
			Select reason = new Select(getxpath(inventoryAdjustReasonCode));
			reason.selectByVisibleText("Damaged");
			getxpath(inventoryAdjustAdd).click();
			waitforxpath(inventoryAdjustProductNo);
			getxpath(inventoryAdjustProductNo).click();
			getxpath(inventoryAdjustProductNo).clear();
			getxpath(inventoryAdjustProductNo).sendKeys(LineItem); //line item is "DMRR0604"
			driver.findElement(By.xpath("//a[text()='"+LineItem+"']")).click();
			getxpath(inventoryAdjustQty).click();
			getxpath(inventoryAdjustQty).clear();
			getxpath(inventoryAdjustQty).sendKeys(Count);
			getxpath(inventoryAdjustSave).click();
			getxpath(inventoryAdjustSaveAndClose).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Update adjustment details*/
	@Test(enabled = true, priority = 5)	
	public void updateAdjustment() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(2000);
			getxpath("//table[@id = 'chartsOfTransferInventoryGrid']/tbody/tr[3]/td[5] ").click();
			//			Actions act = new Actions(driver);
			//			act.moveToElement(driver.findElement(By.id("warehouseListID"))).click().build().perform();

			getxpath(inventoryAdjustWarehouse);
			Select warehouse = new Select(getxpath(inventoryAdjustWarehouse));
			warehouse.selectByVisibleText("DALLAS");
			getxpath(inventoryAdjustSaveAndClose).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*View adjustment details and clear it*/
	@Test(enabled = true, priority = 4)	
	public void viewDetails() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			getxpath("//table[@id = 'chartsOfTransferInventoryGrid']/tbody/tr[5]/td[5] ").click();
			getxpath(inventoryAdjustClear).click();
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
