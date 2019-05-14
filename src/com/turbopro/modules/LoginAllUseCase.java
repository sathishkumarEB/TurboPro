package com.turbopro.modules;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginAllUseCase {
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
  
  @Test(priority=1)
  public void withoutCredentials () 					//Clicking Login button without credentials
  {
	  driver.get("http://qa.tt.eb.local/turbotracker/turbo/");

	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  if(driver.findElement(By.id("errormsg")).getText().equals("Invalid Username or Password."))
	  {
		  System.out.println("Displayed");
	  }
	  else
	  {
		  System.out.println("Not Displayed");
	  }
  }

  @Test(priority=2)
  public void withUsernameOnly () 						//Clicking Login button by entering only username
  {	  
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  if(driver.findElement(By.id("errormsg")).getText().equals("Invalid Username or Password."))
	  {
		  System.out.println("Displayed");
	  }
	  else
	  {
		  System.out.println("Not Displayed");
	  }
	  driver.findElement(By.id("uname")).clear();
  }
  

  @Test(priority=3)
  public void withPasswordOnly () 						//Clicking Login button by entering only password
  {
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  if(driver.findElement(By.id("errormsg")).getText().equals("Invalid Username or Password."))
	  {
		  System.out.println("Displayed");
	  }
	  else
	  {
		  System.out.println("Not Displayed");
	  }
	  driver.findElement(By.id("pwd")).clear();
  }

  
  @Test(priority=4)
  public void withInvalidCredentials () 				//Clicking Login button by entering invalid credentials
  {
	  driver.findElement(By.id("uname")).sendKeys("test");
	  driver.findElement(By.id("pwd")).sendKeys("test");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  if(driver.findElement(By.id("errormsg")).getText().equals("Invalid Username or Password."))
	  {
		  System.out.println("Displayed");
	  }
	  else
	  {
		  System.out.println("Not Displayed");
	  }
	  driver.findElement(By.id("uname")).clear();
	  driver.findElement(By.id("pwd")).clear();
	  
  }
  
  
  @Test(priority=5)
  public void validCredentials() 						//Clicking Login button by entering valid credentials
  {
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
  }

  
 @AfterTest
  public void teardown() 
  {
	  //driver.quit();
  }

}

//Script for LoginAllUseCases 
