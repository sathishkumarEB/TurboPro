package com.turbopro.jobs;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;
import com.turbopro.requirements.RID609;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.xpath.XPath;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class JobQuotes extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, Jobname, Salesrep, Taxterritory;
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

	//TP_JQT_0001 - logging into the application, create new job and navigate to quotes tab
	@Test(enabled = true, priority = 1)	
	public void navigateQuotesInNewJob() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		createNewJob(Jobname, Salesrep, Taxterritory);
		waitforid("jobquotesid");
		getxpath(jobQuotesTab).click();
	}


	//TP_JQT_0002 - create a bidder 
	@Test(enabled = true, priority = 2)
	public void createBidder() throws InterruptedException, Exception
	{
		try{
			getxpath(jobQuotesAddBidder).click();

			//		if(driver.findElement(By.id("ui-dialog-title-serverErrorDialogDiv")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//			driver.navigate().refresh();
			//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobquotesid")));
			//			driver.findElement(By.id("jobquotesid")).click();
			//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Bidder']")));
			//			driver.findElement(By.xpath("//input[@title='Add Bidder']")).click();
			//		}


			waitforid("ui-dialog-title-BidDialogCustom");
			if(getid("ui-dialog-title-BidDialogCustom").isDisplayed())
			{
				getxpath(jobQuotesBidder).click();
				getxpath(jobQuotesBidder).clear();
				getxpath(jobQuotesBidder).sendKeys("Cedar Valley - BAS/GDB");
				waitforxpath("//a[contains(.,'Cedar Valley - BAS/GDB')]");
				getxpath("//a[contains(.,'Cedar Valley - BAS/GDB')]").click();

				getxpath(jobQuotesContact).click();
				Select Contact = new Select(getxpath(jobQuotesContact));
				Contact.selectByVisibleText("+ Add New");

				Thread.sleep(3000);
				if(getid("contactCustom").isDisplayed())
				{
					getxpath("//*[@id='contactForm']/table/tbody/tr[2]/td[2]/input").click();
					getxpath("//*[@id='contactForm']/table/tbody/tr[2]/td[2]/input").clear();
					getxpath("//*[@id='contactForm']/table/tbody/tr[2]/td[2]/input").sendKeys("test");
					getxpath("//input[@onclick='submitContact()']").click();
				}

				Select Type = new Select(getxpath(jobQuotesType));
				Type.selectByVisibleText("MC");
				getxpath(jobQuotesSubmit).click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JQT_0003 edit the bidder from bid list 
	@Test(enabled = true, priority = 3) //false
	public void editBidder() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//td[@title='Cedar Valley - BAS/GDB']");

			getxpath("//td[@title='Cedar Valley - BAS/GDB']").click();

			getxpath("//input[@title='Edit Bidder']").click();

			if(getid("BidDialogCustom").isDisplayed())
			{
				Select Type = new Select(getxpath(jobQuotesType));
				Type.selectByVisibleText("OLD");
				getxpath(jobQuotesSubmit).click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	//TP_JQT_0004 - delete a bidder from bid list 
	@Test(enabled = true, priority = 4) //false
	public void deleteBidder() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//td[@title='Cedar Valley - BAS/GDB']");

			getxpath("//td[@title='Cedar Valley - BAS/GDB']").click();

			getxpath("//input[@title='Delete Bidder']").click();


			if(getid("ui-dialog-title-1").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JQT_0005 - create a quote using quote template
	@Test(enabled = false, priority = 5) //false
	public void createQuote() throws InterruptedException, Exception
	{
		try{
			RID609 Obj = new RID609(); 
			Obj.createQuoteFromTemplate();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JQT_0006 - edit the existing quote
	@Test(enabled = true, priority = 6) //false
	public void editQuote() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='quotes']/tbody/tr[2]/td[@title='MC']");
			getxpath("//table[@id='quotes']/tbody/tr[2]/td[@title='MC']").click();
			getxpath(jobQuotesEditQuote).click();

			//		if(driver.findElement(By.id("ui-dialog-title-serverErrorDialogDiv")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//			driver.navigate().refresh();
			//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobquotesid")));
			//			driver.findElement(By.id("jobquotesid")).click();
			//			driver.findElement(By.xpath("//table[@id='quotes']/tbody/tr[2]/td[@title='BAS']")).click(); //can do some code refrac using label and goto
			//			driver.findElement(By.xpath("//input[@title='Edit Quote']")).click(); //can do some 
			//		}

			waitforid("templateID");
			Select Template = new Select(getxpath(jobQuotesSelectTemplate));
			Template.selectByVisibleText("ARCH - Interiors");

			//		if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			//		{
			//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).click();
			//			driver.findElement(By.id("jobQuoteSubmittedBYFullName")).sendKeys("Bill Barnes");
			//			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[40]/li/a")));
			//			driver.findElement(By.xpath("//body/ul[40]/li/a")).click();
			//		}

			//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("templateID")));
			//		Template.selectByVisibleText("ARCH - Interiors");

			Thread.sleep(3000);
			getxpath(jobQuotesSaveQuoteButton).click();


			Thread.sleep(4000);
			getxpath(jobQuotesCloseQuoteButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JQT_0007 - copy the existing quote
	@Test(enabled = false, priority = 7) //false
	public void copyQuote() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='quotes']/tbody/tr[2]/td[@title='BAS']"))); //issue is here in BAS
			driver.findElement(By.xpath("//table[@id='quotes']/tbody/tr[2]/td[@title='BAS']")).click();
			driver.findElement(By.xpath("//input[@title='Copy Quote']")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("SaveQuoteButtonID")).click();

			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("quoteTypeDetail")));
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("MC");
			Thread.sleep(2000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}



	//TP_JQT_0008 - delete the created quote 
	@Test(enabled = false, priority = 8) //false
	public void deleteQuote() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='quotes']/tbody/tr[3]/td[@title='MC']"))); //issue is here in MC
			driver.findElement(By.xpath("//table[@id='quotes']/tbody/tr[3]/td[@title='MC']")).click(); //have to give string value here for title
			driver.findElement(By.xpath("//input[@title='Delete Quote']")).click();

			if(driver.findElement(By.id("ui-dialog-title-1")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	//TP_JQT_0009 - Update Amount (Estimated and Contract)
	@Test(enabled = true, priority = 9) //false
	public void updateAmount() throws InterruptedException, Exception
	{
		try{
			
			getxpath(jobQuotesContractAmount).click();
			getxpath(jobQuotesContractAmount).clear();
			getxpath(jobQuotesContractAmount).sendKeys("3000");
			getxpath(jobQuotesEstimatedCost).click();
			getxpath(jobQuotesEstimatedCost).clear();
			getxpath(jobQuotesEstimatedCost).sendKeys("2500");
			getxpath(jobQuotesAmountSave).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//TP_JQT_0010 - create bidder, quotes at same time and send email
	@Test(enabled = false, priority = 10) //false
	public void sendQuotes() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobquotesid")));
			driver.findElement(By.id("jobquotesid")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Bidder']")));

			this.createBidder();
			this.createQuote();
			this.copyQuote();
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='quotesBidlist']/tbody/tr[2]/td[@title='MC']")));
			driver.findElement(By.xpath("//table[@id='quotesBidlist']/tbody/tr[2]/td[@title='MC']")).click();

			driver.findElement(By.xpath("//input[@title='Edit Bidder']")).click();

			if(driver.findElement(By.xpath("//span[text()='Add/Edit Bidder']")).isDisplayed())
			{
				Select quotetype = new Select(driver.findElement(By.id("customer_quoteType")));
				quotetype.selectByVisibleText("BAS");
				driver.findElement(By.xpath("//input[@onclick='submitBid()']")).click();
			}

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='quotesBidlist']/tbody/tr[2]/td[@title='BAS']")));
			driver.findElement(By.xpath("//table[@id='quotesBidlist']/tbody/tr[2]/td[@title='BAS']")).click();


			driver.findElement(By.id("contactEmailID")).click(); 	//use admin credentials to login and email the quotes

			if(driver.findElement(By.id("ui-dialog-title-emailpopup")).isDisplayed())
			{
				driver.findElement(By.id("etoaddr")).click();
				driver.findElement(By.id("etoaddr")).clear();
				driver.findElement(By.id("etoaddr")).sendKeys("sathish.kumar@excelblaze.com");
				driver.findElement(By.xpath("//div[@class=' nicEdit-main ']")).click();
				driver.findElement(By.xpath("//div[@class=' nicEdit-main ']")).sendKeys("test email - please ignore");
				driver.findElement(By.xpath("//input[@onclick='submitemailattachment()']")).click();
			}

			getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingDivForPO")));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Marking a Quote as Sent RID 1017*/ 
	@Test(enabled = false, priority = 11)
	public void markQuotesAsSent() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			driver.findElement(By.id("jobquotesid")).click();
			driver.findElement(By.id("faxIcon")).click();

			Thread.sleep(3000);

			//		if(driver.findElement(By.xpath("//span[text()= 'Warning']")).isDisplayed())
			//		{
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
			//		}

			this.createBidder();	

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Cedar Valley - BAS/GDB']")));

			driver.findElement(By.xpath("//td[@title='Cedar Valley - BAS/GDB']")).click();

			driver.findElement(By.id("faxIcon")).click();
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			this.createQuote();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Cedar Valley - BAS/GDB']")));

			driver.findElement(By.xpath("//td[@title='Cedar Valley - BAS/GDB']")).click();
			driver.findElement(By.id("faxIcon")).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*Combined Source button in the Quote Tab - RID 857*/
	/*Update the source details in quotes*/
	@Test(enabled = true, priority = 12)
	public void updateSourceInQuotes() throws InterruptedException, Exception
	{
		try{
			getxpath(jobQuotesSource).click();

			if(getxpath("//span[text()='Source']").isDisplayed())
			{
				getid("bin_number").sendKeys("03");
				getid("plan_date_format").click();
				getlinktext("3").click();
				getid("plan_nuber_id").sendKeys("03");

				getid("sourceDodge").click();
				getid("sourceISqft").click();
				getid("sourceLDI").click();
				getid("sourceOther").click();

				getid("sourceReport").sendKeys("Rep3");
				getid("otherSource").sendKeys("3Othr");

				getid("received_id").click();
				getid("received_id").clear();
				getid("received_id").sendKeys("02");

				getid("quoteThru_id").click();
				getid("quoteThru_id").clear();
				getid("quoteThru_id").sendKeys("03");

				getxpath("//input[@onclick='planAndSpec()']").click();
			}

			driver.navigate().refresh();

			getid("jobquotesid").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='openSource()']")));
			getxpath("//input[@onclick='openSource()']").click();

			if(getid("sourceDodge").isSelected() && getid("sourceISqft").isSelected() 
					&& getid("sourceLDI").isSelected() && getid("sourceOther").isSelected() 
					&& getid("bin_number").getText().toString().contains("03") 
					&& getid("plan_nuber_id").getText().toString().contains("03")
					&& getid("sourceReport").getText().toString().contains("Rep3")
					&& getid("otherSource").getText().toString().contains("3Othr")
					&& getid("received_id").getText().toString().contains("02")
					&& getid("quoteThru_id").getText().toString().contains("03")
					)
			{
				System.out.println("Source details are updated successfully");
			}
			else
			{
				System.out.println("Source details are not updated successfully");
			}

			//		Actions esc = new Actions(driver);
			//		esc.sendKeys(Keys.ESCAPE);

			getxpath("//input[@onclick='planAndSpec()']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Remove the source details and update it -  RID 857*/
	@Test(enabled = false, priority = 13)
	public void removeSourceDetailsInQuotes() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			getxpath("//input[@onclick='openSource()']").click();

			if(getxpath("//span[text()='Source']").isDisplayed())
			{
				getid("bin_number").clear();
				getid("plan_date_format").click();
				getlinktext("3").click();
				getid("plan_nuber_id").clear();

				getid("sourceDodge").click();
				getid("sourceISqft").click();
				getid("sourceLDI").click();
				getid("sourceOther").click();

				getid("sourceReport").clear();
				getid("otherSource").clear();

				getid("received_id").click();
				getid("received_id").clear();

				getid("quoteThru_id").click();
				getid("quoteThru_id").clear();

				getxpath("//input[@onclick='planAndSpec()']").click();
			}

			driver.navigate().refresh();

			getid("jobquotesid").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='openSource()']")));
			getxpath("//input[@onclick='openSource()']").click();
			Thread.sleep(3000);
			if(!getid("sourceDodge").isSelected() && !getid("sourceISqft").isSelected() 
					&& !getid("sourceLDI").isSelected() && !getid("sourceOther").isSelected() 
					&& !getid("bin_number").getText().toString().contains("03") 
					&& !getid("plan_nuber_id").getText().toString().contains("03")
					&& !getid("sourceReport").getText().toString().contains("Rep3")
					&& !getid("otherSource").getText().toString().contains("3Othr")
					&& !getid("received_id").getText().toString().contains("02")
					&& !getid("quoteThru_id").getText().toString().contains("03")
					)
			{
				System.out.println("Source details are updated successfully");
			}
			else
			{
				System.out.println("Source details are not updated successfully");
			}

			//			Thread.sleep(3000);
			//			Actions esc = new Actions(driver);
			//			esc.sendKeys(Keys.ESCAPE);
			Thread.sleep(3000);
			getxpath("//input[@onclick='planAndSpec()']").click();



		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*RID 1031 - Percentage Column Inside The Quotes*/
	@Test(enabled = true, priority = 13)
	public void withPercentageColumn() throws InterruptedException, Exception
	{
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkPercentageDisplayN")));

		if(driver.findElement(By.id("chkPercentageDisplayN")).isSelected())
		{
			driver.findElement(By.id("chkPercentageDisplayY")).click();
		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);


		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(driver.findElement(By.id("jqgh_addnewquotesList_percentageValue")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("H20");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click();
			Thread.sleep(3000);
			//			driver.findElement(By.id("QuoteInlineNote")).click();
			//			driver.findElement(By.id("QuoteInlineNote")).sendKeys("Test Quote Title");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();


			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			Select Percentage = new Select(driver.findElement(By.id("new_row_percentageValue")));
			Percentage.selectByVisibleText("50");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			Select Percentage1 = new Select(driver.findElement(By.id("new_row_percentageValue")));
			Percentage1.selectByVisibleText("75");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type2 = new Select(driver.findElement(By.id("new_row_type")));
			Type2.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);




			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();


		}

	}

	/*RID 1031 - Percentage Column Inside The Quotes*/
	@Test(enabled = true, priority = 14)
	public void withoutPercentageColumn() throws InterruptedException, Exception
	{
		Thread.sleep(5000);
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkPercentageDisplayN")));

		if(driver.findElement(By.id("chkPercentageDisplayY")).isSelected())
		{
			driver.findElement(By.id("chkPercentageDisplayN")).click();
		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);


		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(!driver.findElement(By.id("jqgh_addnewquotesList_percentageValue")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("MC2");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click();
			Thread.sleep(3000);
			//			driver.findElement(By.id("QuoteInlineNote")).click();
			//			driver.findElement(By.id("QuoteInlineNote")).sendKeys("Test Quote Title");
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();


			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			//			Select Percentage = new Select(driver.findElement(By.id("new_row_percentageValue")));
			//			Percentage.selectByVisibleText("50");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			//			Select Percentage1 = new Select(driver.findElement(By.id("new_row_percentageValue")));
			//			Percentage1.selectByVisibleText("75");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type2 = new Select(driver.findElement(By.id("new_row_type")));
			Type2.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
	}


	/*RID 1030 - Lost/Sold Column Inside The Quotes*/
	@Test(enabled = true, priority = 15)
	public void withLostSoldColumn() throws InterruptedException, Exception
	{
		Thread.sleep(4000);
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkLostSoldDisplayN")));

		if(driver.findElement(By.id("chkLostSoldDisplayN")).isSelected())
		{
			driver.findElement(By.id("chkLostSoldDisplayY")).click();
		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);
		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(driver.findElement(By.id("jqgh_addnewquotesList_lostSoldValue")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("HSPC");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			Select lostsold = new Select(driver.findElement(By.id("new_row_lostSoldValue")));
			lostsold.selectByVisibleText("Lost");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type2 = new Select(driver.findElement(By.id("new_row_type")));
			Type2.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Ozarka");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Ozarka')]")).click();
			Select Category2 = new Select(driver.findElement(By.id("new_row_category")));
			Category2.selectByVisibleText("Boiler");
			Select lostsold1 = new Select(driver.findElement(By.id("new_row_lostSoldValue")));
			lostsold1.selectByVisibleText("Sold");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
			Type3.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
	}


	/*RID 1030 - Lost/Sold Column Inside The Quotes*/
	@Test(enabled = true, priority = 16)
	public void withoutlostSoldColumn() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkLostSoldDisplayN")));

		if(driver.findElement(By.id("chkLostSoldDisplayY")).isSelected())
		{
			driver.findElement(By.id("chkLostSoldDisplayN")).click();
		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));
		driver.findElement(By.id("jobquotesid")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));
		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(!driver.findElement(By.id("jqgh_addnewquotesList_lostSoldValue")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("HSPC");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type2 = new Select(driver.findElement(By.id("new_row_type")));
			Type2.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Ozarka");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Ozarka')]")).click();
			Select Category2 = new Select(driver.findElement(By.id("new_row_category")));
			Category2.selectByVisibleText("Boiler");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
			Type3.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.id("jobQuoteRevision")).sendKeys("1");

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
	}


	/*RID 1026 - Selling Type Column In Quotes*/
	@Test(enabled = true, priority = 17)
	public void withSellingTypeColumn() throws InterruptedException, Exception
	{
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkSellingTypeDisplayN")));

		if(driver.findElement(By.id("chkSellingTypeDisplayN")).isSelected())
		{
			driver.findElement(By.id("chkSellingTypeDisplayY")).click();

			driver.findElement(By.id("sellingType1")).sendKeys("Buy/Sell");
			driver.findElement(By.id("sellingType2")).sendKeys("Commission");

		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);


		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(driver.findElement(By.id("jqgh_addnewquotesList_sellingType")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("OLD");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click(); 
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			Select sellingType = new Select(driver.findElement(By.id("new_row_sellingType")));
			sellingType.selectByVisibleText("Buy/Sell");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			Select sellingType1 = new Select(driver.findElement(By.id("new_row_sellingType")));
			sellingType1.selectByVisibleText("Commission");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
			Type3.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
	}

	/*RID 1026 - Selling Type Column In Quotes*/
	@Test(enabled = true, priority = 18)
	public void withoutSellingTypeColumn() throws InterruptedException, Exception
	{
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("chkSellingTypeDisplayN")));

		if(driver.findElement(By.id("chkSellingTypeDisplayN")).isSelected())
		{
			driver.findElement(By.id("chkSellingTypeDisplayY")).click();

			driver.findElement(By.id("sellingType1")).sendKeys("Buy/Sell");
			driver.findElement(By.id("sellingType2")).sendKeys("Commission");

		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);


		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(driver.findElement(By.id("jqgh_addnewquotesList_sellingType")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("OLD");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click(); 
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			Select sellingType = new Select(driver.findElement(By.id("new_row_sellingType")));
			sellingType.selectByVisibleText("Buy/Sell");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			Select sellingType1 = new Select(driver.findElement(By.id("new_row_sellingType")));
			sellingType1.selectByVisibleText("Commission");
			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
			Type3.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);

			Thread.sleep(3000);
			driver.findElement(By.id("jobQuoteRevision")).sendKeys("1");

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}
	}


	/*RID 1059 - Project closure date column in Quotes*/
	@Test(enabled = false, priority = 19)
	public void withProjectClosureColumn() throws InterruptedException, Exception
	{

		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Job Settings")).click();

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("closureDateN")));

		if(driver.findElement(By.id("closureDateN")).isSelected())
		{
			driver.findElement(By.id("closureDateY")).click();
			//			driver.findElement(By.id("sellingType1")).sendKeys("Buy/Sell");
			//			driver.findElement(By.id("sellingType2")).sendKeys("Commission");
		}
		driver.findElement(By.xpath("//input[@onclick='saveJobSettingsSysVariable();']")).click();

		Thread.sleep(3000);


		driver.findElement(By.linkText("Home")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		Actions recentjob = new Actions(driver);
		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobName_id")));

		driver.findElement(By.id("jobquotesid")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Add Quote']")));

		driver.findElement(By.xpath("//input[@title='Add Quote']")).click();

		if(driver.findElement(By.id("jqgh_addnewquotesList_closureDate")).isDisplayed())
		{

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("SaveQuoteButtonID")));
			driver.findElement(By.id("quoteTypeDetail")).click();
			Select QuoteType = new Select(driver.findElement(By.id("quoteTypeDetail")));
			QuoteType.selectByVisibleText("OLD");

			driver.findElement(By.id("addquotegridButton")).click();
			driver.findElement(By.id("new_row_custombutton")).click(); 
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type = new Select(driver.findElement(By.id("new_row_type")));
			Type.selectByVisibleText("Item2");		
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Jam Incentive");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Jam Incentive')]")).click();
			Select Category = new Select(driver.findElement(By.id("new_row_category")));
			Category.selectByVisibleText("AHU");
			//			Select sellingType = new Select(driver.findElement(By.id("new_row_sellingType")));
			//			sellingType.selectByVisibleText("Buy/Sell");

			driver.findElement(By.id("new_row_closureDate")).click();
			driver.findElement(By.linkText("1")).click();




			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);

			Thread.sleep(2000);
			driver.findElement(By.id("addquotegridButton")).click();
			Select Type1 = new Select(driver.findElement(By.id("new_row_type")));
			Type1.selectByVisibleText("Item3");	
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("2300");
			driver.findElement(By.id("new_row_cost")).click();
			driver.findElement(By.id("new_row_cost")).sendKeys("3100");
			driver.findElement(By.id("new_row_vendorname")).click();
			driver.findElement(By.id("new_row_vendorname")).sendKeys("Banner Sales");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains (.,'Banner Sales')]")).click();
			Select Category1 = new Select(driver.findElement(By.id("new_row_category")));
			Category1.selectByVisibleText("Boiler");
			//			Select sellingType1 = new Select(driver.findElement(By.id("new_row_sellingType")));
			//			sellingType1.selectByVisibleText("Commission");

			driver.findElement(By.id("new_row_closureDate")).click();
			driver.findElement(By.linkText("2")).click();

			driver.findElement(By.id("new_row_quantity")).click();
			driver.findElement(By.id("new_row_quantity")).sendKeys("-"+ Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.id("addquotegridButton")).click();
			Select Type3 = new Select(driver.findElement(By.id("new_row_type")));
			Type3.selectByVisibleText("Price");	
			driver.findElement(By.id("new_row_textbox")).click();
			driver.findElement(By.id("new_row_textbox")).sendKeys("Price...........");
			driver.findElement(By.id("new_row_sellprice")).click();
			driver.findElement(By.id("new_row_sellprice")).sendKeys("3000"+ Keys.ENTER);

			Thread.sleep(3000);
			driver.findElement(By.id("SaveQuoteButtonID")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("CloseQuoteButtonID")).click();
		}

	}

	@Test(enabled = true, priority = 20)
	public void amountUpdate() throws InterruptedException, Exception
	{
		
		driver.findElement(By.id("contractAmount")).click();
		driver.findElement(By.id("contractAmount")).clear();
		driver.findElement(By.id("contractAmount")).sendKeys("100000");
		
		driver.findElement(By.id("estimatedCost")).click();
		driver.findElement(By.id("estimatedCost")).clear();
		driver.findElement(By.id("estimatedCost")).sendKeys("80000");
		
		driver.findElement(By.id("sellPriceCost")).click();
		driver.findElement(By.id("sellPriceCost")).clear();
		driver.findElement(By.id("sellPriceCost")).sendKeys("50000");
		
		driver.findElement(By.id("commissionCost")).click();
		driver.findElement(By.id("commissionCost")).clear();
		driver.findElement(By.id("commissionCost")).sendKeys("5000");
		
		driver.findElement(By.xpath("//input[@onclick=\"saveJobAmount()\"]")).click();
		
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
