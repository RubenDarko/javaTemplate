package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class UserDeleteTestBatchSlide extends BaseStep {

    public int activeWell;
    private String patientId;

    public UserDeleteTestBatchSlide(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@UserDeleteTestBatch"},(Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@UserDeleteSlide"},(Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^a Slide \"([^\"]*)\" is selected$",  (String slide) -> {
            scenarioContext.getTestSetupPage().addNewSlide(slide);
        });

        When("^the User clicks the delete button$", () ->
            scenarioContext.getTestSetupPage().getTestBatchPage().deleteSlide()
        );

        Then("^a confirmation dialog is displayed warning the user that the Slide and any Samples will be deleted$", () -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().isDeleteSlideAlertVisible();
            scenarioContext.getTestSetupPage().getTestBatchPage().cancelDeleteSlide();
        });

        When("^the User confirms the delete action$", () -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().deleteSlide();
            scenarioContext.getTestSetupPage().getTestBatchPage().confirmDeleteSlide();
        });

        When("^set Patient ID in well$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for (Map<String, String> item: list) {
                activeWell = Integer.parseInt(item.get("well"));
                patientId = item.get("Patient_ID");
                scenarioContext.getTestSetupPage().getTestBatchPage().addPatientSample(patientId,activeWell-1);
            }
        });

        And("^set Positive Control in well$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for (Map<String, String> item: list) {
                activeWell = Integer.parseInt(item.get("well"));
                scenarioContext.getTestSetupPage().getTestBatchPage().addPositiveControl(activeWell);
            }
        });

        And("^set Negative Control in well$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for (Map<String, String> item: list) {
                activeWell = Integer.parseInt(item.get("well"));
                scenarioContext.getTestSetupPage().getTestBatchPage().addNegativeControl(activeWell);
            }
        });

        And("^the Slide is removed from the Test Batch's list of Slides in the UI$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().getTestBatches().isEmpty())
        );

        And("^no Slide is currently selected$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().getSelectedSlide().isEmpty())
        );

        When("^the user cancels the delete action$", () -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().deleteSlide();
            scenarioContext.getTestSetupPage().getTestBatchPage().cancelDeleteSlide();
        });

        Then("^no changes are made to the Slide Setup after cancel$", () ->
            assertFalse(!scenarioContext.getTestSetupPage().getTestBatchPage().getTestBatches().isEmpty())
        );

        When("^the User clicks the delete Test Batch button$", () ->
            scenarioContext.getTestSetupPage().deleteTestBatch()
        );

        Then("^a confirmation dialog is displayed warning the user that the Test Batch will be deleted$", () -> {
            scenarioContext.getTestSetupPage().isDeleteTestBatchAlertVisible();
            scenarioContext.getTestSetupPage().cancelDeleteTestBatch();
        });

        When("^the User confirms the delete Test Batch action$", () -> {
            scenarioContext.getTestSetupPage().deleteTestBatch();
            scenarioContext.getTestSetupPage().confirmDeleteTestBatch();
        });

        When("^the user cancels the delete Test Batch action$", () -> {
            scenarioContext.getTestSetupPage().deleteTestBatch();
            scenarioContext.getTestSetupPage().cancelDeleteTestBatch();
        });

        Then("^no changes are made to the Test Batches Setup$", () ->
            assertTrue(!scenarioContext.getTestSetupPage().getTestBatchesList().isEmpty())
        );

        And("^the Test Batch is removed from the UI List$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchesList().isEmpty())
        );

        And("^the first Test Batch in the list is selected, if any$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getSelectedTestBatch().isEmpty())
        );
    }
}