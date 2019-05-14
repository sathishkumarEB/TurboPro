package com.turbopro.customermenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class CustomerStatements extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password;
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


	//TC_CS_0001 - logging into the application and navigate to credit tab
	@Test(enabled = true, priority = 1)	
	public void cusStatements() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateCustomerStatement();
	}

	//TC_CS_0002 - Statements sorted with invoice
	@Test(enabled = true, priority = 2)
	public void customerStatementWithInvoice() throws InterruptedException 
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CS_0003 - Statements sorted with date
	@Test(enabled = true, priority = 3)
	public void customerStatementWithDate() throws InterruptedException 
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("dateSorting")));
		driver.findElement(By.id("dateSorting")).click();// select date
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CS_0004 - Statements sorted with customerPO
	@Test(enabled = true, priority = 4)
	public void customerStatementWithcustomerPO() throws InterruptedException 
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerPO")));
		driver.findElement(By.id("customerPO")).click();// select customerPO
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CS_0006 - Statements sorted on selecting invoices with credit
	@Test(enabled = true, priority = 5)
	public void selectInvoicesWithCredit() throws InterruptedException 
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("warehouseInactive")));
		driver.findElement(By.id("warehouseInactive")).click();// select Invoices with credit
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CS_0007 - Edit exclusion date
	@Test(enabled = true, priority = 6)
	public void editExclusionDate() throws InterruptedException 
	{
		try{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		driver.findElement(By.id("exclusionDate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("10")));// select exclusion date date
		driver.findElement(By.linkText("10")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	//TC_CS_0008 - Statements sorted on selecting invoices with credit
	@Test(enabled = true, priority = 7)
	public void changeCustomersettings() throws InterruptedException 
	{
		try{
		companySettings();// navigate to company settings
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Customer Settings")));
		driver.findElement(By.linkText("Customer Settings")).click();// select customer settings
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("chk_cusStGpbyJobYes")));
		driver.findElement(By.id("chk_cusStGpbyJobYes")).click();// select yes
		driver.findElement(By.xpath("//*[@id='settingsCustmerDetailsBlock']/table/tbody/tr[5]/td/fieldset/div/input")).click();// select save
		Thread.sleep(3000);
		navigateCustomerStatement();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("stCustomerID")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.id("stCustomerID"))).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(driver.findElement(By.id("stCustomerID")));
		Thread.sleep(2000);
		drpSalesRep.selectByValue("12399");
		Thread.sleep(2000);// select customer
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[6]/td/input[2]")).click();// click Print
		parentWindow();
		}
		catch(Exception e)
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
