package com.turbopro.customermenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import com.turbopro.basepackages.*;


public class OldcuInvoiceCreating extends Variables {
	//	static ExcelSheetDriver xlsUtil;

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String baseUrl, cuInvoiceNumber;
	FileInputStream fis;
	HSSFWorkbook srcBook ;
		
	@BeforeTest
	public void setUp() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\sathish_kumar\\Documents\\InvoiceInputs1.xls")));
		    
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        // Create a new proxy object and set the proxy
       /* Proxy proxy = new Proxy();
        proxy.setSslProxy("sysvines006.sysvine.local:3128");
        proxy.setSocksUsername("bhuvan_gopal");
        proxy.setSocksPassword("vine@92017");
        //Add the proxy to our capabilities 
        capabilities.setCapability("proxy", proxy);*/
		//driver = new ChromeDriver(capabilities);
		
		baseUrl=srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();	
		
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
		driver = new ChromeDriver();
		driver.manage().window().maximize();
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
		
		public WebDriverWait getWait()
		{
			return new WebDriverWait(driver, 60);
		}	

		@Test
		public void createInvoice() throws Exception {
		Actions action1 = new Actions(driver);

			// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
			String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
			String username = "turbo";
			String password = "turbo@2016";
			String userid=null;
			driver.get(baseUrl);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
			driver.findElement(By.linkText("Login")).click();
			//Authentication
			driver.findElement(By.id("uname")).clear();
			driver.findElement(By.id("uname")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString());
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString());
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
			customerInvoices();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
			driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'New Invoice')]")));
			driver.findElement(By.xpath("//span[contains(text(), 'New Invoice')]")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_customerInvoiceID")));
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).clear();
			driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"customername")).toString());
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[24]/li/a")));
			driver.findElement(By.xpath("//body/ul[24]/li/a")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_TaxTerritory")));
			driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
			driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"taxterritory")).toString());
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[22]/li/a")));
			driver.findElement(By.xpath("//body/ul[22]/li/a")).click();

			driver.findElement(By.id("customerInvoice_frightIDcu")).clear();
			driver.findElement(By.id("customerInvoice_frightIDcu")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Freight")).toString());

			driver.findElement(By.id("CuInvoiceSaveID")).click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("cICheckTab2")));
			driver.findElement(By.id("cICheckTab2")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
			driver.findElement(By.id("new_row_itemCode")).click();
			driver.findElement(By.id("new_row_itemCode")).clear();
			driver.findElement(By.id("new_row_itemCode")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Product")).toString());

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
			driver.findElement(By.xpath("//body/ul[26]/li/a")).click();

			driver.findElement(By.id("new_row_quantityBilled")).click();
			driver.findElement(By.id("new_row_quantityBilled")).clear();
			driver.findElement(By.id("new_row_quantityBilled")).sendKeys(srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Qty")).toString());

			cuInvoiceNumber = driver.findElement(By.id("customerInvoice_lineinvoiceNumberId")).getText();

			driver.findElement(By.id("CuInvoiceSaveID")).click();

			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveCloseID")));
			driver.findElement(By.id("CuInvoiceSaveCloseID")).click();


			//====


			String baseQuery = "select * from cuInvoice where InvoiceNumber = '" + cuInvoiceNumber + "'";
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
	
		//checking the database to check the invoice data 
		@Test(enabled = false, priority = 8)
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







		private void setValueIntoCell(String readCell) {
			// TODO Auto-generated method stub

		}
		@AfterTest(enabled = false)
		public void tearDown() throws Exception {
			driver.quit();
			String verificationErrorString = verificationErrors.toString();
			if (!"".equals(verificationErrorString)) {
//				fail(verificationErrorString);
			}
		}

		private boolean isElementPresent(By by) {
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}


	}



