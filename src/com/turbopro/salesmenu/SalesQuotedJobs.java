package com.turbopro.salesmenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesQuotedJobs extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";
	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;


	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
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

	//	/*TP_SQJ_0001 - logging into the application*/
	//	@Test(enabled = true, priority = 1)	
	//	public void login() throws InterruptedException, Exception
	//	{
	////		loggingIn(Url, UName, Password);
	//	}

	/*TP_SQJ_0001 -  Login to application and View Quoted Jobs*/
	@Test(enabled = true, priority = 1)
	public void viewQuotedJobs() throws InterruptedException, Exception 
	{
		loggingIn(Url, UName, Password);
		navigateSales();
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getid("myQuotedCloseIcon").click();
	}

	/*TP_SQJ_0002 -  View Jobs from Quoted Jobs*/
	@Test(enabled = true, priority = 2)
	public void accessJobsFromQuotedJobs() throws InterruptedException 
	{
		navigateSales();
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getid("salesQuoteSingleDatePicker").click();
		getid("salesQuoteSingleDatePicker").clear();
		getid("salesQuoteSingleDatePicker").sendKeys("05/15/2017");
		getid("bidder").click();

		Actions action = new Actions(driver);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='quotedJobGridDialog']/tbody/tr[@id='1']/td[4]")));
		action.moveToElement(getxpath("//table[@id='quotedJobGridDialog']/tbody/tr[@id='1']/td[4]")).doubleClick().perform();
	}

	/*TP_SQJ_0003 - Sort Quoted Jobs - Clicking date column*/
	@Test(enabled = true, priority = 3)
	public void sortQuotedJobs() throws InterruptedException 
	{
		navigateSales();
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getid("salesQuoteSingleDatePicker").click();
		getid("salesQuoteSingleDatePicker").clear();
		getid("salesQuoteSingleDatePicker").sendKeys("05/15/2017");
		getid("bidder").click();
		getid("jqgh_quotedJobGridDialog_bidDate").click();
		getid("myQuotedCloseIcon").click();
	}

	/* TP_SQJ_0004 - Select and reselect columns of Quoted jobs*/
	@Test(enabled = true, priority = 4)
	public void viewColumnsInQuoteJobs() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getid("salesQuoteSingleDatePicker").click();
		getid("salesQuoteSingleDatePicker").clear();
		getid("salesQuoteSingleDatePicker").sendKeys("05/15/2017");
		getid("bidder").click();

		for (int i=1; i<=2; i++)
		{
			getxpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[4]/input").click();
			getxpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[5]/input").click();
			getxpath("//*[@id='displayQuotedJob']/div[2]/table/tbody/tr/td[6]/input").click();
		}
		getid("myQuotedCloseIcon").click();
	}

	/* TP_SQJ_0005 - View Quoted Jobs PDF*/
	@Test(enabled = true, priority = 5)
	public void quotedJobsPrint() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getid("salesQuoteSingleDatePicker").click();
		getid("salesQuoteSingleDatePicker").clear();
		getid("salesQuoteSingleDatePicker").sendKeys("05/15/2017");
		getid("bidder").click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getxpath("//input[@alt='View PDF']").click();
		parentWindow();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myQuotedCloseIcon")));
		getid("myQuotedCloseIcon").click();
	}

	/* TP_SQJ_0006 - Download CSV report for Quoted Jobs*/
	@Test(enabled = true, priority = 6)
	public void quotedJobsExport() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();
		getid("salesQuoteSingleDatePicker").click();
		getid("salesQuoteSingleDatePicker").clear();
		getid("salesQuoteSingleDatePicker").sendKeys("05/15/2017");
		getid("bidder").click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getxpath("//input[@title='Export Sales Tax PDF']").click();
		Thread.sleep(5000);

		//		parentWindow();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myQuotedCloseIcon")));
		getid("myQuotedCloseIcon").click();
	}

	/*TP_SQJ_007 - View quoted jobs by applying date range filter*/
	@Test(enabled = true, priority = 7)
	public void quotedJobsDateRangeFilter() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();

		Select dateRange = new Select(getid("salesQuoteDateSelect"));
		dateRange.selectByVisibleText("Date Range");

		getid("salesQuoteFromDate").click();
		getid("salesQuoteFromDate").clear();
		getid("salesQuoteFromDate").sendKeys("05/15/2017");

		getid("salesQuoteToDate").click();
		getid("salesQuoteToDate").clear();
		getid("salesQuoteToDate").sendKeys("05/15/2018");
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getid("myQuotedCloseIcon").click();

	}


	/*TP_SQJ_008 - View the quoted jobs based on month to date filter*/
	@Test(enabled = true, priority = 8)
	public void quotedJobsMonthToDateFilter() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();

		Select monthtodate = new Select(getid("salesQuoteDateSelect"));
		monthtodate.selectByVisibleText("Month To Date Ending");

		Select monthdate = new Select(getid("salesQuoteMonthToDateEnding"));
		monthdate.selectByVisibleText("05/31/2017");
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getid("bidder").click();
		getid("myQuotedCloseIcon").click();
	}

	/*TP_SQJ_009 - View the quoted jobs by year to date filter*/
	@Test(enabled = true, priority = 9)
	public void quotedJobsYearToDateFilter() throws InterruptedException 
	{
		navigateSales();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='callQuotedJobDialog();']")));
		getxpath("//input[@onclick='callQuotedJobDialog();']").click();

		Select yeartodate = new Select(getid("salesQuoteDateSelect"));
		yeartodate.selectByVisibleText("Year To Date Ending");

		Select yeardate = new Select(getid("salesQuoteYearToDateEnding"));
		yeardate.selectByVisibleText("05/31/2017");

		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_quotedJobGridDialog")));
		getid("bidder").click();
		getid("myQuotedCloseIcon").click();
	}





	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}
