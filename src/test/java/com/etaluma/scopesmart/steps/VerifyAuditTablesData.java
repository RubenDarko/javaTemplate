package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class VerifyAuditTablesData extends BaseStep {

    private Map<String, String> testBatchAudMap = new HashMap<>();
    private Map<String, String> slideAudMap = new HashMap<>();
    private Map<String, String> wellSampleAudMap = new HashMap<>();

    public VerifyAuditTablesData(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@VerifyAuditTablesData"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^Verify that fields were created in test_batch_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if(!field.equals("Field"))
                    testBatchAudMap.put(field,database.getTestBatchAud().get(0).get(field).toString());
            }
        });

        Then("^Verify that fields are valid in test_batch_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if (!field.equals("Field"))
                    assertNotEquals(testBatchAudMap.get(field), database.getTestBatchAud().get(database.getTestBatchAud().size()-1).get(field).toString());
            }
        });

        Then("^Verify that fields were created in slide_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if(!field.equals("Field"))
                    slideAudMap.put(field, database.getSlideAud().get(0).get(field).toString());
            }
        });

        Then("^Verify that fields are valid in slide_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            //scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if (!field.equals("Field"))
                    assertNotEquals(slideAudMap.get(field), database.getSlideAud().get(database.getSlideAud().size()-1).get(field).toString());
            }
        });

        Then("^Verify that fields were created in well_sample_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if(!field.equals("Field"))
                    wellSampleAudMap.put(field, database.getWellSampleAud().get(0).get(field).toString());
            }
        });

        Then("^Verify that fields are valid in well_sample_aud DB table$", (DataTable entries) -> {
            List<String> list = entries.asList(String.class);
            scenarioContext.getTestSetupPage().waitForAlertNotPresent();
            for (String field : list) {
                if (!field.equals("Field"))
                    assertNotEquals(wellSampleAudMap.get(field), database.getWellSampleAud().get(database.getWellSampleAud().size()-1).get(field).toString());
            }
        });
    }
}