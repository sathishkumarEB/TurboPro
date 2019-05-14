package Login;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.turbopro.MethodsLibrary.Methods;			


public class TestAccessAll extends Methods  {

	static WebDriver driver;
	
	@Test
	public void accessDriver() throws InterruptedException {
//		WebDriver driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("qa.tt.eb.local/turbotracker/turbo/");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		driver.findElement(By.className("login")).click();

		//Authentication
		driver.findElement(By.id("uname")).clear();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sales")));
		driver.findElement(By.linkText("Sales")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
		driver.findElement(By.linkText("Projects")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Banking")));
		driver.findElement(By.linkText("Banking")).click();

		navigateBankWriteChecks();
		try{
			if(driver.findElement(By.xpath("//span[text()='Information']")).isDisplayed())
			{
				driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Write checks popup not showing!");
		}

		Thread.sleep(2000);
		navigateBankReissueChecks();
		Thread.sleep(2000);
		navigateBankReconcileAccounts();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
		driver.findElement(By.linkText("Inventory")).click();
		Thread.sleep(4000);
		navigateInventoryCategories();
		Thread.sleep(3000);
		navigateInventoryWarehouses();
		Thread.sleep(3000);
		receiveInventory();
		Thread.sleep(3000);
		navigateInventoryTransfer();
		Thread.sleep(3000);
		navigateInventoryOrderPoints();
		Thread.sleep(3000);
		navigateInventoryValue();
		Thread.sleep(3000);
		navigateInventoryCount();
		Thread.sleep(3000);
		navigateInventoryTransactions();
		Thread.sleep(3000);
		navigateInventoryAdjustment();
		Thread.sleep(3000);
		navigateCustomers();
		Thread.sleep(3000);
		navigateCustomerPayments();
		Thread.sleep(3000);
		navigateCusUnappliedPayments();
		Thread.sleep(3000);
		navigateCustomerStatement();
		Thread.sleep(3000);
		navigateCustomerSalesOrder();
		Thread.sleep(3000);
		navigateCustomerInvoice();
		Thread.sleep(3000);
		navigateCustomerFinanceCharges();
		Thread.sleep(3000);
		navigateCustomerTaxAdjustments();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Tax Adjustments']")).isDisplayed())
		{
			driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		}

		Thread.sleep(3000);
		navigateCustomerCreditDebitMemo();
		Thread.sleep(3000);
		navigateCustomerAddCostImport();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[text()='Add Cost Import']")).isDisplayed())
		{
			driver.findElement(By.xpath("//body/div[9]/div[1]/a/span")).click();
		}
		Thread.sleep(3000);
		navigateCustomerSalesOrderTemplate();
		Thread.sleep(3000);
		navigateVendors();
		Thread.sleep(3000);
		navigateVendorPurchaseOrders();
		Thread.sleep(3000);
		navigateVendorPayBills();
		Thread.sleep(3000);
		navigateVendorInvoices();
		Thread.sleep(3000);
		navigateEmployeeCommission();
		Thread.sleep(3000);
		navigateRolodex();
		Thread.sleep(3000);
		navigateUsers();
		Thread.sleep(3000);
		navigateSettings();
		Thread.sleep(3000);
		navigateChartOfAccounts();
		Thread.sleep(3000);
		navigateDivisions();
		Thread.sleep(3000);
		navigateTaxTerritories();
		Thread.sleep(3000);
		navigateGeneralLedger();
		Thread.sleep(3000);
		navigateBalanceSheet();
		Thread.sleep(3000);
		navigateTrialBalance();
		Thread.sleep(3000);
		navigateIncomeStatement();
		Thread.sleep(3000);
		navigateJournalEntries();
		Thread.sleep(3000);
		navigateAccountingCycles();
		Thread.sleep(3000);
		navigateGLTransaction();
		Thread.sleep(3000);



	}

}
