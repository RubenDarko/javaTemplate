package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static junit.framework.TestCase.*;

public class AddPatternToResult extends BaseStep {

    public boolean flag;

    public AddPatternToResult(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@AddPatternToResult"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Verify that the Plus Icon is disabled$", () -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getReviewPage().getAddPatternIcon().getAttribute("class").contains("disabled"));
        });

        Then("^Verify that the Plus Icon is enabled$", () -> {
            Thread.sleep(2000);
            assertFalse(scenarioContext.getReviewPage().getAddPatternIcon().getAttribute("class").contains("disabled"));
        });

        When("^User clicks on Add Pattern To Result button$", () ->
            scenarioContext.getReviewPage().getAddPatternIcon().click()
        );

        Then("^Verify Result Patterns Panel is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getResultPatternsPanel().isDisplayed())
        );

        And("^Verify Patterns Text field is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getPatternsTextField().isDisplayed())
        );

        And("^Verify \"([^\"]*)\" Combo box is displayed$", (String label) ->
            assertTrue(scenarioContext.getReviewPage().getComboBoxByLabel(label).isDisplayed())
        );

        And("^Verify \"([^\"]*)\" Icon is displayed$", (String icon) ->
            assertTrue(scenarioContext.getReviewPage().getIconInPatternsPanel(icon).isDisplayed())
        );

        And("^Verify Close Panel Icon$", () ->
            assertTrue(scenarioContext.getReviewPage().getClosePanelIcon().isEnabled())
        );

        When("^User selects \"([^\"]*)\" in \"([^\"]*)\" Combo box$", (String value, String comboBox) ->
            scenarioContext.getReviewPage().selectValueInComboBox(value, scenarioContext.getReviewPage().getComboBoxByLabel(comboBox))
        );

        When("^User clicks on \"([^\"]*)\" Icon$", (String icon) -> {
            scenarioContext.getReviewPage().getIconInPatternsPanel(icon).click();
            Thread.sleep(5000);
        });

        Then("^Verify \"([^\"]*)\" and \"([^\"]*)\" in Text field$", (String pattern, String intensity) -> {
            flag=false;
            Thread.sleep(3000);
            for (WebElement object: scenarioContext.getReviewPage().getPatternsListTextField()) {
                if(object.getText().contains(pattern) && object.getText().contains(intensity))
                    flag = true;
            }
            assertTrue(flag);
        });

        When("^User clicks on Close Panel Icon$", () ->
            scenarioContext.getReviewPage().getClosePanelIcon().click()
        );

        Then("^Verify \"([^\"]*)\" and \"([^\"]*)\" in Patterns$", (String pattern, String intensity) -> {
            flag=false;
            for (WebElement object: scenarioContext.getReviewPage().getPatternsAddedList()) {
                if(object.getText().contains(pattern) && object.getText().contains(intensity))
                    flag = true;
            }
            assertTrue(flag);
        });

        When("^User clicks on Pattern \"([^\"]*)\" with Intensity \"([^\"]*)\" in Text field$", (String pattern, String intensity) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            for (WebElement object: scenarioContext.getReviewPage().getPatternsListTextField()) {
                if(object.getText().contains(pattern) && object.getText().contains(intensity))
                    executor.executeScript("arguments[0].click();", object);
                //object.click();
            }
        });

        When("^User clicks on edit button for \"([^\"]*)\"$", (String section) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getEditIconForSection(section));
        });

        Then("^Clean up from patterns$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getEditIconForSection("Patterns"));
            assertTrue(scenarioContext.getReviewPage().getResultPatternsPanel().isDisplayed());
            for (WebElement object: scenarioContext.getReviewPage().getPatternsListTextField()) {
                object.click();
                if (!scenarioContext.getReviewPage().getIconInPatternsPanel("trash").getAttribute("class").contains("disabled"))
                    scenarioContext.getReviewPage().getIconInPatternsPanel("trash").click();
            }
        });
    }
}