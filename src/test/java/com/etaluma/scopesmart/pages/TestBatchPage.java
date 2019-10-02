package com.etaluma.scopesmart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBatchPage extends BasePage{

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Form-New-Slide']//tr/td[3]/*"))
    private List<WebElement> wellSamples;

    @FindAll(@FindBy(how = How.CSS, using = ".Form-New-Slide .table tbody tr"))
    private List<WebElement> sampleRows;

    public By scanSlideMessage = By.xpath("//span[text()='Scan a slide to add to this Test Batch.']");
    public By scanSlideImage = By.xpath("//img[@alt='Slide instructions']");

    public TestBatchPage(WebDriver driver) {
        super(driver);
    }

    public void addPatientSample(String sampleId, int wellNumber) throws InterruptedException {
        wellSamples.get(wellNumber).click();
        wellSamples.get(wellNumber).sendKeys(sampleId);
        wellSamples.get(wellNumber).sendKeys(Keys.TAB);
        waitForTextToAppear(By.cssSelector(String.format(".Form-New-Slide .table tbody tr:nth-child(%d) td:nth-child(3)", wellNumber+1)),sampleId);
    }

    public void changePatientSample(String sampleId, String well) throws InterruptedException {
        By sampleSwitchButton = By.xpath("//td[text()='" + well + "']/following-sibling::td//button[@class='sample-switch-button btn btn-link']");
        By sampleLabel = By.xpath("//td[text()='" + well + "']/following-sibling::td[2]");
        By sampleField = By.xpath("//td[text()='" + well + "']/following-sibling::td[2]/input");
        while(!driver.findElement(sampleLabel).getText().equals("")) {
            driver.findElement(sampleSwitchButton).click();
            Thread.sleep(2000);
        }
        driver.findElement(sampleField).sendKeys(sampleId);
        driver.findElement(sampleField).sendKeys(Keys.TAB);
    }

    public boolean isScanSlideMessageDisplayed() {
        return driver.findElement(this.scanSlideMessage).isDisplayed();
    }

    public boolean isScanSlideImageDisplayed() {
        return driver.findElement(this.scanSlideImage).isDisplayed();
    }

    public boolean isWellEditable(String wellNumber){
        return driver.findElement(By.xpath("//div[@class='Form-New-Slide']//td[text()='" + wellNumber + "']")).isDisplayed();
    }

    public void selectWell(String wellNumber){
        driver.findElement(By.xpath("//div[@class='Form-New-Slide']//td[text()='" + wellNumber + "']")).click();
    }

    public void openFillDownMenu(String wellNumber){
        selectWell(wellNumber);
        driver.findElement(By.cssSelector(".icon-auto-dilution")).click();
    }

    public void selectFillDown(String dilution) {
        WebElement dilutionDropDown=driver.findElement(By.cssSelector(".popover select"));
        for(WebElement dilutionElement : new Select(dilutionDropDown).getOptions()){
            if(dilutionElement.getText().equals(dilution)) {
                dilutionElement.click();
                break;
            }
        }
    }

    public void fillDown() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    public void cancelFillDown() {
        driver.findElement(By.cssSelector(".btn-secondary")).click();
    }

    public List<Map> getWellSamples(){
        List<Map> wellSamples = new ArrayList<>();
        for(WebElement sampleRow: sampleRows){
            if (sampleRow.getAttribute("class").equals("selected")) {
                Map map = new HashMap();
                map.put("sampleId",sampleRow.findElement(By.cssSelector("td:nth-child(3)")).findElement(By.cssSelector(".well-sample-id-input")).getAttribute("value"));
                map.put("dilution",sampleRow.findElement(By.tagName("select")).getAttribute("value"));
                wellSamples.add(map);
            }
            else{
                String[] dilutionArray = sampleRow.findElement(By.cssSelector("td:nth-child(5)")).getText().split(":");
                String dilution = dilutionArray.length>1? dilutionArray[1]:"";
                Map map = new HashMap();
                map.put("sampleId",sampleRow.findElement(By.cssSelector("td:nth-child(3)")).getText());
                map.put("dilution",dilution);
                wellSamples.add(map);
            }

        }
        return wellSamples;
    }

    public void deleteSlide() {
        By deleteSlideBy = By.cssSelector("button.btn > span.icon-trash");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(2000,0)");
        driver.findElement(deleteSlideBy).click();
    }

    public void confirmDeleteSlide() {
        By deleteSlideButton = By.xpath("//button/span[text()='Delete Slide']");
        By alertBy = By.xpath("//div[@role='alert']");
        driver.findElement(deleteSlideButton).click();
        waitForElementToAppear(driver.findElement(alertBy));
        while(!driver.findElements(alertBy).isEmpty());
    }

    public void cancelDeleteSlide() {
        By cancelSlideButton = By.xpath("//button/span[text()='Cancel']/..");
        driver.findElement(cancelSlideButton).click();
    }

    public void isDeleteSlideAlertVisible() {
        By deleteQuestionBy = By.cssSelector("div#scopeSmartApp\\.slide\\.delete\\.question.modal-header");
        driver.findElement(deleteQuestionBy);
    }

    public void addPositiveControl(int activeWell) {
        By sampleSwitchButton = By.xpath("//td[text()='" + activeWell + "']/following-sibling::td//button[@class='sample-switch-button btn btn-link']");
        By sampleLabel = By.xpath("//td[text()='" + activeWell + "']/following-sibling::td[text()='Positive Control']");
        By overrideAlert = By.xpath("//div[contains(text(),'override this patient')]");
        driver.findElement(sampleSwitchButton).click();
        while(driver.findElements(sampleLabel).isEmpty() && driver.findElements(overrideAlert).isEmpty());
    }

    public boolean getPositiveControlVirtualSlide(int activeWell) {
        By posControlVirtualSlide = By.xpath("//div[contains(@class,'Slide-Selected')]//span[text()='" + activeWell + "']/following-sibling::span[contains(@class,'icon-poscontrol')]");
        return driver.findElement(posControlVirtualSlide).isDisplayed();
    }

    public boolean getNegativeControlVirtualSlide(int activeWell) {
        By negControlVirtualSlide = By.xpath("//div[contains(@class,'Slide-Selected')]//span[text()='" + activeWell + "']/following-sibling::span[contains(@class,'icon-negcontrol')]");
        return driver.findElement(negControlVirtualSlide).isDisplayed();
    }

    public void addNegativeControl(int activeWell) {
        By sampleSwitchButton = By.xpath("//td[text()='" + activeWell + "']/following-sibling::td//button[@class='sample-switch-button btn btn-link']");
        By samplePosLabel = By.xpath("//td[text()='" + activeWell + "']/following-sibling::td[text()='Positive Control']");
        By sampleNegLabel = By.xpath("//td[text()='" + activeWell + "']/following-sibling::td[text()='Negative Control']");
        driver.findElement(sampleSwitchButton).click();
        waitForElementToAppear(driver.findElement(samplePosLabel));
        driver.findElement(sampleSwitchButton).click();
        waitForElementToAppear(driver.findElement(sampleNegLabel));
    }

    public List<WebElement> getTestBatches() {
        By testBatchesList = By.xpath("//div[@class='Batch-Slides-List']/div[contains(@class,'Slide-List-Item')]");
        return driver.findElements(testBatchesList);
    }

    public List<WebElement> getSelectedSlide() {
        By selectedTestBatchesList = By.xpath("//div[@class='Batch-Slides-List']/div[contains(@class,'Slide-List-Item selected')]");
        return driver.findElements(selectedTestBatchesList);
    }
}