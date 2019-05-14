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

public class ProjectsOpenJobs extends Methods {

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

	//TP_POJ_0001 - logging into the application and navigate to projects 
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


	//TP_POJ_0002 - view open jobs in projects 
	@Test(enabled = true, priority = 2)	
	public void openJobsInProjects() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}

	//TP_POJ_0003 - apply sort in the headers of open jobs list 
	@Test(enabled = true, priority = 3)	
	public void sortingInOpenJobs() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button").click();

		for(int i = 1; i<=2; i++)
		{
			getxpath("//*[@id='jqgh_openJobsGridpop_customerName']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_jobName']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_jobNumber']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_contractAmount']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_allocated']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_invoiced']").click();
			Thread.sleep(3000);
			getxpath("//*[@id='jqgh_openJobsGridpop_unreleased']").click();
			Thread.sleep(3000);
		}

		getxpath("//input[@onclick='clearpopuptext()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}

	//TP_POJ_0004 - view the open jobs by date range 
	@Test(enabled = true, priority = 4)	
	public void viewOpenJobsByDateRange() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("daterange")));
		getid("daterange").click();
		getid("rangefromdate").sendKeys("09/01/2017");
		Thread.sleep(2000);
		getid("todate").sendKeys("11/22/2017");
		Thread.sleep(2000);
		getxpath("//input[@onclick='clearpopuptext()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}

	//TP_POJ_0005 - view jobs for the specific customer
	@Test(enabled = true, priority = 5)	
	public void viewOpenJobsForSpecCustomer() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("openJobscustomerName")));
		getid("openJobscustomerName").sendKeys("Vixxo Corporation -CRL");
		Thread.sleep(3000);
		List<WebElement> dropOptions = driver.findElements(By.className("ui-corner-all"));
		dropOptions.get(84).click();
		//		getlinkText("Vixxo Corporation -CRL")).click();
		//driver.findElement(By.xpath("//body/ul[15]/li/a")).click();
		Thread.sleep(3000);
		getxpath("//input[@onclick='clearpopuptext()']").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}


	//TP_POJ_0006 - navigate to different pages of Open jobs in Projects
	@Test(enabled = true, priority = 6)	
	public void navigatePagesInOpenJobs() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gbox_openJobsGrid']//div[@id='jqGridButtonDiv']/button").click();
		Thread.sleep(2000);
		getxpath("//*[@id='next_openJobsGridPagerpop']/span").click();
		getxpath("//*[@id='next_openJobsGridPagerpop']/span").click();
		getxpath("//*[@id='next_openJobsGridPagerpop']/span").click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
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

