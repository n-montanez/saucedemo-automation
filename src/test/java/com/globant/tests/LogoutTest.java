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

    /**
     * Precondition: Log in and on products page
     */
    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    /**
     * Logs out from the system using burger menu button
     */
    @Test(testName = "Logout and redirected to login page")
    public void Logout() {
        // Assert user is on products page
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + productsPath);

        Header header = new Header(productsPage.getDriver());
        Assert.assertEquals(header.getLblLogo().getText(), "Swag Labs");

        // Assert Logout button is present and its content
        SidebarMenu sidebarMenu = header.openBurgerMenu();
        Assert.assertEquals(sidebarMenu.getLinkLogout().getText(), "Logout");

        // Assert user is back on login page
        LoginPage loginPage = sidebarMenu.logout();
        Assert.assertEquals(loginPage.getLblLoginTitle().getText(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }
}
