package io.loop.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class SampleDocuportStepDefs {


    private static final Logger LOG = LogManager.getLogger();
    POM pages = new POM();


    @When("user inserts {string} to {string} field on {string} page")
    public void user_insert_to_field_on_page(String input, String field, String page) {

        switch (page.toLowerCase().trim()) {
            case "login" -> {
                    pages.getLoginPage().insertField(field, input);
            LOG.info(input + " - was successufully sent to - " + field);
            }
            case "received doc" -> {
                pages.getReceivedDocsPage().insertField(field, input);
                LOG.info(input + " - was successufully sent to - " + field);
            }
            default -> throw new IllegalArgumentException("No such a page: " + page);


        }


    }

    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) throws InterruptedException {

        switch (page.toLowerCase().trim()) {

            case "login" , "choose account" ->
                    pages.getLoginPage().clickButton(button);

            case "left navigate" -> pages.getLeftNavigatePage().clickButton(button);
            case "received doc" -> pages.getReceivedDocsPage().clickButton(button);
            case "my uploads" -> {
                pages.getMyUploadsPage().clickButton(button);
                LOG.info(button + " - was successfully clicked - ");
            }
            default -> throw new IllegalArgumentException("Not such a page: " + page);
        }

    }

    @Then("user uploads a document")
    public void user_uploads_a_document() throws AWTException {
//        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
//        element.sendKeys("C:\\Users\\Shelby\\Desktop\\text.txt.txt");

        BrowserUtils.uploadFileForWindows("\"C:\\Users\\Shelby\\.bash_history\"");






    }

}
