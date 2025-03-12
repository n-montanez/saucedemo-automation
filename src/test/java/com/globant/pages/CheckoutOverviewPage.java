package com.globant.pages;

import com.globant.utils.BasePage;
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

    public List<Double> getPriceInfo() {
        List<Double> data = new ArrayList<>();
        data.add(Double.parseDouble(lblSubtotal.getText().split("\\$")[1]));
        data.add(Double.parseDouble(lblTaxes.getText().split("\\$")[1]));
        data.add(Double.parseDouble(lblTotal.getText().split("\\$")[1]));
        return data;
    }

    public CheckoutCompletePage finishCheckout() {
        btnFinish.click();
        return new CheckoutCompletePage(driver);
    }

}
