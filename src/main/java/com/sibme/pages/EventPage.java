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

public class EventPage extends BasePage {
    AssertionUtilities assertionUtilities = new AssertionUtilities();

    FileOperations fileOperations = new FileOperations();

    DriverFactory driverFactory = new DriverFactory();
    AppiumDriver driver;


    @AndroidFindBy(xpath = "(//android.widget.ImageView)[1]/following-sibling::android.widget.TextView")
    public WebElement event_name;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    public WebElement email_field;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text, 'Password')]")
    public WebElement password_field;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
    public WebElement home_button;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
    public WebElement allow_button;


    public EventPage() {
        driver = driverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    public void verifyEventName(String eventName) {
        String actualTextEventName = getText(event_name);
        assertionUtilities.assertEqualTwoValues(actualTextEventName, eventName);
    }
}
