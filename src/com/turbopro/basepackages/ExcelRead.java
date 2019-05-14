package com.turbopro.basepackages;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExcelRead
{
	
	@Test
	public void excelIO() throws Exception 
	{
		String document = "C:\\Users\\sathish_kumar\\Desktop\\DataDriven\\excelread.xlsx";	
		InputStream inp = new FileInputStream(document);	
		XSSFWorkbook workbook = new XSSFWorkbook(inp);
			XSSFSheet sheet = workbook.getSheetAt(0);	 
		Row row = sheet.getRow(0);	
		int m= row.getLastCellNum();
		int n = sheet.getLastRowNum();		
	 
		System.out.println("Row = " +n);
		System.out.println("Column = " +m);
	
		for(int i=0; i<=n; i++)
		{	
			for(int j=0; j<m; j++)
			{
	
			String header1 = (sheet.getRow(i).getCell(j).getStringCellValue());			
			System.out.println(header1);

			
		}
	}
		workbook.close();
		
	}
	}

