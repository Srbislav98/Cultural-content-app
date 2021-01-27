package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddCCPage {
    private WebDriver driver;

    @FindBy(id="Name")
    private WebElement nameInput;
    @FindBy(id="Address")
    private WebElement addInput;
    @FindBy(id="Description")
    private WebElement descInput;
    @FindBy(id="Type")
    private WebElement typeInput;
    @FindBy(id="Location")
    private WebElement locaInput;

    @FindBy(id="Ok")
    private WebElement okButton;
    @FindBy(id="home_btn")
    private WebElement homeButton;

    /*@FindElementBy(className  = "ng-option ng-option-marked")
    private List<WebElement> opcije();*/



    public AddCCPage() {
    }

    public AddCCPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getName() {
        return nameInput;
    }
    public WebElement getAddress() {
        return addInput;
    }
    public WebElement getDescription() {
        return descInput;
    }
    public WebElement getType() {
        return typeInput;
    }
    public WebElement getLocation() { return locaInput; }

    public WebElement getOK() {
        return okButton;
    }
    public WebElement getHome() { return homeButton; }

    public void ensureIsDisabledOkBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.id("Ok"))));
    }
}
