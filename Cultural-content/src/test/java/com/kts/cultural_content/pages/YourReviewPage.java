package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourReviewPage {
    private WebDriver driver;

    @FindBy(id = "ocena")
    private WebElement ocena;

    @FindBy(id = "komentar")
    private WebElement komentar;

    @FindBy(id = "napraviBtn")
    private WebElement napraviButton;

    public YourReviewPage() {
    }

    public YourReviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsVisiblenapraviBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("napraviBtn")));
    }

    public WebElement getOcena() {
        return ocena;
    }

    public WebElement getKomentar() {
        return komentar;
    }

    public WebElement getNapraviButton() {
        return napraviButton;
    }
}
