package com.globant.tests;

import com.globant.pages.Header;
import com.globant.pages.LoginPage;
import com.globant.pages.ProductsPage;
import com.globant.pages.SidebarMenu;
import com.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    @Test(testName = "Logout and redirected to login page")
    @Parameters({"baseUrl"})
    public void Logout(String baseUrl) {
        Header header = new Header(productsPage.getDriver());
        Assert.assertEquals(header.getLblLogo().getText(), "Swag Labs");

        SidebarMenu sidebarMenu = header.openBurgerMenu();
        Assert.assertEquals(sidebarMenu.getLinkLogout().getText(), "Logout");

        LoginPage loginPage = sidebarMenu.logout();
        Assert.assertEquals(loginPage.getLblLoginTitle().getText(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }
}
