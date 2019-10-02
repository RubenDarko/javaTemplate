package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.ReviewPage;
import cucumber.api.Scenario;
import static junit.framework.TestCase.*;

public class ConfirmPatientResults extends BaseStep {

    String nextSampleLabel;

    public ConfirmPatientResults(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ConfirmPatientResults"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Given("^User Reviewer Access the Review Page", () -> {
            super.setup();
            scenarioContext.setReviewPage(new ReviewPage(scenarioContext.getDriver(),"user_reviewer","4VjXb8Fv6zWHhF!"));
        });

        Then("^Select the first Well to confirm$", () -> {
            scenarioContext.getReviewPage().getWellSampleToConfirm().click();
            nextSampleLabel = scenarioContext.getReviewPage().getFollowingSample().getText();
        });

        And("^Verify confirm icon at the top of the sample icon in result$", () -> {
            assertTrue(scenarioContext.getReviewPage().getResultConfirmIcon().isEnabled());
            assertTrue(scenarioContext.getReviewPage().getConfirmedTextResult().isEnabled());
        });

        Then("^Verify confirm icon at the top of the sample icon in list$", () ->
            assertTrue(scenarioContext.getReviewPage().getSampleConfirmedList().isEnabled())
        );

        Then("^Select the first Well to unconfirm$", () ->
            scenarioContext.getReviewPage().getWellSampleToUnconfirm().click()
        );

        Then("^Select the last Well in list to confirm$", () ->
            scenarioContext.getReviewPage().getLastSampleToConfirm().click()
        );

        And("^Verify the next sample to confirm in list is selected$", () ->
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleByLabel(nextSampleLabel).isEnabled())
        );
    }
}