package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ReviewPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'Batch-List-Item')]/div[@class='Batch-Name']")
    private List<WebElement> testBatchesList;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'Batch-List-Item')][last()]//span[contains(@class,'icon-arrow')]")
    private WebElement lastBatchInList;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'Batch-List-Item')][last()]//div[@class='Batch-Name']")
    private WebElement lastBatchName;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Batch-List-Item selected']//div[@class='wellSample-list-item']"))
    private List<WebElement> allWellSamples;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Batch-List-Item selected']//div[@class='wellSample-list-item']//span[contains(@class,'icon')]"))
    private List<WebElement> allWellSampleIcons;

    @FindBy(how = How.XPATH, using = "(//div[@class='collapse show']//div[@class='wellSample-list-item'])[1]")
    private WebElement firstWellSample;

    @FindBy(how = How.XPATH, using = "(//div[@class='collapse show']//div[@class='wellSample-list-item'])[2]")
    private WebElement secondWellSample;

    @FindBy(how = How.XPATH, using = "(//div[@class='collapse show']//div[@class='wellSample-list-item'])[last()]")
    private WebElement lastWellSample;

    @FindBy(how = How.XPATH, using = "//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div")
    private WebElement wellSampleToApprove;

    @FindBy(how = How.XPATH, using = "//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-confirmed'])=0][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div")
    private WebElement wellSampleToConfirm;

    @FindBy(how = How.XPATH, using = "(//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div)[last()]")
    private WebElement lastSampleToApprove;

    @FindBy(how = How.XPATH, using = "(//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-confirmed'])=0][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div)[last()]")
    private WebElement lastSampleToConfirm;

    @FindBy(how = How.XPATH, using = "//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-validated'])=1]/../preceding-sibling::div")
    private WebElement wellSampleToDisapprove;

    @FindBy(how = How.XPATH, using = "//div[@class='collapse show']//span[contains(@class,'positive') or contains(@class,'negative')][count(../span[@class='icon-confirmed'])=1]/../preceding-sibling::div")
    private WebElement wellSampleToUnconfirm;

    @FindBy(how = How.XPATH, using = "//div[@class='collapse show']//span[contains(@class,'failed-qc')][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div")
    private WebElement wellSampleFailedQc;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-approve-check')]")
    private WebElement approveCheckIcon;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']/../following-sibling::span//span[@class='icon-validated']")
    private WebElement resultApproveIcon;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']/../following-sibling::span//span[@class='icon-confirmed']")
    private WebElement resultConfirmIcon;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]//span[@class='icon-validated']")
    private WebElement sampleValidatedList;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]//span[@class='icon-confirmed']")
    private WebElement sampleConfirmedList;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-unapprove')]")
    private WebElement disapproveIcon;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']/../..//span[contains(@class,'icon-edit')]")
    private WebElement editIconResult;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']/../..//div[text()='Approved']")
    private WebElement approvedTextResult;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']/../..//div[text()='Confirmed']")
    private WebElement confirmedTextResult;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'buttons-results')]")
    private WebElement buttonsResults;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]//span[contains(@class,'positive') or contains(@class,'negative')]")
    private WebElement sampleIconList;

    @FindBy(how = How.XPATH, using = "(//div[contains(@style,'bold')]/following-sibling::div/div)[1]")
    private WebElement followingSample;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]/preceding-sibling::div[1]/div")
    private WebElement previousSample;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-navarrow-back')]/..")
    private WebElement backArrow;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-navarrow-next')]/..")
    private WebElement nextArrow;

    @FindBy(how = How.XPATH, using = "//select[@class='pattern-library-select']")
    private WebElement referenceLibrary;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-tag-addpattern')]")
    private WebElement addPatternIcon;

    @FindBy(how = How.XPATH, using = "//img[@class='pattern-library-picture']")
    private WebElement patternPicture;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'pattern-library-overlay')]")
    private WebElement patternLibraryOverlay;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-close-x')]")
    private WebElement closePatternOverlay;

    @FindAll(@FindBy(how = How.XPATH, using = "//ul[@class='control-dots']/li"))
    private List<WebElement> referenceImagesList;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-Artboard')]/..")
    private WebElement reImageIcon;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-untag-reimage')]/..")
    private WebElement unTagReImageIcon;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-info-circle')]/..")
    private WebElement patternInformationIcon;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'pattern-library-overlay')]//span[contains(@class,'icon-chevron')]/..")
    private WebElement patternInformationDropdown;

    @FindBy(how = How.XPATH, using = "//div[@class='result-patterns-edit-panel container']")
    private WebElement resultPatternsPanel;

    @FindBy(how = How.XPATH, using = "//div[@class='patterns-textfield']")
    private WebElement patternsTextField;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-close-x')]")
    private WebElement closePanelIcon;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='patterns-textfield']/div"))
    private List<WebElement> patternsListTextField;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='inline-block pattern-names-div']/div"))
    private List<WebElement> patternsAddedList;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'iconFailed')]")
    private WebElement qcFailedIcon;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]/div[1]")
    private WebElement selectedSample;

    @FindBy(how = How.XPATH, using = "//div[contains(@style,'bold')]//span[contains(@class,'icon')]")
    private WebElement selectedSampleIcon;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'selected')]/span")
    private WebElement selectedSampleResult;

    @FindBy(how = How.XPATH, using = "//div[@class='slide-preview-icon selected']//span[contains(@class,'icon')]")
    private WebElement selectedSampleSlidePreviewIcon;

    @FindAll(@FindBy(how = How.XPATH, using = "//button[@class='qc-samples-failed-button btn btn-secondary']"))
    private List<WebElement> failedQcSamplesButtons;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Batch-List-Item selected']//span[contains(@class,'icon-warning-circle')]"))
    private List<WebElement> failedSamplesIconLabel;

    @FindAll(@FindBy(how = How.XPATH, using = "//ul[@class='BrainhubCarousel__dots']/li"))
    private List<WebElement> sampleImagesDots;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'active')]//img")
    private WebElement activeSampleImage;

    @FindBy(how = How.XPATH, using = "//li[contains(@class,'active')]//*[@data-icon='plus']")
    private WebElement zoomInButton;

    @FindBy(how = How.XPATH, using = "//li[contains(@class,'active')]//*[@data-icon='minus']")
    private WebElement zoomOutButton;

    @FindBy(how = How.XPATH, using = "//button/span[contains(@class,'archive')]")
    private WebElement mainArchiveButton;

    @FindBy(how = How.XPATH, using = "//button//span[contains(@class,'file-pdf')]")
    private WebElement previewPdfButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'archive-modal-submit')]")
    private WebElement archiveWindowButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'archive-modal')]")
    private WebElement archiveWindow;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'pdf-preview')]")
    private WebElement previewPdfWindow;

    @FindBy(how = How.XPATH, using = "//span[@class='icon-file-history']")
    private WebElement auditButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'audit-trial')]")
    private WebElement auditTrialWindow;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'audit-trial')]//div[@class='header-content']")
    private WebElement auditTrialHeader;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'audit-trial')]//button[@class='close']")
    private WebElement closeAuditTrialButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'footer-page')]")
    private WebElement previewPdfPageFooter;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-dialog pdf-preview']//div[contains(@class,'file-name')]")
    private WebElement previewPdfTitle;

    @FindBy(how = How.XPATH, using = "//div[text()='Image Meta Data']")
    private WebElement imageMetaDataPanel;

    @FindBy(how = How.XPATH, using = "//div[@class='header-content']")
    private WebElement metaDataHeader;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Comments')]")
    private WebElement commentsLabel;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'review-value-text')]/span[2]/span[contains(@class, 'icon-edit-pencil')]")
    private WebElement commentsEditButton;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-header']/h5/div[contains(text(), 'Add Comments')]")
    private WebElement commentsModalTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']/descendant::input[@type='checkbox']")
    private WebElement titerCheckbox;

    @FindBy(how = How.XPATH, using = "//select[@class='dropdown-field-copy'][@name='startRatio']")
    private WebElement startingRatioDropdown;

    @FindBy(how = How.XPATH, using = "//select[@class='dropdown-field-copy'][@name='endRatio']")
    private WebElement endingRatioDropdown;

    @FindBy(how = How.XPATH, using = "//button[@class='close']/span[contains(text(), '×')]")
    private WebElement commentsPanelCloseButton;

    @FindBy(how = How.XPATH, using = "//textarea[@class='field-comments']")
    private WebElement commentsPanelTextArea;

    @FindBy(how = How.XPATH, using = "//span[@id='sampleResultComment']")
    private WebElement sampleResultComment;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Sample Image')]")
    private WebElement sampleImageLabel;

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'slick-arrow slick-prev')]")
    private WebElement wellImagePreviousButton;

    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'slick-arrow slick-next')]")
    private WebElement wellImageNextButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'icon-reportimage')]")
    private WebElement iconReportImage;

    @FindBy(how = How.XPATH, using = "//span[contains(@class, 'icon-file-imagemeta')]")
    private WebElement iconMetaData;

    @FindBy(how = How.XPATH, using = "//span[text()='Result']")
    private WebElement resultsLabel;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'popover-filter')]")
    private WebElement filterWindow;

    @FindBy(how = How.ID, using = "filterList56")
    private WebElement filterButton;

    @FindBy(how = How.XPATH, using = "//div[@id='filterList56']/span[contains(@class, 'icon-filter')]")
    private WebElement filterIcon;

    @FindAll(@FindBy(how = How.XPATH, using = "//img[@class='mitotic-image']"))
    private List<WebElement> mitoticImages;

    @FindBy(how = How.XPATH, using = "//div[@class='button-small-gray'][text()='Reset Filters']")
    private WebElement resetFiltersButton;

    @FindBy(how = How.XPATH, using = "//button[@type='button'][text()='Apply Filters']")
    private WebElement applyFiltersButton;

    @FindBy(how = How.XPATH, using = "//button[@class='btn-gray-sm btn btn-secondary'][text()='Cancel']")
    private WebElement filterWindowCancelButton;

    public ReviewPage(WebDriver driver) {
        super(driver);
        HomePage homePage = new HomePage(driver);
        homePage.gotoReview();
        waitForPageToLoad();
    }

    public ReviewPage(WebDriver driver, String user, String pass) {
        super(driver);
        HomePage homePage = new HomePage(driver, user, pass);
        homePage.gotoReview();
        waitForPageToLoad();
    }

    public WebElement getListFilterWindowInputByLabel(String label) {
        By listFilterWindowInputByLabel = By.xpath("//label[text()='" + label + "']/descendant::input");
        return driver.findElement(listFilterWindowInputByLabel);
    }

    public WebElement getElementInSampleListByIcon(String icon) {
        By elementInList = By.xpath("//div[@class='Batch-List-Item selected']//div[@class='wellSample-list-item']//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(elementInList));
        return driver.findElement(elementInList);
    }

    public WebElement getIconFileHistory() {
        By iconFileHistory = By.xpath("//div/span[contains(@class, 'icon-file-history')]");
        return driver.findElement(iconFileHistory);
    }

    public WebElement getFirstPositiveWell() {
        By firstPositiveWell = By.xpath("(//div[@class='collapse show']//span[contains(@class,'positive')][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div)[1]");
        return driver.findElement(firstPositiveWell);
    }

    public WebElement getSpecificPatternDropdownOption(String option) {
        By specificPatternDropdownOption = By.xpath("//select[@class='custom-dropdown']/option[contains(text(), '" + option + "')]");
        return driver.findElement(specificPatternDropdownOption);
    }


    public WebElement getWellButtonByIcon(String icon) {
        By wellButtonByIcon = By.xpath("//button/span[contains(@class, '" + icon + "')]/..");
        waitForElementToAppear(driver.findElement(wellButtonByIcon));
        return driver.findElement(wellButtonByIcon);
    }

    public List<WebElement> getSamplesByResult(String result) {
        By wellsByResult = By.xpath("//div[@class='collapse show']//span[contains(@class,'" + result + "')]");
        return driver.findElements(wellsByResult);
    }

    public List<WebElement> getPreviewSamplesByResult(String result) {
        By wellsByResult = By.xpath("//div[contains(@class,'slide-preview-outer')]//span[contains(@class,'" + result + "')]/..");
        return driver.findElements(wellsByResult);
    }

    public WebElement getElementInSlidePreviewByIcon(String icon) {
        By elementInSlidePreview = By.xpath("//div[@class='slide-preview-icon selected']//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(elementInSlidePreview));
        return driver.findElement(elementInSlidePreview);
    }

    public WebElement getSelectedSampleByLabel(String label) {
        By sampleByLabel = By.xpath("//div[contains(@style,'bold')]//*[text()='" + label + "']");
        waitForElementToAppear(driver.findElement(sampleByLabel));
        return driver.findElement(sampleByLabel);
    }

    public WebElement getGenericObject(String message) {
        By genericObject = By.xpath("//*[contains(text(),'" + message + "')]");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public WebElement getSampleResultComment(String comment) {
        By sampleResult = By.xpath("//span[@id='sampleResultComment'][text()='" + comment + "']");
        waitForElementToAppear(driver.findElement(sampleResult));
        return driver.findElement(sampleResult);
    }

    public WebElement getTextInSummary(String message) {
        By genericObject = By.xpath("//div[@class='Test-Batch-Selected-Title row']/..//*[contains(text(),'" + message + "')]");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public WebElement getIconByLabel(String label) {
        By iconByLabel = By.xpath("//span[text()='" + label + "']/../..//span[@class='dot'][1]");
        waitForElementToAppear(driver.findElement(iconByLabel));
        return driver.findElement(iconByLabel);
    }

    public WebElement getGenericIcon(String icon) {
        By genericIcon = By.xpath("//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(genericIcon));
        return driver.findElement(genericIcon);
    }

    public WebElement getIconByType(String type) {
        By iconByType = By.xpath("//div[@class='col-sm-3']/../..//span[contains(@class,'" + type + "')]");
        waitForElementToAppear(driver.findElement(iconByType));
        return driver.findElement(iconByType);
    }

    public void selectPattern(String pattern) {
        for(WebElement option : new Select(referenceLibrary).getOptions()){
            if(option.getText().equals(pattern)) {
                option.click();
                break;
            }
        }
    }

    public void selectValueInComboBox(String value, WebElement comboBox) {
        for(WebElement option : new Select(comboBox).getOptions()){
            if(option.getText().equals(value)) {
                option.click();
                break;
            }
        }
    }

    public List<WebElement> getTestBatchesList() {
        return testBatchesList;
    }

    public WebElement getApproveCheckIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(approveCheckIcon));
        return approveCheckIcon;
    }

    public List<WebElement> getApproveCheckIcons() {
        By checkIcons = By.xpath("//span[contains(@class,'icon-approve-check')]");
        return driver.findElements(checkIcons);
    }

    public List<WebElement> getWellSampleImages() {
        By wellSampleImages = By.xpath("//div[@class='custom-slick-carousel']/descendant::img");
        return driver.findElements(wellSampleImages);
    }

    public List<WebElement> getFilterWindowPopOver() {
        By filterWindowPopOver = By.xpath("//div[@class='popover show popover-filter bs-popover-bottom']");
        return driver.findElements(filterWindowPopOver);
    }

    public List<WebElement> getWellImagePlusIcon() {
        By wellImagePlusIcon = By.xpath("//*[name()='svg'][contains(@data-icon, 'plus')]");
        return driver.findElements(wellImagePlusIcon);
    }

    public List<WebElement> getWellImageMinusIcon() {
        By wellImageMinusIcon = By.xpath("//*[name()='svg'][contains(@data-icon, 'minus')]");
        return driver.findElements(wellImageMinusIcon);
    }

    public List<WebElement> getWellImageDotButtons() {
        By wellImageDotButton = By.xpath("//ul[@class='slick-dots']/li/button");
        return driver.findElements(wellImageDotButton);
    }

    public List<WebElement> getTooltips() {
        By tooltips = By.xpath("//div[@class='Review-Module2']");
        return driver.findElements(tooltips);
    }

    public List<WebElement> getCommentsModal() {
        By commentsModal = By.xpath("//div[contains(@class, 'modal-dialog review-sample-result-add-coments-modal')]");
        return driver.findElements(commentsModal);
    }

    public WebElement getComboBoxByLabel(String label) {
        By comboBox = By.xpath("//span[text()='" + label + "']/../../following-sibling::div/select");
        waitForElementToAppear(driver.findElement(comboBox));
        return driver.findElement(comboBox);
    }

    public WebElement getIconInPatternsPanel(String icon) {
        By iconInPanel = By.xpath("//span[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(iconInPanel));
        return driver.findElement(iconInPanel);
    }

    public WebElement getEditIconForSection(String section) {
        By editIcon = By.xpath("//span[text()='" + section + "']/../following-sibling::div//span[contains(@class,'icon-edit-pencil')]");
        waitForElementToAppear(driver.findElement(editIcon));
        return driver.findElement(editIcon);
    }

    public WebElement getTestBatchInList(String batch) {
        By batchInList = By.xpath("//div[contains(text(),'" + batch + "')]/following-sibling::div//span[contains(@class,'icon-arrow')]");
        waitForElementToAppear(driver.findElement(batchInList));
        return driver.findElement(batchInList);
    }

    public WebElement getTestBatchByName(String batch) {
        By batchByName = By.xpath("//div[contains(text(),'" + batch + "')]/../../div[@class='Batch-Name']");
        waitForElementToAppear(driver.findElement(batchByName));
        return driver.findElement(batchByName);
    }

    public WebElement getButtonByName(String buttonName) {
        By buttonByName = By.xpath("//button[text()='" + buttonName + "']");
        waitForElementToAppear(driver.findElement(buttonByName));
        return driver.findElement(buttonByName);
    }

    public WebElement getArchiveButtonByName(String buttonName) {
        By buttonByName = By.xpath("//span[text()='" + buttonName + "']");
        waitForElementToAppear(driver.findElement(buttonByName));
        return driver.findElement(buttonByName);
    }

    public WebElement getArchiveButtonCheckboxByName(String buttonName) {
        By buttonByName = By.xpath("//span[text()='" + buttonName + "']/../../input");
        waitForElementToAppear(driver.findElement(buttonByName));
        return driver.findElement(buttonByName);
    }

    public WebElement getResultButtonByIcon(String icon) {
        By resultButton = By.xpath("//div[contains(@class,'buttons-results')]/button[contains(@class,'" + icon + "')]");
        waitForElementToAppear(driver.findElement(resultButton));
        return driver.findElement(resultButton);
    }

    public List<WebElement> getArchiveButtons() {
        By archiveButtons = By.xpath("//button/span[contains(@class,'archive')]");
        return driver.findElements(archiveButtons);
    }

    public WebElement getColumnInAuditTable(String column) {
        By columnInTable = By.xpath("//div[contains(@class,'audit-trial')]//th[text()='" + column + "']");
        return driver.findElement(columnInTable);
    }


    public WebElement getCommentsPanelButton(String buttonName) {
        By commentsPanelButton = By.xpath("//button[contains(text(), '" + buttonName + "')]");
        return driver.findElement(commentsPanelButton);
    }

    public WebElement getLabelInCommentsModal(String label) {
        By labelInCommentsModal = By.xpath("//div[@class='modal-body']/div[@class='subtitle'][contains(text(), '" + label + "')]");
        return driver.findElement(labelInCommentsModal);
    }

    public WebElement getOptionInFilter(String option, String filter) {
        By labelInCommentsModal = By.xpath("//div[contains(text(),'" + filter + "')]/following-sibling::div[1]//label[text()='" + option + "']/input");
        return driver.findElement(labelInCommentsModal);
    }

    public List<WebElement> getRowsByColumn(Integer column) {
        By rowsInColumn = By.xpath("//div[contains(@class,'audit-trial')]//tr/td[" + column + "]");
        return driver.findElements(rowsInColumn);
    }

    public List<WebElement> getPdfWindowsList() {
        By pdfWindows = By.xpath("//div[contains(@class,'pdf-preview')]");
        return driver.findElements(pdfWindows);
    }

    public List<WebElement> getSlidePreviewMixedStatus(){
        By slidePreviewMixedStatus = By.xpath("//div[@class='slide-preview-content-panel']/descendant::span[@class='icon-mixed-status']");
        return driver.findElements(slidePreviewMixedStatus);
    }

    public List<WebElement> getFilterWindowWarningIcon(String filter){
        By filterWindowWarningIcon = By.xpath("//div[contains(text(), '" + filter + "')]/span/span[contains(@class, 'icon-warning-circle')]");
        return driver.findElements(filterWindowWarningIcon);
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

    public WebElement getLastBatchInList() { return lastBatchInList; }

    public List<WebElement> getAllWellSamples() { return allWellSamples; }

    public List<WebElement> getAllWellSampleIcons() {
        return allWellSampleIcons;
    }

    public WebElement getFirstWellSample() { return firstWellSample; }

    public WebElement getLastWellSample() { return lastWellSample; }

    public WebElement getWellSampleToApprove() { return wellSampleToApprove; }

    public WebElement getWellSampleToConfirm() { return wellSampleToConfirm; }

    public WebElement getLastSampleToApprove() { return lastSampleToApprove; }

    public WebElement getLastSampleToConfirm() { return lastSampleToConfirm; }

    public WebElement getWellSampleToDisapprove() { return wellSampleToDisapprove; }

    public WebElement getWellSampleToUnconfirm() { return wellSampleToUnconfirm; }

    public WebElement getApprovedTextResult() { return approvedTextResult; }

    public WebElement getConfirmedTextResult() { return confirmedTextResult; }

    public WebElement getResultApproveIcon() { return resultApproveIcon; }

    public WebElement getResultConfirmIcon() { return resultConfirmIcon; }

    public WebElement getSampleValidatedList() { return sampleValidatedList; }

    public WebElement getSampleConfirmedList() { return sampleConfirmedList; }

    public WebElement getDisapproveIcon() { return disapproveIcon; }

    public WebElement getEditIconResult() { return editIconResult; }

    public WebElement getButtonsResults() { return buttonsResults; }

    public WebElement getSampleIconList() { return sampleIconList; }

    public WebElement getFollowingSample() {
        waitForElementToAppear(followingSample);
        return followingSample;
    }

    public WebElement getPreviousSample() {
        waitForElementToAppear(previousSample);
        return previousSample;
    }

    public WebElement getBackArrow() { return backArrow; }

    public WebElement getNextArrow() { return nextArrow; }

    public WebElement getAddPatternIcon() { return addPatternIcon; }

    public WebElement getPatternPicture() { return patternPicture; }

    public WebElement getPatternLibraryOverlay() { return patternLibraryOverlay; }

    public WebElement getClosePatternOverlay() { return closePatternOverlay; }

    public List<WebElement> getReferenceImagesList() { return referenceImagesList; }

    public WebElement getReImageIcon() { return reImageIcon; }

    public WebElement getUnTagReImageIcon() { return unTagReImageIcon; }

    public WebElement getPatternInformationIcon() { return patternInformationIcon; }

    public WebElement getPatternInformationDropdown() { return patternInformationDropdown; }

    public WebElement getResultPatternsPanel() { return resultPatternsPanel; }

    public WebElement getPatternsTextField() { return patternsTextField; }

    public WebElement getClosePanelIcon() { return closePanelIcon; }

    public WebElement getReferenceLibrary() { return referenceLibrary; }

    public List<WebElement> getPatternsListTextField() { return patternsListTextField; }

    public List<WebElement> getPatternsAddedList() { return patternsAddedList; }

    public WebElement getWellSampleFailedQc() { return wellSampleFailedQc; }

    public List<WebElement> getWellSamplesFailed () {
        By failedSamples = By.xpath("//div[@class='collapse show']//span[contains(@class,'failed-qc')][count(../span[@class='icon-validated'])=0]/../preceding-sibling::div");
        return driver.findElements(failedSamples);
    }

    public List<WebElement> getFilterWindows() {
        By filterWindows = By.xpath("//div[contains(@class,'popover-filter')]");
        return driver.findElements(filterWindows);
    }

    public WebElement getQcFailedIcon() { return qcFailedIcon; }

    public List<WebElement> getFailedQcSamplesButtons() { return failedQcSamplesButtons; }

    public WebElement getSelectedSample() { return selectedSample; }

    public WebElement getSelectedSampleIcon() { return selectedSampleIcon; }

    public List<WebElement> getFailedSamplesIconLabel() { return failedSamplesIconLabel; }

    public List<WebElement> getSampleImagesDots() { return sampleImagesDots; }

    public WebElement getActiveSampleImage() { return activeSampleImage; }

    public WebElement getZoomInButton() { return zoomInButton; }

    public WebElement getZoomOutButton() { return zoomOutButton; }

    public WebElement getMainArchiveButton() { return mainArchiveButton; }

    public WebElement getArchiveWindow() { return archiveWindow; }

    public WebElement getArchiveWindowButton() { return archiveWindowButton; }

    public WebElement getLastBatchName() { return lastBatchName; }

    public WebElement getAuditButton() { return auditButton; }

    public WebElement getAuditTrialWindow() { return auditTrialWindow; }

    public WebElement getAuditTrialHeader(){ return auditTrialHeader; }

    public WebElement getCloseAuditTrialButton() { return closeAuditTrialButton; }

    public WebElement getPreviewPdfButton() { return previewPdfButton; }

    public WebElement getPreviewPdfWindow() { return previewPdfWindow; }

    public WebElement getPageFooter() { return previewPdfPageFooter; }

    public WebElement getPreviewPdfTitle() { return previewPdfTitle; }

    public WebElement getImageMetaDataPanel() { return imageMetaDataPanel; }

    public WebElement getMetaDataHeader() { return metaDataHeader; }

    public WebElement getCommentsLabel() { return commentsLabel; }

    public WebElement getCommentsEditButton() { return commentsEditButton; }

    public WebElement getCommentsModalTitle() { return commentsModalTitle; }

    public WebElement getTiterCheckbox() { return titerCheckbox; }

    public WebElement getStartingRatioDropdown() { return startingRatioDropdown; }

    public WebElement getEndingRatioDropdown() { return endingRatioDropdown; }

    public WebElement getCommentsPanelCloseButton() { return commentsPanelCloseButton; }

    public WebElement getSecondWellSample() { return secondWellSample; }

    public WebElement getCommentsPanelTextArea() { return commentsPanelTextArea; }

    public WebElement getSampleResultComment() { return sampleResultComment; }

    public WebElement getSampleImageLabel() { return sampleImageLabel; }

    public WebElement getWellImagePreviousButton() { return wellImagePreviousButton; }

    public WebElement getWellImageNextButton() { return wellImageNextButton; }

    public WebElement getIconReportImage() { return iconReportImage; }

    public WebElement getIconMetaData() { return iconMetaData; }

    public WebElement getResultsLabel() { return resultsLabel; }

    public WebElement getSelectedSampleSlidePreviewIcon() {
        return selectedSampleSlidePreviewIcon;
    }

    public WebElement getFilterWindow() {
        return filterWindow;
    }

    public WebElement getFilterButton() {
        return filterButton;
    }

    public List<WebElement> getMitoticImages() {
        return mitoticImages;
    }

    public WebElement getFilterIcon() { return filterIcon; }

    public WebElement getResetFiltersButton() { return resetFiltersButton; }

    public WebElement getFilterWindowCancelButton() { return filterWindowCancelButton; }

    public WebElement getApplyFiltersButton() { return applyFiltersButton; }

    public WebElement getSelectedSampleResult() {
        return selectedSampleResult;
    }
}