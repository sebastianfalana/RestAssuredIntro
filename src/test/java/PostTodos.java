import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostTodos extends TestBase {

    String body = "{\n" +
            "    \"userId\": 9,\n" +
            "    \"title\": \"I should do the homework\",\n" +
            "    \"completed\": true\n" +
            "  }";

    String todos = "/todos";

    @Test
    public void shouldPostUser(){
        Response response =
                given()
                        .body(body)
                        .contentType(ContentType.JSON).
                when()
                        .post(baseUrl+todos).
                then()
                        .statusCode(201)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("title"),"I should do the homework");
        Assert.assertEquals(jsonPath.get("completed"),true);
        Assert.assertEquals(jsonPath.get("userId").toString(),"9");

    }
}
