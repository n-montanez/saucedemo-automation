package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title")
    private WebElement lblTitle;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    @FindBy(id = "continue-shopping")
    private WebElement btnContinueShopping;

    public CheckoutInfoPage goToCheckout() {
        btnCheckout.click();
        return new CheckoutInfoPage(driver);
    }

    public void emptyCart() {
        for (WebElement item : cartItems) {
            WebElement btnRemove = item.findElement(By.cssSelector("button[id^=\"remove-\"]"));
            btnRemove.click();
        }
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }
}
