package Tests;

import Pages.P03_LoginPage;
import Pages.P04_HomePage;
import Pages.P07_ProfilePage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class Tc07_ProfilePage {
    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhone");
    private final String phoneNumberSyndicate= DataUtils.getJasonData("ValidRegistrationData","MobilePhoneEng");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");

    public Tc07_ProfilePage() throws FileNotFoundException {
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
    public void ValidNavigateToProfilePage() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterProfilePage()
                .navigateToPackagePageInNeqStatus();
    }
    @Test
    public void ValidLogOut() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterProfilePage()
                .enterLogOut();
        Assert.assertTrue(new P03_LoginPage(getDriver()).
                assertHomePage(DataUtils.getPropertyValue("Environments.properties","LANDING_URL")));
    }
    @Test
    public void packagesAssertionsPageInNeqMember() {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterProfilePage()
                .navigateToPackagePageInNeqStatus();
        Assert.assertTrue(new P07_ProfilePage(getDriver()).assertPackage());
    }
    @Test
    public void packagesAssertionsPageInSyndicateMember() {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumberSyndicate)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterProfilePage()
                .navigateToPackagePageInSyndicateStatus();
        Assert.assertTrue(new P07_ProfilePage(getDriver()).assertPackage());
    }

    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}
