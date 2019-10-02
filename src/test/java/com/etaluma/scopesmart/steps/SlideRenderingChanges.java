package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class SlideRenderingChanges extends BaseStep {

    private int activeWell;

    public SlideRenderingChanges(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@SlideRenderingChanges"},(Scenario scenario)->
                super.teardown(scenario)
        );

        Then("^Verify Positive Control Icon In Well Within The Slide Virtual Representation$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            this.activeWell = Integer.parseInt(list.get(0).get("well"));
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().getPositiveControlVirtualSlide(activeWell));
        });

        Then("^Verify Negative Control Icon In Well Within The Slide Virtual Representation$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            this.activeWell = Integer.parseInt(list.get(0).get("well"));
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPage().getNegativeControlVirtualSlide(activeWell));
        });
    }
}