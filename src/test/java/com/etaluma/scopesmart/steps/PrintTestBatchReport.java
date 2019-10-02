package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import java.io.File;
import static org.junit.Assert.*;

public class PrintTestBatchReport extends BaseStep {
    public PrintTestBatchReport(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@PrintTestBatchReport"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^Verify PDF File is downloaded$", () -> {
            File tempFile = new File(downloadFilePath);
            boolean flag = false;
            for(File file: tempFile.listFiles()) {
                if(file.getName().contains("pdf")) {
                    flag=true;
                }
            }
            assertTrue(flag);
        });

        And("^Clean up Downloads folder$", () -> {
            File tempFile = new File(downloadFilePath);
            for(File file: tempFile.listFiles()) {
                if(file.getName().contains("pdf") || file.getName().contains("csv")) {
                    System.out.println("Delete: " + file.getName());
                    file.delete();
                }
            }
        });
    }
}