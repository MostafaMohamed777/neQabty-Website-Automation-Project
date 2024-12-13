package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_PackageSubscribePage {
    private String name =Utility.generateName();
    private String email =Utility.generateEmail();

    //Locators
    private final By firstName =By.id("firstName");
    private final By secondName =By.id("secondName");
    private final By thirdName =By.id("thirdName");
    private final By lastName =By.id("lastName");
    private final By userEmail =By.id("userEmail");
    private final By userJob =By.id("userJob");
    private final By birthDateTable =By.cssSelector("p-calendar[placeholder=\"تاريخ الميلاد\"]");
    private final By birthDayValue =By.xpath("//tr[1]/td[3]/span");
    private final By confirmSubscribe =By.cssSelector("button[type=\"submit\"]");
    private final By nationalId =By.id("nationalId");
    private final By subscribePhoneNumber=By.id("phoneNumber");
    private final By promoMobileNumber =By.id("promoMobileNumber");
    private final By subscribeAddress=By.id("address");
    private final By checkBox =By.id("agreeTerms");
    private final By confirmBtn=By.cssSelector("button[type=\"button\"]");
    private final By successfulMassage=By.xpath("//h4[text()=\"تم تسجيل الإشتراك بنجاح\"]");


    //Variables
    private final WebDriver driver;

    //constructor
    public P06_PackageSubscribePage(WebDriver driver) {
        this.driver=driver;
    }

    //Actions
    public P06_PackageSubscribePage enterFirstName()
    {
        Utility.sendData(driver,firstName,name);
        return this;
    }
    public P06_PackageSubscribePage enterSecondName()
    {
        Utility.sendData(driver,secondName,name);
        return this;
    }
    public P06_PackageSubscribePage enterThirdName()
    {
        Utility.sendData(driver,thirdName,name);
        return this;
    }
    public P06_PackageSubscribePage enterLastName()
    {
        Utility.sendData(driver,lastName,name);
        return this;
    }
    public P06_PackageSubscribePage enterEmail()
    {
        Utility.sendData(driver,userEmail,email);
        return this;
    }
    public P06_PackageSubscribePage enterBirthDate()
    {
        Utility.clickElement(driver, birthDateTable);
        Utility.clickElement(driver,birthDayValue);
        return this;
    }
    public P06_PackageSubscribePage enterUserJob()
    {
        Utility.sendData(driver,userJob,name);
        return this;
    }
    public P06_PackageSubscribePage dynamicConfirm()
    {
        Utility.scrollToElement(driver,confirmSubscribe);
        Utility.clickElement(driver,confirmSubscribe);
        return this;
    }
    public P06_PackageSubscribePage enterNationalIdForFirstPackage()
    {
        String nationalIDForFirstPackage= Utility.nationalIdForFirstPackage();
        Utility.sendData(driver,nationalId,nationalIDForFirstPackage);
        return this;
    }
    public P06_PackageSubscribePage enterNationalIdForSecondPackage()
    {
        String nationalIDForSecondPackage= Utility.nationalIdForSecoundPackage();
        Utility.sendData(driver,nationalId,nationalIDForSecondPackage);
        return this;
    }
    public P06_PackageSubscribePage enterNationalIdForThirdPackage()
    {
        String nationalIDForThirdPackage= Utility.nationalIdForThirdPackage();
        Utility.sendData(driver,nationalId,nationalIDForThirdPackage);
        return this;
    }
    public P06_PackageSubscribePage enterSubscribePhoneNumber()
    {
        String PhoneNumber= Utility.generatePhoneNumber();
        Utility.sendData(driver,subscribePhoneNumber,PhoneNumber);
        return this;
    }
    public P06_PackageSubscribePage enterPromoMobileNumber()
    {
        String PhoneNumber= Utility.generatePhoneNumber();
        Utility.sendData(driver,promoMobileNumber,PhoneNumber);
        return this;
    }
    public P06_PackageSubscribePage enterAddress()
    {
        String address= Utility.generateAddress();
        Utility.sendData(driver,subscribeAddress,address);
        return this;
    }
    public P06_PackageSubscribePage confirmTermsAndCondition()
    {
        Utility.scrollToElement(driver,checkBox);
        Utility.clickElement(driver,checkBox);
        return this;
    }
    public P06_PackageSubscribePage confirmAllRegistration()
    {
        Utility.scrollToElement(driver,confirmSubscribe);
        Utility.clickElement(driver,confirmSubscribe);
        return this;
    }
    public P06_PackageSubscribePage navigateToPaymentPage()
    {
        Utility.scrollToElement(driver,confirmBtn);
        Utility.clickElement(driver,confirmBtn);
        return this;
    }

    //Assertions
    public boolean assertSuccessfulPackageSubscribe()
    {
       String successfulMass = Utility.getText(driver,successfulMassage);
       return successfulMass.equals("تم تسجيل الإشتراك بنجاح");
    }

}
