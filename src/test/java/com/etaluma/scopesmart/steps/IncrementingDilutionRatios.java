package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class IncrementingDilutionRatios extends BaseStep {

    String selectedEndDilution;
    List<Map> wellSamples;
    String activeWell;
    Integer activeWellInt;

    public IncrementingDilutionRatios(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@IncrementDilutionRatio"},(Scenario scenario)->
            super.teardown(scenario)
        );

        And("^Slide Is Unlocked And Available For Editing$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getTestSummary().getEditTestBatchPencil().isDisplayed())
        );

        When("^the well \"([^\"]*)\" has \"([^\"]*)\"$", (String activeWell, String patientId) -> {
            this.activeWell = activeWell;
            activeWellInt = Integer.parseInt(this.activeWell);
            scenarioContext.getTestSetupPage().getTestBatchPage().addPatientSample(patientId,Integer.parseInt(activeWell)-1);
            wellSamples = scenarioContext.getTestSetupPage().getTestBatchPage().getWellSamples();
        });

        And("^the User clicks the 'fill down' action$", () ->
            scenarioContext.getTestSetupPage().getTestBatchPage().openFillDownMenu(activeWell)
        );

        And("^the User has selected the desired \"([^\"]*)\" for the Patient$", (String endDilution) -> {
            selectedEndDilution = endDilution;
            scenarioContext.getTestSetupPage().getTestBatchPage().selectFillDown(endDilution);
        });

        And("^A message appears warning the User that the Autofill was successfully completed$", () ->
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("Autofill successfully completed").isEmpty())
        );

        And("^there are enough empty wells on the Slide to hold all of the dilutions$", () -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().fillDown();
            int dilution = Integer.parseInt(wellSamples.get(activeWellInt).get("dilution").toString());
            for (int i = activeWellInt+1; i< wellSamples.size(); i++){
                if(!wellSamples.get(i).get("sampleId").toString().isEmpty() || dilution>=Integer.parseInt(this.selectedEndDilution))
                    break;
                dilution=dilution*2;
            }
            assertTrue(dilution>=Integer.parseInt(this.selectedEndDilution));
        });

        Then("^the patient ID is copied into the required unoccupied Wells$", () -> {
            wellSamples = scenarioContext.getTestSetupPage().getTestBatchPage().getWellSamples();
            int dilution = Integer.parseInt(wellSamples.get(activeWellInt).get("dilution").toString());
            boolean isthePatientIdCopied = true;
            for (int i = activeWellInt+1; i< wellSamples.size(); i++){
                if(dilution>=Integer.parseInt(selectedEndDilution)) {
                    break;
                }
                if(!wellSamples.get(activeWellInt).get("sampleId").equals(wellSamples.get(i).get("sampleId"))){
                    isthePatientIdCopied=false;
                    break;
                }
                dilution=dilution*2;
            }
            assertTrue(isthePatientIdCopied);
        });

        And("^the dilution ratio for each Patient Sample is set as (\\d+)x the previous dilution$", (Integer rate) -> {
            int dilution = Integer.parseInt(wellSamples.get(activeWellInt).get("dilution").toString())*rate;
            boolean isTheRateCorrect = true;
            for (int i = activeWellInt+1; i< wellSamples.size(); i++){
                if(dilution>=Integer.parseInt(selectedEndDilution)) {
                    break;
                }
                if(dilution!=Integer.parseInt(wellSamples.get(i).get("dilution").toString())){
                    isTheRateCorrect=false;
                    break;
                }
                dilution=dilution*rate;
            }
            assertTrue(isTheRateCorrect);
        });

        And("^there are NOT enough empty wells on the Slide to hold all of the dilutions$", () -> {
            int dilution = Integer.parseInt(wellSamples.get(activeWellInt).get("dilution").toString());
            for (int i = activeWellInt+1; i< wellSamples.size(); i++){
                if(!wellSamples.get(i).get("sampleId").toString().isEmpty() || dilution>=Integer.parseInt(selectedEndDilution))
                    break;
                dilution=dilution*2;
            }
            assertTrue(dilution<Integer.parseInt(selectedEndDilution));
        });

        And("^the User clicks the 'Fill/Go' action after reading the warning that additional Slides will need to be setup for the remaining dilutions$", () ->
            scenarioContext.getTestSetupPage().getTestBatchPage().fillDown()
        );

        And("^the User clicks 'cancel'$", () ->
            scenarioContext.getTestSetupPage().getTestBatchPage().cancelFillDown()
        );

        Then("^no changes are made to the Slide Setup$", () -> {
            wellSamples = scenarioContext.getTestSetupPage().getTestBatchPage().getWellSamples();
            assertEquals(wellSamples.get(activeWellInt+1).get("sampleId"),wellSamples.get(activeWellInt).get("sampleId"));
        });
    }
}