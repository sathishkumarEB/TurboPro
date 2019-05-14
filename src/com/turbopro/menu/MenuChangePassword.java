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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class MenuChangePassword extends Methods {
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

	/*TP_MCP_0001 - logging into the application and  View Change Password popup*/
	@Test(enabled = true, priority = 1)	
	public void viewcChangePassword() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		WebElement password = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions (driver);
		action.moveToElement(password).build().perform();
		driver.findElement(By.linkText("Change Password")).click();
		driver.findElement(By.linkText("close")).click();
	}

	/*TP_MCP_0002 - Perform change password*/
	@Test( enabled = true, priority = 2)
	public void performChangePassword() throws InterruptedException
	{
		WebElement closeicon = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions (driver);
		action.moveToElement(closeicon).build().perform();
		driver.findElement(By.linkText("Change Password")).click();
		driver.findElement(By.id("oldPswd")).click();
		driver.findElement(By.id("oldPswd")).sendKeys("D3m0");
		driver.findElement(By.id("newPswd")).click();
		driver.findElement(By.id("newPswd")).sendKeys("Test");
		driver.findElement(By.id("cnfrmPswd")).click();
		driver.findElement(By.id("cnfrmPswd")).sendKeys("Test");
		driver.findElement(By.className("ui-button-text")).click();
	}	

	/*TP_MCP_0003 -  Login with new password*/	
	@Test( enabled = true, priority = 3)	
	public void loginAfterChangePassword() throws InterruptedException {
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@value = 'Login']")).click();
	}

	/*TP_MCP_0004 - Change to old password*/
	@Test( enabled = true, priority = 4)
	public void performChangePasswordagain() throws InterruptedException
	{
		WebElement closeicon1 = driver.findElement(By.xpath("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img"));
		Actions action = new Actions (driver);
		action.moveToElement(closeicon1).build().perform();
		driver.findElement(By.linkText("Change Password")).click();
		driver.findElement(By.id("oldPswd")).click();
		driver.findElement(By.id("oldPswd")).sendKeys("Test");
		driver.findElement(By.id("newPswd")).click();
		driver.findElement(By.id("newPswd")).sendKeys("D3m0");
		driver.findElement(By.id("cnfrmPswd")).click();
		driver.findElement(By.id("cnfrmPswd")).sendKeys("D3m0");
		driver.findElement(By.className("ui-button-text")).click();
	}	

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}
