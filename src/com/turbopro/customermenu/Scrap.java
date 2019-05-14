package com.turbopro.customermenu;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class Scrap extends Methods {

	//	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();

	private String cuInvoiceNumber, Url, UName, Password, ProductNo, Taxterritory, Customername, Quantity, Freight;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/SalesOrderInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Customername")).toString();
		ProductNo= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"ProductNo")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Taxterritory = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		//		ProductNo = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"ProductNo")).toString();
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



	//TP_CI_0001 - logging into the application
	@Test(enabled = true, priority = 1)
	public void customerInvoices() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateCustomerInvoice();

		//		driver.findElement(By.linkText("Home")).click();
		//
		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[3]")));
		//		Actions recentjob = new Actions(driver);
		//		recentjob.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[3]"))).doubleClick().perform();
		//		
		//		driver.findElement(By.xpath("//input[@value='Split Commission']")).click();
		//		if(driver.findElement(By.id("ui-dialog-title-openSplitcommDia")).isDisplayed())
		//		{
		//			driver.findElement(By.xpath("//td[@id='CommissionSplitsGrid_iladd']/div")).click();
		//			driver.findElement(By.id("new_row_rep")).sendKeys("Mark Bell");
		//			driver.findElement(By.xpath("//a[contains (.,'Mark Bell')]")).click();
		//			
		////			driver.findElement(By.xpath("//body/ul[32]/li/a")).click();
		//			driver.findElement(By.id("new_row_allocated")).click();
		//			driver.findElement(By.id("new_row_allocated")).clear();
		//			driver.findElement(By.id("new_row_allocated")).sendKeys("100");
		//			driver.findElement(By.id("new_row_splittype")).click();
		//			driver.findElement(By.id("new_row_splittype")).clear();
		//			driver.findElement(By.id("new_row_splittype")).sendKeys("Estimating");
		//			driver.findElement(By.xpath("//a[contains (.,'Estimating')]")).click();
		////			driver.findElement(By.xpath("//body/ul[33]/li/a")).click();
		//			driver.findElement(By.xpath("//td[@id='CommissionSplitsGrid_ilsave']/div")).click();
		//			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();
		//		}
	}		

	@Test(enabled = false, priority = 2)
	public void createCustomerInvoice() throws InterruptedException
	{

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewInvoice()']")));
		driver.findElement(By.xpath("//input[@onclick='addNewInvoice()']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New Invoice']")));
		driver.findElement(By.xpath("//span[text()='New Invoice']")).click();

		//adding customer name and tax territory to save
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_customerInvoiceID")));
		driver.findElement(By.id("customerInvoice_customerInvoiceID")).clear();
		driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Customername);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[24]/li/a")));
		driver.findElement(By.xpath("//body/ul[24]/li/a")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_TaxTerritory")));
		driver.findElement(By.id("customerInvoice_TaxTerritory")).click();
		driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
		driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Taxterritory);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[22]/li/a")));
		driver.findElement(By.xpath("//body/ul[22]/li/a")).click();
		driver.findElement(By.id("customerInvoice_frightIDcu")).clear();
		driver.findElement(By.id("customerInvoice_frightIDcu")).sendKeys(Freight);
		driver.findElement(By.id("CuInvoiceSaveID")).click();

		//adding line items
		Thread.sleep(20000);
		driver.findElement(By.xpath("//a[@href='#soreleaselineitem']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
		driver.findElement(By.id("new_row_itemCode")).click();
		driver.findElement(By.id("new_row_itemCode")).clear();
		driver.findElement(By.id("new_row_itemCode")).sendKeys("*");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
		driver.findElement(By.xpath("//body/ul[26]/li/a")).click();

		driver.findElement(By.id("new_row_unitCost")).click();
		driver.findElement(By.id("new_row_unitCost")).clear();
		driver.findElement(By.id("new_row_unitCost")).sendKeys("3");

		driver.findElement(By.id("new_row_quantityBilled")).click();
		driver.findElement(By.id("new_row_quantityBilled")).clear();
		driver.findElement(By.id("new_row_quantityBilled")).sendKeys(Quantity + Keys.ENTER);
		Thread.sleep(3000);

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(10000);

		//close customer invoice - fetching customer invoice number
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
		cuInvoiceNumber = driver.findElement(By.xpath("//*[@id='1']/td[5]")).getText();
		System.out.println(cuInvoiceNumber);
		Thread.sleep(10000);
	}

	/*Try to edit the customer invoice after its paid - RID 981*/
	@Test(enabled = false, priority = 3)
	public void editCusInvoiceAfterPayment() throws InterruptedException
	{
		navigateCustomerPayments();

		driver.findElement(By.id("addpaymentlist")).click();

		driver.findElement(By.id("payCustomer")).click();
		driver.findElement(By.id("payCustomer")).sendKeys(Customername);
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

		Thread.sleep(5000);

		driver.findElement(By.id("payAmountId")).click();
		driver.findElement(By.id("payAmountId")).clear();
		driver.findElement(By.id("payAmountId")).sendKeys("100");

		Select type = new Select(driver.findElement(By.id("paymentReciptTypeId")));
		type.selectByVisibleText("Other");

		driver.findElement(By.id("payCheckId")).sendKeys("003");
		driver.findElement(By.id("payMemoId")).sendKeys("Dummy Memo");

		WebElement table = driver.findElement(By.id("customerPaymets"));
		List<WebElement> row = table.findElements(By.tagName("tr"));
		System.out.println("Total Number of Rows = " + row.size());
		int a = row.size(); a = a-1;
		System.out.println("reduced row number" + a);
		driver.findElement(By.xpath("//*[@id='"+a+"']/td[2]/img")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("saveInvoiceId")).click();

		driver.navigate().refresh();

		navigateCustomerInvoice();

		driver.findElement(By.id("searchJob")).sendKeys(cuInvoiceNumber);
		driver.findElement(By.id("goSearchButtonID")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='"+cuInvoiceNumber+"']")));
		Actions dblclk = new Actions (driver);
		dblclk.moveToElement(driver.findElement(By.xpath("//td[@title='"+cuInvoiceNumber+"']"))).doubleClick().perform();
		Thread.sleep(4000);
		if(!driver.findElement(By.id("CuInvoiceSaveID")).isEnabled())
		{
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		}

	}






	//TP_CI_0002 - creating a new customer invoice 
	@Test(enabled = true, priority = 2)
	public void createCustomerInvoiceWithOnlyFreight() throws InterruptedException
	{

		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick='addNewInvoice()']")));
			getxpath(addNewInvoice_CustInv).click();
			driver.findElement(By.xpath("//span[text()='New Invoice']")).click();


			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New Invoice']")));


			//adding customer name and tax territory to save
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_customerInvoiceID")));
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).clear();
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Customername);
			Thread.sleep(3000);
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(Keys.RETURN);

			//				getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[24]/li/a")));
			//				driver.findElement(By.xpath("//body/ul[24]/li/a")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_TaxTerritory")));
			driver.findElement(By.id("customerInvoice_TaxTerritory")).click();
			driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Taxterritory);
			Thread.sleep(2000);
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(Keys.RETURN);

			String taxPercentage = driver.findElement(By.id("customerInvoice_generaltaxId")).getText();
			System.out.println(taxPercentage);

			driver.findElement(By.id("customerInvoice_frightIDcu")).clear();
			driver.findElement(By.id("customerInvoice_frightIDcu")).sendKeys(Freight);

			String freighttax = driver.findElement(By.id("customerInvoice_taxIdcu")).getText();
			System.out.println(freighttax);
			
//			System.out.println(freighttax.replaceAll("\\$,", ""));




//			BigDecimal Freight1 = new BigDecimal(Freight);
//			BigDecimal TaxRate = new BigDecimal(taxPercentage);
//			BigDecimal Hundred = new BigDecimal(100);
//
//			BigDecimal Expected =  new BigDecimal(0);
//			Expected = (Freight1.multiply(TaxRate)).divide(Hundred);
//			System.out.println(Expected);
//			
//			if(freighttax.equals(Expected))
//			{
//				System.out.println("Freight tax calculating properly");
//			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}





	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
