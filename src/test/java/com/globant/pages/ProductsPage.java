package com.globant.pages;

import com.globant.utils.BasePage;
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

    /**
     * Uses the list of present products to randomly select any amount, ensuring no repetitions.
     *
     * @param amount amount of products to select
     * @return list of selected products indexes
     */
    public List<Integer> selectRandomIndexes(int amount) {
        List<Integer> selected = new ArrayList<>();
        do {
            int random = ThreadLocalRandom.current().nextInt(0, products.size());
            // Verify for duplicates
            if (!selected.contains(random))
                selected.add(random);
        } while (selected.size() < amount);
        return selected;
    }

    /**
     * Selects the given product and navigates to its details page
     *
     * @param index position of the product to select and click
     * @return product detail page object model
     */
    public SingleProductPage selectProduct(int index) {
        WebElement selectedProduct = this.products.get(index);
        WebElement clickableTitle = selectedProduct.findElement(By.cssSelector("a[id^=\"item\"][id$=\"img_link\"]"));
        clickableTitle.click();
        return new SingleProductPage(this.driver);
    }
}
