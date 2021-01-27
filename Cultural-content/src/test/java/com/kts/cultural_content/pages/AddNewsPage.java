package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewsPage {
    private WebDriver driver;

    @FindBy(id = "naziv")
    private WebElement naziv;

    @FindBy(id = "opis")
    private WebElement opis;

    @FindBy(id = "addBtn")
    private WebElement addButton;

    public AddNewsPage() {
    }

    public AddNewsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsVisibleaddBtn(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("addBtn")));
    }

    public WebElement getNaziv() {
        return naziv;
    }

    public WebElement getOpis() {
        return opis;
    }

    public WebElement getAddButton() {
        return addButton;
    }
}
