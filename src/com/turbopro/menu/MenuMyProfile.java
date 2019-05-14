package com.turbopro.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class MenuMyProfile extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password ;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	/*accessing the chrome driver*/
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Bank.xls")));
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

	/*TP_MMP_0001 - logging into the application and view my profile*/
	@Test(enabled = true, priority = 1)	
	public void viewMyProfile() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		WebElement settings = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions(driver);
		action.moveToElement(settings).build().perform();
		driver.findElement(By.xpath("//li[@onclick = 'openAddNewUserDefaultDialog();']")).click();
		driver.findElement(By.id("cancelUserButton")).click();
	}



	/*TP_MMP_0002 -  Update Warehouse in my profile */
	@Test( enabled = true, priority = 2)
	public void updateWarehouse() throws InterruptedException 
	{
		WebElement settings = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions(driver);
		action.moveToElement(settings).build().perform();
		driver.findElement(By.xpath("//li[@onclick = 'openAddNewUserDefaultDialog();']")).click(); 
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.id("whrhouseheaderID"))).click().build().perform();
		Select warehouse = new Select(driver.findElement(By.id("whrhouseheaderID")));
		warehouse.selectByVisibleText("DALLAS");
		driver.findElement(By.id("saveUserButton")).click();
	} 

	/* TP_MMP_0003 - Update division in my profile */
	@Test( enabled = true, priority = 3)
	public void updateDivision() throws InterruptedException 
	{
		WebElement settings = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions(driver);
		action.moveToElement(settings).build().perform();
		driver.findElement(By.xpath("//li[@onclick = 'openAddNewUserDefaultDialog();']")).click(); 
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.id("cODivisionID"))).click().build().perform();
		Select division = new Select(driver.findElement(By.id("cODivisionID")));
		division.selectByVisibleText("BI Dallas");
		driver.findElement(By.id("saveUserButton")).click();
	} 

	/*TP_MMP_0004 -  Update both warehouse and division in my profile */
	@Test( enabled = true, priority = 4)
	public void updatewarehouseanddivision() throws InterruptedException 
	{
		WebElement settings = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions(driver);
		action.moveToElement(settings).build().perform();
		driver.findElement(By.xpath("//li[@onclick = 'openAddNewUserDefaultDialog();']")).click(); 
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.id("whrhouseheaderID"))).click().build().perform();
		Select warehouse = new Select(driver.findElement(By.id("whrhouseheaderID")));
		warehouse.selectByVisibleText("FT WORTH");
		Actions action2 = new Actions(driver);
		action2.moveToElement(driver.findElement(By.id("cODivisionID"))).click().build().perform();
		Select division = new Select(driver.findElement(By.id("cODivisionID")));
		division.selectByVisibleText("BI Fort Worth");
		driver.findElement(By.id("saveUserButton")).click();
	} 

	/*TP_MMP_0005 -  Edit user settings and cancel */
	@Test( enabled = true, priority = 5)
	public void cancelUserSettings() throws InterruptedException 
	{
		WebElement settings = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions(driver);
		action.moveToElement(settings).build().perform();
		driver.findElement(By.xpath("//li[@onclick = 'openAddNewUserDefaultDialog();']")).click();  
		Actions action1 = new Actions(driver);
		action1.moveToElement(driver.findElement(By.id("whrhouseheaderID"))).click().build().perform();
		Select warehouse = new Select(driver.findElement(By.id("whrhouseheaderID")));
		warehouse.selectByVisibleText("FT WORTH");
		Actions action2 = new Actions(driver);
		action2.moveToElement(driver.findElement(By.id("cODivisionID"))).click().build().perform();
		Select division = new Select(driver.findElement(By.id("cODivisionID")));
		division.selectByVisibleText("BI Fort Worth");
		driver.findElement(By.id("cancelUserButton")).click();
	} 

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}





