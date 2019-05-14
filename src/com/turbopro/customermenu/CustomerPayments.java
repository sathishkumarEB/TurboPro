package com.turbopro.customermenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class CustomerPayments extends Methods
{

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	private String baseUrl, Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername, Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email, Vendorname;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/JobInputs.xls")));
		baseUrl=srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// Create a new proxy object and set the proxy
		Proxy proxy = new Proxy();
		proxy.setSslProxy("sysvines009.sysvine.local:3128");
		proxy.setSocksUsername("sathish_kumar");
		proxy.setSocksPassword("vine@2018");
		//Add the proxy to our capabilities 
		capabilities.setCapability("proxy", proxy);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize(); 


		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
		Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
		Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
		Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Dropshipmanufacturer= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DropshipManufacturer")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
		SO_Productname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		Freight= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString();
		Pro= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pro")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		Email= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Email")).toString();
		Vendorname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"VendorName")).toString();

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

	//logging into the application
	@Test(enabled = true, priority = 1)	
	public void CP_login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	@Test(enabled = true, priority = 2)	
	public void CP_createJob() throws InterruptedException, Exception
	{

		try{
		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername);	//book a job with customer name
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//creating dropship release 
	@Test(enabled = true, priority = 3)	
	public void CP_dropship() throws InterruptedException, Exception
	{
		try{
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); //dropship release
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//creating customer invoice for dropship 
	@Test(enabled = false, priority = 4)	
	public void CP_CIforDropship() throws InterruptedException
	{
		try{
		int index=0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));

		List<WebElement> releaseRows= driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for(int temp=2;temp<=releaseRows.size();temp++)
		{
			System.out.println( releaseRows.size() +","+ driver.findElement(By.xpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]")).getAttribute("title"));
			if(driver.findElement(By.xpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]")).getAttribute("title").equalsIgnoreCase("drop ship"))
				index=temp;
			else
				continue;
		}
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//tr["+index+"]/td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//tr["+index+"]/td[@title='Drop Ship']")).click();
		Thread.sleep(5000);
		cusInvoiceForRelease();

		navigateCustomerInvoice();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));

		cuInvoiceNumber = driver.findElement(By.xpath("//*[@id='1']/td[5]")).getText();
		System.out.println(cuInvoiceNumber);

		//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_invoiceNumberId")));
		//		cuInvoiceNumber = driver.findElement(By.id("customerInvoice_invoiceNumberId")).getText();
		//		System.out.println(cuInvoiceNumber);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}



	//navigate to customer payments
	@Test(enabled = false, priority = 6)	
	public void CP_MakeCustomerPayment() throws InterruptedException
	{
		try{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		navigateCustomerPayments();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addpaymentlist")));		
		Thread.sleep(4000);
		driver.findElement(By.id("addpaymentlist")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("payCustomer")));
		driver.findElement(By.id("payCustomer")).click();
		driver.findElement(By.id("payCustomer")).clear();
		driver.findElement(By.id("payCustomer")).sendKeys(Customername);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("payAmountId")).click();
		driver.findElement(By.id("payAmountId")).clear();
		driver.findElement(By.id("payAmountId")).sendKeys(Allocated);

		driver.findElement(By.id("paymentReciptTypeId")).click();

		Select receiptType = new Select(driver.findElement(By.id("paymentReciptTypeId")));
		receiptType.selectByVisibleText("Cash");

		driver.findElement(By.id("payCheckId")).sendKeys("232");
		driver.findElement(By.id("payMemoId")).sendKeys("Test Memo");

		//		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("//*[@id='gview_customerPaymets']/div[3]"));


		
		driver.findElement(By.xpath("//*[@id='1']/td[4]")).click();
//		jse.executeScript("arguments[0].scrollIntoView();", InvNum.get(0));

		
		Actions scrollAction = new Actions(driver);
		scrollAction.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(5000);

		List<WebElement> InvNum = driver.findElements(By.xpath("//td[@title="+cuInvoiceNumber+"]/preceding::img[1]"));
		InvNum.get(1).click();


		//List<WebElement> InvNum = driver.findElements(By.xpath("//td[@title="+cuInvoiceNumber+"]/preceding::img[1]"));

		//		List<WebElement> InvNum = driver.findElements(By.xpath("//td[@title="+cuInvoiceNumber+"]/preceding::img[1]"));
		//		int sze = InvNum.size();
		//		System.out.println("Size: " + sze);
		//		for (WebElement elt : InvNum)
		//		{
		//			for(int i=1; i<=sze; i++)
		//			{
		//				if(driver.findElement(By.xpath("//*[@id='customerPaymets']/tbody/tr["+i+"]/td[3]")).getText().contains(cuInvoiceNumber))
		//				{
		//					driver.findElement(By.xpath("//*[@id='customerPaymets']/tbody/tr["+i+"]/td[2]")).click();
		//				}
		//			}
		//			
		//		}



		/*	for (WebElement elt : InvNum)
		{
			if(cuInvoiceNumber)
		}
		 */

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	//sorting the customer payments 
	@Test(enabled = false)
	public void customerPaymentsSort() throws InterruptedException
	{
		//		Variables variableObj = new Variables();
		//		variableObj.
		navigateCustomerPayments();
		Thread.sleep(5000);
		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_customerpaymentlist_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_customer")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_reference")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_memo")).click();
			driver.findElement(By.id("jqgh_customerpaymentlist_amount")).click();
		}

		//sort inside add customer payments 
		driver.findElement(By.id("addpaymentlist")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("payCustomer")));

		driver.findElement(By.id("payCustomer")).clear();
		driver.findElement(By.id("payCustomer")).sendKeys("Credit Card Sales");
		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();
		Thread.sleep(3000);

		for(int i = 1; i<=2; i++)
		{
			driver.findElement(By.id("jqgh_customerPaymets_payBill")).click();
			driver.findElement(By.id("jqgh_customerPaymets_invoiceNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_customerPoNumber")).click();
			driver.findElement(By.id("jqgh_customerPaymets_receiptDate")).click();
			driver.findElement(By.id("jqgh_customerPaymets_invoiceBalance")).click();
			driver.findElement(By.id("jqgh_customerPaymets_preDiscountUsed")).click();
			driver.findElement(By.id("jqgh_customerPaymets_paymentApplied")).click();
			driver.findElement(By.id("jqgh_customerPaymets_remaining")).click();
			driver.findElement(By.id("jqgh_customerPaymets_datePaids")).click();
		}

		driver.findElement(By.id("cancelPaymentId")).click();
	}






	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}
