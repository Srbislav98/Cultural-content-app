package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotEmailPage {
    private WebDriver driver;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="reset-button")
    private WebElement resetBtn;

    public ForgotEmailPage() {
    }

    public ForgotEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
    }
    public void ensureIsNotVisibleLoginBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("reset-button")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    }
    public String  ensureIsVisibleMessage() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }

    public WebElement getEmail() {
        return email;
    }


    public WebElement getResetBtn() {
        return resetBtn;
    }
}
