package com.globant.tests;

import com.globant.data.TestDataProvider;
import com.globant.pages.*;
import com.globant.utils.BaseTest;
import com.globant.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class PurchaseFlowTest extends BaseTest {

    private ProductsPage productsPage;

    /**
     * Precondition: Log in and on products page
     */
    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    /**
     * Follow a complete purchase workflow:
     * Adds a product to the cart
     * Starts checkout
     * Fills out checkout info
     * Verifies checkout overview
     * Completes the purchase
     *
     * @param firstName  provided user's first name
     * @param lastName   provided user's last name
     * @param postalCode provided user's location postal code
     */
    @Test(testName = "Complete a purchase workflow", dataProviderClass = TestDataProvider.class, dataProvider = "user-info")
    public void PurchaseWorkflow(String firstName, String lastName, String postalCode) {
        // Assert product's page URL
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + productsPath);

        // Select a random product and add it to the cart
        TestUtils.selectProducts(productsPage, 1);

        // Assert that cart badge shows correct amount of items
        Header header = new Header(productsPage.getDriver());
        Assert.assertEquals(header.getBadgeCart().getText(), "1");

        // Assert cart page content
        CartPage cartPage = header.goToCart();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + cartPath);
        Assert.assertEquals(cartPage.getCartItems().size(), 1);
        Assert.assertEquals(cartPage.getLblTitle().getText(), "Your Cart");

        // Assert Checkout Information page content
        CheckoutInfoPage checkoutInfoPage = cartPage.goToCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + checkoutInfoPath);
        Assert.assertEquals(checkoutInfoPage.getLblTitle().getText(), "Checkout: Your Information");
        Assert.assertEquals(checkoutInfoPage.getFldFirstName().getAttribute("placeholder"), "First Name");
        Assert.assertEquals(checkoutInfoPage.getFldLastName().getAttribute("placeholder"), "Last Name");
        Assert.assertEquals(checkoutInfoPage.getFldPostalCode().getAttribute("placeholder"), "Zip/Postal Code");

        checkoutInfoPage.fillForm(firstName, lastName, postalCode);

        // Assert Checkout Overview page content
        CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.continueCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + checkoutOverviewPath);

        // Assert that product prices are added correctly
        List<Double> productPrices = checkoutOverviewPage.getProductsPriceInfo();
        List<Double> totalPrices = checkoutOverviewPage.getPriceInfo();

        Assert.assertEquals(productPrices.get(productPrices.size() - 1), totalPrices.get(0));
        Assert.assertEquals(totalPrices.get(0) + totalPrices.get(1), totalPrices.get(2));

        // Assert Checkout Complete page content
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + checkoutCompletePath);
        Assert.assertEquals(checkoutCompletePage.getLblCompletedOrder().getText(), "Thank you for your order!");
    }
}
