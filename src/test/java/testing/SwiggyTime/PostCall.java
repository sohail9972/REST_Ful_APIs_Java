package testing.SwiggyTime;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostCall {

    public static final String TOKEN = "91F045FE04341EF894BDE820668900";

    @Test
    public void BookingTest(){
        JSONObject booking = new JSONObject();
        JSONObject bookingpath= new JSONObject();

        booking.put("firstname","sohail");
        booking.put("lastname","maid");
        booking.put("totalprice",500);
        booking.put("depositpaid",true);
        booking.put("additionalneeds","breakfast");
        booking.put("bookingdates",bookingpath);


        bookingpath.put("checkin","2024-07-25");
        bookingpath.put("checkout","2024-07-30");

        Response response = RestAssured
                .given()
                .body(booking.toString())
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        int bookid= response.path("bookingid");

        System.out.println(response.body().asString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("bookingid",bookid)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get("{bookingid}")
                .then()
                .assertThat()
                .statusCode(200);
    }



    @Test
    public void createUser(){
        //Create JOSN Body first to Pass

        JSONObject users = new JSONObject();
        users.put("object_reference_id","SohailMSwiggy@gmail.com");
        users.put("mobile_number","9988776655");
        users.put("email_address","SohailMswiggy@gmail.com");
        users.put("first_name","Swiggy");
        users.put("last_name","S");
        users.put("mobile_country_code","91");


        System.out.println(users.toString());


        Response response =
                RestAssured
                .given().header("Authorization", TOKEN)
                .body(users.toString())
                .contentType(ContentType.JSON)
                .baseUri("https://sandbox.juspay.in/customers")
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200)
                        .extract().response();

        System.out.println(response.body().asString());



    }
}
