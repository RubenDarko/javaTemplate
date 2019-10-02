package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import static org.junit.Assert.*;

public class TestBatchFilter extends BaseStep {

    public TestBatchFilter(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@TestBatchFilter"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@RefreshContents"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@ClearCurrentFilter"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Verify that the Filter Icon is present$", () ->
            scenarioContext.getReviewPage().getFilterIcon().isDisplayed()
        );

        When("^User place the mouse over the Filter Icon$", () -> {
            Thread.sleep(1000);
            Actions actions = new Actions(scenarioContext.getDriver());
            actions.moveToElement(scenarioContext.getReviewPage().getFilterIcon()).perform();
        });

        Then("^Verify that the Filter Tooltip has the message \"([^\"]*)\"$", (String message) ->
            assertTrue(scenarioContext.getReviewPage().validateTooltipMessage(scenarioContext.getReviewPage().getFilterIcon(), message))
        );

        Then("^Verify Tooltip in \"([^\"]*)\" Icon has the message \"([^\"]*)\"$", (String icon, String message) ->
            assertTrue(scenarioContext.getReviewPage().validateTooltipMessage(scenarioContext.getReviewPage().getIconInPatternsPanel(icon), message))
        );

        When("^User clicks on the Filter Icon$", () ->
            scenarioContext.getReviewPage().getFilterIcon().click()
        );

        Then("^Verify that the Test Batch List Filter Window is present$", () ->
            assertFalse(scenarioContext.getReviewPage().getFilterWindowPopOver().isEmpty())
        );

        And("^Verify that the Test Batch List Filter Window has the default values$", () -> {
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Positive Control").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Negative Control").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Sample").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Positive").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Negative").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Uncertain").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Rejected").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Validated").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Confirmed").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Unconfirmed").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("On").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Off").isSelected());
            assertTrue(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("All").isSelected());
            assertFalse(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("None").isSelected());
            assertFalse(scenarioContext.getReviewPage().getListFilterWindowInputByLabel("Specific Pattern:").isSelected());
        });

        When("^User uncheck all the checkboxes from the Test Batch List Filter Window$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Positive Control", "Sample Type"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Negative Control", "Sample Type"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Sample", "Sample Type"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Positive", "Result"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Negative", "Result"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Uncertain", "Result"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Rejected", "Result"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Validated", "Status"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Confirmed", "Status"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Unconfirmed", "Status"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("On", "Manual Imaging Tag"));
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter("Off", "Manual Imaging Tag"));
        });

        And("^User clicks on the Reset Filters Button$", () ->
            scenarioContext.getReviewPage().getResetFiltersButton().click()
        );

        When("^User clicks on the Test Batch List Filter Cancel Button$", () ->
            scenarioContext.getReviewPage().getFilterWindowCancelButton().click()
        );

        Then("^Verify that the Test Batch List Filter Window is Closed$", () ->
            assertTrue(scenarioContext.getReviewPage().getFilterWindowPopOver().isEmpty())
        );

        Then("^Verify that the Warning Icon for \"([^\"]*)\" is present$", (String filter) ->
            assertFalse(scenarioContext.getReviewPage().getFilterWindowWarningIcon(filter).isEmpty())
        );

        And("^Verify that the Apply Filters Button is disabled$", () ->
            assertFalse(scenarioContext.getReviewPage().getApplyFiltersButton().isEnabled())
        );

        Then("^Verify that the Warning Icon for \"([^\"]*)\" is not present$", (String filter) ->
            assertTrue(scenarioContext.getReviewPage().getFilterWindowWarningIcon(filter).isEmpty())
        );

        And("^The Filter \"([^\"]*)\" for the Option \"([^\"]*)\" is checked$", (String option, String filter) ->
            assertTrue(scenarioContext.getReviewPage().getOptionInFilter(option, filter).isSelected())
        );

        And("^User selects the Option \"([^\"]*)\" In Patterns Dropdown$", (String option) ->
            scenarioContext.getReviewPage().getSpecificPatternDropdownOption(option).click()
        );

        When("^User clicks on the Apply Filters Button$", () ->
            scenarioContext.getReviewPage().getApplyFiltersButton().click()
        );

        And("^Verify that the Reset Filter Icon is not clickable$", () ->
            assertTrue(scenarioContext.getReviewPage().getGenericIcon("filter-cancel").findElement(By.xpath("./..")).getAttribute("style").contains("opacity"))
        );

        And("^Verify that the Reset Filter Icon is clickable$", () ->
            assertFalse(scenarioContext.getReviewPage().getGenericIcon("filter-cancel").findElement(By.xpath("./..")).getAttribute("style").contains("opacity"))
        );

        And("^Verify that the Filter Icon is clickable$", () ->
            assertFalse(scenarioContext.getReviewPage().getFilterButton().findElement(By.xpath("./..")).getAttribute("style").contains("opacity"))
        );
    }
}