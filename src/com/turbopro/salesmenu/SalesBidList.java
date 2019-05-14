package com.turbopro.salesmenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesBidList extends Methods {
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


	/*TP_SBL_0001 - logging into the application and navigate to credit tab*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	/*TP_SBL_0002Navigate to sales and view Bid list*/
	@Test(enabled = true, priority = 2)
	public void viewBidList() throws InterruptedException 
	{
		navigateSales();
		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(getxpath(".//*[@id='1']/td[2]")).doubleClick().build().perform();
		getxpath("//*[@id='cancelBLButton']").click();
	}

	/*TP_SBL_0003 - Navigate to sales and view PDF*/
	@Test(enabled = true, priority =3)
	public void viewPDF() throws InterruptedException 
	{
		navigateSales();
		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(getxpath(".//*[@id='1']/td[2]")).doubleClick().build().perform();
		getxpath(".//*[@id='viewBLButton']").click();
		getxpath("//span[contains(.,'OK')]").click();
		getxpath(".//*[@id='bidListFromDate']").clear();
		getxpath(".//*[@id='bidListFromDate']").sendKeys("01/01/2017");
		getxpath(".//*[@id='bidListToDate']").sendKeys(Keys.chord(Keys.CONTROL, "a"), "02/02/2017");
		getxpath("//*[@id='viewBLButton']").click();		
		String parentWindow = driver.getWindowHandle();
		java.util.Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}
		getxpath("//*[@id='cancelBLButton']").click();
	}


	/*TP_SBL_0004 - Close Bid List popup*/
	@Test(enabled = true, priority =4)
	public void closeBidList() throws InterruptedException 
	{
		getxpath(".//*[@id='mainMenuSalesPage']/a").click();
		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(getxpath(".//*[@id='1']/td[2]")).doubleClick().build().perform();		
		getxpath("//*[@id='cancelBLButton']").click();
	}


	/*View Bid List pdf with page separator for each date - RID 926*/
	@Test(enabled = true, priority =5)
	public void viewBidListWithPageBreak() throws InterruptedException 
	{
		Actions doubleclick = new Actions(driver);
		doubleclick.moveToElement(getxpath("//td[@title='Bid List']")).doubleClick().build().perform();

		if(getxpath("//span[text()='Bid List']").isDisplayed())
		{

			//			driver.findElement(By.id("bidListFromDate")).sendKeys("06/01/2018");
			getid("bidListFromDate").click();
			getlinktext("1").click();

			//			driver.findElement(By.id("bidListToDate")).sendKeys("06/20/2018");
			getid("bidListToDate").click();
			getlinktext("20").click();


			getid("blIncludeEngineer").click();
			getid("blIncludeJobBid").click();
			getid("blGroupByDiv").click();
			getid("blIncludeAE").click();
			getid("blIncludeCosting").click();
			getid("blIncludeSubmitting").click();
			getid("blIncludeTakeOff").click();
			getid("blIncludeQuoteBy").click();
			getid("blIncludeStartNewPage").click();

			getid("viewBLButton").click();
			Thread.sleep(6000);
			parentWindow();
		}
	}

	/*View Bid List pdf without page separator for each date - RID 926*/
	@Test(enabled = true, priority =6)
	public void viewBidListWithoutPageBreak() throws InterruptedException 
	{
		Thread.sleep(5000);
		getid("blIncludeStartNewPage").click();
		Thread.sleep(2000);
		getid("viewBLButton").click();
		Thread.sleep(6000);
		parentWindow();

	}


	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

