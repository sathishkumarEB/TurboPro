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

public class SalesUpcomingBids extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, JobName;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"password")).toString();
		JobName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"JobName")).toString();
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

	/*TP_SUB_0001 - logging into the application and navigate to credit tab*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	/*TP_SUB_0002 -  Open upcoming Bids */
	@Test(enabled = true , priority = 2)
	public void upcomingBidsOpen() throws InterruptedException 
	{
		navigateSales();
		getxpath("//tbody/tr[2]/td[1]/li/div/span/input").click();
		getxpath("//*[@id='myUpcomingCloseIcon']").click();
	}

	/*TP_SUB_0003 - Add job in upcoming bids	*/
	@Test(enabled = true , priority = 3)
	public void viewAddedJobInUpcomingBids() throws InterruptedException 
	{
		getxpath("//a[text()='Home']").click();
		
		getlinktext("+ New Job").click();
		getid("jobHeader_JobName_id").sendKeys(JobName);
		getid("mainsave").click();
		getxpath(".//*[@id='mainMenuSalesPage']/a").click();
		getxpath("//tbody/tr[2]/td[1]/li/div/span/input").click();
		getxpath("//*[@id='myUpcomingCloseIcon']").click();
	}

	/*TP_SUB_0004 -  Select and view columns in Upcoming bids*/
	@Test(enabled = true, priority = 4)
	public void viewUpcomingBidsByColumns() throws InterruptedException 
	{
		navigateSales();
		getxpath("//tbody/tr[2]/td[1]/li/div/span/input").click();
		for (int i=1; i<=2; i++)
		{
			getxpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[4]/input").click();
			getxpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[5]/input").click();
			getxpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[6]/input").click();
			getxpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[7]/input").click();
			getxpath("//*[@id='engineer']").click();
			getxpath("//*[@id='displayUpcomingBids']/div[2]/table/tbody/tr/td[9]/input").click();
		}
		getxpath("//*[@id='myUpcomingCloseIcon']").click();
	}

	/* TP_SUB_0005 - sort columns in Upcoming bids */
	@Test(enabled = true , priority = 5)
	public void sortUpcomingBids() throws InterruptedException 
	{
		navigateSales();
		getxpath("//tbody/tr[2]/td[1]/li/div/span/input").click();
		getid("jqgh_UpcomingBidsGridDialog_jobNo").click();
		getxpath("//*[@id='myUpcomingCloseIcon']").click();
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

