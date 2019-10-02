package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.HomePage;
import com.etaluma.scopesmart.pages.SystemConfigurationPage;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class ConfigurationGeneral extends BaseStep {

    public ConfigurationGeneral(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@ConfigurationGeneral"},(Scenario scenario)->
            super.teardown(scenario)
        );

        Given("^The Home Page Is Active$", () -> {
            super.setup();
            scenarioContext.setHomePage(new HomePage(scenarioContext.getDriver()));
        });

        When("^User clicks on the Settings Icon$", () ->
            scenarioContext.getHomePage().getSettingsIcon().click()
        );

        Then("^Select \"([^\"]*)\" option in Dropdown Menu$", (String option) ->
            scenarioContext.getHomePage().getMenuOption(option).click()
        );

        When("^User expands \"([^\"]*)\" option in System Menu$", (String option) -> {
            scenarioContext.setSystemConfigurationPage(new SystemConfigurationPage(scenarioContext.getDriver()));
            scenarioContext.getSystemConfigurationPage().expandSystemMenu(option);
            Thread.sleep(1000);
        });

        Then("^Select \"([^\"]*)\" option in System Menu$", (String option) ->
            scenarioContext.getSystemConfigurationPage().getSubMenuOption(option).click()
        );

        And("^Verify the next messages in System Configuration$", (DataTable entries) -> {
            List<String> list = entries.asList();
            for(String keyWord: list) {
                assertTrue(scenarioContext.getSystemConfigurationPage().getTextInSummary(keyWord).isDisplayed());
            }
        });

        And("^Click on component \"([^\"]*)\" in System Screen$", (String component) -> {
            scenarioContext.getSystemConfigurationPage().getComponentByText(component).click();
            Thread.sleep(200);
            scenarioContext.getSystemConfigurationPage().getComponentByText(component).click();
        });

        Then("^Verify that the default value in \"([^\"]*)\" is empty$", (String component) ->
            assertEquals(scenarioContext.getSystemConfigurationPage().getComponentByText(component).getText(), "")
        );

        Then("^Verify \"([^\"]*)\" button is present$", (String button) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getButtonByIcon(button).isDisplayed())
        );

        Then("^User clicks on the \"([^\"]*)\" button in System Screen$", (String button) ->
            scenarioContext.getSystemConfigurationPage().getButtonByIcon(button).click()
        );

        When("^User enters \"([^\"]*)\" in \"([^\"]*)\"$", (String text, String component) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            int counter=0;
            Thread.sleep(1000);
            scenarioContext.getSystemConfigurationPage().getLabel(component).click();
            while(scenarioContext.getSystemConfigurationPage().getTextAreasByComponent(component).isEmpty() && counter<5) {
                scenarioContext.getSystemConfigurationPage().getComponentByText(component).click();
                Thread.sleep(2000);
                counter++;
            }
            for(int i=0; i<text.length(); i++) {
                actions.sendKeys(Character.toString(text.charAt(i)))
                       .perform();
                Thread.sleep(100);
            }
        });

        Then("^Click on \"([^\"]*)\" button in System Screen$", (String button) ->
            scenarioContext.getSystemConfigurationPage().getButtonByIcon(button).click()
        );

        And("^Verify the string size is \"([^\"]*)\" in \"([^\"]*)\" field$", (String size, String component) ->
            assertEquals(Integer.parseInt(size),scenarioContext.getSystemConfigurationPage().getComponentByText(component).getText().length())
        );

        Then("^Clean up \"([^\"]*)\" field in System Screen$", (String component) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            int counter=0;
            Thread.sleep(1000);
            scenarioContext.getSystemConfigurationPage().getLabel(component).click();
            while(scenarioContext.getSystemConfigurationPage().getTextAreasByComponent(component).isEmpty() && counter<5) {
                scenarioContext.getSystemConfigurationPage().getComponentByText(component).click();
                Thread.sleep(2000);
                counter++;
            }
            Thread.sleep(1000);
            while(!scenarioContext.getSystemConfigurationPage().getTextAreaByComponent(component).getText().isEmpty()) {
                scenarioContext.getSystemConfigurationPage().getTextAreaByComponent(component).click();
                actions.sendKeys(Keys.LEFT)
                       .sendKeys(Keys.DELETE)
                       .perform();
                Thread.sleep(100);
            }
            scenarioContext.getSystemConfigurationPage().getButtonByIcon("checkmark").click();
        });

        Then("^Verify the values in following components$", (DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> map: maps) {
                assertEquals(map.get("Value"), scenarioContext.getSystemConfigurationPage().getComponentByText(map.get("Component")).getText());
            }
        });

        Then("^Verify the values in dropdown \"([^\"]*)\"$", (String dropdown, DataTable entries) -> {
            List<String> values = entries.asList();
            int counter=0;
            scenarioContext.getSystemConfigurationPage().getLabel(dropdown).click();
            while(scenarioContext.getSystemConfigurationPage().getDropdownsByComponent(dropdown).isEmpty() && counter<5) {
                scenarioContext.getSystemConfigurationPage().getComponentByText(dropdown).click();
                Thread.sleep(2000);
                counter++;
            }
            for(WebElement option: scenarioContext.getSystemConfigurationPage().getOptionsInDropdown(dropdown)) {
                assertTrue(values.contains(option.getText().trim()));
                System.out.println("|" + option.getText().trim() + "| Validated");
            }
        });
    }
}