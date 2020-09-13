package cucumber.steps.stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.steps.testSteps.PutTestSteps;
import net.thucydides.core.annotations.Steps;

public class PutStepDefinitions {

  @Steps private PutTestSteps putTestSteps;

  @When("^the user updates name with '(.*)' and job '(.*)' for user with '(\\d+)' id$")
  public void the_user_updates_name_with_and_job_for_user_with_id(String name, String job, int id) {
    putTestSteps.updatesNameAndJobForUserWithAppropriateId(name, job, id);
  }

  @Then("^the user verifies that previous user with '(\\d+)' was updated$")
  public void the_user_verifies_that_previous_user_was_updated(int id) {
    putTestSteps.verifyThatUserWithAppropriateIdIsUpdated(id);
  }
}
