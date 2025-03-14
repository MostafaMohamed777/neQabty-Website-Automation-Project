package Tests;

import Pages.*;
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

import static DriverFactory.DriverFactory.*;
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
        getDriver().get(getPropertyValue("Environments.properties", "REGISTER_URL_STAGING"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void ValidSwappingToVETSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
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
    public void ValidSwappingToTourGuideSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .tourGuidesSyndicates()
                .SyndicatesRegister()
               // .enterStaticMemberId("17855")
               // .enterStaticNationalId("28509032301971")
                .enterMemberId()
                .enterNationalId()
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("النقابة العامة للمرشدين السياحيين"));
    }
    @Test
    public void ValidSwappingToAgricoultureSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .agricultural()
                .SyndicatesRegister()
                .enterMemberId()
                 //enterStaticMemberId("900494")
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("نقابة المهن الزراعية"));
    }


    @Test
    public void ValidSwappingToGptsSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
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
    public void ValidSwappingToENGSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .engineer()
                .SyndicatesRegister()
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("نقابة المهندسين"));
    }
    @Test
    public void ValidSwappingToEDUSyndicate() throws IOException {
        new P02_RegistrationPage(getDriver())
                .registerWithRandomPhoneNumber()
                .registrationWithRandomFullName()
                .confirmRegisterData()
                .registerWithDynamicPinCode()
                .registerWithDynamicConfPinCode()
                .confirmFullRegistration()
                .navigateToHomePage()
                .enterSyndicatePage()
                .educational()
                .SyndicatesRegister()
                .confirmSwapping()
                .navigateToHomePage();
        Assert.assertTrue(new P04_HomePage(getDriver()).assertSyndicateName("نقابة المهن التعليمية"));
    }


    @AfterMethod
    public void quit() {
        //quitDriver();
    }
}
