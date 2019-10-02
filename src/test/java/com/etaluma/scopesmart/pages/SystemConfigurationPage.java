package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SystemConfigurationPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[text()='Maintenance Log Report']")
    private WebElement maintenanceLogPanel;

    @FindBy(how = How.XPATH, using = "//textarea[@name='maintenance-description']")
    private WebElement maintenanceDescriptionArea;

    @FindBy(how = How.XPATH, using = "//h1[@class='header-title']")
    private WebElement headerTitle;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'rdt_Table')]")
    private WebElement maintenanceTable;

    @FindBy(how = How.ID, using = "range-calendar-input")
    private WebElement dataRangePicker;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'PaginationArrow--previous')]")
    private WebElement previousMonthArrow;

    @FindAll(@FindBy(how = How.CLASS_NAME, using = "date-range-picker-input"))
    private List<WebElement> maintenanceTableRecords;

    @FindBy(how = How.CLASS_NAME, using = "popover-inner")
    private WebElement dateRangePickerWindow;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Test Connection')]")
    private WebElement testConnectionButton;

    @FindBy(how = How.XPATH, using = "//input[@value='Apply']")
    private WebElement applyDateButton;

    @FindBy(how = How.XPATH, using = "//div[@class='data-table-container']")
    private WebElement usersInformationTable;

    public SystemConfigurationPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
    }

    public void expandSystemMenu(String subMenu) {
        By menuOption = By.xpath("//*[contains(@class,'tree')]//*[text()='" + subMenu + "']/..");
        waitForElementToAppear(driver.findElement(menuOption));
        if(!driver.findElement(menuOption).getAttribute("class").contains("toggled")) {
            driver.findElement(menuOption).click();
        }
    }

    public WebElement getSubMenuOption(String subMenu) {
        By menuOption = By.xpath("//span[@class='link'][text()='" + subMenu + "']");
        waitForElementToAppear(driver.findElement(menuOption));
        return driver.findElement(menuOption);
    }

    public WebElement getTextInSummary(String message) {
        By genericObject = By.xpath("//div[@class='System-Config-Module']//*[contains(text(),'" + message + "')]");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public WebElement getComponentByText(String text) {
        By component = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + text + "')]/following-sibling::div");
        waitForElementToAppear(driver.findElement(component));
        return driver.findElement(component);
    }

    public WebElement getLabel(String text) {
        By component = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + text + "')]");
        waitForElementToAppear(driver.findElement(component));
        return driver.findElement(component);
    }

    public WebElement getTextAreaByComponent(String component) {
        By area = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + component + "')]/following-sibling::div//textarea");
        waitForElementToAppear(driver.findElement(area));
        return driver.findElement(area);
    }

    public List<WebElement> getTextAreasByComponent(String component) {
        By area = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + component + "')]/following-sibling::div//textarea");
        return driver.findElements(area);
    }

    public List<WebElement> getDropdownsByComponent(String component) {
        By dropdown = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + component + "')]/following-sibling::div//select");
        return driver.findElements(dropdown);
    }

    public List<WebElement> getOptionsInDropdown(String component) {
        By options = By.xpath("//div[@styles='[object Object]'][contains(text(),'" + component + "')]/following-sibling::div//option");
        return driver.findElements(options);
    }

    public WebElement getButtonByIcon(String icon) {
        By component = By.xpath("//span[contains(@class,'" + icon + "')]/..");
        waitForElementToAppear(driver.findElement(component));
        return driver.findElement(component);
    }

    public WebElement getTableRecord(String record) {
        By tableRow = By.xpath("//div[contains(@class,'TableRow')]//div[text()='" + record + "']");
        waitForElementToAppear(driver.findElement(tableRow));
        return driver.findElement(tableRow);
    }

    public WebElement getPdfRecordByValue(String value) {
        By pdfRecord = By.xpath("//div[@class='react-pdf__Page__textContent']/span[text()='" + value + "']");
        waitForElementToAppear(driver.findElement(pdfRecord));
        return driver.findElement(pdfRecord);
    }

    public void waitForDocument() throws InterruptedException {
        int counter=0;
        By document = By.xpath("//canvas[@class='react-pdf__Page__canvas']");
        while(driver.findElements(document).isEmpty() && counter<500) {
            Thread.sleep(10);
            counter++;
        }
        Thread.sleep(2000);
    }

    public WebElement getGenericObject(String message) {
        By genericObject = By.xpath("//*[contains(text(),'" + message + "')]");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public WebElement getObjectByLabel(String message) {
        By genericObject = By.xpath("//*[contains(text(),'" + message + "')][@styles='[object Object]']");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public WebElement getToggleByLabel(String toggle) {
        By genericObject = By.xpath("//input[@checked]/../../../..//*[contains(text(),'" + toggle + "')]/following-sibling::div//input[@class='custom-control-input']");
        return driver.findElement(genericObject);
    }

    public WebElement getGenericIcon(String icon) {
        By genericIcon = By.xpath("//button//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(genericIcon));
        return driver.findElement(genericIcon);
    }

    public WebElement getIcon(String icon) {
        By genericIcon = By.xpath("//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(genericIcon));
        return driver.findElement(genericIcon);
    }

    public WebElement getRadioButtonByLabel(String label) {
        By radioButton = By.xpath("//span[text()='" + label + "']/preceding-sibling::input[@type='radio']");
        waitForElementToAppear(driver.findElement(radioButton));
        return driver.findElement(radioButton);
    }

    public WebElement getTableColumnByLabel(String column) {
        By columnHeader = By.xpath("//div[contains(@id,'column')]/div[text()='" + column + "']");
        waitForElementToAppear(driver.findElement(columnHeader));
        return driver.findElement(columnHeader);
    }

    public int getColumnNumber(String column) {
        int counter=0;
        By tableColumns = By.xpath("//div[contains(@id,'column')]/div");
        List<WebElement> columns = driver.findElements(tableColumns);
        for(int i=0; i<columns.size(); i++) {
            if(columns.get(i).getText().equals(column)) {
                counter=i;
                break;
            }
        }
        return counter+1;
    }

    public int getRowNumberInColumn(String row, int column) {
        int counter=0;
        By tableRows = By.xpath("//div[contains(@class,'TableRow')]/div[" + column + "]/div");
        List<WebElement> rows = driver.findElements(tableRows);
        for(int i=0; i<rows.size(); i++) {
            if(rows.get(i).getText().equals(row)) {
                counter=i;
                break;
            }
        }
        return counter+1;
    }

    public WebElement getLinkArrowInRow(int row) {
        By arrowLink = By.xpath("(//div[contains(@class,'TableRow')]//button)[" + row + "]");
        waitForElementToAppear(driver.findElement(arrowLink));
        return driver.findElement(arrowLink);
    }

    public boolean valuesExist() {
        By listOfValues = By.xpath("//div[text()='There are no records to display']");
        if(driver.findElements(listOfValues).isEmpty())
            return true;
        else
            return false;
    }

    public List<WebElement> getValuesInColumn(int column) {
        By listOfValues = By.xpath("//div[contains(@class,'TableRow')]/div[" + column +"]/div[contains(@class,'cell-content')]");
        return driver.findElements(listOfValues);
    }

    public WebElement getLisRadioButton(String radio) {
        By radioButton = By.xpath("//label[text()='" + radio + "']/input[@type='radio']");
        return driver.findElement(radioButton);
    }

    public void selectYearInDatePicker(String year) {
        By yearDatePicker = By.xpath("//span[contains(@class,'MonthHeaderLabel--year')]//select");
        driver.findElement(yearDatePicker).click();
        for(WebElement years : new Select(driver.findElement(yearDatePicker)).getOptions()){
            if(years.getText().equals(year)) {
                years.click();
                break;
            }
        }
    }

    public void selectMonthInDatePicker(String month) {
        By monthDatePicker = By.xpath("//span[contains(@class,'MonthHeaderLabel--month')]//select");
        driver.findElement(monthDatePicker).click();
        for(WebElement months : new Select(driver.findElement(monthDatePicker)).getOptions()){
            if(months.getText().equals(month)) {
                months.click();
                break;
            }
        }
    }

    public WebElement getDateInPicker(String month, int day) {
        By dateElement = By.xpath("//span[text()='" + month + "']/..//following-sibling::table//td[not(contains(@class,'other'))]//span[text()='" + day + "']");
        return driver.findElement(dateElement);
    }

    public WebElement getTextFieldByLabel(String label) {
        By textField = By.xpath("//span[contains(text(),'" + label + "')]/../following-sibling::div/input");
        waitForElementToAppear(driver.findElement(textField));
        return driver.findElement(textField);
    }

    public WebElement getUserInformationField(String field) {
        By textField = By.xpath("//span[contains(text(),'" + field + "')]/../following-sibling::div[1]/div");
        waitForElementToAppear(driver.findElement(textField));
        return driver.findElement(textField);
    }

    public WebElement getUserInformationLabel(String field) {
        By textField = By.xpath("//span[contains(text(),'" + field + "')]");
        waitForElementToAppear(driver.findElement(textField));
        return driver.findElement(textField);
    }

    public WebElement getUserInformationInput(String field) {
        By userInput = By.xpath("//span[contains(text(),'" + field + "')]/../following-sibling::div[1]//input");
        waitForElementToAppear(driver.findElement(userInput));
        return driver.findElement(userInput);
    }

    public List<WebElement> getUserInformationInputs(String field) {
        By userInput = By.xpath("//span[text()='" + field + "']/../following-sibling::div[1]//input");
        return driver.findElements(userInput);
    }

    public WebElement getComboBoxByLabel(String label) {
        By comboBox = By.xpath("//span[contains(text(),'" + label + "')]/../following-sibling::select");
        waitForElementToAppear(driver.findElement(comboBox));
        return driver.findElement(comboBox);
    }

    public WebElement getPermissionByLabel(String label) {
        By permission = By.xpath("//span[text()='" + label + "']");
        waitForElementToAppear(driver.findElement(permission));
        return driver.findElement(permission);
    }

    public WebElement getPermissionCheckbox(String permission) {
        By element = By.xpath("//span[text()='" + permission + "']/../following-sibling::label[1]/input[@type='checkbox']");
        return driver.findElement(element);
    }

    public WebElement getDateRangePicker() {
        return dataRangePicker;
    }

    public WebElement getHeaderTitle() {
        return headerTitle;
    }

    public WebElement getMaintenanceTable() {
        return maintenanceTable;
    }

    public WebElement getMaintenanceLogPanel() {
        return maintenanceLogPanel;
    }

    public WebElement getMaintenanceDescriptionArea() {
        return maintenanceDescriptionArea;
    }

    public List<WebElement> getMaintenanceTableRecords() {
        return maintenanceTableRecords;
    }

    public WebElement getTestConnectionButton() {
        return testConnectionButton;
    }

    public WebElement getDateRangePickerWindow() {
        return dateRangePickerWindow;
    }

    public List<WebElement> getDateRangePickerWindows() {
        By dateWindow = By.className("popover-inner");
        return driver.findElements(dateWindow);
    }

    public WebElement getPreviousMonthArrow() {
        return previousMonthArrow;
    }

    public WebElement getApplyDateButton() {
        return applyDateButton;
    }

    public WebElement getUsersInformationTable() {
        return usersInformationTable;
    }
}