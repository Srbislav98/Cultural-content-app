package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerificationPage {
    private WebDriver driver;


    @FindBy(id="verify-button")
    private WebElement verificationBtn;

    public VerificationPage() {
    }

    public VerificationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsNotVisibleLoginBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("verify-button")));
    }
    public String  ensureIsVisibleMessage() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }



    public WebElement getConfirmationBtn() {
        return verificationBtn;
    }
}
