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

public class TestDeleteRestAPI {
	@Test
	public void TestDelete()throws Exception
	{
		String url = URLs.ReqResAPI;
		System.out.println(url);
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.DELETE,
				HTTPRequestsContentTypes.JSON);
				connection.addRequestProperty("",url);
				RestClientHandler.sendDelete(connection, "", HTTPRequestsContentTypes.JSON);
				assertTrue( connection.getResponseCode() == 204);	
				
				
}
	
}
	


