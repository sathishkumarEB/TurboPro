package com.turbopro.modules;

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

public class ReceiveInventoryAccrualAccount extends Variables{

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
	public void CreateStockPurchaseOrder() throws InterruptedException 
	{
		login();
		purchaseOrders();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input").click();
		get("//*[@id='vendorsearch']").sendKeys("Dairy");
		Thread.sleep(3000);	
		get("//body/ul[13]/li/a").click();
		get("//*[@id='POSaveID']").click();
		Thread.sleep(4000);		
		get("//*[@id='lineTab']").click();
		String ourPO = get("//*[@id='ourPoLineId']").getAttribute("value"); //getting PO number
		System.out.println(ourPO);
		get("//*[@id='new_row_note']").sendKeys("DMRR1009");
		Thread.sleep(3000);		
		get("//body/ul[15]/li/a").click();
		get("//*[@id='new_row_quantityOrdered']").sendKeys("10" + Keys.ENTER);
		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();

		receiveInventory();
		get("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input").click();
		Thread.sleep(3000);
		get("//*[@id='ponumber']").sendKeys(ourPO);
		Thread.sleep(3000);
		get("//body/div[10]/div[11]/div/button[1]").click();
		Thread.sleep(4000);
		get("//*[@id='ReceiveAllSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		get("//*[@id='priortable']/tbody/tr[2]/td[3]/img").click();
		get("//body/div[8]/div[1]/a/span").click();
		get("//*[@id='POCloseID']").click();


		vendorInvoice_Bills();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='po']").sendKeys(ourPO);
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(3000);

		get("//*[@id='freightGeneralId']").clear();
		get("//*[@id='freightGeneralId']").sendKeys("100");
		Thread.sleep(3000);

		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(5000);
		get("//body/div[17]/div[11]/div/button[1]").click();
		Thread.sleep(4000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

	}
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
	
}
