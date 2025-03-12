package com.globant.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected final WebDriver driver;

    /**
     * Creates base page element and initializes elements annotated with PageFactory
     *
     * @param driver Selenium browser driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
