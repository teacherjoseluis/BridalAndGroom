package com.sibme.pages;

import com.sibme.CoreUtilties.BasePage;
import com.sibme.init.DriverFactory;
import com.sibme.testUtilities.AssertionUtilities;
import com.sibme.testUtilities.FileOperations;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {
    AssertionUtilities assertionUtilities = new AssertionUtilities();

    FileOperations fileOperations = new FileOperations();

    DriverFactory driverFactory = new DriverFactory();
    AppiumDriver driver;


    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'LOGIN')]")
    public WebElement login_button;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    public WebElement email_field;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text, 'Password')]")
    public WebElement password_field;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
    public WebElement home_button;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
    public WebElement allow_button;


    public LoginPage() {
        driver = driverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void loginIntoApp(String userType) throws InterruptedException {
        String username = fileOperations.getDataFromYaml(userType, "username", "userCredentials");
        String password = fileOperations.getDataFromYaml(userType, "password", "userCredentials");
        click(login_button);
        click(email_field);
        sendKeys(email_field, username);
        driver.navigate().back();
        click(password_field);
        sendKeys(password_field, password);
        driver.navigate().back();
        click(login_button);
    }

    public void verifySuccessfulLoginMessage() {
        try {
            for (int i = 0; i < 3; i++) {
                if (isPresentAndDisplayed(allow_button)) {
                    click(allow_button);
                }
            }
        } catch (Exception e) {
        }
        assertionUtilities.assertTheElementVisibility(home_button);
    }
}
