package com.turbopro.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class JobRelease extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername, Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email, Vendorname;
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
		Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
		Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Dropshipmanufacturer= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DropshipManufacturer")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		SO_Productname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		Email= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Email")).toString();
		Vendorname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();

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

	//logging into the application, create new job and navigate to quotes tab
	@Test(enabled = true, priority = 1)	
	public void navigateReleaseInNewJob() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername);	//book a job with customer name
		
	}

	//update release notes
	@Test(enabled = true, priority = 2)	
	public void updateReleaseNotes() throws InterruptedException, Exception
	{
		driver.findElement(By.id("jobreleasetab")).click();
		
		driver.findElement(By.id("releaseNote")).click();
		driver.findElement(By.id("releaseNote")).clear();
		driver.findElement(By.id("releaseNote")).sendKeys("Testing the release");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//input[@onclick='release_save()']")));
		driver.findElement(By.xpath("//input[@onclick='release_save()']")).click();
		
	}
	
	/*Update tax territory in the created job*/
	@Test(enabled = true, priority = 3)	
	public void updateTaxTerritory() throws InterruptedException, Exception
	{
		driver.findElement(By.linkText("Main")).click();

		driver.findElement(By.id("jobMain_TaxTerritory")).click();
		driver.findElement(By.id("jobMain_TaxTerritory")).clear();
		driver.findElement(By.id("jobMain_TaxTerritory")).sendKeys("Houston");

		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains (.,'Houston')]")));
		driver.findElement(By.xpath("//a[contains (.,'Houston')]")).click();

		driver.findElement(By.id("mainsave")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Releases")).click();
		
	}
	

	/*Change order feature testing*/
	@Test(enabled = true, priority = 4)	
	public void addChangeOrder() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//input[@onclick='changeorderdialog()']")).click();
		
		if(driver.findElement(By.id("changeorder")).isDisplayed())
		{
			driver.findElement(By.xpath("//*[@id=\"changeOrderGrid_iladd\"]/div")).click();
			if(driver.findElement(By.id("new_row_customerPonumber")).isDisplayed())
			{
				driver.findElement(By.id("new_row_customerPonumber")).click();
				driver.findElement(By.id("new_row_customerPonumber")).sendKeys("#12");
				
				driver.findElement(By.id("new_row_changeReason")).click();
				driver.findElement(By.id("new_row_changeReason")).sendKeys("test change reason");
				
				driver.findElement(By.id("new_row_changeAmount")).click();
				driver.findElement(By.id("new_row_changeAmount")).clear();
				driver.findElement(By.id("new_row_changeAmount")).sendKeys("1000");
				
				driver.findElement(By.id("new_row_changeCost")).click();
				driver.findElement(By.id("new_row_changeCost")).clear();
				driver.findElement(By.id("new_row_changeCost")).sendKeys("1200");
				
				driver.findElement(By.xpath("//*[@id=\"changeOrderGrid_ilsave\"]/div")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//body/div[18]/div[1]/a/span")).click();
			}
		}
	}
	
	/*Edit the existing Change Order*/
	@Test(enabled = true, priority = 5)	
	public void editChangeOrder() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//input[@onclick='changeorderdialog()']")).click();
		
		if(driver.findElement(By.id("changeorder")).isDisplayed())
		{
			driver.findElement(By.xpath("//td[@title='#12']")).click();
			driver.findElement(By.xpath("//td[@id='ChangeOrderGridPager_left']/table/tbody/tr/td[2]/div")).click();
			
			if(driver.findElement(By.id("1_changdate")).isDisplayed())
			{
				driver.findElement(By.id("1_customerPonumber")).click();
				driver.findElement(By.id("1_customerPonumber")).clear();
				driver.findElement(By.id("1_customerPonumber")).sendKeys("#121");
				
				driver.findElement(By.id("1_changeReason")).click();
				driver.findElement(By.id("1_changeReason")).clear();
				driver.findElement(By.id("1_changeReason")).sendKeys("edited change reason");
				
				driver.findElement(By.id("1_changeAmount")).click();
				driver.findElement(By.id("1_changeAmount")).clear();
				driver.findElement(By.id("1_changeAmount")).sendKeys("1001");
				
				driver.findElement(By.id("1_changeCost")).click();
				driver.findElement(By.id("1_changeCost")).clear();
				driver.findElement(By.id("1_changeCost")).sendKeys("1201");
				
				driver.findElement(By.xpath("//*[@id=\"ChangeOrderGridPager_left\"]/table/tbody/tr/td[3]/div")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//body/div[18]/div[1]/a/span")).click();
			}
		}
	}

	
	/*Delete the existing Change Order*/
	@Test(enabled = true, priority = 6)	
	public void deleteChangeOrder() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//input[@onclick='changeorderdialog()']")).click();
		
		if(driver.findElement(By.id("changeorder")).isDisplayed())
		{
			driver.findElement(By.xpath("//td[@title='#121']")).click();
			driver.findElement(By.xpath("//td[@id='ChangeOrderGridPager_left']/table/tbody/tr/td[2]/div")).click();
			if(driver.findElement(By.id("1_changdate")).isDisplayed())
			{
				driver.findElement(By.id("canDeleteJomID_1")).click();
				driver.findElement(By.xpath("//td[@id='ChangeOrderGridPager_left']/table/tbody/tr/td[3]/div")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//body/div[18]/div[1]/a/span")).click();
			}
		}
	}
	
	

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}
