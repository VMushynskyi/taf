package cucumber.steps.testSteps;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static taf.utils.ApplicationProperties.getApiURI;
import static taf.utils.ApplicationProperties.getUsers;

import io.restassured.http.ContentType;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import taf.model.User;

public class PutTestSteps {

  @Step
  public void updatesNameAndJobForUserWithAppropriateId(String name, String job, int id) {
    Serenity.setSessionVariable("updatedName").to(name);
    Serenity.setSessionVariable("updatedJob").to(job);
    given()
        .contentType(ContentType.JSON)
        .body(new User(name, job))
        .when()
        .put(getApiURI() + getUsers() + id);
  }

  @Step
  public void verifyThatUserWithAppropriateIdIsUpdated(int id) {
    Map<String, Object> usersList =
        when()
            .get(getApiURI() + getUsers() + id)
            .then()
            .statusCode(200)
            .extract()
            .response()
            .jsonPath()
            .setRoot("$")
            .get();
    assertThat(usersList.get("name")).isEqualTo(Serenity.sessionVariableCalled("updatedName"));
    assertThat(usersList.get("job")).isEqualTo(Serenity.sessionVariableCalled("updatedJob"));
  }
}
