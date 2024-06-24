package testing.ReaptingActions;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import testing.ReaptingActions.utils.FileNameConstants;

import java.io.File;
import java.io.IOException;

import static testing.ReaptingActions.utils.FileNameConstants.*;

public class patchRequestCall {

    @Test
    public void patchRequest() {
        try {
            String postAPIrequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY), "UTF-8");


            String tokenAPIRequestBody = FileUtils.readFileToString(new File(TOKEN_REQUEST_BODY), "UTF-8");
            String putAPIRequestBody = FileUtils.readFileToString(new File(PUT_API_REQUEST), "UTF-8");
            String patchAPIRequestBody = FileUtils.readFileToString(new File(PATCH_API_REQUEST), "UTF-8");


            Response response =
                    RestAssured
                            .given()
                            .contentType(ContentType.JSON)
                            //for this API call Post request body was read from the Text File which
                            //we have printend in the String Variable postAPIrequestBody
                            .body(postAPIrequestBody)
                            .baseUri("https://restful-booker.herokuapp.com/booking")
                            .when()
                            .post()
                            .then()
                            .assertThat()
                            .statusCode(200)
                            .extract()
                            .response();
            System.out.println(response.body().asString());

            //$.booking.firstname this Returns value in the JSON array
            //Storing in the JSON array
//           JSONArray jsonarray = (JSONArray) JsonPath.parse(response.body().asString());
//            String firstname = jsonarray.get(0).toString();
//            Assert.assertEquals(firstname, "Sally");


            int bokingId = JsonPath.read(response.body().asString(), "$.bookingid");
//
//
            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    .when()
                    .get("/{bookingId}", bokingId)
                    .then()
                    .assertThat()
                    .statusCode(200);


            //token genreation
            Response tokenAPIResponse = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(tokenAPIRequestBody)
                    .baseUri("https://restful-booker.herokuapp.com/auth")
                    .when()
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

            String token = JsonPath.read(tokenAPIResponse.body().asString(), "$.token");


            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(putAPIRequestBody)
                    .header("Cookie", "token=" + token)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    .when()
                    .put("/{bookingId}", bokingId)
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("firstname", Matchers.equalTo("James"))
                    .body("lastname", Matchers.equalTo("Brown"));

            //Patch Call

            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(patchAPIRequestBody)
                    .header("Cookie", "token=" + token)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    .when()
                    .patch("/{bookingId}",bokingId)
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("firstname",Matchers.equalTo("kullu"))
                    .extract()
                    .response();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
