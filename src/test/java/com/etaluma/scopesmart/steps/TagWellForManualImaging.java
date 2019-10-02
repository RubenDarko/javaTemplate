package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import static junit.framework.TestCase.*;

public class TagWellForManualImaging extends BaseStep {

    public TagWellForManualImaging(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@TagWellForManualImaging"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Select the first well by icon \"([^\"]*)\" in list$", (String icon) -> {
            assertTrue(scenarioContext.getReviewPage().getElementInSampleListByIcon(icon).isEnabled());
            scenarioContext.getReviewPage().getElementInSampleListByIcon(icon).click();
        });

        And("^Verify Re-Image Icon on the bottom right side of the screen$", () ->
            assertTrue(scenarioContext.getReviewPage().getReImageIcon().isEnabled())
        );

        When("^User clicks on Re-Image Icon$", () ->
            scenarioContext.getReviewPage().getReImageIcon().click()
        );

        Then("^Verify \"([^\"]*)\" Icon in Sample List$", (String icon) ->
            assertTrue(scenarioContext.getReviewPage().getElementInSampleListByIcon(icon).isDisplayed())
        );

        And("^Verify \"([^\"]*)\" Icon in Slide Preview$", (String icon) ->
            assertTrue(scenarioContext.getReviewPage().getElementInSlidePreviewByIcon(icon).isDisplayed())
        );

        When("^User clicks on Un Tag Re-Image Icon$", () ->
            scenarioContext.getReviewPage().getUnTagReImageIcon().click()
        );

        And("^Verify Re-Image Icon is disabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getReImageIcon().getAttribute("class").contains("disabled"))
        );
    }
}