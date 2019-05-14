package com.turbopro.settings;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class BankingSettings extends Methods{
	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String baseUrl, cuInvoiceNumber, Url, UName, Password, JobName, Role, Pwd, RolodexName, User,  Initials,  Notes, Discount, Days, Description, Firstname,  LineItem, Lastname, VendorName, Count, Quantity1, TransNo, PO,  Reference, Quantity, Address, SalesRep, TaxTerritory, Employeename, Bondagent, State, City, Company, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Settings.xls")));
		baseUrl=srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();

		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		Proxy proxy = new Proxy();// Create a new proxy object and set the proxy
		proxy.setSslProxy("sysvines006.sysvine.local:3128");
		proxy.setSocksUsername("harini_shankar");
		proxy.setSocksPassword("vine@2017");
		capabilities.setCapability("proxy", proxy);//Add the proxy to our capabilities 
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 


		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		RolodexName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"RolodexName")).toString();
		Initials= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Initials")).toString();
		Pwd= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Pwd")).toString();
		User= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"User")).toString();
		Employeename= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Employeename")).toString();
		Lastname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Lastname")).toString();
		Firstname= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Firstname")).toString();
		Role= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Role")).toString();
		Address= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Address")).toString();
		Notes= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
		Discount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Discount")).toString();
		Days= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Days")).toString();
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

	/*logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateToRolodex() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);	
		navigateSettings();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Banking Settings")).click();
	}
}
