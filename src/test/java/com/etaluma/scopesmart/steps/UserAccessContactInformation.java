package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class UserAccessContactInformation extends BaseStep {

    public UserAccessContactInformation(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@UserAccessContactInformation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^clicks on Contact Info$", () ->
            scenarioContext.getTestSetupPage().selectMenuItemByText("Contact Info")
        );

        And("^Verify the following fields have information$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for (Map<String, String> item: list) {
                String field = item.get("field");
                assertFalse(scenarioContext.getTestSetupPage().getContactInformationField(field).isEmpty());
            }
        });
    }
}