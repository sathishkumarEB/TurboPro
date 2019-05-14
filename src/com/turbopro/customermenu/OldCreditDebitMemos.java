package com.turbopro.customermenu;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;

public class OldCreditDebitMemos extends Methods{
	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	public WebDriver driver;
	public String baseurl= "http://qa.tt.eb.local/turbotracker/turbo/";
	//	public WebDriverWait getWait()
	//	{
	//		return new WebDriverWait(driver, 60);
	//	}

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		Proxy proxy = new Proxy();// Create a new proxy object and set the proxy
		proxy.setSslProxy("sysvines006.sysvine.local:3128");
		proxy.setSocksUsername("harini_shankar");
		proxy.setSocksPassword("vine@2017");
		capabilities.setCapability("proxy", proxy);//Add the proxy to our capabilities 
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize(); // driver = new ChromeDriver();
	}

	// To login to TurboPro site
	@Test( enabled = true, priority = 1)
	public void creditDebitMemo() throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");

		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//input[@value = 'Login']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainMenuCompanyPage']/a")));
		navigateCustomerCreditDebitMemo();
	}

	// Create credit memo
	@Test( enabled = true, priority = 2)
	public void createCreditMemo() throws InterruptedException 
	{
		driver.findElement(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")).click();// To add memo
		driver.findElement(By.id("customerID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("customerID")).sendKeys("Applegate EDM, Inc.");// Enter customer 
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'memotypeID']")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'memotypeID']"))).click().build().perform();
		Thread.sleep(2000);
		Select memotype = new Select(driver.findElement(By.id("memotypeID")));
		Thread.sleep(2000);
		memotype.selectByVisibleText(" Credit ");// Select memo type
		act.moveToElement(driver.findElement(By.id("amountID"))).doubleClick().perform();//driver.findElement(By.id("amountID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("amountID")).sendKeys("400");// Enter amount
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'glAccountsID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'glAccountsID']"))).click().build().perform();
		Thread.sleep(2000);
		Select glaccount = new Select(driver.findElement(By.id("glAccountsID")));
		Thread.sleep(2000);
		glaccount.selectByValue("125");// Select GL Account
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'salesmanID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'salesmanID']"))).click().build().perform();
		Thread.sleep(2000);
		Select salesman = new Select(driver.findElement(By.id("salesmanID")));
		Thread.sleep(2000);
		salesman.selectByVisibleText("Chad Litersky");// Select sales man
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'divisonID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'divisonID']"))).click().build().perform();
		Thread.sleep(2000);
		Select division = new Select(driver.findElement(By.id("divisonID")));
		Thread.sleep(2000);
		division.selectByVisibleText("BI Fort Worth");// Select division
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@"+"id= 'taxterritoryID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'taxterritoryID']"))).click().build().perform();
		Thread.sleep(2000);
		Select taxterritory = new Select(driver.findElement(By.id("taxterritoryID")));
		Thread.sleep(2000);
		taxterritory.selectByVisibleText("Fort Worth TX");// Select tax territory
		driver.findElement(By.id("savememo")).click();// click save&close button
	}

	//	 Edit existing credit memo
	@Test( enabled = true, priority = 3)
	public void editCreditMemo() throws InterruptedException 
	{
		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
		WebElement openJobs = driver.findElement(By.xpath("//*[@id= 'searchJob']"));
		Actions action = new Actions(driver);
		action.moveToElement(openJobs).build().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
		driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
		driver.findElement(By.id("notestextareaID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("notestextareaID")).clear();
		driver.findElement(By.id("notestextareaID")).sendKeys("test");// Edit memo by adding notes
		driver.findElement(By.id("editmemo")).click();// click save&close button	
	}

	// View Memo	
	@Test( enabled = true, priority = 4)
	public void viewMemo() throws InterruptedException 
	{
		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
		WebElement openJobs = driver.findElement(By.xpath("//*[@id= 'searchJob']"));
		Actions action = new Actions(driver);
		action.moveToElement(openJobs).build().perform();
		driver.findElement(By.id("searchJob")).click();
		driver.findElement(By.id("searchJob")).clear();
		driver.findElement(By.id("searchJob")).sendKeys("CR101323");// Enter memo number
		Thread.sleep(2000);
		driver.findElement(By.id("goSearchButtonID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
		driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
		driver.findElement(By.id("editmemo")).click();// click save&close button	
		Thread.sleep(2000);
	}

	// Create Debit memo
	@Test( enabled = true, priority = 5)
	public void createDebitMemo() throws InterruptedException 
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")));
		driver.findElement(By.xpath("//input[@onclick = 'createNewcreditdebitmemo()' ]")).click();// To add memo
		driver.findElement(By.id("customerID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("customerID")).sendKeys("Beco Service Inc - BAS/DMW");// Enter customer 
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'memotypeID']")));
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'memotypeID']"))).click().build().perform();
		Thread.sleep(2000);
		Select memotype = new Select(driver.findElement(By.id("memotypeID")));
		Thread.sleep(2000);
		memotype.selectByVisibleText(" Debit ");// Select memo type
		act.moveToElement(driver.findElement(By.id("amountID"))).doubleClick().perform();//driver.findElement(By.id("amountID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("amountID")).sendKeys("4400");// Enter amount
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'glAccountsID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'glAccountsID']"))).click().build().perform();
		Thread.sleep(2000);
		Select glaccount = new Select(driver.findElement(By.id("glAccountsID")));
		Thread.sleep(2000);
		glaccount.selectByValue("125");// Select GL Account
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'salesmanID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'salesmanID']"))).click().build().perform();
		Thread.sleep(2000);
		Select salesman = new Select(driver.findElement(By.id("salesmanID")));
		Thread.sleep(2000);
		salesman.selectByVisibleText("Chad Litersky");// Select sales man
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'divisonID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'divisonID']"))).click().build().perform();
		Thread.sleep(2000);
		Select division = new Select(driver.findElement(By.id("divisonID")));
		Thread.sleep(2000);
		division.selectByVisibleText("BI Fort Worth");// Select division
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id= 'taxterritoryID']")));
		act.moveToElement(driver.findElement(By.xpath("//*[@id= 'taxterritoryID']"))).click().build().perform();
		Thread.sleep(2000);
		Select taxterritory = new Select(driver.findElement(By.id("taxterritoryID")));
		Thread.sleep(2000);
		taxterritory.selectByVisibleText("Fort Worth TX");// Select tax territory
		Thread.sleep(2000);
		driver.findElement(By.id("savememo")).click();// click save&close button
	}

	//	 Edit existing Debit memo
	@Test( enabled = true, priority = 6)
	public void editDebitMemo() throws InterruptedException 
	{
		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("searchJob")));
		WebElement openJobs = driver.findElement(By.xpath("//*[@id= 'searchJob']"));
		Actions action = new Actions(driver);
		action.moveToElement(openJobs).build().perform();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
		driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
		driver.findElement(By.id("notestextareaID")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("notestextareaID")).clear();
		driver.findElement(By.id("notestextareaID")).sendKeys("test");// Edit memo by adding notes
		driver.findElement(By.id("editmemo")).click();// click save&close button	
	}

	// To view PDF
	@Test( enabled = true, priority = 7)
	public void viewPDF() throws InterruptedException 
	{
		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
		driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
		driver.findElement(By.id("pdfIcon")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Close")).click();// click save&close button	
	}

	// Email Memo
	@Test( enabled = true, priority = 8)
	public void emailMemo() throws InterruptedException 
	{
		driver.navigate().refresh();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")));
		driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='creditDebitMemosGrid']/tbody/tr[@id='1']/td[3]"))).doubleClick().perform();
		driver.findElement(By.id("emailIcon")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//*[@id= 'emailpopup']/table/tbody/tr/td[2]/input")).click();
		driver.findElement(By.linkText("Close")).click();

	}
}







