package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.*;

public class ReferenceLibraryInformation extends BaseStep {

    public ReferenceLibraryInformation(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ReferenceLibraryInformation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@ReferenceLibraryPreview"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Verify that the Information Icon is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getPatternInformationIcon().isEnabled())
        );

        When("^User clicks on the Information Icon$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getPatternInformationIcon());
        });

        Then("^Verify that the Information Dropdown is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getPatternInformationDropdown().isEnabled())
        );

        When("^User clicks on the Information Dropdown$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getPatternInformationDropdown());
        });

        Then("^Verify Overlay does not show the Information$", () ->
            assertFalse(scenarioContext.getReviewPage().getPatternLibraryOverlay().getAttribute("class").contains("expanded"))
        );

        And("^Verify icon \"([^\"]*)\" is present in Reference Library Information$", (String icon) ->
            assertTrue(scenarioContext.getReviewPage().getGenericIcon(icon).isDisplayed())
        );

        When("^User clicks on File View icon in Reference Library Information$", () ->
            scenarioContext.getReviewPage().getGenericIcon("file-view").click()
        );
    }
}