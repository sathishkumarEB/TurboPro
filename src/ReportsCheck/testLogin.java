package ReportsCheck;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.ss.usermodel.Row;

import com.mysql.jdbc.ResultSetMetaData;
import com.opencsv.CSVReader;

import java.sql.ResultSet;
import java.sql.DriverManager;

public class testLogin {
	
	@Parameters({ "username", "password", "table" })
	@Test
	public void testLogin(String LoginUsername, String Loginpassword, String LoginTable) throws Exception {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://sysvines007.sysvine.local:3306/BartosProdQA";
		String username = "turbo";
		String password = "turbo@2016";

				String inputMore;
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
				con.close();
				
				// closing DB Connection
				
				
			
		

		}
	}

