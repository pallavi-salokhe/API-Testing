package JSONMODULES_RestAssured;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

import CONFIG.CONFIGURATION;


public class DeleteData  extends CONFIGURATION {

	@Test
	public static void DeleteResponse(HashMap<String,String> CSVData) throws InterruptedException
	{
		String URL = CSVData.get("URL").toString();
		String RESPONSECONTENTTYPE = CSVData.get("RESPONSECONTENTTYPE").toString();
		int RESPONSESTATUSCODE = Integer.parseInt(CSVData.get("RESPONSESTATUSCODE"));
		int RESPONSETIME = Integer.parseInt(CSVData.get("RESPONSETIME"));
		String DATA  = CSVData.get("DATA").toString();
		String PARAMETERYPE  = CSVData.get("PARAMETERYPE").toString();
		String EXPECTEDRESPONSE =CSVData.get("EXPECTEDRESPONSE").toString();
		
		RequestSpecification req = RestAssured.given();
		Response resp = null;
		String newURL = null;
		if (!PARAMETERYPE.equalsIgnoreCase("Query")){  
			resp =req.delete(URL);}
		else{
			/*boolean isPresent = DATA.indexOf("~") != -1 ? true : false;
			if (isPresent) {
				String[] dta = DATA.split("~");	
				String mainurl = null;
				for (int d=0;d<dta.length;d++)
				{
					if(mainurl==null){mainurl = mainurl;}
					else{mainurl = mainurl+"/"+dta[d];}
				}*/
			 System.out.println("URL creation = " +URL+"/"+DATA);
			
				 newURL = URL+"/"+DATA;
				resp =req.delete(newURL);
				//resp = RestAssured.get(URL+"?"+mainurl);
				//resp.prettyPrint();
		//	}
			//else{ resp =req.delete(URL);resp.prettyPrint();}
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
	
		if (DATA!=null){
			int pos = newURL.lastIndexOf("/");
			System.out.println(pos);
			
			URL = newURL.substring(0,pos);
			System.out.println(URL);
		}
		Response getresp = RestAssured.get(URL);
		getresp.prettyPrint();
			
		
		boolean isPresent = EXPECTEDRESPONSE.indexOf("~") != -1 ? true : false;
		 
				
				
				
		if (isPresent) {
			String[] exp = EXPECTEDRESPONSE.split("~");
			for (int e=0 ;e<exp.length;e++)
			{				
				 JsonPath jsonPathEvaluator = getresp.jsonPath();
				 				 
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
				JsonPath jsonPathEvaluator = getresp.jsonPath();
				System.out.println("I am in len 1 loop" + EXPECTEDRESPONSE)	;
				System.out.println("I am split 0 " + (EXPECTEDRESPONSE.split("=")[0]).trim())	;
				
				String str = jsonPathEvaluator.getString((EXPECTEDRESPONSE.split("=")[0]).trim());
				boolean actval = jsonPathEvaluator.getString((EXPECTEDRESPONSE.split("=")[0]).trim()).contains((EXPECTEDRESPONSE.split("=")[1]).trim());
				boolean expval = Boolean.parseBoolean((EXPECTEDRESPONSE.split("=")[2]));
				Assert.assertEquals(actval,expval );
				
			}

		
	}
	
}
