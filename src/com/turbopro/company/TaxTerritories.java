package com.turbopro.company;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class TaxTerritories extends Methods {

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

	/*TP_CTT_0001 - Logging into the application and navigate to Tax Territories*/
	@Test(enabled = true, priority = 1)	
	public void taxTerritory() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		navigateTaxTerritories();
	}

	/*TP_CTT_0002 - View tax territory details of existing territory*/
	@Test(enabled = true, priority = 2)	
	public void viewTaxTerritoryDetails() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//*[@id='1']/td[3]").isDisplayed())
			{
				getxpath("//*[@id='1']/td[3]").click();
			}		
			Thread.sleep(2000);
			if(getxpath("//*[@id='2']/td[3]").isDisplayed())
			{
				getxpath("//*[@id='2']/td[3]").click();
			}
			Thread.sleep(2000);
			if(getxpath("//*[@id='3']/td[3]").isDisplayed())
			{
				getxpath("//*[@id='3']/td[3]").click();
			}
			Thread.sleep(2000);
			if(getxpath("//*[@id='4']/td[3]").isDisplayed())
			{
				getxpath("//*[@id='4']/td[3]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CTT_0003 - Attempt to add tax territory and cancel it*/
	@Test(enabled = true, priority = 3)	
	public void addAndCancelTaxTerritory() throws InterruptedException, Exception
	{
		try{
			getid("addChartlist").click();
			getxpath("//input[@onclick='cancelAddTaxTerritory()']").click();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CTT_0004 - Create a new tax territory*/
	@Test(enabled = true, priority = 4)	
	public void createTaxTerritory() throws InterruptedException, Exception
	{
		try{
			getid("addChartlist").click();
			if(getid("ui-dialog-title-addNewTaxTerritoryDialog").isDisplayed())
			{
				Thread.sleep(3000);
				getid("stateID").sendKeys("UU");
				getid("stateCodeID").sendKeys("33");
				getid("decriptionID").sendKeys("testtaxterritory");
				getxpath("//input[@onclick='saveNewTaxTerritory()']").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CTT_0005 - Delete the created tax territory*/
	@Test(enabled = true, priority = 5)	
	public void deleteTaxTerritory() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			waitforxpath("//td[@title='testtaxterritory']");
			getxpath("//td[@title='testtaxterritory']").click();
			getid("deleteChartOfAccountID").click();

			if(getxpath("//span[text()='Confirm Delete']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
			if(getxpath("//span[text()='Failure']").isDisplayed())
			{
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CTT_0006 - Update details for the existing tax territory*/
	@Test(enabled = true, priority = 6)	
	public void updateTaxTerritoryDetails() throws InterruptedException, Exception
	{
		try{
			driver.navigate().refresh();
			waitforxpath("//td[@title='testtaxterritory']");
			getxpath("//td[@title='testtaxterritory']").click();
			getid("stateID").click();
			getid("stateID").clear();
			getid("stateID").sendKeys("TT");
			getid("taxRateID").click();
			getid("taxRateID").clear();
			getid("taxRateID").sendKeys("5.6");
			getid("GRTaxRateId").click();
			getid("GRTaxRateId").clear();
			getid("GRTaxRateId").sendKeys("11.02");
			getxpath("//input[@onclick='saveTaxTerritory()']").click();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*TP_CTT_0007 - Make the tax territory as inactive*/
	@Test(enabled = true, priority = 7)	
	public void inactiveTaxTerritory() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//td[@title='testtaxterritory']");
			getxpath("//td[@title='testtaxterritory']").click();

			if(!getid("inActiveChkbx").isSelected())
			{
				getid("inActiveChkbx").click();
			}
			getxpath("//input[@onclick='saveTaxTerritory()']").click();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_CTT_0008 - make the inactive tax territory as active */
	@Test(enabled = true, priority = 8)	
	public void activeTaxTerritory() throws InterruptedException, Exception
	{
		try{
			waitforxpath("//td[@title='testtaxterritory']");
			getxpath("//td[@title='testtaxterritory']").click();
			if(getid("inActiveChkbx").isSelected())
			{
				getid("inActiveChkbx").click();
			}
			getxpath("//input[@onclick='saveTaxTerritory()']").click();
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

