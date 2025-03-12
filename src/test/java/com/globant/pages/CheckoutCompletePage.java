package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test=title")
    private WebElement lblTitle;

    @FindBy(css = "[data-test=complete-header]")
    private WebElement lblCompletedOrder;

    @FindBy(id = "back-to-products")
    private WebElement btnBackHome;

    public WebElement getLblCompletedOrder() {
        return lblCompletedOrder;
    }

    public ProductsPage goBackHome() {
        btnBackHome.click();
        return new ProductsPage(driver);
    }
}
