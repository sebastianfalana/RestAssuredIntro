import base.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsers extends TestBase {
    String users = "/users";

    @Test
    public void shouldGetAllUsersFromCompany() {
        Response response =
                given()
                        .queryParam("company.name", "Deckow-Crist").
                        when()
                        .get(baseUrl + users).
                        then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].company.name"), "Deckow-Crist");


    }
}
