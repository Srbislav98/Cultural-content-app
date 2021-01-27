package com.kts.cultural_content.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @FindBy(id="signin-button")
    private WebElement singinBtn;
    @FindBy(id="signup-button")
    private WebElement signupBtn;

    @FindBy(id="signout-button")
    private WebElement signoutBtn;

    @FindBy(id="profile-button")
    private WebElement profileBtn;

    @FindBy(id="filter_content")
    private WebElement filterContentBtn;

    @FindBy(id="filter_location")
    private WebElement filterLocationBtn;

    @FindBy(id="podatak")
    private WebElement filterContent;

    @FindBy(id="podatak2")
    private WebElement filterLocation;

    @FindBy(id="add_kp")
    private WebElement addKPBtn;

    @FindBy(id="edit_kp")
    private WebElement editKPBtn;

    @FindBy(id="delete_kp")
    private WebElement deleteKPBtn;

    @FindBy(id="home_btn")
    private WebElement homeBtn;

    @FindBy(id="add_tkp")
    private WebElement addTKPBtn;

    @FindBy(id="kp_detaljno")
    private WebElement kpDetaljno;

    @FindBy(id="reset-button")
    private WebElement resetBtn;

    @FindBy(className = "in_waves")
    private List<WebElement> kpDetailBtns;

    public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsNotVisibleSigninBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signin-button")));
    }
    public void ensureIsNotVisibleSignupBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signup-button")));
    }
    public void ensureIsNotVisibleSignoutBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signout-button")));
    }

    public void ensureIsVisibleSignoutBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("signout-button")));
    }
    public void ensureIsVisibleProfileBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("profile-button")));
    }
    public void ensureIsNotVisibleProfileBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("profile-button")));
    }

    public void ensureIsVisibleAddKPBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_kp")));
    }

    public void ensureIsVisibleEditKPBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_kp")));
    }

    public void ensureIsVisibleDeleteKPBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("delete_kp")));
    }

    public void ensureIsVisibleAddTKPBtn() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_tkp")));
    }

    public WebElement getSinginBtn() {
        return singinBtn;
    }

    public WebElement getSignupBtn() {
        return signupBtn;
    }

    public WebElement getSignoutBtn() {
        return signoutBtn;
    }

    public WebElement getProfileBtn() {
        return profileBtn;
    }

    public WebElement getFilterContentBtn() {
        return filterContentBtn;
    }

    public void setFilterContentBtn(WebElement filterContentBtn) {
        this.filterContentBtn = filterContentBtn;
    }

    public WebElement getFilterLocationBtn() {
        return filterLocationBtn;
    }

    public void setFilterLocationBtn(WebElement filterLocationBtn) {
        this.filterLocationBtn = filterLocationBtn;
    }

    public WebElement getFilterContent() {
        return filterContent;
    }

    public void setFilterContent(WebElement filterContent) {
        this.filterContent = filterContent;
    }

    public WebElement getFilterLocation() {
        return filterLocation;
    }

    public void setFilterLocation(WebElement filterLocation) {
        this.filterLocation = filterLocation;
    }

    public WebElement getAddKPBtn() {
        return addKPBtn;
    }

    public void setAddKPBtn(WebElement addKPBtn) {
        this.addKPBtn = addKPBtn;
    }

    public WebElement getEditKPBtn() {
        return editKPBtn;
    }

    public void setEditKPBtn(WebElement editKPBtn) {
        this.editKPBtn = editKPBtn;
    }

    public WebElement getDeleteKPBtn() {
        return deleteKPBtn;
    }

    public void setDeleteKPBtn(WebElement deleteKPBtn) {
        this.deleteKPBtn = deleteKPBtn;
    }

    public WebElement getHomeBtn() {
        return homeBtn;
    }

    public void setHomeBtn(WebElement homeBtn) {
        this.homeBtn = homeBtn;
    }

    public WebElement getAddTKPBtn() {
        return addTKPBtn;
    }

    public void setAddTKPBtn(WebElement addTKPBtn) {
        this.addTKPBtn = addTKPBtn;
    }

    public WebElement getKpDetaljno() {
        return kpDetaljno;
    }

    public void setKpDetaljno(WebElement kpDetaljno) {
        this.kpDetaljno = kpDetaljno;
    }

    public WebElement getResetBtn() {
        return resetBtn;
    }

    public void setResetBtn(WebElement resetBtn) {
        this.resetBtn = resetBtn;
    }

    public List<WebElement> getKpDetailBtns() {
        return kpDetailBtns;
    }

    public void setKpDetailBtns(List<WebElement> kpDetailBtns) {
        this.kpDetailBtns = kpDetailBtns;
    }
}
