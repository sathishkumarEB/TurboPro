package ReportsCheck;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Reports {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public WebDriver driver;

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}

	@Test(enabled = true)
	public void login() throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='mainMenuSalesPage']/a")).click();
		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id='viewsGrid']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='bidListFromDate']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[2]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='bidListToDate']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[4]/a")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='viewBLButton']")).click();		//viewing bidlist in projects tab
		Thread.sleep(3000);

		driver.findElement(By.xpath("//body/div[14]/div[1]/a/span")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='mainMenuProjectsPage']/a")).click();
		Thread.sleep(3000);
		Actions act1 = new Actions(driver);
		act1.moveToElement(driver.findElement(By.xpath("//*[@id='viewsGrid']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='accountReceivablepager_left']/table/tbody/tr/td[1]/div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='accountReceivablepager_left']/table/tbody/tr/td[2]/div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='closear']")).click();
		Thread.sleep(3000);


		driver.findElement(By.xpath("//*[@id='SalesRepComboList']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='SalesRepComboList']/option[5]")).click();
		Thread.sleep(3000);
		Actions act2 = new Actions(driver);
		act2.moveToElement(driver.findElement(By.xpath("//*[@id='viewsGrid']/tbody/tr[3]/td[2]"))).doubleClick().build().perform();

		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='CommissionStatementPager_left']/table/tbody/tr/td[1]/div")).click();
		driver.findElement(By.xpath("//*[@id='CommissionStatementPager_left']/table/tbody/tr/td[2]/div")).click();
		driver.findElement(By.xpath("//*[@id='closeCommission']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//body/div[1]/table[2]/tbody/tr[1]/td[5]/input")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[17]/div[1]/span[2]/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix mySpecialClass']/span[@id='myCloseIcon']")).click();


		//navigating to inventory value

		WebElement inventory1 = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(inventory1).perform();
		driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[6]/a")).click();		

		driver.findElement(By.xpath("//body/div[1]/table[1]/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/img")).click();

		//navigating to inventory transfer
		WebElement inventory2 = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
		Actions action2 = new Actions(driver);
		action2.moveToElement(inventory2).perform();
		driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[4]/a")).click();	

		driver.findElement(By.xpath("//*[@id='jqgridLine']/table/tbody/tr/td/table/tbody/tr/td[4]/input")).click();


		//navigating to inventory count
		WebElement inventory3 = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
		Actions action3 = new Actions(driver);
		action3.moveToElement(inventory3).perform();
		driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[7]/a")).click();		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='getPdfButton']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='accountsPayableImgID']/img")).click();


		//navigating to inventory transactions
		WebElement inventory4 = driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"));
		Actions action4 = new Actions(driver);
		action4.moveToElement(inventory4).perform();
		driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/ul/li[8]/a")).click();

		driver.findElement(By.xpath("//*[@id='searchJob']")).sendKeys("DMRR0604");

		driver.findElement(By.xpath("//body/ul[13]/li/a")).click();

		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr/td/input")).click();

		//customer statements 
		WebElement company = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]/a"));
		WebElement statements = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[3]"));

		Actions action = new Actions(driver);

		action.moveToElement(company).perform();
		action.moveToElement(customers).perform();
		action.click(statements).click();				

		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[5]/td/input[2]")).click();


		//customer sales order

		WebElement company4 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers4 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));
		

		Actions action8 = new Actions(driver);

		action8.moveToElement(company4).perform();
		action8.moveToElement(customers4).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[4]")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[7]")));
		Actions act3 = new Actions(driver);
		act3.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[7]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='generalSOTabPDF']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='contactEmailID_general']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[14]/div[1]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='POReleaseID']")).click();


		//vendor purchase order

		WebElement company2 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement vendor = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/a"));
		WebElement purchaseorder = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[1]/a"));

		Actions action6 = new Actions(driver);

		action6.moveToElement(company2).perform();
		action6.moveToElement(vendor).perform();
		action6.click(purchaseorder).click();

		Actions act4 = new Actions(driver);
		act4.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().build().perform();

		driver.findElement(By.xpath("//*[@id='generalTabPDF']")).click();

		driver.findElement(By.xpath("//tbody/tr/td[2]/input[@id='contactEmailID']")).click();
		driver.findElement(By.xpath("//*[@id='POSaveCloseID']")).click();


		//vendor invoice - accounts payable
		WebElement company3 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"));
		WebElement vendor1 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"));
		WebElement invoicebills = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]/a"));

		Actions action7 = new Actions(driver);

		action7.moveToElement(company3).perform();
		action7.moveToElement(vendor1).perform();
		action7.click(invoicebills).perform();				

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='vendorAccountList']")).click();
		driver.findElement(By.xpath("//*[@id='vendorAccountList']/option[2]")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='accountsPayableImgID']/img")).click();
		
		
		driver.findElement(By.xpath("//*[@id='vendorAccountList']")).click();
		driver.findElement(By.xpath("//*[@id='vendorAccountList']/option[5]")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='UninvoicedForm']/table[1]/tbody/tr/td/div/input[4]")).click();
		driver.findElement(By.xpath("//body/div[15]/div[1]/a/span")).click();
		
		
		//employees commission 
		
		WebElement company5 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"));
		WebElement employees = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[3]/a"));
		WebElement commissions = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[3]/ul/li"));

		Actions action9 = new Actions(driver);

		action9.moveToElement(company5).perform();
		action9.moveToElement(employees).perform();
		action9.click(commissions).perform();
		
		driver.findElement(By.xpath("//*[@id='1']/td[7]/div/a/img")).click();
		driver.findElement(By.xpath("//*[@id='1']/td[9]/div/a/img")).click();
		driver.findElement(By.xpath("//*[@id='1']/td[10]/div/a/img")).click();
		driver.findElement(By.xpath("//*[@id='contactEmailID']")).click();
		
		


	}

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}

