package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import com.kts.cultural_content.pages.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class RegistrationE2ETest {

    private WebDriver driver;

    private RegistrationPage regPage;

    private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        regPage = PageFactory.initElements(driver, RegistrationPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void RegisterinTestUserSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/registration");

        justWait();

        regPage.getEmail().sendKeys("srbislav30111998@gmail.com");
        regPage.getUsername().sendKeys("korisnik");
        regPage.getName().sendKeys("Srbislav");
        regPage.getSurname().sendKeys("Srbislav");

        regPage.getPassword().sendKeys("srbislav");

        regPage.getRegisterBtn().click();

        //homePage.ensureIsVisibleSigninBtn();
        //homePage.ensureIsVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //homePage.ensureIsNotVisibleProfileBtn();
        //categoryPage.ensureIsDisplayedName();
        String a= regPage.ensureIsVisibleSuccesssfulRegistration();
        assertEquals("Successful registration. Check your email for activation link.", a);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }
    @Test
    public void RegisterInTestUserUnsuccessful() throws InterruptedException {

        driver.get("http://localhost:4200/registration");

        justWait();

        regPage.getEmail().sendKeys("123@gmail.com");
        regPage.getUsername().sendKeys("korisnik");
        regPage.getName().sendKeys("Srbislav");
        regPage.getSurname().sendKeys("Srbislav");

        regPage.getPassword().sendKeys("srbislav");

        regPage.getRegisterBtn().click();

        String a= regPage.ensureIsVisibleUnsuccesssfulRegistration();
        //homePage.ensureIsNotVisibleSigninBtn();
        //homePage.ensureIsNVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("Unsuccessful registration. Email/Username is already in use.", a);

    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(1000);
        }
    }
}
