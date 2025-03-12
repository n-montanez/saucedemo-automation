package com.globant.pages;

import com.globant.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidebarMenu extends BasePage {
    public SidebarMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "inventory_sidebar_link")
    private WebElement linkInventory;

    @FindBy(id = "about_sidebar_link")
    private WebElement linkAbout;

    @FindBy(id = "logout_sidebar_link")
    private WebElement linkLogout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement linkResetState;

    public LoginPage logout() {
        linkLogout.click();
        return new LoginPage(driver);
    }
}
