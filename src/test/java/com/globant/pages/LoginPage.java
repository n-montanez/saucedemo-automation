package com.globant.pages;

import com.globant.utils.BasePage;
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

    @FindBy(className = "login_logo")
    private WebElement lblLoginTitle;

    /**
     * Log in with standard user using SauceDemo given data.
     *
     * @return products page object model
     */
    public ProductsPage standardLogin() {
        this.fldUsername.sendKeys("standard_user");
        this.fldPassword.sendKeys("secret_sauce");
        this.btnLogin.click();
        return new ProductsPage(super.getDriver());
    }

    public WebElement getFldUsername() {
        return fldUsername;
    }

    public WebElement getFldPassword() {
        return fldPassword;
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public WebElement getLblLoginTitle() {
        return lblLoginTitle;
    }
}
