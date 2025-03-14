package com.globant.pages;

import com.globant.utils.BasePage;
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

    @FindBy(css = "[data-test=shopping-cart-badge]")
    private WebElement badgeCart;

    public CartPage goToCart() {
        cartButton.click();
        return new CartPage(this.driver);
    }

    public SidebarMenu openBurgerMenu() {
        btnBurgerMenu.click();
        return new SidebarMenu(driver);
    }

    public WebElement getBadgeCart() {
        return badgeCart;
    }

    public WebElement getLblLogo() {
        return lblLogo;
    }
}
