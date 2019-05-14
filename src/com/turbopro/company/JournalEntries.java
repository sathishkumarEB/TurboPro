package com.turbopro.company;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class JournalEntries extends Methods {

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

	/*TP_CJE_0001 - Logging into the application and navigate to Vendors*/
	@Test(enabled = true, priority = 1)	
	public void journalEntries() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateJournalEntries();
	}

	/*TP_CJE_0002 - View existing Journal Entries one by one*/
	@Test(enabled = true, priority = 2)	
	public void viewJournalEntries() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='JournalsGrid']/tbody/tr[2]/td[2]")));

			if(getxpath("//table[@id='JournalsGrid']/tbody/tr[2]/td[2]").isDisplayed())
			{
				getxpath("//table[@id='JournalsGrid']/tbody/tr[2]/td[2]").click();
			}		
			Thread.sleep(2000);
			if(getxpath("//table[@id='JournalsGrid']/tbody/tr[3]/td[2]").isDisplayed())
			{
				getxpath("//table[@id='JournalsGrid']/tbody/tr[3]/td[2]").click();
			}		
			Thread.sleep(2000);
			if(getxpath("//table[@id='JournalsGrid']/tbody/tr[4]/td[2]").isDisplayed())
			{
				getxpath("//table[@id='JournalsGrid']/tbody/tr[4]/td[2]").click();
			}		
			Thread.sleep(2000);
			if(getxpath("//table[@id='JournalsGrid']/tbody/tr[5]/td[2]").isDisplayed())
			{
				getxpath("//table[@id='JournalsGrid']/tbody/tr[5]/td[2]").click();
			}		
			Thread.sleep(2000);
			if(getxpath("//table[@id='JournalsGrid']/tbody/tr[6]/td[2]").isDisplayed())
			{
				getxpath("//table[@id='JournalsGrid']/tbody/tr[6]/td[2]").click();
			}		
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CJE_0003 - Add a Journal Entry*/
	@Test(enabled = true, priority = 3)	
	public void addJournalEntries() throws InterruptedException, Exception
	{

		try{
			getxpath("//input[@onclick='addNewJournalEntry()']").click();
			getid("journalnewsave").click();
			if(getxpath("//span[text()='Add Account Details']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			getid("decriptionNameID").sendKeys("*TestJournalEntry");
			getid("DateOfJournal").click();
			getlinktext("3").click();
			getid("decriptionNameID").click();
			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("2000-00");
			getxpath("//a[text() = '2000-00-000 # ACCOUNTS PAYABLE']").click();

			getid("new_row_debit").click();
			getid("new_row_debit").clear();
			getid("new_row_debit").sendKeys("33"+ Keys.ENTER);


			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("3000-02");
			getxpath("//a[text() = '3000-02-000 # SALES - FT WORTH']").click();

			getid("new_row_credit").click();
			getid("new_row_credit").clear();
			getid("new_row_credit").sendKeys("33"+ Keys.ENTER);

			Thread.sleep(3000);
			getid("journalnewsave").click();
			Thread.sleep(7000);
			//		if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//		}
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalnewsave")));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CJE_0004 - Edit the journal entry*/
	@Test(enabled = true, priority = 4)	
	public void editJournalEntries() throws InterruptedException, Exception
	{
		try{
			getxpath("//table[@id='JournalsGrid']/tbody/tr[2]/td[2]").click();

			getid("journaleditbutton").click();

			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("2000-00");
			getxpath("//a[text() = '2000-00-000 # ACCOUNTS PAYABLE']").click();

			getid("new_row_debit").click();
			getid("new_row_debit").clear();
			getid("new_row_debit").sendKeys("1"+ Keys.ENTER);


			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("3000-02");
			getxpath("//a[text() = '3000-02-000 # SALES - FT WORTH']").click();

			getid("new_row_credit").click();
			getid("new_row_credit").clear();
			getid("new_row_credit").sendKeys("1"+ Keys.ENTER);

			Thread.sleep(3000);
			getid("journaleditsave").click();
			if(getxpath("//span[text()='Reason']").isDisplayed())
			{
				getid("reasontext").click();
				getid("reasontext").sendKeys("test");
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalnewsave")));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CJE_0005 - Delete the journal entry*/
	@Test(enabled = true, priority = 5)	
	public void deleteJournalEntries() throws InterruptedException, Exception
	{
		try{
			getxpath("//table[@id='JournalsGrid']/tbody/tr[2]/td[2]").click();
			getid("journaleditbutton").click();
			getxpath("//img[@onclick='deleteGridRow(4)']").click();
			getxpath("//img[@onclick='deleteGridRow(3)']").click();
			Thread.sleep(3000);
			getid("journaleditsave").click();
			if(getxpath("//span[text()='Reason']").isDisplayed())
			{
				getid("reasontext").click();
				getid("reasontext").sendKeys("test");
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalnewsave")));
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*Add a Journal entry with job number field of expense amount*/
	@Test(enabled = true, priority = 6)	
	public void addJournalEntriesWithJob() throws InterruptedException, Exception
	{

		try{
			getxpath("//input[@onclick='addNewJournalEntry()']").click();
			getid("journalnewsave").click();
			if(getxpath("//span[text()='Add Account Details']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			Thread.sleep(3000);
			getid("decriptionNameID").clear();
			getid("decriptionNameID").sendKeys("*TestJournalEntryWithJob");
			getid("DateOfJournal").click();
			getlinktext("3").click();
			getid("decriptionNameID").click();
			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("3100-01-000");
			getxpath("//a[contains (.,'3100-01-000')]").click();

			getid("new_row_jobNumber").click();
			getid("new_row_jobNumber").clear();
			getid("new_row_jobNumber").sendKeys("DMW181002");
			getxpath("//a[contains (.,'DMW181002')]").click();

			getid("new_row_debit").click();
			getid("new_row_debit").clear();
			getid("new_row_debit").sendKeys("33"+ Keys.ENTER);


			getid("new_row_coAccountNumber").click();
			getid("new_row_coAccountNumber").sendKeys("3000-02");
			getxpath("//a[contains (.,'3000-02')]").click();

			getid("new_row_credit").click();
			getid("new_row_credit").clear();
			getid("new_row_credit").sendKeys("33"+ Keys.ENTER);

			Thread.sleep(3000);
			getid("journalnewsave").click();
			Thread.sleep(7000);
			//		if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//		}
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalnewsave")));


			getlinktext("Home").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobsearch")));
			//Check the financial report of that job 

			getid("jobsearch").click();
			getid("jobsearch").clear();
			getid("jobsearch").sendKeys("DMW181002");
			getxpath("//input[@onclick=\"getJobs()\"]").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Financials")));
			getlinktext("Financials").click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Financial Report")));
			getlinktext("Financial Report").click();
			Thread.sleep(10000);

			//			parentWindow();



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

