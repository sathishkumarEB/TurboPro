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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesFilter extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";
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

	/*logging into the application and navigate to credit tab*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	/*TP_SF_0001 - To select Filter and view list of Sales rep*/
	@Test(enabled = true, priority = 2)
	public void viewSalesRepEntries() throws InterruptedException 
	{
		getxpath(".//*[@id='mainMenuSalesPage']/a").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'SalesRepComboList']")));
		Actions act = new Actions(driver);
		act.moveToElement(getxpath("//*[@id= 'SalesRepComboList']")).doubleClick().build().perform();
	}

	/*TP_SF_0002 - Select specific Sales Rep*/
	@Test(enabled = true, priority = 3)
	public void selectSalesRepEntry() throws InterruptedException 
	{
		getxpath(".//*[@id='mainMenuSalesPage']/a").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'SalesRepComboList']")));
		Actions act = new Actions(driver);
		act.moveToElement(getxpath("//*[@id= 'SalesRepComboList']")).click().build().perform();
		Select drpSalesRep = new Select(getid("SalesRepComboList"));
		drpSalesRep.selectByVisibleText("Chad Stevens");
		getxpath("//*[@id= 'clearCustomer']").click();
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}
