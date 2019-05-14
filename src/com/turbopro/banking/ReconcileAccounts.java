package com.turbopro.banking;

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

public class ReconcileAccounts extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, BankAccount;
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
		BankAccount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"BankAccount")).toString();
	}

	/*To access Excel sheet*/
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

	/*TP_RA_0001 - logging into the application and navigate to Banking (Reconcile Accounts)*/
	@Test(enabled = true, priority = 1)	
	public void navigateToReconcileAccounts() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		navigateBankReconcileAccounts();
	}

	/*TP_RA_0002 - Select Bank account and click reconcile */
	@Test(enabled = false, priority = 2)	
	public void selectBank() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bankAccountsID']")));
			getxpath("//*[@id='bankAccountsID']").click();
			Select Account = new Select(getxpath("//*[@id='bankAccountsID']"));
			Account.selectByVisibleText(BankAccount);
			getxpath("//input[@id='reconcileaccount']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TP_RA_0003 - Select Ending date*/
	@Test(enabled = false, priority = 3)	
	public void selectEndingDate() throws InterruptedException, Exception
	{
		try{
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='reconcileendbalancedate']")));
			getxpath("//input[@id='reconcileendbalancedate']").click();
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("17")));
			getlinktext("17").click();
			getxpath("//input[@value = 'Ok']").click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Create new checks for banking transaction*/
	@Test(enabled = false, priority = 4)	
	public void createNewCheck() throws InterruptedException, Exception
	{
		getlinktext("Banking").click();
		Thread.sleep(3000);
		getxpath("//td[@title='PLAINS CAPITAL BANK ZBA']").click(); //use BankAccount
		getlinktext("Transaction Details").click();
		getxpath("//input[@value=\"New Check\"]").click();

		if(getid("newCheckDetails").isDisplayed())
		{
			getid("paytoID").click();
			getid("paytoID").clear();
			getid("paytoID").sendKeys("Safe Air of Illinois  Inc.");
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Safe Air of Illinois  Inc.')]")));
			getxpath("//a[contains(.,'Safe Air of Illinois  Inc.')]").click();

			getid("checkNo").click();
			getid("checkNo").clear();
			getid("checkNo").sendKeys("#1");

			getid("memoID").click();
			getid("memoID").clear();
			getid("memoID").sendKeys("Memo1");

			getid("checkamountID").click();
			getid("checkamountID").clear();
			getid("checkamountID").sendKeys("1000");

			Select account = new Select(getid("glAccountCheckID"));
			account.selectByVisibleText("1050-00-000 -CASH - BANK ONE PAYROLL");
			Thread.sleep(3000);
			getid("savecheckButton").click(); //write checks button not clicking
		}
	}

	/*Create new transaction for banking transaction*/
	@Test(enabled = true, priority = 5)	
	public void createNewTransaction() throws InterruptedException, Exception
	{
		//		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Bank One ZBA']")));
		getlinktext("Transaction Details").click();

		getxpath("//input[@value=\"New Transaction\"]").click();

		if(getid("newTransactionDetails").isDisplayed())
		{
			getid("referenceID").click();
			getid("referenceID").clear();
			getid("referenceID").sendKeys("Ref01");

			getid("descriptionIDs").click();
			getid("descriptionIDs").clear();
			getid("descriptionIDs").sendKeys("Desc01");

			getid("amountID").click();
			getid("amountID").clear();
			getid("amountID").sendKeys("100");

			Select GLAccount = new Select(getid("glAccountID"));
			GLAccount.selectByVisibleText("1001-00-000 -CASH - BANK ONE ZBA");

			Thread.sleep(3000);
			getid("saveAddButton").click();
		}
	}




	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
}
