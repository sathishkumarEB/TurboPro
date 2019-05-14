package Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mysql.jdbc.ResultSetMetaData;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ConnectionLeak {
	static String driverPath = "C:/Users/sathish_kumar/Downloads/";
	public WebDriver driver;
		  
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
	  System.setProperty("webdriver.chrome.silentOutput", "true");
	  System.setProperty("webdriver.chrome.args", "--disable-logging"); // to disable user logging
	  
	  driver = new ChromeDriver();
//		driver.manage().window().maximize(); 
  }
  
  @Test (priority = 1, enabled = false)
  public void savingRelease() throws InterruptedException 
  {
	  driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  Actions act = new Actions(driver);
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]"))).doubleClick().build().perform();
	  driver.findElement(By.id("jobreleasetab")).click();
	  for(int i=1; i<=3; i++)
	  {
	  Thread.sleep(10000);
	  act.moveToElement(driver.findElement(By.xpath("//*[@id='1']/td[12]"))).doubleClick().build().perform();
	  driver.findElement(By.xpath("//*[@id='polineitems']/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='SaveLinesPOButton']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id='CancelLinesPOButton']")).click();
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  }
  }
  
  
  @Test(priority = 2, enabled = false)
  public void savingCuInvoice() throws InterruptedException 
  {
	  driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  Actions act = new Actions(driver);
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]"))).doubleClick().build().perform();
	  driver.findElement(By.id("jobreleasetab")).click();
//	  for(int i=1; i<=3; i++)
//	  {
	  Thread.sleep(10000);
	  driver.findElement(By.xpath("//*[@id='1']/td[12]")).click();
	  driver.findElement(By.xpath("//*[@id='shiping']//tr[2]/td[9]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='customerInvoicebtnID']")).click();
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//*[@id='cICheckTab2']/a")).click();
	  driver.findElement(By.xpath("//*[@id='CuInvoiceSaveID']")).click();
	  Thread.sleep(4000);
//	  driver.findElement(By.xpath("/html/body/div[41]/div[11]/div/button[1]/span")).click();
	  driver.findElement(By.xpath("//span[contains(.,'Yes')]")).click();
	  driver.findElement(By.xpath("//*[@id='invreasonttextid']")).sendKeys("test");
//	  driver.findElement(By.xpath("/html/body/div[32]/div[11]/div/button/span")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//span[contains(.,'OK')]")).click();
	
	  
	//  driver.findElement(By.xpath("//*[@id='CuInvoiceSaveCloseID']")).click();
//	  }
  }
  

  @Test(priority = 3, enabled = false)
  public void addingVendorInvoice() throws InterruptedException 
  {
	  driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  Actions act = new Actions(driver);
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]"))).doubleClick().build().perform();
	  driver.findElement(By.id("jobreleasetab")).click();
	  Thread.sleep(9000);
	  driver.findElement(By.xpath("//table[@id='release']//tr[4]/td[12]")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//input[@title='Add Vendor Invoice']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Yes')]")));
	  driver.findElement(By.xpath("//span[contains(.,'Yes')]")).click();
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='vendorinvoice1']//tr[@id='1']/td[6]"))).doubleClick().build().perform();
	  WebElement ele = driver.findElement(By.xpath("//table[@id='vendorinvoice1']//tr[@id='1']/td[6]"));
	  Thread.sleep(2000);
	  Actions actions = new Actions(driver);
	  actions.moveToElement(ele);
	  actions.click();
	  actions.sendKeys("20" + Keys.ENTER);
	  actions.build().perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[@id='vendorinvoiceidbutton']")).click();
	  Thread.sleep(2000);
	  
	  List <WebElement> OkBtn = driver.findElements(By.xpath("//span[@class='ui-button-text'][contains(.,'OK')]"));
	  OkBtn.get(3).click();
	  
	  driver.findElement(By.xpath("//span[@class = 'ui-button-text'][contains(., 'Cancel')]")).click();
	  }
  
  
  @Test(priority = 4, enabled = true)
  public void addingCustomerInvoice() throws InterruptedException 
  {
	  driver.get("http://qa.tt.eb.local/turbotracker/turbo/");
	  driver.findElement(By.linkText("Login")).click();
	  driver.findElement(By.id("uname")).sendKeys("Admin");
	  driver.findElement(By.id("pwd")).sendKeys("D3m0");
	  driver.findElement(By.xpath("//table[@class='loginTableForm']/tbody/tr[6]/td[2]/input")).click();
	  Actions act = new Actions(driver);
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='lastOpenedJobs']/tbody/tr[2]/td[3]"))).doubleClick().build().perform();
	  driver.findElement(By.id("jobreleasetab")).click();
	  Thread.sleep(9000);
	  driver.findElement(By.xpath("//table[@id='release']//tr[4]/td[12]")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id='customerInvoicebtnID']")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Yes')]")));
	  driver.findElement(By.xpath("//span[contains(.,'Yes')]")).click();
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//*[@id='CuInvoiceSaveID']")).click();
	  Thread.sleep(9000);
	  driver.findElement(By.xpath("//*[@id='cICheckTab2']/a")).click();
	  act.moveToElement(driver.findElement(By.xpath("//table[@id='customerInvoice_lineitems']/tbody/tr[4]/td[6]"))).doubleClick().build().perform();
	  WebElement ele = driver.findElement(By.xpath("//table[@id='customerInvoice_lineitems']/tbody/tr[4]/td[6]"));
	  Thread.sleep(2000);
	  Actions actions = new Actions(driver);
	  actions.moveToElement(ele);
	  actions.click();
	  actions.sendKeys("30" + Keys.ENTER);
	  actions.build().perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id='CuInvoiceSaveID']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id='CuInvoiceSaveCloseID']")).click();
	
	  
//	  List <WebElement> OkBtn = driver.findElements(By.xpath("//span[@class='ui-button-text'][contains(.,'OK')]"));
//	  OkBtn.get(3).click();
//	  
//	  driver.findElement(By.xpath("//span[@class = 'ui-button-text'][contains(., 'Cancel')]")).click();
	  }
  
  
	// checking the database to check the invoice data
	@Test(enabled = false)
	public void dbCheckForCustomerInvoice() throws InterruptedException, ClassNotFoundException, SQLException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
		String username = "turbo";
		String password = "turbo@2016";
		String userid = null;

		String baseQuery = "select * from cuInvoice where InvoiceNumber = '" + cuInvoiceNumber
				+ "' and NonTaxableSales > 0";
		baseQuery = baseQuery + ";";

		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection(dbUrl, username, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(baseQuery);
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		ArrayList<Object> records = new ArrayList<>();

		while (rs.next()) {

			for (int i = 1; i <= columnsNumber; i++) {
				userid = rs.getString(1);
				records.add(rs.getString(i));
			}
		}

	}

  
 @AfterTest
  public void teardown() 
  {
	  //driver.quit();
  }

}

//Script for navigating to Users module and searching a user 
