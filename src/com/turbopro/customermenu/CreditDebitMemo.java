package com.turbopro.customermenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
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

public class CreditDebitMemo extends Methods
{

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	private String Url, UName, Password;
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

	//TC_CDM_0001 - logging into the application and navigate to credit debit memo
	@Test(enabled = true, priority = 1)	
	public void creditDebitMemo() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateCustomerCreditDebitMemo();
	}

	//TC_CDM_0002 - Create credit memo
	@Test( enabled = true, priority = 2)
	public void createCreditMemo() throws InterruptedException 
	{
		try{
			driver.findElement(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")).click();// To add memo
			driver.findElement(By.id("customerID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("customerID")).sendKeys("Applegate EDM, Inc.");// Enter customer 
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'memotypeID']")));
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'memotypeID']"))).click().build().perform();
			Thread.sleep(2000);
			Select memotype = new Select(driver.findElement(By.id("memotypeID")));
			Thread.sleep(2000);
			memotype.selectByVisibleText(" Credit ");// Select memo type
			act.moveToElement(driver.findElement(By.id("amountID"))).doubleClick().perform();//driver.findElement(By.id("amountID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("amountID")).sendKeys("400");// Enter amount
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'glAccountsID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'glAccountsID']"))).click().build().perform();
			Thread.sleep(2000);
			Select glaccount = new Select(driver.findElement(By.id("glAccountsID")));
			Thread.sleep(2000);
			glaccount.selectByValue("125");// Select GL Account
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'salesmanID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'salesmanID']"))).click().build().perform();
			Thread.sleep(2000);
			Select salesman = new Select(driver.findElement(By.id("salesmanID")));
			Thread.sleep(2000);
			salesman.selectByVisibleText("Chad Litersky");// Select sales man
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'divisonID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'divisonID']"))).click().build().perform();
			Thread.sleep(2000);
			Select division = new Select(driver.findElement(By.id("divisonID")));
			Thread.sleep(2000);
			division.selectByVisibleText("BI Fort Worth");// Select division
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@"+"id= 'taxterritoryID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'taxterritoryID']"))).click().build().perform();
			Thread.sleep(2000);
			Select taxterritory = new Select(driver.findElement(By.id("taxterritoryID")));
			Thread.sleep(2000);
			taxterritory.selectByVisibleText("Fort Worth TX");// Select tax territory
			driver.findElement(By.id("savememo")).click();// click save&close button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0003 -	Edit existing credit memo
	@Test( enabled = true, priority = 3)
	public void editCreditMemo() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
			Thread.sleep(3000);
			driver.findElement(By.id("notestextareaID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("notestextareaID")).clear();
			driver.findElement(By.id("notestextareaID")).sendKeys("test");// Edit memo by adding notes
			driver.findElement(By.id("editmemo")).click();// click save&close button	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0004 - View Memo	
	@Test( enabled = true, priority = 4)
	public void viewMemo() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			WebElement openJobs = driver.findElement(By.xpath("//*[@id= 'searchJob']"));
			Actions action = new Actions(driver);
			action.moveToElement(openJobs).build().perform();
			driver.findElement(By.id("searchJob")).click();
			driver.findElement(By.id("searchJob")).clear();
			driver.findElement(By.id("searchJob")).sendKeys("CR101323");// Enter memo number
			Thread.sleep(2000);
			driver.findElement(By.id("goSearchButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));
			driver.findElement(By.xpath("//*[@id='1']/td[4]")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("editmemo")));
			driver.findElement(By.id("editmemo")).click();// click save&close button	
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0005 - Create Debit memo
	@Test( enabled = true, priority = 5)
	public void createDebitMemo() throws InterruptedException 
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")));
			driver.findElement(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")).click();// To add memo
			driver.findElement(By.id("customerID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("customerID")).sendKeys("Beco Service Inc - BAS/DMW");// Enter customer 
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'memotypeID']")));
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'memotypeID']"))).click().build().perform();
			Thread.sleep(2000);
			Select memotype = new Select(driver.findElement(By.id("memotypeID")));
			Thread.sleep(2000);
			memotype.selectByVisibleText(" Debit ");// Select memo type
			act.moveToElement(driver.findElement(By.id("amountID"))).doubleClick().perform();//driver.findElement(By.id("amountID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("amountID")).sendKeys("4400");// Enter amount
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'glAccountsID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'glAccountsID']"))).click().build().perform();
			Thread.sleep(2000);
			Select glaccount = new Select(driver.findElement(By.id("glAccountsID")));
			Thread.sleep(2000);
			glaccount.selectByValue("125");// Select GL Account
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'salesmanID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'salesmanID']"))).click().build().perform();
			Thread.sleep(2000);
			Select salesman = new Select(driver.findElement(By.id("salesmanID")));
			Thread.sleep(2000);
			salesman.selectByVisibleText("Chad Litersky");// Select sales man
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'divisonID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'divisonID']"))).click().build().perform();
			Thread.sleep(2000);
			Select division = new Select(driver.findElement(By.id("divisonID")));
			Thread.sleep(2000);
			division.selectByVisibleText("BI Fort Worth");// Select division
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'taxterritoryID']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@id= 'taxterritoryID']"))).click().build().perform();
			Thread.sleep(2000);
			Select taxterritory = new Select(driver.findElement(By.id("taxterritoryID")));
			Thread.sleep(2000);
			taxterritory.selectByVisibleText("Fort Worth TX");// Select tax territory
			Thread.sleep(2000);
			driver.findElement(By.id("savememo")).click();// click save&close button
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0006 - Edit existing Debit memo
	@Test( enabled = true, priority = 6)
	public void editDebitMemo() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().perform();
			driver.findElement(By.id("notestextareaID")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("notestextareaID")).clear();
			driver.findElement(By.id("notestextareaID")).sendKeys("test");// Edit memo by adding notes
			Thread.sleep(3000);
			driver.findElement(By.id("editmemo")).click();// click save&close button	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0007 - To view PDF
	@Test( enabled = true, priority = 7)
	public void viewPDF() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
			driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
			driver.findElement(By.id("pdfIcon")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Close")).click();// click save&close button	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TC_CDM_0008 - Email Memo
	@Test( enabled = true, priority = 8)
	public void emailMemo() throws InterruptedException 
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
			driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
			driver.findElement(By.id("emailIcon")).click();
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//*[@id= 'emailpopup']/table/tbody/tr/td[2]/input")).click();
			driver.findElement(By.linkText("Close")).click();
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
