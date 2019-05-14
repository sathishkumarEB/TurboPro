package Login;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class searchjob extends Variables {
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
	public void useCase1() throws InterruptedException
	{

		get("//*[@id='jobsearch']").click();
		get("//*[@id='jobsearch']").clear();
		get("//*[@id='jobsearch']").sendKeys("tjs170301");
		Thread.sleep(5000);
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[3]/input").click();
		Thread.sleep(7000);
		get("//*[@id='mainMenuHomePage']/a").click();
	}

	@Test(enabled = true, priority = 3)
	public void useCase2() throws InterruptedException
	{

		get("//*[@id='jobsearch']").click();
		get("//*[@id='jobsearch']").clear();
		get("//*[@id='jobsearch']").sendKeys("TJS170301");
		Thread.sleep(5000);
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[3]/input").click();
		Thread.sleep(7000);
		get("//*[@id='mainMenuHomePage']/a").click();
	}


	@Test(enabled = true, priority = 4)
	public void useCase3() throws InterruptedException
	{

		get("//*[@id='jobsearch']").click();
		get("//*[@id='jobsearch']").clear();
		get("//*[@id='jobsearch']").sendKeys("anytime fitness");
		Thread.sleep(5000);
		get("//*[@id='intro']/div[3]/div/div/table/tbody/tr[4]/td[3]/input").click();
		Thread.sleep(7000);
		get("//*[@id='mainMenuHomePage']/a").click();
	}



	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}


