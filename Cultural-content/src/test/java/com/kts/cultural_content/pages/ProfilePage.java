package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProfilePage {
    private WebDriver driver;

    @FindBy(className = "btn-danger")
    private List<WebElement> unsubBtn;

    @FindBy(id="podatak")
    private WebElement searchInput;

    @FindBy(id="pretrazi-subove")
    private WebElement searchBtn;

    @FindBy(id="toast-container")
    private WebElement poruka;

    @FindBy(id="td-ime")
    private WebElement ime;

    @FindBy(id="td-prezime")
    private WebElement prezime;

    @FindBy(id="reset-button")
    private WebElement resetBtn;

    public ProfilePage() {
    }

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedEmail() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("email")));
    }
    public void ensureIsVisibleEditBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-dugme")));
    }

    public void ensureIsNotVisibleEmail() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    }
    public String  ensureIsVisibleMessage() {
        return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))).getText();
    }

    public List<WebElement> getUnsubBtn() {
        return unsubBtn;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public WebElement getPoruka() {
        return poruka;
    }

    public WebElement getIme() {
        return ime;
    }

    public WebElement getPrezime() {
        return prezime;
    }

    public WebElement getResetBtn() {
        return resetBtn;
    }
}
