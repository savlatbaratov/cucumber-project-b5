package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class BrowserUtils {

    public static Scenario myScenario;


    /**
     * takes screenshot
     * @author BS
     */


    public static void takeScreenshot(){
        try{
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        } catch (WebDriverException | ClassCastException wbd){
            wbd.getMessage();
        }
    }

    /**
     * validate if the driver switched to the expected url or title
     *
     * @param driver
     * @param expectedUrl
     * @param expectedTitle
     * @author SB
     * implements assertion
     */


    public static void switchWindowandvalidate(WebDriver driver, String expectedUrl, String expectedTitle) {
        // to lowercase the params in order to avoid miss type

        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedUrl)) {
                break;

            }
        }
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));


    }

    /**
     * @param driver
     * @param targetTitle author SB
     */

    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(targetTitle)) {
                return;


            }
        }
        driver.switchTo().window(origin);
    }

    /**
     * clicks any link from loop practice
     *
     * @param nameOfPage
     * @author nsh
     */
    public static void loopLinkClick(String nameOfPage) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage + "']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    /**
     * waits for the provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return element
     * @author SB
     */
    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForClickable2(WebElement element, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException se){
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    /**
     * waits for provided element to be invisible on the page
     *
     * @param element
     * @param timeout
     * @author nsh
     */
    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for provided element to be visible on the page
     *
     * @param element
     * @param timeaout
     * @author nsh
     */
    public static WebElement waitForVisibility(WebElement element, int timeaout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeaout));
        return wait.until(ExpectedConditions.visibilityOf(element));



    }
    public static void uploadFileForWindows(String filePath) throws AWTException {
        // copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // simulate keyboard paste and enter
        Robot robot = new Robot();
        robot.delay(1000);

        // press CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // press enter button
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);



    }

    public static void  uploadFileForMac(String filePath) throws AWTException {
        Robot robot = new Robot();

        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.delay(1000);

        // press ⌘ + Shift + G to open go to finder
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // Paste file path (⌘ + V)
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        // Press Enter again to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
    public static void uploadFileUsingAppleScript(String filePath) throws Exception {
        String script = "tell application \"System Events\"\n"
                + "delay 1\n"
                + "keystroke \"G\" using {command down, shift down}\n"
                + "delay 1\n"
                + "keystroke \"" + filePath + "\"\n"
                + "keystroke return\n"
                + "delay 1\n"
                + "keystroke return\n"
                + "end tell";

        String[] command = { "osascript", "-e", script };
        Runtime.getRuntime().exec(command);
    }

    /**
     * Moves the mouse to given element
     * @param element to hover over
     * @author SB
     *
     */

    public static void hover (WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();

    }
    /**
     * Scrolls down to an element using Javascript
     * @param element
     * @author SB
     */

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

    }
    /**
     * click on element using Javascript
     * @param element
     * @author SB
     */

    public static void clickWithJS(WebElement element){
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException se) {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * performs double click action
     * @param element
     * @author SB
     */

    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick().perform();

    }


    /**
     * performs a pause
     * @param milliSeconds
     * @author SB
     */

    public static void justWait(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException i) {
            i.printStackTrace();

        }
    }

    public static List<String> getElementText(List<WebElement> elements) {
        List<String> elementText = new ArrayList<>();
        for (WebElement element : elements) {
            elementText.add(element.getText());

        }
        return elementText;
    }

    public static List<String> getElementTextWithString(List<WebElement> elements) {
        return elements.stream()
                .map(x->x.getText())
                .collect(Collectors.toList());
    }
    public static List<String> getElementTextWithString2(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}