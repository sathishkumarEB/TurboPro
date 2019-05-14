package com.turbopro.company;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Divisions extends Methods {

	private StringBuffer verificationErrors = new StringBuffer();

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

	/*TP_CD_0001 - Logging into the application and navigate to Divisions*/
	@Test(enabled = true, priority = 1)	
	public void divisions() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateDivisions();
	}

	/*TP_CD_0002 - View the existing divisions one by one*/
	@Test(enabled = true, priority = 2)	
	public void viewExistingDivisions() throws InterruptedException, Exception
	{
		try{
			if(driver.findElement(By.xpath("//*[@id='1']/td[3]")).isDisplayed())
			{
				getxpath("//*[@id='1']/td[3]").click();
			}		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//*[@id='2']/td[3]")).isDisplayed())
			{
				getxpath("//*[@id='2']/td[3]").click();
			}		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//*[@id='3']/td[3]")).isDisplayed())
			{
				getxpath("//*[@id='3']/td[3]").click();
			}		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//*[@id='4']/td[3]")).isDisplayed())
			{
				getxpath("//*[@id='4']/td[3]").click();
			}		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//*[@id='5']/td[3]")).isDisplayed())
			{
				getxpath("//*[@id='5']/td[3]").click();
			}		
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CD_0003 - Create new division*/
	@Test(enabled = true, priority = 3)	
	public void createDivision() throws InterruptedException, Exception
	{
		try{
			getxpath("//input[@value='Clear']").click();
			getid("CoDivisionNameID").sendKeys("Test Division");
			getid("CoDivisionCodeID").sendKeys("DIV");
			Select division = new Select (driver.findElement(By.id("bankAccountsID")));
			division.selectByVisibleText("03");
			getid("accountDisPercentID").sendKeys("10");
			getid("addressID1").sendKeys("test address");
			getxpath("//input[@onclick='saveDivisions()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CD_0004 - Make a division inactive*/
	@Test(enabled = true, priority = 4)	
	public void inactiveDivision() throws InterruptedException, Exception
	{
		try{
			getxpath("//td[@title='Test Division']").click();
			if(!driver.findElement(By.id("inActiveNameID")).isSelected())
			{
				getid("inActiveNameID").click();
			}
			getxpath("//input[@onclick='saveDivisions()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CD_0005 - Make a division inactive*/
	@Test(enabled = true, priority = 5)	
	public void activeDivision() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Test Division']")));
			getxpath("//td[@title='Test Division']").click();
			if(driver.findElement(By.id("inActiveNameID")).isSelected())
			{
				getid("inActiveNameID").click();
			}
			getxpath("//input[@onclick='saveDivisions()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CD_0006 - Delete the division*/
	@Test(enabled = true, priority = 6)	
	public void deleteDivision() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Test Division']")));
			getxpath("//td[@title='Test Division']").click();
			getid("deleteDivisionID").click();
			if(driver.findElement(By.xpath("//span[text()='Confirm Delete']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*Trying to save or delete in division module, when no division is selected or details populated*/
	@Test(enabled = true, priority = 7)	//Check this method whether its working as intended; Getting a popup while running this method
	public void validationCheckInDivision() throws InterruptedException, Exception
	{
		try{
			getid("saveUserButton").click();
			Thread.sleep(3000);
			getid("deleteDivisionID").click();

			if(driver.findElement(By.xpath("//span[text()='Warning']")).isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();	
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@AfterTest
	public void teardown() 
	{
		//		driver.quit();
	}

}

