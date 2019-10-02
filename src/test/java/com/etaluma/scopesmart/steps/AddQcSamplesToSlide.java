package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class AddQcSamplesToSlide extends BaseStep {

    public AddQcSamplesToSlide(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;

        After(new String[]{"@AddQcSamplesToSlide"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        When("^Verify \"([^\"]*)\" Is Displayed In The Patient ID Field For Well \"([^\"]*)\"$", (String value, String well) ->
            assertTrue(scenarioContext.getTestSetupPage().isSampleIdInWellDisplayed(value,well))
        );

        When("^Change To Patient ID In Well$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for (Map<String, String> item: list) {
                String well = item.get("well");
                String patientId = item.get("Patient_ID");
                scenarioContext.getTestSetupPage().getTestBatchPage().changePatientSample(patientId,well);
            }
        });

        Then("^Verify The Dilution Ratio Field Is Disabled In Well \"([^\"]*)\"$", (String well) ->
            assertFalse(!scenarioContext.getTestSetupPage().isDilutionRatioInWellDisplayed(well))
        );

        Then("^Verify The Dilution Ratio Field Is Enabled In Well \"([^\"]*)\"$", (String well) ->
            assertTrue(scenarioContext.getTestSetupPage().isDilutionRatioInWellDisplayed(well))
        );

        And("^A Notification Is Sent To The UI Indicating Successful Changes$", () ->
            assertTrue(scenarioContext.getTestSetupPage().isSuccessAlertDisplayed())
        );

        Then("^User Clicks \"([^\"]*)\" In Overwrite Existing Sample Prompt Message$", (String value) -> {
            JavascriptExecutor executor = (JavascriptExecutor) scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getTestSetupPage().getButtonInOverrideAlert(value));
        });

        Then("^Verify Sample Type Icon Remains A Patient In Well \"([^\"]*)\"$", (String well) ->
            assertTrue(scenarioContext.getTestSetupPage().isPatientIconForWellDisplayed(well))
        );
    }
}
