package com.globant.tests;

import com.globant.data.TestDataProvider;
import com.globant.pages.*;
import com.globant.utils.BaseTest;
import com.globant.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PurchaseFlowTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    @Test(testName = "Complete a purchase workflow", dataProviderClass = TestDataProvider.class, dataProvider = "user-info")
    public void PurchaseWorkflow(String firstName, String lastName, String postalCode) {
        // Select a random product and add it to the cart
        TestUtils.selectProducts(productsPage, 1);

        Header header = new Header(productsPage.getDriver());
        CartPage cartPage = header.goToCart();
        CheckoutInfoPage checkoutInfoPage = cartPage.goToCheckout();
        checkoutInfoPage.fillForm(firstName, lastName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout();

        // Assert that product prices are added correctly
        List<Double> prices = checkoutOverviewPage.getPriceInfo();
        Assert.assertEquals(prices.get(0) + prices.get(1), prices.get(2));

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getLblCompletedOrder().getText(), "Thank you for your order!");
    }
}
