package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import java.util.List;
import static junit.framework.TestCase.*;

public class TestBatchSummary extends BaseStep {

    public TestBatchSummary(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@TestBatchSummary"}, (Scenario scenario) ->
                super.teardown(scenario)
        );

        When("^The Test Batches list in the left side is collapsed$", () ->
            assertTrue(scenarioContext.getReviewPage().getAllWellSamples().isEmpty())
        );

        Then("^The first Test Batch is selected$", () ->
            assertTrue(scenarioContext.getReviewPage().getTestBatchesList().get(0).getAttribute("style").contains("rgb(231, 238, 244)"))
        );

        Then("^Verify \"([^\"]*)\" in Batch info section$", (String text) ->
            assertTrue(scenarioContext.getReviewPage().getTestBatchesList().get(0).getText().contains(text))
        );

        Then("^User selects the Test Batch \"([^\"]*)\" in left list$", (String batch) -> {
            Thread.sleep(500);
            scenarioContext.getReviewPage().getTestBatchByName(batch).click();
            Thread.sleep(500);
            scenarioContext.getReviewPage().getTestBatchByName(batch).click();
            Thread.sleep(500);
            scenarioContext.getReviewPage().getTestBatchByName(batch).click();
        });

        And("^Verify the next messages in Test Batch Summary$", (DataTable entries) -> {
            List<String> list = entries.asList();
            for(String keyWord: list) {
                assertTrue(scenarioContext.getReviewPage().getTextInSummary(keyWord).isDisplayed());
            }
        });

        Then("^Verify the next icons by label$", (DataTable entries) -> {
            List<String> list = entries.asList();
            for(String label: list) {
                assertTrue(scenarioContext.getReviewPage().getIconByLabel(label).isDisplayed());
            }
        });

        And("^Verify the next icons by type$", (DataTable entries) -> {
            List<String> list = entries.asList();
            for(String type: list) {
                assertTrue(scenarioContext.getReviewPage().getIconByType(type).isDisplayed());
            }
        });
    }
}