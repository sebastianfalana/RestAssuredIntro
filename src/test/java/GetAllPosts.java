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
}
