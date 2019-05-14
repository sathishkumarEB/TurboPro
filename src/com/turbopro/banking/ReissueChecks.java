package com.turbopro.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class ReissueChecks extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Description, Reference, BankAccount;
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
		Reference= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reference")).toString();
		Description= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Description")).toString();
		BankAccount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"BankAccount")).toString();
	}

	/*To access excel sheet*/
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

	/*TP_RC_0001 - logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateToReissueIssues() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateBankReissueChecks();
	}

	/*TP_RC_0002 - Print Reissue check details*/
	@Test(enabled = true, priority = 2)	
	public void printCheck() throws InterruptedException, Exception
	{
		getxpath("//*[@id='bankAccountsID']").click();
		Select Account = new Select(getxpath("//*[@id='bankAccountsID']"));
		Account.selectByVisibleText(BankAccount);
		Thread.sleep(2000);
		getid("CheckStartingID").click();
		Select startingcheck = new Select(getid("CheckStartingID"));
		startingcheck.selectByVisibleText("68047");
		Thread.sleep(2000);
		getid("CheckEndingID").click();
		Select endingcheck = new Select(getid("CheckEndingID"));
		endingcheck.selectByVisibleText("68047");
		getxpath("//*[@id='writeChecksFromID']/table/tbody/tr[7]/td/input[1]").click();// click print
	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
}
