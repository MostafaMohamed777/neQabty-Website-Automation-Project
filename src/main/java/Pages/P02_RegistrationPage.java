package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class P02_RegistrationPage {
    private static List<WebElement> pinElements;
    private static List<WebElement> confirmPinElements;



    //Locators
    private final By phoneNumberFiled=By.id("mobileNumber");
    private final By fullName = By.id("full_name");
    private final By confirmBtn = By.cssSelector("button[type=\"submit\"]");
    private final By pinFields = By.cssSelector("[id^='password_field']"); // All elements with ID starting with "password_field"
    private final By confirmPinFields = By.cssSelector("[id^='confirm_password_field']"); // All elements with ID starting with "confirm_password_field
    private final By confirmRegistration =By.cssSelector("button[type=\"submit\"]");
    private final By homePageBtn=By.xpath("//button[text()=\"الصفحة الرئيسية\"]");
    private final By successfulRegister=By.tagName("h4");
    //Variables
    private final WebDriver driver;
    private String generatedPinCode;


    //constructor
    public P02_RegistrationPage(WebDriver driver) {
        this.driver=driver;
    }

    //action
    public  P02_RegistrationPage enterPhoneNumber(String phoneNumber){
        Utility.sendData(driver,phoneNumberFiled,phoneNumber);
        return this;
    }
    public P02_RegistrationPage registerWithUniqueRandomPhoneNumber() throws IOException {
        String phoneNumber = Utility.generateUniquePhoneNumber();
        Utility.saveData("phoneNumber",phoneNumber);
        LogsUtils.info("the generated phone number is ="+phoneNumber);
        enterPhoneNumber(phoneNumber);
        return this;
    }
    public P02_RegistrationPage registerWithRandomPhoneNumber() throws IOException {
        String phoneNumber =Utility.generatePhoneNumber();
        Utility.saveData("phoneNumber",phoneNumber);
        LogsUtils.info("the generated phone number is ="+phoneNumber);
        enterPhoneNumber(phoneNumber);
        return this;
    }
    public P02_RegistrationPage enterFullName(String fullname)
    {
        Utility.sendData(driver,fullName,fullname);
        return this;
    }
    public P02_RegistrationPage registrationWithRandomFullName()
    {
        String fullName = Utility.generateRandomFullName();
        enterFullName(fullName);
        return this;
    }
    public P02_RegistrationPage confirmRegisterData()
    {
        Utility.clickElement(driver,confirmBtn);
        return this;
    }


    public P02_RegistrationPage enterStaticPinCode(String PinCode)
    {
        pinElements =driver.findElements(pinFields); //1 2 3 4 5 6
        LogsUtils.info("Number of pin fields is"+ pinElements.size());
        Utility.fillPinField(pinElements,PinCode);
        return this;
    }
    public P02_RegistrationPage registerWithDynamicPinCode() throws IOException {
        generatedPinCode = Utility.generatePinCode(driver.findElements(pinFields));
        Utility.saveData("pinCode",generatedPinCode);
     return this;
    }

    public P02_RegistrationPage enterStaticConfirmPinCode(String confirmPinCode)
    {
        confirmPinElements =driver.findElements(confirmPinFields);
        LogsUtils.info("Number of confirm pin fields is"+ confirmPinElements.size());
        Utility.fillPinField(confirmPinElements,confirmPinCode);
        return this;
    }

    public P02_RegistrationPage registerWithDynamicConfPinCode()
    {
        confirmPinElements =driver.findElements(confirmPinFields);
        Utility.fillPinField(confirmPinElements,generatedPinCode);
        return this;
    }
    public P02_RegistrationPage confirmFullRegistration()
    {
        Utility.clickElement(driver,confirmRegistration);
        return this;
    }
    public P04_HomePage navigateToHomePage()
    {
        new WebDriverWait(driver,Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(homePageBtn));
        Utility.clickElement(driver,homePageBtn);
        return new P04_HomePage(driver);
    }
    public String getSuccessfulMassage()
    {
       String successfulMassage =Utility.getText(driver,successfulRegister);
        LogsUtils.info("Massage is :"+ successfulMassage);
    return successfulMassage;
    }
    //Assertion
    public boolean checkIfConfirmationButtonClickable(String pen,String conPen)
    {
        WebElement confirmRegistrationBtn =driver.findElement(confirmRegistration);
        boolean arePinsValid = enterStaticPinCode(pen).equals(enterStaticConfirmPinCode(conPen));
        return confirmRegistrationBtn.isEnabled() && arePinsValid;
    }
    public boolean checkIfConfirmationButtonClickableDynamic() throws IOException {
        WebElement confirmRegistrationBtn =driver.findElement(confirmRegistration);
        boolean arePinsValid = registerWithDynamicPinCode().equals(registerWithDynamicConfPinCode());
        return confirmRegistrationBtn.isEnabled() && arePinsValid;
    }
    public boolean assertSuccessfulMassage()
    {
        return  getSuccessfulMassage().equals("تم تسجيل الإشتراك بنجاح");
    }
    public boolean assertHomePage(String expectedValue)
    {
        return Utility.VerifyUrl(driver,expectedValue);
    }
}
