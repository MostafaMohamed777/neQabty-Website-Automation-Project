package Tests;

import Pages.P01_LandingPge;
import Utilittes.LogsUtils;
import org.testng.Assert;
import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilittes.DataUtils.getPropertyValue;

@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class Tc01_LandingPage {

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
        getDriver().get(getPropertyValue("Environments.properties", "LANDING_URL"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void LoginBtnTc() throws IOException {
        new P01_LandingPge(getDriver())
                .clickOnLoginBtn();
        Assert.assertTrue(new P01_LandingPge(getDriver()).
                assertLoginPage(getPropertyValue("Environments.properties", "LOGIN_URL")));
    }
    @Test
    public void RegisterBtnTC() throws IOException {
        new P01_LandingPge(getDriver())
                .clickOnRegisterBtn();
        Assert.assertTrue(new P01_LandingPge(getDriver())
                .assertRegisterPage(getPropertyValue("Environments.properties","REGISTER_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
