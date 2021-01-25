package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    @FindBy(id="signin-button")
    private WebElement singinBtn;
    @FindBy(id="signup-button")
    private WebElement signupBtn;

    @FindBy(id="signout-button")
    private WebElement signoutBtn;

    @FindBy(id="profile-button")
    private WebElement profileBtn;

    public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsNotVisibleSigninBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signin-button")));
    }
    public void ensureIsNotVisibleSignupBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signup-button")));
    }
    public void ensureIsNotVisibleSignoutBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signout-button")));
    }

    public void ensureIsVisibleSignoutBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("signout-button")));
    }
    public void ensureIsVisibleProfileBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("profile-button")));
    }
}
