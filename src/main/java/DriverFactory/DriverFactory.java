package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver>driverThreadLocal=new ThreadLocal<>();

    public static void setUpBrowser(String browser){
        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(options));
                break;
            case "edge":
                driverThreadLocal.set(new EdgeDriver());
                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browser);
        }
    }
    public static WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }
    public static void quitDriver()
    {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
