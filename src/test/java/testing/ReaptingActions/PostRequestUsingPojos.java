package testing.ReaptingActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import testing.ReaptingActions.Pojos.Booking;
import testing.ReaptingActions.Pojos.BookingDates;
import testing.ReaptingActions.utils.FileNameConstants;

import java.io.File;
import java.io.IOException;

public class PostRequestUsingPojos {

    @Test
    public void postApiRequestPojo() throws JsonProcessingException {

        try {
            String jsonSchema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA), "UTF-8");


            BookingDates bookingDates = new BookingDates("2024-07-26", "2024-08-26");
            Booking booking = new Booking("Sally", "Brown", "breakfast", 1000, true, bookingDates);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(booking);
            System.out.println(requestBody);

//        De-Serialization

            Booking bookingDetails = objectMapper.readValue(requestBody, Booking.class);
            System.out.println(bookingDetails.getFirstname());
            System.out.println(bookingDetails.isDepositpaid());

            System.out.println(bookingDetails.getBookingdates().getCheckin());
            System.out.println(bookingDetails.getBookingdates().getCheckout());


            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    .when()
                    .post()
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();

            int bookingId = response.path("bookingid");

            System.out.println(jsonSchema);
            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    .when()
                    .get("/{bookingId}", bookingId)
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}