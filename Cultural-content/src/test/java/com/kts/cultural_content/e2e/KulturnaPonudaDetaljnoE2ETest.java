package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.KulturnaPonudaDetaljnoPage;
import com.kts.cultural_content.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class KulturnaPonudaDetaljnoE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private KulturnaPonudaDetaljnoPage kulturnaPonudaDetaljnoPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        kulturnaPonudaDetaljnoPage = PageFactory.initElements(driver, KulturnaPonudaDetaljnoPage.class);
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

    }
    public void logOut(){
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    @Test
    public void UnsubscribeTest() throws InterruptedException{
        logIn();
        driver.get("http://localhost:4200/kulturna-ponuda-detaljno/100");
        justWait();
        kulturnaPonudaDetaljnoPage.getSubButton().click();
        kulturnaPonudaDetaljnoPage.ensureIsVisibleSubBtn();
        assertEquals("Subscribe", kulturnaPonudaDetaljnoPage.getSubButton().getText());
        logOut();
    }

    @Test
    public void SubscribeTest() throws InterruptedException{
        logIn();
        driver.get("http://localhost:4200/kulturna-ponuda-detaljno/101");
        justWait();
        kulturnaPonudaDetaljnoPage.getSubButton().click();
        kulturnaPonudaDetaljnoPage.ensureIsVisibleSubBtn();
        assertEquals("Unsubscribe", kulturnaPonudaDetaljnoPage.getSubButton().getText());
        logOut();
    }



    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(3000);
        }
    }
}
