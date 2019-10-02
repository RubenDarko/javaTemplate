package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.*;

public class AddPatientToSlide extends BaseStep {

    private Integer wellNumber;
    private String sampleId;

    public AddPatientToSlide(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@AddPatientSampleToSlideKeyboard"},(Scenario scenario)->
            super.teardown(scenario)
        );

        And("^Well \"([^\"]*)\" Within The Slide Is Selected/Active$", (String well) -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().selectWell(well);
        });

        And("^Sample ID \"([^\"]*)\" Is Editable$", (String wellNumber) -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().isWellEditable(wellNumber);
            this.wellNumber = Integer.parseInt(wellNumber);
        });

        When("^User Enters Sample ID \"([^\"]*)\" Using Keyboard$", (String sampleId) ->
            scenarioContext.getTestSetupPage().getTestBatchPage().addPatientSample(sampleId,wellNumber)
        );

        When("^User Confirms Adding Sample ID \"([^\"]*)\" Without Matching In LIS$", (String sampleId) ->
            scenarioContext.getTestSetupPage().getTestBatchPage().addPatientSample(sampleId,wellNumber)
        );

        Then("^Sample ID \"([^\"]*)\" Is Added To Slide$", (String sampleId) -> {
            Map<String,String> sample = new LinkedHashMap<>();
            sample.put("dilution","20");
            sample.put("sampleId",sampleId);
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().getWellSamples().contains(sample));
        });

        Then("^the UI returns the focus to the Sample ID text area$", () -> {
        });

        When("^User Cancels The Add Action Sample ID \"([^\"]*)\"$", (String sampleId) -> {
            scenarioContext.getTestSetupPage().getTestBatchPage().addPatientSample(sampleId,wellNumber);
            scenarioContext.getTestSetupPage().getTestBatchPage().selectWell(wellNumber.toString());
            this.sampleId= sampleId;
        });

        And("^Verify Previously Entered Text Is Visible Editable$", () -> {
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().isWellEditable(wellNumber.toString()));
            assertEquals(sampleId, scenarioContext.getTestSetupPage().getTestBatchPage().getWellSamples().get(wellNumber).get("sampleId"));
        });
    }
}