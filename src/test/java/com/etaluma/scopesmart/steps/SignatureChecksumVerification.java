package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import static org.junit.Assert.*;

public class SignatureChecksumVerification extends BaseStep {

    public SignatureChecksumVerification(ScenarioContext scenarioContext){

        this.scenarioContext=scenarioContext;

        After(new String[]{"@SignatureChecksumVerification"},(Scenario scenario)->
            super.teardown(scenario)
        );

        Then("^Verify signature field is valid in test_batch_aud DB table$", () ->
            assertTrue(!database.getTestBatchAud().get(database.getTestBatchAud().size()-1).get("signature").toString().isEmpty())
        );

        Then("^Verify signature field is valid in slide_aud DB table$", () -> {
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            assertTrue(!database.getSlideAud().get(database.getSlideAud().size()-1).get("signature").toString().isEmpty());
        });

        Then("^Verify signature field is valid in well_sample_aud DB table$", () ->
            assertTrue(!database.getWellSampleAud().get(database.getWellSampleAud().size()-1).get("signature").toString().isEmpty())
        );
    }
}