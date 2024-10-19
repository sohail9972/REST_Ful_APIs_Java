package testing.Ajio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestfulBookerAuth {

    public static void main(String[] args) throws Exception {
        // Step 1: Fetch the auth token from Restful Booker
        String authToken = fetchAuthToken();

        // Step 2: Use the token in an API request
        useAuthToken(authToken);
    }

    // Method to fetch the authentication token
    private static String fetchAuthToken() throws Exception {
        // URL for the auth endpoint
        String tokenUrl = "https://restful-booker.herokuapp.com/auth";

        // Construct the request body with username and password
        String requestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";

        // Create the HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Build the POST request to the /auth endpoint
        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request and get the response
        HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());

        // Parse the token from the response (assuming it's in JSON format)
        String responseBody = tokenResponse.body();
        System.out.println("Token response: " + responseBody);

        // Parse the JSON response to extract the token
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String token = jsonObject.get("token").getAsString();

        return token;
    }

    // Use the auth token in a subsequent API request (example: get bookings)
    private static void useAuthToken(String token) throws Exception {
        // URL for the API endpoint
        String apiUrl = "https://restful-booker.herokuapp.com/booking";

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Build GET request with Authorization header (if needed in other cases)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + token)  // Add the token if needed in Authorization header
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response
        System.out.println("API Response: " + response.body());
    }
}
