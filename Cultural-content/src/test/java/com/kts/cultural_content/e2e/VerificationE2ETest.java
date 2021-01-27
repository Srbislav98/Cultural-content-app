package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.ForgotEmailPage;
import com.kts.cultural_content.pages.VerificationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class VerificationE2ETest {

    private WebDriver driver;

    private VerificationPage regPage;

    //private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        regPage = PageFactory.initElements(driver, VerificationPage.class);
        //homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void VerifyTestUserSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/registrationConfirm/abb");

        justWait();

        regPage.getConfirmationBtn().click();

        //homePage.ensureIsVisibleSigninBtn();
        //homePage.ensureIsVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //homePage.ensureIsNotVisibleProfileBtn();
        //categoryPage.ensureIsDisplayedName();
        String a= regPage.ensureIsVisibleMessage();
        assertEquals("Successful activated profile. You can now sign in.", a);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }
    @Test
    public void VerifyTestUserUnsuccessful() throws InterruptedException {

        driver.get("http://localhost:4200/registrationConfirm/assssa");

        justWait();

        regPage.getConfirmationBtn().click();

        String a= regPage.ensureIsVisibleMessage();
        //homePage.ensureIsNotVisibleSigninBtn();
        //homePage.ensureIsNVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("Activation link is not valid.", a);

    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(1000);
        }
    }
}
