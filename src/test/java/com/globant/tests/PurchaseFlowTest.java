package com.globant.tests;

import com.globant.pages.*;
import com.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PurchaseFlowTest extends BaseTest {

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

    @Test(testName = "Complete a purchase workflow")
    public void PurchaseWorkflow() {
        // Generate 3 random indexes based on products amount and add them to the cart
        List<Integer> selectedProducts = productsPage.selectRandomIndexes(3);
        for (Integer i : selectedProducts) {
            SingleProductPage singleProductPage = productsPage.selectProduct(i);
            singleProductPage.addProductToCart();
            productsPage = singleProductPage.returnToProducts();
        }
        Header header = new Header(productsPage.getDriver());
        CartPage cartPage = header.goToCart();
        CheckoutInfoPage checkoutInfoPage = cartPage.goToCheckout();
        checkoutInfoPage.fillForm("Nicolas", "Montañez", "166661");
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout();

        // Assert that product prices are added correctly
        List<Double> prices = checkoutOverviewPage.getPriceInfo();
        Assert.assertEquals(prices.get(0) + prices.get(1), prices.get(2));

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getLblCompletedOrder().getText(), "Thank you for your order!");
    }
}
