package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.ArchivePage;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static junit.framework.TestCase.*;

public class QueryArchivedPatientResults extends BaseStep {

    List<String> sampleIds = new ArrayList<>();
    String sampleId;
    String slideId;
    String batchName;

    public QueryArchivedPatientResults(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@QueryArchivedPatientResults"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@PatientResultsDetails"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@ViewPrintDownloadReports"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        Given("^The Archive Page Is Active$", () -> {
            super.setup();
            scenarioContext.setArchivePage(new ArchivePage(scenarioContext.getDriver()));
        });

        Then("^Verify the next Radio Buttons are present in Archive Page$", (DataTable entries) -> {
            List<String> radioButtons = entries.asList();
            for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                assertTrue(radioButtons.contains(radio.getText()));
            }
        });

        And("^Verify the next Text Fields and Placeholders in Archive Page$", (DataTable entries) -> {
            List<Map<String, String>> maps = entries.asMaps(String.class, String.class);
            for(Map<String, String> element: maps) {
                assertTrue(scenarioContext.getArchivePage().getTextFieldByLabel(element.get("Field")).isDisplayed());
                assertEquals(scenarioContext.getArchivePage().getTextFieldByLabel(element.get("Field")).getAttribute("placeholder"),
                        element.get("Placeholder"));
            }
        });

        Then("^Verify the next Combo Boxes are present in Archive Page$", (DataTable entries) -> {
            List<Map<String, String>> comboBoxes = entries.asMaps(String.class, String.class);
            for(Map<String, String> comboBox: comboBoxes) {
                assertTrue(scenarioContext.getArchivePage().getComboBoxWithValue(comboBox.get("ComboBox"), comboBox.get("Label")).isDisplayed());
            }
        });

        And("^Verify the next Results Check Boxes are present in Archive Page$", (DataTable entries) -> {
            List<String> results = entries.asList();
            for(String result: results) {
                assertTrue(scenarioContext.getArchivePage().getCheckBoxByLabel(result).isDisplayed());
            }
        });

        Then("^Verify the next User Edited Buttons are present in Archive Page$", (DataTable entries) -> {
            List<String> buttons = entries.asList();
            for(String button: buttons) {
                assertTrue(scenarioContext.getArchivePage().getButtonByLabel(button).isDisplayed());
            }
        });

        And("^Verify the next Icons are present in Archive Page$", (DataTable entries) -> {
            List<String> icons = entries.asList();
            for(String icon: icons) {
                assertTrue(scenarioContext.getArchivePage().getButtonByIcon(icon).isDisplayed());
            }
        });

        Then("^Select \"([^\"]*)\" in \"([^\"]*)\" Combo Box$", (String pattern, String comboBox) -> {
            scenarioContext.getArchivePage().getComboBox(comboBox).click();
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getArchivePage().getComboBoxWithValue(comboBox, pattern));
            scenarioContext.getArchivePage().getComboBox(comboBox).click();
        });

        And("^Click on \"([^\"]*)\" icon in Archive Page$", (String icon) -> {
            scenarioContext.getArchivePage().getButtonByIcon(icon).click();
            Thread.sleep(1000);
        });

        Then("^Validate all results contain \"([^\"]*)\" in column \"([^\"]*)\"$", (String value, String column) -> {
            int index = scenarioContext.getArchivePage().getColumnNumber(column);
            if(!value.equals("any"))
                for(WebElement cell: scenarioContext.getArchivePage().getValuesInColumn(index))
                    if(!cell.getText().equals(""))
                        assertTrue(cell.getText().toLowerCase().contains(value.toLowerCase()));
        });

        Then("^Select \"([^\"]*)\" Check Box$", (String checkBox) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getArchivePage().getCheckBoxByLabel(checkBox));
        });

        Then("^Store temporal result values$", () -> {
            int colSample = scenarioContext.getArchivePage().getColumnNumber("Sample ID");
            sampleId = scenarioContext.getArchivePage().getValuesInColumn(colSample).get(0).getText();
            int colSlide = scenarioContext.getArchivePage().getColumnNumber("Slide ID");
            slideId = scenarioContext.getArchivePage().getValuesInColumn(colSlide).get(0).getText();
            int colBatch = scenarioContext.getArchivePage().getColumnNumber("Batch Name");
            batchName = scenarioContext.getArchivePage().getValuesInColumn(colBatch).get(0).getText();

            for(WebElement cell: scenarioContext.getArchivePage().getValuesInColumn(colSample)){
                sampleIds.add(cell.getText());
            }
        });

        Then("^Enter \"([^\"]*)\" and validate results$", (String value) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            int colSample = scenarioContext.getArchivePage().getColumnNumber("Sample ID");
            int colSlide = scenarioContext.getArchivePage().getColumnNumber("Slide ID");
            int colBatch = scenarioContext.getArchivePage().getColumnNumber("Batch Name");

            for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                if(radio.getText().equals(value))
                    executor.executeScript("arguments[0].click();", radio);
            }

            scenarioContext.getArchivePage().getTextFieldByLabel("sample").clear();
            switch(value) {
                case "Sample ID":
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(sampleId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    for(WebElement cell: scenarioContext.getArchivePage().getValuesInColumn(colSample))
                        assertTrue(cell.getText().toLowerCase().contains(sampleId.toLowerCase()));

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Batch Name"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(sampleId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Slide ID"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(sampleId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());
                    break;

                case "Slide ID":
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(slideId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    for(WebElement cell: scenarioContext.getArchivePage().getValuesInColumn(colSlide))
                        assertTrue(cell.getText().toLowerCase().contains(slideId.toLowerCase()));

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Batch Name"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(slideId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Sample ID"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(slideId);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());
                    break;

                case "Batch Name":
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(batchName);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    for(WebElement cell: scenarioContext.getArchivePage().getValuesInColumn(colBatch))
                        assertTrue(cell.getText().toLowerCase().contains(batchName.toLowerCase()));

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Slide ID"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(batchName);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());

                    for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                        if(radio.getText().equals("Sample ID"))
                            executor.executeScript("arguments[0].click();", radio);
                    }
                    scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(batchName);
                    scenarioContext.getArchivePage().getButtonByIcon("Search").click();
                    Thread.sleep(2000);
                    assertTrue(scenarioContext.getArchivePage().getGenericMessage("Enter search criteria").isDisplayed());
                    break;
            }
        });

        Then("^Select \"([^\"]*)\" Radio Button and enter Value \"([^\"]*)\"$", (String radioButton, String value) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            for(WebElement radio: scenarioContext.getArchivePage().getRadioButtons()) {
                if(radio.getText().equals(radioButton))
                    executor.executeScript("arguments[0].click();", radio);
            }
            scenarioContext.getArchivePage().getTextFieldByLabel("sample").sendKeys(value);
        });

        And("^Validate message \"([^\"]*)\" is displayed in Archive Page$", (String message) ->
            assertTrue(scenarioContext.getArchivePage().getGenericMessage(message).isDisplayed())
        );

        When("^User selects the \"([^\"]*)\" row in column Sample ID$", (String row) -> {
            int counter = 1;
            for(WebElement checkbox: scenarioContext.getArchivePage().getTableCheckBoxes())
                if(counter == Integer.parseInt(row)){
                    checkbox.click();
                    break;
                }
                else {
                    counter ++;
                }
        });

        Then("^Verify that the \"([^\"]*)\" Button is \"([^\"]*)\"$", (String icon, String status) ->
            assertTrue(scenarioContext.getArchivePage().getButtonByIcon(icon).findElement(By.xpath("..")).isEnabled())
        );

        Then("^Verify that the \"([^\"]*)\" Button is enabled$", (String icon) ->
            assertFalse(scenarioContext.getArchivePage().getButtonByIcon(icon).findElement(By.xpath("..")).getAttribute("class").contains("disabled"))
        );

        When("^User clicks on the \"([^\"]*)\" Button$", (String icon) ->
            scenarioContext.getArchivePage().getButtonByIcon(icon).click()
        );

        Then("^Verify that the Samples window is open$", () -> {
            Thread.sleep(2000);
            assertFalse(scenarioContext.getArchivePage().getSamplesWindow().isEmpty());
        });

        Then("^Verify that the \"([^\"]*)\" SampleID match with the one in the Samples Window$", (String row) -> {
            int counter = 1;
            for(WebElement checkbox: scenarioContext.getArchivePage().getTableCheckBoxes())
                if(counter == Integer.parseInt(row)){
                    assertEquals(
                            checkbox.findElement(By.xpath("following-sibling::div[1]/*[2]")).getText(),
                            scenarioContext.getArchivePage().getSampleIDFromSamplesWindow(row).getText());
                    break;
                }
                else {
                    counter ++;
                }
        });

        When("^User selects a \"([^\"]*)\" in the Samples Window$", (String pattern) ->
            scenarioContext.getArchivePage().getReferenceLibraryComboBox().sendKeys(pattern)
        );

        Then("^Verify that an image is shown in the Reference Image section$", () -> {
            assertFalse(scenarioContext.getArchivePage().getReferenceLibraryImagesSection().isEmpty());
        });

        And("^User moves between the different images$", () -> {
            int counter = 1;
            for(WebElement dot: scenarioContext.getArchivePage().getReferenceLibraryImages()){
                dot.click();
                Thread.sleep(2000);
                assertTrue(scenarioContext.getArchivePage().getReferenceLibraryImagesSection().get(0).findElement(
                        By.xpath("ul/*[" + counter + "]/div/img")).isDisplayed());
                counter ++;
            }
        });

        When("^User clicks on the X Icon on the Samples Window$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getArchivePage().getSamplesWindowCloseButton());
        });

        Then("^Verify that the Samples Window is close$", () -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getArchivePage().getSamplesWindow().isEmpty());
        });

        When("^User clicks on the Forward Button on the Samples Window$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getArchivePage().getSamplesWindowForwardButton());
            Thread.sleep(1000);
        });

        When("^User clicks on the Backward Button on the Samples Window$", () -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getArchivePage().getSamplesWindowBackwardButton());
            Thread.sleep(1000);
        });

        Then("^Verify that the \"([^\"]*)\" Sample is selected$", (String sampleID) ->
            assertTrue(scenarioContext.getArchivePage().getSampleIDFromSamplesWindow(
                    sampleID).findElement(By.xpath("..")).getAttribute("style").contains("background-color"))
        );

        Then("^Enter \"([^\"]*)\" in \"([^\"]*)\" text field$", (String value, String field) ->
            scenarioContext.getArchivePage().getTextFieldByLabel(field).sendKeys(value)
        );

        And("^Verify the next Text Fields are empty in Archive Page$", (DataTable entries) -> {
            List<String> fields = entries.asList();
            for(String field: fields) {
                assertEquals(scenarioContext.getArchivePage().getTextFieldByLabel(field).getText(), "");
            }
        });

        Then("^Verify the next Combo Boxes are empty in Archive Page$", (DataTable entries) -> {
            List<String> comboBoxes = entries.asList();
            for(String comboBox: comboBoxes) {
                scenarioContext.getArchivePage().getComboBox(comboBox).click();
                assertEquals(scenarioContext.getArchivePage().getComboBoxSelectedItems(comboBox).size(), 0);
            }
        });

        And("^Verify the next Results Check Boxes are unchecked in Archive Page$", (DataTable entries) -> {
            List<String> checkBoxes = entries.asList();
            for(String checkBox: checkBoxes) {
                assertEquals(scenarioContext.getArchivePage().getCheckBoxByLabel(checkBox).getAttribute("value"), "false");
            }
        });

        When("^User clicks on the \"([^\"]*)\" tile$", (String tile) ->
            scenarioContext.getArchivePage().getTileByLabel(tile).click()
        );

        And("^Verify that Preview PDF Window is opened in Archive Page", () -> {
            scenarioContext.getArchivePage().waitForDocument();
            assertTrue(scenarioContext.getArchivePage().getPreviewPdfWindow().isDisplayed());
        });

        And("^Verify the title is \"([^\"]*)\" in PDF Preview$", (String title) ->
            assertTrue(scenarioContext.getArchivePage().getPdfPreviewTitle(title).isDisplayed())
        );

        And("^Verify Close Panel Icon in PDF Preview$", () ->
            assertTrue(scenarioContext.getArchivePage().getClosePanelIcon().isEnabled())
        );

        And("^Verify following icons in PDF Preview$", (DataTable entries) -> {
            List<String> icons = entries.asList();
            for(String icon: icons)
                assertTrue(scenarioContext.getArchivePage().getGenericIcon(icon).isDisplayed());
        });

        Then("^Verify Page Number footer is present$", () ->
            assertTrue(scenarioContext.getArchivePage().getPreviewPdfPageFooter().isDisplayed())
        );

        When("^User clicks on \"([^\"]*)\" icon in Archive Page$", (String icon) -> {
            scenarioContext.getArchivePage().getGenericIcon(icon).click();
            Thread.sleep(2000);
        });
    }
}