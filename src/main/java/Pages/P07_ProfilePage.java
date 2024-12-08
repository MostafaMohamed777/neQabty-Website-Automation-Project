package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P07_ProfilePage {
    List<WebElement> webElements;

    //Variables
    private final WebDriver driver;

    //Constructors
    public P07_ProfilePage(WebDriver driver) {
        this.driver =driver;
    }

    //Locators
        private final By dynamicLocator =By.xpath("//app-profile-seha-packages/div/div/div");
        private final By personalInfo=By.xpath("//app-profile-wrapper//div[2]/div//a[1]");
        private final By changePassword=By.xpath("//app-profile-wrapper//div[2]/div//a[2]");
        private final By swapping=By.xpath("//app-profile-wrapper//div[2]/div//a[3]");
        private final By packages =By.xpath("//app-profile-wrapper//div[2]/div//a[3]");
        private final By bills =By.xpath("//app-profile-wrapper//div[2]/div//a[5]");
        private final By Docs =By.xpath("//app-profile-wrapper//div[2]/div//a[6]");
        private final By complains =By.xpath("//app-profile-wrapper//div[2]/div//a[7]");
        private final By support =By.xpath("//app-profile-wrapper//div[2]/div//a[8]");
        private final By deleteAcc =By.xpath("//app-profile-wrapper//div[2]/div//a[9]");
        private final By logout =By.xpath("//app-profile-wrapper//div[2]/div//a[10]");


    //Action
    public P07_ProfilePage navigateToPackagePage()
    {
        Utility.clickElement(driver,packages);
        return this;
    }
    public P03_LoginPage enterLogOut()
    {
        Utility.scrollToElement(driver,logout);
        Utility.clickElement(driver,logout);
        return new P03_LoginPage(driver);
    }

    //Assertions
    public boolean assertPackage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(dynamicLocator));
        webElements =driver.findElements(dynamicLocator);
            try {
                if (!webElements.isEmpty()) {
                    LogsUtils.info("This Member has a package. Total packages found: "+(webElements.size()));
                    return true;
                } else {
                    LogsUtils.info("This Member dont have a package");
                    return false;
                }
            } catch (NoSuchElementException e) {
                LogsUtils.info("No subscription elements found on the page.");
                return false;
            }
    }
}