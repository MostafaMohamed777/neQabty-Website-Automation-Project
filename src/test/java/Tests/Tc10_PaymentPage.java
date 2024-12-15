package Tests;

import Pages.P01_LandingPge;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setUpBrowser;
import static Utilittes.DataUtils.getPropertyValue;

public class Tc10_PaymentPage {
    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhoneVET");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");


    public Tc10_PaymentPage() throws FileNotFoundException {
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
    public void ValidChoosePaymentMethod() throws IOException {
        new P01_LandingPge(getDriver())
                .clickOnLoginBtn()
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSubscribePage()
                .enterSubscribeBtn()
                .navigateToPaymentPage()
                .chooseOpayCode();
    }

    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}
