package com.globant.tests;

import com.globant.pages.Header;
import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
import com.globant.pages.SidebarMenu;
import com.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginUser() {
        LoginPage loginPage = loadLoginPage();

        // User is on login page
        Assert.assertNotNull(loginPage.getLblLoginTitle());
        softAssert.assertEquals(loginPage.getLblLoginTitle().getText(), "Swag Labs");

        // Go to products page after login
        productsPage = loginPage.standardLogin();
    }

    @Test(testName = "Logout and redirected to login page")
    public void Logout() {
        Header header = new Header(productsPage.getDriver());
        SidebarMenu sidebarMenu = header.openBurgerMenu();
        sidebarMenu.logout();
    }
}
