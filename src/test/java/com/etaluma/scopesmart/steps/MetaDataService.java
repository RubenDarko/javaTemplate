package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import static org.junit.Assert.*;

public class MetaDataService extends BaseStep {

    public MetaDataService(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@MetaDataService"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^User clicks on the \"([^\"]*)\" icon in Review Page$", (String icon) ->
            scenarioContext.getReviewPage().getGenericIcon(icon).click()
        );

        And("^Validate that Image Meta Data panel is displayed$", () -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getReviewPage().getImageMetaDataPanel().isDisplayed());
        });

        Then("^Verify Image Meta Data contains \"([^\"]*)\"$", (String data) ->
            assertTrue(scenarioContext.getReviewPage().getMetaDataHeader().getText().contains(data))
        );
    }
}