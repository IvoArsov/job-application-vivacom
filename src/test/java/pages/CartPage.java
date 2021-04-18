package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    private By proceedShoppingBtnLocator = By.xpath("//a[@title='Продължи с пазаруването']");
    private By overallAmountHolderLocator = By.className("e-care-home-big-bill-price-digits");
    private By removeItemBtnLocator = By.xpath("//button[@class='btn-close']");
    private By termOfConditionLabelLocator = By.xpath("//a[@href='/online/bg/tac/view/category/mobile-termes-and-conditions']");
    private By proceedClientBtnLocator = By.xpath("//button[text()='Продължи като клиент']");
    private By proceedGuestBtnLocator = By.xpath("//button[text()='Продължи като гост']");
    private By termOfConditionCheckboxLocator = By.className("icon-box_empty");
    private By emptyShoppingCartMainLabelLocator = By.xpath("//div[@class='empty-shopping-cart']//h3");
    private By emptyShoppingCartSubLabelLocator = By.xpath("//div[@class='empty-shopping-cart']//p");

    public CartPage(WebDriver browserDriver) {
        super(browserDriver);
    }

    private List<WebElement> prepareListOfProductsInCart(){
        List<WebElement> closeBtnList = this.browser.findElements(removeItemBtnLocator);
        closeBtnList.remove(0);
        closeBtnList.remove(closeBtnList.size() - 1);
        return closeBtnList;
    }

    public void proceedShopping() throws InterruptedException {
        Thread.sleep(3000);
        WebElement proceedShoppingBtn = this.browser.findElement(proceedShoppingBtnLocator);
        proceedShoppingBtn.click();
    }

    public double getOverallAmount() {
        WebElement overallAmountHolder = this.browser.findElement(overallAmountHolderLocator);
        String overallPrice = overallAmountHolder.getText();
        String overallPriceWithoutLetters = overallPrice.replaceAll("[^\\d.]", "");
        StringBuffer sb = new StringBuffer(overallPriceWithoutLetters);
        sb.deleteCharAt(sb.length()-1);
        String finalString = String.valueOf(sb);
        double convertedFinalString = Double.parseDouble(finalString);
        return convertedFinalString;
    }

    public void removeItemByNumber(int numberOfItem) {
        prepareListOfProductsInCart().get(numberOfItem).click();
    }

    public boolean isTermOfConditionDisplayed() {
        boolean isTermOfConditionVisible;
        WebElement termOfCondition = this.browser.findElement(termOfConditionLabelLocator);
        WebElement termOfConditionCheckbox = this.browser.findElement(termOfConditionCheckboxLocator);
        if (termOfCondition.isDisplayed() && termOfConditionCheckbox.isDisplayed()){
            isTermOfConditionVisible = true;
        } else {
            isTermOfConditionVisible = false;
        }

        return isTermOfConditionVisible;
    }

    public void accessTermOfCondition() throws InterruptedException {
        Thread.sleep(3000);
        WebElement termOfConditionCheckbox = this.browser.findElement(termOfConditionCheckboxLocator);
        termOfConditionCheckbox.click();
    }

    public boolean isProceedButtonsActive(){
        boolean isButtonsActive = true;
        WebElement proceedClientBtn = this.browser.findElement(proceedClientBtnLocator);
        WebElement proceedGuestBtn = this.browser.findElement(proceedGuestBtnLocator);
        String classNameOfClientBtn = proceedClientBtn.getAttribute("class");
        String classNameOfGuestBtn = proceedGuestBtn.getAttribute("class");
        String[] classNamesArr = {classNameOfClientBtn, classNameOfGuestBtn};

        for (String className: classNamesArr) {
            if(className.contains("disable-elm")){
                isButtonsActive = false;
            }else if(className.contains("btn-success")){
                isButtonsActive = true;
            }
        }

        return isButtonsActive;
    }

    public void emptyCart(){
        for (WebElement closeBtn: prepareListOfProductsInCart()) {
            closeBtn.click();
        }
    }

    public String getEmptyCartLabels() throws InterruptedException {
        Thread.sleep(3000);
        WebElement emptyCartMainLabel = this.browser.findElement(emptyShoppingCartMainLabelLocator);
        WebElement emptyCartSubLabel = this.browser.findElement(emptyShoppingCartSubLabelLocator);
        String outputString = emptyCartMainLabel.getText() + ". " + emptyCartSubLabel.getText();
        return outputString;
    }
}
