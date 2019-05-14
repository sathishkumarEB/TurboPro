package com.turbopro.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class Customers extends Methods{

	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Role, Notes, Discount, Days, Firstname,  Lastname, Address, CustomerName, CustomerName1;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Customers.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		CustomerName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		CustomerName1= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName1")).toString();
		Lastname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Lastname")).toString();
		Firstname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Firstname")).toString();
		Role= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Role")).toString();
		Address= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Address")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Discount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Discount")).toString();
		Days= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Days")).toString();
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

	/*TC_CC_0001 - logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateToCustomers() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateCustomers(); // navigate to company customers
	}

	/*TC_CC_0002 - View customer details*/
	@Test(enabled = true, priority = 2)	
	public void viewDetails() throws InterruptedException, Exception
	{
		try{

			waitforxpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]");
			getxpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]").click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closeCustomerButton")));
			getid("closeCustomerButton").click();// click close
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0003 - Add new customer*/
	@Test(enabled = true, priority = 3)	
	public void addCustomer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addCustomersButton")));
			getid("addCustomersButton").click();// click add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerName")));
			getid("customerName").click();
			getid("customerName").clear();
			getid("customerName").sendKeys(CustomerName);// Enter customer name
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'savenewcustomer()']")));
			getxpath("//input[@onclick = 'savenewcustomer()']").click();// click save&close
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

	/*TC_CC_0004 - Add contacts*/
	@Test(enabled = true, priority = 4)	
	public void addContacts() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add_EmployeeDetailsGrid']/div/span")));
			getxpath("//*[@id='add_EmployeeDetailsGrid']/div/span").click();// click add icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
			getid("lastName").click();
			getid("lastName").clear();
			getid("lastName").sendKeys(Lastname);// Enter Last name
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
			getid("firstName").click();
			getid("firstName").clear();
			getid("firstName").sendKeys(Firstname);// Enter first name
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sData")));
			getid("sData").click();// click submit
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

	/*TC_CC_0005 - Edit contacts*/
	@Test(enabled = false, priority = 5)	
	public void editContacts() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[4]")));
			getxpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[4]").click();// select a contact
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit_EmployeeDetailsGrid']/div/span")));
			getxpath("//*[@id='edit_EmployeeDetailsGrid']/div/span").click();// select edit icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobPosition")));
			getid("jobPosition").click();
			getid("jobPosition").clear();
			getid("jobPosition").sendKeys(Role);// Enter role
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sData")));
			getid("sData").click();// click submit
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0006 - Add address details*/
	@Test(enabled = true, priority = 6)	
	public void addAddressDetails() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'callRxAddressDetails()']")));
			getxpath("//input[@onclick = 'callRxAddressDetails()']").click();// select address
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'addAddressRolodexDetails()']")));
			getxpath("//input[@onclick = 'addAddressRolodexDetails()']").click();// click Add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("rolodexAddress1")));
			getid("rolodexAddress1").click();
			getid("rolodexAddress1").clear();
			getid("rolodexAddress1").sendKeys(Address);// Enter address
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'savenewRolodexAddress()']")));
			getxpath("//input[@onclick = 'savenewRolodexAddress()']").click();// click save&close
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[16]/div[1]/a/span")));
			getxpath("//body/div[16]/div[1]/a/span").click();// close customer address popup
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0007 - Add Journal entry*/
	@Test(enabled = true, priority = 7)	
	public void addJournalEntry() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='general_journal']/div/ul/li[2]/a")));
			getxpath("//*[@id='general_journal']/div/ul/li[2]/a").click();// navigate to journal tab
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add_journalDetailsGrid']/div/span")));
			getxpath("//*[@id='add_journalDetailsGrid']/div/span").click();// click Add icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("entryMemo")));
			getid("entryMemo").click();
			getid("entryMemo").clear();
			getid("entryMemo").sendKeys(Notes);// Enter journal entry
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id = 'TblGrid_journalDetailsGrid_2']//td/a[@id='sData']")));
			getxpath("//table[@id = 'TblGrid_journalDetailsGrid_2']//td/a[@id='sData']").click();// click submit
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0008 - Add discount terms for the customer*/
	@Test(enabled = true, priority = 8)	
	public void addDiscountTerms() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("link2")));
			getid("link2").click();// navigate to financial tab
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerTermDiscpercent")));
			getid("customerTermDiscpercent").click();
			getid("customerTermDiscpercent").clear();
			getid("customerTermDiscpercent").sendKeys(Discount);// Enter discount amount
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("terms_discday")));
			getid("terms_discday").click();
			getid("terms_discday").clear();
			getid("terms_discday").sendKeys(Days);// Enter number of days
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("mainsave")));
			getid("mainsave").click();// click save
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("mainClose")));
			getid("mainClose").click();// click close
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0009 - Search for the customer using search field*/
	@Test(enabled = true, priority = 9)	
	public void searchCustomer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(CustomerName);// Enter customer name in search field
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			getid("goSearchButtonID").click();// click Go button
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().build().perform();// Select an existing entry from customer table
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closeCustomerButton")));
			getid("closeCustomerButton").click();// click close
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0010 - Reset customer table*/
	@Test(enabled = true, priority = 10)	
	public void resetCustomerTable() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resetbutton")));
			getid("resetbutton").click();// click reset button
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CC_0011 - Delete customer details*/
	@Test(enabled = true, priority = 11)	
	public void deleteCustomer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(CustomerName);// Enter customer name in search field
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			getid("goSearchButtonID").click();// click Go button
			Thread.sleep(3000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]")));
			getxpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]").click();//// Select an existing entry from customer table
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteCustomersButton")));
			getid("deleteCustomersButton").click();// click delete button
			if(driver.findElement(By.xpath("//span[text()='Confirmation']")).isDisplayed())// Confirm Delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// confirm to delete popup	
			}

			if(driver.findElement(By.xpath("//span[text()='Success ']")).isDisplayed())// Confirm Delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// CLICK ok TO Information popup	
			}
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*Make a customer as inactive*/
	@Test(enabled = true, priority = 12)	
	public void inactiveCustomer() throws InterruptedException, Exception
	{
		try{
			getid("resetbutton").click();
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));

			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(CustomerName1); //customer name - 24 Hour Air Conditioning
			getid("goSearchButtonID").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().build().perform();
			Thread.sleep(4000);

			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("inActiveForFinancialId")));

			if(!driver.findElement(By.id("inActiveForFinancialId")).isSelected())
			{
				getid("inActiveForFinancialId").click();
			}

			getid("mainsave").click();

			Thread.sleep(3000);

			//		if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			//		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			//		}

			getid("mainClose").click();
			Thread.sleep(4000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

	/*Checking the visibility of customer name in different places in application*/
	@Test(enabled = false, priority = 13)	
	public void checkVisbilityOfCustomer() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='advancedSearchbutton']")));
			getxpath("//input[@class='advancedSearchbutton']").click();

			getid("customerId").click();
			getid("customerId").sendKeys(CustomerName1); //checking visibility of customer in advanced search
			Thread.sleep(2000);
			getxpath("//input[@onclick='cancelJOB()']").click();


			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().build().perform();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobHeader_JobCustomerName_id")));

			getid("jobHeader_JobCustomerName_id").click();
			getid("jobHeader_JobCustomerName_id").clear();
			getid("jobHeader_JobCustomerName_id").sendKeys(CustomerName1);  //checking visibility of customer in job main tab
			Thread.sleep(3000);

			getlinktext("Home").click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@onclick='QuickbookPopup()']")));
			getxpath("//a[@onclick='QuickbookPopup()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}


	}



	/*Make a inactive customer as active*/
	@Test(enabled = true, priority = 14)	
	public void activeCustomer() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
			getid("resetbutton").click();
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));


			getid("searchJob").click();
			getid("searchJob").sendKeys(CustomerName1); //customer name - 3650 Shire LLC
			getid("goSearchButtonID").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().build().perform();
			Thread.sleep(4000);

			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("inActiveForFinancialId")));

			if(driver.findElement(By.id("inActiveForFinancialId")).isSelected())
			{
				getid("inActiveForFinancialId").click();
			}

			getid("mainsave").click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			}

			getid("mainClose").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*RID 977 - Updating Accepts cc in the financials tab of customer and saving it*/
	@Test(enabled = true, priority = 15)	
	public void updateAcceptsCC() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(CustomerName1);// Enter customer name in search field
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			getid("goSearchButtonID").click();// click Go button
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().build().perform();// Select an existing entry from customer table
			Thread.sleep(4000);

			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("doesNotAcceptCC")));

			if(driver.findElement(By.id("doesNotAcceptCC")).isSelected())
			{
				getid("doesAcceptCC").click();
			}

			getid("mainsave").click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			}

			driver.navigate().refresh();

			Thread.sleep(4000);

			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("doesNotAcceptCC")));


			if(driver.findElement(By.id("doesAcceptCC")).isSelected())
			{
				getid("doesNotAcceptCC").click();
			}

			getid("mainsave").click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			}

			Thread.sleep(2000);

			getid("mainClose").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}	


	/*RID 1001 - Updating ACH Authorization Form in the financials tab of customer and saving it*/
	@Test(enabled = true, priority = 16)	
	public void updateACHAuthorizationForm() throws InterruptedException, Exception  //need to update code here
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(CustomerName1);// Enter customer name in search field
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));
			getid("goSearchButtonID").click();// click Go button
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//table[@id='customersGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().build().perform();// Select an existing entry from customer table
			Thread.sleep(4000);

			driver.findElement(By.linkText("Financial")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("doesNotachAuthForm")));

			if(driver.findElement(By.id("doesNotachAuthForm")).isSelected())
			{
				getid("doesachAuthForm").click();
			}

			getid("mainsave").click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			}

			driver.navigate().refresh();

			Thread.sleep(4000);

			getlinktext("Financial").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("doesNotachAuthForm")));


			if(driver.findElement(By.id("doesachAuthForm")).isSelected())
			{
				getid("doesNotachAuthForm").click();
			}

			getid("mainsave").click();

			if(driver.findElement(By.xpath("//span[text()='Success']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click(); // Click OK button in Success popup
			}

			Thread.sleep(2000);

			getid("mainClose").click();

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
