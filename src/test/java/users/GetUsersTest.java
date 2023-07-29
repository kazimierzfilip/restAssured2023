package users;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetUsersTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/users";
    }

    @TmsLink("TYPI-5")
    @Test
    @Description("GET users success HTTP status code")
    public void successHttpStatusCode() {
        when().get()
                .then().log().all()
                .statusCode(200);
    }

    @TmsLink("TYPI-6")
    @Test
    @Description("All users are returned")
    public void successAllUsersAreReturned() {
        when().get()
                .then().log().all()
                .statusCode(200)
                .and()
                .body("size()", equalTo(10));
    }

    @TmsLink("TYPI-7")
    @Test
    @Description("All users properties are returned")
    public void successAllUserPropertiesAreReturned() {
        when().get()
                .then().log().all()
                .statusCode(200)
                .and()
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].username", notNullValue())
                .body("[0].email", notNullValue())
                .body("[0].address", notNullValue())
                .body("[0].address.street", notNullValue())
                .body("[0].address.suite", notNullValue())
                .body("[0].address.city", notNullValue())
                .body("[0].address.zipcode", notNullValue())
                .body("[0].address.geo", notNullValue())
                .body("[0].address.geo.lat", notNullValue())
                .body("[0].address.geo.lng", notNullValue())
                .body("[0].phone", notNullValue())
                .body("[0].website", notNullValue())
                .body("[0].company", notNullValue())
                .body("[0].company.name", notNullValue())
                .body("[0].company.catchPhrase", notNullValue())
                .body("[0].company.bs", notNullValue());
    }

}
