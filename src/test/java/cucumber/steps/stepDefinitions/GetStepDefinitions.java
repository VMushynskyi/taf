package cucumber.steps.stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.steps.testSteps.GetTestSteps;
import java.util.List;
import net.thucydides.core.annotations.Steps;

public class GetStepDefinitions {
  @Steps private GetTestSteps getTestSteps;

  @Given("^the user get list of all '(.*)'$")
  public void the_user_get_list_of_all_users(String param) {
    getTestSteps.getListOfAppropriateResources(param);
  }

  @Then("^the user verifies the appropriate list of users$")
  public void the_user_verifies_the_appropriate_list_of_users(DataTable table) {
    List<List<String>> listOfRowValues = table.cells(1);
    getTestSteps.verifyAppropriateListOfUsers(listOfRowValues);
  }
}
