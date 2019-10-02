package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(getURL("/login"));
    }

    @FindAll(@FindBy(how = How.CSS, using = "#users-dropdown"))
    private WebElement usersDropdown;

    @FindBy(how = How.CSS, using = "#password")
    private WebElement password;

    @FindBy(how = How.CSS, using = "#kc-login")
    private WebElement loginButton;

    @FindBy(how = How.NAME, using = "password")
    private WebElement newPasswdField;

    @FindBy(how = How.NAME, using = "confirm")
    private WebElement confirmPasswdField;

    @FindBy(how = How.XPATH, using = "//input[@value='Save Password']")
    private WebElement saveButton;

    public List<WebElement> getNewPasswordMessage() {
        By message = By.xpath("//span[text()='Create New Password']");
        return driver.findElements(message);
    }

    public WebElement getNewPasswdField() {
        return newPasswdField;
    }

    public WebElement getConfirmPasswdField() {
        return confirmPasswdField;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void selectUser(String userName) {
        for(WebElement user : new Select(usersDropdown).getOptions()){
            if(user.getText().equals(userName)) {
                user.click();
                break;
            }
        }
    }

    public boolean isPasswordCleared(){
        return password.getText().length() == 0;
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void login(){
        waitForElementToAppear(driver.findElement(By.cssSelector("#kc-login")));
        driver.findElement(By.cssSelector("#kc-login")).click();
    }

    public void login(String username, String password){
        this.selectUser(username);
        this.setPassword(password);
        this.login();
    }

    public boolean isLoggedIn(){
        return !driver.getCurrentUrl().contains("/auth/realms/etaluma") && isRedirected();
    }

    public boolean isAlertDisplayed(){
        waitForElementToAppear(driver.findElement(By.cssSelector(".alert-error")));
        return driver.findElement(By.cssSelector(".alert-error")).isDisplayed();
    }

    public boolean isRedirected() {
        return driver.getCurrentUrl().endsWith(":8080")
                || driver.getCurrentUrl().endsWith("/#/")
                || driver.getCurrentUrl().endsWith(":8080/");
    }

    public boolean hasNewPasswordPrompt() {
        waitForElementToAppear(driver.findElement(By.cssSelector("input[name=confirm].field-text")));
        return driver.findElement(By.cssSelector("input[name=confirm].field-text")) !=null;
    }

    public void setUserName(String username) {
        ((JavascriptExecutor) driver).executeScript(String.format("let element = document.querySelector('#username'); element.value = '%s';", username));
    }
}