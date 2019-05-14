package com.turbopro.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class GeneralLedger extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
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

	/*TP_CGL_0001 - Logging into the application and navigate to General Ledger*/
	@Test(enabled = true, priority = 1)	
	public void generalLedger() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateGeneralLedger();
	}

	/*TP_CGL_0002 - View account details based on period and year*/
	@Test(enabled = true, priority = 2)	
	public void viewAccountsByPeriodAndYear() throws InterruptedException, Exception
	{
		try{
			getid("periodAndYearID").click();
			Select period = new Select (driver.findElement(By.id("periodsId")));
			period.selectByVisibleText("1");
			Select periodto = new Select (driver.findElement(By.id("periodsToID")));
			periodto.selectByVisibleText("12");
			Select year = new Select (driver.findElement(By.id("yearID")));
			year.selectByVisibleText("2017");
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("jqg_chartsOfAccountsGridGLledger_11")));
			getid("jqg_chartsOfAccountsGridGLledger_11").click();
			getid("saveDDButton").click();
			if(driver.findElement(By.id("load_drillDownGrid")).isDisplayed())
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGL_0003 - View account details based on most recent period*/
	@Test(enabled = true, priority = 3)	
	public void viewAccountsByMostRecentPeriod() throws InterruptedException, Exception
	{
		try{
			getid("mostRecentPeriodsID").click();
			getid("saveDDButton").click();
			if(driver.findElement(By.id("load_drillDownGrid")).isDisplayed())
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGL_0004 - View account details based on fiscal year to date*/
	@Test(enabled = true, priority = 4)	
	public void viewAccountsByFiscalYearToDate() throws InterruptedException, Exception
	{
		try{
			getid("fiscalYearToDate").click();
			getid("saveDDButton").click();
			if(driver.findElement(By.id("load_drillDownGrid")).isDisplayed())
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CGL_0005 - View account details based on fiscal year to date*/
	@Test(enabled = true, priority = 5)	
	public void exportCSV() throws InterruptedException, Exception
	{
		try{
			getid("mostRecentPeriodsID").click();
			getid("saveDDButton").click();
			if(driver.findElement(By.id("load_drillDownGrid")).isDisplayed())
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
			}
			getid("exportGenrealLedgerCSVButton").click();
			Thread.sleep(4000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGL_0006 - Drill down to the account details */
	@Test(enabled = true, priority = 6)	
	public void drillIntoAccountDetails() throws InterruptedException, Exception
	{
		try{
			getid("saveDDButton").click();
			if(driver.findElement(By.id("load_drillDownGrid")).isDisplayed())
			{
				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
			}
			Actions drilldown = new Actions(driver);
			drilldown.moveToElement(driver.findElement(By.xpath("//table[@id='drillDownGrid']/tbody/tr[2]/td[4]"))).doubleClick().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='journalBalanceGrid']/tbody/tr[2]/td[4]")));
			Actions drilldownjournal = new Actions(driver);
			drilldownjournal.moveToElement(driver.findElement(By.xpath("//table[@id='journalBalanceGrid']/tbody/tr[2]/td[4]"))).doubleClick().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='journalDetailGrid']/tbody/tr[2]/td[4]")));
			Actions drilljournaldetail = new Actions(driver);
			drilljournaldetail.moveToElement(driver.findElement(By.xpath("//table[@id='journalDetailGrid']/tbody/tr[2]/td[4]"))).doubleClick().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='transactionDetailGrid']/tbody/tr[2]/td[4]")));
			driver.findElement(By.linkText("Account")).click();
		}
		catch (Exception e)
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
