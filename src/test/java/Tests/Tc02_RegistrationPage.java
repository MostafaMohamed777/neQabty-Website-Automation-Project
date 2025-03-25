package Tests;

import Pages.P01_LandingPge;
import Pages.P02_RegistrationPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import Utilittes.Utility;
import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import org.testng.annotations.Test;

import static DriverFactory.DriverFactory.*;
import static Utilittes.DataUtils.getPropertyValue;


@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class Tc02_RegistrationPage {

    private final String phoneNumber=DataUtils.getJasonData("ValidRegistrationData","MobilePhone");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");
    private final String confirmPinCode =DataUtils.getJasonData("ValidRegistrationData","ConfPinCode");

    public Tc02_RegistrationPage() throws FileNotFoundException {
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
        getDriver().get(getPropertyValue("Environments.properties", "REGISTER_URL_STAGING"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void RegisterButtonTC() throws IOException {
        new P01_LandingPge(getDriver())
                .clickOnRegisterBtn();
        Assert.assertTrue(new P01_LandingPge(getDriver())
                .assertRegisterPage(getPropertyValue("Environments.properties","HOME_URL")));
    }
@Test
public void ValidStaticRegistrationCycleTc()
{
    new P02_RegistrationPage(getDriver())
            .enterPhoneNumber(phoneNumber)
            .registrationWithRandomFullName()
            .confirmRegisterData()
            .enterStaticPinCode(pinCode)
            .enterStaticConfirmPinCode(confirmPinCode);
    Assert.assertTrue(new P02_RegistrationPage(getDriver()).
            checkIfConfirmationButtonClickable(pinCode,confirmPinCode));
}
@Test
public void ValidDynamicRegistration() throws IOException {

       new P02_RegistrationPage(getDriver())
             .registerWithUniqueRandomPhoneNumber()
            .registrationWithRandomFullName()
            .confirmRegisterData()
            .registerWithDynamicPinCode()
            .registerWithDynamicConfPinCode()
   // Assert.assertTrue(new P02_RegistrationPage(getDriver()).
            //  checkIfConfirmationButtonClickableDynamic(),"The confirmation button should be clickable with matching dynamic pins!");
            .confirmFullRegistration()
    //Assert.assertTrue(new P02_RegistrationPage(getDriver()).
       //     assertSuccessfulMassage(),"Successful Massage should displayed as expected ");
   .navigateToHomePage();

    Assert.assertTrue(new P02_RegistrationPage(getDriver()).
            assertHomePage(getPropertyValue("Environments.properties","HOME_URL_Staging")));
}

    @AfterMethod
    public void quit()
    {
       // quitDriver();
    }
}
