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

public class ChartOfAccounts extends Methods {

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

	/*TP_CCOA_0001 - Logging into the application and navigate to chart of accounts*/
	@Test(enabled = true, priority = 1)	
	public void chartOfAccounts() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateChartOfAccounts();
	}

	/*TP_CCOA_0002 - Select different accounts under chart of accounts*/
	@Test(enabled = true, priority = 2)	
	public void viewChartOfAccounts() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='1']/td[2]");
			getxpath("//*[@id='1']/td[2]").click();
			getxpath("//*[@id='2']/td[2]").click();
			getxpath("//*[@id='3']/td[2]").click();
			getxpath("//*[@id='4']/td[2]").click();
			getxpath("//*[@id='5']/td[2]").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CCOA_0003 - View additional tab in chart of accounts*/
	@Test(enabled = true, priority = 3)	
	public void viewAdditionalAccounts() throws InterruptedException, Exception
	{
		try{
			getlinktext(("Additional")).click();
			getxpath("//input[@onclick='saveAdditionalData()']").click();
			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CCOA_0004 - Add an an account in chart of accounts*/
	@Test(enabled = true, priority = 4)	
	public void addAccount() throws InterruptedException, Exception
	{
		try{
			getlinktext("General").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addChartlist")));
			getid("addChartlist").click();
			getid("numberNameID1").sendKeys("1111");
			getid("numberNameID2").sendKeys("01");
			getid("numberNameID3").sendKeys("000");
			getid("decriptionNameID").sendKeys("testchartofaccounts");
			Select type = new Select (driver.findElement(By.id("typeAccount")));
			type.selectByVisibleText("Asset");
			getxpath("//input[@onclick='saveChartsOfAccounts()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CCOA_0005 - Delete chart of accounts*/
	@Test(enabled = true, priority = 5)	
	public void deleteAccount() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='1000-01-000']")));
			getxpath("//td[@title='1000-01-000']").click();
			getid("deleteChartOfAccountID").click();
			if(driver.findElement(By.xpath("//span[text()='Confirm Delete']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CCOA_0006 - Searching a specific account using search*/
	@Test(enabled = true, priority = 6)	
	public void searchSpecificAccount() throws InterruptedException, Exception
	{
		try{
			getid("searchJob").click();
			getid("searchJob").sendKeys("1000-00");
			getxpath("//body/ul[13]/li/a").click();
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

