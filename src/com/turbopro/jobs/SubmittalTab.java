package com.turbopro.jobs;

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

public class SubmittalTab extends Methods{
//	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, JobName, EditedQuantity, LineItem, Quantity, Freight, SalesRep, TaxTerritory, CustomerName, Bondagent, AgentName, Allocated, soproductname, Notes, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
//		baseUrl=srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		openChromeBrowser();
		
		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		JobName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		SalesRep = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SalesRep")).toString();
		TaxTerritory= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		CustomerName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"CustomerName")).toString();
		AgentName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"AgentName")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		LineItem = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"LineItem")).toString();
		Quantity= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		soproductname= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"soproductname")).toString();
		Notes= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		EditedQuantity= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"EditedQuantity")).toString();
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


	//TP_JST_0001 - logging into the application and navigate to submittal tab 
	@Test(enabled = true, priority = 1)	
	public void financialTab() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		createNewJob(JobName, SalesRep, TaxTerritory);
		driver.findElement(By.linkText("Submittal")).click();	
	}

	//TP_JST_0002 - Add submittal details
	@Test(enabled = true, priority = 2)	
	public void addSubmittalDetails() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("sent_ID")));
		driver.findElement(By.id("sent_ID")).click();
		driver.findElement(By.id("sentdate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("11")));
		driver.findElement(By.linkText("11")).click();// select sent date

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resent_ID")));
		driver.findElement(By.id("resent_ID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("resentdate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("12")));
		driver.findElement(By.linkText("12")).click();// select Resent date

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("approved_ID")));
		driver.findElement(By.id("approved_ID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("approveddate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("15")));
		driver.findElement(By.linkText("15")).click();// select approval date

		driver.findElement(By.xpath("//*[@id= 'turbopro-button']/tbody/tr[2]/td[2]/input")).click();// click save
		Thread.sleep(2000);
	}

	//TP_JST_0003 - Add O & M manuals details
	@Test(enabled = true, priority = 3)	
	public void addOMManualDetails() throws InterruptedException, Exception
	{
		driver.findElement(By.id("manualQtyId")).click();
		driver.findElement(By.id("manualQtyId")).sendKeys(Quantity);// Enter quantity

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("requested_ID")));
		driver.findElement(By.id("requested_ID")).click();
		driver.findElement(By.id("requesteddate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("20")));
		driver.findElement(By.linkText("20")).click();// select requested date

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("OMsent_ID")));
		driver.findElement(By.id("OMsent_ID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("OMsentdate")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("20")));
		driver.findElement(By.linkText("20")).click();// select OMsent date

		driver.findElement(By.xpath("//*[@id= 'turbopro-button']/tbody/tr[2]/td[2]/input")).click();// click save
		Thread.sleep(2000);
	}

	//TP_JST_0004 - Edit Quantity and save
	@Test(enabled = true, priority = 4)	
	public void editQuantity() throws InterruptedException, Exception
	{
		driver.findElement(By.id("manualQtyId")).click();
		driver.findElement(By.id("manualQtyId")).clear();
		driver.findElement(By.id("manualQtyId")).sendKeys(EditedQuantity);// Edit Quantity

		driver.findElement(By.xpath("//*[@id= 'turbopro-button']/tbody/tr[2]/td[2]/input")).click();// click save
	}

	@AfterTest
	public void teardown() 
	{
//		driver.quit();
	}

}
