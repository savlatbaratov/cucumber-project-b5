package io.loop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy (xpath = "//input[@type='text']")
    public WebElement userNameInput;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement loginButton;

}
