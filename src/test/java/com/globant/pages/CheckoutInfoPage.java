package com.globant.pages;

import com.globant.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage {
    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-test=title")
    private WebElement lblTitle;

    @FindBy(id = "first-name")
    private WebElement fldFirstName;

    @FindBy(id = "last-name")
    private WebElement fldLastName;

    @FindBy(id = "postal-code")
    private WebElement fldPostalCode;

    @FindBy(id = "continue")
    private WebElement btnContinue;

    @FindBy(id = "cancel")
    private WebElement btnCancel;

    public void fillForm(String firstName, String lastName, String postalCode) {
        fldFirstName.sendKeys(firstName);
        fldLastName.sendKeys(lastName);
        fldPostalCode.sendKeys(postalCode);
    }

    public CheckoutOverviewPage continueCheckout() {
        btnContinue.click();
        return new CheckoutOverviewPage(driver);
    }

    public CartPage cancelCheckout() {
        btnCancel.click();
        return new CartPage(driver);
    }

    public WebElement getLblTitle() {
        return lblTitle;
    }

    public WebElement getFldFirstName() {
        return fldFirstName;
    }

    public WebElement getFldLastName() {
        return fldLastName;
    }

    public WebElement getFldPostalCode() {
        return fldPostalCode;
    }
}
