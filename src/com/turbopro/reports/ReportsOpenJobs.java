package com.turbopro.reports;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

import net.sourceforge.htmlunit.corejs.javascript.regexp.NativeRegExp;

public class ReportsOpenJobs extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";
	private String Url, UName, Password, CustomerName, SalesRep;
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
		CustomerName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		SalesRep= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"SalesRep")).toString();
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

	/*TP_ROJ_0001 - logging into the application and navigate to credit tab*/
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateReportOpenJobs();
	}

	/*TP_ROJ_0002 -  To View Preview Reports*/
	@Test(enabled = true, priority = 2)
	public void viewPreviewReports() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@id= 'previewreports']")).click(); 
		parentWindow();  
	}

	/*TP_ROJ_0003 -  To view print reports */		
	@Test(enabled = true, priority = 3)
	public void viewPrintReports() throws InterruptedException 
	{
		driver.findElement(By.xpath("//*[@id= 'printreports']")).click();
		parentWindow();  
	}

	/*TP_ROJ_0004 -  To view preview reports with customer and sales rep*/
	@Test(enabled = false, priority = 4)
	public void viewPreviewReportsForCusAndSalesRep() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Customer1")));
		driver.findElement(By.id("Customer1")).click();
		driver.findElement(By.id("Customer1")).clear();
		driver.findElement(By.id("Customer1")).sendKeys(CustomerName);
		driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
		driver.findElement(By.id("jobnumber1")).click();
		driver.findElement(By.id("jobnumber1")).clear();
		driver.findElement(By.id("jobnumber1")).sendKeys(SalesRep);
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		driver.findElement(By.xpath("//*[@id= 'previewreports']")).click();
		parentWindow();  
	}

	/*TP_ROJ_0005 -  To view print reports with customer and sales rep*/	
	@Test(enabled = true, priority = 5)
	public void viewPrintReportsForCusAndSalesRep() throws InterruptedException 
	{
		driver.navigate().refresh();
		driver.findElement(By.id("Customer1")).click();
		driver.findElement(By.id("Customer1")).clear();
		driver.findElement(By.id("Customer1")).sendKeys(CustomerName);
		driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
		driver.findElement(By.id("jobnumber1")).click();
		driver.findElement(By.id("jobnumber1")).clear();
		driver.findElement(By.id("jobnumber1")).sendKeys(SalesRep);
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		driver.findElement(By.xpath("//*[@id= 'printreports']")).click();
		parentWindow();  
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

