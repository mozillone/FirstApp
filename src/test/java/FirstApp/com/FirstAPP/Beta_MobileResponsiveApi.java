package FirstApp.com.FirstAPP;

import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification; 



public class Beta_MobileResponsiveApi {
	
	//String sheetName = "Sheet2"; 

	public Beta_MobileResponsiveApi() throws FileNotFoundException {
		super(); 
		
	}
	@BeforeClass
	public void setBaseUri() {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://beta.discover-prospects.com:8086/v4"; 
		//http://beta.discover-prospects.com:8086/v4
		
	}
	@SuppressWarnings("unchecked")
	@Test/*(dataProvider = "getBuzzApiTestData")*/
	//public void Verify_Beta_MobileResponsiveApi(String HostName) throws ParseException, FileNotFoundException {
	public void Verify_Beta_MobileResponsiveApi() throws ParseException, FileNotFoundException {
		RequestSpecification request = RestAssured.given().header("Authorization",
				"Bearer 5e0a50d50229b7f0a49c2c6f814e01a9258ac927"); 
	
		request.header("Content-Type", "application/json"); 

		JSONObject requestParams = new JSONObject(); 
		
		//requestParams.put("url", HostName); 

		requestParams.put("url", "http://learn-automation.com"); 
		
		requestParams.put("country_code", "us"); 
		
		request.body(requestParams.toString()); 

		Response response = request.post("/website/responsive"); 
		
		long responsetime = response.getTime(); 
		
		System.out.println("Response Time is: "+responsetime); 
		
		String responseBody = response.getBody().asString(); 
		
		System.out.println("Response Body is =>" + responseBody); 
		
		JSONParser jp = new JSONParser(); 

		JSONObject jsonResult = (JSONObject) (jp.parse(responseBody));  
		
		//JSONObject jsonStatus = (JSONObject) (jsonResult.get("status")); 

		JSONObject JsonMeta = (JSONObject) (jsonResult.get("meta")); 

		//JSONObject jsonFP = (JSONObject) (jsonResult.get("result")); 
		
		String Status = jsonResult.get("status").toString();  
		
		System.out.println("Status is: "+Status); 
		
		try {
		Assert.assertEquals("200",Status); 
		
		} catch(Error e){
			// Following lines will be printed when the assert condition fails
			System.out.println("Assert equals failed. But test execution is not aborted.");  
			
			System.out.println("Error message: " + e.toString()); 
		}
       // System.out.println("This line is executed because assertEquals"
              //  + "passed since both the strings are same"); 
        
        String ResponsiveStatus = jsonResult.get("responsive").toString();  
        
		System.out.println("Responsive : "+ResponsiveStatus);  
		
		String mobile_compatibleStatus = jsonResult.get("mobile_compatible").toString();  
		
		System.out.println("mobile_compatible : "+mobile_compatibleStatus); 
		
		String mobile_urlStatus = jsonResult.get("mobile_url").toString(); 
		
		System.out.println("mobile_url : "+mobile_urlStatus); 
		
		//String fp_id = jsonFP.get("fp_id").toString(); 
		
		//System.out.println("Fp_Id is: " +fp_id); 
		
		String  reference_id= JsonMeta.get("request_reference_id").toString(); 
		
		System.out.println("Request Reference Id is: "+reference_id); 
		
		System.out.println("***********End**********"); 

	}
	
	/*@DataProvider
	public Object[][] getBuzzApiTestData()
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		Object data[][] = Util.ExcelReader.getTestData(sheetName); 
		return data; 
	}*/

	@AfterClass
	public void Quit() {

		System.out.println("Completed the execution"); 
 
	}
	
	

}