package posts;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PatchPostsTest {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts/{id}";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PatchPostRequest {
        private Integer id;
        private Integer userId;
        private String title;
        private String body;
    }

    @Test
    public void successPostIsUpdated() {
        PatchPostRequest body = new PatchPostRequest(null, null, null, "new body");

        PatchPostRequest response = given()
                .body(body)
                .header(new Header("Content-Type", "application/json"))
                .pathParam("id", 1)
                .log().all()
                .when()
                .patch()
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(PatchPostRequest.class);

        assertEquals("Provided field should be updated", body.getBody(), response.getBody());
        assertNotNull("Not provided fields should not be cleared", response.getId());
        assertNotNull("Not provided fields should not be cleared", response.getUserId());
        assertNotNull("Not provided fields should not be cleared", response.getTitle());
    }

    @Test
    public void errorPostNotFound() {
        PatchPostRequest body = new PatchPostRequest(null, 1, "new title", "new body");

        given()
                .pathParam("id", 0)
                .body(body)
                .log().all()
                .when().patch()
                .then().log().all()
                .statusCode(404);
    }
}
