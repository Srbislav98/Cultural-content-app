package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ReviewsPage {
    private WebDriver driver;

    @FindBy(id = "ocena")
    private WebElement ocena;

    @FindBy(id = "searchBtn")
    private WebElement searchButton;

    @FindBy(id = "backBtn")
    private WebElement bakcButton;

    @FindBy(id = "elementi")
    private List<WebElement> elementi;

    public ReviewsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ReviewsPage() {
    }

    public void ensureIsVisibleSearchBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("searchBtn")));
    }

    public WebElement getOcena() {
        return ocena;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getBakcButton() {
        return bakcButton;
    }

    public List<WebElement> getElementi() {
        return elementi;
    }
}
