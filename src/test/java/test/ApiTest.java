package test;

import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import taf.model.Location;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest extends BaseTest {

    @Test(description = "test get")
    public void getDifferentAuthors() {
        String params = "locations";
        client.get(params);
        int statusCode = client.getResponse().getStatusCode();
        String body = client.getResponse().getBody();
        System.out.println(body);
        body = "[" + body + "]";
        System.out.println("new positions  ---->>> "+body);
        JSONArray authorsArr = new JSONArray(body);
        System.out.println(authorsArr);
        int len = authorsArr.length();
        System.out.println("len --->> "+ len);
        Assert.assertEquals(statusCode, 200);
    }

    @Test(description = "test post")
    public void postLocation() {
        client.post("/locations",new Location((long) 4,"London","Milton"));
        int statusCode = client.getResponse().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(description = "test delete")
    public void deleteLocation() {
        client.delete("/locations/4");
        int statusCode = client.getResponse().getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(description = "test rest assure status code")
    public void getStatusCode() {
        when().get("/locations")
                .then().log().ifValidationFails().statusCode(200);
    }

    @Test(description = "test rest assure  specific location")
    public void verifyLocations() {
        get("/locations/1")
                .then().statusCode(200).assertThat().body("name", equalTo("Bilbo Baggins"));
    }

    @Test(description = "test rest assure post")
    public void verifyPostMethod() {
        String postData = "{\n" +
                "  \"id\": \"4\",\n" +
                "  \"name\": \"Some\",\n" +
                "  \"address\": \"name\"\n" +
                "}";
        given()
                .contentType(ContentType.JSON)
                .body(postData)
                .post("/locations")
                .then()
                .statusCode(200);
    }

    @Test(description = "test rest assure put")
    public void verifyPutMethod() {
        String putData = "{\n" +
                "  \"name\": \"Changed\",\n" +
                "  \"address\": \"name\"\n" +
                "}";
        given()
                .contentType(ContentType.JSON)
                .body(putData)
                .put("/locations/9")
                .then()
                .statusCode(200);
    }

    @Test(description = "test rest assure delete")
    public void verifyDeleteMethod() {
        given()
                .contentType(ContentType.JSON)
                .delete("/locations/4")
                .then()
                .statusCode(200);
    }
}
