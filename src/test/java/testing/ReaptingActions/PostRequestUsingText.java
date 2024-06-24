package testing.ReaptingActions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import testing.ReaptingActions.utils.FileNameConstants;

import java.io.File;
import java.io.IOException;

public class PostRequestUsingText {

    @Test
    public void postRequestText(){

        try {
            String postAPIrequestBody=FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");

            Response response=
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

            //$.booking.firstname this Returns value in the JSON array
            //Storing in the JSON array
//           JSONArray jsonarray = (JSONArray) JsonPath.parse(response.body().asString());

            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(response.body().asString());
            JSONArray array = new JSONArray();

            System.out.println(array.put(obj));

//            List<Map<String, String>> list= JsonPath.read(response.body().asString(),"$.booking.firstname");
//            JsonArray jsonarray = JsonPath.read(response.body().asString(),"$.booking.firstname");
//            net.minidev.json.JSONArray jsonarray =  JsonPath.read(response.body().asString(),"$.booking.firstname");
//            System.out.println(list);
//            String firstname = (String) jsonarray.get(0);
           String firstname = array.get(0).toString();
           Assert.assertEquals(firstname,"Sally");


//            JSONArray jsonarraylast = JsonPath.read(response.body().asString(),"$.booking.lastname");
//
////            JsonArray jsonarraylast = JsonPath.read(response.body().asString(),"$.booking.lastname");
////            net.minidev.json.JSONArray jsonarray2 =  JsonPath.read(response.body().asString(),"$.booking.lastname");
//            String lastnmae = (String) jsonarraylast.get(0);
//            Assert.assertEquals(lastnmae,"Brown");
//
//            JSONArray jsonarraychecking = JsonPath.read(response.body().asString(),"$.booking.bookingdates.checkin");
//
//            String checkin =  (String)jsonarraychecking.get(0);
//            Assert.assertEquals(checkin,"2013-02-23");
//
//            int bokingId = JsonPath.read(response.body().asString(),"$.bookingid");
//
//
//            RestAssured
//                    .given()
//                        .contentType(ContentType.JSON)
//                        .baseUri("https://restful-booker.herokuapp.com/booking")
//                    .when()
//                        .get("/{bookingId}",bokingId)
//                    .then()
//                        .assertThat()
//                        .statusCode(200);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
