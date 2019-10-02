package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.WebElement;
import static junit.framework.TestCase.*;

public class ReferenceLibraryPatterns extends BaseStep {

    public ReferenceLibraryPatterns(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ReferenceLibraryPatterns"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        When("^User selects \"([^\"]*)\" in the Reference Library combo box$", (String pattern) -> {
            Thread.sleep(3000);
            assertTrue(scenarioContext.getReviewPage().getReferenceLibrary().getText().contains(pattern));
            scenarioContext.getReviewPage().selectPattern(pattern);
        });

        Then("^Verify that the Plus Icon is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getAddPatternIcon().isDisplayed())
        );

        And("^Verify message \"([^\"]*)\" is present$", (String message) ->
            assertTrue(scenarioContext.getReviewPage().getGenericObject(message).isDisplayed())
        );

        And("^Verify that the Pattern Image is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getPatternPicture().isDisplayed())
        );

        Then("^Close the Pattern Library Overlay$", () ->
            scenarioContext.getReviewPage().getClosePatternOverlay().click()
        );

        Then("^Navigate through all Images in the Pattern$", () -> {
            for (WebElement imageList: scenarioContext.getReviewPage().getReferenceImagesList()) {
                imageList.click();
                assertTrue(scenarioContext.getReviewPage().getPatternPicture().isDisplayed());
            }
        });
    }
}