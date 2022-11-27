import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllPosts {

    @Test
    public void  shouldGetAllPost() {
        when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void  shouldGetFirstPost() {
        when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
