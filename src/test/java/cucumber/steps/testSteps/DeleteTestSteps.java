package cucumber.steps.testSteps;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;
import static taf.utils.ApplicationProperties.getApiURI;
import static taf.utils.ApplicationProperties.getUsers;

import net.thucydides.core.annotations.Step;

public class DeleteTestSteps {
  @Step
  public void deleteUserById(int id) {
    when().delete(getApiURI() + getUsers() + id);
  }

  @Step
  public void verifyThatUserWithExpectedIdIsDeleted(int id) {
    given()
        .when()
        .get(getApiURI() + getUsers() + id)
        .then()
        .assertThat()
        .statusCode(404)
        .and()
        .body(equalTo("User not found" + id));
  }
}
