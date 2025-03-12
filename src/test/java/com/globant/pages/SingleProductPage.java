package com.globant.pages;

import com.globant.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleProductPage extends BasePage {
    public SingleProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test=inventory-item-name]")
    private WebElement lblProductTitle;

    @FindBy(css = "[data-test=inventory-item-price]")
    private WebElement lblProductPrice;

    @FindBy(css = "[data-test=inventory-item-desc]")
    private WebElement lblProductDesc;

    @FindBy(id = "add-to-cart")
    private WebElement btnAddToCart;

    @FindBy(id = "back-to-products")
    private WebElement btnBackProducts;

    public void addProductToCart() {
        this.btnAddToCart.click();
    }

    public ProductsPage returnToProducts() {
        this.btnBackProducts.click();
        return new ProductsPage(this.driver);
    }

    public WebElement getLblProductTitle() {
        return lblProductTitle;
    }

    public WebElement getLblProductPrice() {
        return lblProductPrice;
    }

    public WebElement getLblProductDesc() {
        return lblProductDesc;
    }

    public WebElement getBtnAddToCart() {
        return btnAddToCart;
    }

    public WebElement getBtnBackProducts() {
        return btnBackProducts;
    }
}
