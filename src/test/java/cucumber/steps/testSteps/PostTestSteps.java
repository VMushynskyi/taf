package cucumber.steps.testSteps;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static taf.service.UserService.storeNextIdByExistingOrder;
import static taf.utils.ApplicationProperties.getApiURI;
import static taf.utils.ApplicationProperties.getUser;

import io.restassured.http.ContentType;
import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import taf.model.User;

public class PostTestSteps {

  @Step
  public void postUser(List<List<String>> listOfRowValues) {
    for (List<String> elements : listOfRowValues) {
      given()
          .contentType(ContentType.JSON)
          .body(new User(elements.get(0), elements.get(1)))
          .when()
          .post(getApiURI() + getUser());
      storeNextIdByExistingOrder();
      Serenity.setSessionVariable("userName").to(elements.get(0));
      Serenity.setSessionVariable("userJob").to(elements.get(1));
    }
  }

  @Step
  public void verifyThatPreviousUserArePosted() {
    int userId = Serenity.sessionVariableCalled("id");
    System.out.println(userId);
    Map<String, Object> usersList =
        when()
            .get(getApiURI() + getUser() + userId)
            .then()
            .statusCode(200)
            .extract()
            .response()
            .jsonPath()
            .setRoot("$")
            .get();
    assertThat(usersList.get("id")).isEqualTo(userId);
    assertThat(usersList.get("name")).isEqualTo(Serenity.sessionVariableCalled("userName"));
    assertThat(usersList.get("job")).isEqualTo(Serenity.sessionVariableCalled("userJob"));
  }
}
