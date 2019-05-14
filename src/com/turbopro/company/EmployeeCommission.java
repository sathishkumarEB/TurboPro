package com.turbopro.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;
import com.turbopro.requirements.RID609;
import com.turbopro.vendors.VendorInvoicesAndBills;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class EmployeeCommission extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String baseUrl, Url, UName, Password;
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

	//TC_EC_0001 - logging into the application, navigate to employee commissions
	@Test(enabled = true, priority = 1)	
	public void employeeCommission() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateEmployeeCommission();
	}


	//TC_EC_0002 - view commissions for different periods 
	@Test(enabled = true, priority = 2)	
	public void viewCommissionForDiffPeriod() throws InterruptedException, Exception
	{
		try{
			getid("endperioddate").click();
			Select PeriodEnding = new Select(driver.findElement(By.id("endperioddate")));
			PeriodEnding.selectByVisibleText("11/30/2017");
			waitforxpath("//table[@id='commissionsGridList']/tbody/tr[@id='1']/td[3]");

			getid("endperioddate").click();
			Select PeriodEnding1 = new Select(driver.findElement(By.id("endperioddate")));
			PeriodEnding1.selectByVisibleText("10/31/2017");
			waitforxpath("//table[@id='commissionsGridList']/tbody/tr[@id='1']/td[3]");

			getid("endperioddate").click();
			Select PeriodEnding2 = new Select(driver.findElement(By.id("endperioddate")));
			PeriodEnding2.selectByVisibleText("12/31/2017");
			waitforxpath("//table[@id='commissionsGridList']/tbody/tr[@id='1']/td[3]");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_EC_0003 - print the commission list 
	@Test(enabled = true, priority = 3)	
	public void printCommissionList() throws InterruptedException, Exception
	{
		try{
			getid("contactEmailID").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_EC_0004 - attempt to create new period and click cancel
	@Test(enabled = true, priority = 4)	
	public void openAndCloseNewPeriod() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='New Period']")));
			getxpath("//input[@value='New Period']").click();

			if(driver.findElement(By.id("ui-dialog-title-NewPeriodDialog")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_EC_0005 - attempt to recalculate and click cancel
	@Test(enabled = true, priority = 5)	
	public void cancelRecalculate() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Recalculate']")));
			getxpath("//input[@value='Recalculate']").click();

			if(getid("ui-dialog-title-ReCalculateDialog").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[3]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	//TC_EC_0006 - attempt to reverse period and click cancel
	@Test(enabled = true, priority = 6)	
	public void cancelReversePeriod() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Reverse Period']")));
			getxpath("//input[@value='Reverse Period']").click();

			if(driver.findElement(By.id("ui-dialog-title-ReversePeriodDialog")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_EC_0007 - print employee commissions for all 
	@Test(enabled = true, priority = 6)	
	public void printAllEmployeeCommission() throws InterruptedException, Exception
	{
		try{
			getid("printAllStatements").click();
			Thread.sleep(4000);
			parentWindow();
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}
