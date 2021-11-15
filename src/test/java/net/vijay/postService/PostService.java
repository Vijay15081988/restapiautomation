package net.vijay.postService;

import io.restassured.response.Response;
import net.vijay.amlosafe.API.util.APISetup;
import net.vijay.amlosafe.requestbuilder.PostServiceRequestBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class PostService extends APISetup {
    
	public static Logger log = Logger.getLogger(PostService.class.getName());
	PostServiceRequestBuilder postServiceRequestBuilder;
    
	public PostService() {
    	postServiceRequestBuilder = new PostServiceRequestBuilder();
    }
    
	@Test
    public void verifyPostService() throws IOException {
	    
    	Response response = postServiceRequestBuilder.veriyPostServiceResponse();
	    Assert.assertEquals(response.getStatusCode(),200);
    }


    


}
