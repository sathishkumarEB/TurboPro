package com.turbopro.projectsmenu;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.MethodsLibrary.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProjectsCustomerProfitMargin extends Methods {

	//accessing the chrome driver
	@BeforeTest
	public void beforeTest() throws FileNotFoundException, IOException, Exception
	{
		openChromeBrowser(); 
	}

	@Test(enabled = true, priority =1)
	public void login() throws InterruptedException 
	{
		driver.get("http://qe.tt.eb.local/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(3000);
	}

	//TP_PCPM_0001 - logging into the application and navigate to projects 
	@Test(enabled = true, priority = 2)	
	public void navigateProjects() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		getlinktext("Projects").click();
	}

	//TP_PCPM_0002 - view open customer profit margin in projects 
	@Test(enabled = true, priority = 3)	
	public void cusProfitMargInProjects() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}

	//TP_PCPM_0003 - apply sort in the headers of customer profit margin list 
	@Test(enabled = true, priority = 4)	
	public void sortingInCusProfMargin() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button").click();

		for(int i = 1; i<=2; i++)
		{
			getxpath("//*[@id='jqgh_CustomerMarginGirdpop_customerName']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_CustomerMarginGirdpop_sales']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_CustomerMarginGirdpop_profit']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_CustomerMarginGirdpop_margin']").click();
			Thread.sleep(2000);
			getxpath("//*[@id='jqgh_CustomerMarginGirdpop_margin2']").click();
			Thread.sleep(2000);
		}
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}

	//TP_PCPM_0004 - view the customer profit margin by date range 
	@Test(enabled = true, priority = 5)	
	public void viewCusProfMargByDateRange() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button").click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("daterangeCM")));
		getid("daterangeCM").click();
		getid("rangefromdateCM").sendKeys("09/01/2017");
		Thread.sleep(2000);
		getid("todateCM").sendKeys("11/22/2017");
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}



	//TP_PCPM_0005 - navigate to different pages of Open purchase orders/sales orders in Projects
	@Test(enabled = true, priority = 6)	
	public void navigatePagesCusProfMarg() throws InterruptedException, Exception
	{
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button")));
		getxpath("//div[@id='gview_CustomerMarginGird']//div[@id='jqGridButtonDiv']/button").click();
		Thread.sleep(2000);
		getxpath("//*[@id='next_CustomerMarginGirdPagerpop']/span").click();
		getxpath("//*[@id='next_CustomerMarginGirdPagerpop']/span").click();
		getxpath("//*[@id='next_CustomerMarginGirdPagerpop']/span").click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("closedialog")));
		getid("closedialog").click();
	}


	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}

