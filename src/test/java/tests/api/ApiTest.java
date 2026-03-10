package tests.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    @Test
    public void testGetPost() {
        baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body("userId", is(1))
                .body("title", notNullValue())
                .log().all();
    }

    @Test
    public void testPostWithString(){
        baseURI = "https://jsonplaceholder.typicode.com";
        String testPost = "{\n"+
                "\"userId\": 1,\n"+
                "\"id\": 1," +
                "\"title\": \"qui est esse\",\n" +
                "\"body\": \"est r\"\n"+
                "}";


        given()
                .contentType(ContentType.JSON)
                .body(testPost)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void testPostWithMap() {
        baseURI = "https://jsonplaceholder.typicode.com";

        // Creating post with HashMap
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("userId", 1);
        bodyParams.put("title", "My Title");
        bodyParams.put("body", "Body content made with automation");

        given()
                //Explaing to API that we are sending a JSON
                .header("Content-type", "application/json; charset=UTF-8")
                //RestAssured uses the dependency Jackson(databind) to convert Map to JSON
                .body(bodyParams)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                //Checking if server received the good message
                .body("title", is("My Title"))
                //printing on console
                .log().all();
    }

    @Test
    public void testPutWithMap(){
        baseURI = "https://jsonplaceholder.typicode.com";

        // Creating post with HashMap
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("title", "My New Title");
        bodyParams.put("body", "Body content STILL made with automation");

        given()
                //Explaing to API that we are sending a JSON
                .header("Content-type", "application/json; charset=UTF-8")
                //RestAssured uses the dependency Jackson(databind) to convert Map to JSON
                .body(bodyParams)
        .when()
                .put("/posts/1")
        .then()
                .statusCode(200)
                //Checking if server received the good message
                .body("title", is("My New Title"))
                //printing on console
                .log().all();

    }

    @Test
    public void testDelete(){
        baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .contentType(ContentType.JSON)
        .when()
                .delete("/posts/1")
        .then()
                .statusCode(200)
                .assertThat().body(is("{}"))
                .log().all();
    }
}