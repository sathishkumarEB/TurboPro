package InventoryAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class cuInvoiceCreating extends Variables {
	//	static ExcelSheetDriver xlsUtil;
	HSSFWorkbook srcBook=null;
	HSSFSheet sourceSheet =null;
	private WebDriver driver;
	private String baseUrl, cuInvoiceNumber;

	private StringBuffer verificationErrors = new StringBuffer();
	//Constructor to initialize Excel for Data source
	public void cuInvoiceCreating() throws IOException {
		//srcBook = HSSFWorkbook(new File("C:\\Users\\sathish_kumar\\Documents\\InvoiceInputs.xls"));      
		//sourceSheet = srcBook.getSheetAt(0);

		//     XSSFRow sourceRow = sourceSheet.getRow(rownum);


		//private int totalPages;


		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\sathish_kumar\\Documents\\InvoiceInputs.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow  row = sheet.getRow(0);
		XSSFCell cell = null;

		int colNum = -1;
		{
			for(int i=0; i< row.getLastCellNum(); i++)
				if(row.getCell(i).getStringCellValue().trim().equals("baseURL"))
					colNum = i;
			System.out.println(colNum);

			{
				row = sheet.getRow(1);
				cell = row.getCell(colNum);
				String baseURL = String.valueOf(cell.getStringCellValue());		
			}
		}
	}




	@BeforeTest
	public void setUp() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/sathish_kumar/Downloads/chromedriver.exe");    
		driver = new ChromeDriver();
		//baseUrl = sourceSheet.getRow(1).getCell(ColumnNumber(1,"baseURL")).getStringCellValue();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private int ColumnNumber(int RowCount,String ColumnHeader) throws Exception
	{		int patchColumn = -1;

	sourceSheet.getRow(RowCount);
	for (int cn=0; cn<sourceSheet.getRow(RowCount).getLastCellNum(); cn++) {
		Cell c = sourceSheet.getRow(RowCount).getCell(cn);
		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
			// Can't be this cell - it's empty
			continue;
		}
		if (c.getCellType() == Cell.CELL_TYPE_STRING) {
			String text = c.getStringCellValue();
			if (ColumnHeader.equalsIgnoreCase(text)) {
				patchColumn = cn;
				break;
			}
		}
	}

	//		catch(Exception e){
	if (patchColumn == -1) {
		throw new Exception("None of the cells in the first row were Patch");
	} 
	else
		return patchColumn;
	//		}
	}

		

	@Test (enabled = true, priority = 1)
	public void usecase1() throws Exception
	{
		login();
	}

	@Test(enabled = true, priority = 2)
	public void createInvoice() throws Exception {
		//	Actions action1 = new Actions(driver);

		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
		String username = "turbo";
		String password = "turbo@2016";
		String userid=null;

		driver.get(baseUrl);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
		driver.findElement(By.linkText("Login")).click();
		//Authentication
		driver.findElement(By.id("uname")).clear();
		driver.findElement(By.id("uname")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"username")).getStringCellValue());
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"password")).getStringCellValue());
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inventory")));
		customerInvoices();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")));
		driver.findElement(By.xpath("//body/div[1]/table/tbody/tr[1]/td[2]/div/input[1]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'New Invoice')]")));
		driver.findElement(By.xpath("//span[contains(text(), 'New Invoice')]")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_customerInvoiceID")));
		driver.findElement(By.id("customerInvoice_customerInvoiceID")).clear();
		driver.findElement(By.id("customerInvoice_customerInvoiceID")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"customername")).getStringCellValue());

		driver.findElement(By.xpath("//body/ul[24]/li/a")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("customerInvoice_TaxTerritory")));
		driver.findElement(By.id("customerInvoice_TaxTerritory")).clear();
		driver.findElement(By.id("customerInvoice_TaxTerritory")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"taxterritory")).getStringCellValue());
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[22]/li/a")));
		driver.findElement(By.xpath("//body/ul[22]/li/a")).click();

		driver.findElement(By.id("customerInvoice_frightIDcu")).clear();
		driver.findElement(By.id("customerInvoice_frightIDcu")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"Freight")).getStringCellValue());

		driver.findElement(By.id("CuInvoiceSaveID")).click();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("cICheckTab2")));
		driver.findElement(By.id("cICheckTab2")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("new_row_itemCode")));
		driver.findElement(By.id("new_row_itemCode")).click();
		driver.findElement(By.id("new_row_itemCode")).clear();
		driver.findElement(By.id("new_row_itemCode")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"Product")).getStringCellValue());

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul[26]/li/a")));
		driver.findElement(By.xpath("//body/ul[26]/li/a")).click();

		driver.findElement(By.id("new_row_quantityBilled")).click();
		driver.findElement(By.id("new_row_quantityBilled")).clear();
		driver.findElement(By.id("new_row_quantityBilled")).sendKeys(sourceSheet.getRow(1).getCell(ColumnNumber(1,"Qty")).getStringCellValue());

		cuInvoiceNumber = driver.findElement(By.id("customerInvoice_lineinvoiceNumberId")).getText();

		driver.findElement(By.id("CuInvoiceSaveID")).click();

		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("CuInvoiceSaveCloseID")));
		driver.findElement(By.id("CuInvoiceSaveCloseID")).click();


		//====


		String baseQuery = "select * from cuInvoice where InvoiceNumber = '" + cuInvoiceNumber + "'";
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
		}

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



