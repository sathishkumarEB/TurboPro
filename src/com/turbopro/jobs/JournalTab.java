package com.turbopro.jobs;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class JournalTab extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, JobName, VendorName, LineItem, Quantity, Freight, SalesRep, TaxTerritory, CustomerName, Notes, Memonumber, Allocated, Pro, Reason, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
		//		baseUrl=srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		JobName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		SalesRep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SalesRep")).toString();
		TaxTerritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		CustomerName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Memonumber= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"MemoNumber")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		VendorName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();
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
			return patchColumn
					;
	}


	//TP_JJT_0001 - logging into the application and navigate to vendor purchase orders
	@Test(enabled = true, priority = 1)	
	public void journalTab() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		createNewJob(JobName, SalesRep, TaxTerritory);
		//		changeStatusToBooked(CustomerName);
		getid("jobTabJournalHeader").click();	
	}

	//TP_JJT_0002 - To add journal entry
	@Test(enabled = true, priority = 2)	
	public void addJournalEntry() throws InterruptedException, Exception
	{
		try{
			getid("add_journal").click();// click add icon
			getid("journalNote").click();
			Thread.sleep(2000);
			getid("journalNote").sendKeys(Notes);// Enter notes
			Actions act = new Actions(driver);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalStatus")));
			act.moveToElement(getid("journalStatus")).click().build().perform();
			Thread.sleep(2000);
			Select journalstatus = new Select(getid("journalStatus"));
			Thread.sleep(2000);
			journalstatus.selectByVisibleText("Open");// Select status
			getid("sData").click();// click submit
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TP_JJT_0003 - To edit status of an existing entry
	@Test(enabled = true, priority = 3)	
	public void editJournalEntry() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '1']/td[2]")));
			getxpath("//*[@id= '1']/td[2]").click();// select existing entry
			getid("edit_journal").click();// click edit icon
			Actions act = new Actions(driver);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("journalStatus")));
			act.moveToElement(getid("journalStatus")).click().build().perform();
			Thread.sleep(2000);
			Select journalstatus = new Select(getid("journalStatus"));
			Thread.sleep(2000);
			journalstatus.selectByVisibleText("Resolved");// Select status
			getid("sData").click();// click submit
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JJT_0004 - Delete an existing entry
	@Test(enabled = true, priority = 4)	
	public void deleteJournalEntry() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '1']/td[2]")));
			getxpath("//*[@id= '1']/td[2]").click();// select existing entry
			Thread.sleep(2000);
			getid("del_journal").click();// click delete icon
			Thread.sleep(2000);
			getid("dData").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Try to delete and edit, when no journal entry is there and handle popups*/
	@Test(enabled = true, priority = 5)	
	public void editWithoutJournalEntry() throws InterruptedException, Exception
	{
		try{
			getid("del_journal").click();// click delete icon

			if(getid("alerthd").isDisplayed())
			{
				getxpath("//*[@id='alerthd']/a/span").click();
			}
			Thread.sleep(3000);
			getid("edit_journal").click();// click edit icon
			{
				getxpath("//*[@id='alerthd']/a/span").click();
			}
			Thread.sleep(3000);
			getid("add_journal").click();
			Thread.sleep(2000);
			getid("sData").click();
			Thread.sleep(2000);
			if(getid("FormError").isDisplayed())
			{
				getid("cData").click();
			}
		}
		catch(Exception e)
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


