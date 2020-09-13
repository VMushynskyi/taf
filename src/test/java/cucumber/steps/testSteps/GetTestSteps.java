package cucumber.steps.testSteps;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static taf.utils.ApplicationProperties.getApiURI;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class GetTestSteps {

  @Step
  public void getListOfAppropriateResources(String param) {
    List<Map<String, Object>> usersList =
        when().get(getApiURI() + param).getBody().jsonPath().setRoot("_embedded.userList").get();
    Serenity.setSessionVariable("usersList").to(usersList);
  }

  @Step
  public void verifyAppropriateListOfUsers(List<List<String>> listOfRowValues) {
    List<Map<String, Object>> users = Serenity.sessionVariableCalled("usersList");
    for (List<String> elements : listOfRowValues) {
      assertThat(users.get(listOfRowValues.indexOf(elements)).get("name"))
          .isEqualTo(elements.get(0));
      assertThat(users.get(listOfRowValues.indexOf(elements)).get("job"))
          .isEqualTo(elements.get(1));
    }
  }
}
