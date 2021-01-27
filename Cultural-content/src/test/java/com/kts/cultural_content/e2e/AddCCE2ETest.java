package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.AddCCPage;
import com.kts.cultural_content.pages.AlterCCPage;
import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddCCE2ETest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddCCPage accPage;
    private AlterCCPage alterPage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        accPage = PageFactory.initElements(driver, AddCCPage.class);
        alterPage = PageFactory.initElements(driver, AlterCCPage.class);
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

        justWait();
        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().equals("testNaziv"))
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

        justWait();
        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().equals("testNaziv"))
                napravio = true;
        }

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-danger"));
        deca.get(deca.size()-1).click();

        justWait();
        assertFalse(napravio);
    }

    @Test
    public void AlterCCTestSuccess() throws InterruptedException {
        login();
        justWait();

        driver.get("http://localhost:4200");
        //justWait();// da se ucita

        List<WebElement>deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-item"));
        deca.get(deca.size()-2).click();
        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-secondary"));
        deca.get(deca.size()-1).click();

        justWait();

        alterPage.getDescription().sendKeys("testDescription");

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-primary"));
        deca.get(0).click();
        justWait();


        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-item"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().contains("testDescription"))
                napravio = true;
        }
        assertTrue(napravio);
    }

    @Test
    public void DeleteCCTestSuccess() throws InterruptedException {
        login();
        justWait();

        List<WebElement>deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("btn-danger"));
        deca.get(deca.size()-1).click();

        justWait();

        deca = new ArrayList<>();
        deca = driver.findElements(By.className("page-link"));
        deca.get(deca.size()-2).click();
        justWait();

        boolean napravio = false;
        deca = new ArrayList<>();
        deca = driver.findElements(By.tagName("td"));
        for (WebElement w : deca){
            if (w.getText().equals("Spomenik streljanim djacima i profesorima"))
                napravio = true;
        }


        driver.get("http://localhost:4200/add-kp");

        accPage.getName().sendKeys("Spomenik streljanim djacima i profesorima");
        accPage.getAddress().sendKeys("Hasana Brkića");
        accPage.getDescription().sendKeys("Spomenik podignut 1963. godine predstavlja originalno umetnicko delo Miograga ZivkovicatestDescriptiontestDescriptionSpomenik podignut 1963. godine predstavlja originalno umetnicko delo Miograga ZivkovicatestDescription");


        deca = new ArrayList<>();
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
