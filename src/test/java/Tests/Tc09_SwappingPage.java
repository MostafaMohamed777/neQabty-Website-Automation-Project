package Tests;

import Pages.P01_LandingPge;
import Pages.P03_LoginPage;
import Pages.P04_HomePage;
import Pages.P08_syndicatePage;
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
public class Tc09_SwappingPage {
    private final String phoneNumber = DataUtils.getJasonData("ValidRegistrationData", "MobilePhone");
    private final String phoneNumberSyndicate = DataUtils.getJasonData("ValidRegistrationData", "MobilePhoneEng");
    private final String pinCode = DataUtils.getJasonData("ValidRegistrationData", "PinCode");

    public Tc09_SwappingPage() throws FileNotFoundException {
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
    public void ValidSwappingToVETSyndicate() throws IOException {
        new P01_LandingPge(getDriver()).clickOnRegisterBtn()
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .veterinarians()
                .SyndicatesRegister()
                .enterMemberId()
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("نقابه المهن البيطرية"));
    }

    @Test
    public void ValidSwappingToGptsSyndicate() throws IOException {
        new P01_LandingPge(getDriver()).clickOnRegisterBtn()
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .PhysicalTherapy()
                .SyndicatesRegister()
                .enterNationalId()
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("نقابة العلاج الطبيعى"));
    }


    @Test
    public void assertThatAllSyndicateDisplayedAsExpected() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSyndicatePage();
        Assert.assertTrue(new P08_syndicatePage(getDriver()).getSyndicateName());
    }

    @Test
    public void ValidNavigateToSwappingSyndicatePage() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSyndicatePage()
                .tourGuidesSyndicates()
                .SyndicatesRegister();
        Assert.assertTrue(new P08_syndicatePage(getDriver()).
                verifyUrl(DataUtils.getPropertyValue("Environments.properties", "Swapping_URl")));
    }

    @AfterMethod
    public void quit() {
        // quitDriver();
    }
}
