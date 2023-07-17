package posts;

import io.github.kazimierzfilip.RestTest;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostPostsTest extends RestTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
    }

    @Data
    public static class PostRequest {
        private Integer id;
        private Integer userId;
        private String title;
        private String body;
    }

    @Issue("TYPICODE-1")
    @Test
    public void success_create_post() {
        PostRequest requestBody = prepareRequest();
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .log().all();

        when().post();

        then().log().all()
                .assertThat()
                .statusCode(201)
                .header("Location", notNullValue())
                .header("Location", matchesPattern(RestAssured.baseURI + RestAssured.basePath + "/\\d+"));

        responseBodyEqualsRequestBody(requestBody);
    }

    private void responseBodyEqualsRequestBody(PostRequest requestBody) {
        Integer id = getIdFromLocationHeader(getResponse().header("Location"));
        requestBody.setId(id);
        PostRequest responseBody = getResponse().as(PostRequest.class);
        assertEquals(requestBody, responseBody, "Response should be the same as request JSON");
    }

    private PostRequest prepareRequest() {
        return new PostRequest() {{
            setUserId(1);
            setTitle("First post");
            setBody("Lorem ipsum dolor sit amet");
        }};
    }
}
