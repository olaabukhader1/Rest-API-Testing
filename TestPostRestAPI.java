package TestClasses;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.HandleRequestReponse;
import requestHandling.RestClientHandler;

public class TestPostRestAPI {
	@Test
	public void TestPostData() throws Exception
{
		//1. Get HttpURLConnection
	HttpURLConnection connection= RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST , HTTPRequestsContentTypes.JSON);
	//2. Read JSON file		
	String json=JSONUtils.readJSONObjectFromFile(FilesPaths.CreatBookingJSONFile);
		//3.sent post request
	RestClientHandler.sendPost(connection, json, HTTPRequestsContentTypes.JSON);
    //4. Read Response
	String response=RestClientHandler.readResponse(connection);
	System.out.println(response);
	//5. convert string to json object 
      JSONObject obj= (JSONObject) JSONUtils.convertStringToJSON(response);
	   System.out.println(obj.get("bookingid"));
	}
	
	@Test
	public void testCreatBooking() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewBookingJSONFile);
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		String bookingid = jsonObject.get("bookingid").toString();
		String additionalneeds = ((JSONObject) jsonObject.get("booking")).get("additionalneeds").toString();

		String checkin = ((JSONObject) (((JSONObject) jsonObject.get("booking")).get("bookingdates"))).get("checkin")
				.toString();
		System.out.println("bookingid=" + bookingid);
		System.out.println("additionalneeds=" + additionalneeds);
		System.out.println("checkin=" + checkin);

		// 6. validation data jsonObject==response
		// (https://restful-booker.herokuapp.com/booking/id)
		
		
	}
	@Test
	public void testPOSTExistingUser() throws Exception {
		// Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.usersInfo, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		// Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.ExistingUser);
		// Post Request
		
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		assertTrue( connection.getResponseCode() != 201);	
		// Reading Response
		String response = RestClientHandler.readResponse(connection);
		assertFalse(response.equals("ola")); 
	}
@Test
public void TestNotFoundUser() throws Exception
{
	
	String url = URLs.ReqResAPI;;
	HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.DELETE,
			HTTPRequestsContentTypes.JSON);
	assertFalse(connection.getResponseCode()==401);

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
