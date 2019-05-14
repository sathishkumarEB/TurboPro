package Login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class APmatchGL {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public static WebDriver driver;

	// public String baseurl= "http://qa.tt.eb.local/turbotracker/turbo/";

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); 		// to disable user logging

		driver = new ChromeDriver();
		//		driver.manage().window().maximize();
	}

	@Test(priority=1, enabled=true)
	public void login() {

		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@Test(priority=2, enabled = true)
	public void accountsPayable() throws InterruptedException {

		WebElement company = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"));
		WebElement vendor = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"));
		WebElement invoicebills = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]/a"));

		Actions action = new Actions(driver);

		action.moveToElement(company).perform();
		action.moveToElement(vendor).perform();
		action.click(invoicebills).perform();				//navigating to vendor invoices and bills

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		Select dropdown = new Select(driver.findElement(By.id("vendorAccountList")));
		dropdown.selectByValue("1");					//selecting accounts payable
		Thread.sleep(20000);
		String getTxt = driver.findElement(By.xpath("//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt);

	}


	@Test(priority=3, enabled = true)
	public void january() throws InterruptedException
	{
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("01/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt1 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt1);
	}


	@Test(priority=4, enabled = true)
	public void february() throws InterruptedException
	{
		driver.findElement(By.id("toDate")).clear();
		driver.findElement(By.id("toDate")).sendKeys("02/28/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt2 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt2);
	}


	@Test(priority=5, enabled = true)
	public void march() throws InterruptedException
	{
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("03/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt3 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt3);
	}


	@Test(priority=6, enabled = true)
	public void april() throws InterruptedException
	{
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("04/30/2017" + Keys.ENTER);
		driver.findElement(By.id("toDate")).sendKeys(Keys.ENTER);
		Thread.sleep(20000);
		String getTxt3 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt3);
	}


	@Test(priority=7, enabled = false)
	public void may() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("05/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt4 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt4);

	}


	@Test(priority=8, enabled = false)
	public void june() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("06/30/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt5 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt5);
	}


	@Test(priority=9, enabled = false)
	public void july() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("07/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt6 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt6);
	}

	@Test(priority=10, enabled = false)
	public void august() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("08/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt7 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt7);
	}


	@Test(priority=11, enabled = false)
	public void september() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("09/30/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt8 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt8);
	}

	@Test(priority=12, enabled = false)
	public void october() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("10/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt9 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt9);
	}


	@Test(priority=13, enabled = false)
	public void november() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("11/30/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt10 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt10);
	}


	@Test(priority=14, enabled = false)
	public void december() throws InterruptedException
	{	
		driver.findElement(By.id("dateRange")).click();
		driver.findElement(By.id("toDate")).sendKeys("10/31/2017" + Keys.ENTER);
		Thread.sleep(20000);
		String getTxt11 = driver.findElement(By.xpath(".//*[@id='gview_accountsPayableGrid']/div[4]/div/table/tbody/tr/td[12]")).getText();
		System.out.println(getTxt11);
	}



	/*	  WebElement company1 = driver.findElement(By.xpath("//div[@class='app_main_navi']/ul/li[6]/a")); 
				  WebElement generalledger = driver.findElement(By.xpath("//div[@class='app_main_navi']/ul/li[6]/ul/li[10]/a"));
				  action.moveToElement(company1).perform();
				  action.click(generalledger).perform();			//navigating to general ledger

				  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

				  driver.findElement(By.id("mostRecentPeriodsID")).click();
				  driver.findElement(By.id("jqg_chartsOfAccountsGridGLledger_47")).click();
				  driver.findElement(By.id("saveDDButton")).click();			//getting account using most recent period

				  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/

	/*driver.findElement(By.id("periodAndYearID")).click();
				  Select from = new Select(driver.findElement(By.id("periodsId")));
				  from.selectByValue("4");

				  Select year = new Select(driver.findElement(By.id("yearID")));
				  year.selectByValue("2017");
				  driver.findElement(By.id("saveDDButton")).click();*/










	@AfterTest
	public void teardown() {
		// driver.quit();
	}

}

// Script for comparing AP and GL
