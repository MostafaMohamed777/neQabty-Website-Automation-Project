package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LandingPge {

    //Locators
    private final By loginBtn =By.xpath("//*[text()=\"تسجيل الدخول\"]");
    private final By registerBtn =By.xpath("//div/div[4]//div//a");

    //Variables
    private WebDriver driver;

    //constructor
    public P01_LandingPge(WebDriver driver) {
        this.driver=driver;
    }

    //action
    public P03_LoginPage clickOnLoginBtn()
    {
        Utility.clickElement(driver,loginBtn);
        return new P03_LoginPage(driver);
    }
    public P02_RegistrationPage clickOnRegisterBtn()
    {
        Utility.clickElement(driver,registerBtn);
        return new P02_RegistrationPage(driver);
    }
    //Assertion
    public boolean assertLoginPage(String expectedValue)
    {
        return   Utility.VerifyUrl(driver,expectedValue);
    }
    public boolean assertRegisterPage(String expectedValue)
    {
        return Utility.VerifyUrl(driver,expectedValue);
    }


}
