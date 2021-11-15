package net.vijay.amlosafe.requestbuilder;

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

public class GetServiceRequestBuilder {
	public static Logger log = Logger.getLogger(GetServiceRequestBuilder.class.getName());
	protected static ConfigProperty configProperty;
	String endpoint = "%sen/flights/RUH-JED/%s/%s/Economy/1Adult";
	
	public GetServiceRequestBuilder(String startDate,String endDate) {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		String domain = configProperty.getBaseURI();
        this.endpoint = String.format(this.endpoint, domain,startDate,endDate);
        log.info("User Docs endpoint: " + endpoint);
	}
	
	public Response getRequestResponse()
	{
		HashMap<String, String> headersMap;
	    HeadersPOJO requestHeaders;
	    RequestBuilder reqbuilder = new RequestBuilder();
	    requestHeaders = new Headers().setValidHeaders();
	    headersMap = new ObjectMapper().convertValue(requestHeaders, new TypeReference<Map<String, String>>() {
	    });
	    log.info("=========================================================================");
	    log.info("Authenticate Endpoint : " + endpoint);
	    log.info("Header : " + headersMap);
	    log.info("=========================================================================");
	    Response response = reqbuilder
	            .setEndPoint(endpoint)
	           .setHeaders(headersMap).executeGet();
	    log.info("Response" + response.getStatusCode());
	    return response;
	}
}
