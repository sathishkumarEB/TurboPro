package com.turbopro.projectsmenu;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class ProjectsCustomerList extends Methods {

	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));
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

	//TP_PAR_0001 - logging into the application and navigate to projects 
	@Test(enabled = true, priority =1)
	public void projects() throws InterruptedException, Exception 
	{

		//		driver.get("http://qe.tt.eb.local/turbotracker/turbo/");
		//		driver.findElement(By.linkText("Login")).click();
		//		driver.findElement(By.id("uname")).sendKeys("Admin");
		//		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		//		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		getlinktext("Projects").click();
	}


	//TP_PCL_0002 - view customer list under projects 
	@Test(enabled = true, priority = 2)	
	public void viewCustomerList() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Customer List']")));
		getxpath("//input[@value='Customer List']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myCloseIcon")));
		getid("myCloseIcon").click();
	}

	//TP_PCL_0003 - view contact details of customer in the list 
	@Test(enabled = true, priority = 3)	
	public void viewContactsInCustomerList() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Customer List']")));
		getxpath("//input[@value='Customer List']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='showContacts(1)']")));
		getxpath("//input[@onclick='showContacts(1)']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("close")));
		getlinktext("close").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='showContacts(2)']")));
		getxpath("//input[@onclick='showContacts(2)']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("close")));
		getlinktext("close").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='showContacts(3)']")));
		getxpath("//input[@onclick='showContacts(3)']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("close")));
		getlinktext("close").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myCloseIcon")));
		getid("myCloseIcon").click();
	}

	//TP_PCL_0004 - download csv report for the customer list 
	@Test(enabled = true, priority = 4)	
	public void downloadCsvForCustomerList() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Customer List']")));
		getxpath("//input[@value='Customer List']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@onclick='exportCustomerContacts()']")));
		getxpath("//img[@onclick='exportCustomerContacts()']").click();	
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myCloseIcon")));
		getid("myCloseIcon").click();
	}

	//TP_PCL_0005 - view customer list for specific salesrep
	@Test(enabled = true, priority = 5)	
	public void viewCustomerListForSpecificSalesrep() throws InterruptedException, Exception
	{
		for(int i=1; i<=2; i++)
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Allison Lewis");
		}
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Customer List']")));
		getxpath("//input[@value='Customer List']").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myCloseIcon")));
		getid("myCloseIcon").click();
	}

	//TP_PCL_0006 - view customer list for all salesrep
	@Test(enabled = true, priority = 6)	
	public void viewCustomerListForAllSalesrep() throws InterruptedException, Exception
	{
		for(int i=1; i<=2; i++)
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("All");
		}
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Customer List']")));
		getxpath("//input[@value='Customer List']").click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("myCloseIcon")));
		getid("myCloseIcon").click();
	}


	@AfterTest
	public void teardown() 
	{
		driver.quit();
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

