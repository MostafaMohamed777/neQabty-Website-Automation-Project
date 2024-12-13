package Tests;

import Pages.P03_LoginPage;
import Pages.P06_PackageSubscribePage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setUpBrowser;
import static Utilittes.DataUtils.getPropertyValue;

@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class Tc06_PackageSubscribe {
    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhone");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");



    public Tc06_PackageSubscribe() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setUp() throws IOException {

        //we create here this new if condition to mack sure that we can run anything using MVN command
        //the If condition is = condition ? true : false

        String browser = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : getPropertyValue("Environments.properties", "BROWSER");
        LogsUtils.info(System.getProperty("browser"));
        setUpBrowser(browser);
        LogsUtils.info(browser + "driver is opened");
        getDriver().get(getPropertyValue("Environments.properties", "LOGIN_URL"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }


    @Test
    public void ValidFirstPackageSubscribe() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .ScrollingAndNavigatePackagesPage()
                .enterFirstPackage()
                .enterFirstName()
                .enterSecondName()
                .enterThirdName()
                .enterLastName()
                .enterEmail()
                .enterBirthDate()
                .enterUserJob()
                .dynamicConfirm()
                .enterNationalIdForFirstPackage()
                .dynamicConfirm()
                .enterSubscribePhoneNumber()
                .enterPromoMobileNumber()
                .enterAddress()
                .confirmTermsAndCondition()
                .confirmAllRegistration()
                .navigateToPaymentPage();
      //  Assert.assertTrue(new P06_PackageSubscribePage(getDriver()).assertSuccessfulPackageSubscribe());
        ;
    }
    @Test
    public void ValidSecondPackageSubscribe() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .ScrollingAndNavigatePackagesPage()
                .enterSecondPackage()
                .enterFirstName()
                .enterSecondName()
                .enterThirdName()
                .enterLastName()
                .enterEmail()
                .enterBirthDate()
                .enterUserJob()
                .dynamicConfirm()
                .enterNationalIdForSecondPackage()
                .dynamicConfirm()
                .enterSubscribePhoneNumber()
                .enterPromoMobileNumber()
                .enterAddress()
                .confirmTermsAndCondition()
                .navigateToPaymentPage();
       // Assert.assertTrue(new P06_PackageSubscribePage(getDriver()).assertSuccessfulPackageSubscribe());
        ;
    }

    @Test
    public void ValidThirdPackageSubscribe() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .ScrollingAndNavigatePackagesPage()
                .enterThirdPackage()
                .enterFirstName()
                .enterSecondName()
                .enterThirdName()
                .enterLastName()
                .enterEmail()
                .enterBirthDate()
                .enterUserJob()
                .dynamicConfirm()
                .enterNationalIdForThirdPackage()
                .dynamicConfirm()
                .enterSubscribePhoneNumber()
                .enterPromoMobileNumber()
                .enterAddress()
                .confirmTermsAndCondition()
                .navigateToPaymentPage();
       // Assert.assertTrue(new P06_PackageSubscribePage(getDriver()).assertSuccessfulPackageSubscribe());
    }


    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}

