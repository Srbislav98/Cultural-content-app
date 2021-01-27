package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class ReviewsE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private KulturnaPonudaDetaljnoPage kulturnaPonudaDetaljnoPage;
    private ReviewsPage reviewsPage;
    private YourReviewPage yourReviewPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        kulturnaPonudaDetaljnoPage = PageFactory.initElements(driver, KulturnaPonudaDetaljnoPage.class);
        reviewsPage=PageFactory.initElements(driver,ReviewsPage.class);
        yourReviewPage=PageFactory.initElements(driver,YourReviewPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void logIn() throws InterruptedException {

        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        justWait();

    }
    public void logOut(){
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    @Test
    public void FilterByGrade() throws InterruptedException{
        logIn();
        driver.get("http://localhost:4200/kulturna-ponuda-detaljno/100");
        justWait();
        kulturnaPonudaDetaljnoPage.ensureIsVisiblereviewsBtn();
        kulturnaPonudaDetaljnoPage.getReviewsButton().click();
        driver.get("http://localhost:4200/your-review/100");
        justWait();
        //yourReviewPage.getOcena().clear();
        yourReviewPage.getOcena().sendKeys("4");
        yourReviewPage.getKomentar().clear();
        yourReviewPage.getKomentar().sendKeys("Test neki");
        yourReviewPage.ensureIsVisiblenapraviBtn();
        yourReviewPage.getNapraviButton().click();
        justWait();
        driver.get("http://localhost:4200/reviews/100");
        justWait();
        reviewsPage.ensureIsVisibleSearchBtn();
        reviewsPage.getOcena().sendKeys("4");
        reviewsPage.getSearchButton().click();
        justWait();
        assertEquals(1,reviewsPage.getElementi().size());
        logOut();
    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }
}
