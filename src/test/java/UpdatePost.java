import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdatePost extends TestBase {
    String fullBody = "{\n" +
            "    \"userId\": 9,\n" +
            "    \"title\": \"I should do the homework\",\n" +
            "    \"completed\": true\n" +
            "  }";

    String partOfBody = "{\n" +
            "    \"userId\": 9,\n" +
            "    \"title\": \"I should do the homework\",\n" +
            "    \"completed\": true\n" +
            "  }";
    private String posts = "/posts";

    @Test
    public void shouldUpdatePost() {

        Response response =
        given()
                .pathParams("postId","3")
                        .body(fullBody)
                                .contentType(ContentType.JSON).
                when()
                .put(baseUrl+posts+"/{postId}").
                then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();



    }
}
