package com.turbopro.home;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
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

public class Home extends Methods {

	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	private StringBuffer verificationErrors = new StringBuffer();

	private String Url, UName, Password, JobName, JobNumber, City, JobNameSearch, CustomerPO, JobNumberSearch, InventorySearchName, InventorySearchChar, SearchPO, SearchRolodex, CustomerName, Division;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));

		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// Create a new proxy object and set the proxy
		Proxy proxy = new Proxy();
		proxy.setSslProxy("sysvines006.sysvine.local:3128");
		proxy.setSocksUsername("sathish_kumar");
		proxy.setSocksPassword("vine@2017");
		//Add the proxy to our capabilities 
		capabilities.setCapability("proxy", proxy);
		driver = new ChromeDriver(capabilities);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 


		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		JobName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobName")).toString();
		JobNumber= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobNumber")).toString();
		City = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"City")).toString();
		JobNameSearch= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobNameSearch")).toString();
		CustomerPO= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerPO")).toString();
		JobNumberSearch= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"JobNumberSearch")).toString();
		InventorySearchName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"InventorySearchName")).toString();
		InventorySearchChar= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"InventorySearchChar")).toString();
		SearchPO= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SearchPO")).toString();
		SearchRolodex= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"SearchRolodex")).toString();
		CustomerName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Division = srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Division")).toString();

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

	//TP_H_0001 - logging into the application
	@Test(enabled = true, priority = 1)	
	public void login() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(4000);
	}

	/*TP_H_0002 - Advanced search by filtering budget status*/ 
	@Test(enabled = true, priority = 2)	
	public void budgetAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("budgetnameID");
			getxpath(advancedStatusBudget).click();
			Thread.sleep(3000);
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0003 - Advanced search by filtering bid status*/ 
	@Test(enabled = true, priority = 3)	
	public void bidAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getxpath(homeMenu).click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("bidnameID");
			getid("bidnameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0004 - Advanced search by filtering quote status*/ 
	@Test(enabled = true, priority = 4)	
	public void quoteAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getxpath(homeMenu).click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("quotenameID");
			getid("quotenameID").click();
			//			getxpath("//input[@value='Search']").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0005 - Advanced search by filtering booked status*/ 
	@Test(enabled = true, priority = 5)	
	public void bookedAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("bookednameID");
			getid("bookednameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0006 - Advanced search by filtering closed status*/ 
	@Test(enabled = true, priority = 6)	
	public void closedAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("closednameID");
			getid("closednameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0007 - Advanced search by filtering submitted status*/ 
	@Test(enabled = true, priority = 7)	
	public void submittedAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("submittednameID");
			getid("submittednameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0008 - Advanced search by filtering planning status*/ 
	@Test(enabled = true, priority = 8)	
	public void planningAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("planningnameID");
			getid("planningnameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0009 - Advanced search by filtering lost status*/ 
	@Test(enabled = true, priority = 9)	
	public void lostAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("lostnameID");
			getid("lostnameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*TP_H_0010 - Advanced search by filtering abondoned status*/ 
	@Test(enabled = true, priority = 10)	
	public void abondonedAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("abondonednameID");
			getid("abondonednameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0011 - Advanced search by filtering reject status*/ 
	@Test(enabled = true, priority = 11)	
	public void rejectAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("rejectnameID");
			getid("rejectnameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0012 - Advanced search by filtering overbudget status*/ 
	@Test(enabled = true, priority = 12)	
	public void overbudgetAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("overBudgetnameID");
			getid("overBudgetnameID").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*TP_H_0013 - Advanced search by job number*/ 
	@Test(enabled = true, priority = 13)	
	public void jobNumberAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("jobNumberID");
			getid("jobNumberID").sendKeys(JobNumber);
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
			//			waitforid("jobHeader_JobName_id")));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0014 - Advanced search by city*/ 
	@Test(enabled = true, priority = 14)	
	public void cityAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("citynameID");
			getid("citynameID").sendKeys(City);
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0015 - Advanced search by job name*/ 
	@Test(enabled = true, priority = 15)	
	public void jobnameAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("projectID");
			getid("projectID").sendKeys(JobNameSearch);
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0016 - Advanced search by filtering date range*/ 
	@Test(enabled = true, priority = 16)	
	public void rangeAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("bidDateCheckbox");
			getid("bidDateCheckbox").click();
			getid("rangepickerID").click();
			Thread.sleep(3000);
			getlinktext("1").click();
			getid("thruPickerID").click();
			getlinktext("28").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
			Thread.sleep(4000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0017 - Advanced search by customer PO*/ 
	@Test(enabled = true, priority = 17)	
	public void customerpoAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("customerPoNameID");
			getid("customerPoNameID").sendKeys(CustomerPO);
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0018 - Advanced search by selecting division*/ 
	@Test(enabled = true, priority = 18)	
	public void divisionAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			Select division = new Select(getid("divisionID"));
			division.selectByVisibleText(Division); //"Bartos Air Solutions" is a division here
			//			getxpath("//input[@value='Search']").click();
			getxpath(advancedSearchButton).click();
			waitforxpath("//*[@id='1']/td[4]");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0019 - Search job by entering job name*/ 
	@Test(enabled = true, priority = 19)	
	public void searchJobByName() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("jobsearch").sendKeys(JobNameSearch);
			getxpath("//input[@onclick='getJobs()']").click();
			waitforxpath("//*[@id='1']/td[4]");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0020 - Search job by entering job name*/ 
	@Test(enabled = true, priority = 20)	
	public void searchJobByNumber() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("jobsearch").sendKeys(JobNumberSearch);
			getxpath("//input[@onclick='getJobs()']").click();
			waitforid("jobHeader_JobName_id"); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0021 - Search inventory by entering inventory name*/
	@Test(enabled = true, priority = 21)	
	public void searchInventoryByName() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("inventorysearch").sendKeys(InventorySearchName);  //Some problem is there while executing
			getxpath("//input[@onclick='getInventory()']").click();
			waitforid("codeId");
			Thread.sleep(5000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0022 - Search inventory by entering three characters*/
	@Test(enabled = true, priority = 22)	
	public void searchInventoryByCharacters() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("inventorysearch").sendKeys(InventorySearchChar);
			getxpath("//input[@onclick='getInventory()']").click();
			waitforxpath("//*[@id='1']/td[3]");
			Thread.sleep(3000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0023 - Search inventory by selecting from suggestions*/
	@Test(enabled = true, priority = 23)	
	public void searchInventoryFromSuggestions() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("inventorysearch").sendKeys(InventorySearchName);
			getid("inventorysearch").sendKeys(Keys.ARROW_DOWN);
			getid("inventorysearch").sendKeys(Keys.ENTER);
			//			getxpath("//body/ul[15]/li/a").click();
			waitforid("codeId");
			Thread.sleep(4000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0024 - Search PO from home widget*/
	@Test(enabled = true, priority = 24)	
	public void searchPO() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("posearch").sendKeys(SearchPO);
			getxpath("//body/ul[16]/li/a").click();
			waitforid("jobHeader_JobName_id");
			Thread.sleep(4000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	/*TP_H_0025 - Opening the recently opened job*/
	@Test(enabled = true, priority = 25)	
	public void openRecentlyOpenedJob() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(4000);
			getlinktext("Home").click();
			waitforxpath("//*[@id='1']/td[3]");
			Actions recentjob = new Actions(driver);
			recentjob.moveToElement(getxpath("//*[@id='1']/td[3]")).doubleClick().perform();
			waitforid("jobHeader_JobName_id");
			Thread.sleep(4000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0026 - Search rolodex from the widget*/
	@Test(enabled = false, priority = 26)	
	public void searchRolodex() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getid("rolodex").sendKeys(SearchRolodex);
			getxpath("//body/ul[14]/li[1]/a").click();
			waitforid("customerNameHeader");
			Thread.sleep(4000);
			getlinktext("Home").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0027 - Accessing quickbook and closing it*/
	@Test(enabled = true, priority = 27)	
	public void accessQuickbookAndClose() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath("//a[@onclick='QuickbookPopup()']");
			getxpath("//a[@onclick='QuickbookPopup()']").click();
			waitforid("QBSaveCloseID");
			getid("QBSaveCloseID").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*TP_H_0028 - Creating a booked job using quickbook*/
	@Test(enabled = true, priority = 28)	
	public void createJobUsingQuickbook() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath("//a[@onclick='QuickbookPopup()']");
			getxpath("//a[@onclick='QuickbookPopup()']").click();
			getid("Quickbookprojectid").sendKeys(JobName);
			Select division = new Select (getid("QuickbookDivisionid"));
			division.selectByVisibleText(Division);
			getid("QuickbookCustomer_name").sendKeys(CustomerName);
			getxpath("//body/ul[18]/li/a").click();
			getid("QBSaveID").click();
			Thread.sleep(4000);
			waitforid("deletejob");
			getid("deletejob").click();

			if(getid("ui-dialog-title-1").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	/*Advanced search by filtering nobid status*/ 
	@Test(enabled = true, priority = 29)	
	public void nobidAdvancedSearch() throws InterruptedException, Exception
	{
		try{
			getlinktext("Home").click();
			waitforxpath(advancedButton);
			getxpath(advancedButton).click();
			waitforid("noBidNameID");
			getid("noBidNameID").click();
			getxpath("//input[@value='Search']").click();
			waitforxpath("//td[@aria-describedby='searchResultsGrid_jobNumber']");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}



	@AfterTest
	public void teardown() 
	{
//				driver.quit();
	}

}
