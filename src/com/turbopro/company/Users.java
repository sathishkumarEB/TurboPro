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

public class Users extends Methods{

	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Pwd, LoginName, User,  Initials,  Firstname,  Lastname;
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
		LoginName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LoginName")).toString();
		Initials= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Initials")).toString();
		Pwd= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pwd")).toString();
		User= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"User")).toString();
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

	/*TP_CU_0001 - logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateToUsers() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateUsers(); // navigate to company users
	}

	/*TP_CU_0002 - View User details*/
	@Test(enabled = true, priority = 2)	
	public void viewUserDetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//table[@id='userlist']/tbody/tr[@id='1']/td[4]");
			getxpath("//table[@id='userlist']/tbody/tr[@id='1']/td[4]").click();
			Actions doubleclick = new Actions(driver);
			doubleclick.moveToElement(getxpath("//table[@id='userlist']/tbody/tr[@id='1']/td[4]")).doubleClick().perform();
			Thread.sleep(3000);

			waitforxpath("//*[@id='userpermissionDetailsDiv']/a");
			getxpath("//*[@id='userpermissionDetailsDiv']/a").click();// click permissions

			waitforxpath("//*[@id='userEmailDetailsDiv']/a");
			getxpath("//*[@id='userEmailDetailsDiv']/a").click();// click Email
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CU_0003 - Edit User details*/
	@Test(enabled = true, priority = 3)	
	public void editUserDetails() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//*[@id='userpermissionDetailsDiv']/a");
			getxpath("//*[@id='userpermissionDetailsDiv']/a").click();// click permissions

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("groupDefaultId")));
			getid("groupDefaultId").click();// select group default

			waitforxpath("//input[@onclick = 'updateUserDetails()']");
			getxpath("//input[@onclick = 'updateUserDetails()']").click();// click save&close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CU_0004 - Create new user*/
	@Test(enabled = false, priority = 4)	
	public void createNewUser() throws InterruptedException, Exception	//some issue is there here
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("adduserlist")));
			getid("adduserlist").click();// click Add

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("loginNameID")));
			getid("loginNameID").click();
			getid("loginNameID").clear();
			getid("loginNameID").sendKeys(LoginName);// Enter login name

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("firstNameID")));
			getid("firstNameID").click();
			getid("firstNameID").clear();
			getid("firstNameID").sendKeys(Firstname);// Enter first name

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("lastNameID")));
			getid("lastNameID").click();
			getid("lastNameID").clear();
			getid("lastNameID").sendKeys(Lastname);// Enter last name

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("initialsNameID")));
			getid("initialsNameID").click();
			getid("initialsNameID").clear();
			getid("initialsNameID").sendKeys(Initials);// Enter Initials

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNameID")));
			getid("passwordNameID").click();
			getid("passwordNameID").clear();
			getid("passwordNameID").sendKeys(Pwd);// Enter password

			waitforxpath("//input[@onclick = 'saveNewUser()']");
			getxpath("//input[@onclick = 'saveNewUser()']").click();// click save&close

			if(getxpath("//span[text() = 'Redirect Login Page']").isDisplayed())// Redirect login page popup appears //issue is there
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[2]").click();// click No
			}	

			waitforxpath("//input[@onclick = 'updateUserDetails()']");
			getxpath("//input[@onclick = 'updateUserDetails()']").click();// click save & close
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CU_0005 - Search user*/
	@Test(enabled = true, priority = 5)	
	public void searchUser() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
			getid("searchJob").click();
			getid("searchJob").clear();
			getid("searchJob").sendKeys(User);// Enter user name in search field

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("goSearchButtonID")));	
			getid("goSearchButtonID").click();// click Go
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CU_0006 - select active checkbox user*/
	@Test(enabled = true, priority = 6)	
	public void viewActiveUsers() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			if(!getid("activeUsersList").isSelected())//Active checkbox is not selected
			{
				getid("activeUsersList").click();// select active checkbox	
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CU_0007 - view inactive users*/
	@Test(enabled = true, priority = 7)	
	public void viewInactiveUsers() throws InterruptedException, Exception
	{
		try{
			if(getid("activeUsersList").isSelected())//Active checkbox is selected
			{
				getid("activeUsersList").click();// select active checkbox	
			}
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
