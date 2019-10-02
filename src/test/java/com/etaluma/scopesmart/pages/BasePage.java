package com.etaluma.scopesmart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static final int TIMEOUT = 10;
    public static final int POLLING = 100;
    public static final String BASE_URL = "http://ec2-35-160-94-191.us-west-2.compute.amazonaws.com:8080";
    protected WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, TimeUnit.SECONDS.toMillis(POLLING));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public boolean validateTooltipMessage(WebElement element, String message) throws InterruptedException {
        Actions actions = new Actions(driver);
        By tooltip = By.xpath("//*[contains(text(),'" + message + "')]");
        actions.moveToElement(element).perform();
        Thread.sleep(500);
        if(driver.findElement(tooltip).isDisplayed())
            return true;
        else
            return false;
    }

    public WebElement getGenericObjectWithText(String text) {
        By genericObject = By.xpath("//*[text()='" + text  + "']");
        waitForElementToAppear(driver.findElement(genericObject));
        return driver.findElement(genericObject);
    }

    public void waitForPageToLoad() {
        wait.until(driver1 -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForElementToAppear(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOfAllElements(locator));
    }

    public boolean isElementPresentWithWait(By element, long timeoutMillis) {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(timeoutMillis))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isValidDate(String inDate, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public boolean isElementPresentWithWait(By element) {
        return isElementPresentWithWait( element, TIMEOUT*1000);
    }

    protected void waitForElementToDisappear(WebElement locator) {
        wait.until(ExpectedConditions.invisibilityOfAllElements(locator));
    }

    protected void waitForTextToAppear(By locator, String text) {
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    protected String getURL(String uri){
        return BASE_URL+uri;
    }
}