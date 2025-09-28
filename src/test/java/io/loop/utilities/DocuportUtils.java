package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DocuportUtils {

    /**
     * logins to docuport application
     *
     * @param driver, which is initialized in the test base
     * @param role,   comes from docuport constants
     *                author nsh
     */
    public static void login(WebDriver driver, String role) throws InterruptedException {
        driver.get(ConfigurationReader.getProperties("docuportBETA"));
        WebElement username = driver.findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        switch (role.toLowerCase()) {
            case "client":
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD_CLIENT);
                break;
            case "supervisor":
                username.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_SUPERVISOR);
                break;
            case "advisor":
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD_ADVISOR);
                break;
            case "employee":
                username.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                password.sendKeys(DocuportConstants.PASSWORD_EMPLOYEE);
                break;
            default:
                throw new InterruptedException("There is not such a role: " + role);
        }

        loginButton.click();

        if (role.toLowerCase().equals("client")) {
            Thread.sleep(3000);
            WebElement cont = driver.findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(3000);
        }


    }

    public static void logOut(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DocuportConstants.SMALL));
        WebElement userIcon = driver.findElement(By.xpath("//div[@class='v-avatar primary']"));
        userIcon.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DocuportConstants.SMALL));
        WebElement logOut = driver.findElement(By.xpath("//span[contains(text(),'Log out')]"));
        logOut.click();


    }

    /**
     *
     * @param driver
     * @param emailAddress
     * @param field
     * @return author SB
     */

    public static String returnAnyFieldFromTable(WebDriver driver, String emailAddress, String field) throws InterruptedException {
        WebElement element = null;
        String xpath = "";

        switch (field.toLowerCase()) {
            case "full name":
                xpath = "//td[2][text()='" + emailAddress + "']/preceding-sibling::td//span[2]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "username":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[1]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "inviter":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[2]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "phone number":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[3]/span";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "role":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[4]/span/span";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "advisor":
                xpath = "//td[2][text()='" + emailAddress + "']/following-sibling::td[5]";
                element = driver.findElement(By.xpath(xpath));
                break;

            default:
                throw new InterruptedException("There is not such a field: " + field);
        }

        return element.getText().trim();
    }

    /**
     *
     * @param driver
     * @param fullName
     * @param field
     * @return author SB
     */


    public static String returnValueFromTableByName (WebDriver driver, String fullName, String field) throws InterruptedException {
        WebElement element = null;
        String xpath = "";


        switch (field.toLowerCase()) {
            case "name":
                xpath = "//td[2][text()='Alexandra Gray']";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "type":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[1]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "amount":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[2]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "date":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[3]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "street":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[4]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "city":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[5]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "state":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[6]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "zip":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[7]";
                element = driver.findElement(By.xpath(xpath));
                break;

            case "card":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[8]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "card number":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[9]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "expiration":
                xpath = "//td[2][text()='" + fullName + "']/following-sibling::td[10]";
                element = driver.findElement(By.xpath(xpath));
                break;

            default:
                throw new InterruptedException("There is not such a field: " + field);
        }
        return element.getText().trim();


        }




    }

