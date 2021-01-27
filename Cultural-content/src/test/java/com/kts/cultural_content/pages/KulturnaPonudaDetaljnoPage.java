package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class KulturnaPonudaDetaljnoPage {
    private WebDriver driver;

    @FindBy(id="subscribeBtn")
    private WebElement subButton;

    @FindBy(id = "reviewsBtn")
    private WebElement reviewsButton;

    @FindBy(id = "addReviewBtn")
    private WebElement addReviewButton;

    @FindBy(id = "editBtn")
    private WebElement editButton;

    @FindBy(id = "deleteBtn")
    private WebElement deleteButton;

    @FindBy(id = "addNewsBtn")
    private WebElement addNewsButton;

    @FindBy(id = "backBtn")
    private WebElement backButton;

    @FindBy(id = "elementi")
    private List<WebElement> elementi;

    public KulturnaPonudaDetaljnoPage() {
    }

    public KulturnaPonudaDetaljnoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsVisibleSubBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("subscribeBtn")));
    }

    public void ensureIsVisibleaddReviewBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("addReviewBtn")));
    }
    public void ensureIsVisibleeditBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("editBtn")));
    }
    public void ensureIsVisiblereviewsBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("reviewsBtn")));
    }
    public void ensureIsVisibledeleteBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteBtn")));
    }
    public void ensureIsVisibleaddNewsBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("addNewsBtn")));
    }

    public void ensureIsVisiblebackBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("backBtn")));
    }

    public List<WebElement> getElementi() {
        return elementi;
    }

    public WebElement getSubButton() {
        return subButton;
    }

    public WebElement getReviewsButton() {
        return reviewsButton;
    }

    public WebElement getAddReviewButton() {
        return addReviewButton;
    }

    public WebElement getEditButton() {
        return editButton;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    public WebElement getAddNewsButton() {
        return addNewsButton;
    }

    public WebElement getBackButton() {
        return backButton;
    }
}
