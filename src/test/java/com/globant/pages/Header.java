package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".app_logo")
    private WebElement lblLogo;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement btnBurgerMenu;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartButton;

    public CartPage goToCart() {
        cartButton.click();
        return new CartPage(this.driver);
    }

    public SidebarMenu openBurgerMenu() {
        btnBurgerMenu.click();
        return new SidebarMenu(driver);
    }
}
