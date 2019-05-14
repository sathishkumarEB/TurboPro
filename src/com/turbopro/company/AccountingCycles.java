package com.turbopro.company;



import org.openqa.selenium.By;
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

public class AccountingCycles extends Methods {

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

	/*TP_CAC_0001 - logging into the application and navigate to accounting cycles*/
	@Test(enabled = true, priority = 1)	
	public void accountingCycles() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateAccountingCycles();
	}

	/*TP_CAC_0002 - Attempt to create fiscal year and respond to popup*/
	@Test(enabled = true, priority = 2)	
	public void fiscalYearCreateAndRespondPopup() throws InterruptedException, Exception
	{
		try{
			
			waitforxpath("//input[@value='Add']");
			getxpath(("//input[@value='Add']")).click();

			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CAC_0003 - Attempt to close fiscal year and respond to popup*/
	@Test(enabled = true, priority = 3)	
	public void closeFiscalYearAndRespondPopup() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closefiscalyearid")));
			getid("closefiscalyearid").click();

			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CAC_0004 - Close the current period by selecting radio button and revert back*/
	@Test(enabled = true, priority = 4)	
	public void closeCurrentPeriod() throws InterruptedException, Exception
	{
		try{
			if(driver.findElement(By.id("rad_open1")).isEnabled())
			{
				getid("rad_close1").click();
				if(driver.findElement(By.id("ui-dialog-title-2")).isDisplayed())
				{
					getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
				}
				else
				{
					System.out.println("Popup not displayed");
				}
			Thread.sleep(3000);
			}
			getxpath("//input[@onclick='savecoFiscalPeriods()']").click();
			Thread.sleep(4000);

			if(driver.findElement(By.id("rad_close1")).isEnabled())
			{
				getid("rad_open1").click();
				if(driver.findElement(By.id("ui-dialog-title-3")).isDisplayed())
				{
					getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		//		driver.findElement(By.xpath("//input[@onclick='savecoFiscalPeriods()']")).click();
	}


	/*TP_CAC_0005 - Update description for accounting cycles*/
	@Test(enabled = false, priority = 5)	
	public void updateDescInAccountCycles() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("txa_Description")));
			getid("txa_Description").click();
			getid("txa_Description").clear();
			getid("txa_Description").sendKeys("testing accounting cycles");
			getxpath("//input[@onclick='savecoFiscalPeriods()']").click();
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
