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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

import net.sourceforge.htmlunit.corejs.javascript.regexp.NativeRegExp;

public class ReportsJobOpportunityForecast extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";
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

	/*TP_JOF_0001 - logging into the application and navigate to job opportunity forecast*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateReportJobOpportunityForecast();
	}

	/*TP_JOF_0002 -  To View Job Opportunity Forecast Reports and export csv for month to date ending filter*/
	@Test(enabled = true, priority = 2)
	public void viewPdfOfJobOpportunityForecast() throws InterruptedException 
	{
		//To view the pdf report
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
			parentWindow();  
		}

		//To download the csv report 
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@value= 'Export']")).click(); 
		}
		Thread.sleep(5000);
	}


	/*TP_JOF_0003 -  To View Preview Reports and export csv for month to date ending filter*/
	@Test(enabled = true, priority = 3)
	public void viewJobOpportunityForecastReportWithMonthToDateFilter() throws InterruptedException 
	{
		//To view the pdf report for a salesrep
		if(driver.findElement(By.id("salesRepListReportId")).isDisplayed())
		{

			if(driver.findElement(By.id("salesRepListReportId")).isDisplayed())
			{
				Select salesrep = new Select(driver.findElement(By.id("salesRepListReportId")));
				salesrep.selectByVisibleText("Rita Kipp");
				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
				parentWindow();  
			}
		}

		//To view the pdf report for a specific job status
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			if(driver.findElement(By.id("jobStatusId")).isDisplayed())
			{
				Select JobStatus = new Select(driver.findElement(By.id("jobStatusId")));
				JobStatus.selectByVisibleText("Budget");
				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
				parentWindow();  
			}
		}


		//To view the pdf report for a specific quote type
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			if(driver.findElement(By.id("quoteTypeListId")).isDisplayed())
			{
				Select QuoteType = new Select(driver.findElement(By.id("quoteTypeListId")));
				QuoteType.selectByVisibleText("OWNER");
				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
				parentWindow();  
			}
		}
	}


	/*TP_JOF_0004 -  To View Job Opportunity Forecast Reports and export csv for single date filter*/
	@Test(enabled = true, priority = 4)
	public void viewJobOpportunityForecastReportWithSingleDateFilter() throws InterruptedException 
	{

		Select DateFilter = new Select(driver.findElement(By.id("jobForecastDateSelect")));
		DateFilter.selectByVisibleText("Single Date");

		driver.findElement(By.id("jobForecastSingleDatePicker")).click();

		if(driver.findElement(By.id("ui-datepicker-div")).isDisplayed())
		{
			driver.findElement(By.linkText("1")).click();
			driver.findElement(By.id("jobForecastDateSelect")).click();
		}

//		driver.findElement(By.id("jobForecastFieldSet")).click();
		
		//To view the pdf report
		if(driver.findElement(By.xpath("//input[@value= 'View']")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
			parentWindow();  
		}

		Thread.sleep(3000);

		this.viewJobOpportunityForecastReportWithMonthToDateFilter();
	}


	/*TP_JOF_0005 -  To View Job Opportunity Forecast Reports and export csv for date range filter*/
	@Test(enabled = true, priority = 5)
	public void viewJobOpportunityForecastReportWithDateRangeFilter() throws InterruptedException 
	{

		Select DateFilter = new Select(driver.findElement(By.id("jobForecastDateSelect")));
		DateFilter.selectByVisibleText("Date Range");

		if(driver.findElement(By.id("jobForecastFromDate")).isDisplayed())
		{
			driver.findElement(By.id("jobForecastFromDate")).click();
			driver.findElement(By.id("jobForecastFromDate")).clear();
			driver.findElement(By.id("jobForecastFromDate")).sendKeys("01/30/2019");

			driver.findElement(By.id("jobForecastToDate")).click();
			driver.findElement(By.id("jobForecastToDate")).clear();
			driver.findElement(By.id("jobForecastToDate")).sendKeys("04/30/2019");

			driver.findElement(By.id("jobForecastFieldSet")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
			parentWindow();
		}

		//To download the csv report 
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@value= 'Export']")).click(); 
		}
		Thread.sleep(5000);

		this.viewJobOpportunityForecastReportWithMonthToDateFilter();

	}


	/*TP_JOF_0006 -  To View Job Opportunity Forecast Reports and export csv for Year to Date ending filter*/
	@Test(enabled = true, priority = 6)
	public void viewJobOpportunityForecastReportWithYearToDateEndingFilter() throws InterruptedException 
	{

		Select DateFilter = new Select(driver.findElement(By.id("jobForecastDateSelect")));
		DateFilter.selectByVisibleText("Year to Date Ending");

		//To view the csv report with year to date filter
		if(driver.findElement(By.id("jobForecastYearToDateEnding")).isDisplayed())
		{
//			Select YearToDate = new Select(driver.findElement(By.id("jobForecastYearToDateEnding")));
//			YearToDate.deselectByVisibleText("04/30/2019");

			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value= 'View']")).click(); 
			parentWindow();
		}

		//To download the csv report 
		if(driver.findElement(By.id("ui-dialog-title-jobForecastDiv")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@value= 'Export']")).click(); 
		}
		Thread.sleep(5000);

		this.viewJobOpportunityForecastReportWithMonthToDateFilter();

	}



	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
	}

}

