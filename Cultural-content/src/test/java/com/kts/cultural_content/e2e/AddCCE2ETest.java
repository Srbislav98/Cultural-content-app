package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.AddCCPage;
import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddCCE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddCCPage accPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        accPage = PageFactory.initElements(driver, AddCCPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void CreateCCTestSuccess() throws InterruptedException {
        login();
        justWait();
        driver.get("http://localhost:4200/add-kp");
        //justWait();// da se ucita

        accPage.getName().sendKeys("testNaziv");
        accPage.getAddress().sendKeys("test");
        accPage.getDescription().sendKeys("test...");


        List<WebElement> deca = new ArrayList<>();
        accPage.getType().click();
        //justWait();
        deca = driver.findElements(By.className("scrollable-content"));
        deca.get(0).click();

        //justWait();

        deca = new ArrayList<>();
        accPage.getLocation().click();
        //justWait();
        deca = driver.findElements(By.className("ng-option-label"));
        deca.get(5).click();

        //justWait();

        accPage.getOK().click();
        accPage.getHome().click();

        assertEquals("http://localhost:4200/", driver.getCurrentUrl());

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        assertEquals(4, deca.size());
        //posto pocetni test ima 4 elemenata, sa 2 elementa po strani + next, 5 elemenata ce imate 3 strane + next = 4 strana
    }

    @Test
    public void CreateCCTestFail() throws InterruptedException {
        login();
        justWait();
        List<WebElement>pnb = new ArrayList<>();
        pnb = driver.findElements(By.className("page-link"));
        driver.get("http://localhost:4200/add-kp");
        //justWait();// da se ucita

        accPage.getName().sendKeys("kulturnaponuda");
        accPage.getAddress().sendKeys("test");
        accPage.getDescription().sendKeys("test...");


        List<WebElement> deca = new ArrayList<>();
        accPage.getType().click();
        //justWait();
        deca = driver.findElements(By.className("scrollable-content"));
        deca.get(0).click();

        //justWait();

        deca = new ArrayList<>();
        accPage.getLocation().click();
        //justWait();
        deca = driver.findElements(By.className("ng-option-label"));
        deca.get(5).click();

        //justWait();

        accPage.getOK().click();
        accPage.getHome().click();

        assertEquals("http://localhost:4200/", driver.getCurrentUrl());

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        assertEquals(pnb.size(), deca.size());
        //br strana se nece promeniti
    }

    private void login() throws InterruptedException {
        driver.get("http://localhost:4200/login");

        justWait();

        loginPage.getEmail().sendKeys("124@gmail.com");
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginBtn().click();
    }

    private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(1000);
        }
    }
}
