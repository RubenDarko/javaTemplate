package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.TestBatchPage;
import cucumber.api.Scenario;
import static org.junit.Assert.*;

public class HowToScanInstructions extends BaseStep {

    public HowToScanInstructions(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@HowToScanInstructions"},(Scenario scenario)->
            super.teardown(scenario)
        );

        Then("^User Verifies How To Scan In Test Batch$", () -> {
            assertTrue(scenarioContext.getTestSetupPage().isScanKitMessageDisplayed());
            assertTrue(scenarioContext.getTestSetupPage().isScanKitImageDisplayed());
        });

        Then("^User Verifies How To Scan In Slide$", () -> {
            scenarioContext.getTestSetupPage().setTestBatchPage(new TestBatchPage(scenarioContext.getDriver()));
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().isScanSlideMessageDisplayed());
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().isScanSlideImageDisplayed());
        });

        Then("^User Verifies How To Scan In Samples$", () -> {
            assertTrue(scenarioContext.getTestSetupPage().getTestSummary().isScanSampleMessageDisplayed());
            assertTrue(scenarioContext.getTestSetupPage().getTestSummary().isScanSampleImageDisplayed());
        });
        
        Then("^User Verifies How To Scan Disappear In Test Batch$", () -> {
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().scanKitImage).isEmpty());
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().scanKitImage).isEmpty());
        });

        And("^User Verifies How To Scan Disappear In Slide$", () -> {
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().getTestBatchPage().scanSlideMessage).isEmpty());
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().getTestBatchPage().scanSlideImage).isEmpty());
        });

        And("^User Verifies How To Scan Disappear In Samples$", () -> {
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().getTestSummary().scanSampleMessage).isEmpty());
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().getTestSummary().scanSampleImage).isEmpty());
        });
    }
}