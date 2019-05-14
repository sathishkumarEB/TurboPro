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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class Rolodex extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Role, RolodexName, Notes, Lastname, Address;
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
		RolodexName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"RolodexName")).toString();
		Lastname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Lastname")).toString();
		Role= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Role")).toString();
		Address= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Address")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
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

	/*TP_CR_0001 - logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateToRolodex() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateRolodex(); // navigate to compant rolodex
	}

	/*TP_CR_0002 - Select category*/
	@Test(enabled = true, priority = 2)	
	public void selectCategory() throws InterruptedException, Exception
	{
		try{
			if(!getid("customercheckbox").isSelected())//customer checkbox is not selected
			{
				getid("customercheckbox").click();// select customer
			}

			if(!getid("vendorcheckbox").isSelected())//vendor checkbox is not selected
			{
				getid("vendorcheckbox").click();// select vendor
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0003 - View Rolodex details*/
	@Test(enabled = true, priority = 3)	
	public void viewRolodex() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='rolodexList']/tbody/tr[@id='4']/td[3]");
			getxpath("//table[@id='rolodexList']/tbody/tr[@id='4']/td[3]").click();
			Actions doubleclick = new Actions(driver);
			doubleclick.moveToElement(getxpath("//table[@id='rolodexList']/tbody/tr[@id='4']/td[3]")).doubleClick().perform();
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0004 - Add new rolodex*/
	@Test(enabled = true, priority = 4)	
	public void addRolodex() throws InterruptedException, Exception
	{
		try{
			navigateRolodex(); // navigate to rolodex
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addvendorlist")));
			getid("addvendorlist").click();// click Add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("rolodexID")));
			getid("rolodexID").click();
			getid("rolodexID").clear();
			getid("rolodexID").sendKeys(RolodexName);// Enter login name

			if(!getxpath("//input[@id = 'customerCheckID']").isSelected())//customer check box is not selected
			{
				getxpath("//input[@id = 'customerCheckID']").click();// select customer
			}

			waitforxpath("//input[@onclick = 'saveNewRolodex()']");
			getxpath("//input[@onclick = 'saveNewRolodex()']").click();// click save&close
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0005 - Add contact*/
	@Test(enabled = true, priority = 5)	
	public void addContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@onclick = 'addRolodexContacts()']");
			getxpath("//input[@onclick = 'addRolodexContacts()']").click();// click Add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
			getid("lastName").click();
			getid("lastName").clear();
			getid("lastName").sendKeys(Lastname);// Enter last name
			waitforxpath("//input[@onclick = 'submitContact()']");
			getxpath("//input[@onclick = 'submitContact()']").click();// click submit
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0006 - Edit contact*/
	@Test(enabled = true, priority = 6)	
	public void editContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='VendorDetailsGrid']/tbody/tr[@id='1']/td[3]");
			getxpath("//table[@id='VendorDetailsGrid']/tbody/tr[@id='1']/td[3]").click(); // select a contact
			waitforxpath("//input[@onclick = 'editRolodexContacts()']");
			getxpath("//input[@onclick = 'editRolodexContacts()']").click();// click Edit
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jobPosition")));
			getid("jobPosition").click();
			getid("jobPosition").clear();
			getid("jobPosition").sendKeys(Role);// Enter role
			waitforxpath("//input[@onclick='submitContact()']");
			getxpath("//input[@onclick='submitContact()']").click();// click submit

			if(getxpath("//span[text()='Information']").isDisplayed())//Information popup displayed
			{
				getxpath("//span[text()='OK']").click();// select ok
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0007 - Delete contact*/
	@Test(enabled = true, priority = 7)	
	public void deleteContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='VendorDetailsGrid']/tbody/tr[@id='1']/td[3]");
			getxpath("//table[@id='VendorDetailsGrid']/tbody/tr[@id='1']/td[3]").click(); // select a contact
			waitforxpath("//input[@onclick = 'deleteRolodexContact()']");
			getxpath("//input[@onclick = 'deleteRolodexContact()']").click();// click Delete

			if(getxpath("//span[text()='Confirm Delete']").isDisplayed())//confirm delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// select submit
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0008 - Add address to Rolodex*/
	@Test(enabled = true, priority = 8)	
	public void addAddress() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@onclick = 'callRxAddressDetails()']");
			getxpath("//input[@onclick = 'callRxAddressDetails()']").click(); // click Address
			waitforxpath("//input[@onclick = 'addAddressRolodexDetails()']");
			getxpath("//input[@onclick = 'addAddressRolodexDetails()']").click();// click Add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("rolodexAddress1")));
			getid("rolodexAddress1").click();
			getid("rolodexAddress1").clear();
			getid("rolodexAddress1").sendKeys(Address);// Enter address
			waitforxpath("//input[@onclick = 'savenewRolodexAddress()']");
			getxpath("//input[@onclick = 'savenewRolodexAddress()']").click();// click save&close
			waitforxpath("//input[@onclick = 'cancelAddressRolodexDetails()']");
			getxpath("//input[@onclick = 'cancelAddressRolodexDetails()']").click();// click cancel
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0009 - Add journal entry*/
	@Test(enabled = true, priority = 9)	
	public void addJournal() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Journal")));
			getlinktext("Journal").click(); // navigate to Journal
			waitforxpath("//*[@id='add_journalDetailsGrid']/div/span");
			getxpath("//*[@id='add_journalDetailsGrid']/div/span").click();// click Add icon
			waitforxpath("//textarea[@id='entryMemo']");
			getxpath("//textarea[@id='entryMemo']").click();
			getxpath("//textarea[@id='entryMemo']").clear();
			getxpath("//textarea[@id='entryMemo']").sendKeys(Notes);// Enter notes in Journal
			waitforxpath("//a[@id='sData']");
			getxpath("//a[@id='sData']").click();// click submit
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0010 - Edit journal entry*/
	@Test(enabled = true, priority = 10)	
	public void editJournal() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='journalDetailsGrid']/tbody/tr[@id='1']/td[1]");
			getxpath("//table[@id='journalDetailsGrid']/tbody/tr[@id='1']/td[1]").click();// select first entry
			waitforxpath("//*[@id='edit_journalDetailsGrid']/div/span");
			getxpath("//*[@id='edit_journalDetailsGrid']/div/span").click();// click edit icon
			waitforxpath("//textarea[@id='entryMemo']");
			getxpath("//textarea[@id='entryMemo']").click();
			getxpath("//textarea[@id='entryMemo']").clear();
			getxpath("//textarea[@id='entryMemo']").sendKeys(RolodexName);// edit notes in Journal
			waitforxpath("//a[@id='sData']");
			getxpath("//a[@id='sData']").click();// click submit
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CR_0011 - Delete rolodex entry*/
	@Test(enabled = true, priority = 11)	
	public void deleteRolodex() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='rolodexdetailstab']/ul/li[1]/a");
			getxpath("//*[@id='rolodexdetailstab']/ul/li[1]/a").click();// navigate to contacts
			waitforxpath("//input[@id='employeeche']");
			getxpath("//input[@id='employeeche']").click();// select employee checkbox
			navigateEmployees(); //navigate to employees
			waitforxpath("//td[@title='*Test']");
			getxpath("//td[@title='*Test']").click();
			Actions doubleclick = new Actions(driver);
			doubleclick.moveToElement(getxpath("//td[@title='*Test']")).doubleClick().perform();// select specific employee
			Thread.sleep(3000);
			waitforxpath("//input[@onclick = 'deleteEmployee()']");
			getxpath("//input[@onclick = 'deleteEmployee()']").click();// click delete employee

			if(getxpath("//span[text()='Confirm Delete']").isDisplayed())//confirm delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// select submit
			}

			if(getxpath("//span[text()='Message']").isDisplayed()) // Message popup is displayed
			{
				getxpath("//span[text()='OK']").click();// select ok
			}
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
