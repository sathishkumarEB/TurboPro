package com.turbopro.jobs;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class JobMainTab extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String City, Costing, Submitting, Ordering, Takeoff, Quoteby, Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername;
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
		Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
		Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		City= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"City")).toString();
		Costing= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Costing")).toString();
		Submitting= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Submitting")).toString();
		Ordering= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Ordering")).toString();
		Takeoff= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Takeoff")).toString();
		Quoteby= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quoteby")).toString();
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

	/*TP_JMT_0001 - logging into the application, create new job*/
	@Test(enabled = true, priority = 1)	
	public void navigateReleaseInNewJob() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		createNewJob(Jobname, Salesrep, Taxterritory);
	}

	/*TP_JMT_0002, 0003 - update customer and change status to booked*/
	@Test(enabled = true, priority = 2)	
	public void updateCustomerName() throws InterruptedException, Exception
	{
		try{
			changeStatusToBooked(Customername);	//book a job with customer name		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_JMT_0004 - update job location in main tab*/
	@Test(enabled = true, priority = 3)	
	public void updateJobLocation() throws InterruptedException, Exception
	{
		try{
			getlinktext("Main").click();
			getxpath(jobMainAddressLineOne).sendKeys("Test");
			getxpath(jobMainAddressLineTwo).sendKeys("Test");
			getxpath(jobMainAddressLineThree).sendKeys("Test");
			getxpath(jobMainAddressCity).sendKeys(City);
			getxpath(jobMainAddressCity).sendKeys(Keys.ARROW_DOWN);
			getxpath(jobMainAddressCity).sendKeys(Keys.ENTER);
//			driver.findElement(By.xpath("//body/ul[29]/li/a")).click();
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_JMT_0005 - update employees assigned in main tab*/
	@Test(enabled = true, priority = 4)	
	public void updateEmployeesAssigned() throws InterruptedException, Exception
	{
		try{
			getxpath(jobMainEmpCosting).sendKeys(Costing);
			getxpath("//body/ul[18]/li/a").click();
			getxpath(jobMainEmpSubmitting).sendKeys(Submitting);
			getxpath("//body/ul[19]/li/a").click();
			getxpath(jobMainEmpOrdering).sendKeys(Ordering);
			getxpath("//body/ul[21]/li/a").click();
			getxpath(jobMainEmpTakeOff).sendKeys(Takeoff);
			getxpath("//body/ul[22]/li/a").click();
			getxpath(jobMainEmpQuoteBy).sendKeys(Quoteby);
			getxpath("//body/ul[23]/li/a").click();
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_JMT_0006 - Add new architecture*/
	@Test(enabled = true, priority = 5)	
	public void addNewArchitect() throws InterruptedException, Exception
	{
		try{
			getxpath(jobMainArchitect).sendKeys("test");
			getlinktext("+ Add New").click();

			if(getid("ui-dialog-title-addArchitectDialog").isDisplayed())
			{
				getid("companyName2").sendKeys("testarchitect");
				getxpath("//input[@onclick='saveNewArchitect()']").click();
			}
			Thread.sleep(2000);
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_JMT_0007 - Add engineer name*/
	@Test(enabled = true, priority = 6)	
	public void addNewEngineer() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			getxpath(jobMainEngineer).sendKeys("test");
			getlinktext("+ Add New").click();

			if(getid("ui-dialog-title-addEngineerDialog").isDisplayed())
			{
				getid("companyName1").sendKeys("testengineer");
				getxpath("//input[@onclick='saveNewEngineer()']").click();
			}
			Thread.sleep(2000);
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_JMT_0008 - Change tax territory*/
	@Test(enabled = true, priority = 7)	
	public void changeTaxTerritory() throws InterruptedException, Exception
	{
		try{
			getxpath(jobMainJobSiteTaxTerritory).click();
			getxpath(jobMainJobSiteTaxTerritory).clear();
			getxpath(jobMainJobSiteTaxTerritory).sendKeys("Austin");
			getxpath("//a[contains (.,'Austin')]").click();
			//			driver.findElement(By.xpath("//body/ul[27]/li/a")).click();
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_JMT_0009 - Add split commissions in main tab of job*/
	@Test(enabled = true, priority = 8)	
	public void addSplitCommission() throws InterruptedException, Exception
	{
		try{
			getxpath(jobMainEmpSplitCommission).click();
			if(getid("ui-dialog-title-openSplitcommDia").isDisplayed())
			{
				getxpath("//td[@id='CommissionSplitsGrid_iladd']/div").click();
				getid("new_row_rep").sendKeys("Mark Bell");
				getxpath("//a[contains (.,'Mark Bell')]").click();

				//				driver.findElement(By.xpath("//body/ul[32]/li/a")).click();
				getid("new_row_allocated").click();
				getid("new_row_allocated").clear();
				getid("new_row_allocated").sendKeys("100");
				getid("new_row_splittype").click();
				getid("new_row_splittype").clear();
				getid("new_row_splittype").sendKeys("Estimating");
				getxpath("//a[contains (.,'Estimating')]").click();
				//				driver.findElement(By.xpath("//body/ul[33]/li/a")).click();
				getxpath("//td[@id='CommissionSplitsGrid_ilsave']/div").click();
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}

			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Updating the bid Status Type under Bid Date box*/
	@Test(enabled = true, priority = 9)	
	public void updateStatusType() throws InterruptedException, Exception
	{
		try{
			getxpath(jobMainBidStatusType).click();
			Select statusType = new Select(getxpath(jobMainBidStatusType));
			statusType.selectByVisibleText("Level 1");
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Updating Original bid date*/
	@Test(enabled = true, priority = 10)	
	public void updateOriginaBidDate() throws InterruptedException, Exception
	{
		try{
			//		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("originalbidDate_Date")));
			Thread.sleep(4000);
			getxpath(jobMainOriginalBidDate).click();
			getlinktext("1").click();
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[3]/button[2]")).click();
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Updating the general contractor*/
	@Test(enabled = true, priority = 11)	
	public void addGeneralContractor() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			getxpath(jobMainGeneralContractor).sendKeys("test");
			getlinktext("+ Add New").click();

			if(getid("addContractorDialog").isDisplayed())
			{
				getid("companyName3").sendKeys("testgeneralcontractor");
				getxpath("//input[@onclick='saveNewContractor()']").click();
			}
			Thread.sleep(2000);
			getxpath(jobMainSaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Updating the Customer PO*/
	@Test(enabled = true, priority = 12)	
	public void updateCustomerPO() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		getxpath(jobMainCustomerPO).click();
		getxpath(jobMainCustomerPO).clear();
		getxpath(jobMainCustomerPO).sendKeys("PO #123");

		getxpath(jobMainMoreButton).click();

		if(getid("ui-dialog-title-openCustomerPODia").isDisplayed())
		{
			getid("covered_material1").sendKeys("Covered_Material 001");
			getid("po_number1").sendKeys("PO #123 Check");
			//			driver.findElement(By.id("contractAmount")).clear();
			//			driver.findElement(By.id("contractAmount")).sendKeys("100");	
			getid("savecustomerpo").click();

			if(getxpath("//span[text()= 'Success']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
	}


	/*RID 995 - Updating owner field in the design/construct team of main tab*/
	@Test(enabled = true, priority = 13)	
	public void updateOwner() throws InterruptedException, Exception
	{
		getxpath(jobMainOwner).click();
		getxpath(jobMainOwner).clear();
		getxpath(jobMainOwner).sendKeys("Barcel USA");
		Thread.sleep(3000);
		getxpath("//a[contains (.,'Barcel USA')]").click();
		Thread.sleep(2000);
		getxpath(jobMainSaveButton).click();
		Thread.sleep(3000);
	}

	/*RID 1025 - Project Manager dropdown under customer*/
	@Test(enabled = true, priority = 14)	
	public void updateProjectManager() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		Select ProjectManager = new Select(getxpath(jobMainProjectManager));
		ProjectManager.selectByVisibleText("+ Add New");

		getid("lastName").sendKeys("TestLastName");
		getid("firstName").sendKeys("TestFirstName");
		getid("jobPosition").sendKeys("TestRole");
		getid("email").sendKeys("test@email.com");
		getid("areaCodeDirID").sendKeys("123");
		getid("exchangeCodeDirID").sendKeys("456");
		getid("subscriberNumberDirID").sendKeys("7890");

		getid("areaCodeCellId").sendKeys("098");
		getid("exchangeCodeCellId").sendKeys("765");
		getid("subscriberNumberCellId").sendKeys("4321");

		getid("division").sendKeys("TestDivision");

		getxpath("//input[@onclick='submitProjectManagerDialog()']").click();
		Thread.sleep(3000);
		getxpath(jobMainSaveButton).click();

		String cusname = (getid("jobHeader_JobCustomerName_id").getText());
		System.out.println(cusname);

	}

	/*RID 1075 - Change Job Status To No Bid*/
	@Test(enabled = false, priority = 15)	
	public void nobidJobStatus() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		Select status = new Select(getxpath(jobMainJobStatus));
		status.selectByVisibleText("No Bid");

	}


	/*RID 1113A - Updating the Installing Contractor*/
	@Test(enabled = true, priority = 16)	
	public void updatingInstallingContractor() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		getxpath(jobMainInstallingContractor).click();
		getxpath(jobMainInstallingContractor).sendKeys("Amarillo Winair");
		Thread.sleep(3000);
		getxpath(jobMainInstallingContractor).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		getxpath(jobMainInstallingContractor).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		getxpath(jobMainSaveButton).click();
		
	}


	/*RID 1113A - Updating the Installing Contractor*/
	@Test(enabled = true, priority = 17)	
	public void removingInstallingContractor() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		getxpath(jobMainInstallingContractor).click();
		getxpath(jobMainInstallingContractor).clear();
		Thread.sleep(3000);
		getxpath(jobMainSaveButton).click();
	}


	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
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
