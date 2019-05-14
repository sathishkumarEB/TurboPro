package com.turbopro.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.turbopro.MethodsLibrary.Methods;

public class BankingDetails extends Methods{
	private StringBuffer verificationErrors = new StringBuffer();
	String ourPO = "";

	private String Url, UName, Password, Description,  AccountType, Asset, Interest, Fees, DepositAccount, Reference;
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
		AccountType= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"AccountType")).toString();
		Asset= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Asset")).toString();
		DepositAccount= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"DepositAccount")).toString();
		Interest= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Interest")).toString();
		Fees= srcBook.getSheetAt(0).getRow(1).getCell(ColumnNumber(srcBook,0,0,"Fees")).toString();
	}

	/*Code for Excel sheet*/
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

	/*TC_BD_0001 - logging into the application and navigate to Banking*/
	@Test(enabled = true, priority = 1)	
	public void navigateBanking() throws InterruptedException, Exception {
		loggingIn(Url, UName, Password);  
		getxpath("//*[@id='mainmenuBankingPage']/a").click();  
	}

	/*TC_BD_0002 - View different bank details*/
	@Test(enabled = true, priority = 2)	
	public void viewBanking() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'Bank One Payroll']").click();  	
			getxpath("//td[@title = 'PLAINS CAPITAL BANK PAYROLL']").click();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_BD_0003 - Add new banking account*/
	@Test(enabled = true, priority = 3)	
	public void addBank() throws InterruptedException, Exception {
		try{
			getid("addChartlist").click(); 
			getid("descriptionID").click();
			getid("descriptionID").clear();
			getid("descriptionID").sendKeys(Description); 
			getid("typeID").click();
			Select Type = new Select(getid("typeID"));
			Type.selectByVisibleText(AccountType);  
			getid("assetAccountID").click();
			Select asset = new Select(getid("assetAccountID"));
			asset.selectByVisibleText(Asset);  
			getid("depositDefultAccountID").click();
			Select deposits = new Select(getid("depositDefultAccountID"));
			deposits.selectByVisibleText(DepositAccount); 
			getid("interstDefultAccountID").click();
			Select interest = new Select(getid("interstDefultAccountID"));
			interest.selectByVisibleText(Interest); 
			getid("feesDefultAccountID").click();
			Select fees = new Select(getid("feesDefultAccountID"));
			fees.selectByVisibleText(Fees); 			
			getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_BD_0004 - Delete existing bank account*/
	@Test(enabled = true, priority = 4)	
	public void deleteBank() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'Testing']").click();  
			getxpath("//input[@value = 'Delete']").click();  
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_BD_0005 - Update Bank details*/
	@Test(enabled = true, priority = 5)	
	public void updateBankAccount() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'Bank One Payroll']").click();  
			getid("bankName1ID").click();
			getid("bankName1ID").clear();
			getid("bankName1ID").sendKeys(Reference);  
			getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
			getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_BD_0006 - Inactivate a Bank account*/
	@Test(enabled = true, priority = 6)	
	public void inactivateBankAccount() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(!getid("inActiveID").isSelected())
			{
				getid("inActiveID").click(); 
				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*TC_BD_0007 - Activate an inactive Bank account*/
	@Test(enabled = true, priority = 7)	
	public void activateBankAccount() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(getid("inActiveID").isSelected())
			{
				getid("inActiveID").click(); 
				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Add account holder name and address*/
	@Test(enabled = true, priority = 8)	
	public void addAccountHolderNameAndAddress() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(getid("accountHolder1ID").isDisplayed())
			{
				getid("accountHolder1ID").click(); 
				getid("accountHolder1ID").clear();
				getid("accountHolder1ID").sendKeys("Test Address one");
				getid("accountHolder2ID").sendKeys("Test Address two");
				getid("accountHolder3ID").sendKeys("Test Address three");
				getid("accountHolder4ID").sendKeys("Test Address four");
				getid("accountHolder5ID").sendKeys("Test Address five");
				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Add account holder name and address*/
	@Test(enabled = true, priority = 9)	
	public void removeAccountHolderNameAndAddress() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(getid("accountHolder1ID").isDisplayed())
			{
				getid("accountHolder1ID").click(); 
				getid("accountHolder1ID").clear();
				getid("accountHolder2ID").clear();
				getid("accountHolder3ID").clear();
				getid("accountHolder4ID").clear();
				getid("accountHolder5ID").clear();
				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*Add bank name and address */
	@Test(enabled = true, priority = 10)	
	public void addBankNameAndAddress() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(getid("bankName1ID").isDisplayed())
			{
				getid("bankName1ID").click(); 
				getid("bankName1ID").clear();
				getid("bankName1ID").sendKeys("Bank address test1");
				getid("bankName2ID").sendKeys("Bank address test2");
				getid("bankName3ID").sendKeys("Bank address test3");
				getid("bankName4ID").sendKeys("Bank address test4");
				getid("bankName5ID").sendKeys("Bank address test5");

				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	/*Re bank name and address */
	@Test(enabled = true, priority = 11)	
	public void removeBankNameAndAddress() throws InterruptedException, Exception {
		try{
			getxpath("//td[@title = 'PLAINS CAPITAL BANK ZBA']").click();  

			if(getid("bankName1ID").isDisplayed())
			{
				getid("bankName1ID").click(); 
				getid("bankName1ID").clear();
				getid("bankName2ID").clear();
				getid("bankName3ID").clear();
				getid("bankName4ID").clear();
				getid("bankName5ID").clear();

				getxpath("//input[@onclick = 'saveBankingDetails()']").click();  
				getxpath("//div[(contains(@style,'display: block;'))]/div[11]/div/button[1]").click();  
			}
		}
		catch(Exception e)
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
