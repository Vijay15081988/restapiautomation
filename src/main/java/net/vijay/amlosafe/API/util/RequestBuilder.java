package net.vijay.amlosafe.API.util;

import java.util.HashMap;
import java.util.Map;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    private Map<String, String> headers;
    private Map<String, String> params;
    private Map<String, String> pathparams;
    private String endPoint;
    private Object request;

    private Map<String, String> getHeaders() {
        return headers;
    }

    public RequestBuilder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    private String getEndPoint() {
        return endPoint;
    }

    public RequestBuilder setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    private Object getRequestBody() {
        return request;
    }

    public RequestBuilder setRequestBody(Object request) {
        this.request = request;
        return this;
    }

    private Map<String, String> getParams() {
        return params;
    }

    public RequestBuilder setParams(HashMap<String, String> params) {
        this.params = params;
        return this;
    }

    private Map<String, String> getPathParams() {
        return pathparams;
    }

    public RequestBuilder setPathParams(HashMap<String, String> pathparams) {
        this.pathparams = pathparams;
        return this;
    }

    public Response executeGet() {
        RestAssured.defaultParser = Parser.JSON;
//		RequestSpecification request = RestAssured.given().when().with().contentType("application/json; charset=utf-8");
        RequestSpecification request = RestAssured.given().when().with().contentType("application/json");
        if (this.getEndPoint() != null) {

            if (this.getHeaders() != null) {
                request = request.headers(getHeaders());
            }
            if (this.getParams() != null) {
                request = request.params(getParams());
            }
            if (this.getPathParams() != null) {
                request = request.pathParams(getPathParams());
            }
            return request.get(getEndPoint()).then().extract().response();
        }
        return null;
    }


    public Response executePost() {
        RequestSpecification request = RestAssured.given().when().with().contentType("application/json");
        if (this.getEndPoint() != null) {

            if (this.getHeaders() != null) {
                request = request.headers(getHeaders());
            }
            if (this.getParams() != null) {
                request = request.params(getParams());

            }
            if (this.getPathParams() != null) {
                request = request.pathParams(getPathParams());

            }
            if(this.getRequestBody() != null) {
                return request.body(getRequestBody()).post(getEndPoint()).then().extract().response();
            }else
            {
                return request.post(getEndPoint()).then().extract().response();
            }
        }
        return null;
    }




    public Response executePut() {
        RequestSpecification request = RestAssured.given().when().with().contentType("application/json");
        if (this.getEndPoint() != null ) {
            if (this.getHeaders() != null) {

                request = request.headers(getHeaders());
            }
            if (this.getParams() != null) {
                request = request.params(getParams());

            }
            if (this.getPathParams() != null) {
                request = request.pathParams(getPathParams());

            }
            if(this.getRequestBody() != null) {
                return request.body(getRequestBody()).contentType(ContentType.JSON).put(getEndPoint()).then().extract().response();
            }else
            {
                return request.put(getEndPoint()).then().extract().response();
            }
        }
        return null;
    }

    public Response executePatch() {
        RequestSpecification request = RestAssured.given().when().with().contentType("application/json");
        if (this.getEndPoint() != null && this.getRequestBody() != null) {
            if (this.getHeaders() != null) {

                request = request.headers(getHeaders());
            }

            if (this.getPathParams() != null) {
                request = request.pathParams(getPathParams());
            }

            return request.body(getRequestBody()).contentType(ContentType.JSON).patch(getEndPoint()).then().extract().response();
        }
        return null;
    }


    public Response executeDelete() {
        RequestSpecification request = RestAssured.given().when().with().contentType("application/json");
        if (this.getEndPoint() != null ) {
            if (this.getHeaders() != null) {
                request = request.headers(getHeaders());
            }

            if (this.getPathParams() != null) {
                request = request.pathParams(getPathParams());
            }

            return request.contentType(ContentType.JSON).delete(getEndPoint()).then().extract().response();
        }
        return null;
    }

}
