package com.globant.utils;

import com.globant.pages.ProductsPage;
import com.globant.pages.SingleProductPage;

import java.util.List;

public class TestUtils {
    /**
     * Adds the given amount of products to the cart
     *
     * @param productsPage Base products page object to interact with
     * @param amount Amount of products to add to the cart
     */
    public static void selectProducts(ProductsPage productsPage, int amount) {
        List<Integer> selectedProducts = productsPage.selectRandomIndexes(amount);
        for (Integer i : selectedProducts) {
            SingleProductPage singleProductPage = productsPage.selectProduct(i);
            singleProductPage.addProductToCart();
            productsPage = singleProductPage.returnToProducts();
        }
    }
}
