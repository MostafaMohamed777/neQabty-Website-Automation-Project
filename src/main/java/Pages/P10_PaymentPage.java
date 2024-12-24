package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P10_PaymentPage {
    
    //locators
    private final By opayCode=By.xpath("//form/div/div[1]//input");
    private final By opayCard=By.xpath("//form/div/div[2]//input");
    private final By neqabtyWallet=By.xpath("//form/div/div[3]//input");
    private final By fawrey=By.xpath("//form/div/div[4]//input");
    private final By cridet=By.xpath("//form/div/div[5]//input");

    //variables
    private final WebDriver driver;

    //constructors
    public P10_PaymentPage(WebDriver driver) {
        this.driver=driver;
    }

    //Action
    public P10_PaymentPage chooseOpayCode()
    {
        Utility.generalWait(driver,opayCode,10);
        driver.findElement(opayCode).click();
        return this;
    }
    public P10_PaymentPage chooseOpayCard()
    {
        Utility.generalWait(driver,opayCard,10);
        driver.findElement(opayCard).click();
        return this;
    }
    public P10_PaymentPage chooseFawry()
    {
        Utility.generalWait(driver,fawrey,10);
        driver.findElement(fawrey).click();
        return this;
    }
    public P10_PaymentPage chooseWallet()
    {
        Utility.generalWait(driver,neqabtyWallet,10);
        driver.findElement(neqabtyWallet).click();
        return this;
    }
    public P10_PaymentPage chooseCridet()
    {
        Utility.generalWait(driver,cridet,10);
        driver.findElement(cridet).click();
        return this;
    }

    //Assertions

}
