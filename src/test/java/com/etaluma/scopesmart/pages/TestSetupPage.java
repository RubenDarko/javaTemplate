package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class TestSetupPage extends BasePage {

    public static final String OFFLINE = "offline";

    @FindBy(how = How.CSS, using = "div span.icon-scanner")
    private WebElement iconScanner;

    @FindAll(@FindBy(how = How.XPATH, using = "//select[@class='input-group']"))
    private WebElement languageCombo;

    @FindBy(how = How.CSS, using = "span.icon-new-slide")
    private WebElement iconNewSlide;

    @FindBy(how = How.XPATH, using = "//div[@class='Batch-Name']")
    public WebElement testBatchList;

    @FindBy (how = How.XPATH, using = "//button/span[contains(@class,'icon-checkmark')]")
    public WebElement approveCheckMark;

    @FindBy (how = How.XPATH, using = "//img[@alt='Kit instructions']")
    public WebElement scanKit;

    @FindBy (how = How.XPATH, using = "//div[@class='bg-dark-80']")
    public WebElement testBatchWorkspace;

    @FindBy (how = How.XPATH, using = "//div[@class='bg-dark-20']")
    public WebElement testBatchListWorkspace;

    @FindBy (how = How.XPATH, using = "//button/span[contains(@class,'icon-edit-pencil')]")
    public WebElement editTestBatchButton;

    @FindBy (how = How.XPATH, using = "//div[@class='slide-preview-outer selected']//span[@class='slide-preview-barcode-text']")
    public WebElement selectedSlide;

    @FindBy (how = How.XPATH, using = "//span[contains(@class,'icon-checkmark')]")
    public WebElement approveTestBatchCheckMark;

    @FindBy (how = How.XPATH, using = "//span[contains(@class,'icon-edit-pencil')]")
    public WebElement editTestBatchIcon;

    @FindBy (how = How.CLASS_NAME, using = "icon-newtestbatch")
    private WebElement addNewTestBatch;

    @FindBy (how = How.CLASS_NAME, using = "modal-content")
    private WebElement createTestBatchWindow;

    @FindBy (how = How.ID, using = "add-test-batch-slide-type")
    private WebElement slideType;

    @FindBy (how = How.ID, using = "add-test-batch-kit-part-num")
    private WebElement kitPart;

    @FindBy (how = How.ID, using = "add-test-batch-kit-lot-num")
    private WebElement kitLot;

    @FindBy (how = How.ID, using = "add-test-batch-expiration-date")
    private WebElement expirationDate;

    @FindBy (how = How.ID, using = "add-test-batch-cofactor")
    private WebElement cofactorValue;

    @FindBy (how = How.ID, using = "add-test-batch-checksum")
    private WebElement checksum;

    @FindBy (how = How.XPATH, using = "//button[contains(@class, 'button-verify')]")
    private WebElement checksumButton;

    @FindBy (how = How.XPATH, using = "//button/span[text()='Create Test Batch']")
    private WebElement createTestBatchButton;

    @FindBy (how = How.XPATH, using = "//div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary disabled']")
    private WebElement disabledCreatedTestBatchButton;

    @FindBy (how = How.XPATH, using = "//div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary']")
    private WebElement enabledCreatedTestBatchButton;

    @FindAll(@FindBy (how = How.XPATH, using = "//span[@id='verify']/span[contains(@class,'icon-warning-circle')]"))
    private List<WebElement> checksumFails;

    @FindAll(@FindBy (how = How.XPATH, using = "//span[@id='verify']/span[contains(@class,'icon-checkmark')]"))
    private List<WebElement> checksumOks;

    @FindBy (how = How.XPATH, using = "//span[@id='verify']/span[contains(@class,'icon-warning-circle')]")
    private WebElement checksumFail;

    @FindBy (how = How.XPATH, using = "//span[@id='verify']/span[contains(@class,'icon-checkmark')]")
    private WebElement checksumOk;

    @FindBy (how = How.XPATH, using = "//div[@class='Batch-List-Item selected']/div[@class='Batch-Name']/div[contains(@style, 'cursor: pointer')]")
    private WebElement selectedBatchName;

    @FindBy (how = How.XPATH, using = "//span[text()='Worklist']/../following-sibling::div/child::a")
    private WebElement worklist;

    @FindBy (how = How.XPATH, using = "//div[@class='worklist-autocomplete-body']//input")
    private WebElement worklistTextField;

    @FindBy (how = How.XPATH, using = "//span[@class='icon-close-x ']")
    private WebElement worklistDeleteButton;

    @FindBy (how = How.XPATH, using = "//button[@class='worklist-autocomplete-button-container btn btn-secondary']/span[contains(@class,'icon-checkmark')]")
    private WebElement worklistOkButton;

    @FindBy (how = How.XPATH, using = "//input[@class='react-autosuggest__input']")
    private WebElement worklistInput;

    @FindBy (how = How.XPATH, using = "//div[@class='Batch-List-Item '][last()]")
    private WebElement lastUnselectedTestBatch;

    @FindBy (how = How.XPATH, using = "//div[@class='Batch-List-Item selected']/child::div[2]/child::div[1]/child::div[2]/child::span[1]")
    private WebElement addNewSlideButton;

    @FindBy (how = How.XPATH, using = "//input[@id='slideId']")
    private WebElement CreateSlideInput;

    @FindBy (how = How.XPATH, using = "//button[@class='Create-slide-button']")
    private WebElement CreateSlideButton;

    @FindBy (how = How.XPATH, using = "//td/span[contains(text(), 'Patient')]")
    private WebElement slideSampleIDSpanText;

    @FindBy (how = How.XPATH, using = "//button[@class='btn btn-link']/span[@class='icon-checkmark ']")
    private WebElement testBatchApproveIcon;

    @FindBy (how = How.XPATH, using = "//button[@class='btn btn-link']/span[@class='icon-edit-pencil ']")
    private WebElement testBatchPencilIcon;

    @FindBy (how = How.XPATH, using = "//div[@class='modal fade show']")
    private WebElement slideModal;

    @FindBy (how = How.XPATH, using = "//span[contains(@class, 'icon-auto-dilution')]/parent::button")
    private WebElement dilutionButton;

    @FindBy (how = How.XPATH, using = "//span[contains(@class, 'icon-clear')]")
    private WebElement clearSampleID;

    @FindBy (how = How.XPATH, using = "//div[@class='Slide-Selected row']/child::div[1]")
    private WebElement wellsWorkspace;

    @FindBy (how = How.XPATH, using = "//div[@class='Batch-Slides-List']/div/div[contains(@class, 'slide-preview-outer')]")
    private WebElement slide;

    @FindBy (how = How.XPATH, using = "//span[contains(@class, 'icon-trash')]/parent::button")
    private WebElement trashButton;

    @FindBy (how = How.XPATH, using = "//div[@class='modal-header']/button[@class='close']")
    private WebElement slideCloseButton;

    public By positiveControlListItems = By.xpath("//span[text()='Positive Control']/../following-sibling::div//li[@class='control-sample-li']");
    public By negativeControlListItems = By.xpath("//span[text()='Negative Control']/../following-sibling::div//li[@class='control-sample-li']");
    public By scanKitMessage = By.xpath("//span[text()='Scan a Kit Lot to create a new Test Batch.']");
    public By scanKitImage = By.xpath("//img[@alt='Kit instructions']");
    private TestSummaryPage testSummary;
    private TestBatchPage testBatchPage;
    private CreateSlideModal createSlideModal;

    public TestSetupPage(WebDriver driver) {
        super(driver);
        HomePage homePage = new HomePage(this.driver);
        homePage.gotoTestSetup();
        this.waitForPageToLoad();
    }

    public WebElement getAddNewTestBatch() {
        return addNewTestBatch;
    }

    public boolean isCreateSlideFormPresent() {
        By createSlideForm = By.xpath("//div[@class='Create-slide-title']");

        try {
            driver.findElement(createSlideForm);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public List<WebElement> getEditTestBatchButton() {
        By editTestBatchButton = By.xpath("//button/span[contains(@class,'icon-edit-pencil')]");
        driver.findElements(editTestBatchButton);
        return driver.findElements(editTestBatchButton);
    }

    public WebElement getSlideSampleID(String text) {
        By slideSampleID = By.xpath("//td[contains(text(), '" + text + "')]");
        return driver.findElement(slideSampleID);
    }

    public WebElement getSampleIDInputEditable(String well) {
        By sampleIDInputEditable = By.xpath("//td/child::input[@value='" + well + "']");
        return driver.findElement(sampleIDInputEditable);
    }

    public WebElement getSampleIDIcon(String well, String icon) {
        By sampleIDIcon = By.xpath("//td[text()='" + well + "']/following-sibling::td/descendant::span[2][contains(@class, '" +  icon + "')]");
        return driver.findElement(sampleIDIcon);
    }

    public WebElement getWellRow(String well) {
        By wellRow = By.xpath("//td[text()='" + well + "']/parent::tr");
        return driver.findElement(wellRow);
    }

    public WebElement getWellRowNumber(String well) {
        By wellRowNumber = By.xpath("//td[text()='" + well + "']");
        return driver.findElement(wellRowNumber);
    }

    public WebElement getSelectedSlide() {
        waitForElementToAppear(selectedSlide);
        return selectedSlide;
    }

    public WebElement getTestBarchWorkspace(){
        waitForElementToAppear(testBatchWorkspace);
        return testBatchWorkspace;
    }

    public boolean isScanKitMessageDisplayed() {
        return driver.findElement(this.scanKitMessage).isDisplayed();
    }

    public boolean isScanKitImageDisplayed() {
        return driver.findElement(this.scanKitImage).isDisplayed();
    }

    public void scanTestBatch(String testBatch){
        new Actions(driver).sendKeys(testBatch).perform();
        this.waitForPageToLoad();
        setTestSummary(new TestSummaryPage(driver));
    }

    public boolean isScannerConnected(){
        //TODO update to ONLINE once dev enable the ONLINE icon
        return getParentNode(iconScanner).getAttribute("class").contains(OFFLINE);
    }

    private WebElement getParentNode(WebElement myElement){
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", myElement);
    }

    public TestSummaryPage getTestSummary() {
        return testSummary;
    }

    public void setTestSummary(TestSummaryPage testSummary) {
        this.testSummary = testSummary;
    }

    public WebElement getAlert() {
        By bySelector = By.cssSelector(".Toastify__toast");
        this.isElementPresentWithWait(bySelector);
        return driver.findElement(bySelector);
    }

    public void waitForAlertNotPresent() {
        By bySelector = By.cssSelector(".Toastify__toast");
        while(driver.findElement(bySelector).isDisplayed());
    }

    public WebElement getSelectByLabel(String label) {
        By selectByLabel = By.xpath("//div[@class='modal-body']//span[contains(text(),'" + label + "')]/../following-sibling::div/*");
        return driver.findElement(selectByLabel);
    }

    public WebElement getDilutionDropdownValue(String value) {
        By dilutionDropdownValue = By.xpath("//select[@class='dilution-picker-select'][option='" + value + "']");
        return driver.findElement(dilutionDropdownValue);
    }

    public WebElement getInputByLabel(String label) {
        By inputByLabel = By.xpath("//div[@class='modal-body']//span[contains(text(),'" + label + "')]/../following-sibling::div/descendant::input");
        return driver.findElement(inputByLabel);
    }

    public boolean hasErrorAlert() {
        return getAlert().getAttribute("class").contains("warning") ||
                getAlert().getAttribute("class").contains("error") ;
    }

    public WebElement getSelectedBatch() {
        By byBatchListItemSelected = By.cssSelector(".Batch-List-Item.selected");
        this.waitForElementToAppear(driver.findElement(byBatchListItemSelected));
        return driver.findElement(byBatchListItemSelected);
    }

    public WebElement getSlideSampleIDInput(String well) {
        By slideSampleIDInput = By.xpath("//td[text()='" + well + "']/following-sibling::td/following-sibling::td/input[@class='well-sample-id-input']");
        return driver.findElement(slideSampleIDInput);
    }

    public WebElement getSlidePatientButton(String well) {
        By slidePatientButton = By.xpath("//td[text()='" + well + "']/following-sibling::td/button[@class='sample-switch-button btn btn-link']");
        return driver.findElement(slidePatientButton);
    }

    private static TestSetupPage single_instance = null;

    public void addNewSlide(String slideId) {
        iconNewSlide.click();
        this.waitForPageToLoad();
        setCreateSlideModal(new CreateSlideModal(driver));
        createSlideModal.addSlide(slideId);
        this.waitForPageToLoad();
        setTestBatchPage(new TestBatchPage(driver));
    }

    public void setCreateSlideModal(CreateSlideModal createSlideModal) {
        this.createSlideModal = createSlideModal;
    }

    public TestBatchPage getTestBatchPage() {
        return testBatchPage;
    }

    public void setTestBatchPage(TestBatchPage testBatchPage) {
        this.testBatchPage = testBatchPage;
    }

    public void confirmTestBatch() {
        waitForElementToAppear(driver.findElement(By.xpath("//a[text()='Yes']")));
        driver.findElement(By.xpath("//a[text()='Yes']")).click();
    }

    public void deleteTestBatch() {
        By deleteTestBatchBy = By.xpath("//button/span[contains(@class,'icon-trash')]");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(2000,0)");
        driver.findElement(deleteTestBatchBy).click();
    }

    public void confirmDeleteTestBatch() {
        By deleteTestBatchButton = By.id("jhi-confirm-delete-slide");
        wait.until(ExpectedConditions.presenceOfElementLocated(deleteTestBatchButton));
        driver.findElement(deleteTestBatchButton).click();
    }

    public void cancelDeleteTestBatch() {
        By cancelTestBatchButton = By.xpath("//span[text()='Cancel']/..");
        driver.findElement(cancelTestBatchButton).click();
    }

    public boolean isDeleteTestBatchAlertVisible() {
        By deleteTestBatchQuestionBy = By.id("scopeSmartApp.testBatch.delete.question");
        return driver.findElement(deleteTestBatchQuestionBy).isDisplayed();
    }

    public List<WebElement> getTestBatchesList() {
        By testBatchesList = By.xpath("//div[contains(@class,'Batch-List-Item')]");
        return driver.findElements(testBatchesList);
    }

    public List<WebElement> getSamplesIDEditable(String sampleID) {
        By samplesIDEditable = By.xpath("//input[@class='well-sample-id-input'][@value='" + sampleID + "']");
        return driver.findElements(samplesIDEditable);
    }

    public WebElement getEmptySampleIDEditable(String well) {
        By emptySampleIDEditable = By.xpath("//td[text()='" + well + "']/following-sibling::td[2]/input");
        return driver.findElement(emptySampleIDEditable);
    }

    public List<WebElement> getSlideEmptySamples() {
        By emptySlideSamples = By.xpath("//input[@class='well-sample-id-input'][@value='']");
        return driver.findElements(emptySlideSamples);
    }

    public List<WebElement> getSelectedTestBatch() {
        By selectedTestBatchList = By.xpath("//div[contains(@class,'Batch-List-Item selected')]/div[@class='Batch-Name']");
        return driver.findElements(selectedTestBatchList);
    }

    public void selectLanguage(String language) {
        By langCombo = By.xpath("//select[@class='input-group']");
        waitForElementToAppear(driver.findElement(langCombo));
        for(WebElement lang : new Select(languageCombo).getOptions()){
            if(lang.getAttribute("value").equals(language)) {
                lang.click();
                break;
            }
        }
    }

    public List<WebElement> getAlertWithText(String text) {
        By alertWithText = By.xpath("//div[@role='alert'][contains(text(),'" + text + "')]");
        return driver.findElements(alertWithText);
    }

    public void verifyUserGuide(String userGuide) {
        By pdfFile = By.xpath("//embed[contains(@src,'" + userGuide + "')]");
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        waitForElementToAppear(driver.findElement(pdfFile));
        driver.close();
        driver.switchTo().window(availableWindows.get(0));
    }

    public void verifyInvalidResource() {
        By errorMessage = By.xpath("//p[text()='Sorry, an error has occurred.']");
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        waitForElementToAppear(driver.findElement(errorMessage));
        driver.close();
        driver.switchTo().window(availableWindows.get(0));
    }

    public void openSettingsIcon() {
        By settingsGear = By.xpath("//span[@class='icon-setting header-icon']");
        waitForElementToAppear(driver.findElement(settingsGear));
        driver.findElement(settingsGear).click();
    }

    public void openHelpIcon() {
        By helpIcon = By.xpath("//span[contains(@class,'icon-help')]");
        waitForElementToAppear(driver.findElement(helpIcon));
        driver.findElement(helpIcon).click();
    }

    public void openUserGuideLink(String userGuide) {
        By userGuideLink = By.xpath("//a[contains(@href,'" + userGuide + "')]");
        waitForElementToAppear(driver.findElement(userGuideLink));
        driver.findElement(userGuideLink).click();
        waitForElementToDisappear(driver.findElement(userGuideLink));
    }

    public void selectMenuItemByText(String text) {
        By menuItem = By.xpath("//span[text()='" + text + "']");
        waitForElementToAppear(driver.findElement(menuItem));
        driver.findElement(menuItem).click();
    }

    public String getContactInformationField(String field) {
        By informationField = By.xpath("//button//*[contains(text(),'" + field + "')]/..");
        waitForElementToAppear(driver.findElement(informationField));
        return driver.findElement(informationField).getText();
    }

    public void switchToUserGuideTab() {
        ArrayList<String> availableWindows = new ArrayList<>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }
    }

    public List<WebElement> getSlides() {
        By bySlides = By.xpath("//div[contains(@class,'Slide-List-Item')]");
        return driver.findElements(bySlides);
    }

    public String getBatchCounter() {
        By batchCounterContainer = By.xpath("//div[@class='Batch-Name']/div");
        return driver.findElement(batchCounterContainer).getText();
    }

    public List<WebElement> getSlideCountWarningIcon() {
        By warningIcon = By.xpath("//span[contains(@class,'icon-warning-circle')]");
        return driver.findElements(warningIcon);
    }

    public List<WebElement> getCheckMark() {
        By checkMark = By.xpath("//div[@class='Batch-Name']//span[contains(@class,'icon-checkmark')]");
        return driver.findElements(checkMark);
    }

    public String getValueFromSummaryItem(String item) {
        By summaryItem = By.xpath("//span[text()='" + item + "']/../following-sibling::div");
        return driver.findElement(summaryItem).getText();
    }

    public boolean isWarningIconFromSummaryItemDisplayed(String item) {
        By summaryItem = By.xpath("//span[text()='" + item + "']/../following-sibling::div//span[@class='warn-icon']");
        return driver.findElement(summaryItem).isDisplayed();
    }

    public boolean isSampleIdInWellDisplayed(String value, String well) {
        By sampleControlField = By.xpath("//tr//td[text()='" + well + "']/following-sibling::td[text()='" + value + "']");
        By sampleIdField = By.xpath("//tr//td[text()='" + well + "']/following-sibling::td/span[text()='" + value + "']");

        if (value.equals("Positive Control") || value.equals("Negative Control"))
            return driver.findElement(sampleControlField).isDisplayed();
        else
            return driver.findElement(sampleIdField).isDisplayed();
    }

    public boolean isDilutionRatioInWellDisplayed(String well) {
        By dilutionRation = By.xpath("//tr//td[text()='" + well + "']/following-sibling::td//select");
        return driver.findElements(dilutionRation).isEmpty();
    }

    public boolean isSuccessAlertDisplayed() {
        By successAlert = By.xpath("//div[contains(@class,'toast--success')])");
        return driver.findElement(successAlert).isDisplayed();
    }

    public WebElement getButtonInOverrideAlert(String button) {
        By buttonInOverrideAlert = By.xpath("//div[contains(text(),'override this patient')]/a[text()='" + button + "']");
        return driver.findElement(buttonInOverrideAlert);
    }

    public WebElement getSlideType(String slide){
        By slideType = By.xpath("//select[@id='add-test-batch-slide-type']/option[@value='" + slide + "']");
        return driver.findElement(slideType);
    }

    public boolean isPatientIconForWellDisplayed(String well) {
        By sampleSwitchButton = By.xpath("//td[text()='" + well + "']/following-sibling::td//span[contains(@class,'icon-patient')]");
        return driver.findElement(sampleSwitchButton).isDisplayed();
    }

    public List<WebElement> getWorklistAutoSuggestions() {
        By worklistAutoSuggestions = By.xpath("//div[@id='react-autowhatever-1']/ul[@class='react-autosuggest__suggestions-list']/li/div");
        return driver.findElements(worklistAutoSuggestions);
    }

    public WebElement getApproveTestBatchCheckMark() {
        return approveTestBatchCheckMark;
    }

    public WebElement getEditTestBatchIcon() {
        return editTestBatchIcon;
    }

    public WebElement getCreateTestBatchWindow() { return createTestBatchWindow; }

    public WebElement getChecksum(){ return checksum; }

    public WebElement getChecksumButton(){ return checksumButton; }

    public WebElement getCreateTestBatchButton(){ return createTestBatchButton; }

    public WebElement getChecksumFail(){ return checksumFail; }

    public WebElement getChecksumOk(){ return checksumOk; }

    public List<WebElement> getChecksumFails() { return checksumFails; }

    public List<WebElement> getChecksumOks() { return checksumOks; }

    public WebElement getSelectedBatchName(){ return selectedBatchName; }

    public WebElement getDisabledCreatedTestBatchButton(){ return disabledCreatedTestBatchButton; }

    public WebElement getEnabledCreatedTestBatchButton(){ return enabledCreatedTestBatchButton; }

    public WebElement getWorklist(){ return worklist; }

    public WebElement getWorklistDeleteButton(){ return worklistDeleteButton; }

    public WebElement getWorklistOkButton(){ return worklistOkButton; }

    public WebElement getWorklistInput(){ return worklistInput; }

    public WebElement getLastUnselectedTestBatch(){ return lastUnselectedTestBatch; }

    public WebElement getAddNewSlideButton(){ return addNewSlideButton; }

    public WebElement getCreateSlideInput(){ return CreateSlideInput; }

    public WebElement getCreateSlideButton(){ return CreateSlideButton; }

    public WebElement getSlideSampleIDSpanText(){ return slideSampleIDSpanText; }

    public WebElement getTestBatchApproveIcon(){ return testBatchApproveIcon; }

    public WebElement getTestBatchPencilIcon(){ return testBatchPencilIcon; }

    public WebElement getSlideModal(){ return slideModal; }

    public WebElement getDilutionButton(){ return dilutionButton; }

    public WebElement getWellsWorkspace(){ return wellsWorkspace; }

    public WebElement getClearSampleID(){ return clearSampleID; }

    public WebElement getSlide(){ return slide; }

    public WebElement getTrashButton(){ return trashButton; }

    public WebElement getSlideCloseButton(){ return slideCloseButton; }

    public WebElement getWorklistTextField() {
        return worklistTextField;
    }
}