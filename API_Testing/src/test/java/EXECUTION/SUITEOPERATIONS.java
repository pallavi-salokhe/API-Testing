package EXECUTION;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.resolver.readers.CatalogReader;

import CONFIG.CONFIGURATION;
import EXCELOPERATIONS.READDATA;
import FileOperations.ReadFileData;
import JSONMODULES_RestAssured.DeleteData;
import JSONMODULES_RestAssured.GetData;
import JSONMODULES_RestAssured.PostData;

public class SUITEOPERATIONS extends CONFIGURATION{
@Test
	public void StartSuite() throws IOException, ParserConfigurationException, SAXException, ParseException, InterruptedException
	{
		//get file location from config file
		//geet csv handle 
		//find count of the rows in it
		// run the loop for the number of rows
		//within the loop
		//----get first row data in hashtable
		//then check the operation & accordingly call the rest assured function
		//then validate the data as well
		Init();
		BufferedReader br =ReadFileData.getFileHandle(CONFIGURATION.GET_DATA);
	//	int rowcount = READDATA.GetTotalRowCount(fs,0);
		//int colcount =READDATA.GetTotalColumnCount(fs,0);
		String[] headers = ReadFileData.getHeaders(br);
		String line = "";
		// CatalogReader reader2 = null;
		// long countOfLines = Files.lines(Paths.get(new File(CONFIGURATION.GET_DATA).getPath())).count();
		 
	//		System.out.println(countOfLines);
			
			//br.readLine();
		//	br.	;
			String line1=null;
			 while ((line1 = br.readLine()) != null){ //loop will run from 2nd line
			        //some code
				
				// String  fulline = br.readLine();
				 System.out.println("fulline " +line1);
				 			
				 String[] dataarray = line1.split("\\|"); 
				 System.out.println("dataarray " +Arrays.toString(dataarray));
				 
				 HashMap<String,String> CSVData=new HashMap<String,String>();
				 System.out.println("headers.length  "+headers.length);
				 for(int a=0;a<headers.length;a++){
	//				 System.out.println("headers[h] "+headers[h]);
			//		 System.out.println("dataarray[h] "+dataarray[h]);
					 CSVData.put(headers[a], dataarray[a]) ;
					 
				 }
				 System.out.println("CSVData  "+CSVData);
				// System.exit(0);
				 String op = CSVData.get("OPERATION");
				 
				 if(op.equalsIgnoreCase("GET"))
					 {
						 GetData.GetResponse(CSVData);
					 }
				 else if(op.equalsIgnoreCase("POST"))
				 {
					 PostData.PostResponse(CSVData);
				 } 
				 else if(op.equalsIgnoreCase("DELETE"))
				 {
					 DeleteData.DeleteResponse(CSVData);
				 }
				 
				 System.out.println("===============================================");
				 				 
				 
				 
				 
				 
					}
				 
				 
				 }

			
	
	
}
