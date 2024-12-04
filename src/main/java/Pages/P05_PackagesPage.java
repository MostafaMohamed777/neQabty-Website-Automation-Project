package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P05_PackagesPage {
    List<WebElement>elements;

    //locators
    private final By dynamicLocator=By.xpath("//app-seha-packages/div/div/div");
    private final By FirstPackage =By.xpath("//app-seha-packages/div/div/div[3]/a/div[1]");
    private final By secondPackage=By.xpath("//app-seha-packages/div/div/div[1]/a/div[1]");
    private final By thirdPackage=By.xpath("//app-seha-packages/div/div/div[2]/a/div[1]");
    //variables
    private final WebDriver driver;

    //constructors
    public P05_PackagesPage(WebDriver driver)
    {
        this.driver=driver;
    }
    //Action
    public P06_PackageSubscribePage enterFirstPackage()  {
        new WebDriverWait(driver,Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(FirstPackage));
        Utility.clickElement(driver,FirstPackage);
        return new P06_PackageSubscribePage(driver);
    }
    public P06_PackageSubscribePage enterSecondPackage()
    {
        new WebDriverWait(driver,Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(secondPackage));
        Utility.clickElement(driver,secondPackage);
        return new P06_PackageSubscribePage(driver);
    }
    public P06_PackageSubscribePage enterThirdPackage()
    {
        new WebDriverWait(driver,Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(thirdPackage));
        Utility.clickElement(driver,thirdPackage);
        return new P06_PackageSubscribePage(driver);
    }
    //Assertions
    public boolean assertPackageDisplayed()
    {
       WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        elements=driver.findElements(dynamicLocator);
        for (int i=0;i<elements.size();i++)
        {
            try {
                wait.until(ExpectedConditions.visibilityOf(elements.get(i)));
                LogsUtils.info("Element"+(i+1)+"Is Visible");
            }catch (Exception e)
            {
                LogsUtils.info("Element"+(i+1)+"Is Not Visible");
                return false;
            }
        }
        return true;
    }
    public boolean verifyUrl(String expectedValue)
    {
      return   Utility.VerifyUrl(driver,expectedValue);
    }

}
