package JSONMODULES_RestAssured;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import CONFIG.CONFIGURATION;

public class PostData extends CONFIGURATION{
	public static JSONArray UserList = null;
	public static JSONObject jasonobj = null;
	public static JSONObject json = null;
	

	public static void PostResponse(HashMap<String,String> CSVData) throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		String URL = CSVData.get("URL").toString();
		String RESPONSECONTENTTYPE = CSVData.get("RESPONSECONTENTTYPE").toString();
		int RESPONSESTATUSCODE = Integer.parseInt(CSVData.get("RESPONSESTATUSCODE"));
		int RESPONSETIME = Integer.parseInt(CSVData.get("RESPONSETIME"));
		String DATA  = CSVData.get("DATA").toString();
		String EXPECTEDRESPONSE =CSVData.get("EXPECTEDRESPONSE").toString();
		
		
		//int counter = getDataFromJsonFile(DATA);
		
		RequestSpecification req = RestAssured.given();
		req.header("Content-Type", RESPONSECONTENTTYPE);
	      
		//for (int i=0;i<counter;i++)
		//{
			
			//json = (JSONObject)UserList.get(i);
			//GetKeysOfJson(i);	        
		
			//req.body(json.toJSONString());
		System.out.println("DATA = " +DATA);
			req.body(DATA);
			Response resp =req.post(URL);
			
			int code = resp.getStatusCode();
			
			System.out.println("Code = " +code);
			 	 
			Assert.assertEquals(code,RESPONSESTATUSCODE);	
					
			String contenttype = resp.contentType();
			System.out.println("content type = " +contenttype);
			
			Assert.assertEquals(contenttype.split(";")[0],RESPONSECONTENTTYPE);
			int tme = (int) resp.timeIn(TimeUnit.SECONDS);
			System.out.println("tme = " +tme);
			Assert.assertTrue(resp.timeIn(TimeUnit.SECONDS)<RESPONSETIME, "Time is within the limit");
			boolean isPresent = EXPECTEDRESPONSE.indexOf("~") != -1 ? true : false;
			if (isPresent) {
				String[] exp = EXPECTEDRESPONSE.split("~");
			//}else {String exp = EXPECTEDRESPONSE; }
			
				System.out.println("len " +exp.length);
				resp.prettyPrint();
			
			//if(exp.length<1)
			//{			
				for (int e=0 ;e<exp.length;e++)
				{
					JsonPath jsonPathEvaluator = resp.jsonPath();
					
					System.out.println("List " +jsonPathEvaluator.getList((exp[e].split("=")[0]).trim()));
					System.out.println("Expected val  "+(exp[e].split("=")[1]).trim());
					 
					 Thread.sleep(2000);
					boolean actval = jsonPathEvaluator.getList((exp[e].split("=")[0]).trim()).contains((exp[e].split("=")[1]).trim());		
					System.out.println("actval " +actval);
					System.out.println("boolean val  "+(exp[e].split("=")[2]).trim());
					boolean expval = Boolean.parseBoolean((exp[e].split("=")[2]));
					Assert.assertEquals(actval,expval );
				}
			}
			else
			{
				System.out.println("I am in len 1 loop")	;
				JsonPath jsonPathEvaluator = resp.jsonPath();
				System.out.println("I am in len 1 loop" + EXPECTEDRESPONSE)	;
				String str = jsonPathEvaluator.getString((EXPECTEDRESPONSE.split("=")[0]).trim());
				boolean actval = jsonPathEvaluator.getString((EXPECTEDRESPONSE.split("=")[0]).trim()).contains((EXPECTEDRESPONSE.split("=")[1]).trim());
				boolean expval = Boolean.parseBoolean((EXPECTEDRESPONSE.split("=")[2]));
				Assert.assertEquals(actval,expval );
				
			}
		
	}
	
	public static int getDataFromJsonFile(String split) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();			
		Object obj = jsonParser.parse(new FileReader(split));
	//	JSONArray jsonObject = (JSONArray) obj;
		 jasonobj =  (JSONObject) obj;
		UserList = (JSONArray) jasonobj.get("users");
		
		//CompList =  (JSONArray) obj;
       // System.out.println("Company List = " +CompList);
        int recordCounter = UserList.size();
        System.out.println(recordCounter);
        return recordCounter;

	}
	
	public static void GetKeysOfJson(int jsoncnt)
	{
		Iterator<Object> iterator = UserList.iterator();
        //while(iterator.hasNext()){
            JSONObject jsonObject = (JSONObject) iterator.next();
            System.out.println(jsonObject.keySet());
            
           // Object[] keysetarr = new Object[];
            Object[] keysetarr =  jsonObject.keySet().toArray();
            
            for(int j = 0;j<keysetarr.length;j++){
                System.out.println(keysetarr[j]);
    	        jasonobj = (JSONObject)UserList.get(jsoncnt);
    	        System.out.println(keysetarr[j]);
    	        System.out.println(jasonobj.get(keysetarr[j]));
                json.put(keysetarr[j],  jasonobj.get(keysetarr[j]) );               
                
           }
        //}
	}
	
	
}
