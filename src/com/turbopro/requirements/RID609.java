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

public class RID609 extends Methods {

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

	@Test(enabled = true, priority = 2)
	public void createJob() throws InterruptedException, Exception
	{
		createNewJob(Jobname, Salesrep, Taxterritory);
	}

	//creating a quotes inside a job 
	@Test(enabled = true, priority = 3)
	public void quotes() throws InterruptedException, Exception
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
		QuoteType.selectByVisibleText("MC");

		//add title
		driver.findElement(By.id("addquotegridButton")).click();
		driver.findElement(By.id("new_row_custombutton")).click();
		Thread.sleep(3000);		
		WebElement editor = driver.findElement(By.id("QuoteckEditordivbx"));
		Actions builder = new Actions(driver);
		Actions seriesOfActions = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

		//adding first line item to the quotes
		driver.findElement(By.id("addquotegridButton")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type = new Select(driver.findElement(By.id("new_row_type")));
		Type.selectByVisibleText("Item2");
		driver.findElement(By.id("new_row_quantity")).sendKeys("3");
		driver.findElement(By.id("new_row_cost")).sendKeys("333");
		driver.findElement(By.id("new_row_vendorname")).sendKeys("Air Vent Inc.");
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_custombutton")).click();
		Thread.sleep(3000);	
		Actions seriesOfActions1 = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions1.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_quantity")));
		driver.findElement(By.id("new_row_quantity")).click();
		driver.findElement(By.id("new_row_quantity")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		//adding second line item to the quotes
		driver.findElement(By.id("addquotegridButton")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
		Type1.selectByVisibleText("Item3");
		driver.findElement(By.id("new_row_quantity")).sendKeys("4");
		driver.findElement(By.id("new_row_cost")).sendKeys("444");
		driver.findElement(By.id("new_row_vendorname")).sendKeys("Air Vent Inc.");
		driver.findElement(By.id("new_row_custombutton")).click();
		Thread.sleep(3000);	
		Actions seriesOfActions3 = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions3.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_quantity")));
		driver.findElement(By.id("new_row_quantity")).click();
		driver.findElement(By.id("new_row_quantity")).sendKeys(Keys.ENTER);

		//adding the price line item to the quotes
		driver.findElement(By.id("addquotegridButton")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
		Type3.selectByVisibleText("Price");
		driver.findElement(By.id("new_row_textbox")).sendKeys("Price....");
		driver.findElement(By.id("new_row_sellprice")).sendKeys("777" + Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.id("SaveQuoteButtonID")).click();	
		Thread.sleep(4000);
		driver.findElement(By.id("CloseQuoteButtonID")).click();	
	}

	//creating a quotes inside a job using templates  
	@Test(enabled = true, priority = 4)
	public void createQuoteFromTemplate() throws InterruptedException, Exception
	{
		driver.findElement(By.id("jobquotesid")).click();
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));
		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
		driver.findElement(By.id("quoteTypeDetail")).click();
		Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
		QuoteType.selectByVisibleText("MC");
		driver.findElement(By.id("templateID")).click();
		Select Template = new Select(driver.findElement(By.id("templateID")));
		Template.selectByVisibleText("Air Device Template");
		Thread.sleep(3000);

		if(driver.findElement(By.xpath("//span[text()='Warning']")).isDisplayed())
		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).clear();
			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).sendKeys("Alan Stark");
			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).sendKeys(Keys.ENTER);

			//	driver.findElement(By.id("SaveQuoteButtonID")).click();
		} else 
			if(driver.findElement(By.xpath("//span[text()='Warning']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
				driver.findElement(By.id("jobQuoteRevision")).sendKeys("1");
				Template.selectByVisibleText("Air Device Template");

				//	driver.findElement(By.id("SaveQuoteButtonID")).click();
			}
		Thread.sleep(3000);
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("CloseQuoteButtonID")).click();
	}


	//deleting the created quote
	@Test(enabled = true, priority = 5)
	public void deleteQuote() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Delete Quote']")));
		driver.findElement(By.xpath("//td[@title='BAS']")).click();
		driver.findElement(By.xpath("//input[@title='Delete Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Delete the Quote Record?')]")));
		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Delete the Quote Record?')]")))
		{
			if(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Delete the Quote Record?')]")).isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
				new Actions(driver).click(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]"))).build().perform();
			}
		} 
	}


	//creating quotes by creating multiple templates
	@Test(enabled = true, priority = 6)
	public void createQuoteByMultipleTemplates() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		driver.findElement(By.id("jobquotesid")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));
		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
		driver.findElement(By.id("quoteTypeDetail")).click();
		Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
		QuoteType.selectByVisibleText("Eng");
		driver.findElement(By.id("templateID")).click();
		Select Template = new Select(driver.findElement(By.id("templateID")));
		Template.selectByVisibleText("Air Device Template");
		Thread.sleep(3000);
		Template.selectByVisibleText("CRL-Boomerang");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("SaveQuoteButtonID")));		
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("addquotesloader")));
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("CloseQuoteButtonID")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));

	}


	//delete line items in the created quotes
	@Test(enabled = true, priority = 6)
	public void deleteLineItemsInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("joQuoteHeaderIDforimg_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("joQuoteHeaderIDforimg_1")));
		driver.findElement(By.id("joQuoteHeaderIDforimg_1")).click();
		driver.findElement(By.id("joQuoteHeaderIDforimg_2")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("SaveQuoteButtonID")));
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("addquotesloader")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}

	//cut and paste the line items in quotes 
	@Test(enabled = true, priority = 7)
	public void cutAndPasteLineItemsInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqg_addnewquotesList_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("jqg_addnewquotesList_1")));
		driver.findElement(By.id("jqg_addnewquotesList_1")).click();
		driver.findElement(By.id("jqg_addnewquotesList_2")).click();
		driver.findElement(By.id("jqg_addnewquotesList_3")).click();
		driver.findElement(By.id("jqg_addnewquotesList_4")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("cutQuotes")));
		driver.findElement(By.id("cutQuotes")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("pasteQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("addquotesloader")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//copy and paste the line items in quotes 
	@Test(enabled = true, priority = 8)
	public void copyAndPasteLineItemsInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqg_addnewquotesList_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("jqg_addnewquotesList_1")));
		driver.findElement(By.id("jqg_addnewquotesList_1")).click();
		driver.findElement(By.id("jqg_addnewquotesList_2")).click();
		driver.findElement(By.id("jqg_addnewquotesList_3")).click();
		driver.findElement(By.id("jqg_addnewquotesList_4")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("copyQuotes")));
		driver.findElement(By.id("copyQuotes")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("pasteQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("SaveQuoteButtonID")).click();
		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("addquotesloader")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//cancel the cut action after selecting line items in quotes
	@Test(enabled = true, priority = 9)
	public void cancelCutActionInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqg_addnewquotesList_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("jqg_addnewquotesList_1")));
		driver.findElement(By.id("jqg_addnewquotesList_1")).click();
		driver.findElement(By.id("jqg_addnewquotesList_2")).click();
		driver.findElement(By.id("jqg_addnewquotesList_3")).click();
		driver.findElement(By.id("jqg_addnewquotesList_4")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("cutQuotes")));
		driver.findElement(By.id("cutQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("cancelQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//cancel the cut action after selecting line items in quotes
	@Test(enabled = true, priority = 10)
	public void cancelCopyActionInQuotes() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqg_addnewquotesList_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("jqg_addnewquotesList_1")));
		driver.findElement(By.id("jqg_addnewquotesList_1")).click();
		driver.findElement(By.id("jqg_addnewquotesList_2")).click();
		driver.findElement(By.id("jqg_addnewquotesList_3")).click();
		driver.findElement(By.id("jqg_addnewquotesList_4")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("copyQuotes")));
		driver.findElement(By.id("copyQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("cancelQuotes")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//save the quote as template
	@Test(enabled = true, priority = 11)
	public void saveQuoteAsTemplate() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jqg_addnewquotesList_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("saveastemplateid")));
		driver.findElement(By.id("saveastemplateid")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("NewTemplateName")));
		driver.findElement(By.id("NewTemplateName")).click();
		driver.findElement(By.id("NewTemplateName")).sendKeys("*");
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("CloseQuoteButtonID")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//Closing the quotes without saving and responding as No
	@Test(enabled = false, priority = 12)
	public void deleteLineItemsAndCloseWithoutSave() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("joQuoteHeaderIDforimg_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("joQuoteHeaderIDforimg_1")));
		driver.findElement(By.id("joQuoteHeaderIDforimg_1")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("CloseQuoteButtonID")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]")))
		{
			if(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]")).isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]")));
				new Actions(driver).click(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]"))).build().perform();
			}
		} 
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//Closing the quotes and responding as Yes to save

	@Test(enabled = false, priority = 13)
	public void deleteLineItemsAndSaveWhileClosing() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Eng']")).click();
		driver.findElement(By.xpath("//input[@title='Edit Quote']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("joQuoteHeaderIDforimg_1")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("joQuoteHeaderIDforimg_1")));
		driver.findElement(By.id("joQuoteHeaderIDforimg_1")).click();
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("CloseQuoteButtonID")));
		driver.findElement(By.id("CloseQuoteButtonID")).click();
		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]")))
		{
			if(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]")).isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
				new Actions(driver).click(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]"))).build().perform();
			}
		} 
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Eng']")));
	}


	//opening the saved quote template 
	@Test(enabled = true, priority = 14)
	public void openSavedTemplate() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='*']")).click();
		driver.findElement(By.xpath("//img[@title='Edit Quote Template']")).click();
		driver.findElement(By.id("quoteTemplateCancel")).click();
	}

	//view pdf of saved quote template 
	@Test(enabled = true, priority = 15)
	public void viewSavedTemplatePdf() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='*']")).click();
		driver.findElement(By.xpath("//img[@title='Edit Quote Template']")).click();
		driver.findElement(By.id("QuoteTemppreviewButton")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
				// Perform your operation here    
				Thread.sleep(10000);
				driver.close();
				driver.switchTo().window(parentWindow);
				Thread.sleep(3000);      
			}
		}
		driver.findElement(By.id("quoteTemplateCancel")).click();
	}



	//update template description in quote template 
	@Test(enabled = true, priority = 16)
	public void updateTemplateDescription() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*']")));
		driver.findElement(By.xpath("//td[@title='*']")).click();
		driver.findElement(By.xpath("//img[@title='Edit Quote Template']")).click();
		driver.findElement(By.id("templateDescription")).click();
		driver.findElement(By.id("templateDescription")).clear();
		driver.findElement(By.id("templateDescription")).sendKeys("*test");
		driver.findElement(By.id("quoteTemplateSaveId")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("quoteTemplateCancel")).click();
	}

	//delete the template from the quote template
	@Test(enabled = true, priority = 17)
	public void deleteQuoteTemplate() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='*test']")));
		driver.findElement(By.xpath("//td[@title='*test']")).click();
		driver.findElement(By.xpath("//img[@title='Delete Quote Template']")).click();
		if(isElementPresent(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Do you want to delete')]")))
		{
			if(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Do you want to delete')]")).isDisplayed())
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
				new Actions(driver).click(driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]"))).build().perform();
			}
		} 

	}

	//add a new quote template
	@Test(enabled = true, priority = 18)
	public void createQuoteTemplate() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//img[@title='Add Quote Template']")).click();
		driver.findElement(By.id("templateDescription")).click();
		driver.findElement(By.id("templateDescription")).clear();
		driver.findElement(By.id("templateDescription")).sendKeys("*test1");
		driver.findElement(By.id("addquotegridButton_temp")).click();
		driver.findElement(By.id("new_row_custombutton_temp")).click();
		Thread.sleep(3000);		
		WebElement editor = driver.findElement(By.id("QuoteckEditordivbx_temp"));
		Actions builder = new Actions(driver);
		Actions seriesOfActions = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

		//adding first line item to the quotes
		driver.findElement(By.id("addquotegridButton_temp")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type = new Select(driver.findElement(By.id("new_row_type")));
		Type.selectByVisibleText("Item2");
		driver.findElement(By.id("new_row_quantity")).sendKeys("3");
		driver.findElement(By.id("new_row_cost")).sendKeys("333");
		driver.findElement(By.id("new_row_vendorname")).sendKeys("Air Vent Inc.");
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_custombutton_temp")).click();
		Thread.sleep(3000);	
		Actions seriesOfActions1 = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions1.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_quantity")));
		driver.findElement(By.id("new_row_quantity")).click();
		driver.findElement(By.id("new_row_quantity")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		//adding second line item to the quotes
		driver.findElement(By.id("addquotegridButton_temp")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
		Type1.selectByVisibleText("Item3");
		driver.findElement(By.id("new_row_quantity")).sendKeys("4");
		driver.findElement(By.id("new_row_cost")).sendKeys("444");
		driver.findElement(By.id("new_row_vendorname")).sendKeys("Air Vent Inc.");
		driver.findElement(By.id("new_row_custombutton_temp")).click();
		Thread.sleep(3000);	
		Actions seriesOfActions3 = builder.moveToElement(editor).click().sendKeys(editor, "testing the quotes");
		seriesOfActions3.perform();
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_quantity")));
		driver.findElement(By.id("new_row_quantity")).click();
		driver.findElement(By.id("new_row_quantity")).sendKeys(Keys.ENTER);

		//adding the price line item to the quotes
		driver.findElement(By.id("addquotegridButton_temp")).click();
		driver.findElement(By.id("new_row_type")).click();
		Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
		Type3.selectByVisibleText("Price");
		driver.findElement(By.id("new_row_textbox")).sendKeys("Price....");
		driver.findElement(By.id("new_row_sellprice")).sendKeys("777" + Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.id("quoteTemplateSaveId")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("quoteTemplateCancel")).click();
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
