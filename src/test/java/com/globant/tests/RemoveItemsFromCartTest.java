package com.globant.tests;

import com.globant.pages.*;
import com.globant.utils.BaseTest;
import com.globant.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveItemsFromCartTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    @Test(testName = "Remove all the elements from the shopping cart")
    public void RemoveItemsFromCart() {
        // Generate 3 random indexes based on products amount and add them to the cart
        TestUtils.selectProducts(productsPage, 3);

        // Assert that cart badge shows correct amount of items
        Header header = new Header(productsPage.getDriver());
        Assert.assertEquals(header.getBadgeCart().getText(), "3");

        // Assert cart page content
        CartPage cartPage = header.goToCart();
        Assert.assertEquals(cartPage.getLblTitle().getText(), "Your Cart");
        Assert.assertEquals(cartPage.getCartItems().size(), 3);
        cartPage.emptyCart();

        // Assert cart items are empty
        Assert.assertTrue(cartPage.getCartItems().isEmpty());
    }

}
