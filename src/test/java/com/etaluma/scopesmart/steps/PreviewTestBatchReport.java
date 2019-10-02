package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PreviewTestBatchReport extends BaseStep {
    public PreviewTestBatchReport(ScenarioContext scenarioContext) {

        this.scenarioContext = scenarioContext;

        After(new String[]{"@PreviewTestBatchReport"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@DownloadTestBatchReport"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Then("^User clicks on Preview PDF", () -> {
            Thread.sleep(1000);
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getPreviewPdfButton());
        });

        And("^Verify that Preview PDF Window is opened", () -> {
            scenarioContext.getReviewPage().waitForDocument();
            assertTrue(scenarioContext.getReviewPage().getPreviewPdfWindow().isDisplayed());
        });

        And("^Verify following icons in Preview PDF Window$", (DataTable entries) -> {
            List<String> icons = entries.asList();
            for(String icon: icons)
                assertTrue(scenarioContext.getReviewPage().getGenericIcon(icon).isDisplayed());
        });

        Then("^Verify Page Number is present in footer$", () ->
            assertTrue(scenarioContext.getReviewPage().getPageFooter().isDisplayed())
        );

        And("^Verify the title is \"([^\"]*)\"$", (String title) ->
            assertEquals(title, scenarioContext.getReviewPage().getPreviewPdfTitle().getText())
        );

        And("^User clicks on Close Preview Icon$", () -> {
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getClosePanelIcon());
            Thread.sleep(1500);
        });

        Then("^Verify that the Backdrop is disabled$", () ->
            assertFalse(scenarioContext.getReviewPage().getPdfWindowsList().isEmpty())
        );

        Then("^Verify that Preview PDF Window is hidden$", () ->
            assertTrue(scenarioContext.getReviewPage().getPdfWindowsList().isEmpty())
        );

        And("^User clicks on \"([^\"]*)\" icon in Preview PDF Window$", (String icon) -> {
            Thread.sleep(2000);
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getGenericIcon(icon));
            Thread.sleep(2000);
        });

        Then("^Verify OS Print dialog$", () -> {
            Thread.sleep(1000);
            String windowHandle = scenarioContext.getDriver().getWindowHandle();
            ArrayList<String> tabs = new ArrayList<>(scenarioContext.getDriver().getWindowHandles());
            scenarioContext.getDriver().switchTo().window(tabs.get(0));
            scenarioContext.getDriver().switchTo().window(windowHandle);
        });

        Then("^Verify the downloaded file is \"([^\"]*)\"$", (String filename) -> {
            Thread.sleep(2000);
            File tempFile = new File(downloadFilePath + filename);
            assertTrue(tempFile.exists());
            tempFile.delete();
        });
    }
}