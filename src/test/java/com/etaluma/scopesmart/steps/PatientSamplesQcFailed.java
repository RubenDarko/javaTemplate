package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static junit.framework.TestCase.*;

public class PatientSamplesQcFailed extends BaseStep {

    public String selectedSample;
    public String failedSample;
    public int totalFailedSamples;

    public PatientSamplesQcFailed(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@PatientSamplesQcFailed"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@IconsFromFailedImagingSession"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Select the first Well failed QC in list$", () -> {
            Thread.sleep(2000);
            scenarioContext.getReviewPage().getWellSampleFailedQc().click();
        });

        And("^Verify QC Failed Icon$", () ->
            assertTrue(scenarioContext.getReviewPage().getQcFailedIcon().isDisplayed())
        );

        And("^Verify buttons for Failed QC samples$", () -> {
            for (WebElement button : scenarioContext.getReviewPage().getFailedQcSamplesButtons()) {
                assertTrue(button.getText().contains("Ctrl: Slide ID:"));
                assertTrue(button.getText().contains("- Well #:"));
            }
        });

        And("^Verify navigation when Different Wells are selected$", () -> {
            scenarioContext.getReviewPage().getFailedQcSamplesButtons().get(0).click();
            Thread.sleep(2000);
            scenarioContext.getReviewPage().getWellSampleFailedQc().click();
        });

        And("^Verify Failed Icon and Label$", () ->
            assertFalse(scenarioContext.getReviewPage().getFailedSamplesIconLabel().isEmpty())
        );

        And("^Verify user cannot interact with page components behind the Overlay$", () -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            Thread.sleep(2000);
            actions.moveToElement(scenarioContext.getReviewPage().getButtonByName("DAPI")).click().perform();
            assertTrue(scenarioContext.getReviewPage().getButtonByName("FITC").getAttribute("class").contains("selected"));
            selectedSample = scenarioContext.getReviewPage().getSelectedSample().getText();
            actions.moveToElement(scenarioContext.getReviewPage().getNextArrow()).click().perform();
            assertEquals(scenarioContext.getReviewPage().getSelectedSample().getText(),selectedSample);
        });

        And("^Select \"([^\"]*)\" button in Result$", (String result) -> {
            if (!scenarioContext.getReviewPage().getWellSamplesFailed().isEmpty())
                failedSample = scenarioContext.getReviewPage().getWellSampleFailedQc().getText();
            totalFailedSamples = scenarioContext.getReviewPage().getWellSamplesFailed().size();
            scenarioContext.getReviewPage().getResultButtonByIcon(result).click();
            Thread.sleep(2000);
        });

        Then("^Verify that well result has not changed$", () ->
            assertEquals(failedSample, scenarioContext.getReviewPage().getWellSampleFailedQc().getText())
        );

        When("^User clicks on the first failed well$", () -> {
            scenarioContext.getReviewPage().getFailedQcSamplesButtons().get(0).click();
            Thread.sleep(2000);
        });

        And("^Verify that all related wells has changed$", () ->
            assertNotSame(totalFailedSamples, scenarioContext.getReviewPage().getWellSamplesFailed().size())
        );

        And("^Verify Tooltip when mouse over the failed sample$", () ->
            assertFalse(scenarioContext.getReviewPage().getTooltips().isEmpty())
        );
    }
}