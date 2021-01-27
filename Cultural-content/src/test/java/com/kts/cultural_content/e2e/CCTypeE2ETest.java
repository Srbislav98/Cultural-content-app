package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.AddCCTypePage;
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

import static org.junit.Assert.*;

public class CCTypeE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddCCTypePage accPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        accPage = PageFactory.initElements(driver, AddCCTypePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void CreateCCTestSuccess() throws InterruptedException {
        login();
        justWait();
        driver.get("http://localhost:4200/add-tkp");

        accPage.getName().sendKeys("testTip");

        List<WebElement> deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-primary"));
        deca.get(0).click();

        justWait();
        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().equals("testTip"))
                napravio = true;
        }

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-danger"));
        deca.get(deca.size()-1).click();

        justWait();
        assertTrue(napravio);
    }

    @Test
    public void CreateCCTestFail() throws InterruptedException {
        login();
        justWait();
        driver.get("http://localhost:4200/add-tkp");

        accPage.getName().sendKeys("muzej");

        List<WebElement> deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-primary"));
        deca.get(0).click();

        assertEquals("http://localhost:4200/add-tkp", driver.getCurrentUrl());
    }

    @Test
    public void DeleteCCTypeTestSuccess() throws InterruptedException {
        login();
        justWait();

        List<WebElement>deca = new ArrayList<>();
        deca = driver.findElements(By.id("add_kp"));
        deca.get(1).click();
        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-danger"));
        deca.get(0).click();

        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-danger"));
        deca.get(deca.size()-1).click();

        justWait();
        //driver.get("http://localhost:4200/add-tkp");

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().equals("sajam"))
                napravio = true;
        }


        driver.get("http://localhost:4200/add-tkp");

        accPage.getName().sendKeys("sajam");
        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-primary"));
        deca.get(0).click();

        justWait();
        assertFalse(napravio);
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
