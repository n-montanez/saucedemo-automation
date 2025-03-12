package com.globant.tests;

import com.globant.pages.*;
import com.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RemoveItemsFromCartTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginUser() {
        productsPage = loginAndGetProductsPage();
    }

    @Test(testName = "Remove all the elements from the shopping cart")
    public void RemoveItemsFromCart() {
        // Generate 3 random indexes based on products amount and add them to the cart
        List<Integer> selectedProducts = productsPage.selectRandomIndexes(3);
        for (Integer i : selectedProducts) {
            SingleProductPage singleProductPage = productsPage.selectProduct(i);
            singleProductPage.addProductToCart();
            productsPage = singleProductPage.returnToProducts();
        }
        Header header = new Header(productsPage.getDriver());
        CartPage cartPage = header.goToCart();
        cartPage.emptyCart();

        // Assert cart items are empty
        Assert.assertTrue(cartPage.getCartItems().isEmpty());
    }

}
