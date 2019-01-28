package CONFIG;

import java.io.InputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javafx.scene.Scene;

import javax.xml.parsers.ParserConfigurationException;



//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;


//import GENRICFUNCTIONS.XMLOperations;


public class CONFIGURATION {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	
	public static String GET_URL = null;
	public static String POST_FILEPATH = null;
	public static String New_POST_FILEPATH = null;
	public static String DELETE_URL = null;
	public static String API_TYPE= null;
	public static String API_FUNCTION= null;
	public static String POST_URL = null;
	public static String GET_DATA = null;
//	public static Scene scene = null;
//	@When("^setting values to the variable from the Settings.properties$")	
	public  void Init() throws IOException, ParserConfigurationException, SAXException
	{
		System.out.println("Init values have started printing");
		
		
		GET_URL = this.getParameterVal_child("GET_URL");
		POST_URL = this.getParameterVal_child("POST_URL");
		POST_FILEPATH = this.getParameterVal_child("POST_FILEPATH");
		New_POST_FILEPATH = this.getParameterVal_child("New_POST_FILEPATH");
		DELETE_URL = this.getParameterVal_child("DELETE_URL");
		API_TYPE = this.getParameterVal_child("API_TYPE");
		API_FUNCTION = this.getParameterVal_child("API_FUNCTION");
		GET_DATA = this.getParameterVal_child("GET_DATA");
	//	scene = new Scene(null);
		System.out.println("Init values have ended printing");
	}
	public String getParameterVal_child(String paraname) throws IOException
	{
	//	System.out.println("getParameterVal_child values have started printing");
		Properties prop = new Properties();
		InputStream input = null;
		String paraVal = null;
		
				try {
					input = getClass().getClassLoader().getResourceAsStream("settings.properties");
					prop.load(input);
					paraVal = prop.getProperty(paraname);
				//	System.out.println(paraVal);
					input.close();} 
				catch (IOException e) {
					e.printStackTrace();
				}
			//	System.out.println("getParameterVal_child values have ended printing");
				return paraVal;
		
	}
	
	public void getAllParameterVal_child() throws IOException
	{
		
		Properties prop = new Properties();
		InputStream input = null;
				
				try {
					input = getClass().getClassLoader().getResourceAsStream("settings.properties");
					prop.load(input);
					
					Enumeration<?> e = prop.propertyNames();
					while (e.hasMoreElements()) {
						String key = (String) e.nextElement();
						String value = prop.getProperty(key);
						System.out.println("Key : " + key + ", Value : " + value);							
					}
					
					input.close();
					} 
								
				catch (IOException e) {
					e.printStackTrace();
				}			
		
	}

	
}
