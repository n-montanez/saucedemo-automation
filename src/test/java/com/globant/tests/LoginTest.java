package com.globant.tests;

import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
import com.globant.utils.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(testName = "Login test")
    public void TestLogin() {
        LoginPage loginPage = loadLoginPage();
        ProductsPage productsPage = loginPage.standardLogin();
    }
}
