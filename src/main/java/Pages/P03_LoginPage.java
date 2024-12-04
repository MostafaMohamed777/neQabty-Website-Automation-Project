package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_LoginPage {
   private static List<WebElement> pinElements;

    //Locators
    private final By pinFields = By.cssSelector("[id^='password_field']");
    private final By confirmBtn = By.cssSelector("button[type=\"submit\"]");
    private final By forgetPass = By.cssSelector("a[routerlink=\"/auth/forget-password\"]");
    private final By phoneNumberFiled=By.id("mobileNumber");

    //constructor
    public P03_LoginPage(WebDriver driver) {
        this.driver=driver;
    }

    //variables
    private final WebDriver driver;

    //Action

    public P03_LoginPage enterPinCode()
   {
    String pinCode  =Utility.getData("pinCode");
       if (pinCode == null || pinCode.isEmpty()) {
           LogsUtils.error("Pin Code is null or empty!");
       }
    pinElements =driver.findElements(pinFields);
    Utility.fillPinField(pinElements,pinCode);
    return this;
   }
    public  P03_LoginPage enterPhoneNumber(){
        String phoneNumber = Utility.getData("phoneNumber");
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            LogsUtils.error("Phone number is null or empty!");
        }
        System.out.println("Phone Number Retrieved for Login: " + phoneNumber);
        Utility.sendData(driver,phoneNumberFiled,phoneNumber);
        return this;
    }
    public P03_LoginPage confirmLoginPhoneNumber()
    {
        Utility.clickElement(driver,confirmBtn);
        return this;
    }
   public P04_HomePage confirmLoginBTn()
   {
    Utility.clickElement(driver,confirmBtn);
    return new P04_HomePage(driver);
   }
    //assertions
    public boolean assertHomePage(String expectedValue)
    {
        return Utility.VerifyUrl(driver,expectedValue);
    }


}
