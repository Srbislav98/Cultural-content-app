package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EditProfilePage {
    private WebDriver driver;

    @FindBy(className = "btn-danger")
    private WebElement cancelBtn;

    @FindBy(className= "btn-primary")
    private WebElement confirmBtn;

    @FindBy(id="Name")
    private WebElement ime;

    @FindBy(id="Surname")
    private WebElement prezime;

    @FindBy(id="Password")
    private WebElement password;

    public EditProfilePage() {
    }

    public EditProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
    }
    public void ensureIsNotVisibleLoginBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("unsub-button")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    }
    public String  ensureIsVisibleMessage() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getConfirmBtn() {
        return confirmBtn;
    }

    public WebElement getIme() {
        return ime;
    }

    public WebElement getPrezime() {
        return prezime;
    }

    public WebElement getPassword() {
        return password;
    }
}
