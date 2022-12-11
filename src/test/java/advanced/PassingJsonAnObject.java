package advanced;

import base.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import static org.testng.Assert.assertEquals;
public class PassingJsonAnObject extends TestBase {

    private String posts = "/posts";
    @Test
    public void shouldGetFirstPostAndValidateJson() {
        Post post =
                when()
                        .get(baseUrl + posts + "/1").
                        then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .as(Post.class);

        assertEquals(post.getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        assertEquals(post.getId(), 1);
    }

        @Test
        public void shouldCreateNewPost() {
        Post post = new Post(1,1,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit","some text");
            given()
                    .body(post)
                    .contentType(ContentType.JSON).
                    when()
                    .post(baseUrl + posts).
                    then()
                    .statusCode(201);
        }

    @Test
    public void shouldUpdatePatchWithLimitedBody(){
        Map<String, Object> post = new HashMap<>();

        post.put("title", "this is some new title");
        post.put("body", "text text");


        Response response =
                given()
                        .pathParam("postId", "3")
                        .body(post)
                        .contentType(ContentType.JSON).
                        when()
                        .patch(baseUrl + posts + "/{postId}").
                        then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("id").toString(), "3");
        Assert.assertEquals(jsonPath.get("userId").toString(), "1");
        Assert.assertEquals(jsonPath.get("title"), "this is some new title");
        Assert.assertEquals(jsonPath.get("body"), "text text");
    }
    }

