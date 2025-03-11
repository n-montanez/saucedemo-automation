package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement fldUsername;

    @FindBy(id = "password")
    private WebElement fldPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    public ProductsPage standardLogin() {
        this.fldUsername.sendKeys("standard_user");
        this.fldPassword.sendKeys("secret_sauce");
        this.btnLogin.click();
        return new ProductsPage(super.getDriver());
    }
}
