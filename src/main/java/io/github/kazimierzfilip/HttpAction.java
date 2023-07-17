package io.github.kazimierzfilip;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

public class HttpAction {
    private final RequestSpecification requestSpecification;
    @Getter
    private Response response;

    public HttpAction(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public void post() {
        response = requestSpecification.post();
    }
}
