package Tests;

import Pages.P01_LandingPge;
import Pages.P03_LoginPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
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

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setUpBrowser;
import static Utilittes.DataUtils.getPropertyValue;

@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class Tc03_LoginPage {
    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhone");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");
    private final String confirmPinCode =DataUtils.getJasonData("ValidRegistrationData","ConfPinCode");


    public Tc03_LoginPage() throws FileNotFoundException {
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
        getDriver().get(getPropertyValue("Environments.properties", "LANDING_URL_STAGING"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void ValidLoginCycle() throws IOException {
        new P01_LandingPge(getDriver()).clickOnLoginBtn();
        new P03_LoginPage(getDriver())
                .enterDynamicPhoneNumber()
                .confirmLoginPhoneNumber()
                .enterDynamicPinCode()
                .confirmLoginBTn();
        Assert.assertTrue(new P03_LoginPage(getDriver()).
                assertHomePage(DataUtils.getPropertyValue("Environments.properties","HOME_URL_Staging")));
    }


    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}
