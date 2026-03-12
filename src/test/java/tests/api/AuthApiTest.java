package tests.api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.given;


public class AuthApiTest {

    String apiAddress = "https://restful-booker.herokuapp.com";
    String endPointLogin = "/auth"; //POST
    String endPointDelete = "/booking/"; //DELETE

    @Test
    public void testDeleteReserve(){
        //Call getLogin() to save the token
        String myToken = getLogin();

        //Get the ID of the reservation
        int reserveID = given().
                when().
                    get(apiAddress + endPointDelete).
                then().
                    extract().
                    path("[0].bookingid");
        System.out.println(reserveID);

        //Use token to authenticate and delete reserve
        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + myToken) // Use Token here.
        .when()
                .delete(apiAddress+endPointDelete+reserveID)
        .then()
                .statusCode(201); // 201 when delete was a success
    }

    //Method to do the login and return the authentication code
    public String getLogin(){

        // Creating post with HashMap
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("username","admin");
        bodyParams.put("password","password123");

        String myToken = given()
                //Explaing to API that we are sending a JSON
                .header("Content-type", "application/json; charset=UTF-8")
                //RestAssured uses the dependency Jackson(databind) to convert Map to JSON
                .body(bodyParams)
        .when()
                .post(apiAddress+endPointLogin)
        .then()
                .statusCode(200)
                .extract() // Extract response from site.
                .path("token"); //Jsonpath look for a key "token" on Json response and return to string.

        System.out.println("My access token is: " + myToken);

        return myToken;
    }
}
