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

public class ProjectsFilter extends Methods {

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

	//TP_PF_0006 - View sales rep entries by applying filter
	@Test(enabled = true, priority = 2)
	public void viewSalesRepEntries() throws InterruptedException 
	{
//		getxpath("//*[@id= 'mainMenuProjectsPage']/a").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'SalesRepComboList']")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(getxpath("//*[@id= 'SalesRepComboList']")).doubleClick().build().perform();
		Thread.sleep(2000);
	}

	//TP_PF_0007 - Select sales rep entries and apply filter
	@Test(enabled = true, priority = 3)
	public void selectSalesRepEntry() throws InterruptedException 
	{
		getxpath("//*[@id= 'mainMenuProjectsPage']/a").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'SalesRepComboList']")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(getxpath("//*[@id= 'SalesRepComboList']")).click().build().perform();
		Thread.sleep(2000);
		Select drpSalesRep = new Select(getid("SalesRepComboList"));
		Thread.sleep(2000);
		drpSalesRep.selectByVisibleText("Chad Stevens");
		Thread.sleep(2000);
		getxpath("//*[@id= 'clearCustomer']").click();
	}

	@AfterTest
	public void teardown() 
	{
		// driver.quit();
	}


}



