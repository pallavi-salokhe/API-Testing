package JSONMODULES_RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;







import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import CONFIG.CONFIGURATION;

public class GetData extends CONFIGURATION{
				
	
	//@Test
	public static void GetResponse(HashMap<String,String> CSVData) throws MalformedURLException, InterruptedException, ParseException 
	{
		
		String URL = CSVData.get("URL").toString();
		String RESPONSECONTENTTYPE = CSVData.get("RESPONSECONTENTTYPE").toString();
		int RESPONSESTATUSCODE = Integer.parseInt(CSVData.get("RESPONSESTATUSCODE"));
		int RESPONSETIME = Integer.parseInt(CSVData.get("RESPONSETIME"));
		String DATA  = CSVData.get("DATA").toString();
		String PARAMETERYPE  = CSVData.get("PARAMETERYPE").toString();
		String EXPECTEDRESPONSE =CSVData.get("EXPECTEDRESPONSE").toString();
		Response resp = null;
		if (PARAMETERYPE.equalsIgnoreCase("Query")){ 
			boolean isPresent = DATA.indexOf("~") != -1 ? true : false;
			if (isPresent) {
				String[] dta = DATA.split("~");	
				String mainurl = null;
				for (int d=0;d<dta.length;d++)
				{
					if(mainurl==null){mainurl = mainurl;}
					else{mainurl = mainurl+"&"+dta[d];}
				}
				resp = RestAssured.get(URL+"?"+mainurl);
				resp.prettyPrint();
			}
			else{resp = RestAssured.get(URL);resp.prettyPrint();}
			
		}
		else{
			resp = RestAssured.get(URL);
			
		}
		  
		int code = resp.getStatusCode();
			
		 System.out.println("Code = " +code);
		 	 
		Assert.assertEquals(code,RESPONSESTATUSCODE);	
				
		String contenttype = resp.contentType();
		System.out.println("content type = " +contenttype);
		String body = resp.body().prettyPrint();
				 
		Assert.assertEquals(contenttype.split(";")[0],RESPONSECONTENTTYPE);
		int tme = (int) resp.timeIn(TimeUnit.SECONDS);
		System.out.println("tme = " +tme);
		Assert.assertTrue(resp.timeIn(TimeUnit.SECONDS)<RESPONSETIME, "Time is within the limit");
	
		boolean isPresent = EXPECTEDRESPONSE.indexOf("~") != -1 ? true : false;
		if (isPresent) {
			String[] exp = EXPECTEDRESPONSE.split("~");
			for (int e=0 ;e<exp.length;e++)
			{				
				 JsonPath jsonPathEvaluator = resp.jsonPath();
				 				 
				 System.out.println("List " +jsonPathEvaluator.getList((exp[e].split("=")[0]).trim()));
				 System.out.println("Expected val  "+(exp[e].split("=")[1]).trim());
				 List<String> lst = jsonPathEvaluator.getList((exp[e].split("=")[0]));
				 int lstsize = lst.size();
				
				 
				 Thread.sleep(2000);
				 boolean actval = false;
				 for (int j=0; j<lstsize;j++)
				 {
					if(((exp[e].split("=")[1]).trim()).equalsIgnoreCase(lst.get(j)))	
					{
						actval = true;
						break;
					}
				 }
			//	boolean actval = jsonPathEvaluator.getList((exp[e].split("=")[0]).trim()).contains((exp[e].split("=")[1]).trim());		
				//System.out.println("actval " +actval);
				//System.out.println("boolean val  "+(exp[e].split("=")[2]).trim());
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

				 List<String> lst = jsonPathEvaluator.getList((EXPECTEDRESPONSE.split("=")[0]));
				 int lstsize = lst.size();
				
				 
				 Thread.sleep(2000);
				 boolean actval = false;
				 for (int j=0; j<lstsize;j++)
				 {
					if(((EXPECTEDRESPONSE.split("=")[1]).trim()).equalsIgnoreCase(lst.get(j)))	
					{
						actval = true;
						break;
					}
				 }
					
		//		JSONArray jsonarr_1 = (JSONArray) jobj.get((EXPECTEDRESPONSE.split("=")[0]).trim());
				
			//	System.out.println("json array " + jsonarr_1)	;
			//	boolean actval = jsonPathEvaluator.getString((EXPECTEDRESPONSE.split("=")[0]).trim()).contains((EXPECTEDRESPONSE.split("=")[1]).trim());
				boolean expval = Boolean.parseBoolean((EXPECTEDRESPONSE.split("=")[2]));
				Assert.assertEquals(actval,expval );
				
			}
		}
		
	
			
	}

