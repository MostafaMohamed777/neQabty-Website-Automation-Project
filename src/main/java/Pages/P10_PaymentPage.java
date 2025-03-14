package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P10_PaymentPage {
    
    //locators
    private final By opayCode=By.xpath("//form/div/div[1]//input");
    private final By opayCard=By.xpath("//form/div/div[2]//input");
    private final By neqabtyWallet=By.xpath("//form/div/div[3]//input");
    private final By fawrey=By.xpath("//form/div/div[4]//input");
    private final By gediea =By.xpath("//form/div/div[5]//input");
    private final By OrderWay=By.xpath("//app-delivery-method/div//div//input");
    private final By deliveryAddress=By.id("deliveryAddress");
    private final By deliveryMobile=By.id("deliveryMobile");
    private final By deliveryNotes=By.id("deliveryNotes");
    private final By ConfBtn=By.cssSelector("button[type=\"submit\"]");
    private final By assertionKey=By.xpath("//div/div[2]/div[1]/div[1]/h6");
    private final By assertionKeyForGeidea=By.xpath("//*[@id=\"geideaModalForm\"]/div[1]/div/span");
    private final By assertionKeyForOpayCard=By.xpath("//*[@id=\"app\"]//p/span");


    //variables
    private final WebDriver driver;

    //constructors
    public P10_PaymentPage(WebDriver driver) {
        this.driver=driver;
    }

    //Action
    public P10_PaymentPage chooseOpayCode()
    {
        Utility.generalWait(driver,opayCode,5);
        driver.findElement(opayCode).click();
        return this;
    }
    public P10_PaymentPage chooseOpayCard()
    {
        Utility.generalWait(driver,opayCard,5);
        driver.findElement(opayCard).click();
        return this;
    }
    public P10_PaymentPage chooseFawry()
    {
        Utility.generalWait(driver,fawrey,5);
        driver.findElement(fawrey).click();
        return this;
    }
    public P10_PaymentPage chooseWallet()
    {
        Utility.generalWait(driver,neqabtyWallet,5);
        driver.findElement(neqabtyWallet).click();
        return this;
    }
    public P10_PaymentPage chooseGediea()
    {
        Utility.generalWait(driver, gediea,5);
        driver.findElement(gediea).click();
        return this;
    }
    public P10_PaymentPage chooseOrderWay()
    {
        driver.findElement(OrderWay).click();
        return this;
    }
    public P10_PaymentPage enterDeliveryAddress()
    {
       Utility.scrollToElement(driver,deliveryAddress);
       Utility.sendData(driver,deliveryAddress,"address");
        return this;
    }
    public P10_PaymentPage enterDeliveryMobile()
    {
        Utility.scrollToElement(driver,deliveryMobile);
        Utility.sendData(driver,deliveryMobile,"01094184627");
        return this;
    }
    public P10_PaymentPage enterDeliveryNotes()
    {
        Utility.scrollToElement(driver,deliveryNotes);
        Utility.sendData(driver,deliveryNotes,"Moo Moo");
        return this;
    }
    public P10_PaymentPage confirmData()
    {
        Utility.scrollToElement(driver,ConfBtn);
        Utility.clickElement(driver,ConfBtn);
        return this;
    }
    public String getMassageForOpayAndFawery()
    {
        Utility.generalWait(driver, gediea,10);
        String successfulMassage =Utility.getText(driver,assertionKey);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }
    public String getMassageForGeidea()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("geidea"));
        Utility.generalWait(driver,assertionKeyForGeidea,5);
        String successfulMassage =Utility.getText(driver,assertionKeyForGeidea);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }

    //Assertions
    public boolean assertMassageForOpayAndFawery()
    {
        return  getMassageForOpayAndFawery().equals("الفاتورة الكلية");
    }
    public boolean assertMassageForGeidea()
    {
        return  getMassageForGeidea().equals("NEQABTY");
    }
    public String getMassageForOPayCard()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("opaycheckout"));
        Utility.generalWait(driver,assertionKeyForOpayCard,5);
        String successfulMassage =Utility.getText(driver,assertionKeyForOpayCard);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }
    public boolean assertMassageForOpayCard()
    {
        return getMassageForOPayCard().equals("EGP");
    }

}
