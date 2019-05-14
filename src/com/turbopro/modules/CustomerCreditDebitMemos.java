package com.turbopro.modules;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.webdriven.commands.Click;
import com.turbopro.basepackages.*;


public class CustomerCreditDebitMemos extends Variables {

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

	@Test(enabled = true, priority = 1)
	public void login1() throws InterruptedException 
	{
		login();		
	}

	@Test(enabled = true, priority = 2)
	public void memo1() throws InterruptedException 
	{
		WebElement company9 = get("//*[@id='mainMenuCompanyPage']/a");
		WebElement customers9 = get(".//*[@id='mainMenuCompanyPage']/ul/li[1]");

		Actions action9 = new Actions(driver);

		action9.moveToElement(company9).perform();
		action9.moveToElement(customers9).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[8]").click();
	}

	@Test(enabled = false, priority = 3)
	public void usecase1() throws InterruptedException 
	{
		WebDriverWait wait3 = new WebDriverWait(driver, 15);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[4]")));

		Actions act4 = new Actions(driver);
		act4.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().build().perform();
		driver.findElement(By.xpath("//*[@id='editmemo']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='savememo']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='searchJob']")).sendKeys("CR101181");
		Thread.sleep(4000);
		get("//*[@id='goSearchButtonID']").click();
		act4.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[4]"))).doubleClick().build().perform();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/ul[14]/li/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='editmemo']")).click();
	}

	@Test(enabled = true, priority = 4)
	public void usecase2() throws InterruptedException 
	{
		get("//body/div[1]/div[2]/div[1]/input").click();
		Thread.sleep(3000);
		get("//*[@id='customerID']").sendKeys("Cooper Sheet");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='memotypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='memotypeID']/option[@value = 1]").click();
		Thread.sleep(3000);
		get("//*[@id='DescriptionID']").sendKeys("test");
		Thread.sleep(3000);
		get("//*[@id='notestextareaID']").sendKeys("test");
		Thread.sleep(3000);
		get("//*[@id='amountID']").clear();
		get("//*[@id='amountID']").sendKeys("10000000");
		Thread.sleep(3000);
		get("//*[@id='taxterritoryID']").click();
		get("//*[@id='taxterritoryID']/option[@value=61]").click();
		Thread.sleep(3000);
		get("//*[@id='glAccountsID']").click();
		get("//*[@id='glAccountsID']/option[@value=130]").click();
		Thread.sleep(3000);
		get("//*[@id='salesmanID']").click();
		get("//*[@id='salesmanID']/option[@value=10]").click();
		Thread.sleep(3000);
		get("//*[@id='divisonID']").click();
		get("//*[@id='divisonID']/option[@value=2]").click();
		Thread.sleep(3000);
		get("//*[@id='savememo']").click();

	}


	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}
