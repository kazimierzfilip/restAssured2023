package posts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kazimierzfilip.RestTest;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.Data;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("POST /posts")
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
    @TmsLink("TYPI-1")
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

    @TmsLink("TYPI-2")
    @Test
    public void success_id_from_request_is_not_used() {
        PostRequest requestBody = new PostRequest() {{
            setId(1);
            setUserId(1);
            setTitle("First post");
            setBody("Lorem ipsum dolor sit amet");
        }};

        given()
                .body(requestBody)
                .and()
                .contentType(ContentType.JSON)
                .log().all();

        when().post();

        then().log().all()
                .assertThat()
                .statusCode(201)
                .header("Location", notNullValue());

        Integer id = getIdFromLocationHeader(getResponse().header("Location"));
        PostRequest responseBody = getResponse().as(PostRequest.class);

        assertEquals(id, responseBody.getId(), "Response id should be the same as in Location header");
        assertNotEquals(requestBody.getId(), responseBody.getId(),
                "Response id should not be the same as request id (id should be generated)");
    }

    @Issue("TYPICODE-2")
    @TmsLink("TYPI-3")
    @Test
    public void error_userId_invalid_type() {
        PostRequest requestBody = prepareRequest();
        Map<String, Object> map = new ObjectMapper().convertValue(requestBody, new TypeReference<>() {
        });
        map.put("userId", "0");

        given()
                .body(map)
                .and()
                .contentType(ContentType.JSON)
                .log().all();

        when().post();

        then().log().all()
                .assertThat()
                .statusCode(400)
                .header("Location", nullValue());
    }

    @Issue("TYPICODE-3")
    @TmsLink("TYPI-4")
    @Test
    public void error_invalid_content_type() {
        PostRequest requestBody = prepareRequest();

        given()
                .body(requestBody)
                .and()
                .contentType(ContentType.XML)
                .log().all();

        when().post();

        then().log().all()
                .assertThat()
                .statusCode(415)
                .header("Location", nullValue());
    }

    private PostRequest prepareRequest() {
        return new PostRequest() {{
            setUserId(1);
            setTitle("First post");
            setBody("Lorem ipsum dolor sit amet");
        }};
    }
}
