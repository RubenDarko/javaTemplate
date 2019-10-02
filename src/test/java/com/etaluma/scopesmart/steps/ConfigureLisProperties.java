package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import static junit.framework.TestCase.*;

public class ConfigureLisProperties extends BaseStep {

    public ConfigureLisProperties(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ConfigureLisProperties"},(Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Click on radio button \"([^\"]*)\" in Configuration LIS$", (String radio) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getLisRadioButton(radio));
        });

        And("^Verify radio button \"([^\"]*)\" in Configuration LIS$", (String radio) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getLisRadioButton(radio).isEnabled())
        );

        And("^Verify message \"([^\"]*)\" is present in Configuration LIS$", (String message) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getObjectByLabel(message).isDisplayed())
        );

        Then("^Click on \"([^\"]*)\" toggle button$", (String toggle) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle));
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle));
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle));
            Thread.sleep(2000);
        });

        And("^Verify \"([^\"]*)\" toggle button$", (String toggle) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle).isEnabled())
        );

        Then("^Verify \"([^\"]*)\" toggle button is \"([^\"]*)\"$", (String toggle, String value) ->
            assertEquals(scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle).getAttribute("value"), value)
        );

        Then("^Verify the Test Connection button is present$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getTestConnectionButton().isDisplayed())
        );

        Then("^Verify radio button \"([^\"]*)\" is checked$", (String radio) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getLisRadioButton(radio).isSelected())
        );

        Then("^Verify radio button \"([^\"]*)\" is unchecked$", (String radio) ->
            assertFalse(scenarioContext.getSystemConfigurationPage().getLisRadioButton(radio).isSelected())
        );

        And("^Verify \"([^\"]*)\" toggle button attribute \"([^\"]*)\" is \"([^\"]*)\"$", (String toggle, String attribute, String value) ->
            assertEquals(scenarioContext.getSystemConfigurationPage().getToggleByLabel(toggle).getAttribute(attribute), value)
        );
    }
}