package net.vijay.API.getService;

import io.restassured.response.Response;
import net.vijay.amlosafe.API.controller.Headers;
import net.vijay.amlosafe.API.request.model.HeadersPOJO;
import net.vijay.amlosafe.API.util.APISetup;
import net.vijay.amlosafe.API.util.RequestBuilder;
import net.vijay.amlosafe.requestbuilder.GetServiceRequestBuilder;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GetService extends APISetup {
    public static Logger log = Logger.getLogger(GetService.class.getName());
    GetServiceRequestBuilder getServiceRequestBuilder;
    
    public GetService() {
    	getServiceRequestBuilder = new GetServiceRequestBuilder(System.getProperty("start_date"),System.getProperty("end_date"));
    }
    
    @Test
    public void verifyGetService() {
        Response response = getServiceRequestBuilder.getRequestResponse();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
