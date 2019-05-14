package InventoryAutomation;
import java.awt.RenderingHints.Key;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class CustomerPayments extends Variables {

	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	//	public WebDriver driver;

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
	public void case1_login() throws InterruptedException 
	{
		login();		
	}

	@Test(enabled = true, priority = 2)
	public void case2_customerPayments() throws InterruptedException 
	{
		customerPayments();
		
	}
	
	
}
