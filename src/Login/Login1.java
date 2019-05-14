package Login;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Login1 {
	static String driverPath = "C:/Users/sathish_kumar/workspace/";
	public WebDriver driver;

	//  public String baseurl= "http://qa.tt.eb.local/turbotracker/turbo/";

	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging

		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
	}

	@Test
	public void login() 
	{
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");

		driver.findElement(By.linkText("Login")).click();

		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");

		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]"))).doubleClick().build().perform();
		// driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]")).Click();
		driver.findElement(By.id("jobreleasetab")).click();
		driver.findElement(By.xpath("//table[@id='release']/tbody/tr[2]/td[12]")).click();
		driver.findElement(By.xpath("//td[@onclick='redirecttovieworaddvendorinvoice()']")).click();

		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);



	}

	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

}

//Script for navigating to Users module and searching a user 
