package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.HomePage;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Map;
import static junit.framework.TestCase.*;

public class UserManagement extends BaseStep {

    public UserManagement(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@UserManagement"},(Scenario scenario)->
            super.teardown(scenario)
        );

        After(new String[]{"@EditUserInformation"},(Scenario scenario)->
            super.teardown(scenario)
        );

        When("^User clicks on \"([^\"]*)\" Icon in System Configuration$", (String icon) ->
            scenarioContext.getSystemConfigurationPage().getIcon(icon).click()
        );

        And("^Verify the next Text Fields and Placeholders in User Management$", (DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> element: maps) {
                assertTrue(scenarioContext.getSystemConfigurationPage().getTextFieldByLabel(element.get("Field")).isDisplayed());
                assertEquals(scenarioContext.getSystemConfigurationPage().getTextFieldByLabel(element.get("Field")).getAttribute("placeholder"),
                        element.get("Placeholder"));
            }
        });

        And("^Verify \"([^\"]*)\" Combo Box in User Management$", (String comboBox) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getComboBoxByLabel(comboBox).isDisplayed())
        );

        Then("^Verify the next Permissions in System Configuration$", (DataTable entries) -> {
            List<String> permissions = entries.asList();
            for(String permission: permissions)
                assertTrue(scenarioContext.getSystemConfigurationPage().getPermissionByLabel(permission).isDisplayed());
        });

        Then("^Select the arrow icon for \"([^\"]*)\" row$", (String value) -> {
            int column = scenarioContext.getSystemConfigurationPage().getColumnNumber("Name");
            int row = scenarioContext.getSystemConfigurationPage().getRowNumberInColumn(value, column);
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getLinkArrowInRow(row));
            Thread.sleep(5000);
        });

        And("^Verify permission \"([^\"]*)\" is in status \"([^\"]*)\"", (String permission, String status) ->
            assertEquals(scenarioContext.getSystemConfigurationPage().getPermissionCheckbox(permission).getAttribute("value"), status)
        );

        Given("^User Reviewer Access the Home Page", () -> {
            super.setup();
            scenarioContext.setHomePage(new HomePage(scenarioContext.getDriver(),"user_reviewer","4VjXb8Fv6zWHhF!"));
        });

        Then("^Verify Users Information Table is displayed$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getUsersInformationTable().isDisplayed())
        );

        Then("^Verify the following fields are present$", (DataTable entries) -> {
            List<String> fields = entries.asList();
            for(String field: fields)
                assertTrue(scenarioContext.getSystemConfigurationPage().getUserInformationField(field).isEnabled());
        });

        Then("^User clicks on the field \"([^\"]*)\" in User Management$", (String field) ->
            scenarioContext.getSystemConfigurationPage().getUserInformationLabel(field).click()
        );

        Then("^Clean up \"([^\"]*)\" field in User Management$", (String component) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            int counter=0;
            Thread.sleep(1000);
            scenarioContext.getSystemConfigurationPage().getUserInformationField(component).click();
            while(scenarioContext.getSystemConfigurationPage().getUserInformationInputs(component).isEmpty() && counter<5) {
                scenarioContext.getSystemConfigurationPage().getUserInformationField(component).click();
                Thread.sleep(2000);
                counter++;
            }
            Thread.sleep(1000);
            while(!scenarioContext.getSystemConfigurationPage().getUserInformationInput(component).getAttribute("value").equals("")) {
                scenarioContext.getSystemConfigurationPage().getUserInformationInput(component).click();
                actions.sendKeys(Keys.LEFT)
                       .sendKeys(Keys.DELETE)
                       .perform();
                Thread.sleep(100);
            }
            scenarioContext.getSystemConfigurationPage().getButtonByIcon("checkmark").click();
        });

        When("^User enters \"([^\"]*)\" in \"([^\"]*)\" in User Management$", (String text, String component) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            int counter=0;
            Thread.sleep(1000);
            scenarioContext.getSystemConfigurationPage().getUserInformationField(component).click();
            while(scenarioContext.getSystemConfigurationPage().getUserInformationInputs(component).isEmpty() && counter<5) {
                scenarioContext.getSystemConfigurationPage().getUserInformationField(component).click();
                Thread.sleep(2000);
                counter++;
            }
            for(int i=0; i<text.length(); i++) {
                actions.sendKeys(Character.toString(text.charAt(i)))
                        .perform();
                Thread.sleep(100);
            }
        });

        Then("^Verify the values in following components in User Management$", (DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> map: maps) {
                assertEquals(map.get("Value"), scenarioContext.getSystemConfigurationPage().getUserInformationField(map.get("Component")).getText());
            }
        });
    }
}