package com.turbopro.jobs;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class CustomerInvoiceInsideJobOld extends Methods {

	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	private StringBuffer verificationErrors = new StringBuffer();

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, Jobname, Salesrep, Taxterritory, Customername, Dropshipmanufacturer, Notes, Allocated, SO_Productname, Quantity, Freight, Pro, Reason, Email;
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
		driver = new ChromeDriver();
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
	//	@Test(enabled = true, priority = 1)
	//	public void createRelease() throws InterruptedException, IOException, Exception 
	//	{
	//		String Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
	//		String UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
	//		String Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
	//		String Jobname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"jobname")).toString();
	//		String Salesrep = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Salesrep")).toString();
	//		String Taxterritory= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"TaxTerritory")).toString();
	//		String Customername= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
	//		String Dropshipmanufacturer= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DropshipManufacturer")).toString();
	//		String Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
	//		String Allocated= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Allocated")).toString();
	//		String SO_Productname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
	//		String Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();


	//TP_CII_0001
	//logging into the application
	@Test	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
	}

	//TP_CII_0001
	@Test
	public void createJob() throws InterruptedException, Exception
	{

		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername);	//book a job with customer name
	}

	//TP_CII_0002
	@Test
	public void dropship() throws InterruptedException, Exception
	{

		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship(); //dropship release
	}

	//TP_CII_0003
	@Test
	public void stockorder() throws InterruptedException, Exception
	{
		releaseStockOrder(Notes, Allocated);
		addLineItemsForStockorder(SO_Productname, Quantity); //stock order release
	}

	//TP_CII_0004
	@Test
	public void billonly() throws InterruptedException, Exception
	{
		releaseBillOnly(Notes, Allocated);
		addSplitCommission(Salesrep); //bill only release
	}

	//TP_CII_0005
	@Test
	public void commission() throws InterruptedException, Exception
	{
		releaseCommission(Dropshipmanufacturer, Notes, Allocated); //commission order
		addLineItemsForDropship(); //reusing the method which is used for adding line items in dropship
	}

	//TP_CII_0006
	//creating service order release
	@Test
	public void service() throws InterruptedException, Exception
	{
		releaseService(Notes, Allocated);
		addLineItemsForService(); //service order
	}

	//TP_CII_0007
	//creating customer invoice for dropship 
	@Test
	public void CIforDropship() throws InterruptedException
	{
int index=0;
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));
//		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
//		Thread.sleep(3000);
		
		List<WebElement> releaseRows= driver.findElement(By.id("release")).findElements(By.tagName("tr"));
		for(int temp=2;temp<=releaseRows.size();temp++)
		{
			System.out.println(driver.findElement(By.xpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]")).getAttribute("title"));
			if(driver.findElement(By.xpath("//table[@id='release']/tbody/tr["+temp+"]/td[9]")).getAttribute("title").equalsIgnoreCase("drop ship"))
			{
				index=temp;
			}
			else
				continue;
		}
		driver.findElement(By.xpath("//tr["+index+"]/td[@title='Drop Ship']")).click();
		
		cusInvoiceForRelease();
		
	}

	//TP_CII_0008
	//creating customer invoice for stock order
	@Test
	public void CIforStockorder() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Stock Order']")).click();
		cusInvoiceForRelease();
	}

	//TP_CII_0009
	//creating customer  invoice for bill only 
	@Test
	public void CIforBillonly() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Bill Only']")).click();
		cusInvoiceForRelease();
	}

	//TP_CII_0010
	//creating customer invoice for service order 
	@Test
	public void CIforService() throws InterruptedException, Exception
	{
		driver.findElement(By.xpath("//td[@title='Service']")).click();
		cusInvoiceForRelease();
	}

	@Test
	public void dropshipMultipleLineitems() throws InterruptedException, Exception
	{
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addMultiLineItemsForDropship();		//dropship with multiple line items
	}

	//TP_CII_0017
	//Import lineitems from xml in dropship
	@Test
	public void dropshipImportXml() throws InterruptedException, Exception
	{
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		importXmlForDropship();				//dropship by adding line items through importing xml
	}

	//TP_CII_0011
	@Test
	public void updateCI() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		openCustomerInvoice();
		Thread.sleep(4000);
		updateCustomerInvoice(Freight, Pro, Taxterritory, Reason, Email);
	}

	//TP_CII_0019
	//update decription of the existing customer invoice
	@Test
	public void updateDesc() throws InterruptedException
	{
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
		driver.findElement(By.xpath("//div[42]//div//div[@class='ui-dialog-buttonset']//button[contains(.,'Yes')]")).click();

		driver.findElement(By.id("invreasonttextid")).sendKeys("test");
		driver.findElement(By.xpath("//div[34]//div//div//button[contains(.,'OK')]")).click();

		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
	}

	
	//TP_CII_0012
	//update line items for existing dropship and create customer invoice
	@Test
	public void updateLineItems() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		createNewJob(Jobname, Salesrep, Taxterritory);
		changeStatusToBooked(Customername);	//book a job with customer name
		releaseDropShip(Dropshipmanufacturer, Notes, Allocated);
		addLineItemsForDropship();
		Thread.sleep(4000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Drop Ship']")));
		driver.findElement(By.xpath("//td[@title='Drop Ship']")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
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

	//TP_CII_0013
	//create partial customer invoice for stock order 
	@Test
	public void partialCIForSO() throws InterruptedException, Exception
	{
		//		loggingIn(Url, UName, Password);
		//		createNewJob(Jobname, Salesrep, Taxterritory);
		//		changeStatusToBooked(Customername);	//book a job with customer name

		releaseStockOrder(Notes, Allocated);
		addOneLineItemForStockOrder(SO_Productname, Quantity); //stock order

		SO_Productname= srcBook.getSheetAt(0).getRow(2).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity); 

		SO_Productname= srcBook.getSheetAt(0).getRow(3).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity); 

		SO_Productname= srcBook.getSheetAt(0).getRow(4).getCell(ColumnNumber(srcBook,0,0,"SO_ProductName")).toString();
		Quantity= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SO_Quantity")).toString();
		addOneLineItemForStockOrder(SO_Productname, Quantity);
		Thread.sleep(2000);

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("SaveLineSOReleaseID")));
		driver.findElement(By.id("SaveLineSOReleaseID")).click();
		Thread.sleep(6000);
		driver.findElement(By.id("closeLineSOReleaseID")).click();

		//first customer invoice 
		Thread.sleep(4000);
		selectStockorderRelease();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@title='Stock Order'])[2]")));
//		driver.findElement(By.xpath("(//td[@title='Stock Order'])[2]")).click();
		Thread.sleep(3000);

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoicebtnID")));
		driver.findElement(By.id("customerInvoicebtnID")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		Thread.sleep(6000);

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));

		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(12000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Line Items")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("canDeleteCuInvID_4")));
		driver.findElement(By.id("canDeleteCuInvID_4")).click();
		driver.findElement(By.id("canDeleteCuInvID_3")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(18000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

		//second customer invoice
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@title='Stock Order'])[2]")));
//		driver.findElement(By.xpath("(//td[@title='Stock Order'])[2]")).click();
		selectStockorderRelease();
		Thread.sleep(3000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("customerInvoicebtnID")));
		driver.findElement(By.id("customerInvoicebtnID")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveID")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveID")));
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(12000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Line Items")));
		driver.findElement(By.linkText("Line Items")).click();
		Thread.sleep(3000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();
		if(driver.findElement(By.xpath("//b[contains(.,'Do You want to close the SO transaction Status?')]")).isDisplayed())
		{
			driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();
		}
	}

	//TP_CII_0020
	//Check the Do Not Email checkbox and view the pdf
	@Test
	public void viewPdfAfterCheckingDoNotEmail() throws InterruptedException, Exception
	{
		openCustomerInvoice();	
		Thread.sleep(4000);
		driver.findElement(By.id("customerInvoie_doNotMailID")).click();
		driver.findElement(By.id("CuInvoiceSaveID")).click();
		driver.findElement(By.xpath("//div[41]//div//div[@class='ui-dialog-buttonset']//button[contains(.,'Yes')]")).click();
		driver.findElement(By.id("invreasonttextid")).sendKeys("test");
		driver.findElement(By.xpath("//div[34]//div//div//button[contains(.,'OK')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='imgInvoicePDF']/input")).click();
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();

	}


	//create customer invoice with tax enabled 
	@Test(enabled = false, priority = 19)
	public void createCustomerInvoiceWithTax() throws InterruptedException, Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,350)", "");
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
		jse.executeScript("window.scrollBy(0,350)", "");

		driver.findElement(By.id("CuInvoiceSaveID")).click();
		Thread.sleep(6000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Line Items")));
		jse.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.linkText("Line Items")).click();

	}







	//need to give some timing between each customer invoice creating 



	//checking the database to check the invoice data 
	@Test(enabled = false)
	public void dbCheckForCustomerInvoice() throws InterruptedException, ClassNotFoundException, SQLException
	{
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
		String username = "turbo";
		String password = "turbo@2016";
		String userid=null;

		String baseQuery = "select * from cuInvoice where InvoiceNumber = '" + cuInvoiceNumber + "' and NonTaxableSales > 0";
		baseQuery = baseQuery+";";


		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection(dbUrl, username, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(baseQuery);
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		ArrayList<Object> records = new ArrayList<>();

		while (rs.next()) {

			for (int i = 1; i <= columnsNumber; i++) {
				userid=rs.getString(1);
				records.add(rs.getString(i));
				System.out.print(rs.getString(i) + " "); // Print one element of a row
			}
			System.out.println();
		}

	}

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
