package net.vijay.amlosafe.requestbuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.aeonbits.owner.ConfigFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import net.vijay.amlosafe.API.controller.Headers;
import net.vijay.amlosafe.API.request.model.HeadersPOJO;
import net.vijay.amlosafe.API.util.ConfigProperty;
import net.vijay.amlosafe.API.util.RequestBuilder;
import net.vijay.amlosafe.API.util.TestUtil;

public class PostServiceRequestBuilder {
	public static Logger log = Logger.getLogger(PostServiceRequestBuilder.class.getName());
	String endpoint = "%sapi/v3/flights/flight/get-fares-calender";
	protected static ConfigProperty configProperty;
	
	public PostServiceRequestBuilder() {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		String domain = configProperty.getBaseURI();
        this.endpoint = String.format(this.endpoint, domain);
        log.info("User Docs endpoint: " + endpoint);
	}
		
	public Response veriyPostServiceResponse() throws IOException
	{
		String filePath =configProperty.getDataFilePath();
		log.info("File Path is :"+filePath);
		HashMap<String, String> headersMap;
		HeadersPOJO requestHeaders;    
	    RequestBuilder reqbuilder = new RequestBuilder();
	    requestHeaders = new Headers().setValidHeaders();
	    headersMap = new ObjectMapper().convertValue(requestHeaders, new TypeReference<Map<String, String>>() {
	    });
	    String json=TestUtil.readFileAsString(filePath);
	    log.info("=========================================================================");
	    log.info("Authenticate Endpoint : " + endpoint);
	    log.info("Header : " + headersMap);
	    log.info("=========================================================================");
	
	    Response response = reqbuilder
	            .setEndPoint(endpoint)
	            .setHeaders(headersMap).setRequestBody(json).executePost();
	
	    log.info("Response" + response.getStatusCode());
	    return response;
	}
	
}
