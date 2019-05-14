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
import com.turbopro.basepackages.*;

public class Vendors_Submenu extends Variables {
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

	@Test(enabled = true)
	public void Vendors() throws InterruptedException 
	{
		login();

		//vendors
		WebElement company = get("//*[@id='mainMenuCompanyPage']/a");
		Actions action = new Actions(driver);
		action.moveToElement(company).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]").click();

		get("//*[@id='addvendorlist']").click();
		Thread.sleep(2000);
		get("//*[@id='addNewVendor']/table/tbody/tr/td/input[1]").click();
		Thread.sleep(2000);
		get("//*[@id='addNewVendor']/table/tbody/tr/td/input[2]").click();


		//Purchase Orders

		WebElement company1 = get("//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor = get("//*[@id='mainMenuCompanyPage']/ul/li[2]");

		Actions action1 = new Actions(driver);

		action1.moveToElement(company1).perform();
		action1.moveToElement(vendor).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[1]").click();

		Thread.sleep(5000);

		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input").click();
		get("//*[@value='Close']").click();
		get("//body/div[15]/div[11]/div/button[2]/span").click();

		Thread.sleep(3000);

		get("//*[@id='searchJob']").sendKeys("102228");
		get("//*[@id='search']/table/tbody/tr/td[2]/input[1]").click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[8]")));

		Actions act = new Actions(driver);
		act.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().build().perform();
		Thread.sleep(3000);
		get("//*[@id='lineTab']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();



		//Vendor Pay Bills 
		WebElement company2 = get("//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor1 = get("//*[@id='mainMenuCompanyPage']/ul/li[2]");

		Actions action2 = new Actions(driver);

		action2.moveToElement(company2).perform();
		action2.moveToElement(vendor1).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[2]").click();
		Thread.sleep(4000);
		get("//*[@id='vendorGroupOrUnGroup']").click();
		Thread.sleep(4000);
		get("//*[@id='vendorGroupOrUnGroup']").click();
		Thread.sleep(4000);
		get("//*[@id='filterButton']").click();
		Thread.sleep(4000);
		get("//*[@id='filtertypesDiv']/table/tbody/tr[1]/td/span").click();
		Thread.sleep(4000);
		get("//*[@id='filterButton']").click();
		Thread.sleep(4000);
		get("//*[@id='filtertypesDiv']/table/tbody/tr[2]/td/span").click();
		Thread.sleep(4000);
		get("//*[@id='filterButton']").click();
		Thread.sleep(4000);
		get("//*[@id='filtertypesDiv']/table/tbody/tr[3]/td/span").click();
		Thread.sleep(4000);


		//Vendor Invoice and Bills 
		WebElement company3 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement vendor2 = get(".//*[@id='mainMenuCompanyPage']/ul/li[2]");

		Actions action3 = new Actions(driver);

		action3.moveToElement(company3).perform();
		action3.moveToElement(vendor2).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]").click();
		Thread.sleep(3000);

		Actions act1 = new Actions(driver);
		act1.moveToElement(get("//*[@id='invoicesGrid']/tbody/tr[2]/td[6]")).doubleClick().build().perform();

		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(3000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();
		Thread.sleep(5000);
		get("//*[@id='vendorAccountList']").click();
		get("//*[@id='vendorAccountList']/option[2]").click();
		Thread.sleep(4000);
		get("//*[@id='accountsPayableImgID']/img").click();
		Thread.sleep(4000);
		get("//*[@id='vendorAccountList']").click();
		get("//*[@id='vendorAccountList']/option[5]").click();
		Thread.sleep(4000);
		get("//*[@id='UninvoicedForm']/table[1]/tbody/tr/td/div/input[4]").click();
		get("//body/div[15]/div[1]/a/span").click();
		Thread.sleep(4000);
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='saveTermsokButton']").click();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(4000);
		//	get("//tbody/tr[2]/td//*[@id='saveTermsButton']")).click();
		get("//*[@id='addNewVeInvFmDlgclsbuttonwithoutpo']").click();


	}

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}