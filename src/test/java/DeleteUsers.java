import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsers extends TestBase{
    public String users = "/users";
    @Test
    public void shouldUpdateUser() {

        given()
                .pathParams("userId", "1").

                when()
                .delete(baseUrl + users + "/{userId}").
                then()
                .statusCode(200);
    }
}
