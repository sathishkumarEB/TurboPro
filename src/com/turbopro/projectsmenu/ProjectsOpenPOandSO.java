package com.turbopro.projectsmenu;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ProjectsOpenPOandSO extends Methods {

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

	//TP_POPS_0001 - logging into the application and navigate to projects 
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
		driver.findElement(By.linkText("Projects")).click();
	}

	//TP_POPS_0002 - view open purchase orders/sales orders in projects 
	@Test(enabled = true, priority = 2)	
	public void openPoSoInProjects() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialogPoSo")));
		getid("closedialogPoSo").click();
	}

	//TP_POPS_0003 - apply sort in the headers of open purchase orders/sales orders list 
	@Test(enabled = true, priority = 3)	
	public void sortingInOpenPoSo() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button").click();

		for(int i = 1; i<=2; i++)
		{
			getxpath("//*[@id='jqgh_poSoGrid_dateWanted']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_poSoGrid_ponumber']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_poSoGrid_vendorName']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_poSoGrid_vendorOrderNumber']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_poSoGrid_shipDate']").click();
			Thread.sleep(2000);
		}

		getxpath("//input[@onclick='clearpopuptextPoSo()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialogPoSo")));
		getid("closedialogPoSo").click();
	}

	//TP_POPS_0004 - view the open purchase order/sales orders by date range 
	@Test(enabled = true, priority = 4)	
	public void viewOpenPoSoByDateRange() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("daterangePoSo")));
		getid("daterangePoSo").click();
		getid("rangefromdatePoSo").sendKeys("09/01/2017");
		Thread.sleep(2000);
		getid("todatePoSo").sendKeys("11/22/2017");
		Thread.sleep(2000);
		getxpath("//input[@onclick='clearpopuptextPoSo()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialogPoSo")));
		getid("closedialogPoSo").click();
	}

	//TP_POPS_0005 - view open purchase order/sales orders for the specific customer
	@Test(enabled = false, priority = 5)	
	public void viewOpenPoSoForSpecCustomer() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("openJobscustomerNamePoSo")));
		getid("openJobscustomerNamePoSo").sendKeys("Vixxo Corporation -CRL");
		getlinktext("Vixxo Corporation -CRL").click();
		Thread.sleep(3000);
		List<WebElement> dropOptions = driver.findElements(By.className("ui-corner-all"));
		dropOptions.get(84).click();
		Thread.sleep(3000);
		getxpath("//input[@onclick='clearpopuptextPoSo()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialogPoSo")));
		getid("closedialogPoSo").click();
	}


	//TP_POPS_0006 - navigate to different pages of Open purchase orders/sales orders in Projects
	@Test(enabled = true, priority = 6)	
	public void navigatePagesInOpenPoSo() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_SalesPurchaseOrdersGrid']//div[@id='jqGridButtonDiv']/button").click();
		Thread.sleep(2000);
		getxpath("//*[@id='next_poSoGridPager']/span").click(); //some issue is here
		getxpath("//*[@id='next_poSoGridPager']/span").click();
		getxpath("//*[@id='next_poSoGridPager']/span").click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialogPoSo")));
		getid("closedialogPoSo").click();
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

