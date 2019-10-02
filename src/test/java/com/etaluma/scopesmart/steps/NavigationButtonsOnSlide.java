package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import static junit.framework.TestCase.*;

public class NavigationButtonsOnSlide extends BaseStep {

    String nextSampleLabel;
    String previousSampleLabel;

    public NavigationButtonsOnSlide(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@NavigationButtonsOnSlide"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Select the first Well in list$", () -> {
            scenarioContext.getReviewPage().getFirstWellSample().click();
            nextSampleLabel = scenarioContext.getReviewPage().getFollowingSample().getText();
        });

        Then("^Select the second Well in list$", () ->
            scenarioContext.getReviewPage().getSecondWellSample().click()
        );

        Then("^Select the last Well in list$", () -> {
            scenarioContext.getReviewPage().getLastWellSample().click();
            previousSampleLabel = scenarioContext.getReviewPage().getPreviousSample().getText();
        });

        And("^Verify the following sample in list is selected$", () -> {
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleByLabel(nextSampleLabel).isDisplayed());
            previousSampleLabel = scenarioContext.getReviewPage().getPreviousSample().getText();
        });

        And("^Verify the previous sample in list is selected$", () -> {
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleByLabel(previousSampleLabel).isDisplayed());
            nextSampleLabel = scenarioContext.getReviewPage().getFollowingSample().getText();
        });

        And("^Verify Forward Arrow is enabled$", () ->
            assertFalse(scenarioContext.getReviewPage().getNextArrow().getAttribute("class").contains("disabled"))
        );

        And("^Verify Backward Arrow is enabled$", () ->
            assertFalse(scenarioContext.getReviewPage().getBackArrow().getAttribute("class").contains("disabled"))
        );

        And("^Verify Backward Arrow is disabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getBackArrow().getAttribute("class").contains("disabled"))
        );

        And("^Verify Forward Arrow is disabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getNextArrow().getAttribute("class").contains("disabled"))
        );

        Then("^Click on the Forward Arrow$", () ->
            scenarioContext.getReviewPage().getNextArrow().click()
        );

        Then("^Click on the Backward Arrow$", () ->
            scenarioContext.getReviewPage().getBackArrow().click()
        );

        Then("^Press the Down Arrow in the keyboard$", () ->
            new Actions(scenarioContext.getDriver()).sendKeys(Keys.ARROW_DOWN).perform()
        );

        Then("^Press the Up Arrow in the keyboard$", () ->
            new Actions(scenarioContext.getDriver()).sendKeys(Keys.ARROW_UP).perform()
        );
    }
}