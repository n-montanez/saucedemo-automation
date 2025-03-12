package com.globant.pages;

import com.globant.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test=title")
    private WebElement lblTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test=subtotal-label]")
    private WebElement lblSubtotal;

    @FindBy(css = "[data-test=tax-label]")
    private WebElement lblTaxes;

    @FindBy(css = "[data-test=total-label]")
    private WebElement lblTotal;

    @FindBy(id = "cancel")
    private WebElement btnCancel;

    @FindBy(id = "finish")
    private WebElement btnFinish;

    /**
     * Creates a list with each receipt price: Products, Taxes & Total
     *
     * @return List of doubles
     */
    public List<Double> getPriceInfo() {
        List<Double> data = new ArrayList<>();
        /*
         * Uses regex to separate price label from $0.99 to 0.99 to parse as double
         */
        data.add(Double.parseDouble(lblSubtotal.getText().split("\\$")[1]));
        data.add(Double.parseDouble(lblTaxes.getText().split("\\$")[1]));
        data.add(Double.parseDouble(lblTotal.getText().split("\\$")[1]));
        return data;
    }

    /**
     * Creates a list with each cart's product price and their total
     *
     * @return List of doubles with each product price. Final position contains the total price
     */
    public List<Double> getProductsPriceInfo() {
        List<Double> data = new ArrayList<>();
        double total = 0;
        for (WebElement item : cartItems) {
            String priceLabel = item.findElement(By.cssSelector("[data-test=inventory-item-price]")).getText();
            /*
             * Uses regex to separate price label from $0.99 to 0.99 to parse as double
             */
            double price = Double.parseDouble(priceLabel.split("\\$")[1]);
            total += price;
            data.add(price);
        }
        data.add(total);
        return data;
    }

    public CheckoutCompletePage finishCheckout() {
        btnFinish.click();
        return new CheckoutCompletePage(driver);
    }

}
