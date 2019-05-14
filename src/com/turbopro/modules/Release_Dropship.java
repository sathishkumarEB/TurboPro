package com.turbopro.modules;

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

public class Release_Dropship extends Variables {

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

	/*TS-0081*/
	//Create dropship with single line item, create vendor invoice and customer invoice for the same 
	@Test(enabled = false, priority = 2)
	public void useCase_1() throws InterruptedException 
	{

		/*driver.findElement(By.id("jobsearch")).sendKeys("CRL170701");
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(4000);
		 */		

		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();


		Thread.sleep(3000);
		get("//*[@id='new_row_note']").sendKeys("ma310010");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);

		Actions action = new Actions(driver);
		action.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]")).doubleClick().perform();

		get("//*[@id='new_row_quantityOrdered']").sendKeys("10" + Keys.ENTER);


		Thread.sleep(3000);
		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);

		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);
		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();

		Thread.sleep(3000);
		//get("//body/div[25]/div[1]/a/span").click();
		//get("//button[contains(.,'Close')]").click();

		get("//*[@id='vendorinvoiceidclosebutton']").click();

		Thread.sleep(3000);

		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);	
	}



	/*TS-0084*/
	//Create dropship with multiple line item, create vendor invoice and customer invoice for the same 
	@Test(enabled = false, priority = 3)
	public void useCase_3() throws InterruptedException 
	{

		/*driver.findElement(By.id("jobsearch")).sendKeys("CRL170701");
			get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
			Thread.sleep(4000);
		 */		

		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();
		Thread.sleep(3000);

		for(int i=0;i<10;i++)
		{
			get("//*[@id='new_row_note']").sendKeys("ma310010");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
		}


		Thread.sleep(3000);
		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);

		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);
		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		//get("//body/div[25]/div[1]/a/span").click();
		//get("//button[contains(.,'Close')]").click();

		get("//*[@id='vendorinvoiceidclosebutton']").click();

		Thread.sleep(3000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);	
	}


	/*TS-0085*/
	//Create multiple dropship release in a single job, along with customer and vendor invoice for the same 

	@Test(enabled = false, priority = 4)
	public void useCase_4() throws InterruptedException 
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);

		int a=57;
		for(int j=1;j<4;j++)

		{
			get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(4000);
			get("//*[@id='releasesTypeID']").click();
			Thread.sleep(3000);
			get("//*[@id='releasesTypeID']/option[2]").click();
			Thread.sleep(3000);
			get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
			Thread.sleep(5000);
			get("//body/ul[36]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='NoteID']").sendKeys("auto");
			Thread.sleep(3000);
			get("//*[@id='AllocatedID']").sendKeys("1000");
			Thread.sleep(3000);
			get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
			Thread.sleep(3000);
			get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
			Thread.sleep(5000);
			get("//*[@id='polineitems']/a").click();
			Thread.sleep(3000);


			for(int i=0;i<2;i++)
			{
				get("//*[@id='new_row_note']").sendKeys("ma310010");
				Thread.sleep(3000);
				get("//body/ul["+a+"]/li/a").click();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_quantityOrdered")).clear();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
			}

			if(a==57)
			{
				a++;
			}			


			Thread.sleep(3000);
			get("//*[@id='SaveLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='CancelLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='"+j+"']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();	
			Thread.sleep(3000);
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(5000);
			get("//button[contains(.,'Cancel')]").click();
			Thread.sleep(3000);
			get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
			Thread.sleep(3000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
			Thread.sleep(3000);
			get("//*[@id='vendorinvoiceidclosebutton']").click();
			Thread.sleep(3000);
			get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
			Thread.sleep(3000);
			get("//*[@id='customerInvoicebtnID']").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(6000);
			get("//*[@id='cICheckTab2']/a").click();
			Thread.sleep(6000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(10000);
			get("//*[@id='CuInvoiceSaveCloseID']").click();
			Thread.sleep(6000);
		}
	}

	/*TS-0086*/
	//Import a xml file while adding line items in the Purchase Order(Dropship)
	@Test(enabled = false, priority = 5)
	public void useCase_5() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();
		Thread.sleep(4000);


		get("//*[@id='importFromXML']").click();
		Thread.sleep(2000);
		get("//*[@id='uploadXml_Form']/button").click();
		Thread.sleep(2000);
		get("//*[@id='xmlfileid']").sendKeys("C:\\Users\\sathish_kumar\\Desktop\\RST161101A.XML");
		Thread.sleep(3000);
		get("//*[@id='uploadXml_Form']/button").click();
		Thread.sleep(3000);
		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);

		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);

		get("//*[@id='vendorInvoiceNum']").sendKeys("20");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("20");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);

		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidclosebutton']").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_proNumberID']").sendKeys("3333");
		Thread.sleep(2000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("3");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
	}

	/*TS-0087*/
	//create a dropship release with different product items
	@Test(enabled = false, priority = 6)
	public void useCase_6() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(5000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("Air Vent");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("different product items");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("500");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();
		Thread.sleep(4000);


		List <String> itemcode = new ArrayList<String>();
		itemcode.add("MA310010");
		itemcode.add("MA310012");
		itemcode.add("MA3100A06");
		itemcode.add("MA3100A08");
		itemcode.add("MA3100A10");
		itemcode.add("MA3100A12");
		itemcode.add("MA3100A14");
		itemcode.add("MA3100SA212");

		for(int i=0;i<8;i++)
		{
			get("//*[@id='new_row_note']").sendKeys(itemcode.get(i));
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
		}


		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);

		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);

		get("//*[@id='vendorInvoiceNum']").sendKeys("01");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("01");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);

		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(5000);
		Thread.sleep(5000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidclosebutton']").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_proNumberID']").sendKeys("6");
		Thread.sleep(2000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("3");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
	}

	/*TS-0088*/
	//create a line items with product as *
	@Test(enabled = false, priority = 7)
	public void useCase_7() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(5000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("Air Vent");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("line items as *");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("500");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();
		Thread.sleep(4000);

		int l = 1;
		int m = 5;
		for(int i=0; i<5; i++)

		{
			get("//*[@id='new_row_note']").sendKeys("*");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Integer.toString(l));
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).sendKeys(Integer.toString(m));

			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).sendKeys(Integer.toString(l) + Keys.ENTER);
			l++; m++;
		}	




		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);

		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);

		//		get("//*[@id='vendorInvoiceNum']").sendKeys("37");
		//		Thread.sleep(2000);
		//		get("//*[@id='proNumberID']").sendKeys("47");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);

		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(5000);
		Thread.sleep(5000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidclosebutton']").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_proNumberID']").sendKeys("3333");
		Thread.sleep(2000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("3");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
	}


	/*TS-0089*/
	//Making parital vendor invoices for the created purchase orders 
	@Test(enabled = false, priority = 8)
	public void useCase_8() throws InterruptedException
	{
		CreateJobUsingQuickBook();
		Thread.sleep(5000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("Dan Sargent");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("Partial vendor invoices");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("300");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);
		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();
		Thread.sleep(4000);

		int l = 1;
		int m = 5;
		for(int i=0; i<5; i++)

		{
			get("//*[@id='new_row_note']").sendKeys("*");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Integer.toString(l));
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).sendKeys(Integer.toString(m));

			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).sendKeys(Integer.toString(l) + Keys.ENTER);
			l++; m++;
		}	

		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='canDoVIID_5']").click();
		get("//*[@id='canDoVIID_4']").click();
		get("//*[@id='canDoVIID_3']").click();

		get("//*[@id='vendorInvoiceNum']").sendKeys("7");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("7");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='canDoVIID_5']").click();
		get("//*[@id='vendorInvoiceNum']").sendKeys("8");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("8");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='vendorInvoiceNum']").sendKeys("9");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("9");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);


		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(5000);
		Thread.sleep(5000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidclosebutton']").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoicebtnID']").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//*[@id='customerInvoice_proNumberID']").sendKeys("3333");
		Thread.sleep(2000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(6000);
		get("//*[@id='cICheckTab2']/a").click();
		Thread.sleep(6000);
		get("//*[@id='customerInvoice_frightIDcu']").clear();
		get("//*[@id='customerInvoice_frightIDcu']").sendKeys("3");
		Thread.sleep(3000);
		get("//*[@id='CuInvoiceSaveID']").click();
		Thread.sleep(10000);
		get("//*[@id='CuInvoiceSaveCloseID']").click();
		Thread.sleep(6000);
	}

	/*TS-0090*/
	//create a dropship release with different types of manufacturer names, create vendor invoice and customer invoice for the same 
	@Test(enabled = false, priority = 9)
	public void useCase_9() throws InterruptedException 
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);


		List <String> manufacturername = new ArrayList<String>();
		manufacturername.add("no-data");
		manufacturername.add("dairy");
		manufacturername.add("honeywell analytics");
		manufacturername.add("western can");

		int a=57;
		for(int j=1;j<4;j++)

		{
			get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(4000);
			get("//*[@id='releasesTypeID']").click();
			Thread.sleep(3000);
			get("//*[@id='releasesTypeID']/option[2]").click();
			Thread.sleep(3000);
			get("//*[@id='ReleasesManuID']").sendKeys(manufacturername.get(j));
			Thread.sleep(5000);
			get("//body/ul[36]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='NoteID']").sendKeys("auto");
			Thread.sleep(3000);
			get("//*[@id='AllocatedID']").sendKeys("1000");
			Thread.sleep(3000);
			get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
			Thread.sleep(3000);
			get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
			Thread.sleep(5000);
			get("//*[@id='polineitems']/a").click();
			Thread.sleep(3000);


			for(int i=0;i<2;i++)
			{
				get("//*[@id='new_row_note']").sendKeys("ma310010");
				Thread.sleep(3000);
				get("//body/ul["+a+"]/li/a").click();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_quantityOrdered")).clear();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
			}

			if(a==57)
			{
				a++;
			}			


			Thread.sleep(3000);
			get("//*[@id='SaveLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='CancelLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='"+j+"']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();	
			Thread.sleep(3000);
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(5000);
			get("//button[contains(.,'Cancel')]").click();
			Thread.sleep(3000);
			get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
			Thread.sleep(3000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
			Thread.sleep(3000);
			get("//*[@id='vendorinvoiceidclosebutton']").click();
			Thread.sleep(3000);
			get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
			Thread.sleep(3000);
			get("//*[@id='customerInvoicebtnID']").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(6000);
			get("//*[@id='cICheckTab2']/a").click();
			Thread.sleep(6000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(10000);
			get("//*[@id='CuInvoiceSaveCloseID']").click();
			Thread.sleep(6000);
		}
	}


	/*TS-0091*/
	//making partial customer invoice and checking whether the billed and unbilled amount is updating properly 
	@Test(enabled = false, priority = 10)
	public void useCase_10() throws InterruptedException 
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();

		Thread.sleep(4000);
		get("//*[@id='new_row_note']").sendKeys("ma310010");
		Thread.sleep(3000);
		get("//body/ul[57]/li/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("new_row_quantityOrdered")).clear();
		Thread.sleep(3000);
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);


		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);
		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(3000);
		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidclosebutton']").click();
		Thread.sleep(3000);

		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);

		List <String> partialCuInvoice = new ArrayList<String>();
		partialCuInvoice.add("500");
		partialCuInvoice.add("300");
		partialCuInvoice.add("200");


		for(int qp=0;qp<3;qp++)
		{
			get("//*[@id='customerInvoicebtnID']").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(6000);
			get("//*[@id='cICheckTab2']/a").click();
			Thread.sleep(6000);

			Actions action = new Actions(driver);
			action.moveToElement(get("//*[@id='customerInvoice_lineitems']/tbody/tr[3]/td[6]")).doubleClick().perform();
			Thread.sleep(2000);
			get("//*[@id='2_unitCost']").click();
			Thread.sleep(2000);
			get("//*[@id='2_unitCost']").clear();
			Thread.sleep(2000);
			get("//*[@id='2_unitCost']").sendKeys(partialCuInvoice.get(qp) + Keys.ENTER);
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(10000);
			get("//*[@id='CuInvoiceSaveCloseID']").click();
			Thread.sleep(6000);
			String billedamount = get("//*[@id='billedamount']").getText(); 
			System.out.println("Billed:" + billedamount);
			String unbilledamount = get("//*[@id='unbilledamount']").getText(); 
			System.out.println("UnBilled:" + unbilledamount);
		}
	}

	
	/*TS-0092*/
	//entering the same vendor invoice number and handling the popups - this script causing problem
	@Test(enabled = false, priority = 11)
	public void useCase_11() throws InterruptedException 
	{
		CreateJobUsingQuickBook();
		Thread.sleep(4000);

		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(4000);
		get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(4000);
		get("//*[@id='releasesTypeID']").click();
		Thread.sleep(3000);
		get("//*[@id='releasesTypeID']/option[2]").click();
		Thread.sleep(3000);
		get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
		Thread.sleep(5000);
		get("//body/ul[36]/li/a").click();
		Thread.sleep(3000);
		get("//*[@id='NoteID']").sendKeys("auto");
		Thread.sleep(3000);
		get("//*[@id='AllocatedID']").sendKeys("1000");
		Thread.sleep(3000);
		get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
		Thread.sleep(3000);

		get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
		Thread.sleep(5000);
		get("//*[@id='polineitems']/a").click();

		Thread.sleep(3000);

		int l = 1;
		int m = 5;
		for(int i=0; i<5; i++)

		{
			get("//*[@id='new_row_note']").sendKeys("*");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Integer.toString(l));
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_unitCost")).sendKeys(Integer.toString(m));

			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_priceMultiplier")).sendKeys(Integer.toString(l) + Keys.ENTER);
			l++; m++;
		}	

		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='canDoVIID_5']").click();
		get("//*[@id='canDoVIID_4']").click();
		get("//*[@id='canDoVIID_3']").click();
		get("//*[@id='vendorInvoiceNum']").sendKeys("4");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("4");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(5000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='canDoVIID_5']").click();
		get("//*[@id='vendorInvoiceNum']").sendKeys("4");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("4");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(3000);
		get("//span[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//span[contains(.,'OK')]").click();
		Thread.sleep(5000);


		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='vendorInvoiceNum']").sendKeys("4");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("4");
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(3000);
		get("//span[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//span[contains(.,'OK')]").click();
		Thread.sleep(5000);


		get("//*[@id='SaveLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='CancelLinesPOButton']").click();
		Thread.sleep(3000);
		get("//*[@id='1']/td[12]").click();
		Thread.sleep(5000);
		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
		Thread.sleep(3000);
		get("//button[contains(.,'Yes')]").click();	
		Thread.sleep(3000);
		get("//*[@id='vendorInvoiceNum']").sendKeys("4");
		Thread.sleep(2000);
		get("//*[@id='proNumberID']").sendKeys("4");
		Thread.sleep(3000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(3000);
		get("//span[contains(.,'Yes')]").click();
		Thread.sleep(3000);
		get("//span[contains(.,'OK')]").click();
		Thread.sleep(5000);
		get("//button[contains(.,'Cancel')]").click();
		Thread.sleep(3000);
		//		get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
		//		Thread.sleep(3000);
		//		get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();
		//		Thread.sleep(3000);
		//		get("//*[@id='vendorinvoiceidclosebutton']").click();
		//		Thread.sleep(3000);


	}


	/*TS-0094*/
	//searching specific job, opening vendor invoice, edit it(adding freight) and save it 
	@Test(enabled = false, priority = 12)
	public void useCase_12() throws InterruptedException 

	{
		//login();
		get("//*[@id='mainMenuHomePage']/a").click();
		Thread.sleep(3000);
		driver.findElement(By.id("jobsearch")).sendKeys(Keys.chord("feb20"));
		get("//tbody/tr[4]/td[3]/input[@class='searchbutton']").click();
		Thread.sleep(4000);
		get("//*[@id='jobreleasetab']").click();
		Thread.sleep(8000);
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorInvoicebtnID']").click();
		Thread.sleep(3000);

		//editing freight
		get("//*[@id='freight_ID']").clear();
		Thread.sleep(3000);
		get("//*[@id='freight_ID']").sendKeys("8");
		Thread.sleep(2000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").clear();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").sendKeys("test");
		Thread.sleep(2000);
		get("//body/div[33]/div[11]/div/button/span").click();
		//		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shiping']//tbody/tr[2]/td[6]")));


		//editing vendor inv# number
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorInvoicebtnID']").click();
		Thread.sleep(3000);

		get("//*[@id='vendorInvoiceNum']").clear();
		Thread.sleep(3000);
		get("//*[@id='vendorInvoiceNum']").sendKeys("123");

		Thread.sleep(2000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").clear();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").sendKeys("tester");
		Thread.sleep(2000);
		get("//body/div[33]/div[11]/div/button/span").click();
		//Thread.sleep(5000);

		WebDriverWait wait1 = new WebDriverWait(driver, 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shiping']//tbody/tr[2]/td[6]")));


		//editing Pro# number
		get("//*[@id='shiping']//tbody/tr[2]/td[6]").click();
		Thread.sleep(3000);
		get("//*[@id='vendorInvoicebtnID']").click();
		Thread.sleep(3000);

		get("//*[@id='proNumberID']").clear();
		Thread.sleep(3000);
		get("//*[@id='proNumberID']").sendKeys("123");

		Thread.sleep(2000);
		get("//*[@id='vendorinvoiceidbutton']").click();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").clear();
		Thread.sleep(2000);
		get("//*[@id='jobinvreasonttextid']").sendKeys("tested");
		Thread.sleep(2000);
		get("//body/div[33]/div[11]/div/button/span").click();
		Thread.sleep(4000);


	}


	/*TS-0095*/
	//entering the different vendor invoice number and handling the popups 
		@Test(enabled = false, priority = 13)
		public void useCase_13() throws InterruptedException 
		{
			CreateJobUsingQuickBook();
			Thread.sleep(4000);

			get("//*[@id='jobreleasetab']").click();
			Thread.sleep(4000);
			get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(4000);
			get("//*[@id='releasesTypeID']").click();
			Thread.sleep(3000);
			get("//*[@id='releasesTypeID']/option[2]").click();
			Thread.sleep(3000);
			get("//*[@id='ReleasesManuID']").sendKeys("venture");
			Thread.sleep(5000);
			get("//body/ul[36]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='NoteID']").sendKeys("vendorinvoice");
			Thread.sleep(3000);
			get("//*[@id='AllocatedID']").sendKeys("1000");
			Thread.sleep(3000);
			get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
			Thread.sleep(3000);

			get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
			Thread.sleep(5000);
			get("//*[@id='polineitems']/a").click();

			Thread.sleep(3000);

			int l = 1;
			int m = 5;
			for(int i=0; i<5; i++)

			{
				get("//*[@id='new_row_note']").sendKeys("*");
				Thread.sleep(3000);
				get("//body/ul[57]/li/a").click();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_quantityOrdered")).clear();
				Thread.sleep(3000);
				driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(Integer.toString(l));
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_unitCost")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_unitCost")).clear();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_unitCost")).sendKeys(Integer.toString(m));

				Thread.sleep(2000);
				driver.findElement(By.id("new_row_priceMultiplier")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_priceMultiplier")).clear();
				Thread.sleep(2000);
				driver.findElement(By.id("new_row_priceMultiplier")).sendKeys(Integer.toString(l) + Keys.ENTER);
				l++; m++;
			}	

			get("//*[@id='SaveLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='CancelLinesPOButton']").click();
			Thread.sleep(3000);


			get("//*[@id='1']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();	
			Thread.sleep(3000);
			get("//*[@id='canDoVIID_5']").click();
			get("//*[@id='canDoVIID_4']").click();
			get("//*[@id='canDoVIID_3']").click();
			get("//*[@id='vendorInvoiceNum']").sendKeys("3");
			Thread.sleep(2000);
			get("//*[@id='proNumberID']").sendKeys("3");
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(5000);


			get("//*[@id='1']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();	
			Thread.sleep(3000);
			get("//*[@id='canDoVIID_5']").click();
			get("//*[@id='vendorInvoiceNum']").sendKeys("4");
			Thread.sleep(2000);
			get("//*[@id='proNumberID']").sendKeys("4");
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(3000);
			


			get("//*[@id='1']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();	
			Thread.sleep(3000);
			get("//*[@id='vendorInvoiceNum']").sendKeys("5");
			Thread.sleep(2000);
			get("//*[@id='proNumberID']").sendKeys("5");
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Cancel')]").click();	

		}

		
		/*TS-0095*/
		//Creating a customer invoice for the dropship, viewing pdf and sending email
		@Test(enabled = false, priority = 14)
		public void useCase_14() throws InterruptedException 
		{
			CreateJobUsingQuickBook();
			Thread.sleep(4000);

			get("//*[@id='jobreleasetab']").click();
			Thread.sleep(4000);
			get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(4000);
			get("//*[@id='releasesTypeID']").click();
			Thread.sleep(3000);
			get("//*[@id='releasesTypeID']/option[2]").click();
			Thread.sleep(3000);
			get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
			Thread.sleep(5000);
			get("//body/ul[36]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='NoteID']").sendKeys("auto");
			Thread.sleep(3000);
			get("//*[@id='AllocatedID']").sendKeys("1000");
			Thread.sleep(3000);
			get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
			Thread.sleep(3000);

			get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
			Thread.sleep(5000);
			get("//*[@id='polineitems']/a").click();


			Thread.sleep(3000);
			get("//*[@id='new_row_note']").sendKeys("ma310010");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);

			Actions action = new Actions(driver);
			action.moveToElement(get("//*[@id='lineItemGrid']/tbody/tr[2]/td[7]")).doubleClick().perform();
			get("//*[@id='new_row_quantityOrdered']").sendKeys("10" + Keys.ENTER);

			
			Thread.sleep(3000);
			get("//*[@id='SaveLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='CancelLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='1']/td[12]").click();
			Thread.sleep(5000);
			
			get("//*[@id='customerInvoicebtnID']").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Yes')]").click();
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(6000);
			get("//*[@id='cICheckTab2']/a").click();
			Thread.sleep(6000);
			get("//*[@id='imgInvoicePDF']/input").click();
			Thread.sleep(3000);
			get("//*[@id='imgInvoiceEmail']/input").click();
			Thread.sleep(3000);
			get("//div[contains(.,'Email Compose')][1]//input[@name='etoaddr']").clear();
			Thread.sleep(2000);
			get("//div[contains(.,'Email Compose')][1]//input[@name='etoaddr']").sendKeys("sathish.kumar@excelblaze.com");
			Thread.sleep(3000);
			get("//*[@id='emailform']/fieldset/table/tbody/tr[5]/td[2]/div[2]/div").sendKeys("testmail");
			Thread.sleep(3000);
			get("//div[contains(.,'Email Compose')][1]//input[@value='Send']").click();
						
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,'Message')]/div[1]")));
			
			get("//div[contains(.,'Message')]//button[contains(.,'OK')]").click();
			Thread.sleep(3000);
			get("//*[@id='CuInvoiceSaveID']").click();
			Thread.sleep(10000);
			get("//*[@id='CuInvoiceSaveCloseID']").click();
			Thread.sleep(6000);	
			
		}
		
		/*TS-0096*/
		//Create a purchase order and create vendor invoice, while creating change the vendor name and save it
		@Test(enabled = true, priority = 15)
		public void useCase_15() throws InterruptedException 
		{

			CreateJobUsingQuickBook();
			Thread.sleep(4000);

			get("//*[@id='jobreleasetab']").click();
			Thread.sleep(4000);
			get("//*[@id='ui-tabs-5']/table[3]/tbody/tr/td/fieldset/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(4000);
			get("//*[@id='releasesTypeID']").click();
			Thread.sleep(3000);
			get("//*[@id='releasesTypeID']/option[2]").click();
			Thread.sleep(3000);
			get("//*[@id='ReleasesManuID']").sendKeys("greenheck");
			Thread.sleep(5000);
			get("//body/ul[36]/li/a").click();
			Thread.sleep(3000);
			get("//*[@id='NoteID']").sendKeys("auto");
			Thread.sleep(3000);
			get("//*[@id='AllocatedID']").sendKeys("1000");
			Thread.sleep(3000);
			get("//*[@id='openReleaseDigForm']/table[2]/tbody/tr/td[4]/input").click();
			Thread.sleep(3000);

			get("//*[@id='pogeneral']/table/tbody/tr/td[4]/input[@id='POReleaseID']").click();
			Thread.sleep(5000);
			get("//*[@id='polineitems']/a").click();


			Thread.sleep(3000);
			get("//*[@id='new_row_note']").sendKeys("ma310010");
			Thread.sleep(3000);
			get("//body/ul[57]/li/a").click();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("new_row_quantityOrdered")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("new_row_quantityOrdered")).sendKeys("1" + Keys.ENTER);
			

			Thread.sleep(3000);
			get("//*[@id='SaveLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='CancelLinesPOButton']").click();
			Thread.sleep(3000);
			get("//*[@id='1']/td[12]").click();
			Thread.sleep(5000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[1]/fieldset/table/tbody/tr/td[1]/input").click();
			Thread.sleep(3000);

			get("//button[contains(.,'Yes')]").click();
			Thread.sleep(3000);
			
			get("//*[@id='manufacurterNameID']").clear();
			get("//*[@id='manufacurterNameID']").sendKeys("Dairy");
			Thread.sleep(3000);
			get("//body/ul[35]/li/a").click();
			Thread.sleep(3000);
			
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(5000);
			get("//button[contains(.,'Cancel')]").click();
			Thread.sleep(3000);
			get("//*[@id='shiping']/tbody/tr[2]/td[6]").click();
			Thread.sleep(3000);
			get("//*[@id='ui-tabs-5']/fieldset/div[2]/table/tbody/tr[2]/td[2]/input[2]").click();

			Thread.sleep(3000);
			get("//*[@id='vendorInvoiceNum']").sendKeys("7");
			Thread.sleep(2000);
			get("//*[@id='proNumberID']").sendKeys("7");
			Thread.sleep(2000);
			get("//*[@id='vendorinvoiceidbutton']").click();
			Thread.sleep(3000);
			
			get("//*[@id='jobinvreasonttextid']").sendKeys("test");
			Thread.sleep(2000);
			get("//body/div[33]/div[11]/div/button/span").click();
//			get("//button[contains(.,'OK')]").click();
			Thread.sleep(3000);
			get("//button[contains(.,'Cancel')]").click();
			
		}
		
	@AfterTest
	public void teardown() 
	{
		//driver.quit();
	}

}
