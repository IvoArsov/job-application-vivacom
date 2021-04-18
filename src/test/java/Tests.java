import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductProfilePage;


public class Tests{
    private static String driverLocation = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
    private WebDriver browser;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", driverLocation);
        this.browser = new ChromeDriver();
        this.browser.manage().deleteAllCookies();
        this.browser.manage().window().maximize();
        this.browser.get("https://www.vivacom.bg/bg/");
    }

    @After
    public void tearDown(){
        this.browser.manage().deleteAllCookies();
        this.browser.quit();
    }

    @Test
    public void mainTest() throws InterruptedException {
        HomePage homePage = new HomePage(this.browser);
        homePage.selectSubmenuByMenu("Мобилни телефони");

        CategoryPage categoryPage = new CategoryPage(this.browser);
        categoryPage.selectBrand("APPLE");
        categoryPage.selectColor("GOLD");
        categoryPage.selectItem("APPLE IPHONE 12 PRO MAX 128GB");

        ProductProfilePage productProfilePage = new ProductProfilePage(this.browser);
        productProfilePage.selectPrice(1979.98);
        productProfilePage.addToCart();

        CartPage cartPage = new CartPage(this.browser);

        //Assert block
        Assert.assertEquals("Кошница | VIVACOM", cartPage.getPageTitle());

        cartPage.proceedShopping();

        homePage.backToHome();
        homePage.selectSubmenuByMenu("Аксесоари");

        categoryPage.selectBrand("APPLE");
        categoryPage.selectPriceFilter("между 5 и 40 лв.");
        categoryPage.selectItem("APPLE СЛУШАЛКИ с LIGHTNING CONNECTOR");

        productProfilePage.addToCart();

        //Assert block
        Assert.assertEquals("Кошница | VIVACOM", cartPage.getPageTitle());

        if (cartPage.getOverallAmount() > 2000){
            cartPage.removeItemByNumber(0);
        }

        //Assert block
        Assert.assertTrue(cartPage.isTermOfConditionDisplayed());
        Assert.assertFalse(cartPage.isProceedButtonsActive());

        cartPage.accessTermOfCondition();

        //Assert block
        Assert.assertTrue(cartPage.isProceedButtonsActive());

        cartPage.emptyCart();

        //Assert block
        Assert.assertEquals("В момента кошницата ви е празна. Вижте актуалните ни оферти и изберете най-подходящата за вас. Ако искате да разгледате предходно добавени продукти, натиснете \"Вход\".", cartPage.getEmptyCartLabels());
    }
}
