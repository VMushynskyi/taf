package test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static taf.utils.ApplicationProperties.*;
import static taf.utils.FakeData.getFakeName;
import static taf.utils.FakeData.getJobName;

import io.restassured.http.ContentType;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import org.testng.annotations.Test;
import taf.model.User;

public class DefaultAssuredTests {

  @Test(description = "test get")
  public void testGet() {
    List<Map<String, Object>> usersList =
        when()
            .get(getApiURI() + getUser())
            .then()
            .statusCode(200)
            .extract()
            .response()
            .jsonPath()
            .setRoot("_embedded.userList")
            .get();
    assertThat("" + usersList.size() + "").isEqualTo(getInitialCapacityOfUsersList());
  }

  @Test(description = "test post")
  public void testPost() {
    given()
        .contentType(ContentType.JSON)
        .body(new User(getFakeName(), getJobName()))
        .when()
        .post(getApiURI() + getUser())
        .then()
        .assertThat()
        .statusCode(200)
        .body("job", equalTo(Serenity.sessionVariableCalled("jobPosition")));
  }

  @Test(description = "test put")
  public void testPut() {
    given()
        .contentType(ContentType.JSON)
        .body(new User(getFakeName(), getJobName()))
        .when()
        .put(getApiURI() + getUser() + "2")
        .then()
        .assertThat()
        .statusCode(200)
        .body("job", equalTo(Serenity.sessionVariableCalled("jobPosition")));
  }

  @Test(description = "test delete")
  public void testDelete() {
    given()
        .when()
        .delete(getApiURI() + getUser() + "6")
        .then()
        .assertThat()
        .statusCode(200)
        .and()
        .body(equalTo(""));
  }
}
