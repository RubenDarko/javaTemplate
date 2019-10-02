package com.etaluma.scopesmart.steps;

import com.etaluma.scopesmart.cucumber.ScenarioContext;
import com.etaluma.scopesmart.pages.SystemConfigurationPage;
import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import static org.junit.Assert.*;

public class MaintenanceHistoryReport extends BaseStep {

    ArrayList<String> tableRecords = new ArrayList<>();
    Date topDate;
    Date bottomDate;
    Date currentDate;

    public MaintenanceHistoryReport(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;

        After(new String[]{"@MaintenanceHistoryReport"},(Scenario scenario)->
            super.teardown(scenario)
        );

        After(new String[]{"@MaintenanceLog"},(Scenario scenario)->
            super.teardown(scenario)
        );

        After(new String[]{"@MaintenanceHistoryDownload"},(Scenario scenario)->
            super.teardown(scenario)
        );

        After(new String[]{"@MaintenanceDateRange"},(Scenario scenario)->
            super.teardown(scenario)
        );

        And("^Verify CSV File is downloaded$", () -> {
            Thread.sleep(1500);
            File tempFile = new File(downloadFilePath);
            boolean flag = false;
            for(File file: tempFile.listFiles()) {
                if(file.getName().contains("csv")) {
                    flag=true;
                }
            }
            assertTrue(flag);
        });

        Then("^Click on the \"([^\"]*)\" icon$", (String icon) ->
            scenarioContext.getSystemConfigurationPage().getButtonByIcon(icon).click()
        );

        Then("^Verify that the \"([^\"]*)\" icon is present$", (String icon) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getButtonByIcon(icon).isDisplayed())
        );

        And("^Validate that Maintenance Log Report panel is displayed$", () -> {
            scenarioContext.getSystemConfigurationPage().waitForDocument();
            assertTrue(scenarioContext.getSystemConfigurationPage().getMaintenanceLogPanel().isDisplayed());
        });

        And("^Schedule a sample maintenance for testing$", () -> {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.add(Calendar.DATE, -1);
            currentDate = mCalendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String record = "TestRecord: " + formatter.format(date);

            tableRecords.clear();
            Thread.sleep(500);
            scenarioContext.getSystemConfigurationPage().getMaintenanceDescriptionArea().clear();
            Thread.sleep(500);
            scenarioContext.getSystemConfigurationPage().getMaintenanceDescriptionArea().sendKeys(record);
            Thread.sleep(500);
            scenarioContext.getSystemConfigurationPage().getButtonByIcon("file-add").click();
            Thread.sleep(5000);
            assertTrue(scenarioContext.getSystemConfigurationPage().getTableRecord(record).isDisplayed());
            for(WebElement tableCell: scenarioContext.getSystemConfigurationPage().getMaintenanceTableRecords())
                tableRecords.add(tableCell.getText());
        });

        And("^Verify message \"([^\"]*)\" is present in System Configuration$", (String message) -> {
            scenarioContext.setSystemConfigurationPage(new SystemConfigurationPage(scenarioContext.getDriver()));
            assertTrue(scenarioContext.getSystemConfigurationPage().getGenericObject(message).isDisplayed());
        });

        Then("^Verify Maintenance Log Report contents$", () -> {
            for(String record: tableRecords)
                assertTrue(scenarioContext.getSystemConfigurationPage().getPdfRecordByValue(record).isDisplayed());
        });

        And("^User clicks on \"([^\"]*)\" icon in Maintenance PDF Window$", (String icon) -> {
            Thread.sleep(2000);
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getGenericIcon(icon));
            Thread.sleep(2000);
        });

        Then("^Verify that the radio button \"([^\"]*)\" is present$", (String radio) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getRadioButtonByLabel(radio).isDisplayed())
        );

        And("^Click on the radio button \"([^\"]*)\"$", (String radio) -> {
            Thread.sleep(500);
            scenarioContext.getSystemConfigurationPage().getRadioButtonByLabel(radio).click();
        });

        And("^Verify the Maintenance Description Text Area$", () -> {
            assertTrue(scenarioContext.getSystemConfigurationPage().getMaintenanceDescriptionArea().isDisplayed());
            assertEquals(scenarioContext.getSystemConfigurationPage().getMaintenanceDescriptionArea().getAttribute("placeholder"),"Enter Description");
        });

        Then("^Verify title is \"([^\"]*)\" in System Configuration$", (String title) ->
            assertEquals(scenarioContext.getSystemConfigurationPage().getHeaderTitle().getText(),title)
        );

        Then("^Verify the Maintenance Table is present in System Configuration$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getMaintenanceTable().isDisplayed())
        );

        And("^User clicks on \"([^\"]*)\" icon in Maintenance Page$", (String icon) -> {
            Thread.sleep(2000);
            JavascriptExecutor executor;
            executor = scenarioContext.getDriver();
            executor.executeScript("arguments[0].click();", scenarioContext.getSystemConfigurationPage().getIcon(icon));
            Thread.sleep(3000);
        });

        And("^Verify \"([^\"]*)\" as Table Header$", (String header) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getTableColumnByLabel(header).isDisplayed())
        );

        And("^Verify the Date Range component is present$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getDateRangePicker().isDisplayed())
        );

        Then("^Validate cell value is \"([^\"]*)\" in added record$", (String value) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getTableRecord(value).isDisplayed())
        );

        Then("^Verify Date Range window is present$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getDateRangePickerWindow().isDisplayed())
        );

        And("^Verify Date Range window is not present$", () ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getDateRangePickerWindows().isEmpty())
        );

        And("^Select the \"([^\"]*)\" in Date Range Picker$", (String range) -> {
            Calendar mCalendar = Calendar.getInstance();
            String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            int year = Calendar.getInstance().get(Calendar.YEAR);
            scenarioContext.getSystemConfigurationPage().selectYearInDatePicker(Integer.toString(year));
            scenarioContext.getSystemConfigurationPage().selectMonthInDatePicker(month);

            switch(range) {
                case "Previous":
                    scenarioContext.getSystemConfigurationPage().getPreviousMonthArrow().click();
                    topDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    mCalendar.add(Calendar.DATE, -7);
                    bottomDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    break;
                case "Next":
                    bottomDate = mCalendar.getTime();
                    mCalendar.add(Calendar.DATE, 1);
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    mCalendar.add(Calendar.DATE, 7);
                    topDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    break;
                case "Yesterday":
                    scenarioContext.getSystemConfigurationPage().getPreviousMonthArrow().click();
                    topDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    mCalendar.add(Calendar.DATE, -1);
                    bottomDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    break;
                case "Tomorrow":
                    bottomDate = currentDate;
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    mCalendar.add(Calendar.DATE, 1);
                    topDate = mCalendar.getTime();
                    scenarioContext.getSystemConfigurationPage().getDateInPicker(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), mCalendar.get(Calendar.DAY_OF_MONTH)).click();
                    break;
            }
        });

        When("^User clicks on Apply button in Date Range Picker$", () -> {
            scenarioContext.getSystemConfigurationPage().getApplyDateButton().click();
            while(!scenarioContext.getSystemConfigurationPage().getDateRangePickerWindows().isEmpty())
                Thread.sleep(100);
        });

        Then("^Validate records in table match the date range$", () -> {
            if(scenarioContext.getSystemConfigurationPage().valuesExist()) {
                int index = scenarioContext.getSystemConfigurationPage().getColumnNumber("Time");
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
                Date temp;

                for (WebElement record : scenarioContext.getSystemConfigurationPage().getValuesInColumn(index)) {
                    if (!record.getText().equals("")) {
                        temp = dateFormat.parse(record.getText());
                        assertTrue(temp.after(bottomDate));
                        assertTrue(temp.before(topDate));
                    }
                }
            }
        });

        And("^Verify \"([^\"]*)\" Icon in System Configuration$", (String icon) ->
            assertTrue(scenarioContext.getSystemConfigurationPage().getIcon(icon).isDisplayed())
        );

        And("^Verify Date Range Picker dates$", () -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String start;
            String end;
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.getTime();
            mCalendar.add(Calendar.DATE, 1);
            end = dateFormat.format(mCalendar.getTime());
            mCalendar.add(Calendar.DATE, -91);
            start = dateFormat.format(mCalendar.getTime());
            assertEquals(start + " ~ " + end, scenarioContext.getSystemConfigurationPage().getDateRangePicker().getText());
        });
    }
}