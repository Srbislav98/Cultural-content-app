package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import com.kts.cultural_content.pages.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class ProfileE2ETest {

    private WebDriver driver;

    private ProfilePage profilePage;
    private LoginPage loginPage;

    private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void logIn(){

        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        //assertEquals("http://localhost:4200/", driver.getCurrentUrl());
    }
    public void logOut() throws InterruptedException {
        homePage.getSignoutBtn().click();
        //assertEquals("http://localhost:4200/", driver.getCurrentUrl());
    }
    @Test
    public void UnsubscribeTest() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        homePage.ensureIsVisibleProfileBtn();
        //homePage.getProfileBtn().click();
        driver.get("http://localhost:4200/profil");
        justWait();
        profilePage.getUnsubBtn().get(0).click();
        String a=profilePage.ensureIsVisibleMessage();
        assertEquals("Succcessful unsubscription.", a);
        profilePage.getPoruka().click();
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();

    }
    @Test
    public void SearchSubTestNothingFound() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        homePage.ensureIsVisibleProfileBtn();
        //homePage.getProfileBtn().click();
        driver.get("http://localhost:4200/profil");
        justWait();
        profilePage.getSearchInput().sendKeys("wefoiengerkl");
        profilePage.getSearchBtn().click();
        justWait();
        assertEquals(profilePage.getUnsubBtn().size(), 0);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }
    @Test
    public void SearchSubTestOneResult() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        homePage.ensureIsVisibleProfileBtn();
        //homePage.getProfileBtn().click();
        driver.get("http://localhost:4200/profil");
        justWait();
        profilePage.getSearchInput().sendKeys("Exit");
        profilePage.getSearchBtn().click();
        justWait();
        assertEquals(profilePage.getUnsubBtn().size(), 1);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }
}
