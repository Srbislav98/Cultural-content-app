package com.kts.cultural_content.e2e;

import com.kts.cultural_content.pages.HomePage;
import com.kts.cultural_content.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePageE2ETest {

    private WebDriver webDriver;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        homePage = PageFactory.initElements(webDriver, HomePage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void AdminOptionsTest() throws InterruptedException {
        webDriver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("124@gmail.com");
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginBtn().click();
        justWait(1000);
        homePage.ensureIsVisibleAddKPBtn();
        homePage.ensureIsVisibleEditKPBtn();
        homePage.ensureIsVisibleDeleteKPBtn();
        homePage.ensureIsVisibleAddTKPBtn();
        justWait(5000);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    @Test
    public void  FilterContentTest() throws InterruptedException {
        webDriver.get("http://localhost:4200");
        homePage.getFilterContent().sendKeys("g");
        justWait(1000);
        homePage.getFilterContentBtn().click();
        justWait(5000);
        homePage.getHomeBtn().click();
        justWait(1000);
    }

    @Test
    public void  FilterLocationTest() throws InterruptedException {
        webDriver.get("http://localhost:4200");
        homePage.getFilterLocation().sendKeys("Beograd");
        justWait(1000);
        homePage.getFilterLocationBtn().click();
        justWait(5000);
        homePage.getHomeBtn().click();
        justWait(1000);
    }

    @Test
    public void  FilterLocationTestFail() throws InterruptedException {
        webDriver.get("http://localhost:4200");
        homePage.getFilterLocation().sendKeys("Nedodjija");
        justWait(1000);
        homePage.getFilterLocationBtn().click();
        justWait(5000);
        homePage.getHomeBtn().click();
        justWait(1000);
    }

    @Test
    public void  FilterContentTestFail() throws InterruptedException {
        webDriver.get("http://localhost:4200");
        homePage.getFilterContent().sendKeys("Jake Paul");
        justWait(1000);
        homePage.getFilterContentBtn().click();
        justWait(5000);
        homePage.getHomeBtn().click();
        justWait(1000);
    }

    @Test
    public void RegisteredUserOptionsTest() throws InterruptedException {
        webDriver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        justWait(1000);
        homePage.ensureIsVisibleProfileBtn();
        justWait(5000);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    @Test
    public void AdminOptionsClickableTest() throws InterruptedException {
        webDriver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("124@gmail.com");
        loginPage.getPassword().sendKeys("admin");
        loginPage.getLoginBtn().click();
        justWait(1000);
        homePage.ensureIsVisibleAddKPBtn();
        homePage.getAddKPBtn().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(1000);
        homePage.ensureIsVisibleEditKPBtn();
        homePage.getEditKPBtn().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(1000);
        homePage.ensureIsVisibleDeleteKPBtn();
        /*homePage.getDeleteKPBtn().click();
        justWait(2000);
        homePage.getHomeBtn().click();*/
        justWait(1000);
        homePage.ensureIsVisibleAddTKPBtn();
        homePage.getAddTKPBtn().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(1000);
        homePage.getKpDetaljno().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(5000);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    @Test
    public void RegisteredUserOptionsClickableTest() throws InterruptedException {
        webDriver.get("http://localhost:4200/login");
        loginPage.getEmail().sendKeys("123@gmail.com");
        loginPage.getPassword().sendKeys("user");
        loginPage.getLoginBtn().click();
        justWait(1000);
        homePage.getKpDetaljno().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(2000);
        homePage.ensureIsVisibleProfileBtn();
        homePage.getProfileBtn().click();
        justWait(2000);
        homePage.getHomeBtn().click();
        justWait(5000);
        homePage.ensureIsVisibleSignoutBtn();
        homePage.getSignoutBtn().click();
    }

    private void justWait(int milliseconds) throws InterruptedException {
        synchronized (webDriver)
        {
            webDriver.wait(milliseconds);
        }
    }
}
