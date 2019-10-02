package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.ReviewPage;
import cucumber.api.Scenario;
import static junit.framework.TestCase.*;

public class ApprovePatientResults extends BaseStep {

    String nextSampleLabel;

    public ApprovePatientResults(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ApprovePatientResults"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Given("^The Review Page Is Active$", () -> {
            super.setup();
            scenarioContext.setReviewPage(new ReviewPage(scenarioContext.getDriver()));
        });

        When("^User expands Batch \"([^\"]*)\" from the list in the left side$", (String batch) -> {
            scenarioContext.getReviewPage().getTestBatchInList(batch).click();
            assertTrue(scenarioContext.getReviewPage().getWellSampleToApprove().isDisplayed());
        });

        When("^User expands the last Batch from the list in the left side$", () -> {
            scenarioContext.getReviewPage().getLastBatchInList().click();
            Thread.sleep(1000);
            assertTrue(scenarioContext.getReviewPage().getFirstWellSample().isDisplayed());
        });

        And("^User selects the first Well Sample in the list$", () ->
            scenarioContext.getReviewPage().getFirstWellSample().click()
        );

        Then("^Select the first Well to approve$", () -> {
            scenarioContext.getReviewPage().getWellSampleToApprove().click();
            nextSampleLabel = scenarioContext.getReviewPage().getFollowingSample().getText();
        });

        And("^Verify Approve Icon on the bottom right side of the screen$", () ->
            assertTrue(scenarioContext.getReviewPage().getApproveCheckIcon().isDisplayed())
        );

        Then("^Click on the Approve Icon$", () ->
            scenarioContext.getReviewPage().getApproveCheckIcon().click()
        );

        And("^Verify check mark at the top of the sample icon in result$", () -> {
            assertTrue(scenarioContext.getReviewPage().getResultApproveIcon().isEnabled());
            assertTrue(scenarioContext.getReviewPage().getApprovedTextResult().isEnabled());
        });

        Then("^Verify check mark at the top of the sample icon in list$", () ->
            assertTrue(scenarioContext.getReviewPage().getSampleValidatedList().isEnabled())
        );

        And("^Verify Disapprove Icon is displayed on the bottom right side of the screen$", () ->
            assertTrue(scenarioContext.getReviewPage().getDisapproveIcon().isEnabled())
        );

        Then("^Verify Edit Icon in result is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getEditIconResult().isDisplayed())
        );

        And("^Verify the next sample in list is selected$", () ->
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleByLabel(nextSampleLabel).isEnabled())
        );

        Then("^Select the first Well to disapprove$", () ->
            scenarioContext.getReviewPage().getWellSampleToDisapprove().click()
        );

        Then("^Click on the Disapprove Icon$", () ->
            scenarioContext.getReviewPage().getDisapproveIcon().click()
        );

        And("^Verify Positive, Negative or Rejected icon in result$", () ->
            assertTrue(scenarioContext.getReviewPage().getButtonsResults().isEnabled())
        );

        Then("^Verify Positive, Negative or Rejected icon in list$", () ->
            assertTrue(scenarioContext.getReviewPage().getSampleIconList().isEnabled())
        );

        Then("^Select the last Well in list to approve$", () ->
            scenarioContext.getReviewPage().getLastSampleToApprove().click()
        );

        And("^The Slide Preview has \"([^\"]*)\" Wells in Mixed Status$", (String well) ->
            assertEquals(Integer.parseInt(well), scenarioContext.getReviewPage().getSlidePreviewMixedStatus().size())
        );
    }
}