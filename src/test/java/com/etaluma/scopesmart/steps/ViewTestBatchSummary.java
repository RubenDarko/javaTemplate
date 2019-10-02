package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import static org.junit.Assert.*;

public class ViewTestBatchSummary extends BaseStep {

    public ViewTestBatchSummary(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ViewTestBatchSummary"}, (Scenario scenario) ->
                super.teardown(scenario)
        );

        When("^There Are No Slides In The Test Batch List$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getSlides().isEmpty())
        );

        Then("^The Slides Count Field Will Display 0 In Test Batch List$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getBatchCounter().contains("(0)"))
        );

        And("^The Slides Count Warning Icon Will Be Visible In Test Batch List$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getSlideCountWarningIcon().isEmpty())
        );

        Then("^The Check Mark Is Disabled In Test Batch List$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getCheckMark().isEmpty())
        );

        When("^The \"([^\"]*)\" Count Field Will Display \"([^\"]*)\"$", (String summaryItem, String value) ->
            assertTrue(scenarioContext.getTestSetupPage().getValueFromSummaryItem(summaryItem).contains(value))
        );

        Then("^The \"([^\"]*)\" Count Warning Icon Will Be Visible$", (String summaryItem) ->
            assertTrue(scenarioContext.getTestSetupPage().isWarningIconFromSummaryItemDisplayed(summaryItem))
        );

        And("^The Positive Control Line Will Expand To Show A List Of All Positive Controls$", () -> {
            scenarioContext.getTestSetupPage().testBatchList.click();
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().positiveControlListItems).size()>=1);
        });

        And("^The Positive Control Line Will Expand To Show A List Of All Negative Controls$", () -> {
            scenarioContext.getTestSetupPage().testBatchList.click();
            assertTrue(scenarioContext.getDriver().findElements(scenarioContext.getTestSetupPage().negativeControlListItems).size()>=1);
        });

        Then("^The Check Mark For Validation Will Be Enabled$", () -> {
            scenarioContext.getTestSetupPage().approveCheckMark.click();
            assertTrue(!scenarioContext.getTestSetupPage().getCheckMark().isEmpty());
        });
    }
}