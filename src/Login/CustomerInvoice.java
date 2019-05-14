package Login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CustomerInvoice {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public static WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.args", "--disable-logging"); 		// to disable user logging

		driver = new ChromeDriver();
//		driver.manage().window().maximize();
	}

	@Test(priority=1)
	public void customerInvoice() {
		
		driver.get("http://qa.tt.eb.local/turbotracker/turbo/");

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("uname")).sendKeys("Admin");
		driver.findElement(By.id("pwd")).sendKeys("D3m0");
		driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
				 WebElement company = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/a"));
				 WebElement customers = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]/a"));
				 WebElement invoice = driver.findElement(By.xpath(".//*[@id='mainMenuCompanyPage']/ul/li[1]/ul/li[5]/a"));
						 
				 Actions action = new Actions(driver);

				 action.moveToElement(company).perform();
				 action.moveToElement(customers).perform();
				 action.click(invoice).perform();				//navigating to customer invoice

				 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);		 
	}

	
	@Test(priority=2)
	public void SortByCreatedOn() {						//sort customer invoice based on Created On
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_createdOn']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_createdOn']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	
	@Test(priority=3)
	public void SortByCustomerPO() {					//sort customer invoice based on CustomerPO
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_customerPonumber']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_customerPonumber']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=3)
	public void SortByInvoice() {						//sort customer invoice based on Invoice
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_invoiceNumber']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_invoiceNumber']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=4)
	public void SortByJobName() {						//sort customer invoice based on Job name
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_jobname']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_jobname']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=5)
	public void SortByCustomer() {						//sort customer invoice based on Customer
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_quickJobName']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_quickJobName']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=6)
	public void SortByTotal() {							//sort customer invoice based on Total
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_invoiceAmount']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_invoiceAmount']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=7)
	public void SortByCheck() {							//sort customer invoice based on Check
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_chkNo']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_chkNo']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=8)
	public void SortByDatePaid() {						//sort customer invoice based on date paid
		
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_datePaid']")).click();
			driver.findElement(By.xpath(".//*[@id='jqgh_CustomerInvoiceGrid_datePaid']")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
	
	@Test(priority=9)
	public void SearchJob() {						//Search Invoice using job or invoice number
		
			driver.findElement(By.xpath(".//*[@id='searchJob']")).sendKeys("JN5857A1");
			driver.findElement(By.xpath(".//*[@id='search']/table/tbody/tr/td[2]/input[1]")).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(".//*[@id='1']/td[8]"))).doubleClick().perform();
			driver.findElement(By.xpath(".//*[@id='CuInvoiceSaveCloseID']")).click();
			
			}
	
	
	
	
	@AfterTest
	public void teardown() {
		// driver.quit();
	}

}

// Script for navigating to Customer Invoice, sorting invoices and searching any one of the job.
