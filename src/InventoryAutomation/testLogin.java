package InventoryAutomation;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class testLogin
{
	
	@Test
	public void excelIO() throws Exception 
	{
		String document = "C:\\Users\\sathish_kumar\\Documents\\InvoiceInputs.xls";	
		InputStream inp = new FileInputStream(document);	
		XSSFWorkbook workbook = new XSSFWorkbook(inp);
			XSSFSheet sheet = workbook.getSheetAt(0);	 
		Row row = sheet.getRow(0);	
		int m= row.getLastCellNum();
		int n = sheet.getLastRowNum();		
	 
		System.out.println("Row = " +(n+1));
		System.out.println("Column = " +m);
		workbook.close();
		
	}
	
	 
	public class ExcelApiTest
	{
	    public FileInputStream fis = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	 
	    public ExcelApiTest(String xlFilePath) throws Exception
	    {
	        fis = new FileInputStream(xlFilePath);
	        workbook = new XSSFWorkbook(fis);
	        fis.close();
	    }
	 
	    /*public String getCellData(String sheetName, String colName, int rowNum)
	    {
	        try
	        {
	            int col_Num = -1;
	            sheet = workbook.getSheet(sheetName);
	            row = sheet.getRow(0);
	            for(int i = 0; i < row.getLastCellNum(); i++)
	            {
	                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
	                    col_Num = i;
	            }
	 
	            row = sheet.getRow(rowNum - 1);
	            cell = row.getCell(col_Num);
	 
	            if(cell.getCellType() == CellType.STRING)
	                return cell.getStringCellValue();
	            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
	            {
	                String cellValue = String.valueOf(cell.getNumericCellValue());
	                if(HSSFDateUtil.isCellDateFormatted(cell))
	                {
	                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
	                    Date date = cell.getDateCellValue();
	                    cellValue = df.format(date);
	                }
	                return cellValue;
	            }else if(cell.getCellType() == CellType.BLANK)
	                return "";
	            else
	                return String.valueOf(cell.getBooleanCellValue());
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
	        }*/
	    }
	}
	
	



