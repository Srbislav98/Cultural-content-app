package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.EditProfilePage;
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

public class EditProfileE2ETest {
    private WebDriver driver;

    private ProfilePage profilePage;
    private EditProfilePage editProfilePage;
    private LoginPage loginPage;

    private HomePage homePage;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        editProfilePage = PageFactory.initElements(driver, EditProfilePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void EditProfileTest() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        homePage.ensureIsVisibleProfileBtn();
        //homePage.getProfileBtn().click();
        driver.get("http://localhost:4200/edit-profile");
        justWait();
        editProfilePage.getIme().clear();
        editProfilePage.getIme().sendKeys("Stefan");
        editProfilePage.getPrezime().clear();
        editProfilePage.getPrezime().sendKeys("Stefanovic");
        editProfilePage.getPassword().sendKeys("stefanov");
        editProfilePage.getConfirmBtn().click();
        String a=profilePage.ensureIsVisibleMessage();
        assertEquals("Successful profile editing.", a);
        profilePage.getPoruka().click();
        profilePage.ensureIsVisibleEditBtn();
        assertEquals(profilePage.getIme().getText(),"Stefan");
        assertEquals(profilePage.getPrezime().getText(),"Stefanovic");
        homePage.getSignoutBtn().click();

    }
    @Test
    public void EditProfileButCancelTestOne() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("stefanov");
        loginPage.getLoginBtn().click();
        homePage.ensureIsVisibleProfileBtn();
        //homePage.getProfileBtn().click();
        driver.get("http://localhost:4200/profil");
        justWait();
        String ime=profilePage.getIme().getText();
        String prezime=profilePage.getPrezime().getText();
        driver.get("http://localhost:4200/edit-profile");
        justWait();
        editProfilePage.getIme().sendKeys("Stefadsdffn");
        editProfilePage.getPrezime().sendKeys("Stefanfdfdfdovic");
        editProfilePage.getPassword().sendKeys("stefadffdnov");
        editProfilePage.getCancelBtn().click();
        //String a=profilePage.ensureIsVisibleMessage();
        //assertEquals("Successful profile editing.", a);
        //profilePage.getPoruka().click();
        assertEquals(ime,"Stefan");
        assertEquals(prezime,"Stefanovic");
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
