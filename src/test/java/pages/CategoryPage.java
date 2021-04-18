package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver browserDriver) {
        super(browserDriver);
    }

    public void selectBrand(String brandName) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//span[text()='" + brandName.toUpperCase() + "']";
        WebElement selectedBrand = this.browser.findElement(By.xpath(xpath));
        selectedBrand.click();
    }

    public void selectColor(String color) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//span[text()='" + color.toUpperCase() + "']";
        WebElement selectedColor = this.browser.findElement(By.xpath(xpath));
        selectedColor.click();
    }

    public void selectItem(String itemName) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//h3[text()='" + itemName + "']";
        WebElement phoneSelector = this.browser.findElement(By.xpath(xpath));
        phoneSelector.click();
    }

    public void selectPriceFilter(String priceRange) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//span[text()='" + priceRange + "']";
        WebElement priceRangeSelector = this.browser.findElement(By.xpath(xpath));
        priceRangeSelector.click();
    }
}
