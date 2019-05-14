package com.turbopro.projectsmenu;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class ProjectsCommissionStatements extends Methods {

	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/HomeInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
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

	//TP_PAR_0001 - logging into the application and navigate to projects 
	@Test(enabled = true, priority =1)
	public void projects() throws InterruptedException, Exception 
	{

		//		driver.get("http://qe.tt.eb.local/turbotracker/turbo/");
		//		driver.findElement(By.linkText("Login")).click();
		//		driver.findElement(By.id("uname")).sendKeys("Admin");
		//		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		//		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		getlinktext("Projects").click();
	}


	//TP_PCS_0001 - attempt to access commission statements without selecting salesrep 
	@Test(enabled = true, priority = 2)	
	public void accessCommStateWithoutSalesRep() throws InterruptedException, Exception
	{

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		if(getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").isDisplayed())
		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
		}
	}

	//TP_PCS_0002 - selecting specific salesrep and view commission statement
	@Test(enabled = true, priority = 3)	
	public void accessCommStateWithSalesRep() throws InterruptedException, Exception
	{

		if(getid("SalesRepComboList").isDisplayed())
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Bill Barnes");
		}

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		Thread.sleep(5000);
		getid("closeCommission").click();

	}


	//TP_PCS_0003 - view pdf of the commission statement  
	@Test(enabled = true, priority = 4)	
	public void viewPdfOfCommStatment() throws InterruptedException, Exception
	{

		if(getid("SalesRepComboList").isDisplayed())
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Bill Barnes");
		}

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		Thread.sleep(5000);

		getxpath("//*[@id='CommissionStatementPager_left']/table/tbody/tr/td[1]/div").click();
		parentWindow();
		getid("closeCommission").click();

	}


	//TP_PCS_0004 - download csv for the commission statement  
	@Test(enabled = true, priority = 5)	
	public void downloadCsvOfCommStatment() throws InterruptedException, Exception
	{

		if(getid("SalesRepComboList").isDisplayed())
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Bill Barnes");
		}

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		Thread.sleep(5000);
		getxpath("//*[@id='CommissionStatementPager_left']/table/tbody/tr/td[2]/div").click();
		getid("closeCommission").click();
	}


	//TP_PCS_0006 - apply sorting in commission statements
	@Test(enabled = true, priority = 6)	
	public void sortCommStatmentHeaders() throws InterruptedException, Exception
	{

		if(getid("SalesRepComboList").isDisplayed())
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Bill Barnes");
		}

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		Thread.sleep(5000);

		for(int i = 1; i<=2; i++)
		{
			getid("jqgh_CommissionStatementGrid_jobNumber").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_jobName").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_releaseType").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_reference").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_datePaid").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_ecRepSplitProfit").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_ecInrepsplitCommissionRate").click();
			Thread.sleep(2000);
			getid("jqgh_CommissionStatementGrid_ecInrepsplitAmountDue").click();
		}

		getid("closeCommission").click();

	}

	//TP_PCS_0005 - view customer statement of salesrep based on date range
	@Test(enabled = true, priority = 7)	
	public void viewCommStatmentByDateRange() throws InterruptedException, Exception
	{

		if(getid("SalesRepComboList").isDisplayed())
		{
			getid("SalesRepComboList").click();
			Select salesRep = new Select(getid("SalesRepComboList"));
			salesRep.selectByVisibleText("Bill Barnes");
		}

		if(getxpath("//td[@title='Commission Statement']").isDisplayed())
		{
			Actions action = new Actions(driver);
			action.moveToElement(getxpath("//td[@title='Commission Statement']")).doubleClick().perform();
		}

		Thread.sleep(5000);

		if(getid("endperioddate").isDisplayed())
		{
			getid("endperioddate").click();
			Select salesRep = new Select(getid("endperioddate"));
			salesRep.selectByVisibleText("09/13/2017");
		}
		Thread.sleep(4000);

		getid("closeCommission").click();

	}



	@AfterTest
	public void teardown() 
	{
		driver.quit();
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

