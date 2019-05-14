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

public class PermissionsToDeleteJob extends Variables{

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
	public void usecase1() throws InterruptedException   //RID 594
	{
		login();
		Thread.sleep(4000);
		companyUsers();

		get("//*[@id='searchJob']").sendKeys("temp");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='userpermissionDetailsDiv']/a").click();
		Thread.sleep(3000);

		if(get("//*[@id='jobDeleteID']").isSelected())
		{
			get("//*[@id='jobDeleteID']").click();
		}

//		else
//		{
//			get("//*[@id='jobDeleteID']").isDisplayed();
//		}

		if(get("//*[@id='sysAdminIDBox']").isSelected())
		{
			get("//*[@id='sysAdminIDBox']").click();
		}

//		else
//		{
//			get("//*[@id='sysAdminIDBox']").isDisplayed();
//		}

		Thread.sleep(3000);
		get("//*[@id='userDetailsFormId']/div/table[2]/tbody/tr[2]/td/input").click();
		Thread.sleep(3000);
		logout();

		loginTemp();

		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();

		get("//*[@id='quotenameID']").click();

		get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click(); 

		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

		Thread.sleep(3000);

		if(get("//*[@id='deletejob']").isEnabled())
		{
			System.out.println("Delete button is enabled - failed");
		}
		else
		{
			System.out.println("Delete button is disabled - passed");
		}

		Thread.sleep(3000);
		
		get("//*[@id='advancedSearchButton']").click();

		Thread.sleep(3000);
		
		get("//*[@id='bookednameID']").click();

		get("//*[@id='searchAdvJobForm']/table/tbody/tr/td[3]/input[1]").click();

		WebDriverWait wait1 = new WebDriverWait(driver, 45);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

		Thread.sleep(3000);
		
		if(get("//*[@id='deletejob']").isEnabled())
		{
			System.out.println("Delete button is enaabled - failed");
		}
		else
		{
			System.out.println("Delete button is disabled - passed");
		}
		
		
		Thread.sleep(4000);
		
		get("//*[@id='mainMenuHomePage']/a").click();
		
		Thread.sleep(4000);
		
		logout();
		
	}

		@Test(enabled = true, priority = 2)
		public void usecase2() throws InterruptedException 
		{
			login();
			Thread.sleep(4000);
			companyUsers();

			get("//*[@id='searchJob']").sendKeys("temp");
			Thread.sleep(3000);
			get("//body/ul[13]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='userpermissionDetailsDiv']/a").click();
			Thread.sleep(3000);

			if(!(get("//*[@id='jobDeleteID']").isSelected()))
			{
				get("//*[@id='jobDeleteID']").click();
			}

//			else
//			{
//				get("//*[@id='jobDeleteID']").isDisplayed();
//			}

			if(get("//*[@id='sysAdminIDBox']").isSelected())
			{
				get("//*[@id='sysAdminIDBox']").click();
			}

//			else
//			{
//				get("//*[@id='sysAdminIDBox']").isDisplayed();
//			}

			Thread.sleep(3000);
			get("//*[@id='userDetailsFormId']/div/table[2]/tbody/tr[2]/td/input").click();
			Thread.sleep(3000);
			logout();

			loginTemp();

			get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();

			get("//*[@id='quotenameID']").click();

			get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click(); 

			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action = new Actions(driver);
			action.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);

			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - passed");
			}
			else
			{
				System.out.println("Delete button is disabled - failed");
			}

			Thread.sleep(4000);
			
			get("//*[@id='advancedSearchButton']").click();

			Thread.sleep(3000);
			
			get("//*[@id='bookednameID']").click();

			get("//*[@id='searchAdvJobForm']/table/tbody/tr/td[3]/input[1]").click();

			WebDriverWait wait1 = new WebDriverWait(driver, 45);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action1 = new Actions(driver);
			action1.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);
			
			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - failed");
			}
			else
			{
				System.out.println("Delete button is disabled - passed");
			}


			Thread.sleep(4000);
			
			get("//*[@id='mainMenuHomePage']/a").click();
			
			Thread.sleep(4000);
			
			logout();
			
			
	}


		@Test(enabled = true, priority = 3)
		public void usecase3() throws InterruptedException 
		{
			login();
			Thread.sleep(4000);
			companyUsers();

			get("//*[@id='searchJob']").sendKeys("temp");
			Thread.sleep(3000);
			get("//body/ul[13]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='userpermissionDetailsDiv']/a").click();
			Thread.sleep(3000);

			if(get("//*[@id='jobDeleteID']").isSelected())
			{
				get("//*[@id='jobDeleteID']").click();
			}

//			else
//			{
//				get("//*[@id='jobDeleteID']").isDisplayed();
//			}

			if(!(get("//*[@id='sysAdminIDBox']").isSelected()))
			{
				get("//*[@id='sysAdminIDBox']").click();
			}

//			else
//			{
//				get("//*[@id='sysAdminIDBox']").isDisplayed();
//			}

			Thread.sleep(3000);
			get("//*[@id='userDetailsFormId']/div/table[2]/tbody/tr[2]/td/input").click();
			Thread.sleep(3000);
			logout();

			loginTemp();

			get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();

			get("//*[@id='quotenameID']").click();

			get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click(); 

			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action = new Actions(driver);
			action.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);

			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - passed");
			}
			else
			{
				System.out.println("Delete button is disabled - failed");
			}

			Thread.sleep(4000);
			
			get("//*[@id='advancedSearchButton']").click();

			Thread.sleep(3000);
			
			get("//*[@id='bookednameID']").click();

			get("//*[@id='searchAdvJobForm']/table/tbody/tr/td[3]/input[1]").click();

			WebDriverWait wait1 = new WebDriverWait(driver, 45);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action1 = new Actions(driver);
			action1.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);
			
			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - passed");
			}
			else
			{
				System.out.println("Delete button is disabled - failed");
			}


			Thread.sleep(4000);
			
			get("//*[@id='mainMenuHomePage']/a").click();
			
			Thread.sleep(4000);
			
			logout();
			
			
	}
		
		@Test(enabled = true, priority = 4)
		public void usecase4() throws InterruptedException 
		{
			login();
			Thread.sleep(4000);
			companyUsers();

			get("//*[@id='searchJob']").sendKeys("temp");
			Thread.sleep(3000);
			get("//body/ul[13]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='userpermissionDetailsDiv']/a").click();
			Thread.sleep(3000);

			if(!(get("//*[@id='jobDeleteID']").isSelected()))
			{
				get("//*[@id='jobDeleteID']").click();
			}

//			else
//			{
//				get("//*[@id='jobDeleteID']").isDisplayed();
//			}

			if(!(get("//*[@id='sysAdminIDBox']").isSelected()))
			{
				get("//*[@id='sysAdminIDBox']").click();
			}

//			else
//			{
//				get("//*[@id='sysAdminIDBox']").isDisplayed();
//			}

			Thread.sleep(3000);
			get("//*[@id='userDetailsFormId']/div/table[2]/tbody/tr[2]/td/input").click();
			Thread.sleep(3000);
			logout();

			loginTemp();

			get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[4]/input").click();

			get("//*[@id='quotenameID']").click();

			get("//*[@id='searchAdvJob']/table/tbody/tr/td[3]/input[1]").click(); 

			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action = new Actions(driver);
			action.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);

			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - passed");
			}
			else
			{
				System.out.println("Delete button is disabled - failed");
			}

			Thread.sleep(4000);
			
			get("//*[@id='advancedSearchButton']").click();

			Thread.sleep(3000);
			
			get("//*[@id='bookednameID']").click();

			get("//*[@id='searchAdvJobForm']/table/tbody/tr/td[3]/input[1]").click();

			WebDriverWait wait1 = new WebDriverWait(driver, 45);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[6]")));

			Actions action1 = new Actions(driver);
			action1.moveToElement(get("//*[@id='1']/td[6]")).doubleClick().perform();

			Thread.sleep(3000);
			
			if(get("//*[@id='deletejob']").isEnabled())
			{
				System.out.println("Delete button is enabled - passed");
			}
			else
			{
				System.out.println("Delete button is disabled - failed");
			}


			Thread.sleep(4000);
			
			get("//*[@id='mainMenuHomePage']/a").click();
			
			Thread.sleep(4000);
			
			logout();
			
			
	}
		
		
		
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}



}
