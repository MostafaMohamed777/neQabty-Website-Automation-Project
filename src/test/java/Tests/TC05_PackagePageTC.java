package Tests;

import Pages.P03_LoginPage;
import Pages.P05_PackagesPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
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
public class TC05_PackagePageTC {

    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhone");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData","PinCode");


    public TC05_PackagePageTC() throws FileNotFoundException {
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
    public void ValidVisibleOfPackage()  {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .ScrollingAndNavigatePackagesPage()
                .assertPackageDisplayed();
        Assert.assertTrue(new P05_PackagesPage(getDriver()).assertPackageDisplayed());
    }
    @Test
    public void ValidNavigateToPackagePage() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .ScrollingAndNavigatePackagesPage()
                .enterSecondPackage();
        Assert.assertTrue(new P05_PackagesPage(getDriver()).verifyUrl(DataUtils.getPropertyValue("Environments.properties","PACKAGES_PAGE")));
    }

    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}
