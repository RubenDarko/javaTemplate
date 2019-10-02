package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

public class ArchiveTestBatch extends BaseStep {

    public ArchiveTestBatch(ScenarioContext scenarioContext) {

        this.scenarioContext = scenarioContext;

        After(new String[]{"@ArchiveTestBatch"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^User clicks on Archive$", () -> {
            Thread.sleep(1000);
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getMainArchiveButton());
        });

        Then("^Verify that Archive Window is opened$", () -> {
            Thread.sleep(3000);
            assertTrue(scenarioContext.getReviewPage().getArchiveWindow().isDisplayed());
        });

        And("^Verify Archive icon is present and disabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getArchiveWindowButton().getAttribute("class").contains("disabled"))
        );

        When("^User clicks on the \"([^\"]*)\" button checkbox$", (String button) ->
            scenarioContext.getReviewPage().getArchiveButtonByName(button).click()
        );

        Then("^Verify \"([^\"]*)\" checkbox is selected$", (String button) ->
            assertEquals(scenarioContext.getReviewPage().getArchiveButtonCheckboxByName(button).getAttribute("checked"), "true")
        );

        And("^Verify \"([^\"]*)\" checkbox is not selected$", (String button) ->
            assertNull(scenarioContext.getReviewPage().getArchiveButtonCheckboxByName(button).getAttribute("checked"))
        );

        Then("^Approve all wells in selected test batch$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            for(WebElement wellSample: scenarioContext.getReviewPage().getAllWellSamples()) {
                wellSample.click();
                Thread.sleep(3000);
                assertTrue(scenarioContext.getReviewPage().getSelectedSampleByLabel(wellSample.getText()).isDisplayed());
                if (scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class").contains("reimage")) {
                    System.out.println(wellSample.getText() + " " + scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class"));
                    scenarioContext.getReviewPage().getUnTagReImageIcon().click();
                    Thread.sleep(1000);
                    System.out.println(wellSample.getText() + " " + scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class"));
                }
                if (scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class").contains("uncertain")) {
                    if (wellSample.getText().contains("Positive"))
                        scenarioContext.getReviewPage().getResultButtonByIcon("positive").click();
                    else
                        scenarioContext.getReviewPage().getResultButtonByIcon("negative").click();
                    System.out.print(wellSample.getText() + " ");
                    System.out.println(scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class") + "Change");
                } else {
                    System.out.print(wellSample.getText() + " ");
                    System.out.println(scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class") + "Keep");
                }
                if (!scenarioContext.getReviewPage().getApproveCheckIcons().isEmpty())
                    scenarioContext.getReviewPage().getApproveCheckIcon().click();
            }
            while(scenarioContext.getReviewPage().getArchiveButtons().isEmpty()) {
                Thread.sleep(1000);
                scenarioContext.getReviewPage().getLastBatchName().click();
            }
            System.out.println("All wells were approved");
            Thread.sleep(1000);
        });

        And("^Verify Archive icon is present and enabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getArchiveWindowButton().getAttribute("class").contains("enabled"))
        );

        When("^User clicks on Archive icon$", () ->
            scenarioContext.getReviewPage().getArchiveWindowButton().click()
        );
    }
}