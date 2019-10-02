package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class TestSummaryPage extends BasePage {

    @FindAll(@FindBy(how = How.CSS, using = "div.test-batch-summary-info div.row"))
    private List<WebElement> testBatchSummary;

    @FindAll(@FindBy(how = How.CSS, using = "div.Batch-Info"))
    private List<WebElement> testBatchInfo;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-edit-pencil')]")
    private WebElement editTestBatchPencil;

    public By scanSampleMessage = By.xpath("//span[text()='Scan a sample vial to add to the selected well or type in a sample ID.']");
    public By scanSampleImage = By.xpath("//img[@alt='Well Sample instructions']");

    public TestSummaryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEditTestBatchPencil() {
        return editTestBatchPencil;
    }

    public boolean isScanSampleMessageDisplayed() {
        return driver.findElement(this.scanSampleMessage).isDisplayed();
    }

    public boolean isScanSampleImageDisplayed() {
        return driver.findElement(this.scanSampleImage).isDisplayed();
    }

    public Integer getBatchSize(){
        return testBatchInfo.size();
    }

    public String getDate() {
        return getSummaryElementValue(testBatchSummary.get(2));
    }

    String getSummaryElementValue(WebElement element){
        return getSplittedElement(element)[1];
    }

    private String[] getSplittedElement(WebElement element) {
        return element.getText().split("\n");
    }
}
