package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static junit.framework.TestCase.*;

public class DigitalZoomImage extends BaseStep {

    public DigitalZoomImage(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@DigitalZoomImage"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        When("^User position the mouse over the image$", () -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            actions.moveToElement(scenarioContext.getReviewPage().getActiveSampleImage()).click().perform();
        });

        Then("^Zoom In Every Sample Image to 2x$", () -> {
            for (WebElement dot: scenarioContext.getReviewPage().getSampleImagesDots()) {
                dot.click();
                Thread.sleep(2000);
                while (!scenarioContext.getReviewPage().getActiveSampleImage().getAttribute("style").contains("scale(2")) {
                    scenarioContext.getReviewPage().getZoomInButton().click();
                }
                assertTrue(scenarioContext.getReviewPage().getActiveSampleImage().getAttribute("style").contains("scale(2"));
            }
        });

        And("^Zoom Out Every Sample Image to Original Size$", () -> {
            for (WebElement dot: scenarioContext.getReviewPage().getSampleImagesDots()) {
                dot.click();
                Thread.sleep(2000);
                while (!scenarioContext.getReviewPage().getActiveSampleImage().getAttribute("style").contains("scale(0.45")) {
                    scenarioContext.getReviewPage().getZoomOutButton().click();
                }
                assertTrue(scenarioContext.getReviewPage().getActiveSampleImage().getAttribute("style").contains("scale(0.45"));
            }
        });
    }
}