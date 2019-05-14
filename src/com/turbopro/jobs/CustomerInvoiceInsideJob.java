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

public class CustomerInvoiceInsideJob extends Methods {

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
	@Test
	public void login() throws InterruptedException, Exception {
		loggingIn(Url, UName, Password);
	}

	/* TP_CII_0001 - Creating a new job*/
	@Test
	public void createJob() throws InterruptedException, Exception {

		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername); // book a job with customer name
	}

	/* TP_CII_0002 - Creating a dropship release */
	@Test
	public void dropship() throws InterruptedException, Exception {

		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); // dropship release
	}

	@Test
	public void dropship1() throws InterruptedException, Exception {

		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); // dropship release
	}

	@Test
	public void dropship2() throws InterruptedException, Exception {

		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); // dropship release
	}

	/* TP_CII_0003 - Creating a Stock Order release */
	@Test
	public void stockorder() throws InterruptedException, Exception {
		releaseStockOrder(Notes, Allocated);
		addLineItemsForStockorder(SO_Productname, Quantity); // stock order
		// release
	}

	/* TP_CII_0004 - Creating a Bill only release */
	@Test
	public void billonly() throws InterruptedException, Exception {
		releaseBillOnly(Notes, Allocated);
		addSplitCommission(Salesrep); // bill only release
	}

	// TP_CII_0005 - Creating a Commission release 
	@Test
	public void commission() throws InterruptedException, Exception {
		releaseCommission(Dropshipmanufacturer, Notes, Allocated); // commission
		// order
		addLineItemsForDropship(); // reusing the method which is used for
		// adding line items in dropship
	}

	// TP_CII_0006 - Creating service order release
	@Test
	public void service() throws InterruptedException, Exception {
		releaseService(Notes, Allocated);
		addLineItemsForService(); // service order
	}

	// TP_CII_0007 - creating customer invoice for dropship
	@Test
	public void CIforDropship() throws InterruptedException {
		int index = 0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows.size(); temp++) {
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]"))
					.getAttribute("title").equalsIgnoreCase("drop ship"))
				index = temp;
			else
				continue;
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",	driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")).click();
		Thread.sleep(5000);
		cusInvoiceForRelease();

	}

	//creating customer invoice for the dropship1
	@Test
	public void CIforDropship1() throws InterruptedException {
		int index = 0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows.size(); temp++) {
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]")).getAttribute("title").equalsIgnoreCase("drop ship"))index = temp;
			else
				continue;
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",	driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")).click();
		Thread.sleep(5000);
		cusInvoiceForRelease();

	}


	//creating customer invoice for dropship2
	@Test
	public void CIforDropship2() throws InterruptedException {
		int index = 0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows.size(); temp++) 
		{
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]")).getAttribute("title").equalsIgnoreCase("drop ship"))index = temp;
			else
				continue;
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")).click();
		Thread.sleep(5000);
		cusInvoiceForRelease();
	}

	//creating customer invoice for dropship3
	@Test
	public void CIforDropship3() throws InterruptedException {
		int index = 0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows = driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for (int temp = 2; temp <= releaseRows.size(); temp++) {
			if (driver.findElement(By.xpath("//table[@id='release']/tbody/tr[" + temp + "]/td[9]")).getAttribute("title").equalsIgnoreCase("drop ship"))index = temp;
			else
				continue;
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//tr[" + index + "]/td[@title='Drop Ship']")).click();
		Thread.sleep(5000);
		cusInvoiceForRelease();

	}

	// TP_CII_0008 - creating customer invoice for stock order
	@Test
	public void CIforStockorder() throws InterruptedException, Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@title='Stock Order']")));
		driver.findElement(By.xpath("//td[@title='Stock Order']")).click();
		cusInvoiceForRelease();
	}

	// TP_CII_0009 - creating customer invoice for bill only
	@Test
	public void CIforBillonly() throws InterruptedException, Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@title='Bill Only']")));
		driver.findElement(By.xpath("//td[@title='Bill Only']")).click();
		cusInvoiceForRelease();
	}

	// TP_CII_0010 - creating customer invoice for service order
	@Test
	public void CIforService() throws InterruptedException, Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@title='Service']")));
		driver.findElement(By.xpath("//td[@title='Service']")).click();
		cusInvoiceForRelease();
	}

	//Creating drip ship with multiple line items
	@Test
	public void dropshipMultipleLineitems() throws InterruptedException, Exception {
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addMultiLineItemsForDropship(); // dropship with multiple line items
	}

	// TP_CII_0017
	// Import lineitems from xml in dropship
	@Test
	public void dropshipImportXml() throws InterruptedException, Exception {
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		importXmlForDropship(); // dropship by adding line items through
		// importing xml
	}

	// TP_CII_0011 - update customer invoice which is created for dropship
	@Test
	public void updateCI() throws InterruptedException {
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		openCustomerInvoice();
		Thread.sleep(4000);
		updateCustomerInvoice(Freight, Pro, Taxterritory, Reason, Email);
	}

	// TP_CII_0019
	// update description of the existing customer invoice
	@Test
	public void updateDesc() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		openCustomerInvoice();
		driver.findElement(By.linkText("Line Items")).click();

		driver.findElement(By.xpath("//td[@title='Quoted Price']")).click();

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//td[@title='Quoted Price']"))).doubleClick().perform();
		driver.findElement(By.xpath("//td[@title='Quoted Price']")).click();
		driver.findElement(By.xpath("//td[@title='Quoted Price']")).sendKeys("test" + Keys.ENTER);

		driver.findElement(By.id("CuInvoiceSaveID")).click();
		driver.findElement(By.xpath("//div[42]//div//div[@class='ui-dialog-buttonset']//button[contains(.,'Yes')]"))
		.click();

		driver.findElement(By.id("invreasonttextid")).sendKeys("test");
		driver.findElement(By.xpath("//div[34]//div//div//button[contains(.,'OK')]")).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
	}

	// TP_CII_0012
	// update line items for existing dropship and create customer invoice
	@Test
	public void updateLineItems() throws InterruptedException, Exception {
		loggingIn(Url, UName, Password);
		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername); // book a job with customer name
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship();
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//td[@title='Drop Ship']")));
		cusInvoiceForRelease();
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		driver.findElement(By.linkText("Order")).click();
		addMultiLineItemsForDropship();
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		Thread.sleep(3000);
		cusInvoiceForRelease();
	}

	// TP_CII_0013
	// create partial customer invoice for stock order
	@Test
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

	// TP_CII_0020
	// Check the Do Not Email checkbox and view the pdf
	@Test
	public void viewPdfAfterCheckingDoNotEmail() throws InterruptedException, Exception {
		openCustomerInvoice();
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoie_doNotMailID")));

		driver.findElement(By.id("customerInvoie_doNotMailID")).click();
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		if (driver
				.findElement(By
						.xpath("//div[(contains(@style,'display: block;'))]/div[2]/span/b[contains(.,'You have made changes, would you like to save?')]"))
				.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")));
			new Actions(driver)
			.click(driver
					.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")))
			.build().perform();
		}
		driver.findElement(By.id("invreasonttextid")).sendKeys("test");
		driver.findElement(By.xpath("//div[@id='invreasondialog']/following-sibling::div[9]/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='imgInvoicePDF']/input")).click();

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				// Perform your operation here
				Thread.sleep(10000);
				driver.close();
				driver.switchTo().window(parentWindow);
				Thread.sleep(3000);
			}
		}

		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

	}

	// create customer invoice with tax enabled
	@Test(enabled = false, priority = 19)
	public void createCustomerInvoiceWithTax() throws InterruptedException, Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoicebtnID")));
		driver.findElement(By.id("customerInvoicebtnID")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		Thread.sleep(6000);

		driver.findElement(By.id("customerInvoice_TaxTerritory")).click();
		driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
		driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys("Dallas");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Dallas')]")));
		driver.findElement(By.xpath("//a[contains(.,'Dallas')]")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();

	}


	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
