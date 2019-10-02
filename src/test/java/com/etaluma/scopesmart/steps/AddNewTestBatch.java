package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.TestSetupPage;
import com.etaluma.scopesmart.pages.TestSummaryPage;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;
import static org.junit.Assert.*;

public class AddNewTestBatch extends BaseStep {

    private String barcode;

    public AddNewTestBatch(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@AddNewTestBatch"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@TestBatchValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@TestBatchWorklistValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@TestBatchSlideValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@SampleIDValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@SampleIDScanValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@TestBatchLockUnlockValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Given("^The Test Setup Page Is Active$", () -> {
            super.setup();
            scenarioContext.setTestSetupPage(new TestSetupPage(scenarioContext.getDriver()));
        });

        And("^Hand-Held Scanner Is Connected To Client PC$", () ->
            assertTrue(scenarioContext.getTestSetupPage().isScannerConnected())
        );

        And("^User Triggers A \"([^\"]*)\" Scan$", (String barcode) -> {
            this.barcode=barcode;
            Actions actions = new Actions(scenarioContext.getDriver());
            scenarioContext.getTestSetupPage().waitForPageToLoad();
            actions.moveToElement(scenarioContext.getTestSetupPage().getTestBarchWorkspace()).click().perform();
            actions.moveToElement(scenarioContext.getTestSetupPage().getTestBarchWorkspace()).sendKeys(barcode).perform();
            scenarioContext.getTestSetupPage().waitForPageToLoad();
            scenarioContext.getTestSetupPage().setTestSummary(new TestSummaryPage(scenarioContext.getDriver()));
            Thread.sleep(2000);
        });

        And("^Kit Lot Is Not Associated With Another Test Batch In The Setup State$", () ->
            assertEquals(new Integer(1),scenarioContext.getTestSetupPage().getTestSummary().getBatchSize())
        );

        And("^Kit Lot Expiration Date Is Greater Than Or Equal To The Current Date$", () ->
            assertTrue(LocalDate.parse(scenarioContext.getTestSetupPage().getTestSummary().getDate()).compareTo(LocalDate.now())>= 0)
        );

        And("^Test Batch Is Displayed And Selected In UI$", () -> {
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getGenericObjectWithText("Test Batch Summary");
        });

        And("^Notification Sent To UI Indicating The Successful Changes$", () -> {
            Thread.sleep(1000);
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("created").isEmpty());
        });

        And("^Kit Lot Is Already Associated With \"([^\"]*)\" In Setup State That Has Capacity For More Slides$", (String anotherTestBatch) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            scenarioContext.getTestSetupPage().waitForPageToLoad();
            actions.moveToElement(scenarioContext.getTestSetupPage().getTestBarchWorkspace()).click().perform();
            actions.moveToElement(scenarioContext.getTestSetupPage().getTestBarchWorkspace()).sendKeys(anotherTestBatch).perform();
            scenarioContext.getTestSetupPage().waitForPageToLoad();
            scenarioContext.getTestSetupPage().setTestSummary(new TestSummaryPage(scenarioContext.getDriver()));
            Thread.sleep(2000);
        });

        And("^User Confirms The Creation Of A New Test Batch Anyway$", () ->
            scenarioContext.getTestSetupPage().confirmTestBatch()
        );

        When("^Scan Is Not Determined To Be A Kit Lot$", () ->
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("No well is currently selected").isEmpty())
        );

        And("^Kit Lot Expiration Date Is Less Than The Current Date$", () ->
            assertTrue(new SimpleDateFormat("yyMMdd").parse(barcode.substring(15,21)).toInstant().compareTo(Instant.now())< 0)
        );

        And("^Error Notification Is Displayed$", () ->
            assertTrue(scenarioContext.getTestSetupPage().hasErrorAlert())
        );

        When("^User clicks on Add new Test Batches button$", () ->
           scenarioContext.getTestSetupPage().getAddNewTestBatch().click()
        );

        Then("^The Test Batch form is displayed$", () -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getTestSetupPage().getCreateTestBatchWindow().isDisplayed());
        });

        When( "^User fill out create Test Batch form$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for(Map<String, String> entry: list) {
                scenarioContext.getTestSetupPage().getSelectByLabel(entry.get("input")).sendKeys(entry.get("value"));
            }
        });

        And( "^User fill out the worklist inside the Test Batch form$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for(Map<String, String> entry: list) {
                scenarioContext.getTestSetupPage().getInputByLabel(entry.get("input")).sendKeys(entry.get("value"));
            }
        });

        And("^The Checksum data is correct$", () -> {
            for(int i=0; i<=99; i++){
                scenarioContext.getTestSetupPage().getChecksum().clear();
                scenarioContext.getTestSetupPage().getChecksum().sendKeys(i < 10 ? String.format("0%s", i): String.format("%s", i));
                scenarioContext.getTestSetupPage().getChecksumButton().click();
                Thread.sleep(50);
                if(scenarioContext.getTestSetupPage().getChecksumFails().isEmpty())
                    if(!scenarioContext.getTestSetupPage().getChecksumOks().isEmpty())
                        break;
            }
        });

        And("^User clicks on the Create Test Batch button$", () ->
            scenarioContext.getTestSetupPage().getCreateTestBatchButton().click()
        );

        And ("^The Test Batch name is \"([^\"]*)\"-\"([^\"]*)\"-01M$", (String slideType, String customDate) -> {
            String currentBatchName = scenarioContext.getTestSetupPage().getSelectedBatchName().getText();
            String expectedBatchName = String.format("%s-%s-01M", slideType, customDate);
            assertEquals(expectedBatchName, currentBatchName);
        });

        When("^Clean up Test Batches$", () -> {
            boolean flag=true;
            while (!scenarioContext.getTestSetupPage().getTestBatchesList().isEmpty() && flag) {
                JavascriptExecutor executor = scenarioContext.getDriver();
                executor.executeScript("arguments[0].click();",scenarioContext.getTestSetupPage().getSelectedBatch());
                Thread.sleep(1000);
                if (scenarioContext.getTestSetupPage().getEditTestBatchButton().isEmpty()) {
                    scenarioContext.getTestSetupPage().deleteTestBatch();
                    scenarioContext.getTestSetupPage().confirmDeleteTestBatch();
                } else {
                    scenarioContext.getTestSetupPage().editTestBatchButton.click();
                    scenarioContext.getTestSetupPage().deleteTestBatch();
                    scenarioContext.getTestSetupPage().confirmDeleteTestBatch();
                }
                Thread.sleep(3000);
                if(scenarioContext.getTestSetupPage().getAlertWithText("Error: An error occurred while processing your request").isEmpty())
                    flag = true;
                else
                    flag = false;
            }
        });

        And("^The Create Test Batch button is disabled$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getDisabledCreatedTestBatchButton().isDisplayed())
        );

        And("^The Create Test Batch button is enabled$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getEnabledCreatedTestBatchButton().isDisplayed())
        );

        When( "^User change the Kit Part value$", (DataTable entries) -> {
            List<Map<String, String>> list = entries.asMaps(String.class, String.class);
            for(Map<String, String> entry: list) {
                scenarioContext.getTestSetupPage().getInputByLabel(entry.get("input")).clear();
                scenarioContext.getTestSetupPage().getInputByLabel(entry.get("input")).sendKeys(entry.get("value"));
            }
        });

        And("^Verify that the Green icon next to the Verify button is not present$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getChecksumOk().isDisplayed())
        );

        And("^Verify that a Red icon next to the Verify button is present$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getChecksumFail().isDisplayed())
        );

        And("^User types 21 characters in the \"([^\"]*)\" input field$", (String worklist) ->
            scenarioContext.getTestSetupPage().getInputByLabel("Worklist").sendKeys(worklist)
        );

        And("^Verify that the Worklist input field only accepts \"([^\"]*)\" characters$", (String characters) -> {
            int worklistValue = scenarioContext.getTestSetupPage().getInputByLabel("Worklist").getAttribute("value").length();
            int expectedCharacters = Integer.parseInt(characters);
            assertEquals(worklistValue, expectedCharacters);

        });

        When("^User clicks on the Worklist input field$", () ->
            scenarioContext.getTestSetupPage().getWorklist().click()
        );

        And("^User delete the Worklist name$", () ->
            scenarioContext.getTestSetupPage().getWorklistDeleteButton().click()
        );

        Then("^Verify that the \"([^\"]*)\" name is not displayed in the Test Batch Summary page$", (String expectedWorklist) -> {
            Thread.sleep(2000);
            String currentWorklist =  scenarioContext.getTestSetupPage().getWorklist().getText();
            assertEquals("Add +", currentWorklist);
        });

        When("^User types a \"([^\"]*)\" name of 21 characters in the Worklist input field$", (String worklist) -> {
            scenarioContext.getTestSetupPage().getWorklist().click();
            scenarioContext.getTestSetupPage().getWorklistInput().sendKeys(worklist);
            scenarioContext.getTestSetupPage().getWorklistOkButton().click();
        });

        And("^Verify that the Test Batch Summary Worklist input field only accepts \"([^\"]*)\" characters$", (String characters) -> {
            Thread.sleep(2000);
            int worklistValue = scenarioContext.getTestSetupPage().getWorklist().getText().length();
            int expectedCharacters = Integer.parseInt(characters);
            assertEquals(expectedCharacters, worklistValue);
        });

        And("^The \"([^\"]*)\" name is displayed in the Test Batch Summary page$", (String worklist) -> {
            Thread.sleep(2000);
            assertEquals(worklist, scenarioContext.getTestSetupPage().getWorklist().getText());
        });

        And("^User enters a new \"([^\"]*)\" name$", (String worklist) -> {
            scenarioContext.getTestSetupPage().getWorklist().click();
            scenarioContext.getTestSetupPage().getWorklistDeleteButton().click();
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getWorklist().click();
            scenarioContext.getTestSetupPage().getWorklistTextField().sendKeys(worklist);
            scenarioContext.getTestSetupPage().getWorklistOkButton().click();
            Thread.sleep(2000);
        });

        And("^User enters a \"([^\"]*)\" name$", (String worklist) -> {
            scenarioContext.getTestSetupPage().getWorklist().click();
            scenarioContext.getTestSetupPage().getWorklistInput().sendKeys(worklist);
            scenarioContext.getTestSetupPage().getWorklistOkButton().click();
            Thread.sleep(2000);
        });

        When("^User selects the last unselected Test Batch created$", () ->
            scenarioContext.getTestSetupPage().getLastUnselectedTestBatch().click()
        );

        And("^User types the first character in the \"([^\"]*)\"$", (String worklist) ->
            scenarioContext.getTestSetupPage().getWorklistInput().sendKeys(String.valueOf(worklist.charAt(0)))
        );

        Then("^Verify that the dropdown is populated with the \"([^\"]*)\" name from the first Test Batch$", (String worklist) -> {
            List<WebElement> allElements = scenarioContext.getTestSetupPage().getWorklistAutoSuggestions();

            String populatedOption = "";
            for (WebElement element: allElements) {
                if (element.getText().equals(worklist)){ populatedOption = element.getText();}
            }
            assertEquals(worklist, populatedOption);
        });

        When("^User selects the \"([^\"]*)\" name from the first Test Batch$", (String worklist) -> {
            List<WebElement> allElements = scenarioContext.getTestSetupPage().getWorklistAutoSuggestions();
            for (WebElement element: allElements) {
                if (element.getText().equals(worklist)){ element.click();}
            }
            Thread.sleep(2000);
        });

        And("^The Test Batch Worklist is empty$", () -> {
            assertEquals("Add +", scenarioContext.getTestSetupPage().getWorklist().getText());
        });

        When("^User clicks on the Add a New Slide button$", () ->
            scenarioContext.getTestSetupPage().getAddNewSlideButton().click()
        );

        Then("^Verify that the Create Slide form is displayed$", () -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
        });

        When("^User types \"([^\"]*)\" in the Slide ID input field$", (String data) -> {
            scenarioContext.getTestSetupPage().getCreateSlideInput().sendKeys(data);
        });

        And("^User Triggers a \"([^\"]*)\" scan in the \"([^\"]*)\"$", (String data, String well) -> {
            Actions actions = new Actions(scenarioContext.getDriver());
            actions.moveToElement(scenarioContext.getTestSetupPage().getWellsWorkspace()).sendKeys(data).perform();
            Thread.sleep(2000);
        });

        Then("^The Create Slide button is \"([^\"]*)\"$", (String status) -> {
            if(status.equals("disabled")){
                assertFalse(scenarioContext.getTestSetupPage().getCreateSlideButton().isEnabled());
            } else if (status.equals("enabled")){
                assertTrue(scenarioContext.getTestSetupPage().getCreateSlideButton().isEnabled());
            }
        });

        When("^User clicks on the Create Slide button$", () ->
                scenarioContext.getTestSetupPage().getCreateSlideButton().click()
        );

        Then("^Verify that the Create Slide form is \"([^\"]*)\"$", (String condition) -> {
            if(condition.equals("yes")) {
                assertTrue(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
                scenarioContext.getTestSetupPage().getSlideModal().click();
            } else if(condition.equals("no")) {
                Thread.sleep(2000);
                assertFalse(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
            }
        });

        And("^The Test Batch has a \"([^\"]*)\"", (String slideID) -> {
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getAddNewSlideButton().click();
            Thread.sleep(2000);
            assertTrue(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
            scenarioContext.getTestSetupPage().getCreateSlideInput().sendKeys(slideID);
            assertTrue(scenarioContext.getTestSetupPage().getCreateSlideButton().isEnabled());
            scenarioContext.getTestSetupPage().getCreateSlideButton().click();
            Thread.sleep(2000);
            assertFalse(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
            assertTrue(scenarioContext.getTestSetupPage().getAlertWithText("Error: Slide barcode is already in use.").isEmpty());
        });

        When("^User types the same \"([^\"]*)\" than previous Test Batch$", (String slideID) -> {
            scenarioContext.getTestSetupPage().getCreateSlideInput().sendKeys(slideID);
        });

        And("^Verify that a Notification is displayed indicating that the Slide is created$", () -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getTestSetupPage().getAlertWithText("A new Slide is created with identifier").isEmpty());
        });

        And("^User converts the first Well to be a Positive Control$", () -> {
            scenarioContext.getTestSetupPage().getSlidePatientButton("1").click();
            assertTrue(scenarioContext.getTestSetupPage().getSlideSampleID("Positive Control").isDisplayed());
        });

        And("^User converts the second Well to be a Negative Control$", () -> {
            for(int i=0; i<2; i++){
                scenarioContext.getTestSetupPage().getSlidePatientButton("2").click();
                Thread.sleep(2000);
            }
            assertTrue(scenarioContext.getTestSetupPage().getSlideSampleID("Negative Control").isDisplayed());
        });

        And("^User types Patient for the \"([^\"]*)\" in the Sample ID input$", (String well) -> {
            scenarioContext.getTestSetupPage().getSlideSampleIDInput(well).click();
            scenarioContext.getTestSetupPage().getSlideSampleIDInput(well).sendKeys("Patient");
            scenarioContext.getTestSetupPage().getSlideSampleIDInput(well).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            assertTrue(scenarioContext.getTestSetupPage().getSlideSampleIDSpanText().isDisplayed());
        });

        When("^User clicks in the \"([^\"]*)\"$", (String well) ->
            scenarioContext.getTestSetupPage().getSlideSampleIDInput(well).click()
        );

        When("^User clicks in the \"([^\"]*)\" number", (String well) ->
            scenarioContext.getTestSetupPage().getWellRowNumber(well).click()
        );

        When("^User clicks in the \"([^\"]*)\" again$", (String well) ->
            scenarioContext.getTestSetupPage().getWellRow(well).click()
        );

        When("^Verify that the \"([^\"]*)\" becomes highlighted$", (String well) ->{
            assertEquals("selected", scenarioContext.getTestSetupPage().getWellRow(well).getAttribute("class"));
        });

        When("^User clicks on the Test Batch$", () ->
            scenarioContext.getTestSetupPage().getSelectedBatchName().click()
        );

        And("^User clicks on the Approve Check Icon$", () ->
            scenarioContext.getTestSetupPage().getTestBatchApproveIcon().click()
        );

        And("^Verify that the Test Batch is blocked$", () ->
            assertTrue(scenarioContext.getTestSetupPage().getTestBatchPencilIcon().isDisplayed())
        );

        And("^Verify that the Test Batch is unlocked", () ->
                assertTrue(scenarioContext.getTestSetupPage().getTestBatchApproveIcon().isDisplayed())
        );

        And("^Verify that the Create Slide  button is disabled$", () -> {
            scenarioContext.getTestSetupPage().getAddNewSlideButton().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("A Slide can only be added to an unlocked Test Batch").isEmpty());
        });

        And("^Verify that the Create Slide button is enabled", () -> {
            scenarioContext.getTestSetupPage().getAddNewSlideButton().click();
            assertTrue(scenarioContext.getTestSetupPage().isCreateSlideFormPresent());
        });

        And("^User validate all the wells in the Test Batch Slide$", () -> {
            for (int well = 1; well < 13; well++) {
                scenarioContext.getTestSetupPage().getSlideSampleIDInput(String.format("%d", well)).click();
                assertEquals("selected", scenarioContext.getTestSetupPage().getWellRow(String.format("%d", well)).getAttribute("class"));
            }
        });

        And("^Verify that the Sample ID for the \"([^\"]*)\" is editable$", (String sampleID) ->
            assertTrue(scenarioContext.getTestSetupPage().getSampleIDInputEditable(sampleID).isDisplayed())
        );

        And("^Verify that the first Well icon changes to Positive Control$", () ->
            assertEquals("icon-positive-control", scenarioContext.getTestSetupPage().getSampleIDIcon(
                    "1", "icon-positive-control").getAttribute("class").replaceAll("\\s+",""))
        );

        And("^Verify that the second Well icon changes to Negative Control", () ->
            assertEquals("icon-negative-control", scenarioContext.getTestSetupPage().getSampleIDIcon(
                    "2", "icon-negative-control").getAttribute("class").replaceAll("\\s+",""))
        );

        And("^Verify that the \"([^\"]*)\" icon is \"([^\"]*)\"", (String well, String icon) ->
            assertEquals(icon, scenarioContext.getTestSetupPage().getSampleIDIcon(
                    well, icon).getAttribute("class").replaceAll("\\s+",""))
        );

        And("^Verify that both Wells are not editable", () -> {
            assertTrue(scenarioContext.getTestSetupPage().getSamplesIDEditable("Positive Control").isEmpty());
            assertTrue(scenarioContext.getTestSetupPage().getSamplesIDEditable("Negative Control").isEmpty());
        });

        And("^Verify that the \"([^\"]*)\" is \"([^\"]*)\"$", (String sampleID, String condition) -> {
            if(condition.equals("editable")){
                assertTrue(!scenarioContext.getTestSetupPage().getSamplesIDEditable(sampleID).isEmpty());
            }
            else if(condition.equals("not editable")){
                assertTrue(scenarioContext.getTestSetupPage().getSamplesIDEditable(sampleID).isEmpty());
            }

        });

        And("^The Sample ID for the Well \"([^\"]*)\" Is editable$", (String well) ->
            assertTrue(scenarioContext.getTestSetupPage().getEmptySampleIDEditable(well).isDisplayed())
        );

        And("^Verify that the Dilution is enabled", () ->
            assertTrue(scenarioContext.getTestSetupPage().getDilutionButton().isEnabled())
        );

        When("^User clicks on the Well reset button$", () ->
           scenarioContext.getTestSetupPage().getClearSampleID().click()
        );

        Then("^Verify that the SampleID is clear for the \"([^\"]*)\"$", (String well) ->
            assertTrue(scenarioContext.getTestSetupPage().getSlideSampleIDInput(well).isDisplayed())
        );

        Then("^Verify that the Dilution value is set to \"([^\"]*)\"$", (String value) ->
            assertTrue(scenarioContext.getTestSetupPage().getDilutionDropdownValue(value).isDisplayed())
        );

        And("^Verify that the \"([^\"]*)\" accepted only 20 characters$", (String sampleID) ->
            //TODO: Check max length with the scanner
            assertEquals(sampleID.substring(0, sampleID.length() - 1),
                    scenarioContext.getTestSetupPage().getSlideSampleID(sampleID.substring(0, sampleID.length() - 1)).getText())
        );

        And("^Verify that a Notification is displayed indicating that the Test Batch has been updated$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getAlertWithText("A Test Batch is updated").isEmpty())
        );

        And("^Verify that the User is not able to add a New Slide$", () -> {
            scenarioContext.getTestSetupPage().getAddNewSlideButton().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText(
                    "A Slide can only be added to an unlocked Test Batch").isEmpty());
        });

        And("^Verify that the Test Batch Worklist is not editable$", () -> {
            scenarioContext.getTestSetupPage().getWorklist().click();
            scenarioContext.getTestSetupPage().getWorklistOkButton().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("A Slide can only be added to an unlocked Test Batch The scan data is discarded").isEmpty());
        });

        When("^User clicks on the Slide$", () ->
            scenarioContext.getTestSetupPage().getSlide().click()
        );

        Then("^Verify that the Wells cannot be cleared", () -> {
            scenarioContext.getTestSetupPage().getSlidePatientButton("1").click();
            scenarioContext.getTestSetupPage().getClearSampleID().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("Error: Test Batch is not in Setup state and cannot be modified").isEmpty());
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getSlidePatientButton("2").click();
            scenarioContext.getTestSetupPage().getClearSampleID().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("Error: Test Batch is not in Setup state and cannot be modified").isEmpty());
            Thread.sleep(2000);
            scenarioContext.getTestSetupPage().getSlidePatientButton("3").click();
            scenarioContext.getTestSetupPage().getClearSampleID().click();
            assertTrue(!scenarioContext.getTestSetupPage().getAlertWithText("Error: Test Batch is not in Setup state and cannot be modified").isEmpty());
        });

        And("^Verify that the Delete Slide button is disabled$", () ->
            assertFalse(scenarioContext.getTestSetupPage().getTrashButton().isEnabled())
        );

        And("^User clicks on the Test Batch Edit Icon$", () ->
            scenarioContext.getTestSetupPage().getEditTestBatchIcon().click()
        );

        And("^User close the Create Slide window$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getTestSetupPage().getSlideCloseButton());
        });
    }
}