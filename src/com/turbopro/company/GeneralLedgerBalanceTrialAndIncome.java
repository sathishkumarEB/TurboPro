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

public class GeneralLedgerBalanceTrialAndIncome extends Methods {

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

	/*TP_CGLS_0001 - Logging into the application and navigate to Balance sheet*/
	@Test(enabled = true, priority = 1)	
	public void balanceSheet() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateBalanceSheet();
	}


	/*TP_CGLS_0002 - View the balance statement without account number*/
	@Test(enabled = true, priority = 2)	
	public void viewBalanceSheetWithoutAccNumber() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@value='View']");
			getxpath("//input[@value='View']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0003 - View the balance statement with account number*/
	@Test(enabled = true, priority = 3)	
	public void viewBalanceSheetWithAccNumber() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@value='View']");
			getid("showAccount").click();
			getxpath("//input[@value='View']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0004 - Navigate to trial balance*/
	@Test(enabled = true, priority = 4)	
	public void trialBalance() throws InterruptedException, Exception
	{
		try{
			navigateTrialBalance();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0004 - View trial balance by showing the Current period*/
	@Test(enabled = true, priority = 5)	
	public void viewTrialBalanceWithCurrentPeriodPrint() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@value='View']");
			if(getxpath("//input[@value='No']").isSelected())
			{
				getid("showCurrentPeriodyes").click();
			}
			getxpath("//input[@value='View']").click();
			waitforxpath("//*[@id='1']/td[3]");
			getxpath("//input[@value='Print']").click();
			parentWindow();
			driver.navigate().back();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CGLS_0005 - View trial balance by without showing the Current period*/
	@Test(enabled = true, priority = 6)	
	public void viewTrialBalanceWithoutCurrentPeriodPrint() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='View']")));
			if(getid("showCurrentPeriodyes").isSelected())
			{
				getxpath("//input[@value='No']").click();
			}
			getxpath("//input[@value='View']").click();
			waitforxpath("//*[@id='1']/td[3]");
			getxpath("//input[@value='Print']").click();
			parentWindow();
			driver.navigate().back();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CGLS_0006 - Navigate to income statement*/
	@Test(enabled = true, priority = 7)	
	public void incomeStatement() throws InterruptedException, Exception
	{
		try{
			navigateIncomeStatement();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0007 - View income statement by showing the Account number*/
	@Test(enabled = true, priority = 8)	
	public void viewIncomeStatementWithAccNumber() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@value='View']");
			if(getxpath("//input[@value='No']").isSelected())
			{
				getxpath("//input[@value='Yes']").click();
			}
			getxpath("//input[@value='View']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0008 - View income statement by not showing the Account number*/
	@Test(enabled = true, priority = 9)	
	public void viewIncomeStatementWithoutAccNumber() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@value='View']");
			if(getxpath("//input[@value='Yes']").isSelected())
			{
				getxpath("//input[@value='No']").click();
			}
			getxpath("//input[@value='View']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0009 - View income statement with division*/
	@Test(enabled = true, priority = 10)	
	public void viewIncomeStatementWithDivision() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			waitforxpath("//input[@value='View']");

			Select division = new Select (getid("divisionSelect"));
			division.selectByVisibleText("Bartos Air Solutions");
			if(getxpath("//input[@value='No']").isSelected())
			{
				getxpath("//input[@value='Yes']").click();
			}
			getxpath("//input[@value='View']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CGLS_0010 - Download csv report of income statement*/
	@Test(enabled = true, priority = 11)	
	public void downloadCsvOfIncomeStatement() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//img[@title='Export CSV']");
			getxpath("//img[@title='Export CSV']").click();
		}
		catch (Exception e)
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

