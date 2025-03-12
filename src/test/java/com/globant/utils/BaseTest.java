package com.globant.utils;

import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;

    protected String baseUrl;
    protected String productsPath;
    protected String cartPath;
    protected String checkoutInfoPath;
    protected String checkoutOverviewPath;
    protected String checkoutCompletePath;

    /**
     * Sets up selenium browser driver based on given parameter
     *
     * @param browser Suite configuration browser
     */
    private void setupDriver(String browser) {
        if ("firefox".equals(browser)) {
            this.driver = new FirefoxDriver();
        } else {
            this.driver = new ChromeDriver();
        }
    }

    /**
     * Sends driver to given url
     *
     * @param url base url
     */
    public void navigateTo(String url) {
        if(this.driver != null)
            driver.get(url);
    }

    public LoginPage loadLoginPage() {
        return new LoginPage(this.driver);
    }

    /**
     * Log in with standard user and goes to products page
     * Method to be used as a precondition for tests
     *
     * @return products page object model
     */
    public ProductsPage loginAndGetProductsPage() {
        LoginPage loginPage = loadLoginPage();

        // User is on login page
        Assert.assertNotNull(loginPage.getLblLoginTitle());
        softAssert.assertEquals(loginPage.getLblLoginTitle().getText(), "Swag Labs");

        // Go to products page after login
        return loginPage.standardLogin();
    }

    /**
     * Initializes url's and paths for further testing
     * This method is run before each test class
     *
     * @param baseUrl suite configuration base url
     * @param productsPath sauce demo products path
     * @param cartPath sauce demo cart path
     * @param checkoutInfoPath sauce demo checkout information path
     * @param checkoutOverviewPath sauce checkout overview path
     * @param checkoutCompletePath sauce checkout completed path
     */
    @Parameters({"baseUrl", "productsPath", "cartPath", "checkoutInfoPath", "checkoutOverviewPath", "checkoutCompletePath"})
    @BeforeClass
    public void setUpUrls(
            String baseUrl, String productsPath, String cartPath,
            String checkoutInfoPath, String checkoutOverviewPath, String checkoutCompletePath) {
        this.baseUrl = baseUrl;
        this.productsPath = productsPath;
        this.cartPath = cartPath;
        this.checkoutInfoPath = checkoutInfoPath;
        this.checkoutOverviewPath = checkoutOverviewPath;
        this.checkoutCompletePath = checkoutCompletePath;
    }

    /**
     * Initializes driver and soft assert elements to share with all test classes
     *
     * @param browser suite configuration browser
     * @param baseUrl suite configuration base url
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void beforeMethod(String browser, String baseUrl) {
        setupDriver(browser);
        softAssert = new SoftAssert();
        driver.manage().window().maximize();
        navigateTo(baseUrl);
    }

    /**
     * Finishes browser session and closes driver
     */
    @AfterMethod()
    public void afterMethod() {
        driver.close();
    }
}
