package com.turbopro.company;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class Settings extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		try
		{
			srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));
			openChromeBrowser();

			Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
			UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
			Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		}
		catch (Exception e) 
		{
			System.out.println("Exception occurred:" +e);
		}


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

	/*TP_CS_0001 - Logging into the application and navigate to settings*/
	@Test(enabled = true, priority = 1)	
	public void settings() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateSettings();
	}

	/*TP_CS_0002 - Upload a logo in company settings */
	@Test(enabled = true, priority = 2)	
	public void uploadCompanyLogo() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("file2")));
		driver.findElement(By.id("file2")).sendKeys("C:\\Users\\sathish_kumar\\Desktop\\companyLogo.gif");
		driver.findElement(By.xpath("//button[@onclick='uploadJqueryForm()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	/*TP_CS_0003 - Exporting the company contacts*/
	@Test(enabled = true, priority = 3)	
	public void exportCompanyContacts() throws InterruptedException, Exception
	{
		try{
		Thread.sleep(5000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@onclick='exportCompanyContacts()']")));
		driver.findElement(By.xpath("//img[@onclick='exportCompanyContacts()']")).click();
		Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0004 - Defined group defaults - Opening and clicking save & close*/
	@Test(enabled = true, priority = 4)	
	public void updateGroupDefaults() throws InterruptedException, Exception
	{
		try{
		for(int i=1; i<7;i++)
		{	
			driver.findElement(By.id("permission"+i+"")).click();
			if(driver.findElement(By.xpath("//span[text()='Group Permission']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0005 - Navigating to customer settings and view customer type one by one*/
	@Test(enabled = true, priority = 5)	
	public void viewCustomerType() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Customer Settings")));
		driver.findElement(By.linkText("Customer Settings")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='chartsOfCuTypesGrid']/tbody/tr[2]/td[2]")));
		for(int i=2; i>6; i++)
		{
			driver.findElement(By.xpath("//table[@id='chartsOfCuTypesGrid']/tbody/tr["+i+"]/td[2]")).click();
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0006 - Add a customer type in customer settings*/
	@Test(enabled = true, priority = 6)	
	public void addCustomerType() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/input[@value='Clear']")));
		driver.findElement(By.xpath("//span/input[@value='Clear']")).click();

		driver.findElement(By.id("typeDescriptionId")).sendKeys("*testCusType");
		driver.findElement(By.id("typeCodeId")).sendKeys("TT");
		driver.findElement(By.xpath("//input[@onclick='saveCuTypeData()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0007 - Delete the customer type from customer settings*/
	@Test(enabled = true, priority = 7)	
	public void deleteCustomerType() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*testCusType']")));
		driver.findElement(By.xpath("//td[@title='*testCusType']")).click();
		driver.findElement(By.xpath("//input[@onclick='deleteCuType()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0008 - View existing customer payment terms one by one*/
	@Test(enabled = true, priority = 8)	
	public void viewCustomerPaymentTerms() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='chartsOfTermsGrid']/tbody/tr[2]/td[3]")));
		for(int i=2; i>6; i++)
		{
			driver.findElement(By.xpath("//table[@id='chartsOfTermsGrid']/tbody/tr["+i+"]/td[3]")).click();
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0009 - Create a customer payment term in customer settings*/
	@Test(enabled = true, priority = 9)	
	public void createCustomerPaymentTerms() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.xpath("//input[@onclick='openAddNewTermsDialog()']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Add New Payment Terms']")).isDisplayed())
		{
			driver.findElement(By.xpath("//form[@id='addNewTermsFormID']/table/tbody/tr/td/fieldset/table/tbody/tr/td[2]/input[@id='description']")).sendKeys("*testcustomerpaymentterms");
			driver.findElement(By.xpath("//form[@id='addNewTermsFormID']/table/tbody/tr[2]/td/fieldset/table/tbody/tr/td/textarea[@id='orderNote']")).sendKeys("order note for testing the customers");
			driver.findElement(By.xpath("//form[@id='addNewTermsFormID']/table/tbody/tr[3]/td/fieldset/table/tbody/tr/td/input[@id='pickTicket1']")).sendKeys("test pick ticket note 1");
			driver.findElement(By.xpath("//form[@id='addNewTermsFormID']/table/tbody/tr[3]/td/fieldset/table/tbody/tr/td/input[@id='pickTicket2']")).sendKeys("test pick ticket note 2");
			driver.findElement(By.id("saveTermsButton")).click();
		}
		Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0010 - Delete the customer payment term in customer settings*/
	@Test(enabled = true, priority = 10)	
	public void deleteCustomerPaymentTerms() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.xpath("//td[@title='*testcustomerpaymentterms']")).click();
		driver.findElement(By.xpath("//input[@onclick='deletePaymentTerms()']")).click();
		if(driver.findElement(By.xpath("//span[text()='Confirm Delete']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0011 - View existing ship via details one by one in Vendor Settings*/
	@Test(enabled = true, priority = 11)	
	public void viewShipVia() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.linkText("Vendor Settings")).click();
		for(int i=2; i>6; i++)
		{
			driver.findElement(By.xpath("//table[@id='shipViaListGrid']/tbody/tr["+i+"]/td[3]")).click();
		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0012 - Add a ship via details in vendor settings*/
	@Test(enabled = true, priority = 12)	
	public void addShipVia() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.xpath("//*[@id='chartsDetailsDiv']/table/tbody/tr/td/table/tbody/tr[1]/td/input[1]")).click();
		driver.findElement(By.id("DescriptionID")).sendKeys("*testShipVia");
		driver.findElement(By.id("trackingUrlID")).sendKeys("testingShipUrl");
		List<WebElement> save = driver.findElements(By.id("saveshipviadetails"));
		save.get(0).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0013 - Delete the specific ship via detail, which is added in vendor settings*/
	@Test(enabled = true, priority = 13)	
	public void deleteShipVia() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.xpath("//td[@title='*testShipVia']")).click();
		List<WebElement> delete = driver.findElements(By.id("deleteshipviadetails"));
		delete.get(0).click();
		Thread.sleep(3000);
		//		if(driver.findElement(By.xpath("//span[text()='Confirm Delete']")).isDisplayed())
		//		{
		//			Thread.sleep(2000);
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		//		}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0014 - View existing freight charges one by one in vendor settings*/   
	@Test(enabled = true, priority = 14)	
	public void viewFreightCharges() throws InterruptedException, Exception
	{
		try{
		if(driver.findElement(By.xpath("//table[@id='freightChargesGrid']/tbody/tr[2]/td[3]")).isDisplayed())
		{
			for(int i=2; i>4; i++)
			{
				driver.findElement(By.xpath("//table[@id='freightChargesGrid']/tbody/tr["+i+"]/td[3]")).click();
			}
		}

		List<WebElement> save = driver.findElements(By.id("saveshipviadetails"));
		save.get(1).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0015 - Add freight charges in vendor settings*/
	@Test(enabled = true, priority = 15)	
	public void addFreightCharges() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.id("freigthDescriptionID")).click();
		driver.findElement(By.id("freigthDescriptionID")).clear();
		driver.findElement(By.id("freigthDescriptionID")).sendKeys("*TestFreightCharge");
		driver.findElement(By.id("addnewfreight")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0016 - Delete the freight charge in the vendor settings*/
	@Test(enabled = true, priority = 16)	
	public void deleteFreightCharges() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*TestFreightCharge']")));
		driver.findElement(By.xpath("//td[@title='*TestFreightCharge']")).click();

		List<WebElement> delete = driver.findElements(By.id("deleteshipviadetails"));
		delete.get(1).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0016 - Employee settings - add new split type*/
	@Test(enabled = true, priority = 17)	
	public void addSplitTypesInEmpSettings() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.linkText("Employee Settings")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("splittypedescription")).click();
		driver.findElement(By.id("splittypedescription")).clear();
		driver.findElement(By.id("splittypedescription")).sendKeys("*testsplit");
		driver.findElement(By.id("spltypdefaultper")).sendKeys("33");
		driver.findElement(By.xpath("//input[@onclick='addCommissionSplitTypes()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0017 - Delete the split type in employee settings*/
	@Test(enabled = true, priority = 18)	
	public void deleteSplitTypesInEmpSettings() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*testsplit']")));
		driver.findElement(By.xpath("//td[@title='*testsplit']")).click();
		List<WebElement> delete = driver.findElements(By.id("deleteshipviadetails"));
		delete.get(2).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0018 - View the existing bid status one by one in Job Settings*/
	@Test(enabled = true, priority = 19)	
	public void viewBidStatus() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.linkText("Job Settings")).click();
		if(driver.findElement(By.xpath("//table[@id='bidstatusgrid']/tbody/tr[2]/td[3]")).isDisplayed())
		{
			for(int i=2; i>6; i++)
			{
				driver.findElement(By.xpath("//table[@id='freightChargesGrid']/tbody/tr["+i+"]/td[3]")).click();
			}
		}
		driver.findElement(By.xpath("//input[@onclick='editBidStatus()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0019 - Add a new bid statues in Job Settings*/
	@Test(enabled = true, priority = 20)	
	public void addBidStatus() throws InterruptedException, Exception
	{
		try{
		Thread.sleep(3000);
		driver.findElement(By.id("BidDescriptionID")).sendKeys("*testbidstatus");
		driver.findElement(By.id("bidcodeid")).sendKeys("TT");
		driver.findElement(By.xpath("//input[@onclick='addBidStatus()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0020 - Delete the existing bid status in Job Settings*/
	@Test(enabled = true, priority = 21)	
	public void deleteBidStatus() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*testbidstatus']")));
		driver.findElement(By.xpath("//td[@title='*testbidstatus']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@onclick='deleteBidStatus()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0020 - Add Quotes Category in Job Settings*/
	@Test(enabled = true, priority = 22)	
	public void addQuotesCategory() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.id("quotesCategoryDescription")).sendKeys("*testquotescategory");
		driver.findElement(By.xpath("//input[@onclick='addQuotesCategory()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0021 - Delete quotes category in Job Settings*/
	@Test(enabled = true, priority = 23)	
	public void deleteQuotesCategory() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*testquotescategory']")));
		driver.findElement(By.xpath("//td[@title='*testquotescategory']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@onclick='deleteQuotesCategory()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0022 - Add departments in Inventory settings*/
	@Test(enabled = true, priority = 24)	
	public void createDepartments() throws InterruptedException, Exception
	{
		try{
		driver.findElement(By.linkText("Inventory Settings")).click();
		driver.findElement(By.xpath("//input[@onclick='clearDepartmentDetails()']")).click();
		driver.findElement(By.id("departmentName")).sendKeys("Test Inven Dep");
		driver.findElement(By.id("revenueAccount")).sendKeys("1350");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		driver.findElement(By.id("cgsAccount")).sendKeys("1200");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[14]/li/a")));
		driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
		driver.findElement(By.xpath("//input[@onclick='SaveDepartmentDetails()']")).click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CS_0023 - Make department as inactive in Inventory settings*/
	@Test(enabled = true, priority = 25)	
	public void inactiveDepartment() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Test Inven Dep']")));
		driver.findElement(By.xpath("//td[@title='Test Inven Dep']")).click();
		driver.findElement(By.id("departmentInactive")).click();
		driver.findElement(By.xpath("//input[@onclick='SaveDepartmentDetails()']")).click();
		Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CS_0024 - Delete the department in the Inventory Settings*/
	@Test(enabled = true, priority = 26)	
	public void deleteDepartments() throws InterruptedException, Exception
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Test Inven Dep']")));
		driver.findElement(By.xpath("//td[@title='Test Inven Dep']")).click();
		driver.findElement(By.xpath("//input[@onclick='deleteDepartmentDetails()']")).click();
		Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*RID 721 - Invoice Profit Margin on Commission Statement*/
	/*TP_CS_0025 - View employee commissions with profit margin*/
	@Test(enabled = true, priority = 27)	
	public void viewInvoiceProfitMargin() throws InterruptedException, Exception
	{
		try{
		navigateSettings();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Employee Settings")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("show_invoiceprofitMarginNo")));
		
		if(driver.findElement(By.id("show_invoiceprofitMarginNo")).isSelected())
		{
			driver.findElement(By.id("show_invoiceprofitMarginYes")).click();
		}

		driver.findElement(By.id("btnCommissionSettings")).click();
		Thread.sleep(3000);
		navigateEmployeeCommission();
		Thread.sleep(5000);
		
		Select EndPeriod = new Select(driver.findElement(By.id("endperioddate")));
		EndPeriod.selectByVisibleText("10/31/2017");
		
		driver.findElement(By.xpath("//*[@id='2']/td[7]/div/a/img")).click();
		Thread.sleep(5000);
		parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	

	/*RID 721 - Invoice Profit Margin on Commission Statement*/
	/*TP_CS_0026 - View employee commissions with profit commission*/
	@Test(enabled = true, priority = 28)	
	public void viewInvoiceCommissionWithSplite() throws InterruptedException, Exception
	{
		try{
		navigateSettings();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Employee Settings")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("show_invoiceprofitMarginNo")));
		
		if(driver.findElement(By.id("show_invoiceprofitMarginYes")).isSelected())
		{
			driver.findElement(By.id("show_invoiceprofitMarginNo")).click();
		}

		driver.findElement(By.id("btnCommissionSettings")).click();
		Thread.sleep(3000);
		navigateEmployeeCommission();
		Thread.sleep(5000);
		
		Select EndPeriod = new Select(driver.findElement(By.id("endperioddate")));
		EndPeriod.selectByVisibleText("10/31/2017");
		
		driver.findElement(By.xpath("//*[@id='2']/td[7]/div/a/img")).click();
		Thread.sleep(5000);
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

}

