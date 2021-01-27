package com.kts.cultural_content.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlterCCPage {
    private WebDriver driver;

    @FindBy(id="Description")
    private WebElement dInput;

    public AlterCCPage() {
    }

    public AlterCCPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDescription() {
        return dInput;
    }
}
