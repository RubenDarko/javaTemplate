package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.TestBatchPage;
import cucumber.api.Scenario;
import static org.junit.Assert.*;

public class AddSlideToTestBatchHandHeldReader extends BaseStep {

    private String barcode;

    public AddSlideToTestBatchHandHeldReader(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@AddSlideToTestBatchHandHeldReader"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^User Triggers A \"([^\"]*)\" Slide Scan$", (String barcode) -> {
            this.barcode=barcode;
            scenarioContext.getTestSetupPage().scanTestBatch(this.barcode);
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().setTestBatchPage(new TestBatchPage(scenarioContext.getDriver()));
        });

        And("^the Slide is displayed in the Test Batch's Slide List$", () -> {
            assertTrue(!scenarioContext.getTestSetupPage().getTestBatchesList().isEmpty());
            assertEquals(scenarioContext.getTestSetupPage().getSelectedSlide().getText().length(),6);
        });

        And("^the Slide is selected to display Slide details with no data yet$", () ->
            assertTrue(!scenarioContext.getTestSetupPage().getSlideEmptySamples().isEmpty())
        );

        When("^the scan data is determined to not be for a Slide$", () ->
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("No well is currently selected.").isEmpty())
        );

        Then("^the scan is not processed$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getSlides().isEmpty())
        );

        And("^the Slide Type does not match the Slide Type for the Test Batch$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getAlertWithText("Error: The given Slide Type is not supported by the current Kit.").isEmpty())
        );

        And("^the Slide ID is already associated with another Test Batch$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getAlertWithText("Error: Slide barcode is already in use.").isEmpty())
        );

        Then("^the scan data is discarded$", () ->
            assertFalse(!scenarioContext.getTestSetupPage().getSlides().isEmpty())
        );

        When("^click on Test Batch list$", () -> {
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            scenarioContext.getTestSetupPage().testBatchList.click();
        });

        When("^User clicks on the selected Test Batch$", () -> {
            scenarioContext.getTestSetupPage().getSelectedTestBatch().get(0).click();
            Thread.sleep(500);
            scenarioContext.getTestSetupPage().getSelectedTestBatch().get(0).click();
            Thread.sleep(500);
            scenarioContext.getTestSetupPage().getSelectedTestBatch().get(0).click();
            Thread.sleep(500);
        });

        And("^User Approves the Test Batch$", () -> {
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getApproveTestBatchCheckMark().click();
        });

        And("^User Unlocks the Test Batch$", () -> {
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getEditTestBatchIcon().click();
        });
    }
}