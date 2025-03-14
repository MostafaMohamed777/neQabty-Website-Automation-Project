package Tests;

import Pages.P03_LoginPage;
import Pages.P10_PaymentPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import org.testng.Assert;
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
    private final String phoneNumber= DataUtils.getJasonData("ValidRegistrationData","MobilePhone3elag");
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
        getDriver().get(getPropertyValue("Environments.properties", "LOGIN_URL_STAGING"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void paymentWithOpayCode() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSubscribePage()
                .enterSubscribeBtn()
                .navigateToPaymentPage()
                .chooseOrderWay()
                .enterDeliveryAddress()
                .enterDeliveryMobile()
                .enterDeliveryNotes()
                .confirmData()
                .chooseOpayCode()
                .confirmData();
         Assert.assertTrue(new P10_PaymentPage(getDriver()).
                 assertMassageForOpayAndFawery(),"Successful Massage should displayed as expected ");
    }
    @Test
    public void paymentWithFawry() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSubscribePage()
                .enterSubscribeBtn()
                .navigateToPaymentPage()
                .chooseOrderWay()
                .enterDeliveryAddress()
                .enterDeliveryMobile()
                .enterDeliveryNotes()
                .confirmData()
                .chooseOpayCode()
                .confirmData();
        Assert.assertTrue(new P10_PaymentPage(getDriver()).
                assertMassageForOpayAndFawery(),"Successful Massage should displayed as expected ");
    }
    @Test
    public void paymentWithGediea() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSubscribePage()
                .enterSubscribeBtn()
                .navigateToPaymentPage()
                .chooseOrderWay()
                .enterDeliveryAddress()
                .enterDeliveryMobile()
                .enterDeliveryNotes()
                .confirmData()
                .chooseGediea()
                .confirmData();
        Assert.assertTrue(new P10_PaymentPage(getDriver()).
                assertMassageForGeidea(),"Successful Massage should displayed as expected ");
    }
    @Test
    public void paymentWithOpayCard() throws IOException {
        new P03_LoginPage(getDriver())
                .enterStaticPhoneNumber(phoneNumber)
                .confirmLoginPhoneNumber()
                .enterStaticPinCode(pinCode)
                .confirmLoginBTn()
                .enterSubscribePage()
                .enterSubscribeBtn()
                .navigateToPaymentPage()
                .chooseOrderWay()
                .enterDeliveryAddress()
                .enterDeliveryMobile()
                .enterDeliveryNotes()
                .confirmData()
                .chooseOpayCard()
                .confirmData();
        Assert.assertTrue(new P10_PaymentPage(getDriver()).
                assertMassageForOpayCard(),"Successful Massage should displayed as expected ");
    }

    @AfterMethod
    public void quit()
    {
        // quitDriver();
    }
}
