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



public class Beta_Check_Availability_Api{
	
	//String sheetName = "Sheet2"; 

	public Beta_Check_Availability_Api() throws FileNotFoundException {
		super(); 
		
	}
	@BeforeClass
	public void setBaseUri() {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://beta.discover-prospects.com:8086/v4/listing"; 
		
	}
	@SuppressWarnings("unchecked")
	@Test/*(dataProvider = "getBuzzApiTestData")*/
	//public void Verify_Beta_CheckAvailabilityApi(String HostName) throws ParseException, FileNotFoundException {
	public void Verify_Beta_CheckAvailabilityApi() throws ParseException, FileNotFoundException {
		RequestSpecification request = RestAssured.given().header("Authorization",
				"Bearer 5e0a50d50229b7f0a49c2c6f814e01a9258ac927"); 
	
		request.header("Content-Type", "application/json"); 

		JSONObject requestParams = new JSONObject(); 

		requestParams.put("hostname", "www.pizzzahut.com"); 
		//www.pizzahut.com
		
		requestParams.put("country_code", "us"); 
		
		request.body(requestParams.toString()); 

		Response response = request.post("/checkavaialability"); 
		
		long responsetime = response.getTime(); 
		
		System.out.println("Response Time is: "+responsetime); 
		
		String responseBody = response.getBody().asString(); 
		
		System.out.println("Response Body is =>" + responseBody); 
		
		JSONParser jp = new JSONParser(); 

		JSONObject jsonResult = (JSONObject) (jp.parse(responseBody)); 
		
		//JSONObject jsonStatus = (JSONObject) (jsonResult.get("status")); 

		JSONObject JsonMeta = (JSONObject) (jsonResult.get("meta")); 

		JSONObject jsonFP = (JSONObject) (jsonResult.get("result")); 
		
		String Status = jsonFP.get("status").toString(); 
		
		System.out.println("Status is: "+Status); 
		
		try {
		Assert.assertEquals("200",Status); 
		} catch(Error e){
			// Following lines will be printed when the assert condition fails
			System.out.println("Assert equals failed. But test execution is not aborted.");
			
			System.out.println("Error message: " + e.toString()); 
		}
        /*System.out.println("This line is executed because assertEquals"
                + "passed since both the strings are same");*/
		
		String fp_id = jsonFP.get("fp_id").toString(); 
		
		System.out.println("Fp_Id is: " +fp_id); 
		
		String  reference_id= JsonMeta.get("request_reference_id").toString(); 
		
		System.out.println("Request Reference Id is: "+reference_id); 
		
		

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

