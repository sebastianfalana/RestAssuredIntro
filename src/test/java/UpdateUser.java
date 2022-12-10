import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser extends TestBase{

    String fullBody = "{\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Leanne Graham\",\n" +
            "        \"username\": \"ZMIANA2\",\n" +
            "        \"email\": \"Sincere@april.biz\",\n" +
            "        \"address\": {\n" +
            "            \"street\": \"ZMIANA\",\n" +
            "            \"suite\": \"Apt. 556\",\n" +
            "            \"city\": \"Gwenborough\",\n" +
            "            \"zipcode\": \"92998-3874\",\n" +
            "            \"geo\": {\n" +
            "                \"lat\": \"-37.3159\",\n" +
            "                \"lng\": \"81.1496\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"phone\": \"1-770-736-8031 x56442\",\n" +
            "        \"website\": \"hildegard.org\",\n" +
            "        \"company\": {\n" +
            "            \"name\": \"Romaguera-Crona\",\n" +
            "            \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "            \"bs\": \"harness real-time e-markets\"\n" +
            "        }\n" +
            "    }";

    String partOfBody = "{\n" +
            "        \n" +
            "        \"name\": \"ZMIANA\",\n" +
            "        \"username\": \"ff\"\n" +
            "        \n" +
            "        }";
    public String users = "/users";

    @Test
    public void shouldUpdateUser() {

        Response response =
                given()
                        .pathParams("userId","1")
                        .body(fullBody)
                        .contentType(ContentType.JSON).
                        when()
                        .put(baseUrl+users+"/{userId}").
                        then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("id").toString().toString(), "1");
        Assert.assertEquals(jsonPath.get("username").toString(), "ZMIANA2");
        Assert.assertEquals(jsonPath.get("address.geo.lng"),"81.1496");

    }

    @Test
    public void shouldUpdatePartUser() {

        Response response =
                given()
                        .pathParams("userId","1")
                        .body(partOfBody)
                        .contentType(ContentType.JSON).
                        when()
                        .put(baseUrl+users+"/{userId}").
                        then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("username").toString(), "ff");
        Assert.assertNull(jsonPath.get("lat"));
        Assert.assertEquals(jsonPath.get("name"), "ZMIANA");



    }
}
