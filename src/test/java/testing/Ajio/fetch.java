package testing.Ajio;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class fetch {
    public static void main(String[] args) {

        ValidatableResponse response=
                 RestAssured.given().auth().preemptive().basic("username", "password").when().get("/secured/hello").then().statusCode(200);
        System.out.println(response);
//Response response =
//                RestAssured.given().
//                        header("Content-Type", "application/json").
//                        body(loginPayload).
//                        when().
//                        post("/login").
//                        then().
//                        log().ifError().
//                        statusCode(200).
//                        contentType("application/vnd.api+json").
//                        body("$", hasKey("authorization_token")).                                   //authorization_token is present in the response
//                        body("any { it.key == 'authorization_token' }", is(notNullValue())).        //authorization_token value is not null - has a value
//                        extract().response();
    }

}
