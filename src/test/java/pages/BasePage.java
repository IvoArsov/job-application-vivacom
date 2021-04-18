package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public WebDriver browser;

    private By devicesBtnLocator = By.xpath("(//a[text()='Устройства'])[2]");
    private By backToHomeBtnLocator = By.xpath("(//a[@href='/'])[2]");

    public BasePage(WebDriver browserDriver) {
        this.browser = browserDriver;
    }

    public void selectSubmenuByMenu(String submenu) throws InterruptedException {
        Thread.sleep(3000);
        String xpath = "//a[@title='" + submenu + "']";
        WebElement submenuBtn = this.browser.findElement(By.xpath(xpath));
        WebElement deviceBtn = this.browser.findElement(devicesBtnLocator);
        deviceBtn.click();
        Thread.sleep(3000);
        submenuBtn.click();
    }

    public String getPageTitle() throws InterruptedException {
        Thread.sleep(3000);
        return this.browser.getTitle();
    }

    public void backToHome() throws InterruptedException {
        Thread.sleep(3000);
        WebElement backToHomeBtn = this.browser.findElement(backToHomeBtnLocator);
        backToHomeBtn.click();
    }
}
