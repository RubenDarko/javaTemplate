package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import cucumber.api.Scenario;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;
import static junit.framework.TestCase.*;

public class WellComponentValidation extends BaseStep {

    String selectedResult;

    public WellComponentValidation(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

        After(new String[]{"@WellComponentValidation"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@ViewWellStatus"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@ViewMitoticCell"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        After(new String[]{"@SelectedSamplesResult"}, (Scenario scenario) ->
            super.teardown(scenario)
        );

        And("^Verify that the Sample Image label is present$", () -> {
            Thread.sleep(2000);
            assertTrue(scenarioContext.getReviewPage().getSampleImageLabel().isDisplayed());
        });

        And("^Verify that an Image is present under Sample Image label$", () ->
            assertFalse(scenarioContext.getReviewPage().getWellSampleImages().isEmpty())
        );

        And("^Verify that the image has dots$", () ->
            assertFalse(scenarioContext.getReviewPage().getWellImageDotButtons().isEmpty())
        );

        And("^Verify that the image has Left arrow$", () ->
            assertTrue(scenarioContext.getReviewPage().getWellImagePreviousButton().isDisplayed())
        );

        And("^Verify that the image has Right arrow$", () ->
            assertTrue(scenarioContext.getReviewPage().getWellImageNextButton().isDisplayed())
        );

        And("^Verify that the image has Minus Button$", () ->
            assertFalse(scenarioContext.getReviewPage().getWellImageMinusIcon().isEmpty())
        );

        And("^Verify that the image has Plus Button$", () ->
            assertFalse(scenarioContext.getReviewPage().getWellImagePlusIcon().isEmpty())
        );

        And("^Verify that the image has a Report Image Icon$", () ->
            assertTrue(scenarioContext.getReviewPage().getIconReportImage().isDisplayed())
        );

        And("^Verify that the image has a Image Meta Data Icon$", () ->
            assertTrue(scenarioContext.getReviewPage().getIconMetaData().isDisplayed())
        );

        When("^User navigates through all the Well Images with the Next Arrow$", () -> {
            int counter = 0;
            while(!scenarioContext.getReviewPage().getWellImageNextButton().getAttribute("class").contains("slick-disabled")){
                scenarioContext.getReviewPage().getWellImageNextButton().click();
                Thread.sleep(1000);
                scenarioContext.getReviewPage().getWellSampleImages().get(counter +1).isDisplayed();
                counter += 1;
            }
        });

        When("^User navigates through all the Well Images with the Back Arrow$", () -> {
            int counter = 0;
            while(!scenarioContext.getReviewPage().getWellImagePreviousButton().getAttribute("class").contains("slick-disabled")){
                scenarioContext.getReviewPage().getWellImagePreviousButton().click();
                Thread.sleep(1000);
                scenarioContext.getReviewPage().getWellSampleImages().get(counter +1).isDisplayed();
                counter += 1;
            }
        });

        And("^Verify that the Results label is present$", () -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getReviewPage().getResultsLabel().isDisplayed());
        });

        And("^Verify that the \"([^\"]*)\" is present$", (String icon) -> {
            Thread.sleep(1000);
            assertTrue(scenarioContext.getReviewPage().getWellButtonByIcon(icon).isDisplayed());
        });

        And("^Verify that the file-history Icon is present$", () ->
            assertTrue(scenarioContext.getReviewPage().getIconFileHistory().isEnabled())
        );

        And("^User selects the first Positive Well in list$", () ->
            scenarioContext.getReviewPage().getFirstPositiveWell().click()
        );

        And("^Verify that the \"([^\"]*)\" is selected$", (String icon) -> {
            System.out.println(String.format("icon: %s - isSelected: %s", icon, scenarioContext.getReviewPage().getWellButtonByIcon(icon).isSelected()));
            assertTrue(scenarioContext.getReviewPage().getWellButtonByIcon(icon).getAttribute("class").contains("selected"));
        });

        And("^Verify that the \"([^\"]*)\" is not selected$", (String icon) ->
            assertFalse(scenarioContext.getReviewPage().getWellButtonByIcon(icon).getAttribute("class").contains("selected"))
        );

        And("^User clicks on the \"([^\"]*)\"$", (String icon) -> {
            scenarioContext.getReviewPage().getWellButtonByIcon(icon).click();
            Thread.sleep(800);
        });

        Then("^Verify that Well Samples has a \"([^\"]*)\" Result$", (String result) ->
            assertTrue(!scenarioContext.getReviewPage().getSamplesByResult(result.toLowerCase()).isEmpty())
        );

        And("^Verify all the \"([^\"]*)\" Wells in Slide Preview are not clickable$", (String result) -> {
            for(WebElement well: scenarioContext.getReviewPage().getPreviewSamplesByResult(result.toLowerCase())) {
                well.click();
                Thread.sleep(3000);
                assertFalse(well.getAttribute("class").contains("selected"));
            }
        });

        And("^Verify all the \"([^\"]*)\" Wells in Slide Preview are clickable$", (String result) -> {
            for(WebElement well: scenarioContext.getReviewPage().getPreviewSamplesByResult(result.toLowerCase())) {
                well.click();
                Thread.sleep(4000);
                assertTrue(well.getAttribute("class").contains("selected"));
            }
        });

        When("^Verify following icons in Sample Result are present$", (DataTable entries) -> {
            List<String> icons = entries.asList();
            for(String icon: icons)
                assertTrue(scenarioContext.getReviewPage().getResultButtonByIcon(icon.toLowerCase()).isDisplayed());
        });

        When("^User selects \"([^\"]*)\" in Sample Result$", (String result) -> {
            scenarioContext.getReviewPage().getResultButtonByIcon(result.toLowerCase()).click();
            Thread.sleep(3000);
        });

        Then("^Verify Icon in Well List is \"([^\"]*)\"$", (String result) ->
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleIcon().getAttribute("class").contains(result.toLowerCase()))
        );

        Then("^Verify Icon in Slide Preview is \"([^\"]*)\"$", (String result) ->
            assertTrue(scenarioContext.getReviewPage().getSelectedSampleSlidePreviewIcon().getAttribute("class").contains(result.toLowerCase()))
        );

        And("^Verify the Filter Window is Present$", () ->
            assertTrue(scenarioContext.getReviewPage().getFilterWindow().isDisplayed())
        );

        And("^Verify the Filter Window is Closed", () ->
            assertTrue(scenarioContext.getReviewPage().getFilterWindows().isEmpty())
        );

        Then("^User clicks on Show Filter button$", () ->
            scenarioContext.getReviewPage().getFilterButton().click()
        );

        When("^User Unchecks the Option \"([^\"]*)\" in Filter \"([^\"]*)\"$", (String option, String filter) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            if(scenarioContext.getReviewPage().getOptionInFilter(option, filter).isSelected())
                executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter(option, filter));
        });

        When("^User Checks the Option \"([^\"]*)\" in Filter \"([^\"]*)\"$", (String option, String filter) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            if(!scenarioContext.getReviewPage().getOptionInFilter(option, filter).isSelected())
                executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getOptionInFilter(option, filter));
        });

        Then("^User clicks on \"([^\"]*)\" button$", (String button) -> {
            JavascriptExecutor executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getReviewPage().getButtonByName(button));
            Thread.sleep(3000);
        });

        And("^Verify Well Samples do not contain \"([^\"]*)\"$", (String icon) -> {
            for(WebElement sampleIcon: scenarioContext.getReviewPage().getAllWellSampleIcons())
                assertFalse(sampleIcon.getAttribute("class").contains(icon.toLowerCase()));
        });

        And("^Verify Well Samples contain only \"([^\"]*)\"$", (String icon) -> {
            for(WebElement sampleIcon: scenarioContext.getReviewPage().getAllWellSampleIcons())
                assertTrue(sampleIcon.getAttribute("class").contains(icon.toLowerCase()));
        });

        Then("^Navigate through all Images in the Pattern clicking in the next arrow$", () -> {
            while(!scenarioContext.getReviewPage().getWellImageNextButton().getAttribute("class").contains("disabled"))
                scenarioContext.getReviewPage().getWellImageNextButton().click();
        });

        And("^Navigate through all Images in the Pattern clicking in the previous arrow$", () -> {
            while(!scenarioContext.getReviewPage().getWellImagePreviousButton().getAttribute("class").contains("disabled"))
                scenarioContext.getReviewPage().getWellImagePreviousButton().click();
        });

        Then("^Verify Mitotic Components are present$", () -> {
            for(WebElement mitoticImage: scenarioContext.getReviewPage().getMitoticImages())
                assertTrue(mitoticImage.isDisplayed());
        });

        And("^Verify Mitotic Components have title$", () -> {
            for(WebElement mitoticImage: scenarioContext.getReviewPage().getMitoticImages())
                assertTrue(mitoticImage.findElement(By.xpath("./following-sibling::div[1]")).getText().contains("Mitotic"));
        });

        Then("^Verify Sample Image is displayed$", () ->
            assertTrue(scenarioContext.getReviewPage().getActiveSampleImage().isDisplayed())
        );

        And("^Store selected sample result$", () ->
            selectedResult = scenarioContext.getReviewPage().getSelectedSampleResult().getAttribute("class")
        );

        And("^Select the initial sample result$", () -> {
            scenarioContext.getReviewPage().getResultButtonByIcon(selectedResult.replace("icon-sample-","").trim()).click();
            Thread.sleep(1000);
        });

        Then("^Verify \"([^\"]*)\" Icon in Sample Result is selected$", (String result) ->
            assertTrue(scenarioContext.getReviewPage().getResultButtonByIcon(result.toLowerCase()).getAttribute("class").contains("selected"))
        );

        Then("^Verify \"([^\"]*)\" Icon in Sample Result is not selected$", (String result) ->
            assertFalse(scenarioContext.getReviewPage().getResultButtonByIcon(result.toLowerCase()).getAttribute("class").contains("selected"))
        );
    }
}