package com.turbopro.modules;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class GeneralLedger extends Variables{

	static String driverPath = "C:/Users/sathish_kumar/Downloads/";


	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}


	//Period and Year - Selecting a specific account, drill down to various tabs and export to csv
	@Test(enabled = true, priority = 1)
	public void usecase1() throws InterruptedException 
	{
		login();
		generalLedgerTab();

		get("//*[@id='saveDDButton']").click();
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_1']").click();
		get("//*[@id='saveDDButton']").click();
		get("//*[@id='periodsId']").click();
		get("//*[@id='periodsId']/option[@value='1']").click();
		get("//*[@id='periodsToID']").click();
		get("//*[@id='periodsToID']/option[@value='6']").click();
		get("//*[@id='saveDDButton']").click();
		get("//*[@id='yearID']").click();
		get("//*[@id='yearID']/option[@value='2017']").click();
		get("//*[@id='saveDDButton']").click();

		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")).doubleClick().perform();

		Thread.sleep(3000);
		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='journalBalanceGrid']/tbody/tr[2]/td[4]")).doubleClick().perform();

		Thread.sleep(3000);
		Actions action2 = new Actions(driver);
		action2.moveToElement(get("//*[@id='journalDetailGrid']/tbody/tr[2]/td[4]")).doubleClick().perform();


		get("//*[@id='accountDetailsTab']/a").click();
		Thread.sleep(3000);
		get("//*[@id='exportGenrealLedgerCSVButton']").click();
	}

	//Period and Year - Selecting a different account, fetch account details, to check whether the tabs are getting refreshed
	@Test(enabled = true, priority = 2)
	public void usecase2() throws InterruptedException 
	{

		get("//*[@id='jqg_chartsOfAccountsGridGLledger_1']").click();
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_2']").click();
		get("//*[@id='saveDDButton']").click();
		get("//*[@id='journalBalancesDetailsTab']/a").click();
		get("//*[@id='journalDetailsTab']/a").click();
		get("//*[@id='transactionDetailsTab']/a").click();
		get("//*[@id='budgetsDetailsTab']/a").click();
		Thread.sleep(3000);
		get("//*[@id='transactionNotesDetailsTab']/a").click();
		Thread.sleep(3000);
		get("//*[@id='accountDetailsTab']/a").click();

		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")).doubleClick().perform();
		Thread.sleep(3000);
		get("//*[@id='accountDetailsTab']/a").click();

	}

	//Period and Year - Selecting more than 2 accounts and fetching account details 
	@Test(enabled = true, priority = 3)
	public void usecase3() throws InterruptedException 
	{

		driver.navigate().refresh();
		get("//*[@id='periodsId']").click();
		get("//*[@id='periodsId']/option[@value='1']").click();
		get("//*[@id='periodsToID']").click();
		get("//*[@id='periodsToID']/option[@value='6']").click();
		get("//*[@id='yearID']").click();
		get("//*[@id='yearID']/option[@value='2017']").click();

		get("//*[@id='jqg_chartsOfAccountsGridGLledger_1']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_47']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_11']").click();

		get("//*[@id='saveDDButton']").click();
		WebDriverWait accountGrid = (new WebDriverWait(driver, 30));
		accountGrid.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
		get("//*[@id='exportGenrealLedgerCSVButton']").click();

	}




	//exporting all the account details for selected period 
	@Test(enabled = false, priority = 4)
	public void usecase4() throws InterruptedException 
	{

		get("//*[@id='cb_chartsOfAccountsGridGLledger']").click();
		get("//*[@id='saveDDButton']").click();


		WebDriverWait accountGrid = (new WebDriverWait(driver, 30));
		accountGrid.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")));

		get("//*[@id='exportGenrealLedgerCSVButton']").click();

	}



	//most recent period for specific account
	@Test(enabled = true, priority = 5)
	public void usecase5() throws InterruptedException
	{
		driver.navigate().refresh();

		get("//*[@id='mostRecentPeriodsID']").click();

		get("//*[@id='saveDDButton']").click();

		get("//*[@id='jqg_chartsOfAccountsGridGLledger_47']").click();

		get("//*[@id='saveDDButton']").click();
		WebDriverWait accountGrid = (new WebDriverWait(driver, 30));
		accountGrid.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
		get("//*[@id='exportGenrealLedgerCSVButton']").click();

	}

	//most recent period and selecting all the chart of accounts
	@Test(enabled = false, priority = 6)
	public void usecase6() throws InterruptedException
	{
		driver.navigate().refresh();
		get("//*[@id='mostRecentPeriodsID']").click();
		get("//*[@id='cb_chartsOfAccountsGridGLledger']").click();
		get("//*[@id='saveDDButton']").click();
		WebDriverWait accountGrid = (new WebDriverWait(driver, 30));
		accountGrid.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='drillDownGrid']/tbody/tr[2]/td[4]")));
		get("//*[@id='exportGenrealLedgerCSVButton']").click();


	}

	//fiscal year to date for specific accounts - multiple accounts one by one 
	@Test(enabled = true, priority = 7)
	public void usecase7() throws InterruptedException
	{
		driver.navigate().refresh();

		get("//*[@id='fiscalYearToDate']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_1']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_47']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_11']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='exportGenrealLedgerCSVButton']").click();


	}

	//most recent period and selecting specific accounts more than 2
	@Test(enabled = true, priority = 8)
	public void usecase8() throws InterruptedException
	{
		driver.navigate().refresh();
		get("//*[@id='mostRecentPeriodsID']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_1']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_47']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='jqg_chartsOfAccountsGridGLledger_11']").click();

		get("//*[@id='saveDDButton']").click();
		Thread.sleep(3000);
		get("//*[@id='exportGenrealLedgerCSVButton']").click();

	}




	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}