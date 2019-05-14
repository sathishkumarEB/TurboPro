package com.turbopro.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class SalesTax extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password;
	FileInputStream fis;
	HSSFWorkbook srcBook ;

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		srcBook=new HSSFWorkbook(new FileInputStream(new File("./testdata/bookedjobInputs.xls")));
		openChromeBrowser();

		Url= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"baseURL")).toString();
		UName= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"username")).toString();
		Password= srcBook.getSheetAt(1).getRow(1).getCell(ColumnNumber(srcBook,0,0,"password")).toString();

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


	//logging into the application and navigate to sales tax under tax territory
	@Test(enabled = true, priority = 1)	
	public void salesTax() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(1000);
		navigateTaxterritorySalesTax();
		Thread.sleep(2000);
	}

	//View sales tax report for single date
	@Test(enabled = true, priority = 2)	
	public void singleDateSalesTaxReport() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//span[text()='Sales Tax']").isDisplayed())
			{
				getxpath("//input[@title='View Sales Tax PDF']").click();
			}
			parentWindow();
			Thread.sleep(4000);

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*View sales tax report by date range*/
	@Test(enabled = true, priority = 3)	
	public void dateRangeSalesTaxReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Date Range: ");// Select date type
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*View sales tax report by date range and selecting specific date*/
	@Test(enabled = true, priority = 4)	
	public void dateRangeSalesTaxReportForSpecificMonth() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Date Range: ");// Select date type
			getid("salesTaxFromDate").click();
			getid("salesTaxFromDate").clear();
			getid("salesTaxFromDate").sendKeys("02/08/2018");
			getid("salesTaxDateSelect").click();
			getid("salesTaxToDate").click();
			getid("salesTaxToDate").clear();
			getid("salesTaxToDate").sendKeys("03/08/2018");
			Thread.sleep(3000);		

			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}

			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*view sales tax report by month to date range*/
	@Test(enabled = true, priority = 5)	
	public void monthToDateRangeSalesTaxReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Month To Date Ending: ");// Select date type
			Thread.sleep(3000);
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}

			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*view sales tax report by year to date range*/
	@Test(enabled = true, priority = 6)	
	public void yearToDateRangeSalesTaxReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Year To Date Ending: ");// Select date type
			Thread.sleep(3000);
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='View Sales Tax PDF']").click();
			parentWindow();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*export the sales tax csv report for single date*/
	@Test(enabled = true, priority = 7)	
	public void singleDateSalesTaxCsvReport() throws InterruptedException, Exception
	{
		try{
			if(getxpath("//span[text()='Sales Tax']").isDisplayed())
			{
				getxpath("//input[@title='Export Sales Tax PDF']").click();
			}
			Thread.sleep(4000);
			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*export the sales tax csv report by date range*/
	@Test(enabled = true, priority = 8)	
	public void dateRangeSalesTaxCsvReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Date Range: ");// Select date type
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*export sales tax csv report by date range and selecting specific date*/
	@Test(enabled = true, priority = 9)	
	public void dateRangeSalesTaxCsvReportForSpecificMonth() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Date Range: ");// Select date type
			getid("salesTaxFromDate").click();
			getid("salesTaxFromDate").clear();
			getid("salesTaxFromDate").sendKeys("02/08/2018");
			getid("salesTaxDateSelect").click();
			getid("salesTaxToDate").click();
			getid("salesTaxToDate").clear();
			getid("salesTaxToDate").sendKeys("03/08/2018");
			Thread.sleep(3000);		
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}
			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*export sales tax csv report by month to date range*/
	@Test(enabled = true, priority = 10)	
	public void monthToDateRangeSalesTaxCsvReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Month To Date Ending: ");// Select date type
			Thread.sleep(3000);
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}

			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	/*export sales tax csv report by year to date range*/
	@Test(enabled = true, priority = 11)	
	public void yearToDateRangeSalesTaxCsvReport() throws InterruptedException, Exception
	{
		try{
			getid("salesTaxDateSelect").click();
			Select datetype = new Select(getid("salesTaxDateSelect"));
			Thread.sleep(2000);
			datetype.selectByVisibleText(" Year To Date Ending: ");// Select date type
			Thread.sleep(3000);
			if(getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);

			if(!getid("salesTaxShowInvoiceDetails").isSelected())
			{
				getid("salesTaxShowInvoiceDetails").click();
			}

			if(!getid("salesTaxShowOnlyPaidInvoices").isSelected())
			{
				getid("salesTaxShowOnlyPaidInvoices").click();
			}
			getxpath("//input[@title='Export Sales Tax PDF']").click();
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	/*Clicking Cancel button navigates to home page*/
	@Test(enabled = true, priority = 12)	
	public void cancelSalesTaxAndNavigateToHome() throws InterruptedException, Exception
	{
		try{
			getxpath("//input[@title='Cancel']").click();
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

