package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title")
    private WebElement lblTitle;

    @FindBy(css = ".inventory_item")
    private List<WebElement> products;

    public List<Integer> selectRandomIndexes(int amount) {
        List<Integer> selected = new ArrayList<>();
        do {
            int random = ThreadLocalRandom.current().nextInt(0, products.size());
            if (!selected.contains(random))
                selected.add(random);
        } while (selected.size() < amount);
        return selected;
    }

    public SingleProductPage selectProduct(int index) {
        WebElement selectedProduct = this.products.get(index);
        WebElement clickableTitle = selectedProduct.findElement(By.cssSelector("a[id^=\"item\"][id$=\"img_link\"]"));
        clickableTitle.click();
        return new SingleProductPage(this.driver);
    }
}
