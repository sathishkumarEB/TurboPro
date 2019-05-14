package InventoryAutomation;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable; 

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
 
public class ExcelSheetDriver {
 
 // create sheet name	
 static Sheet wrksheet;
 // create workbook name
 static Workbook wrkbook =null;
static WritableWorkbook workbook = null;
 static Hashtable dict= new Hashtable();
 //Create a Constructor
 public ExcelSheetDriver(String ExcelSheetPath) throws BiffException, IOException
 {
  //Initialize
  wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
  workbook = Workbook.createWorkbook(new File(ExcelSheetPath));
  
  //For Demo purpose the excel sheet path is hardcoded, but not recommended :)
  WritableSheet  wrksheet = (WritableSheet) workbook.getSheet("Sheet1");
 }
 
 
 
 //Returns the Number of Rows
 public static int RowCount()
 {
  return ((Sheet) wrkbook).getRows();
 }
 
 public static int ColumnCount()
 {
  return ((Sheet) wrkbook).getColumns();
 }
 
 //Returns the Cell value by taking row and Column values as argument
 public static String ReadCell(int column,int row)
 {
  return ((Sheet) wrkbook).getCell(column,row).getContents();
 }
 
 
 //Create Column Dictionary to hold all the Column Names
 public static void ColumnDictionary()
 {
  //Iterate through all the columns in the Excel sheet and store the value in Hashtable
  for(int col=0;col < ((Sheet) wrkbook).getColumns();col++)
  {
   dict.put(ReadCell(col,0), col);
  }
 }
 
 //Read Column Names
 public static int GetCell(String colName)
 {
  try {
   int value;
   value = ((Integer) dict.get(colName)).intValue();
   return value;
  } catch (NullPointerException e) {
   return (0);
 
  }
 }
 
}

