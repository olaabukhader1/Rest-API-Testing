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
import static java.util.concurrent.TimeUnit.MILLISECONDS;


public class TestGetRestAPI {
	String url = URLs.ReqResAPI;

	@Ignore
	@Test
	public void TestRestClientHandler() throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));

	}
	@Ignore
	
	@Test 
	public void TestGETWithNotExistUserID() throws IOException {
		String userID = "3";
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.usersInfo + userID, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("ERROR 404 Not Found!!!", connection.getResponseCode() == 404);
		String response = RestClientHandler.readResponse(connection);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		String url = URLs.ReqResAPI+"2";
		//url = url.replace("userID", "2");
		System.out.println(url);
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.UpdateUserJSONFile);
		System.out.println(resquestJSONObject);
		// 3. Post Request
		RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		System.out.println(connection.getResponseCode());
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
	}

@Test
public void getUserDetailsTest() throws Exception
{
	// 1. Open Connection --- HttpURLConnection
	String url = URLs.ReqResAPI+"2";
	//url = url.replace("userID", "2");
	System.out.println(url);
	HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
			HTTPRequestsContentTypes.JSON);
	// 2. Prepare Json Object
	String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.userListJSONFile);
	System.out.println(resquestJSONObject);
	// 3. Post Request
	RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
	// 4. Reading Response
	System.out.println(connection.getResponseCode());
	String response = RestClientHandler.readResponse(connection);
	System.out.println(response);
	//5. convert string to json object 
JSONObject obj= (JSONObject) JSONUtils.convertStringToJSON(response);
	System.out.println(obj.get("bookingid"));
	
	
}
}
