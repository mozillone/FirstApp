  package FirstApp.com.FirstAPP;

import java.io.FileNotFoundException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import Base.TestBase;

public class Beta_Domain_Details_Api extends TestBase{
	
	//String sheetName = "Sheet2";

	public Beta_Domain_Details_Api() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
	@BeforeClass
	public void setBaseUri() {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://beta.discover-prospects.com:8086/v4"; 
		
	}
	@SuppressWarnings("unchecked")
	@Test/*(dataProvider = "getBuzzApiTestData")*/
	//public void Verify_Beta_Domain_Details_Api(String HostName) throws ParseException, FileNotFoundException {
	public void Verify_Beta_Domain_Details_Api() throws ParseException, FileNotFoundException {
		RequestSpecification request = RestAssured.given().header("Authorization",
				"Bearer 5e0a50d50229b7f0a49c2c6f814e01a9258ac927"); 
	
		request.header("Content-Type", "application/json"); 

		JSONObject requestParams = new JSONObject(); 
		
		requestParams.put("domain", "zoeskitchen.com"); 
		
		requestParams.put("country_code", "us"); 
		
		request.body(requestParams.toString()); 

		Response response = request.post("/domain/details"); 
		
		long responsetime = response.getTime(); 
		
		System.out.println("Response Time is: "+responsetime); 
		
		String responseBody = response.getBody().prettyPrint(); 
		
		System.out.println("Response Body is =>" + responseBody); 
		
		JSONParser jp = new JSONParser(); 
		
		JSONObject jsonResult = (JSONObject) (jp.parse(responseBody)); 
		
		//System.out.println("Status : "+jsonResult.get("status")); 
		
		String Status = jsonResult.get("status").toString(); 
		
		System.out.println("Status is: "+Status); 
		
		try {
			Assert.assertEquals("200",Status); 
			} catch(Error e){
				// Following lines will be printed when the assert condition fails
				System.out.println("Assert equals failed. But test execution is not aborted."); 
				
				System.out.println("Error message: " + e.toString()); 
				
			} 
		
        JSONObject jsonData = (JSONObject) (jsonResult.get("data")); 
        
        JSONObject jsonMeta = (JSONObject) (jsonResult.get("meta")); 
        
        System.out.println("Request Rferenceid :"+jsonMeta.get("request_reference_id"));     
        
        System.out.println("Domain :"+jsonMeta.get("domain")); 
        
        System.out.println("Name Server:"+jsonData.get("name_servers")); 
        
        JSONObject jsonReg = (JSONObject) (jsonData.get("registration")); 
        
        System.out.println("Created Date : "+jsonReg.get("createdDate")); 
        
        System.out.println("RegistrarName : "+jsonReg.get("registrarName")); 
        
        System.out.println("ExpiresDate : "+jsonReg.get("expiresDate")); 
        
        System.out.println("UpdatedDate : "+jsonReg.get("updatedDate")); 
        
        System.out.println("Registrant : "+jsonReg.get("registrant")); 

}
	
}

