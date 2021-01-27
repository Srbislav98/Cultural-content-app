package com.kts.cultural_content.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCCTypePage {
    private WebDriver driver;

    @FindBy(id="Name")
    private WebElement nameInput;

    public AddCCTypePage() {
    }

    public AddCCTypePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getName() {
        return nameInput;
    }
}
