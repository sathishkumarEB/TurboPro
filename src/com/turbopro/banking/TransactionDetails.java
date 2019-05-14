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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class TransactionDetails extends Methods {
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Amount, Reason, Description, Reference, CustomerName;
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
		Amount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Amount")).toString();
		Reason= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reason")).toString();
		CustomerName= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"CustomerName")).toString();
		Reference= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Reference")).toString();
		Description= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Description")).toString();
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

	/*TC_BT_0001 - logging into the application and navigate to Banking Transactions*/
	@Test(enabled = true, priority = 1)	
	public void navigateBankingTransactions() throws InterruptedException, Exception
	{
		loggingIn(Url, UName, Password);
		Thread.sleep(3000);
		getxpath("//*[@id='mainmenuBankingPage']/a").click();
		getlinktext("Transaction Details").click();
	}

	/*TC_BT_0002 - View transaction details*/
	@Test(enabled = true, priority = 2)	
	public void viewTransactionDetails() throws InterruptedException, Exception
	{
		getxpath("//td[@title = 'Bank One Operating']").click();
		getxpath("//td[@title = 'Bank One Payroll']").click();
	}

	/*TC_BT_0003- Filter transactions details*/
	@Test(enabled = true, priority = 3)	
	public void filterTransactionDetails() throws InterruptedException, Exception
	{
		getxpath("//td[@title = 'Bank One Operating']").click();
		getxpath("//input[@value= 'Transaction Filter']").click();
		getid("transactionfilterSelectBox1").click();
		Select transactionFilter = new Select(getid("transactionfilterSelectBox1"));
		transactionFilter.selectByVisibleText("Amount");
		getid("transactionfiltertextboxID").click();
		getid("transactionfiltertextboxID").clear();
		getid("transactionfiltertextboxID").sendKeys(Amount);
		getid("transactionDetailsID").click();
		getxpath("//body/div[13]/div[1]/a/span").click();
		getid("trasactionCancelID").click();
	}

	/*TC_BT_0004 - start new transaction*/
	@Test(enabled = true, priority = 4)	
	public void startTransaction() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='New Transaction']")));
		getxpath("//input[@value='New Transaction']").click();
		getid("referenceID").click();
		getid("referenceID").clear();
		getid("referenceID").sendKeys(Reference);
		getid("descriptionIDs").click();
		getid("descriptionIDs").clear();
		getid("descriptionIDs").sendKeys(Description);
		getid("amountID").click();
		getid("amountID").clear();
		getid("amountID").sendKeys(Amount);
		getid("glAccountID").click();
		Select glAccount = new Select(getid("glAccountID"));
		glAccount.selectByValue("561");
		getid("saveAddButton").click();
	}

	/*TC_BT_0005 - Write new check*/
	@Test(enabled = true, priority = 5)	
	public void writeNewCheck() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		getlinktext("Transaction Details").click();
		getxpath("//td[@title = 'testingbanking']").click();
		getxpath("//input[@value='New Check']").click();
		getid("glAccountCheckID").click();
		Select glAccountcheck = new Select(getid("glAccountCheckID"));
		glAccountcheck.selectByValue("130");
		getid("checkamountID").click();
		getid("checkamountID").clear();
		getid("checkamountID").sendKeys(Amount);
		getid("paytoID").click();
		getid("paytoID").clear();
		getid("paytoID").sendKeys(CustomerName);
		getxpath("//input[@id= 'savecheckButton']").click();
	}

	/*TC_BT_0006 - void check*/
	@Test(enabled = true, priority = 6)	
	public void voidCheck() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		getxpath("//td[@title = 'PLAINS CAPITAL BANK OPERATING']").click();
		getlinktext("Transaction Details").click();
		getxpath("//table[@id = 'transactionRegisterGrid']/tbody/tr[2]/td[8]").click();
		getxpath("//input[@value = 'Void Check']").click();
		getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
	}

	/*TC_BT_0007 - Edit transaction*/
	@Test(enabled = false, priority = 7)	
	public void editTransaction() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();
		getlinktext("Transaction Details").click();
		getxpath("//table[@id = 'transactionRegisterGrid']/tbody/tr[2]/td[8]").click();
		getxpath("//input[@onclick = 'editTransaction()']").click();
		if(getxpath("//b[text()='Transaction Rollback.You cant edit']").isDisplayed())
		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
		}
		else
		{
			getid("withdrawalID").click();
			getid("saveEditButton").click();
			getid("reasonttextid").click();
			getid("reasonttextid").clear();
			getid("reasonttextid").sendKeys(Reason);
			getxpath("//body/div[11]/div[11]/div/button/span").click();
		}
	}

	/*TC_BT_0008 - Delete transaction*/
	@Test(enabled = false, priority = 8)	
	public void deleteTransaction() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();
		getlinktext("Transaction Details").click();
		getxpath("//table[@id = 'transactionRegisterGrid']/tbody/tr[2]/td[8]").click();
		getxpath("//input[@value = 'Delete Transaction']").click();
		if(getxpath("//span[text()='Information']").isDisplayed())
		{
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();
		}
		else
		{		
			getid("reasonttextid").click();
			getid("reasonttextid").clear();
			getid("reasonttextid").sendKeys(Reason);
			getxpath("//body/div[11]/div[11]/div/button/span").click();
			if(getxpath("//span[text()='Confirm Delete']").isDisplayed())
			{
				getxpath("//body/div[14]/div[11]/div/button[1]/span").click();
			}
		}
	}

	/*Apply transaction filter for reconcile and view pdf, csv report */
	@Test(enabled = true, priority = 9)	
	public void transactionFilterForReconcile() throws InterruptedException, Exception
	{
		driver.navigate().refresh();
		Thread.sleep(3000);
		getxpath("//*[@id='mainmenuBankingPage']/a").click();
		getlinktext("Transaction Details").click();

		getxpath("//input[@value='Transaction Filter']").click();

		Select transfilter = new Select (getid("transactionfilterSelectBox1"));
		transfilter.selectByVisibleText("Cleared Status");
		Thread.sleep(3000);
		Select transfilter1 = new Select (getid("transfilTBdynamic"));
		transfilter1.selectByVisibleText("Reconcile");

		getid("transactionDetailsID").click();
		Thread.sleep(4000);

		getxpath("//*[@id='findbytransactionRegisterGridPager_left']/table/tbody/tr/td[1]/div").click();
		Thread.sleep(5000);

		getxpath("//*[@id='findbytransactionRegisterGridPager_left']/table/tbody/tr/td[2]/div").click();
		parentWindow();
		Thread.sleep(3000);
		getxpath("//body/div[13]/div[1]/a/span").click();
	}


	/*Apply transaction filter for unreconcile and view pdf, csv report */
	@Test(enabled = true, priority = 10)	
	public void transactionFilterForUnreconcile() throws InterruptedException, Exception
	{

		Thread.sleep(3000);
		Select transfilter1 = new Select (getid("transfilTBdynamic"));
		transfilter1.selectByVisibleText("Unreconciled");

		getid("transactionDetailsID").click();
		Thread.sleep(4000);

		getxpath("//*[@id='findbytransactionRegisterGridPager_left']/table/tbody/tr/td[1]/div").click();
		Thread.sleep(5000);

		getxpath("//*[@id='findbytransactionRegisterGridPager_left']/table/tbody/tr/td[2]/div").click();
		parentWindow();
		Thread.sleep(3000);
		getxpath("//body/div[13]/div[1]/a/span").click();
	}




	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
}
