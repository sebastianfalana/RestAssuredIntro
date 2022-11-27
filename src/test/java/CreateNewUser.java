import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateNewUser extends TestBase{

    String body = "{\n" +
            "    \"name\": \"Seba Seba\",\n" +
            "    \"username\": \"Bret\",\n" +
            "    \"email\": \"tralaaaa@yopmail.com\",\n" +
            "    \"address\": {\n" +
            "        \"street\": \"Kulas Light\",\n" +
            "        \"suite\": \"Apt. 556\",\n" +
            "        \"city\": \"Waweczka\",\n" +
            "        \"zipcode\": \"92998-3874\",\n" +
            "        \"geo\": {\n" +
            "            \"lat\": \"-37.3159\",\n" +
            "            \"lng\": \"81.1496\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"phone\": \"22*145*55\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "        \"name\": \"Romaguera-Crona\",\n" +
            "        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "        \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "}";
    private String users = "/users";


    @Test
    public void shouldCreateNewUser(){
        Response response =
        given()
                .body(body)
                .contentType(ContentType.JSON).
        when()
                .post(baseUrl+users).
        then()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("username"), "Bret");




    }
}
