package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;

public class MultipleImagingSessions extends BaseStep {

    public MultipleImagingSessions(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@MultipleImagingSessions"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Pending Implementation$", () ->
            scenarioContext.getReviewPage().getWellSampleFailedQc().click()
        );
    }
}