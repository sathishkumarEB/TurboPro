package com.turbopro.modules;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
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

public class ReleaseTab extends Variables {

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

	/*TS-0082 and TS-0083*/
	@Test(enabled = true, priority = 1)
	public void login1() throws InterruptedException 
	{
		login();		
	}

	//editing the Job Name in release tab and saving it
	@Test(enabled = true, priority = 2)
	public void useCase_1() throws InterruptedException 
	{
		driver.findElement(By.id("jobsearch")).sendKeys("feb20");
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(3000);	
		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		/*get("//*[@id='ui-tabs-5']/table/tbody/tr/td/div/form/table/tbody/tr/td/input[@id='jobHeader_JobName_id']").click();
		get("//*[@id='ui-tabs-5']/table/tbody/tr/td/div/form/table/tbody/tr/td/input[@id='jobHeader_JobName_id']").sendKeys("2");*/

		get("//*[@id='ui-tabs-5']/table[4]/tbody/tr[2]/td/input").click();

	}

	//editing Release Notes content and saving it
	@Test(enabled = true, priority = 3)
	public void useCase_2() throws InterruptedException 
	{
		get("//*[@id='releaseNote']").click();
		Thread.sleep(3000);
		get("//*[@id='releaseNote']").sendKeys("s");
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/table[4]/tbody/tr[2]/td/input").click();
		Thread.sleep(3000);
		get("//*[@id='releaseNote']").click();
		Thread.sleep(2000);
		get("//*[@id='releaseNote']").sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/table[4]/tbody/tr[2]/td/input").click();

	}

	//clicking Billing Entire Jobs checkbox 
	@Test(enabled = true, priority = 4)
	public void useCase_3() throws InterruptedException 
	{
		get("//*[@id='billing']").click();
		Thread.sleep(3000);
		get("//*[@id='billing']").click();

	}

	//check PO Amount checkbox 
	@Test(enabled = true, priority = 5)
	public void useCase_4() throws InterruptedException 
	{
		get("//*[@id='poAmountID']").click();
		Thread.sleep(3000);
		get("//*[@id='poAmountID']").click();
		Thread.sleep(3000);
	}

	//check Invoice Amount checkbox 
	@Test(enabled = true, priority = 5)
	public void useCase_5() throws InterruptedException 
	{
		get("//*[@id='invoiceAmountID']").click();
		Thread.sleep(3000);
		get("//*[@id='invoiceAmountID']").click();
		Thread.sleep(3000);
	}

	//check both PO Amount and Invoice Amount checkbox 
	@Test(enabled = true, priority = 6)
	public void useCase_6() throws InterruptedException 
	{
		get("//*[@id='poAmountID']").click();
		Thread.sleep(5000);
		get("//*[@id='invoiceAmountID']").click();
		Thread.sleep(5000);
		get("//*[@id='poAmountID']").click();
		Thread.sleep(5000);
		get("//*[@id='invoiceAmountID']").click();
		Thread.sleep(5000);
	}

	//clicking add button in release 
	@Test(enabled = true, priority = 7)
	public void useCase_7() throws InterruptedException 
	{
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(2000);
		get("//*[@id='openReleaseDig']/form/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(2000);
		get("//*[@id='openReleaseDig']/form/table[2]/tbody/tr/td[5]/input").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(2000);
		get("//body/div[27]/div[1]/a/span").click();

	}

	//clicking edit release button in release 
	@Test(enabled = true, priority = 8)
	public void useCase_8() throws InterruptedException 
	{
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[2]/input").click();
		Thread.sleep(2000);
		get("//*[@id='openReleaseDig']/form/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(2000);
		get("//*[@id='openReleaseDig']/form/table[2]/tbody/tr/td[5]/input").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[2]/input").click();
		Thread.sleep(2000);
		get("//body/div[27]/div[1]/a/span").click();
	}

	//clicking delete button in release 
	@Test(enabled = true, priority = 9)
	public void useCase_9() throws InterruptedException 
	{
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[3]").click();
		Thread.sleep(2000);
		get("//body/div[38]/div[11]/div/button/span").click();
		Thread.sleep(2000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[3]").click();
		Thread.sleep(2000);
		get("//body/div[38]/div[1]/a/span").click();
	}

	
	
	

	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}
