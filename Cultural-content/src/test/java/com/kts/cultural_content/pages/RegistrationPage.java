package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="username")
    private WebElement username;
    @FindBy(id="Name")
    private WebElement name;
    @FindBy(id="Surname")
    private WebElement surname;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="register-button")
    private WebElement registerBtn;

    public RegistrationPage() {
    }

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
    }
    public void ensureIsNotVisibleRegisterBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("register-button")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    }
    public String  ensureIsVisibleUnsuccesssfulRegistration() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }
    public String  ensureIsVisibleSuccesssfulRegistration() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }

    public WebElement getUsername() {
        return username;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getSurname() {
        return surname;
    }
}
