package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P11_PharmacyPage {

    static float totalPrice;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;

    //Locators
    private final By chefaaIcone =By.xpath("//app-pharmacy-home//div[2]/div/div/div/div/div");
    private final By confirmRegistrationIcon =By.xpath("//ngb-offcanvas-panel/div[3]/button[1]");
    private final By productName =By.id("productName");
    private final By searchBtn =By.cssSelector("[type=\"submit\"]");
    private final By AddAllProducts =By.xpath("(//p-inputnumber/span/button[1])");
    private final By productsPrice=By.xpath("(//div/div/p[1]/span[1])");
    private final By navigateToCart =By.xpath("//app-medical-search//div/div/div[2]/button");


    //Variables
    private final WebDriver driver;

    //constructor
    public P11_PharmacyPage(WebDriver driver) {
        this.driver=driver;
    }

    //Action
    public P11_PharmacyPage ChefaaRegistration()
    {
        Utility.clickElement(driver,chefaaIcone);
        return this;
    }
    public P11_PharmacyPage ConfirmChefaaRegistration()
    {
        Utility.clickElement(driver,confirmRegistrationIcon);
        return this;
    }
    public P11_PharmacyPage enterProductName()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("chefaa"));
        Utility.sendData(driver,productName,"Panadol");
        return this;
    }
    public P11_PharmacyPage searchProduct()
    {
        Utility.clickElement(driver,searchBtn);
        return this;
    }
    public P11_PharmacyPage selectProduct()
    {
        Utility.generalWait(driver,AddAllProducts,15);
        allProducts =driver.findElements(AddAllProducts);
        LogsUtils.info("Number Of All Products Is" + allProducts.size());
        for (int i=1;i <= allProducts.size(); i++)
        {
            By AddProductsToCart = By.xpath("(//p-inputnumber/span/button[1])[" + i + "]");
            Utility.scrollToElement(driver,AddProductsToCart);
            Utility.clickElement(driver, AddProductsToCart);
        }
        return this;
    }
    public P11_PharmacyPage navigateToCart()
    {
        Utility.scrollToElement(driver,navigateToCart);
        Utility.clickElement(driver,navigateToCart);
        return this;
    }





}
