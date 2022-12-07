import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetAllTodos extends TestBase{

    String todos = "/todos";
    @Test
    public void shouldGetAllTodos(){
        when()
                .get(baseUrl+todos)
                .then()
                .statusCode(200);

    }

    @Test
    public void shouldGetFourthTodos(){
        Response response =
        when()
                .get(baseUrl+todos+"/4")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("id"), "1");

    }

    @Test
    public void shouldGetAllTodosWithUserId4(){
        given()
                .queryParam("userId","1").
                when()
                .get(baseUrl+todos).
                then()
                .statusCode(200);

    }

    @Test
    public void shouldGetAllTodosWithUserId4AndCompleted(){
        given()
                .queryParam("userId","1")
                .queryParam("completed",true).
                when()
                .get(baseUrl+todos).
                then()
                .statusCode(200);

    }


}
