package com.turbopro.salesmenu;

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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesPendingJobs extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;


	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
	}

	/*To access Excel sheet*/
	private int ColumnNumber(HSSFWorkbook Hwb,int sheetNum, int RowCount,String ColumnHeader) throws Exception
	{			
		int patchColumn = -1;
		for (int cn=0; cn<Hwb.getSheetAt(sheetNum).getRow(RowCount).getLastCellNum(); cn++) {
			Cell c = Hwb.getSheetAt(sheetNum).getRow(RowCount).getCell(cn);
			if (c.toString() == null) {
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

	/*TP_SPJ_0001 - logging into the application and navigate to credit tab*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	/*TP_SPJ_0002 -  View Pending jobs*/
	@Test(enabled = true, priority = 2)
	public void viewPendingJobs() throws InterruptedException 
	{
		navigateSales();
		getxpath("//input[@onclick='OpenPendingBidsgridDialog()']").click();
		getid("myPendingCloseIcon").click();
	}

	/*TP_SPJ_0003 - To sort quoted jobs*/ 
	@Test(enabled = true, priority = 3)
	public void sortPendingJobs() throws InterruptedException 
	{
		navigateSales();
		getxpath("//input[@onclick='OpenPendingBidsgridDialog()']").click();
		getid("jqgh_PendingQuoteGridDialog_bidDate").click();
		getid("myPendingCloseIcon").click();
	}

	/*TP_SPJ_0004 -  To select and reselect quoted jobs columns*/
	@Test(enabled = true, priority = 4)
	public void viewColumnsInPendingJobs() throws InterruptedException 
	{
		navigateSales();
		getxpath("//input[@onclick='OpenPendingBidsgridDialog()']").click();
		for (int i=1; i<=2; i++)
		{
			getxpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[4]/input").click();
			getxpath("//*[@id='PendingJobsFooter']/table/tbody/tr/td[5]/input").click();
		}
		getid("myPendingCloseIcon").click();
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

