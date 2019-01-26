package EXECUTION;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import CONFIG.CONFIGURATION;
import JSONMODULES_RestAssured.PostData;

public class POSTAPIDATA extends CONFIGURATION{
	@BeforeClass
	public void before() throws IOException, ParserConfigurationException, SAXException
	{
	//	String strDate =null;
		//Scenario scene = null;
		Init();						
		}	
	
	@Test
	public void PostAPIData() throws Exception
	{	
		File file = new File(POST_URL);
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 String st; 
		  while ((st = br.readLine()) != null) 
		  {
		    System.out.println(st); 
		    String[] split= st.split(";");
		    System.out.println(split[0]);
		    System.out.println(split[1]);
		    System.out.println(split[2]);
		  //  PostData.PostResponse(split);
		  }
	}
}
