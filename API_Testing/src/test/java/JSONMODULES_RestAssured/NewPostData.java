package JSONMODULES_RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import CONFIG.CONFIGURATION;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class NewPostData extends CONFIGURATION{
	
	@BeforeClass
	public void before() throws IOException, ParserConfigurationException, SAXException
	{
	//	String strDate =null;
		//Scenario scene = null;
		Init();					
								
		}
	
	public String generateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));

	}
	@Test
	public void post() throws IOException {

	   String jsonBody = generateStringFromResource(New_POST_FILEPATH);
	   RequestSpecification req = RestAssured.given();
	   
	   req.contentType("application/json");
	   req.body(jsonBody);
	   Response resp = req.post("http://localhost:3000/users");
	   int code = resp.getStatusCode() ;
	   
			   Assert.assertEquals(code,201);
	           
	}
	
}
