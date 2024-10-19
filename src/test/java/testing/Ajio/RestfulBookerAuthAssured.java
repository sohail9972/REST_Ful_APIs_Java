package testing.Ajio;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestfulBookerAuthAssured {

    public static void main(String[] args) {
        // Step 1: Fetch the auth token
        String authToken = fetchAuthToken();

        // Step 2: Use the token in an API request
        if (authToken != null) {
            useAuthToken(authToken);
            useAuthTokenPost(authToken);
        }

    }

    // Method to fetch the authentication token
    public static String fetchAuthToken() {
        // Set base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Request body for authentication
        String requestBody = "{\n" +
                "  \"username\" : \"admin\",\n" +
                "  \"password\" : \"password123\"\n" +
                "}";

        // Perform POST request to /auth endpoint and get the token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");

        // Check the response status
        if (response.statusCode() == 200) {
            // Extract token from the response
            String token = response.jsonPath().getString("token");
            System.out.println("Auth Token: " + token);
            return token;
        } else {
            System.out.println("Failed to fetch token. Status Code: " + response.statusCode());
            return null;
        }
    }

    // Method to use the fetched auth token
    public static void useAuthToken(String token) {
        // Example: Perform GET request to the /booking endpoint using the token
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)  // Add token to Authorization header if needed
                .when()
                .get("/booking");

        // Print the response
        System.out.println("API Response: " + response.getBody().asString());
    }

    public static String useAuthTokenPost(String token) {

        String RequestBody = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        // Example: Perform GET request to the /booking endpoint using the token
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)  // Add token to Authorization header if needed
                .body(RequestBody)
                .when()
                .post("/booking");
        if (response.statusCode() == 200) {
            // Extract token from the response
            String bookingid = response.jsonPath().getString("bookingid");
            System.out.println(bookingid);

        } else {
            System.out.println("Failed to Create the Book. Status Code: " + response.statusCode());

        }
        return null;
    }
}

