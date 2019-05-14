package com.turbopro.modules;


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

public class Customers_Submenu {
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

		WebElement company = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));

		Actions action = new Actions(driver);

		action.moveToElement(company).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]")).click();


		//customer payments

		WebElement company1 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action1 = new Actions(driver);

		action1.moveToElement(company1).perform();
		action1.moveToElement(customers).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[1]")).click();
		Thread.sleep(9000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='cancelPaymentId']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='addpaymentlist']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='cancelPaymentId']")).click();


		//customer unapplied payments 

		WebElement company2 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers2 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action2 = new Actions(driver);

		action2.moveToElement(company2).perform();
		action2.moveToElement(customers2).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[2]")).click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions act1 = new Actions(driver);
		act1.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().build().perform();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='saveInvoiceId']")).click();

		Thread.sleep(4000);

		//customer statements 
		WebElement company3 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers3 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));


		Actions action3 = new Actions(driver);

		action3.moveToElement(company3).perform();
		action3.moveToElement(customers3).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[3]")).click();

		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='statementsForm']/table/tbody/tr[5]/td/input[2]")).click();



		//customer sales order

		WebElement company4 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers4 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));


		Actions action4 = new Actions(driver);

		action4.moveToElement(company4).perform();
		action4.moveToElement(customers4).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[4]")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[7]")));
		Actions act2 = new Actions(driver);
		act2.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[7]"))).doubleClick().build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='generalSOTabPDF']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='contactEmailID_general']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[14]/div[1]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='POReleaseID']")).click();


		//customer invoice 
		WebElement company5 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers5 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));


		Actions action5 = new Actions(driver);

		action5.moveToElement(company5).perform();
		action5.moveToElement(customers5).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[5]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, 15);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));
		Actions act3 = new Actions(driver);
		act3.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[8]"))).doubleClick().build().perform();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='cICheckTab2']/a")).click();

		driver.findElement(By.xpath("//*[@id='imgInvoicePDF']/input")).click();
		Thread.sleep(3000);
		//		driver.findElement(By.xpath("//*[@id='contactEmailID']")).click();
		//		Thread.sleep(3000);
		//		driver.findElement(By.xpath("//body/div[16]/div[1]/a/span")).click();
		//		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='CuInvoiceSaveCloseID']")).click();



		//Finance Charges
		WebElement company6 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers6 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action6 = new Actions(driver);

		action6.moveToElement(company6).perform();
		action6.moveToElement(customers6).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[6]")).click();


		//Tax Adjustments
		WebElement company7 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers7 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action7 = new Actions(driver);

		action7.moveToElement(company7).perform();
		action7.moveToElement(customers7).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[7]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@value='OK']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='txAdForm']/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/input[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='txAdForm']/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/input[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@onclick='cancel()']")).click();
		Thread.sleep(2000);

		
		WebElement company8 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers8 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action8 = new Actions(driver);

		action8.moveToElement(company8).perform();
		action8.moveToElement(customers8).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[7]")).click();

		driver.findElement(By.xpath("//*[@id='cuInvNoid']")).sendKeys("164993R1");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@value='OK']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='txAdForm']/table/tbody/tr/td/fieldset/table/tbody/tr[4]/td/input[1]")).click();


		//Credit Debit Memos
		WebElement company9 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers9 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action9 = new Actions(driver);

		action9.moveToElement(company9).perform();
		action9.moveToElement(customers9).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[8]")).click();

		WebDriverWait wait3 = new WebDriverWait(driver, 15);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions act4 = new Actions(driver);
		act4.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().build().perform();
		driver.findElement(By.xpath("//*[@id='editmemo']")).click();

		driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/input")).click();
		driver.findElement(By.xpath("//*[@id='savememo']")).click();
		driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='searchJob']")).sendKeys("CR101100");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='editmemo']")).click();


		// Sales Order Template 

		WebElement company10 = driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"));
		WebElement customers10 = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]"));

		Actions action10 = new Actions(driver);

		action10.moveToElement(company10).perform();
		action10.moveToElement(customers10).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[9]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[1]/input")).click();
		driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[3]/input[2]")).click();
		driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[3]/input[3]")).click();

		driver.findElement(By.xpath("//*[@id='1']/td[3]")).click();
		driver.findElement(By.xpath("//*[@id='showhidePrice']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='showhidePrice']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='showOrderPointsButtons']/table/tbody/tr/td[3]/input[2]")).click();



	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}