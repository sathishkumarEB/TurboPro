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
			driver.findElement(By.id("jqgh_chartsOfTransferInventoryGrid_transferDate")).click(); 
			driver.findElement(By.xpath("//*[@id='jqgh_chartsOfTransferInventoryGrid_transferDate']/span/span[2]")).click();
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
			driver.findElement(By.id("transferDateID")).click();
			driver.findElement(By.id("transferDateID")).click();
			driver.findElement(By.linkText("12")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.id("warehouseListID"))).click().build().perform();
			Select warehouse = new Select(driver.findElement(By.id("warehouseListID")));
			warehouse.selectByVisibleText(InventoryWarehouse1); //select the warehouse as "FT WORTH"
			Thread.sleep(1000);
			driver.findElement(By.id("referenceID")).click();
			driver.findElement(By.id("referenceID")).clear();
			driver.findElement(By.id("referenceID")).sendKeys(Description);
			Actions act1 = new Actions(driver);
			act1.moveToElement(driver.findElement(By.id("reasonCodeID"))).click().build().perform();
			Select reason = new Select(driver.findElement(By.id("reasonCodeID")));
			reason.selectByVisibleText("Damaged");
			driver.findElement(By.xpath("//*[@id='chartsOfTransferListGrid_iladd']/div")).click(); 
			driver.findElement(By.id("new_row_itemCode")).click();
			driver.findElement(By.id("new_row_itemCode")).clear();
			driver.findElement(By.id("new_row_itemCode")).sendKeys(LineItem); //line item is "DMRR0604"
			driver.findElement(By.xpath("//a[text()='"+LineItem+"']")).click();
			driver.findElement(By.id("new_row_quantityTransfered")).click();
			driver.findElement(By.id("new_row_quantityTransfered")).clear();
			driver.findElement(By.id("new_row_quantityTransfered")).sendKeys(Count);
			driver.findElement(By.id("chartsOfTransferListGrid_ilsave")).click(); 
			driver.findElement(By.id("saveIAButtonID")).click();
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
			driver.findElement(By.xpath("//table[@id = 'chartsOfTransferInventoryGrid']/tbody/tr[3]/td[5] ")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.id("warehouseListID"))).click().build().perform();
			Select warehouse = new Select(driver.findElement(By.id("warehouseListID")));
			warehouse.selectByVisibleText("DALLAS");
			driver.findElement(By.id("saveIAButtonID")).click();
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
			driver.findElement(By.xpath("//table[@id = 'chartsOfTransferInventoryGrid']/tbody/tr[5]/td[5] ")).click();
			driver.findElement(By.id("clearIAButtonID")).click();
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
