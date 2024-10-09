package testing.Zulri;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class TestPostman {

    public static void main(String[] args) {
        JSONObject body = new JSONObject();
        body.put("name", "ZulriTestretro");
        body.put("job", "Interview");

        System.out.println(body);
        Response response =
                RestAssured
                        .given()
                        .header("Content-Type","application/json")
                        .contentType(ContentType.JSON)
                        .baseUri("https://reqres.in/api/users")
                        .body(body.toString())
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(201)
                        .body("name", Matchers.equalTo("ZulriTestretro"))
                        .body("job",Matchers.equalTo("Interview"))
                        .extract()
                        .response();


        System.out.println(response.body().asString());
        JsonPath jsonPath = response.jsonPath();
        int id = Integer.valueOf(jsonPath.getString("id"));
        System.out.println(id);

       RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .baseUri("https://reqres.in/api/users")
                .when()
                .put("{id}",id)
                .then()
                .assertThat()
                .statusCode(200);
        System.out.println("Put Operation done");

        RestAssured
                .given()
                .baseUri("https://reqres.in/api/users")
                .when()
                .delete("{id}",id)
                .then()
                .assertThat()
                .statusCode(204)
                .extract()
                .response();
        System.out.println("deletion Done");
    }

}
