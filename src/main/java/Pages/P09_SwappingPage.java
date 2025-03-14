package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P09_SwappingPage {

    //locators
    private final By membership_id =By.id("membership_id");
    private final By national_id =By.id("national_id");
    private final By confirmBtn =By.cssSelector("button[type=\"submit\"]");
    private final By homePageBtn=By.xpath("//button[text()=\"الصفحة الرئيسية\"]");

    //Variables
    private final WebDriver driver;

    //contracture
    public P09_SwappingPage(WebDriver driver) {
        this.driver= driver;
    }

    //Action
    public P09_SwappingPage enterNationalId()
    {
       String nationalId = Utility.nationalIdForFirstPackage();
       Utility.sendData(driver,national_id,nationalId);
       return this;
    }
    public P09_SwappingPage enterMemberId()
    {
        String memberId=Utility.generateMemberId();
        Utility.sendData(driver,membership_id,memberId);
        return this;
    }
    public P09_SwappingPage confirmSwapping()
    {
        Utility.clickElement(driver,confirmBtn);
        return this;
    }
    public P04_HomePage navigateToHomePage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(homePageBtn));
        Utility.clickElement(driver,homePageBtn);
        return new P04_HomePage(driver);
    }
    public P09_SwappingPage enterStaticMemberId (String memberIdd)
    {
        Utility.sendData(driver,membership_id,memberIdd);
        return this;
    }
    public P09_SwappingPage enterStaticNationalId(String NationalId)
    {
        Utility.sendData(driver,national_id,NationalId);
        return this;
    }
    //Assertions

}
