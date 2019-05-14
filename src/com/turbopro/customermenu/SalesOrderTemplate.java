package com.turbopro.customermenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesOrderTemplate extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, ProductNo, template, Qty, Notes, Email;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/Template.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();
		ProductNo= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"ProductNo")).toString();
		template = srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"template")).toString();
		Qty= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,1,0,"Qty")).toString();
		Notes= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Notes")).toString();
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


	//TC_SOT_0001 - logging into the application and navigate to credit tab
	@Test(enabled = true, priority = 1)	
	public void SOTemplate() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateCustomerSalesOrderTemplate();
	}

	//TC_SOT_0002 - Add new sales order template
	@Test(enabled = true, priority = 2)	
	public void addSalesOrderTemplate() throws InterruptedException, Exception
	{
		try{
			driver.findElement(By.id("templateId")).click();
			driver.findElement(By.id("templateId")).sendKeys(template);// Provide template description
			driver.findElement(By.xpath("//*[@id= 'showOrderPointsButtons']/table/tbody/tr/td[1]/input[2]")).click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_SOT_0003 - Add line items
	@Test(enabled = true, priority = 3)	
	public void addLineItems() throws InterruptedException, Exception
	{
		try{
			Thread.sleep(5000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='38']/td[3]")));
			driver.findElement(By.xpath("//*[@id='38']/td[3]")).click();// select an entry from template table
			Thread.sleep(4000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("addSalesOrderTemplateGrid_iladd")));
			driver.findElement(By.id("addSalesOrderTemplateGrid_iladd")).click();// click add icon
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));
			driver.findElement(By.id("new_row_note")).sendKeys(ProductNo);// Enter Product No.
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[13]/li/a")));	
			driver.findElement(By.xpath("//body/ul[13]/li/a")).click();// select specific entry
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_note")));	
			driver.findElement(By.id("new_row_note")).click();// click line item
			driver.findElement(By.id("new_row_note")).sendKeys(Keys.ENTER);// Hit Enter
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_SOT_0004 - Edit template
	@Test(enabled = true, priority = 4)	
	public void editTemplate() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '5']/td[3]")));
			driver.findElement(By.xpath("//*[@id='5']/td[3]")).click();// select an entry from template table
			driver.findElement(By.id("addSalesOrderTemplateGrid_iledit")).click();// click edit icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("1_quantityOrdered")));
			driver.findElement(By.id("1_quantityOrdered")).clear();
			driver.findElement(By.id("1_quantityOrdered")).sendKeys(Qty);// Enter Product No.
			driver.findElement(By.id("addSalesOrderTemplateGrid_ilsave")).click();// click add icon
			driver.findElement(By.xpath("//*[@id= 'showOrderPointsButtons']/table/tbody/tr/td[1]/input[2]")).click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}


	}

	//TC_SOT_0005 - Delete template
	@Test(enabled = true, priority = 5)	
	public void deleteTemplate() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '28']/td[3]")));
			driver.findElement(By.xpath("//*[@id='28']/td[3]")).click();// select an entry from template table
			driver.findElement(By.xpath("//*[@id= 'showOrderPointsButtons']/table/tbody/tr/td[1]/input[3]")).click();// click save
			driver.findElement(By.xpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]")).click();// Click Yes in Info popup
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_SOT_0006 - Edit note of a line item
	@Test(enabled = true, priority = 6)	
	public void editNote() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '30']/td[3]")));
			driver.findElement(By.xpath("//*[@id='30']/td[3]")).click();// select an entry from template table
			driver.findElement(By.id("inlinenotecustombutton")).click();// click edit note icon
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'SoLineItemNoteForm']/table[1]/tbody/tr/td/div[2]/div")));
			//		driver.findElement(By.id("SoLineItemNoteForm")).clear();
			driver.findElement(By.xpath("//*[@id='SoLineItemNoteForm']/table[1]/tbody/tr/td/div[2]/div")).sendKeys(Notes);// Edit Notes
			driver.findElement(By.id("SaveInlineNoteID")).click();// click save in Inline notes
			driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[1]/input[2]")).click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	//TC_SOT_0007 - view and hide price
	@Test(enabled = true, priority = 7)	
	public void viewHidePrice() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= '5']/td[3]")));
			driver.findElement(By.xpath("//*[@id='5']/td[3]")).click();// select an entry from template table
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("showhidePrice")));
			driver.findElement(By.id("showhidePrice")).click();// click show price
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("showhidePrice")));
			driver.findElement(By.id("showhidePrice")).click();// click hide price
			driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[1]/input[2]")).click();// click save
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


}