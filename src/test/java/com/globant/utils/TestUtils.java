package com.globant.utils;

import com.globant.pages.ProductsPage;
import com.globant.pages.SingleProductPage;

import java.util.List;

public class TestUtils {
    public static void selectProducts(ProductsPage productsPage, int amount) {
        List<Integer> selectedProducts = productsPage.selectRandomIndexes(amount);
        for (Integer i : selectedProducts) {
            SingleProductPage singleProductPage = productsPage.selectProduct(i);
            singleProductPage.addProductToCart();
            productsPage = singleProductPage.returnToProducts();
        }
    }
}
