package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;
import static junit.framework.TestCase.*;

public class SampleResultAuditTrail extends BaseStep {
    public SampleResultAuditTrail(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;

        After(new String[]{"@SampleResultAuditTrail"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^Verify Audit button is present", () ->
            assertTrue(scenarioContext.getReviewPage().getAuditButton().isEnabled())
        );

        When("^User clicks on the Audit button$", () -> {
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getAuditButton());
            assertTrue(scenarioContext.getReviewPage().getAuditTrialWindow().isEnabled());
        });

        Then("^Verify \"([^\"]*)\" text is present in Audit Trial$", (String text) -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getReviewPage().getAuditTrialHeader().getText().contains(text));
        });

        Then("^Verify following columns are present$", (DataTable datatable) -> {
            List<String> columns = datatable.asList(String.class);
            for(String column: columns) {
                assertTrue(scenarioContext.getReviewPage().getColumnInAuditTable(column).isDisplayed());
            }
        });

        And("^Verify X button is present in Audit Trial$", () ->
            assertTrue(scenarioContext.getReviewPage().getCloseAuditTrialButton().isEnabled())
        );

        And("^Verify Time column cell date format is \"([^\"]*)\"$", (String format) -> {
            for(WebElement row: scenarioContext.getReviewPage().getRowsByColumn(1)) {
                assertTrue(scenarioContext.getReviewPage().isValidDate(row.getText(),format));
            }
        });

        And("^Click on the X button to close Audit Trial$", () -> {
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getCloseAuditTrialButton());
        });
    }
}