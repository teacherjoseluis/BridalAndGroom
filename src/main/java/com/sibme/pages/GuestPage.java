package com.sibme.pages;

import com.sibme.CoreUtilties.BasePage;
import com.sibme.init.DriverFactory;
import com.sibme.testUtilities.AssertionUtilities;
import com.sibme.testUtilities.FileOperations;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class GuestPage extends BasePage {
    AssertionUtilities assertionUtilities = new AssertionUtilities();

    FileOperations fileOperations = new FileOperations();

    DriverFactory driverFactory = new DriverFactory();
    AppiumDriver driver;


    @AndroidFindBy(xpath = "(//android.widget.ImageButton)[2]")
    public WebElement plus_icon;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
    public WebElement guest_name_input_field;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    public WebElement guest_email_input_field;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
    public WebElement guest_phone_input_field;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='SAVE']")
    public WebElement save_guest_button;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
    public WebElement home_button;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
    public WebElement allow_button;


    public GuestPage() {
        driver = driverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    public void moveToGuestPage(String iconName) {
        WebElement icon_element = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc='" + iconName + "']"));
        click(icon_element);
        WebElement icon_text_element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + iconName + "']"));
        assertionUtilities.assertTheElementVisibility(icon_text_element);
    }

    public void clickOnPlus() {
        click(plus_icon);
    }

    public void clickOnButton(String buttonName) {
        WebElement button_element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + buttonName + "']/following-sibling::android.widget.ImageButton"));
        click(button_element);
    }

    public void verifyScreenTitle(String screenName) {
        WebElement screen_title = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id, 'id/appbartitle') and @text='" + screenName + "']"));
        assertionUtilities.assertTheElementVisibility(screen_title);
    }

    public void enterGuestName(String guestName) {
        click(guest_name_input_field);
        sendKeys(guest_name_input_field, guestName);
        driver.navigate().back();

    }

    public void enterGuestEmail(String guestEmail) {
        click(guest_email_input_field);
        sendKeys(guest_email_input_field, guestEmail);
        driver.navigate().back();

    }

    public void enterGuestPhone(String guestPhoneNumber) {
        click(guest_phone_input_field);
        sendKeys(guest_phone_input_field, guestPhoneNumber);
        driver.navigate().back();
    }

    public void clickOnRadioButton(String groupName, String radioButtonName) {
        WebElement radio_button_element = driver.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id, ':id/" + groupName + "group')]/android.widget.RadioButton[@text='" + radioButtonName + "']"));
        click(radio_button_element);
    }

    public void saveGuest() {
        click(save_guest_button);
        Alert alert = driver.switchTo().alert();

        // Accept the alert
        alert.accept();


        try {
            for (int i = 0; i < 3; i++) {
                if (isPresentAndDisplayed(allow_button)) {
                    click(allow_button);
                }
            }
        } catch (Exception e) {

        }
    }
}
