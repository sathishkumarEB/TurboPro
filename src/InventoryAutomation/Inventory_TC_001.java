package InventoryAutomation;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.turbopro.basepackages.*;
	
public class Inventory_TC_001 extends Variables {
	static ExcelSheetDriver xlsUtil;
	private StringBuffer verificationErrors = new StringBuffer();
	//Constructor to initialze Excel for Data source

	public Inventory_TC_001() throws BiffException, IOException
	{
		//Let's assume we have only one Excel File which holds all Testcases. weird :) Just for Demo !!!
		xlsUtil = new ExcelSheetDriver("C:\\Users\\sathish_kumar\\Documents\\TP_input.xls");
		//Load the Excel Sheet Col in to Dictionary for Further use in our Test cases.
		xlsUtil.ColumnDictionary();
	}

	private WebDriver driver;
	private String baseUrl,OurPO;
	private int totalPages;

	@BeforeTest
	public void setUp() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/sathish_kumar/Downloads/chromedriver.exe");    
		driver = new ChromeDriver();
		baseUrl = xlsUtil.ReadCell(xlsUtil.GetCell("baseURL"),1);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public WebDriverWait getWait()
	{
		return new WebDriverWait(driver, 60);
	}	

	@Test
	public void testingInventory() throws Exception {
		Actions action1 = new Actions(driver);
	//	try{
		driver.get(baseUrl);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
		driver.findElement(By.linkText("Login")).click();
		//Authentication
		driver.findElement(By.id("uname")).clear();
		driver.findElement(By.id("uname")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("username"),1));
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("password"),1));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
		driver.findElement(By.linkText("Inventory")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addCustomersButton")));
		driver.findElement(By.id("addCustomersButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("codeId")).clear();
		driver.findElement(By.id("codeId")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("code"),1));
		driver.findElement(By.id("descriptionId")).clear();
		driver.findElement(By.id("descriptionId")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Description"),1));
		driver.findElement(By.id("inventoryIDBox")).click();

		driver.findElement(By.xpath("//span[contains(text(), 'OK')]")).click();

		Select departmentSelected = new Select(driver.findElement(By.id("departmentId")));
		departmentSelected.selectByVisibleText(xlsUtil.ReadCell(xlsUtil.GetCell("Department"),1));
		Select CategorySelect = new Select(driver.findElement(By.id("categoryId")));
		CategorySelect.selectByVisibleText("Three");
		Select binSelect = new Select(driver.findElement(By.id("binId")));
		binSelect.selectByVisibleText(xlsUtil.ReadCell(xlsUtil.GetCell("Bin_per_Warehouse"),1));
		driver.findElement(By.id("poIDBox")).click();
		driver.findElement(By.id("soIDBox")).click();
		driver.findElement(By.id("invoiceIDBox")).click();
		driver.findElement(By.id("pickTicketIDBox")).click();
		driver.findElement(By.id("sellingPriceId")).clear();
		driver.findElement(By.id("sellingPriceId")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("base_selling_price"),1));
		driver.findElement(By.id("taxableIDBox")).click();
		driver.findElement(By.id("multiplierId")).clear();
		driver.findElement(By.id("multiplierId")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("multiplier"),1));
		driver.findElement(By.id("factoryCostId")).clear();
		driver.findElement(By.id("factoryCostId")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("factory_cost"),1));
//		driver.findElement(By.id("saveUserButton")).click();
		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@value='Save & Close']")).click();  
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addCustomersButton")));
		action1.moveToElement(driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/a"))).build().perform();
		action1.moveToElement(driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]"))).build().perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@alt='Add new Job']")));
		driver.findElement(By.xpath("//input[@alt='Add new Job']")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("POSaveID")));
		driver.findElement(By.id("vendorsearch")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Vendor"),1));
		List<WebElement> vendor_suggestion = driver.findElements(By.className("ui-menu-item"));
		for(WebElement temp:vendor_suggestion)
		{
			if(temp.getText().startsWith(xlsUtil.ReadCell(xlsUtil.GetCell("Vendor"),1)))
				temp.click();
		}
		driver.findElement(By.id("POSaveID")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("lineTab")).click();
		OurPO=driver.findElement(By.id("ourPoLineId")).getAttribute("value");
		System.out.println(OurPO);
		driver.findElement(By.id("new_row_note")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Product No"),1));
		List<WebElement> productNo_suggestion = driver.findElements(By.className("ui-menu-item"));
		for(WebElement temp:productNo_suggestion)
		{
			if(temp.getText().startsWith(xlsUtil.ReadCell(xlsUtil.GetCell("Product No"),1)))
				temp.click();
		}
		driver.findElement(By.id("new_row_quantityOrdered")).clear();
		driver.findElement(By.id("new_row_quantityOrdered")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Qty"),1) + Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.id("SaveLinesPOButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("CancelLinesPOButton")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@alt='Add new Job']")));
		action1.moveToElement(driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"))).build().perform();
		driver.findElement(By.linkText("Receive Inventory")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='New']")));
		driver.findElement(By.xpath("//input[@value='New']")).click();
		
//upto this
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
		driver.findElement(By.xpath("//*[@id='ponumber']")).sendKeys(OurPO);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[10]/div[11]/div/button[1]")));
		driver.findElement(By.xpath("//body/div[10]/div[11]/div/button[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
		driver.findElement(By.xpath("//*[@id='1']/td[5]")).click();
		driver.findElement(By.xpath("//*[@id='1_quantityReceived']")).clear();
		driver.findElement(By.xpath("//*[@id='1_quantityReceived']")).sendKeys("1" + Keys.ENTER);
		//get("//*[@id='ReceiveAllSaveID']").click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='POSaveID']")).click();
		Thread.sleep(7000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='priorreceipts']")));
		driver.findElement(By.xpath("//*[@id='priorreceipts']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[8]/div[1]/a/span")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='POCloseID']")));
		driver.findElement(By.xpath("//*[@id='POCloseID']")).click();
		Thread.sleep(3000);
		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"))).perform();
		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"))).perform();
		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")).click();
		driver.findElement(By.xpath("//*[@id='po']")).sendKeys(OurPO);
		driver.findElement(By.xpath("//*[@id='saveTermscancelButton']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='freightGeneralId']")).clear();
		driver.findElement(By.xpath("//*[@id='freightGeneralId']")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Freight"),1));
		Thread.sleep(3000);
		driver.findElement(By.id("addNewVeInvFmDlgbuttonsave")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addNewVeInvFmDlgbuttonsave")));
		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();
//		Label label= new Label(xlsUtil.ColumnCount()+1, 1, "RESULTS");
//		xlsUtil.wrksheet.findCell(label);
//
//		}
//		catch(Exception e)
//		{
//			
//		}
		
		
		
		
		
		
//============		
//		driver.findElement(By.linkText("Inventory")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addCustomersButton")));
//		driver.findElement(By.id("searchJob")).clear();
//		driver.findElement(By.id("searchJob")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Description"),1));
//		driver.findElement(By.id("goSearchButtonID")).click();
//		String avgcost = driver.findElement(By.id("avgCostId")).getText();
//		System.out.println(avgcost);
//		
//		
//		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"))).perform();
//		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"))).perform();
//		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]")).click();
//		
//		if (driver.findElement(By.id("msgDlg")).isDisplayed()) {
//			driver.findElement(By.xpath("//span[(text()= 'OK')]")).click();
//			Thread.sleep(3000);
//			action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"))).perform();
//			action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"))).perform();
//			driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]")).click();
//		}
//		
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
//		driver.findElement(By.id("searchJob")).clear();
////		driver.findElement(By.id("searchJob")).sendKeys(OurPO);
//		driver.findElement(By.id("searchJob")).sendKeys("102707");
//		driver.findElement(By.id("goSearchButtonID")).click();
//		
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='1']/td[@title='102707']")));
//		//getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='1']/td[@title='"+OurPO+"']")));
//		action1.moveToElement(driver.findElement(By.xpath("//tr[@id='1']/td[@title='102707']"))).doubleClick().build().perform();
//		//action1.moveToElement(driver.findElement(By.xpath("//tr[@id='1']/td[@title='"+OurPO+"']"))).doubleClick().build().perform();
//		
//		driver.findElement(By.id("vendorInvoicePO")).clear();
//		driver.findElement(By.id("vendorInvoicePO")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Vendor_Invoice"),1));
//		
//		driver.findElement(By.id("proPO")).clear();
//		driver.findElement(By.id("proPO")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Pro_#"),1));
//		
//		driver.findElement(By.id("addNewVeInvFmDlgbuttonsave")).click();
//
//		driver.findElement(By.xpath("//span[(text()= 'No')]")).click();
//		driver.findElement(By.id("addNewVeInvFmDlgbuttonsave")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//span[(text()= 'Yes')]")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("invreasonttextid")));
//		driver.findElement(By.id("invreasonttextid")).clear();
//		driver.findElement(By.id("invreasonttextid")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Reason"),1));
//		driver.findElement(By.xpath("//span[(text()= 'OK')]")).click();
//		
//		getWait().until(ExpectedConditions.elementToBeClickable(By.id("addNewVeInvFmDlgbuttonsave")));
//		
//		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();
//
////		==========
//		
//		
//		action1.moveToElement(driver.findElement(By.xpath("//*[@id='mainmenuInventoryPage']/a"))).build().perform();
//		driver.findElement(By.linkText("Receive Inventory")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='New']")));
//		driver.findElement(By.xpath("//input[@value='New']")).click();
//		
//
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("ponumber")));
//		driver.findElement(By.xpath("//*[@id='ponumber']")).sendKeys(OurPO);
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[10]/div[11]/div/button[1]")));
//		driver.findElement(By.xpath("//body/div[10]/div[11]/div/button[1]")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/td[5]")));
//		
//		driver.findElement(By.xpath("//*[@id='1']/td[5]")).click();
//		driver.findElement(By.xpath("//*[@id='1_quantityReceived']")).clear();
//		driver.findElement(By.xpath("//*[@id='1_quantityReceived']")).sendKeys("1" + Keys.ENTER);
//		
//		driver.findElement(By.id("ReceiveAllSaveID")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@id='POSaveID']")).click();
//		Thread.sleep(7000);
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='priorreceipts']")));
//		driver.findElement(By.xpath("//*[@id='priorreceipts']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//body/div[8]/div[1]/a/span")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='POCloseID']")));
//		driver.findElement(By.xpath("//*[@id='POCloseID']")).click();
//		Thread.sleep(3000);
//		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"))).perform();
//		action1.moveToElement(driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[2]"))).perform();
//		driver.findElement(By.xpath("//*[@id='mainMenuCompanyPage']/ul/li[2]/ul/li[3]")).click();
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
//		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")).click();
//		driver.findElement(By.xpath("//*[@id='po']")).sendKeys(OurPO);
//		driver.findElement(By.xpath("//*[@id='saveTermscancelButton']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@id='freightGeneralId']")).clear();
//		driver.findElement(By.xpath("//*[@id='freightGeneralId']")).sendKeys(xlsUtil.ReadCell(xlsUtil.GetCell("Freight"),1));
//		Thread.sleep(3000);
//		driver.findElement(By.id("addNewVeInvFmDlgbuttonsave")).click();
//		Thread.sleep(7000);
//		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("addNewVeInvFmDlgbuttonsave")));
//		driver.findElement(By.id("addNewVeInvFmDlgclsbutton")).click();
		
		
		
		
		
		
		
	}

	
	
	
	
	
	

	private void setValueIntoCell(String readCell) {
		// TODO Auto-generated method stub
		
	}
	@AfterTest(enabled = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
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
