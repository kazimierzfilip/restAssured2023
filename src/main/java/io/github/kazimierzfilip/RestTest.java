package io.github.kazimierzfilip;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestTest {

    private RequestSpecification requestSpecification;
    private HttpAction httpAction;

    public RequestSpecification given() {
        requestSpecification = RestAssured.given();
        return requestSpecification;
    }

    public HttpAction when() {
        httpAction = new HttpAction(requestSpecification);
        return httpAction;
    }

    public ValidatableResponse then() {
        if (httpAction == null) {
            throw new RuntimeException("HTTP action not performed yet");
        }
        return httpAction.getResponse().then();
    }

    public Response getResponse() {
        if (httpAction == null) {
            throw new RuntimeException("HTTP action not performed yet");
        }
        return httpAction.getResponse();
    }

    public Integer getIdFromLocationHeader(String location) {
        return Integer.parseInt(location.substring(location.lastIndexOf("/") + 1));
    }
}
