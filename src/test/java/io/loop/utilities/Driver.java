package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
    Creating a private contractor, so we are closing access to the object of this clas from outside the class
     */

    private Driver() {
    }
   /*
   Making our drive instance private, so it is not reachable from outside the class
   We make it static because we want it to run before everything else, and we will use it in a static method
    */

    private static WebDriver driver;

    /**
     * Singleton pattern
     * @return
     */

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigurationReader.getProperties("browser");
            ChromeOptions options = new ChromeOptions();
            switch (browserType.toLowerCase()) {
                case "chrome" -> {

                    options.addArguments("--disable-blink-features=AutomationControlled");
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> driver = new FirefoxDriver();
                case "safari" -> driver = new SafariDriver();

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        }
        return driver;
    }
    /**
     * Closing the driver
     * @SB
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

