package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P08_syndicatePage {

    //Locator
    private final By tourGuides =By.xpath("//owl-stage/div/div/div[1]");
    private final By physicalTherapy =By.xpath("//owl-stage/div/div/div[2]");
    private final By agricultural =By.xpath("//owl-stage/div/div/div[3]");
    private final By educational =By.xpath("//owl-stage/div/div/div[4]");
    private final By veterinarians =By.xpath("//owl-stage/div/div/div[5]");
    private final By engineer =By.xpath("//owl-stage/div/div/div[6]");
    private final By swappingBtn=By.xpath("//div[2]/div[2]/div/button ");
    private final By syndicatesDynamicLocator=By.xpath("//div/div/a/div/h4");
    //Variable
    private final WebDriver driver;

    //constructor
    public P08_syndicatePage(WebDriver driver) {
        this.driver=driver;
    }

    //Action
    public P08_syndicatePage tourGuidesSyndicates()
    {
        Utility.clickElement(driver,tourGuides);
        return this;
    }
    public P08_syndicatePage PhysicalTherapy()
    {
        Utility.clickElement(driver,physicalTherapy);
        return this;
    }
    public P08_syndicatePage agricultural()
    {
        Utility.clickElement(driver,agricultural);
        return this;
    }
    public P08_syndicatePage educational()
    {
        Utility.clickElement(driver,educational);
        return this;
    }
    public P08_syndicatePage veterinarians()
    {
        Utility.scrollToElement(driver,veterinarians);
        Utility.clickElement(driver,veterinarians);
        return this;
    }
    public P08_syndicatePage engineer()
    {
        Utility.scrollToElement(driver,engineer);
        Utility.clickElement(driver,engineer);
        return this;
    }

    public P09_SwappingPage SyndicatesRegister()
    {
        Utility.clickElement(driver,swappingBtn);
        return new P09_SwappingPage(driver);
    }

    //Assertions
    public boolean verifyUrl(String expectedValue)
    {
        return   Utility.VerifyUrl(driver,expectedValue);
    }
    public boolean getSyndicateName() {
        List<WebElement> webElements = driver.findElements(syndicatesDynamicLocator);
        for (int i=1;i<= webElements.size();i++) {
            By element =By.xpath("//div/div[" + i + "]/a/div/h4");
            Utility.scrollToElement(driver,element);
            String syndicatesName = Utility.getText(driver,element);
            LogsUtils.info("Syndicate Name IS" + syndicatesName);
        }
        LogsUtils.info("Syndicate Number IS"+ webElements.size());
        return true;
    }
}
