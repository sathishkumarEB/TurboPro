package com.turbopro.basepackages;

import org.openqa.selenium.By;
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

public class Variables {
	public static WebDriver driver;


	//strings for open buttons in sales tab
	public static String openingUpcomingBids      = 	"//tbody/tr[2]/td[1]/li/div/span/input";
	public static String pendingJobs      		  = 	"//tbody/tr[2]/td[2]//li/div/span/input";
	public static String quotedJobs      		  = 	"//tbody/tr[3]/td[2]//li/div/span/input";
	public static String awardedContractors       = 	"//tbody/tr[3]/td[3]//li/div/span/input";

	public static String newJob       = 	"//*[@id='intro']/div[3]/div/div/table/tbody/tr[5]/td[2]/label[1]/a";






	//shortcut for get element by xpath  
	public static WebElement get(String element) {
		return driver.findElement(By.xpath(element));
	}







	//creating a job using quickbook for customer venture mechanical
	public void CreateJobUsingQuickBook() throws InterruptedException 
	{

		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[5]/td[2]/label[3]/a").click();
		get("//*[@id='QBSaveID']").click();
		get("//*[@id='Quickbookprojectid']").sendKeys("test");

		get("//*[@id='QBSaveID']").click();
		get("//*[@id='QuickbookDivisionid']").click();
		get("//*[@id='QuickbookDivisionid']/option[2]").click();
		get("//*[@id='QBSaveID']").click();
		get("//*[@id='QuickbookCustomer_name']").sendKeys("venture mechanical");
		Thread.sleep(3000);
		get("//body/ul[18]/li[1]/a").click();
		get("//*[@id='QBSaveID']").click();
		Thread.sleep(4000);

	}


	//method for login as user temp
	public void loginTemp() throws InterruptedException 
//	public void loginTemp(String baseUrl) throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("temp");
		driver.findElement(By.id("pwd")).sendKeys("Barcorp#");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(5000);
		/*if(isElementPresent(By.id("")))
		{
			return true;
			
		}
		else
		{
			
		}*/
	}


	//method for login
	public void login() throws InterruptedException 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(5000);

	}

	//method to logout
	public void logout() throws InterruptedException
	{
		WebElement logout = get("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/a/img");
		Actions action4 = new Actions(driver);
		action4.moveToElement(logout).perform();
		get("//*[@id='turbo_app_header']/div[2]/div[1]/ul/li[1]/ul/li[3]/a").click();
	}

	//method for navigating to vendors list
	public void vendors() throws InterruptedException
	{
		WebElement company = get("//*[@id='mainMenuCompanyPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(company).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]").click();
	}

	//method for navigating to vendors - purchase orders
	public void purchaseOrders() throws InterruptedException
	{
		//Purchase Orders
		WebElement company1 = get("//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor = get("//*[@id='mainMenuCompanyPage']/ul/li[2]");
		Actions action1 = new Actions(driver);
		action1.moveToElement(company1).perform();
		action1.moveToElement(vendor).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[1]").click();

	}

	//method to access receive inventory
	public void receiveInventory() throws InterruptedException 
	{

		WebElement inventory2 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action2 = new Actions(driver);
		action2.moveToElement(inventory2).perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[3]/a").click();		//navigating to receive inventory
	}


	//method to access Vendor Invoice and Bills 
	public void vendorInvoice_Bills() throws InterruptedException 
	{
		WebElement company3 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor2 = get(".//*[@id='mainMenuCompanyPage']/ul/li[2]");
		Actions action3 = new Actions(driver);
		action3.moveToElement(company3).perform();
		action3.moveToElement(vendor2).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]").click();
	}

	//method for waiting until the element is visible or not
	public WebDriverWait getWait()
	{
		return new WebDriverWait(driver, 60);
	}


	//method to navigate to Inventory page
	public void Inventory() throws InterruptedException 
	{
		driver.findElement(By.id("mainmenuInventoryPage")).click();
	}

	//method to navigate to warehouse in inventory
	public void inventoryWarehouse() throws InterruptedException
	{
		driver.navigate().refresh();
		WebElement inventory1 = get("//*[@id='mainmenuInventoryPage']/a");
		Actions action1 = new Actions(driver);
		action1.moveToElement(inventory1).build().perform();
		get("//*[@id='mainmenuInventoryPage']/ul/li[2]/a").click(); 
	}


	//method to navigate to sales tab
	public void salesTab() throws InterruptedException 
	{
		driver.findElement(By.xpath(".//*[@id='mainMenuSalesPage']/a")).click();
	}

	//method to click bidlist
	public void bidList() throws InterruptedException 
	{
		Actions act = new Actions(driver);	
		act.moveToElement(driver.findElement(By.xpath(".//*[@id='1']/td[2]"))).doubleClick().build().perform();
		driver.findElement(By.xpath(".//*[@id='viewBLButton']")).click();
	}

	//method to navigate to projects tab
	public void projectsTab() throws InterruptedException 
	{
		driver.findElement(By.id("mainMenuProjectsPage")).click();
	}

	//method to navigate to Company -> Users
	public void companyUsers() throws InterruptedException
	{
		WebElement company3 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action3 = new Actions(driver);
		action3.moveToElement(company3).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[5]").click();
	}


	//method to navigate to Tax Territory 
	public void taxTerritory() throws InterruptedException
	{
		WebElement company7 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action7 = new Actions(driver);
		action7.moveToElement(company7).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[9]").click();
	}



	//method to navigate to Company -> settings
	public void companySettings() throws InterruptedException 
	{
		WebElement company4 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action4 = new Actions(driver);
		action4.moveToElement(company4).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[6]").click();
	}

	//General Ledger

	public void generalLedgerTab() throws InterruptedException
	{
		WebElement company8 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action8 = new Actions(driver);
		action8.moveToElement(company8).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[10]").click();
		Thread.sleep(5000);
	}

	//method to navigate to customer payments module
	public void customerPayments() throws InterruptedException
	{
		WebElement company1 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action1 = new Actions(driver);

		action1.moveToElement(company1).perform();
		action1.moveToElement(customers).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[1]")).click();
	}

	//method to navigate to customer unapplied payments module 
	public void customerUnappliedPayments() throws InterruptedException
	{
		WebElement company2 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers2 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action2 = new Actions(driver);

		action2.moveToElement(company2).perform();
		action2.moveToElement(customers2).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[2]")).click();
	}

	//method to navigate to sales order under customer menu
	public void salesOrder() throws InterruptedException
	{
		WebElement company4 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers4 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));
		Actions action4 = new Actions(driver);
		action4.moveToElement(company4).perform();
		action4.moveToElement(customers4).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[4]")).click();
	}


	//method to navigate to customer invoices list (outside the job)

	public void customerInvoices() throws InterruptedException
	{
		WebElement company5 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers5 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action5 = new Actions(driver);

		action5.moveToElement(company5).perform();
		action5.moveToElement(customers5).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[5]")).click();
	}

	//method for navigating to credit debit memo
	public void creditDebitMemo() throws InterruptedException
	{
		WebElement company8 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers8 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action8 = new Actions(driver);

		action8.moveToElement(company8).perform();
		action8.moveToElement(customers8).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[8]")).click();
	}

	//customer statements 
	public void customerStatements() throws InterruptedException
	{
		WebElement company = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action = new Actions(driver);

		action.moveToElement(company).perform();
		action.moveToElement(customers).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[3]")).click();
	}

	//method for accounts receivable
	public void accountsReceivable() throws InterruptedException 
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='ui-jqgrid-bdiv']//table[@id='viewsGrid']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//*[@id='closear']")).click();
	}






}