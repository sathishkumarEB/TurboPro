package com.turbopro.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class test2642a extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername,
	Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook;

	// accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception {
		srcBook = new HSSFWorkbook(new FileInputStream(new File("./testdata/JobInputs.xls")));
		openChromeBrowser();

		Url = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "baseURL")).toString();
		UName = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "username")).toString();
		Password = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "password")).toString();
		Jobname = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Salesrep")).toString();
		Taxterritory = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "TaxTerritory")).toString();
		Customername = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "CustomerName")).toString();
		Dropshipmanufacturer = srcBook.getSheetAt(0).getRow(1)
				.getCell(ColumnNumber(srcBook, 0, 0, "DropshipManufacturer")).toString();
		Notes = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Notes")).toString();
		Allocated = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Allocated")).toString();
		SO_Productname = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "SO_ProductName"))
				.toString();
		Quantity = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "SO_Quantity")).toString();
		Freight = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Freight")).toString();
		Pro = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Pro")).toString();
		Reason = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Reason")).toString();
		Email = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "Email")).toString();

	}

	private int ColumnNumber(HSSFWorkbook Hwb, int sheetNum, int RowCount, String ColumnHeader) throws Exception {
		int patchColumn = -1;
		for (int cn = 0; cn < Hwb.getSheetAt(sheetNum).getRow(RowCount).getLastCellNum(); cn++) {
			Cell c = Hwb.getSheetAt(sheetNum).getRow(RowCount).getCell(cn);
			if (c.toString() == null) {
				// Can't be this cell - it's empty
				continue;
			} else {
				String text = c.toString();
				if (ColumnHeader.equalsIgnoreCase(text)) {
					patchColumn = cn;
					break;
				}
			}
		}
		if (patchColumn == -1) {
			throw new Exception("None of the cells in the first row were Patch");
		} else
			return patchColumn;
	}

	// TP_CII_0001
	// logging into the application
	@Test(enabled = true, priority = 1)
	public void login() throws InterruptedException, Exception {
		loggingIn(Url, UName, Password);
	}

	/* TP_CII_0001 - Creating a new job*/
	@Test(enabled = true, priority = 2)
	public void createJob() throws InterruptedException, Exception {

		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername); // book a job with customer name
	}


	// TP_CII_0013
	// create partial customer invoice for stock order
	@Test(enabled = true, priority = 12)
	public void partialCIForSO() throws InterruptedException, Exception {

		releaseStockOrder(Notes, Allocated);
		addOneLineItemForStockOrder(SO_Productname, Quantity); // stock order

		SO_Productname = srcBook.getSheetAt(0).getRow(2).getCell(ColumnNumber(srcBook, 0, 0, "SO_ProductName"))
				.toString();
		Quantity = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity);

		SO_Productname = srcBook.getSheetAt(0).getRow(3).getCell(ColumnNumber(srcBook, 0, 0, "SO_ProductName"))
				.toString();
		Quantity = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity);

		SO_Productname = srcBook.getSheetAt(0).getRow(4).getCell(ColumnNumber(srcBook, 0, 0, "SO_ProductName"))
				.toString();
		Quantity = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook, 0, 0, "SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity);
		Thread.sleep(2000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("SaveLineSOReleaseID")));
		driver.findElement(By.id("SaveLineSOReleaseID")).click();
		Thread.sleep(6000);
		driver.findElement(By.id("closeLineSOReleaseID")).click();

		// first customer invoice
		Thread.sleep(4000);

		int index = 0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Stock Order']")));

		List<WebElement> releaseRows = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows.size(); temp++) {
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]"))
					.getAttribute("title").equalsIgnoreCase("Stock Order")) {
				index = temp;
			} else
				continue;
		}
		getWait().until(ExpectedConditions
				.elementToBeClickable(By.xpath("//table[@id='release']/tbody/tr[" + index + "]/td[9]")));
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + index + "]/td[9]")));
		driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + index + "]/td[9]")).click();

		Thread.sleep(3000);

		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoicebtnID")));
		driver.findElement(By.id("customerInvoicebtnID")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		Thread.sleep(6000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));

		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(12000);
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Line Items")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("canDeleteCuInvID_4")));
		driver.findElement(By.id("canDeleteCuInvID_4")).click();
		driver.findElement(By.id("canDeleteCuInvID_3")).click();
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(18000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

		// second customer invoice
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Stock Order']")));
		List<WebElement> releaseRows1 = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows1.size(); temp++) {
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]"))
					.getAttribute("title").equalsIgnoreCase("Stock Order")) {
				index = temp;
			} else
				continue;
		}
		driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Stock Order']")).click();

		Thread.sleep(3000);
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoicebtnID")));
		driver.findElement(By.id("customerInvoicebtnID")).click();
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
		driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(12000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		Thread.sleep(3000);
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		if (driver
				.findElement(By
						.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'Do You want to close the SO transaction Status?')]"))
				.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[.='Cancel']")));
			new Actions(driver)
			.click(driver.findElement(
					By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[.='Cancel']")))
			.build().perform();
		}
	}




	@AfterTest
	public void teardown() {
		//driver.quit();
	}
}
