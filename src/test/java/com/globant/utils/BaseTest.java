package com.globant.utils;

import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;

    private void setupDriver(String browser) {
        if ("firefox".equals(browser)) {
            this.driver = new FirefoxDriver();
        } else {
            this.driver = new ChromeDriver();
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public LoginPage loadLoginPage() {
        return new LoginPage(this.driver);
    }

    public ProductsPage loginAndGetProductsPage() {
        LoginPage loginPage = loadLoginPage();

        // User is on login page
        Assert.assertNotNull(loginPage.getLblLoginTitle());
        softAssert.assertEquals(loginPage.getLblLoginTitle().getText(), "Swag Labs");

        // Go to products page after login
        return loginPage.standardLogin();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void beforeMethod(String browser, String baseUrl) {
        setupDriver(browser);
        softAssert = new SoftAssert();
        driver.manage().window().maximize();
        navigateTo(baseUrl);
    }

    @AfterMethod()
    public void afterMethod() {
        driver.close();
    }
}
