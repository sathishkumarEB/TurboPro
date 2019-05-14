package com.turbopro.modules;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.thoughtworks.selenium.webdriven.commands.Click;
import com.turbopro.basepackages.*;

public class CheckAverageCost extends Variables {

	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	String PO;


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
	public void checkingAverageCost() throws InterruptedException 
	{

		Inventory();

		get("//*[@id='searchJob']").sendKeys("DMRR1009");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);
		currentOnHand();

		String avgCost = get("//*[@id='avgCostId']").getText(); //getting average cost 
		System.out.println("beforeVeBill" + avgCost);
		avgCost = avgCost.replace("$", "");
		CurrentAvgCost = new BigDecimal(avgCost.trim());
		System.out.println("average cost" + CurrentAvgCost);

	}

	@Test(enabled = true, priority = 3)
	public void CreateStockPurchaseOrder() throws InterruptedException 
	{

		purchaseOrders();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input").click();
		get("//*[@id='vendorsearch']").sendKeys("Dairy");
		Thread.sleep(5000);	
		get("//body/ul[13]/li/a").click();
		get("//*[@id='POSaveID']").click();
		Thread.sleep(4000);		
		get("//*[@id='lineTab']").click();
		String ourPO = get("//*[@id='ourPoLineId']").getAttribute("value"); //getting PO number
		PO = ourPO;
		System.out.println(ourPO);
		get("//*[@id='new_row_note']").sendKeys("DMRR1009");
		Thread.sleep(3000);		
		get("//body/ul[15]/li/a").click();
		get("//*[@id='new_row_quantityOrdered']").sendKeys("8" + Keys.ENTER);
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

		get("//*[@id='1']/td[5]").click();

		get("//*[@id='1_quantityReceived']").clear();
		get("//*[@id='1_quantityReceived']").sendKeys("1" + Keys.ENTER);


		//get("//*[@id='ReceiveAllSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		//	get("//*[@id='priortable']/tbody/tr[2]/td[2]/img").click();
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
		//get("//body/div[17]/div[11]/div/button[1]").click();
		veBillAmount();

		Thread.sleep(2000);
		veBillQty();
		Thread.sleep(2000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		//checkingAverageCostAfterVeBill();
		Thread.sleep(4000);


		avgCostFormula();

	}



	@Test(enabled = true, priority = 4)
	public void changeMultiplier3() throws InterruptedException //change multiplier as 3
	{
		receiveInventory();
		//		get("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input").click();
		Thread.sleep(3000);
		get("//*[@id='searchJob']").sendKeys(PO);
		get("//*[@id='goSearchButtonID']").click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().perform();

		//		get("//*[@id='ponumber']").sendKeys(PO);
		Thread.sleep(3000);
		//		get("//body/div[10]/div[11]/div/button[1]").click();
		//		Thread.sleep(2000);
		//		get("//body/div[12]/div[11]/div/button[1]/span").click();
		//		Thread.sleep(4000);
		get("//*[@id='1']/td[5]").click();
		get("//*[@id='1_quantityReceived']").clear();
		get("//*[@id='1_quantityReceived']").sendKeys("2" + Keys.ENTER);
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		//	get("//*[@id='priortable']/tbody/tr[2]/td[2]/img").click();
		get("//body/div[8]/div[1]/a/span").click();
		get("//*[@id='POCloseID']").click();

		vendorInvoice_Bills();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='po']").sendKeys(PO);
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(3000);
		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]")).doubleClick().perform();
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]/input").clear();
		Thread.sleep(2000);
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]/input").sendKeys("3" + Keys.ENTER);
		Thread.sleep(5000);
		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(5000);
		//get("//body/div[17]/div[11]/div/button[1]").click();
		//Thread.sleep(4000);

		veBillAmount();

		Thread.sleep(2000);
		veBillQty();
		Thread.sleep(2000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		//checkingAverageCostAfterVeBill();
		Thread.sleep(4000);


		avgCostFormula();


	}

	@Test(enabled = true, priority = 5)
	public void changeMultiplier() throws InterruptedException //change multiplier as 0.5
	{
		receiveInventory();
		//get("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input").click();
		Thread.sleep(3000);

		get("//*[@id='searchJob']").sendKeys(PO);
		get("//*[@id='goSearchButtonID']").click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().perform();

		//get("//*[@id='ponumber']").sendKeys(PO);
		//		Thread.sleep(3000);
		//		get("//body/div[10]/div[11]/div/button[1]").click();
		//		Thread.sleep(2000);
		//		get("//body/div[12]/div[11]/div/button[1]/span").click();
		Thread.sleep(4000);
		get("//*[@id='1']/td[5]").click();
		get("//*[@id='1_quantityReceived']").clear();
		get("//*[@id='1_quantityReceived']").sendKeys("3" + Keys.ENTER);
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		//	get("//*[@id='priortable']/tbody/tr[2]/td[2]/img").click();
		get("//body/div[8]/div[1]/a/span").click();
		get("//*[@id='POCloseID']").click();

		vendorInvoice_Bills();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='po']").sendKeys(PO);
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(3000);
		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]")).doubleClick().perform();
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]/input").clear();
		Thread.sleep(2000);
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]/input").sendKeys("0.5" + Keys.ENTER);
		Thread.sleep(5000);
		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(5000);

		//get("//body/div[17]/div[11]/div/button[1]").click();
		//Thread.sleep(4000);


		veBillAmount();

		Thread.sleep(2000);
		veBillQty();
		Thread.sleep(2000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		//checkingAverageCostAfterVeBill();
		Thread.sleep(4000);


		avgCostFormula();


	}


	@Test(enabled = true, priority = 6)
	public void changeCostEach() throws InterruptedException //change cost each to 20 in the veBill 
	{
		receiveInventory();
		//	get("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input").click();
		Thread.sleep(3000);

		get("//*[@id='searchJob']").sendKeys(PO);
		get("//*[@id='goSearchButtonID']").click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().perform();

		//		get("//*[@id='ponumber']").sendKeys(PO);
		//		Thread.sleep(3000);
		//		get("//body/div[10]/div[11]/div/button[1]").click();
		//		Thread.sleep(2000);
		//		get("//body/div[12]/div[11]/div/button[1]/span").click();
		Thread.sleep(4000);
		get("//*[@id='1']/td[5]").click();
		get("//*[@id='1_quantityReceived']").clear();
		get("//*[@id='1_quantityReceived']").sendKeys("4" + Keys.ENTER);
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		//	get("//*[@id='priortable']/tbody/tr[2]/td[2]/img").click();
		get("//body/div[8]/div[1]/a/span").click();
		get("//*[@id='POCloseID']").click();

		vendorInvoice_Bills();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='po']").sendKeys(PO);
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(3000);
		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]")).doubleClick().perform();
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]/input").clear();
		Thread.sleep(2000);
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]/input").sendKeys("20" + Keys.ENTER);
		Thread.sleep(5000);
		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(5000);

		//get("//body/div[17]/div[11]/div/button[1]").click();
		//Thread.sleep(4000);

		veBillAmount();

		Thread.sleep(2000);
		veBillQty();
		Thread.sleep(2000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		//checkingAverageCostAfterVeBill();
		Thread.sleep(4000);


		avgCostFormula();


	}


	@Test(enabled = true, priority = 7)
	public void changeCostEach_1() throws InterruptedException //change cost each to 2 in the veBill 
	{
		receiveInventory();
		//	get("//body/div[1]/div[2]/table/tbody/tr/td[2]/div/input").click();
		Thread.sleep(3000);

		get("//*[@id='searchJob']").sendKeys(PO);
		get("//*[@id='goSearchButtonID']").click();
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='1']/td[8]")).doubleClick().perform();

		//		get("//*[@id='ponumber']").sendKeys(PO);
		//		Thread.sleep(3000);
		//		get("//body/div[10]/div[11]/div/button[1]").click();
		//		Thread.sleep(2000);
		//		get("//body/div[12]/div[11]/div/button[1]/span").click();
		Thread.sleep(4000);
		get("//*[@id='1']/td[5]").click();
		get("//*[@id='1_quantityReceived']").clear();
		get("//*[@id='1_quantityReceived']").sendKeys("5" + Keys.ENTER);
		Thread.sleep(3000);
		get("//*[@id='POSaveID']").click();
		Thread.sleep(3000);
		get("//*[@id='priorreceipts']").click();
		Thread.sleep(4000);
		//	get("//*[@id='priortable']/tbody/tr[2]/td[2]/img").click();
		get("//body/div[8]/div[1]/a/span").click();
		get("//*[@id='POCloseID']").click();

		vendorInvoice_Bills();
		get("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]").click();
		get("//*[@id='po']").sendKeys(PO);
		get("//*[@id='saveTermscancelButton']").click();
		Thread.sleep(3000);
		Actions action1 = new Actions(driver);
		action1.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]")).doubleClick().perform();
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]/input").clear();
		Thread.sleep(2000);
		get("//*[@id='lineItemGrid']/tbody/tr[2]/td[6]/input").sendKeys("2" + Keys.ENTER);
		Thread.sleep(5000);
		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(5000);

		//get("//body/div[17]/div[11]/div/button[1]").click();
		//Thread.sleep(4000);


		veBillAmount();

		Thread.sleep(2000);
		veBillQty();
		Thread.sleep(2000);
		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		//checkingAverageCostAfterVeBill();
		Thread.sleep(4000);


		avgCostFormula();


	}


	@Test(enabled = false, priority = 7)
	public void checkingAverageCostAfterVeBill() throws InterruptedException 
	{
		Inventory();

		get("//*[@id='searchJob']").sendKeys("DMRR1009");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);
		String avgCost = get("//*[@id='avgCostId']").getText(); //getting average cost after vebill
		System.out.println("afterVeBill:" + avgCost);

	}


	/*
	//opening the created veBill and editing it(removing freight)
	@Test(enabled = false, priority = 8)
	public void vendorBillEdit() throws InterruptedException 
	{
		vendorInvoice_Bills();
		get("//*[@id='searchJob']").sendKeys(PO);
		get("//*[@id='goSearchButtonID']").click();

		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='invoicesGrid']/tbody/tr[2]/td[6]")).doubleClick().perform();
		Thread.sleep(3000);
		get("//*[@id='freightGeneralId']").clear();
		get("//*[@id='freightGeneralId']").sendKeys("0");;
		Thread.sleep(3000);
		get("//*[@id='addNewVeInvFmDlgbuttonsave']").click();
		Thread.sleep(3000);
		//	get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		if(get("//body/div[18]/div[2]").isDisplayed())
		{

			get("//button[contains(.,'Yes')]").click();
		}

		Thread.sleep(3000);

		if(get("//*[@id='invreasonttextid']").isDisplayed())
		{
			get("//*[@id='invreasonttextid']").sendKeys("test");

			get("//button[contains(.,'OK')]").click();
		}

		Thread.sleep(3000);

		if(get("//body/div[19]/div[2]").isDisplayed())
		{
			get("//button[contains(.,'Cancel')]").click();
		}

		get("//*[@id='addNewVeInvFmDlgclsbutton']").click();

		checkingAverageCostAfterVeBill();

	}*/


	BigDecimal CurrentOnHand, VebillAmount, VeBillQty, CurrentAvgCost, avgCost;


	public void avgCostFormula() throws InterruptedException 
	{


		//	avgCost = ((( CurrentOnHand - VeBillQty ) * CurrentAvgCost) + (VeBillQty * ( VebillAmount / VeBillQty ))) / CurrentOnHand ;

		avgCost = ((( CurrentOnHand.subtract(VeBillQty)).multiply(CurrentAvgCost) ).add((VeBillQty.multiply(VebillAmount.divide(VeBillQty, 2, RoundingMode.HALF_UP))) )).divide(CurrentOnHand, 2, RoundingMode.HALF_UP);
		System.out.println("AverageCost" + avgCost);

	}




	public void currentOnHand() throws InterruptedException 
	{

		Inventory();

		get("//*[@id='searchJob']").sendKeys("DMRR1009");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);

		CurrentOnHand = new BigDecimal(get("//*[@id='onHandProduct']").getText().trim()); //getting current on hand value
		//System.out.println("onhand" + CurrentOnHand);	
	}



	public void veBillAmount() throws InterruptedException 
	{
		String billamt = get("//*[@id='lineItemGrid']/tbody/tr[2]/td[10]").getText();
	//	System.out.println("billamt" + billamt);	
		billamt = billamt.replace("$", "");
		VebillAmount = new BigDecimal(billamt.trim());
//		System.out.println("billamtwithoutdollar" + VebillAmount);


	}


	public void veBillQty() throws InterruptedException 
	{

		String billqty = get("//*[@id='lineItemGrid']/tbody/tr[2]/td[5]").getText();
		//		System.out.println("billqty" + billqty);	
		//		billqty = billqty.replace("$", "");
		VeBillQty = new BigDecimal(billqty.trim());
	//	System.out.println("billqtywithoutdollar" + VeBillQty);
	}



	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}


}
