package cucumber.steps.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.steps.testSteps.DeleteTestSteps;
import net.thucydides.core.annotations.Steps;

public class DeleteStepDefinitions {
  @Steps private DeleteTestSteps deleteTestSteps;

  @Given("^the user deletes desired user from list by '(\\d+)' id$")
  public void the_user_deletes_desired_user_from_list_by_id(int id) {
    deleteTestSteps.deleteUserById(id);
  }

  @Then("^the user verifies that appropriate user with '(\\d+)' id$")
  public void the_user_verifies_that_appropriate_user_with_id(int id) {
    deleteTestSteps.verifyThatUserWithExpectedIdIsDeleted(id);
  }
}
