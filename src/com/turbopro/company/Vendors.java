package com.turbopro.company;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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

public class Vendors extends Methods {

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

	/*TP_CV_0001 - Logging into the application and navigate to Vendors*/
	@Test(enabled = true, priority = 1)	
	public void vendors() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateVendors();
	}

	/*TP_CV_0002 - Searching a specific vendor using search functionality*/
	@Test(enabled = true, priority = 2)	
	public void searchVendor() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			getid("searchJob").sendKeys("2999");
			getxpath("//body/ul[14]/li").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerNameHeader")));
			driver.navigate().back();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			driver.navigate().refresh();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0003 - Opening the existing vendor from table, by double clicking*/
	@Test(enabled = true, priority = 3)	
	public void openExistingVendor() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='3']/td[5]")));
			Actions doubleclick = new Actions(driver);
			doubleclick.moveToElement(getxpath("//*[@id='3']/td[5]")).doubleClick().build().perform();
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0004 - Creating a new vendor*/
	@Test(enabled = true, priority = 4)	
	public void createVendor() throws InterruptedException, Exception
	{
		try{
			navigateVendors();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='3']/td[5]")));
			getid("addvendorlist").click();
			if(getxpath("//span[text()='Add New Vendor']").isDisplayed())
			{
				getid("VendorID").sendKeys("*TestingCreateVendor");
				getxpath("//input[@onclick='saveNewVendor()']").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0005 - Add contact details to vendor*/
	@Test(enabled = true, priority = 5)	
	public void addContactsToVendor() throws InterruptedException, Exception
	{
		try{
			getxpath("//input[@onclick='addRolodexContacts()']").click();
			if(getxpath("//span[text()='Add/Edit Contact']").isDisplayed())
			{
				getid("lastName").sendKeys("testcontact");
				getxpath("//input[@onclick='submitContact()']").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0006 - Adding journal entry to vendor*/
	@Test(enabled = true, priority = 6)	
	public void addJournalToVendor() throws InterruptedException, Exception
	{
		try{
			getlinktext("Journal").click();
			getxpath("//*[@id='add_journalDetailsGrid']/div/span").click();
			if(getxpath("//span[text()='New Journal Entry']").isDisplayed())
			{
				getid("entryMemo").click();
				getid("entryMemo").clear();
				getid("entryMemo").sendKeys("testingjournalentry");
				getid("sData").click();
			}
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0007 - Adding financial details to vendor*/
	@Test(enabled = true, priority = 7)	
	public void addFinancialsToVendor() throws InterruptedException, Exception
	{
		try{
			getlinktext("Financial").click();
			getid("Fin_duedays").click();
			getid("Fin_duedays").clear();
			getid("Fin_duedays").sendKeys("3");
			getid("Fin_discpercent").click();
			getid("Fin_discpercent").clear();
			getid("Fin_discpercent").sendKeys("6");
			getid("mainsave").click();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CV_0008 - Delete a specific vendor*/
	@Test(enabled = true, priority = 8)	
	public void deleteVendor() throws InterruptedException, Exception
	{
		try{
			navigateVendors();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*TestingCreateVendor']")));
			getxpath("//td[@title='*TestingCreateVendor']").click();
			getid("deletevendorlist").click();
			if(getxpath("//span[text()='Confirmation']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
			if(getxpath("//span[text()='Success ']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*Updating the expiration date for the vendor in financials tab*/
	@Test(enabled = true, priority = 9)	
	public void updateExpirationDates() throws InterruptedException, Exception
	{
		try{
			getid("searchJob").click();
			getid("searchJob").sendKeys("Advanced Arch Grilles");
			getid("goSearchButtonID").click();
			Thread.sleep(4000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Advanced Arch Grilles']")));
			Actions act = new Actions(driver);
			act.moveToElement(getxpath("//td[@title='Advanced Arch Grilles']")).doubleClick().perform(); // open the vendor from search results
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerNameHeader")));
			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("generalLiabilityDateId")));

			getid("generalLiabilityDateId").click();
			getlinktext("1").click();
			Thread.sleep(3000);

			getid("workersCompensationDateId").click();
			getlinktext("28").click();
			Thread.sleep(3000);
			getid("mainsave").click();
			driver.navigate().refresh();
			getlinktext("Financial").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}


	/*Remove the expiration dates for the vendors in financials tab*/
	@Test(enabled = true, priority = 10)	
	public void removeExpirationDates() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			getlinktext("Financial").click();

			getid("generalLiabilityDateId").click();
			getid("generalLiabilityDateId").clear();
			Thread.sleep(3000);
			getid("Fin_1099_expense").click();
			Thread.sleep(2000);
			getid("workersCompensationDateId").click();
			getid("workersCompensationDateId").clear();
			Thread.sleep(3000);
			getid("mainsave").click();
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

