package Login;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class testphantom {
 
 HtmlUnitDriver driver;
 String pagetitle;

 @BeforeTest
 public void setup() throws Exception {
  //Initializing HtmlUnitDriver to run test with Chrome browser.
  HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
  //Enabling JavaScript of HtmlUnitDriver.
  driver.setJavascriptEnabled(true);  
  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  
  //To hide warnings logs from execution console.
  Logger logger = Logger.getLogger("");
  logger.setLevel(Level.OFF);
  
  //Opening URL In HtmlUnitDriver.
  driver.get("https://www.google.co.in/");
 }


 @Test
 public void googleSearch() {  
  //Get current page title using javascript executor.
  JavascriptExecutor javascript = (JavascriptExecutor) driver;
  String pagetitle=(String)javascript.executeScript("return document.title");  
  System.out.println("My Page Title Is  : "+pagetitle);
  
  driver.findElement(By.id("gs_htif0")).click();
  driver.findElement(By.id("gs_htif0")).sendKeys("green"+ Keys.ENTER);
  
  // To locate table.
  /*WebElement mytable = driver.findElement(By.xpath(".//*[@id='post-body-8228718889842861683']/div[1]/table/tbody"));
  // To locate rows of table.
  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
  // To calculate no of rows In table.
  int rows_count = rows_table.size();

  // Loop will execute till the last row of table.
  for (int row = 0; row < rows_count; row++) {
   // To locate columns(cells) of that specific row.
   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
   // To calculate no of columns(cells) In that specific row.
   int columns_count = Columns_row.size();
   System.out.println("Number of cells In Row " + row + " are "+ columns_count);

   // Loop will execute till the last cell of that specific row.
   for (int column = 0; column < columns_count; column++) {
    // To retrieve text from that specific cell.
    String celtext = Columns_row.get(column).getText();
    System.out.println("Cell Value Of row number " + row+ " and column number " + column + " Is " + celtext);
   }
  System.out.println("--------------------------------------------------");
  }*/
 }
 
 @AfterTest
 public void tearDown() throws Exception {
  //Closing HtmlUnitDriver.
  driver.quit();
 }

 
}