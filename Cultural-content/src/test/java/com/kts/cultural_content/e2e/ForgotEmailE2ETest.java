package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.ForgotEmailPage;
import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class ForgotEmailE2ETest {

    private WebDriver driver;

    private ForgotEmailPage regPage;

    //private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        regPage = PageFactory.initElements(driver, ForgotEmailPage.class);
        //homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ResetTestUserSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/forgotten-password");

        justWait();

        regPage.getEmail().sendKeys("srbislav30111998@gmail.com");

        regPage.getResetBtn().click();

        //homePage.ensureIsVisibleSigninBtn();
        //homePage.ensureIsVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //homePage.ensureIsNotVisibleProfileBtn();
        //categoryPage.ensureIsDisplayedName();
        String a= regPage.ensureIsVisibleMessage();
        assertEquals("Please check your email for a new password!", a);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }
    @Test
    public void ResetInTestUserUnsuccessful() throws InterruptedException {

        driver.get("http://localhost:4200/forgotten-password");

        justWait();

        regPage.getEmail().sendKeys("12efdfd3@gmail.com");
        regPage.getResetBtn().click();

        String a= regPage.ensureIsVisibleMessage();
        //homePage.ensureIsNotVisibleSigninBtn();
        //homePage.ensureIsNVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("Unsuccessful password reseting.", a);

    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(1000);
        }
    }
}
