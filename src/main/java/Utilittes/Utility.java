package Utilittes;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.SimpleFormatter;


public class Utility {

    private static String ScreenShoots_Path ="test-OutPut/ScreenShoots/";
    private static final HashSet<String> generatedNumbers =new HashSet<>();
    private static Random random =new Random();
    private static final Faker faker = new Faker();
    private static final String DATA_FILE = "testData.properties";


    // ======================= WebDriver Helper Methods ======================= //

    /**
     * Clicks on an element after ensuring it is clickable.
     */
    //ToDo:Click Element
    public static void clickElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    /**
     * Sends text to an element after ensuring it is visible.
     */
    //ToDo:Send Text
    public static void sendData(WebDriver driver,By locator,String text){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    /**
     * Retrieves the text of a visible element.
     */
    //ToDo:GetText
    public static String getText(WebDriver driver,By locator){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    /**
     * Scrolls to the specified element using JavaScript.
     */
    //Todo: Scrolling to Element
    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = findWebElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            Thread.sleep(500); // Small delay for animations/transitions
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void Scroll2(WebDriver driver, By locator)
    {
        WebElement element = findWebElement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    /**
     * Verifies the current URL matches the expected URL.
     */
    //ToDo: Verify Url
    public static boolean VerifyUrl(WebDriver driver, String expectedUrl) {
        try {
            GeneralWaite2(driver, 5).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Finds a WebElement using a locator.
     */
    //Todo: Converting By to webElement
    public static WebElement findWebElement(WebDriver driver,By locator){
        return driver.findElement(locator);
    }

    // ======================= Wait Methods ======================= //

    /**
     * Waits until an element is visible.
     */
    public static void generalWait(WebDriver driver,By locator,int timeOut){
        try {
            new WebDriverWait(driver,Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e)
        {
            System.out.println("Element not found within " + timeOut + " seconds: " + locator.toString());
        }

    }

    public static WebDriverWait GeneralWaite2(WebDriver driver,int timeout)
    {
        return new WebDriverWait(driver,Duration.ofSeconds(timeout));
    }

    // ======================= Dropdown Handling ======================= //

    /**
     * Selects an option from a dropdown by visible text.
     */
    //Todo: selecting from drop Down
    public static void selectingFromDropDown(WebDriver driver,By locator,String option){
        new Select(findWebElement(driver,locator)).deselectByVisibleText(option);
    }

    // ======================= Screenshot Utility ======================= //

    /**
     * Captures a screenshot, saves it to a file, and attaches it to an Allure report.
     */
    //Todo: Taking ScreenShoot
    public static void talkingScreenShoot(WebDriver driver,String screenShootName) throws IOException {
        try {
            //caption ScreenShoot using TakesScreenshot
            File screenSrc =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //saving screenShoot to file if needed
            File screenDest =new File(ScreenShoots_Path+screenShootName+"-"+getTimeStamp()+".png");
            FileUtils.copyFile(screenSrc,screenDest);
            //attach screenShoot to allure
            Allure.addAttachment(screenShootName, Files.newInputStream(Path.of(screenDest.getPath())));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    // ======================= Utility Methods ======================= //

    /**
     * Generates a timestamp for naming files or logs.
     */
    //ToDo: Get TimeStamp
    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ss-a").format(new Date());
    }


    // ======================= Cookie Handling ======================= //

    /**
     * Gets all cookies from the browser.
     */
    //Todo:Add Cookies
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    /**
     * Restores browser session by adding cookies.
     */
    //ToDo: Set Cookies
    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }


    // ======================= Random Data Generation ======================= //


    /**
     * Generates a unique Egyptian phone number.
     */

    //ToDo: generate Unique Egyptian PhoneNumber
    public static String generateUniquePhoneNumber()
    {
        String phoneNumber;
        do {
            phoneNumber = generatePhoneNumber();
        }while (!generatedNumbers.add(phoneNumber));
        return phoneNumber;

    }

    /**
     * Helper method to generate a phone number.
     */
    private static String generatePhoneNumber()
    {
        // Valid prefixes for Egyptian mobile numbers
        String[] prefixes = {"010", "011", "012", "015"};
        String prefix = prefixes[random.nextInt(prefixes.length)]; // Randomly pick a prefix

        // Generate the remaining 8 digits
        StringBuilder phoneNumber = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(random.nextInt(10)); // Append a random digit (0-9)
        }
       // storedPhoneNumber=phoneNumber.toString();
        return phoneNumber.toString();


    }

    /**
     * Generates a random full name using Faker.
     */
    //ToDo: generate random user name
    public static String generateRandomFullName()
    {
        String fullName = faker.name().fullName();
        return fullName;
    }
    /**
     * Generates a random name using Faker.
     */
    //ToDo: generate random  name
    public static String generateName()
    {
        String Name =faker.name().name();
        return Name;
    }
    /**
     * Generates a random email using Faker.
     */
    //ToDo: generate random  email
    public static String generateEmail()
    {
        String email=faker.internet().emailAddress();
        return email;
    }




    // ======================= PIN Code Handling ======================= //

    /**
     * Generates a dynamic PIN code, fills it in the provided fields, and returns the code.
     */
    //ToDo : generate dynamic PinCode
    public static String generatePinCode(List<WebElement> elements)
    {
        StringBuilder pinCode=new StringBuilder();
        for (WebElement element:elements)
        {
            int digit =random.nextInt(9)+1;  //digit from 1>>9 because refaat has a bug when picNode started with 0
            pinCode.append(digit);
            element.sendKeys(String.valueOf(digit));
        }
        LogsUtils.info("Generated PIN Code:"+pinCode);
        return pinCode.toString();
    }

    /**
     * Fills the given PIN code into the provided fields.
     */
    //ToDo: Fill Pin Code
    public static void fillPinField(List<WebElement> elements,String pinCode)
    {
        for (int i=0 ;i <elements.size();i++)
        {
            if (i<pinCode.length())
            {
                elements.get(i).sendKeys(String.valueOf(pinCode.charAt(i)));
            }else{
                LogsUtils.error("Code length is shorter than PIN fields!");
            }
        }
    }


    // ======================= Data Save and Retrieve ======================= //

    /**
     * saving data in properties File .
     */
    //ToDo : Save data to the properties file
    public static void saveData(String key,String value) throws FileNotFoundException {
        try (FileOutputStream output =new FileOutputStream("src/test/resources/testData.properties",true)){
            Properties properties=new Properties();
            properties.setProperty(key,value);
            properties.store(output,null);
            LogsUtils.info(key+"saved with value:"+value);
        } catch (IOException e) {
            LogsUtils.error("Field to save data :"+e.getMessage());
        }}
    //ToDo: Retrieve data from the properties file
    public static String getData(String key)
    {
        try (FileInputStream file =new FileInputStream("src/test/resources/testData.properties")) {
            Properties properties=new Properties();
            properties.load(file);
            String value=properties.getProperty(key);
            LogsUtils.info("Retrieved"+key+"with value"+value);
            return value;
        } catch (IOException e) {
           LogsUtils.error("Failed to retrieve data for key:"+key+ "Erorr"+e.getMessage());
           return null;
        }}
    /*
    private static String storedPhoneNumber;
    private static String storedPinCode;

    public static void setPhoneNumber(String number) {
        storedPhoneNumber = number;
        LogsUtils.info("storedPhoneNumber Is :"+storedPhoneNumber);
    }

    public static void setPinCode(String code) {
        storedPinCode = code;
        LogsUtils.info("stored Pin Code Is :"+storedPinCode);
    }

    public static String getPhoneNumber()
    {
        LogsUtils.info("Phone number retrieved:"+storedPhoneNumber);
        return storedPhoneNumber;
    }
    public static String getPinCode(){
        return storedPinCode;
    }


     */


    //destractor

}