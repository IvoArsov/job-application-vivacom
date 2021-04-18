package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductProfilePage extends BasePage {
    private By addToCartBtnLocator = By.xpath("(//button[@type='submit'])[2]");

    public ProductProfilePage(WebDriver browserDriver) {
        super(browserDriver);
    }

    public void selectPrice(double selectedPrice) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//span[text()='\n" +
                "\t\t\t\t\t\t\t\t\t\t" + selectedPrice + "']";
        WebElement priceSelector = this.browser.findElement(By.xpath(xpath));
        priceSelector.click();
    }

    public void addToCart(){
        WebElement addToCartBtn = this.browser.findElement(addToCartBtnLocator);
        addToCartBtn.click();
    }
}
