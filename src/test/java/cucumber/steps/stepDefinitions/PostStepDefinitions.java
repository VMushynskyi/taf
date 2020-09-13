package cucumber.steps.stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.steps.testSteps.PostTestSteps;
import java.util.List;
import net.thucydides.core.annotations.Steps;

public class PostStepDefinitions {

  @Steps private PostTestSteps postTestSteps;

  @When("^the user posts desired user$")
  public void the_user_post_desired_user_and_verifies_appropriate_response(DataTable table) {
    List<List<String>> listOfRowValues = table.cells(1);
    postTestSteps.postUser(listOfRowValues);
  }

  @Then("^the user verifies that previous user is posted$")
  public void the_user_verifies_that_previous_user_is_posted() {
    postTestSteps.verifyThatPreviousUserArePosted();
  }
}
