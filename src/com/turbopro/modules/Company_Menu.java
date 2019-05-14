package com.turbopro.modules;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.turbopro.basepackages.*;

public class Company_Menu extends Variables{
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

	@Test(enabled = true)
	public void Company() throws InterruptedException 
	{
//		login();
		
		driver.get("https://metroequipsales.aandespecialties.com/turbotracker/turbo/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(5000);
		

		//Employees 
		WebElement company = get(".//*[@id='mainMenuCompanyPage']/a");

		Actions action = new Actions(driver);

		action.moveToElement(company).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[3]").click();
		Thread.sleep(5000);
		get("//*[@id='addEmployeeButton']").click();
		get("//*[@id='addNewEmployeeForm']/table/tbody/tr/td/input[2]").click();
		
	
		
		//Employee - Commissions 
		WebElement company1 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement employees = get("//*[@id='mainMenuCompanyPage']/ul/li[3]");
		
		Actions action1 = new Actions(driver);

		action1.moveToElement(company1).perform();
		action1.moveToElement(employees).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[3]/ul/li").click();
		Thread.sleep(5000);
		
		
		get("//body/div[1]/table[2]/tbody/tr[1]/td[1]/input[1]").click();
		Thread.sleep(2000);
		get("//body/div[11]/div[11]/div/button[2]/span").click();
		Thread.sleep(2000);
		get("//*[@id='1']/td[7]/div/a/img").click();
		Thread.sleep(2000);
		get("//*[@id='1']/td[8]/div/a/img").click();
		Thread.sleep(2000);
		get("//*[@id='cancelButton']").click();
		Thread.sleep(2000);
		get("//*[@id='1']/td[9]/div/a/img").click();
		
	
		
		
		//Rolodex
		
		WebElement company2 = get(".//*[@id='mainMenuCompanyPage']/a");

		Actions action2 = new Actions(driver);

		action2.moveToElement(company2).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[4]").click();
		Thread.sleep(5000);
		
		get("//*[@id='customercheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='vendorcheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='employeecheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='architectcheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='engineercheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='gccheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='ownercheckbox']").click();
		Thread.sleep(2000);
		get("//*[@id='ListCategory5Desc']").click();
		Thread.sleep(2000);
		get("//*[@id='ListCategory6Desc']").click();
		Thread.sleep(2000);
		get("//*[@id='ListCategory7Desc']").click();
		Thread.sleep(2000);
		
		get("//*[@id='addvendorlist']").click();
		Thread.sleep(2000);
		get("//*[@id='addNewRolodex']/table/tbody/tr/td[2]/input[1]").click();
		Thread.sleep(2000);
		get("//*[@id='addNewRolodex']/table/tbody/tr/td[2]/input[2]").click();
		
	
		
		//users 
		
		WebElement company3 = get(".//*[@id='mainMenuCompanyPage']/a");

		Actions action3 = new Actions(driver);

		action3.moveToElement(company3).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[5]").click();
		Thread.sleep(5000);
		
		get("//*[@id='adduserlist']").click();
		get("//*[@id='userDetailsPage']/table/tbody/tr/td//*[@id='saveUserButton']").click();
		Thread.sleep(2000);
		get("//*[@id='userDetailsPage']/table/tbody/tr/td//*[@id='cancelUserButton']").click();
		get("//*[@id='activeUsersList']").click();
		Thread.sleep(2000);
		get("//*[@id='activeUsersList']").click();
		Thread.sleep(4000);
		
		Actions act = new Actions(driver);
		act.moveToElement(get("//*[@id='3']/td[5]")).doubleClick().build().perform();
		Thread.sleep(3000);
		
		get("//*[@id='userDetailsFormId']/div/table[2]/tbody/tr[2]/td/*[@id='saveUserButton']").click();
		
		Thread.sleep(3000);
		
		//settings
		WebElement company4 = get(".//*[@id='mainMenuCompanyPage']/a");

		Actions action4 = new Actions(driver);

		action4.moveToElement(company4).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[6]").click();
		Thread.sleep(5000);
		
		
		get("//*[@id='settingsFormDetailsBlock']/div[1]/img").click();
		
		get("//*[@id='permission1']").click();
		get("//body/div[9]/div[11]/div/button/span").click();
		Thread.sleep(2000);
		get("//*[@id='companyFormId']/table/tbody/tr/td/fieldset/table[2]/tbody/tr[1]/td/input").click();
		get("//*[@id='settingsCustmerDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='settingsVendorDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='settingsEmployeeDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='settingsJobDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='settingsInventoryDetails']/a").click();
		Thread.sleep(2000);
		get("//*[@id='settingsBankingDetails']/a").click();
		
			
		
	
		//chart of accounts 
		
		WebElement company5 = get(".//*[@id='mainMenuCompanyPage']/a");

		Actions action5 = new Actions(driver);

		action5.moveToElement(company5).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[7]").click();
		Thread.sleep(5000);
		
//		get("//*[@id='addChartlist']").click();
//		get("//*[@id='chartsDetailsFromID']/table/tbody/tr/td[2]/table/tbody/tr/td/*[@id='saveUserButton']").click();
//		Thread.sleep(4000);
//		get("//body/div[11]/div[11]/div/button/span").click();
//		
//
//		Thread.sleep(2000);
//		get("//*[@id='deleteChartOfAccountID']").click();
//		Thread.sleep(2000);
//		get("//body/div[16]/div[11]/div/button[2]/span").click();
//		Thread.sleep(2000);
//		get("//*[@id='deleteChartOfAccountID']").click();
//		Thread.sleep(2000);
//		get("//body/div[12]/div[11]/div/button[1]/span").click();
//		Thread.sleep(2000);
//		get("//body/div[11]/div[11]/div/button[1]/span").click();
		get("//*[@id='chartsDetailsTab']/div/ul/li[2]/a").click();
		
		
	
		
		
		//Divisions 
		
		WebElement company6 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action6 = new Actions(driver);
		action6.moveToElement(company6).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[8]").click();
		Thread.sleep(5000);
		
		get("//*[@id='deleteDivisionID']").click();
		
		get("//body/div[11]/div[11]/div/button/span").click();
		get("//*[@id='addDivisionList']").click();
		get("//*[@id='1']/td[3]").click();
		
		
		
		//Tax Territory
		
		WebElement company7 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action7 = new Actions(driver);
		action7.moveToElement(company7).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[9]").click();
		Thread.sleep(5000);
		
		
		get("//*[@id='taxTerritoryDetailsDiv']/table/tbody/tr/td[3]//*[@id='saveUserButton']").click();
		Thread.sleep(5000);
		get("//body/div[12]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//*[@id='deleteChartOfAccountID']").click();
		Thread.sleep(3000);
		get("//body/div[13]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//*[@id='addChartlist']").click();
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/table/tbody/tr/td/*[@id='cancelUserButton']").click();
		Thread.sleep(3000);
		get("//*[@id='addChartlist']").click();
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr/td[2]/*[@id='stateID']").sendKeys("TT");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr/td[4]/*[@id='stateCodeID']").sendKeys("TT");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/fieldset/table/tbody/tr[2]/td[2]//*[@id='decriptionID']").sendKeys("TEST");
		Thread.sleep(3000);
		get("//*[@id='addNewTaxTerritoryFormID']/div/table/tbody/tr/td/*[@id='saveUserButton']").click();
		Thread.sleep(3000);
		get("//body/div[14]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//td[@title='TEST']").click();
		Thread.sleep(3000);
		get("//*[@id='deleteChartOfAccountID']").click();
		Thread.sleep(3000);
		get("//body/div[15]/div[11]/div/button[2]").click();
		Thread.sleep(3000);
		get("//*[@id='deleteChartOfAccountID']").click();
		Thread.sleep(3000);
		get("//body/div[16]/div[11]/div/button[1]").click();
		Thread.sleep(3000);
		get("//body/div[14]/div[11]/div/button").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[3]").click();
		get("//*[@id='2']/td[3]").click();
		
		
		
		//General Ledger
		
		WebElement company8 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action8 = new Actions(driver);
		action8.moveToElement(company8).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[10]").click();
		Thread.sleep(5000);
		
		get("//*[@id='accountsID']").sendKeys("2000-00-000");
		Thread.sleep(3000);
		get("//body/ul[13]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='mostRecentPeriodsID']").click();
		Thread.sleep(3000);
		get("//*[@id='saveDDButton']").click();
		
		
		//Journal Entries 
		
		WebElement company9 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action9 = new Actions(driver);
		action9.moveToElement(company9).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[11]").click();
		Thread.sleep(5000);

		get("//*[@id='1']/td[2]").click();
		get("//*[@id='2']/td[2]").click();
		
		
		
		//Accounting Cycles
		
		WebElement company10 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action10 = new Actions(driver);
		action10.moveToElement(company10).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[12]").click();
		Thread.sleep(5000);
		
		get("//*[@id='chartsDetailsFromID']/table/tbody/tr/td/table/tbody/tr[1]/td[1]/div/span[3]/input").click();
		Thread.sleep(2000);
		get("//body/div[9]/div[11]/div/button").click();
		Thread.sleep(4000);
		get("//*[@id='closefiscalyearid']").click();
		Thread.sleep(2000);
		get("//body/div[9]/div[11]/div/button").click();
		
		
	
		
	//GL Transaction 
		
		WebElement company11 = get(".//*[@id='mainMenuCompanyPage']/a");
		Actions action11 = new Actions(driver);
		action11.moveToElement(company11).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[13]").click();
		Thread.sleep(5000);
		
		get("//*[@id='drillDownPager1_center']/table/tbody/tr/td[8]/select").click();
		Thread.sleep(2000);
		get("//*[@id='drillDownPager1_center']/table/tbody/tr/td[8]/select/option[2]").click();
		
			
		
		//General Ledger - Balance Sheet 
		
		WebElement company12 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger12 = get("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		
		Actions action12 = new Actions(driver);

		action12.moveToElement(company12).perform();
		action12.moveToElement(generalledger12).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[1]").click();
		Thread.sleep(3000);
		
		get("//*[@id='showAccount']").click();
		Thread.sleep(2000);
		get("//*[@id='balanceSheetForm']/table/tbody/tr[6]/td[2]/input").click();
		
		
		//General Ledger - Trial Balance Sheet 
		
		
		WebElement company13 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger13 = get("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		
		Actions action13 = new Actions(driver);

		action13.moveToElement(company13).perform();
		action13.moveToElement(generalledger13).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[2]").click();
		Thread.sleep(3000);
		
		get("//*[@id='balanceSheetForm']/table/tbody/tr[7]/td[2]/input").click();
		Thread.sleep(5000);
		get("//body/div[1]/table/tbody/tr[3]/td/div/input").click();
		
		
		//General Ledger - Income Statement  
		
		
		WebElement company14 = get(".//*[@id='mainMenuCompanyPage']/a");
		WebElement generalledger14 = get("//*[@id='mainMenuCompanyPage']/ul/li[10]");
		
		Actions action14 = new Actions(driver);

		action14.moveToElement(company14).perform();
		action14.moveToElement(generalledger14).perform();
		get("//*[@id='mainMenuCompanyPage']/ul/li[10]/ul/li[3]").click();
		Thread.sleep(3000);
		
		get("//*[@id='balanceSheetForm']/table/tbody/tr[8]/td[2]/input").click();
		Thread.sleep(2000);
		get("//*[@id='balanceSheetForm']/table/tbody/tr[8]/td[2]/span/img").click();
		
		
	}
	
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}
}
