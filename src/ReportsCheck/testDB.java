package ReportsCheck;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mysql.jdbc.ResultSetMetaData;


import java.sql.ResultSet;
import java.sql.DriverManager;

public class testDB {

	  private StringBuffer verificationErrors = new StringBuffer();
		private WebDriver driver;
	private String baseUrl;
	@BeforeTest
	public void setUp() throws Exception 
	{
		  System.setProperty("webdriver.chrome.driver","C:/Users/sathish_kumar/Downloads/chromedriver.exe");    
		  driver = new ChromeDriver();
		  baseUrl = "http://qa.tt.eb.local/turbotracker/turbo/";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public WebDriverWait getWait()
	{
		  return new WebDriverWait(driver, 60);
	}	
	
	@Parameters({ "username", "password", "table" })
	@Test
	public void testDB1(String LoginUsername, String Loginpassword, String LoginTable) throws Exception {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
		String username = "turbo";
		String password = "turbo@2016";
		String userid=null;

		//	try{
			driver.get(baseUrl);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
			driver.findElement(By.linkText("Login")).click();
			//Authentication
			driver.findElement(By.id("uname")).clear();
			driver.findElement(By.id("uname")).sendKeys(LoginUsername);
			driver.findElement(By.id("pwd")).clear();
			driver.findElement(By.id("pwd")).sendKeys(Loginpassword);
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(2000);
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
		
				String baseQuery = "select * from " + LoginTable + " where LoginName = '" + LoginUsername + "'";
				 baseQuery = baseQuery+";";

				 
				Class.forName("com.mysql.jdbc.Driver");

				Connection con = DriverManager.getConnection(dbUrl, username, password);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(baseQuery);
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();

				ArrayList<Object> records = new ArrayList<>();

				while (rs.next()) {

					for (int i = 1; i <= columnsNumber; i++) {
						userid=rs.getString(1);
						records.add(rs.getString(i));
						System.out.print(rs.getString(i) + " "); // Print one element of a row
					}
					System.out.println();

			

					// While Loop to iterate through all data and print results
					/*
					 * while (rs.next()){ String myName = rs.getString(1);
					 * String myAge = rs.getString(2); System.
					 * out.println(myName+"  "+myAge); }
					 */

				}	
				Statement stmt1 = con.createStatement();
				String BaseQuery2= "SELECT * FROM (SELECT * FROM TPUsage ORDER BY tpUsageID DESC LIMIT 30) sub WHERE sub.createdDate <= DATE_SUB(NOW(), INTERVAL 1 MINUTE) AND sub.userID ="+ userid+";";
				ResultSet rs1 = stmt1.executeQuery(BaseQuery2);
				ResultSetMetaData rsmd1 = (ResultSetMetaData) rs1.getMetaData();
				int columnsNumber1 = rsmd1.getColumnCount();
				ArrayList<Object> records1 = new ArrayList<>();

				while (rs1.next()) {

					for (int i = 1; i <= columnsNumber1; i++) {
						records1.add(rs1.getString(i));
						System.out.print(rs1.getString(i) + " "); // Print one element of a row
					}
					System.out.println();
				}
				con.close();				
				// closing DB Connection
		}
	 @AfterTest
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	}