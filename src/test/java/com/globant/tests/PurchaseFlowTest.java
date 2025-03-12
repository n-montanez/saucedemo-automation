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

        // Assert that cart badge shows correct amount of items
        Assert.assertEquals(header.getBadgeCart().getText(), "1");

        // Assert cart page content
        CartPage cartPage = header.goToCart();
        Assert.assertEquals(cartPage.getCartItems().size(), 1);
        Assert.assertEquals(cartPage.getLblTitle().getText(), "Your Cart");

        // Assert Checkout Information page content
        CheckoutInfoPage checkoutInfoPage = cartPage.goToCheckout();
        Assert.assertEquals(checkoutInfoPage.getLblTitle().getText(), "Checkout: Your Information");
        Assert.assertEquals(checkoutInfoPage.getFldFirstName().getAttribute("placeholder"), "First Name");
        Assert.assertEquals(checkoutInfoPage.getFldLastName().getAttribute("placeholder"), "Last Name");
        Assert.assertEquals(checkoutInfoPage.getFldPostalCode().getAttribute("placeholder"), "Zip/Postal Code");

        checkoutInfoPage.fillForm(firstName, lastName, postalCode);

        // Assert Checkout Overview page content
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout();

        // Assert that product prices are added correctly
        List<Double> productPrices = checkoutOverviewPage.getProductsPriceInfo();
        List<Double> totalPrices = checkoutOverviewPage.getPriceInfo();

        Assert.assertEquals(productPrices.get(productPrices.size() - 1), totalPrices.get(0));
        Assert.assertEquals(totalPrices.get(0) + totalPrices.get(1), totalPrices.get(2));

        // Assert Checkout Complete page content
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getLblCompletedOrder().getText(), "Thank you for your order!");
    }
}
