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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class Employees extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Firstname,  Lastname, Employeename;
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
		Employeename= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Employeename")).toString();
		Lastname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Lastname")).toString();
		Firstname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Firstname")).toString();
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

	/*TC_CE_0001 - logging into the application and navigate to Employees*/
	@Test(enabled = true, priority = 1)	
	public void navigateToEmployees() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateEmployees(); // navigate to company employees
	}

	/*TC_CE_0002 - View Employee details*/
	@Test(enabled = true, priority = 2)	
	public void viewEmployee() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='EmployeesListGrid']/tbody/tr[@id='3']/td[3]");
			getxpath("//table[@id='EmployeesListGrid']/tbody/tr[@id='3']/td[3]").click();
			Actions doubleclick = new Actions(driver);
			doubleclick.moveToElement(driver.findElement(By.xpath("//table[@id='EmployeesListGrid']/tbody/tr[@id='3']/td[3]"))).doubleClick().perform();
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TC_CE_0003 - Add new Employee*/
	@Test(enabled = true, priority = 3)	
	public void addEmployee() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(3000);
			navigateEmployees(); // navigate to company employees
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addEmployeeButton")));
			getid("addEmployeeButton").click();// click add
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("employeeID")));
			getid("employeeID").click();
			getid("employeeID").clear();
			getid("employeeID").sendKeys(Employeename);// Enter customer name
			waitforxpath("//input[@onclick = 'saveNewEmployee()']");
			getxpath("//input[@onclick = 'saveNewEmployee()']").click();// click save&close	
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0004 - Add contact*/
	@Test(enabled = true, priority = 4)	
	public void addContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//input[@onclick = 'addEngineerContacts()']");
			getxpath("//input[@onclick = 'addEngineerContacts()']").click();// click add
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

	/*TC_CE_0005 - Edit contact*/
	@Test(enabled = true, priority = 5)	
	public void editContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[3]");
			getxpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[3]").click();// select an existing entry
			waitforxpath("//input[@value = ' Edit ']");
			getxpath("//input[@value = ' Edit ']").click();// click Edit button
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
			getid("firstName").click();
			getid("firstName").clear();
			getid("firstName").sendKeys(Firstname);// Enter first name
			waitforxpath("//input[@value = 'Submit']");
			getxpath("//input[@value = 'Submit']").click();// click submit	

			if(driver.findElement(By.xpath("//span[@id = 'ui-dialog-title-msgDlg']")).isDisplayed())// Information popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// click ok to Information popup
			}	
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0006 - Delete contact*/
	@Test(enabled = true, priority = 6)	
	public void deleteContact() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[3]");
			getxpath("//table[@id='EmployeeDetailsGrid']/tbody/tr[@id='1']/td[3]").click();// select an existing entry
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));
			getid("delete").click();// click delete button

			if(driver.findElement(By.xpath("//span[@id = 'ui-dialog-title-1']")).isDisplayed())// Confirm delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// click submit	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0007 - Select categories*/
	@Test(enabled = true, priority = 7)	
	public void selectCategories() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerchek")));
			getid("customerchek").click();// select customer checkbox
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0008 - Provide Employee details*/
	@Test(enabled = true, priority = 8)	
	public void provideEmployeeDetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='employee']/a");
			getxpath("//*[@id='employee']/a").click();// navigate to employees
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("empGenderID")));
			Select endingcheck = new Select(driver.findElement(By.id("empGenderID")));
			Thread.sleep(2000);
			endingcheck.selectByVisibleText("Male");// Select gender
			waitforxpath("//input[@onclick = 'submitCommisionsGeneral()']");
			getxpath("//input[@onclick = 'submitCommisionsGeneral()']").click();// click save
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0009 - Delete Employee details*/
	@Test(enabled = true, priority = 9)	
	public void deleteEmployeeDetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='viewEmployee']/div/ul/li[1]/a");
			getxpath("//*[@id='viewEmployee']/div/ul/li[1]/a").click();// navigate to contacts
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteEmployeeID")));
			getid("deleteEmployeeID").click();// click delete employee

			if(driver.findElement(By.id("ui-dialog-title-2")).isDisplayed())// Confirm Delete popup is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// click submit	
			}

			if(driver.findElement(By.xpath("//span[text() = 'Message']")).isDisplayed())// Message is displayed
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();// click ok	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0010 - View Active Employees*/
	@Test(enabled = true, priority = 10)	
	public void viewActiveEmployees() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("activeEmployeesList")));
			getid("activeEmployeesList").click();// select active checkbox
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_CE_0011 - View InActive Employees*/
	@Test(enabled = true, priority = 11)	
	public void viewInactiveEmployees() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("activeEmployeesList")));
			getid("activeEmployeesList").click();// select active checkbox
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
	}

}
