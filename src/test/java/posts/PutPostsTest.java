package posts;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class PutPostsTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts/{id}";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutPostRequest {
        private Integer id;
        private Integer userId;
        private String title;
        private String body;
    }

    @Test
    public void successPostIsUpdated() {
        PutPostRequest body = new PutPostRequest(1, 1, "new title", "new body");

        PutPostRequest response = given()
                .body(body)
                .header(new Header("Content-Type", "application/json"))
                .pathParam("id", 1)
                .log().all()
                .when()
                .put()
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(PutPostRequest.class);

        assertEquals("Response should be the same as request body", body, response);
    }

    @Test
    public void errorPostNotFound() {
        PutPostRequest body = new PutPostRequest(1, 1, "new title", "new body");

        given()
                .pathParam("id", 0)
                .body(body)
                .log().all()
                .when().put()
                .then().log().all()
                .statusCode(404);
    }
}
