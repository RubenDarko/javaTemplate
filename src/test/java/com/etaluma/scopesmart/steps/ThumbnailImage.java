package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static junit.framework.TestCase.*;

public class ThumbnailImage extends BaseStep {

    public ThumbnailImage(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ThumbnailImage"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        When("^User clicks on the button \"([^\"]*)\"$", (String button) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();",scenarioContext.getReviewPage().getButtonByName(button));
            Thread.sleep(500);
            executor.executeScript("arguments[0].click();",scenarioContext.getReviewPage().getButtonByName(button));
            Thread.sleep(500);
            executor.executeScript("arguments[0].click();",scenarioContext.getReviewPage().getButtonByName(button));
            Thread.sleep(3000);
        });

        Then("^Verify button \"([^\"]*)\" is selected$", (String button) ->
            assertTrue(scenarioContext.getReviewPage().getButtonByName(button).getAttribute("class").contains("selected"))
        );

        Then("^Verify button \"([^\"]*)\" is not selected$", (String button) ->
            assertFalse(scenarioContext.getReviewPage().getButtonByName(button).getAttribute("class").contains("selected"))
        );

        Then("^Verify Report Image for all Slides$", () -> {
            for (WebElement image: scenarioContext.getReviewPage().getSampleImagesDots()) {
                image.click();
                if (!scenarioContext.getReviewPage().getIconInPatternsPanel("reportimage").getAttribute("class").contains("on")) {
                    scenarioContext.getReviewPage().getIconInPatternsPanel("reportimage").click();
                    assertTrue(scenarioContext.getReviewPage().getIconInPatternsPanel("reportimage").getAttribute("class").contains("on"));
                }
            }
        });
    }
}