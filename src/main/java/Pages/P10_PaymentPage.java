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
    private final By TotalAmountInReset=By.xpath("//app-success-code/div/div[2]/div/div[2]//p/span[1]");
    private final By TotalAmountInSystem=By.xpath("//app-success-code/div//div/div[3]/div/div/p/span[1]\n");
    private final By TotalAmountForSystemWithDilevreyFees =By.xpath("//app-inquiry/div/div/div[3]/div/div/p/span[1]");
    private final By TotalAmountInOPayCard =By.xpath("//*[@id=\"app\"]/div//p/text()");
    private final By assertionKeyForGeidea=By.xpath("//*[@id=\"geideaModalForm\"]/div[1]/div/span");
    private final By assertionKeyForOpayCard=By.xpath("//*[@id=\"app\"]//p/span");
    private final By gedieaCardNumber=By.cssSelector("[name=\"number\"]");
    private final By opayCardNumber=By.cssSelector("[placeholder=\"Card Number\"]");
    private final By gedieaExpiryDay=By.cssSelector("[name=\"expiry\"]");
    private final By opayExpiryMonth=By.cssSelector("[placeholder=\"Expiry Month [MM]\"]");
    private final By opayExpiryYear=By.cssSelector("[placeholder=\"Expiry Year [YY]\"]");
    private final By gedieaCvv=By.cssSelector("[name=\"cvv\"]");
    private final By opayCvv=By.cssSelector("[placeholder=\"Card Security Code [CVV]\"]");
    private final By gedieaCardName=By.cssSelector("[name=\"owner\"]");
    private final By opayCardName=By.cssSelector("[placeholder=\"Card Holder Name\"]");
    private final By gedieaConfirmPayment=By.id("payButton");
    private final By opayConfirmPayment=By.xpath("//*[@id=\"app\"]/div//button[2]");


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
        Utility.generalWait(driver, assertionKey,10);
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
    public String getMassageForOPayCard()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("opaycheckout"));
        Utility.generalWait(driver,assertionKeyForOpayCard,5);
        String successfulMassage =Utility.getText(driver,assertionKeyForOpayCard);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }
    public String getTotalAmountForReset()
    {
        Utility.generalWait(driver, TotalAmountInReset,10);
        String successfulMassage =Utility.getText(driver,TotalAmountInReset);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }
    public String getTotalAmountForSystemWithDilevreyFees()
    {
        Utility.generalWait(driver, TotalAmountInSystem,10);
        String successfulMassage =Utility.getText(driver,TotalAmountInSystem);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }
    public String getTotalAmountForOpayCard()
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("opaycheckout"));
        Utility.generalWait(driver,TotalAmountInOPayCard,35);
        Utility.generalWait(driver, TotalAmountInOPayCard,10);
        String successfulMassage =Utility.getText(driver,TotalAmountInOPayCard);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }

    public String getTotalAmountInSystemWithDilevreyFees()
    {
        Utility.generalWait(driver,TotalAmountForSystemWithDilevreyFees,5);
        String successfulMassage =Utility.getText(driver,TotalAmountForSystemWithDilevreyFees);
        LogsUtils.info("Massage is :"+ successfulMassage);
        return successfulMassage;
    }


    public P10_PaymentPage enterGedieaCardNumber(String CardNumber)
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("geidea"));
        Utility.sendData(driver,gedieaCardNumber,CardNumber);
        return this;
    }
    public P10_PaymentPage enterGedieaExpiryDay(String expiryDay)
    {
        Utility.scrollToElement(driver,gedieaExpiryDay);
        Utility.sendData(driver,gedieaExpiryDay,expiryDay);
        return this;
    }
    public P10_PaymentPage enterGedieaCvv(String Cvv)
    {
        Utility.scrollToElement(driver,gedieaCvv);
        Utility.sendData(driver,gedieaCvv,Cvv);
        return this;
    }
    public P10_PaymentPage enterGedieaCardName()
    {
        Utility.scrollToElement(driver,gedieaCardName);
        Utility.sendData(driver,gedieaCardName,"Mostafa Moo");
        return this;
    }
    public P10_PaymentPage confirmGedieaPayment()
    {
        Utility.scrollToElement(driver,gedieaConfirmPayment);
        Utility.clickElement(driver,gedieaConfirmPayment);
        return this;
    }

    public P10_PaymentPage enterOpayCardNumber(String CardNumber)
    {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("opaycheckout"));
        Utility.generalWait(driver,opayCardNumber,35);
        Utility.scrollToElement(driver,opayCardNumber);
        Utility.sendData(driver,opayCardNumber,CardNumber);
        return this;
    }
    public P10_PaymentPage enterOpayExpiryMonth(String expiryMonth)
    {
        Utility.scrollToElement(driver,opayExpiryMonth);
        Utility.sendData(driver,opayExpiryMonth,expiryMonth);
        return this;
    }
    public P10_PaymentPage enterOpayExpiryYear(String expiryYear)
    {
        Utility.scrollToElement(driver,opayExpiryYear);
        Utility.sendData(driver,opayExpiryYear,expiryYear);
        return this;
    }
    public P10_PaymentPage enterOpayCvv(String Cvv)
    {
        Utility.scrollToElement(driver,opayCvv);
        Utility.sendData(driver,opayCvv,Cvv);
        return this;
    }
    public P10_PaymentPage enterOpayCardName()
    {
        Utility.scrollToElement(driver,opayCardName);
        Utility.sendData(driver,opayCardName,"Mostafa Moo");
        return this;
    }
    public P10_PaymentPage confirmOpayPayment()
    {
        Utility.scrollToElement(driver,opayConfirmPayment);
        Utility.clickElement(driver,opayConfirmPayment);
        return this;
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

    public boolean assertMassageForOpayCard()
    {
        return getMassageForOPayCard().equals("EGP");
    }
    public boolean assertTotalAmount()
    {
        return getTotalAmountForReset().equals(getTotalAmountForSystemWithDilevreyFees());
    }
    public boolean assertTotalAmountForOpayCard()
    {
        return getTotalAmountInSystemWithDilevreyFees().equals(getTotalAmountForOpayCard());
    }


}
