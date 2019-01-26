package JSONMODULES_RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutData {

	@Test
	public void PostResponse()
	{
		RequestSpecification req = RestAssured.given();
		req.header("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("id", "5");
		json.put("firstname", "Janet");
		json.put("lasttname", "Smith");
		json.put("email", "Janet.Smith@gmail.com");
		json.put("age", "46");
		json.put("companyId", "2");
		
		req.body(json.toJSONString());
		
		Response resp =req.put("http://localhost:3000/users/5");
		
		int code = resp.getStatusCode();
		 System.out.println("Code = " +code);
		Assert.assertEquals(code,200);
		
	}
	
}
