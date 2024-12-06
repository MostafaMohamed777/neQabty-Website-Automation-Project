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
    private final By confirmNationalId =By.cssSelector("button[type=\"submit\"]");



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
    public P06_PackageSubscribePage confirmSubscribe()
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


    //Assertions

}
