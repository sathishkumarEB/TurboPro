package com.turbopro.requirements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class RID612 extends Methods {

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername, Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email, Vendorname;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/JobInputs.xls")));
		baseUrl=srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();

		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 


		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
		Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Dropshipmanufacturer= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DropshipManufacturer")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		SO_Productname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		Email= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Email")).toString();
		Vendorname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();

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

	//logging into the application
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	//navigate to company > job settings 
	@Test(enabled = true, priority = 2)	
	public void jobSettings() throws InterruptedException, Exception
	{
		companySettings();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Job Settings")));
		driver.findElement(By.linkText("Job Settings")).click();
	}

	//setting font style and size as No 
	@Test (enabled = true, priority =3)
	public void settingFontSizeStyleToNo() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("fontSizeonTextEditor")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("fontSizeonTextEditor")));


		if(driver.findElement(By.id("chkfontSizeonTextEditorY")).isEnabled())
		{
			driver.findElement(By.id("chkfontSizeonTextEditorN")).click();
		}

		if(driver.findElement(By.id("chkfontStyleonTextEditorY")).isEnabled())
		{
			driver.findElement(By.id("chkfontStyleonTextEditorN")).click();
		}

		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

	}


	//creating a new job
	@Test(enabled = true, priority = 4)
	public void createJob() throws InterruptedException, Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Home")));
		driver.findElement(By.linkText("Home")).click();
		createNewJob(Jobname, Salesrep, Taxterritory);
	}

	//creating a quotes inside a job 
	@Test(enabled = true, priority = 5)
	public void editorInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.id("jobquotesid")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));
		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'OK')]")));
		driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteTypeDetail")));
		driver.findElement(By.id("quoteTypeDetail")).click();
		Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
		QuoteType.selectByVisibleText("CO");

		//add title
		driver.findElement(By.id("addquotegridButton")).click();
		driver.findElement(By.id("new_row_custombutton")).click();
		Thread.sleep(3000);	

		String fontStyle = driver.findElement(By.id("cke_15_text")).getText();
		String fontSize = driver.findElement(By.id("cke_16_text")).getText();
		if(fontStyle.contentEquals("Times New Roman") && fontSize.contentEquals("10"))
		{
			WebElement editor = driver.findElement(By.id("QuoteckEditordivbx"));
			Actions builder = new Actions(driver);
			Actions seriesOfActions = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
			seriesOfActions.perform();
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		}
		else
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
		}

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CloseQuoteButtonID")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
	}



	//setting font style and size as Yes and changing font style/size 
	@Test (enabled = true, priority =6)
	public void settingFontSizeStyleToYes() throws InterruptedException, Exception
	{
		jobSettings();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("fontSizeonTextEditor")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("fontSizeonTextEditor")));


		if(driver.findElement(By.id("chkfontSizeonTextEditorN")).isEnabled())
		{
			driver.findElement(By.id("chkfontSizeonTextEditorY")).click();
		}

		if(driver.findElement(By.id("chkfontStyleonTextEditorN")).isEnabled())
		{
			driver.findElement(By.id("chkfontStyleonTextEditorY")).click();
		}

		driver.findElement(By.id("fontSizeonTextEditor")).click();
		Select fontSize = new Select(driver.findElement(By.id("fontSizeonTextEditor")));
		fontSize.selectByVisibleText("12pt");
		
		driver.findElement(By.id("fontStyleonTextEditor")).click();
		Select fontStyle = new Select(driver.findElement(By.id("fontStyleonTextEditor")));
		fontStyle.selectByVisibleText("Comic Sans MS");
		
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

	}


	//Accessing the quotes in the recently opened job and view the editor
		@Test(enabled = true, priority = 7)
		public void quotesInRecentJob() throws InterruptedException, Exception
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.linkText("Home")));
			driver.findElement(By.linkText("Home")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			
			Actions doubClick = new Actions(driver);
			doubClick.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().build().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobquotesid")));
			
			driver.findElement(By.id("jobquotesid")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));
			driver.findElement(By.xpath("//input[@title='Add Quote']")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'OK')]")));
			driver.findElement(By.xpath("//button[contains(.,'OK')]")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteTypeDetail")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("CO");

			//add title
			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click();
			Thread.sleep(3000);	

			String fontStyle = driver.findElement(By.id("cke_15_text")).getText();
			String fontSize = driver.findElement(By.id("cke_16_text")).getText();
			if(fontStyle.contentEquals("Comic Sans MS") && fontSize.contentEquals("12"))
			{
				WebElement editor = driver.findElement(By.id("QuoteckEditordivbx"));
				Actions builder = new Actions(driver);
				Actions seriesOfActions = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
				seriesOfActions.perform();
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
			else
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")).click();
			}

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CloseQuoteButtonID")));
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}



	@AfterTest
	public void teardown() 
	{
		//driver.quit();
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
