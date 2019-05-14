package com.turbopro.reports;

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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;


public class BookingProfit extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	private String Url, UName, Password, CustomerName, SalesRep;
	FileInputStream fis;
	HSSFWorkbook srcBook ;


	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"password")).toString();
		CustomerName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		SalesRep= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"SalesRep")).toString();
	}

	/*To access Excel sheet*/
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

	/*TP_RBP_0001 - logging into the application and navigate to Reports - Booking Profit*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateReportBookingProfit();
	}

	/*Select single date and apply filter*/
	@Test(enabled = true, priority = 2)	
	public void singleDate() throws InterruptedException, Exception
	{
		driver.findElement(By.id("salesTaxSingleDatePicker")).click();
		driver.findElement(By.id("salesTaxSingleDatePicker")).clear();
		driver.findElement(By.id("salesTaxSingleDatePicker")).sendKeys("04/27/2018");
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		Thread.sleep(4000);
		driver.findElement(By.id("resetBooking")).click();
		
	}
	
	
	/*Select date range and apply filter*/
	@Test(enabled = true, priority = 3)	
	public void dateRange() throws InterruptedException, Exception
	{
		Select DateFilter = new Select(driver.findElement(By.id("salesTaxDateSelect")));
		DateFilter.selectByVisibleText(" Date Range ");
		driver.findElement(By.id("salesTaxFromDate")).click();
		driver.findElement(By.id("salesTaxFromDate")).clear();
		driver.findElement(By.id("salesTaxFromDate")).sendKeys("01/01/2018");
		Thread.sleep(3000);
		driver.findElement(By.id("salesTaxToDate")).click();
		driver.findElement(By.id("salesTaxToDate")).clear();
		driver.findElement(By.id("salesTaxToDate")).sendKeys("04/30/2018");
		
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		Thread.sleep(4000);
		driver.findElement(By.id("resetBooking")).click();
		
	}
	
	/*Select month to date ending and apply filter*/
	@Test(enabled = true, priority = 4)	
	public void monthToDate() throws InterruptedException, Exception
	{
		Select DateFilter = new Select(driver.findElement(By.id("salesTaxDateSelect")));
		DateFilter.selectByVisibleText(" Month To Date Ending ");
		
		Select MonthToDateFilter = new Select(driver.findElement(By.id("salesTaxMonthToDateEnding")));
		MonthToDateFilter.selectByVisibleText("12/31/2017");
		
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		Thread.sleep(4000);
		driver.findElement(By.id("resetBooking")).click();
		
		
	}
	
	
	/*Select month to date ending and apply filter and select salesrep*/
	@Test(enabled = true, priority = 5)	
	public void monthToDateWithSalesrep() throws InterruptedException, Exception
	{
		Select DateFilter = new Select(driver.findElement(By.id("salesTaxDateSelect")));
		DateFilter.selectByVisibleText(" Month To Date Ending ");
		Select MonthToDateFilter = new Select(driver.findElement(By.id("salesTaxMonthToDateEnding")));
		MonthToDateFilter.selectByVisibleText("12/31/2017");
		Thread.sleep(2000);
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		Select SalesRep = new Select(driver.findElement(By.id("salesRepListId")));
		SalesRep.selectByVisibleText("John Henderson");
		Thread.sleep(3000);
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		Thread.sleep(4000);
		driver.findElement(By.id("resetBooking")).click();
		
	}

	/*Select year to date ending filter*/
	@Test(enabled = true, priority = 6)
	public void yearToDate() throws InterruptedException, Exception
	{
		Select DateFilter = new Select(driver.findElement(By.id("salesTaxDateSelect")));
		DateFilter.selectByVisibleText(" Year To Date Ending ");
		
		Select YearToDateFilter = new Select(driver.findElement(By.id("salesTaxYearToDateEnding")));
		YearToDateFilter.selectByVisibleText("12/31/2017");
		
		driver.findElement(By.id("searchBooking")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_drillDownGrid1")));
		
		driver.findElement(By.xpath("//input[@value='Export']"));
		
		
	}
	
	
	
	
	
	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

