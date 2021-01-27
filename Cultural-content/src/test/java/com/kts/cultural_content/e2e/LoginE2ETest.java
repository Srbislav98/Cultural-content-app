package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class LoginE2ETest {

    private WebDriver driver;

    private LoginPage loginPage;

    private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void LogInTestUserSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getEmail().sendKeys("123@gmail.com");

        loginPage.getPassword().sendKeys("user");

        loginPage.getLoginBtn().click();

        homePage.ensureIsNotVisibleSigninBtn();
        homePage.ensureIsNotVisibleSignupBtn();
        homePage.ensureIsVisibleSignoutBtn();
        homePage.ensureIsVisibleProfileBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("http://localhost:4200/", driver.getCurrentUrl());

    }
    @Test
    public void LogInTestAdminSuccess() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getEmail().sendKeys("124@gmail.com");

        loginPage.getPassword().sendKeys("admin");

        loginPage.getLoginBtn().click();

        homePage.ensureIsNotVisibleSigninBtn();
        homePage.ensureIsNotVisibleSignupBtn();
        homePage.ensureIsVisibleSignoutBtn();
        homePage.ensureIsNotVisibleProfileBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("http://localhost:4200/", driver.getCurrentUrl());

    }
    @Test
    public void LogInTestUserUnsuccessful() throws InterruptedException {

        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getEmail().sendKeys("1234554@gmail.com");

        loginPage.getPassword().sendKeys("user");

        loginPage.getLoginBtn().click();

        String a=loginPage.ensureIsVisibleUnsuccesssfulLogin();
        //homePage.ensureIsNotVisibleSigninBtn();
        //homePage.ensureIsNVisibleSignupBtn();
        //homePage.ensureIsNotVisibleSignoutBtn();
        //categoryPage.ensureIsDisplayedName();

        assertEquals("Unsuccessful login! Check email and password.", a);

    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(5000);
        }
    }
}
