package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P04_HomePage {

    //Locators
    private final By packages=By.xpath("//app-seha-services/div/div/div[1]");
    private final By news =By.xpath("//h4[text()=\"الاخبار\"]");
    private final By course=By.xpath("//h4[text()=\"كورس تدريبات القاده\"]");
    private final By pharmacy= By.xpath("//app-home-services/div/div/div[1]");
    private final By syndicate =By.xpath("//p[text()=\"النقابات\"]");
    private final By payments =By.xpath("//p[text()=\"المدفوعات\"]");
    private final By services =By.xpath("//p[text()=\"الخدمات\"]");
    private final By notfications =By.xpath("//div[2]/ul/li[1]");
    private final By profile =By.xpath("//div[2]/ul/li[2]");
    private final By logout =By.xpath("//div[2]/ul/li[3]");

    //variables
    private final WebDriver driver;
    //constructor
    public P04_HomePage(WebDriver driver) {
        this.driver=driver;
    }

    //Action
    public P05_PackagesPage ScrollingAndNavigatePackagesPage()  {
       Utility.scrollToElement(driver,packages);
      Utility.clickElement(driver,packages);
        return new P05_PackagesPage(driver);
    }
    public P07_ProfilePage enterProfilePage()
    {
        Utility.clickElement(driver,profile);
        return new P07_ProfilePage(driver);
    }

    //Assertions
    public boolean assertPackagePage(String expectedValue)
    {
        return Utility.VerifyUrl(driver,expectedValue);
    }





}
