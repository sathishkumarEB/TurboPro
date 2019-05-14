package com.turbopro.jobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class CreditTab extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, JobName, SalesRep, TaxTerritory, CustomerName, AgentName, Notes;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		JobName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		SalesRep = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SalesRep")).toString();
		TaxTerritory= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		CustomerName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		AgentName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"AgentName")).toString();
		Notes= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
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


	//TP_JCT_0001 - logging into the application and navigate to credit tab
	@Test(enabled = true, priority = 1)	
	public void creditTab() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		createNewJob(JobName, SalesRep, TaxTerritory);
		changeStatusToBooked(CustomerName);
		Thread.sleep(3000);
		//		if(driver.findElement(By.xpath("//span[text()='Payment Terms Order Note']")).isDisplayed())
		//		{
		//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		//		}

		getlinktext("Credit").click();
	}

	//TP_JCT_0002 - To changed status to credit
	@Test(enabled = true, priority = 2)	
	public void changeCreditStatus() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("creditStatusListID")));
			Thread.sleep(2000);
			Select creditstatus = new Select(getid("creditStatusListID"));
			Thread.sleep(2000);
			creditstatus.selectByVisibleText("Approved");// Select status
			getxpath("//input[@onclick= 'credit_save()']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Change Credit Type*/
	@Test(enabled = true, priority = 3)	
	public void changeCreditType() throws InterruptedException, Exception
	{
		getid("creditTypeListID").click();
		Select creditType = new Select(getid("creditTypeListID"));
		creditType.selectByVisibleText("Pending");
		getxpath("//input[@onclick= 'credit_save()']").click();

	}



	//TP_JCT_0003 - Add Bond Agent details
	@Test(enabled = true, priority = 4)	
	public void AddBondAgentDetails() throws InterruptedException, Exception
	{
		try{
			getid("bond").click();// select Bonded Job checkbox
			getid("BondAgentNameID").click();
			getid("BondAgentNameID").sendKeys(AgentName);// Enter Agent name
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+AgentName+"']")));	
			getxpath("//a[text()='"+AgentName+"']").click();// Select Agent name from drop down

			//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[35]/li/a")));	
			//		driver.findElement(By.xpath("//body/ul[35]/li/a")).click();// Select Agent name from drop down

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("condagentContactList")));
			Thread.sleep(2000);
			Select BondAgentName = new Select(getid("condagentContactList"));
			Thread.sleep(2000);
			BondAgentName.selectByValue("0");// select contact
			getxpath("//input[@onclick= 'credit_save()']").click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JCT_0004 - select Final waiver checkbox
	@Test(enabled = true, priority = 5)	
	public void selectFinalWaiver() throws InterruptedException, Exception
	{
		try{
			getid("isFinalWaiver").click();// select Final waiver checkbox
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JCT_0005 - Add Notes
	@Test(enabled = true, priority = 6)	
	public void addNotes() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("creditNotesID")));
			Thread.sleep(2000);
			getid("creditNotesID").sendKeys(Notes);// Enter Notes
			getxpath("//input[@onclick= 'credit_save()']").click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_JCT_0006 - RID 1057 - Contractor Draw feature*/
	@Test(enabled = true, priority = 7)	
	public void updateContractorDraw() throws InterruptedException, Exception
	{
		try{
			Select drawselect = new Select(getid("drawSelectField"));
			drawselect.selectByVisibleText("Yes");
			Thread.sleep(2000);
			getid("drawInformation").click();
			getid("drawInformation").clear();
			getid("drawInformation").sendKeys("Testing Contractor Draw");
			Thread.sleep(2000);
			Select initials = new Select(getid("drawUserInitials"));
			initials.selectByVisibleText("AB");
			Thread.sleep(2000);
			getxpath("//input[@onclick= 'credit_save()']").click();// click save
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*Update GC*/
	@Test(enabled = true, priority = 8)	
	public void updateGC() throws InterruptedException, Exception
	{
		getid("gcContactsList").click();

		Select gc = new Select(getid("gcContactsList"));
		Thread.sleep(2000);
		gc.selectByVisibleText("(Add new)");// Select status
		Thread.sleep(2000);
		getxpath("//input[@onclick= 'credit_save()']").click();// click save

	}

	/*Update GC*/
	@Test(enabled = true, priority = 9)	
	public void updateOwner() throws InterruptedException, Exception
	{
		Thread.sleep(2000);
		getid("ownerNameID").sendKeys("TestOwnerName");
		getid("ownerAddressID1").sendKeys("TestAddress1");
		getid("ownerAddressID2").sendKeys("TestAddress2");
		getid("ownerCityID").sendKeys("City");
		getid("ownerStateID").sendKeys("CS");
		getid("ownerZipID").sendKeys("23121");
		Thread.sleep(2000);
		getxpath("//input[@onclick= 'credit_save()']").click();// click save
	}


	/*Update Notices*/
	@Test(enabled = true, priority = 10)	
	public void updateNotices() throws InterruptedException, Exception
	{
		getid("RequestedNoc_Check").click();
		Select check = new Select(getid("Credit_requestedNocBY"));
		check.selectByVisibleText("AB");

		getid("ReceivedNoc_check").click();
		check.selectByVisibleText("AG");

		getid("NTCSentGC_Check").click();
		check.selectByVisibleText("AL");

		getxpath("//input[@name=\"creditReferenceNumber\"]").sendKeys("#Reference");
		Thread.sleep(2000);
		getxpath("//input[@onclick= 'credit_save()']").click();// click save
	}


	/*Update Claim Filed*/
	@Test(enabled = true, priority = 11)	
	public void updateClaimFiled() throws InterruptedException, Exception
	{
		Select CFiled = new Select(getid("claimFiledList"));
		CFiled.selectByVisibleText("Bond");
		Thread.sleep(2000);
		getid("claimFiledDate").click();
		getlinktext("1").click();
		Thread.sleep(2000);
		Select ClFiled = new Select(getid("ClaimFiledByID"));
		ClFiled.selectByVisibleText("AL");
		Thread.sleep(2000);
		getxpath("//input[@onclick= 'credit_save()']").click();// click save
	}


	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
}


