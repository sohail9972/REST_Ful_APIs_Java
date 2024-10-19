package testing.Ajio;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class AuthToken {
    @Test
    public void authenticate() {
       JSONObject object = new JSONObject();
       object.put("username","admin");
       object.put("password","password123");

//       UserLoginDetails credentials = new UserLoginDetails();
//        credentials.setUsername("admin");
//        credentials.setPassword("password");

//
////        Response response =
//                RestAssured.given()
//                        .header("Content-Type","application/json")
//                        .body(credentials)
//                        .when()
//                        .post("https://restful-booker.herokuapp.com/auth")
//                        .then()
//                        .assertThat()
//                        .statusCode(200)
//                        .extract()
//                        .response().as(TestAuthToken.class);



//        System.out.println(response);
//        String url = "https://restful-booker.herokuapp.com";
//
//       TestAuthToken authenticationToken =
//
//
//                given()
////                        .accept("application/json")
////                        .contentType("application/json")
//                        .body(credentials)
//                        .expect()
//                        .statusCode(200)
//                        .when()
//                        .post(url+"/auth")
//                        .then()
//                        .log().all()
//                        .extract()
//                        .body().as(TestAuthToken.class);
//
//        assertNotNull(authenticationToken.getToken());
//
//        "username" : "admin",
//                "password" : "password123"



//        Response response =
//                RestAssured.given().
//                        header("Content-Type", "application/json").
//                        body().
//                        when().
//                        post("/login").
//                        then().
//                        log().ifError().
//                        statusCode(200).
//                        contentType("application/vnd.api+json").
//                        body("$", hasKey("authorization_token")).                                   //authorization_token is present in the response
//                        body("any { it.key == 'authorization_token' }", is(notNullValue())).        //authorization_token value is not null - has a value
//                        extract().response();


        Response response =
                RestAssured.given()
                        .header("Content-Type", "application/json")
//                .contentType(ContentType.JSON)
                        .body(object)
//                .pathParams("bookingid",bookingId)
//                        .baseUri("https://restful-booker.herokuapp.com/booking/{{b_id}}")    or next line then mention params in get method
                .baseUri("https://restful-booker.herokuapp.com")
                .when()
                .post("/auth")
                .then()
                .assertThat()
                .statusCode(200)
                        .extract().response();

//        String auth =response.asString();
//        System.out.println(auth);



    }
}
