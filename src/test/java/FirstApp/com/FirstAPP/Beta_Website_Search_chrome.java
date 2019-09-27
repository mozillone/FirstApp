package FirstApp.com.FirstAPP;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.response.Response;

public class Beta_Website_Search_chrome {
	
	public Beta_Website_Search_chrome(){
		
	}
	
	@BeforeClass
	public void setBaseUri(){
		
		RestAssured.baseURI = "http://beta.discover-prospects.com:8086/v4"; 
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void Verify_Beta_Website_Search_chrome(){
		
		
		RequestSpecification request = RestAssured.given().header("Authorization",
				"Bearer 5e0a50d50229b7f0a49c2c6f814e01a9258ac927");
		
		request.header("Content-Type", "application/json");
		
		JSONObject requestparams = new JSONObject();
		
		requestparams.put("hostname", "www.papajohns.com");
		
		requestparams.put("country_code", "us");
		
		requestparams.put("locality", "London");
		
		requestparams.put("region", "KY");
		
		Response response = request.post("/prospects/website_search_chrome");
		
		long responsetime = response.getTime();
		
		System.out.println("Response time is: "+ responsetime); 
		
		String responseBody = response.getBody().asString(); 
		
		System.out.println("Response Body is =>"+ responseBody); 
		
		
		
	}

}
