package InventoryAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TPAutomationSample {
	
	HSSFWorkbook wb;
	public int ColumnNumber(HSSFWorkbook wb,int SheetNum,int RowNum, String ColumnHeader) throws Exception
	{
		int columnNumber=-1;
		for (int cn=0; cn<wb.getSheetAt(SheetNum).getRow(RowNum).getLastCellNum(); cn++) {
			Cell c = wb.getSheetAt(SheetNum).getRow(RowNum).getCell(cn);
			if (c.getStringCellValue() == null) {
				// Can't be this cell - it's empty
				continue;
			}
			else {
				String text = c.getStringCellValue();
				if (ColumnHeader.equalsIgnoreCase(text)) {
					columnNumber = cn;
					break;
				}
			}
		}

		//		catch(Exception e){
		if (columnNumber == -1) {
			throw new Exception("None of the cells in the first row were Patch");
		} 
		else
		
		return columnNumber;
		
	}
	
@BeforeTest
public void setUp() throws FileNotFoundException, IOException
{
	 wb=new HSSFWorkbook(new FileInputStream(new File("C:/Users/sathish_kumar/Documents/InvoiceInputs1.xls")));
}
	
@Test
public void testTpSample() throws Exception
{
	String baseUrl;
//	HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(new File("/Users/bhuvang/Downloads/InvoiceInputs1.xls")));
	baseUrl=wb.getSheetAt(0).getRow(1).getCell(ColumnNumber(wb,0,0,"baseURL")).toString();
	System.out.println(baseUrl);
}
	

}
