package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "User123!";
    public static final int MAX_LOGIN_BUTTON_RETRIES = 100; //TODO: Sometimes the login button doesn't respond, to be fixed by Dev

    @FindBy(how = How.CSS, using = "a[href*='/#/test-setup']")
    private WebElement testSetupButton;

    @FindBy(how = How.CSS, using = "a[href*='/#/imaging']")
    private WebElement imagingButton;

    @FindBy(how = How.CSS, using = "a[href*='/#/review']")
    private WebElement reviewButton;

    @FindBy(how = How.CSS, using = "a[href*='/#/archive']")
    private WebElement archiveButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-help')]")
    private WebElement helpIcon;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'icon-setting')]")
    private WebElement settingsIcon;

    public HomePage(WebDriver driver) {
        super(driver);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(USERNAME, PASSWORD);
        loginPage.waitForPageToLoad();
        this.waitForPageToLoad();
    }

    public HomePage(WebDriver driver, String user, String pass) {
        super(driver);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(user, pass);
        loginPage.waitForPageToLoad();
        this.waitForPageToLoad();
    }

    public void gotoTestSetup(){
        By loginByButton = By.cssSelector("button[class=\"btn btn-primary btn-lg\"]");
        int retries =0;
        while (isElementPresentWithWait(loginByButton,1)){
            driver.findElement(loginByButton).click();
            retries++;
            System.out.println("Retrying loginButton Click:"+retries);
            if(retries> MAX_LOGIN_BUTTON_RETRIES){
                break;
            }
        }
        By byTestSetupMenu = By.cssSelector("a[href*='/#/test-setup']");
        waitForElementToAppear(driver.findElement(byTestSetupMenu));
        driver.findElement(byTestSetupMenu).click();
    }

    public void gotoReview(){
        reviewButton.click();
    }

    public void gotoArchive(){
        archiveButton.click();
    }

    public WebElement getMenuOption(String option) {
        By menuOption = By.xpath("//div[contains(@class,'help-dropdown-menu')]//span[text()='" + option + "']");
        return driver.findElement(menuOption);
    }

    public WebElement getSettingsIcon() {
        return settingsIcon;
    }
}
