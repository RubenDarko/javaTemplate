package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class TestBatchComments extends BaseStep {

    private String sampleResultsComment;

    public TestBatchComments(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@TestBatchComments"}, (Scenario scenario) ->
                super.teardown(scenario)
        );

        Then("^Verify that the Label Comments is present$", () -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getReviewPage().getCommentsLabel().isDisplayed());
        });

        Then("^Verify that the Comments Edit Button is present$", () ->
            assertTrue(scenarioContext.getReviewPage().getCommentsEditButton().isDisplayed())
        );

        When("^User clicks on the Comments Edit Button$", () ->
           scenarioContext.getReviewPage().getCommentsEditButton().click()
        );

        Then("^Verify that the Add Comments Panel is present$", () -> {
            Thread.sleep(1000);
            assertFalse(scenarioContext.getReviewPage().getCommentsModal().isEmpty());
        });

        Then("^Verify that Comment Panel has the title \"([^\"]*)\"$", (String title) ->
            assertEquals(title, scenarioContext.getReviewPage().getCommentsModalTitle().getText())
        );

        And("^Verify that the label \"([^\"]*)\" is present in Comments Panel$", (String label) ->
                assertTrue(scenarioContext.getReviewPage().getLabelInCommentsModal(label).isDisplayed())
        );

        And("^Verify that the Titer checkbox is present$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getTiterCheckbox());
        });

        And("^Verify that the ComboBox Starting Ratio 1 is present$", () ->
                assertTrue(scenarioContext.getReviewPage().getStartingRatioDropdown().isDisplayed())
        );

        And("^Verify that the ComboBox Ending Ratio 1 is present$", () ->
                assertTrue(scenarioContext.getReviewPage().getEndingRatioDropdown().isDisplayed())
        );

        And("^Verify that the \"([^\"]*)\" Button is present$", (String buttonName) ->
                assertTrue(scenarioContext.getReviewPage().getCommentsPanelButton(buttonName).isDisplayed())
        );

        And("^Verify that the X Button is present$", () ->
                assertTrue(scenarioContext.getReviewPage().getCommentsPanelCloseButton().isDisplayed())
        );

        And("^User clear the comments in the text area$", () -> {
            sampleResultsComment = scenarioContext.getReviewPage().getSampleResultComment().getText();
            scenarioContext.getReviewPage().getCommentsPanelTextArea().clear();
        });

        And("^User types \"([^\"]*)\" in the text area$", (String data) -> {
            scenarioContext.getReviewPage().getCommentsPanelTextArea().clear();
            scenarioContext.getReviewPage().getCommentsPanelTextArea().sendKeys(data);
        });

        And("^User clicks on the Comment Panel X Button$", () ->
            scenarioContext.getReviewPage().getCommentsPanelCloseButton().click()
        );

        Then("^Verify that the Comment Panel is closed$", () -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getReviewPage().getCommentsModal().isEmpty());
        });

        Then("^Verify that the Comment has not change$", () ->
            assertEquals(sampleResultsComment, scenarioContext.getReviewPage().getSampleResultComment().getText())
        );

        And("^User clicks on the Comments Panel \"([^\"]*)\" Button$", (String button) ->
            scenarioContext.getReviewPage().getCommentsPanelButton(button).click()
        );

        And("^User clicks on the Titer Checkbox$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getTiterCheckbox());
        });

        And("^Verify that the Starting Ratio 1 dropdown is enabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getStartingRatioDropdown().isEnabled())
        );

        And("^Verify that the Ending Ratio 1 dropdown is enabled$", () ->
            assertTrue(scenarioContext.getReviewPage().getEndingRatioDropdown().isEnabled())
        );

        And("^Verify that the Comments section contains Titer text$", () ->
            assertTrue(scenarioContext.getReviewPage().getSampleResultComment().getText().contains("Titer"))
        );

        Then("^Verify that the Titer checkbox is unchecked$", () ->
            assertFalse(scenarioContext.getReviewPage().getTiterCheckbox().isSelected())
        );

        And("^Verify that the Comments section not contains Titer text$", () ->
            assertFalse(scenarioContext.getReviewPage().getSampleResultComment().getText().contains("Titer"))
        );

        And("^The Comments Panel \"([^\"]*)\" Button is disabled$", (String buttonName) ->
            assertFalse(scenarioContext.getReviewPage().getCommentsPanelButton(buttonName).isEnabled())
        );

        And("^Verify that the Comments Panel \"([^\"]*)\" Button is not enable with the following values$", (String buttonName, DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> element: maps) {
                scenarioContext.getReviewPage().getStartingRatioDropdown().sendKeys(element.get("Value1"));
                scenarioContext.getReviewPage().getEndingRatioDropdown().sendKeys(element.get("Value2"));
                assertFalse(scenarioContext.getReviewPage().getCommentsPanelButton(buttonName).isEnabled());
            }
        });

        And("^Verify that the Comments Panel \"([^\"]*)\" Button is enable with the following values$", (String buttonName, DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> element: maps) {
                scenarioContext.getReviewPage().getStartingRatioDropdown().sendKeys(element.get("Value1"));
                scenarioContext.getReviewPage().getEndingRatioDropdown().sendKeys(element.get("Value2"));
                assertTrue(scenarioContext.getReviewPage().getCommentsPanelButton(buttonName).isEnabled());
            }
        });

        And("^Verify \"([^\"]*)\" in Comments section$", (String comment) ->
            assertTrue(scenarioContext.getReviewPage().getSampleResultComment(comment).isDisplayed())
        );
    }
}
