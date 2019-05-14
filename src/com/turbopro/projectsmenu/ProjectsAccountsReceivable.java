package com.turbopro.projectsmenu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
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

public class ProjectsAccountsReceivable extends Methods {

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

	//TP_PAR_0001 - logging into the application and navigate to projects 
	@Test(enabled = true, priority =1)
	public void projects() throws InterruptedException, Exception 
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		getlinktext("Projects").click();
	}


	//view accounts receivable under projects 
	@Test(enabled = true, priority = 2)	
	public void viewAccountsReceivable() throws InterruptedException, Exception
	{
		if(getxpath("//td[@title='Accounts Receivable']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Accounts Receivable']")).doubleClick().perform();
		}
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closear")));
		getid("closear").click();
	}


	//TP_PAR_0003 - download csv of accounts receivable
	@Test(enabled = true, priority = 3)	
	public void downloadCsvForAR() throws InterruptedException, Exception
	{
		if(getxpath("//td[@title='Accounts Receivable']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Accounts Receivable']")).doubleClick().perform();
		}
		Thread.sleep(3000);
		getxpath("//td[@title='CSV']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closear")));
		getid("closear").click();
	}

	//TP_PAR_0002 - print the accounts receivable 
	@Test(enabled = false, priority = 4)	
	public void viewPdfOfAR() throws InterruptedException, Exception
	{
		if(getxpath("//td[@title='Accounts Receivable']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Accounts Receivable']")).doubleClick().perform();
		}
		Thread.sleep(5000);
		getxpath("//td[@title='Print']").click();
		Thread.sleep(15000);
		parentWindow();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closear")));
		getid("closear").click();

	}

	//TP_PAR_0005 - apply sorting in the headers of accounts receivable 
	@Test(enabled = true, priority = 5)	
	public void applySortInARHeaders() throws InterruptedException, Exception
	{
		if(getxpath("//td[@title='Accounts Receivable']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Accounts Receivable']")).doubleClick().perform();
		}
		Thread.sleep(5000);

		for(int i = 1; i<=2; i++)
		{
			getid("jqgh_AccountReceivableGrid_customerName").click();
			Thread.sleep(2000);
			getid("jqgh_AccountReceivableGrid_currentAmt").click();
			Thread.sleep(2000);
			getid("jqgh_AccountReceivableGrid_thirtyDays").click();
			Thread.sleep(2000);
			getid("jqgh_AccountReceivableGrid_sixtyDays").click();
			Thread.sleep(2000);
			getid("jqgh_AccountReceivableGrid_ninetyDays").click();
			Thread.sleep(2000);
			getid("jqgh_AccountReceivableGrid_totalDaysAmt").click();
		}

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closear")));
		getid("closear").click();

	}	


	//TP_PAR_0004 - view accounts receivable based on the selected date range
	@Test(enabled = true, priority = 6)	
	public void viewARbyDate() throws InterruptedException, Exception
	{
		if(getxpath("//td[@title='Accounts Receivable']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Accounts Receivable']")).doubleClick().perform();
		}
		Thread.sleep(5000);
		getid("asofardate").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("21")));
		getlinktext("21").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closear")));
		getid("closear").click();
	}


	@AfterTest
	public void teardown() 
	{
		driver.quit();
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

