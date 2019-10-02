package com.etaluma.scopesmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateSlideModal extends BasePage{

    private WebElement slideIdInput;
    private WebElement createSlideButton;

    public CreateSlideModal(WebDriver driver) {
        super(driver);
        By inputSlideBy = By.cssSelector("input#slideId");
        slideIdInput = driver.findElement(inputSlideBy);
        createSlideButton = driver.findElement(By.cssSelector(".Create-slide-button"));
    }

    public void addSlide(String slideId){
        slideIdInput.sendKeys(slideId);
        createSlideButton.click();
    }
}