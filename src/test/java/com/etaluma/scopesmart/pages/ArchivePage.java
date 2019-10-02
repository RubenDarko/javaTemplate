package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class ArchivePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'close')]")
    private WebElement samplesWindowCloseButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'icon-navarrow-back')]")
    private WebElement samplesWindowBackwardButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'icon-navarrow-next')]")
    private WebElement samplesWindowForwardButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'pdf-preview')]")
    private WebElement previewPdfWindow;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-close-x')]")
    private WebElement closePanelIcon;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'footer-page')]")
    private WebElement previewPdfPageFooter;

    public ArchivePage(WebDriver driver) {
        super(driver);
        HomePage homePage = new HomePage(driver);
        homePage.gotoArchive();
        waitForPageToLoad();
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

    public WebElement getGenericMessage(String message) {
        By genericElement = By.xpath("//*[contains(text(),'" + message + "')]");
        waitForElementToAppear(driver.findElement(genericElement));
        return driver.findElement(genericElement);
    }

    public List<WebElement> getRadioButtons() {
        By radioButtons = By.xpath("//input[@type='radio']/..");
        return driver.findElements(radioButtons);
    }

    public WebElement getTextFieldByLabel(String label) {
        By textField = By.xpath("//input[contains(@name,'" + label.toLowerCase() + "')]");
        waitForElementToAppear(driver.findElement(textField));
        return driver.findElement(textField);
    }

    public WebElement getComboBox(String label) {
        By comboBox = By.xpath("//label[contains(text(),'" + label + "')]/following-sibling::div//div[@class='dropdown']");
        waitForElementToAppear(driver.findElement(comboBox));
        return driver.findElement(comboBox);
    }

    public WebElement getComboBoxWithValue(String label, String value) {
        By comboBox = By.xpath("//label[contains(text(),'" + label + "')]/following-sibling::div//span[text()='" + value + "']");
        waitForElementToAppear(driver.findElement(comboBox));
        return driver.findElement(comboBox);
    }

    public WebElement getCheckBoxByLabel(String label) {
        By checkBox = By.xpath("//input[@name='" + label.toUpperCase() + "']");
        waitForElementToAppear(driver.findElement(checkBox));
        return driver.findElement(checkBox);
    }

    public WebElement getReferenceLibraryComboBox() {
        By referenceLibraryComboBox = By.xpath("//select[@class='pattern-library-select']");
        waitForElementToAppear(driver.findElement(referenceLibraryComboBox));
        return driver.findElement(referenceLibraryComboBox);
    }

    public WebElement getButtonByLabel(String label) {
        By button = By.xpath("//button[text()='" + label + "']");
        waitForElementToAppear(driver.findElement(button));
        return driver.findElement(button);
    }

    public WebElement getButtonByIcon(String icon) {
        By button = By.xpath("//button/span[contains(@class,'" + icon.toLowerCase() + "')]");
        waitForElementToAppear(driver.findElement(button));
        return driver.findElement(button);
    }

    public WebElement getTileByLabel(String tile) {
        By button = By.xpath("//span[text()='" + tile + "']/../preceding-sibling::span");
        waitForElementToAppear(driver.findElement(button));
        return driver.findElement(button);
    }

    public List<WebElement> getValuesInColumn(int column) {
        By listOfValues = By.xpath("//div[contains(@class,'TableRow')]/div[" + column +"]/div[contains(@class,'cell-content')]");
        return driver.findElements(listOfValues);
    }

    public List<WebElement> getTableCheckBoxes() {
        By tableCheckBoxes = By.xpath("//div[contains(@class,'TableRow')]/div[1]");
        return driver.findElements(tableCheckBoxes);
    }

    public List<WebElement> getSamplesWindow() {
        By samplesWindow = By.xpath("//div[contains(@class, 'modal-body')]");
        return driver.findElements(samplesWindow);
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
        return counter+2;
    }

    public WebElement getGenericIcon(String icon) {
        By genericIcon = By.xpath("//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(genericIcon));
        return driver.findElement(genericIcon);
    }

    public WebElement getSampleIDFromSamplesWindow(String sampleID) {
        By sampleIDFromSamplesWindow  = By.xpath("//div[contains(@class, 'archive-samples-result-list-item')][" + sampleID + "]/*[1]");
        return driver.findElement(sampleIDFromSamplesWindow);
    }

    public List<WebElement> getReferenceLibraryImagesSection() {
        By referenceLibraryImagesSection = By.xpath("//div[contains(@class, 'slider-wrapper axis-horizontal')]");
        return driver.findElements(referenceLibraryImagesSection);
    }

    public List<WebElement> getReferenceLibraryImages() {
        By referenceLibraryImages = By.xpath("//ul[contains(@class, 'control-dots')]/*");
        return driver.findElements(referenceLibraryImages);
    }

    public List<WebElement> getComboBoxSelectedItems(String comboBox) {
        By items = By.xpath("//label[contains(text(),'" + comboBox + "')]/following-sibling::div//label[@aria-selected='true']");
        return driver.findElements(items);
    }

    public WebElement getPdfPreviewTitle(String title) {
        By document = By.xpath("//div[@class='react-pdf__Page__textContent']//span[text()='" + title + "']");
        return driver.findElement(document);
    }

    public WebElement getSamplesWindowCloseButton() { return samplesWindowCloseButton; }

    public WebElement getSamplesWindowBackwardButton() { return samplesWindowBackwardButton; }

    public WebElement getSamplesWindowForwardButton() { return samplesWindowForwardButton; }

    public WebElement getPreviewPdfWindow() {
        return previewPdfWindow;
    }

    public WebElement getClosePanelIcon() {
        return closePanelIcon;
    }

    public WebElement getPreviewPdfPageFooter() {
        return previewPdfPageFooter;
    }
}
