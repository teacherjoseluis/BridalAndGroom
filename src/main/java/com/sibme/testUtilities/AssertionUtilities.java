package com.sibme.testUtilities;

import com.sibme.CoreUtilties.BasePage;
import com.sibme.CoreUtilties.CoreManipulation;
import com.sibme.init.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

;

public class AssertionUtilities {
    BasePage basePage=new BasePage();
    DriverFactory driverFactory=new DriverFactory();
    AppiumDriver driver;


    CoreManipulation coreManipulation=new CoreManipulation();

    public AssertionUtilities(){
        driver = driverFactory.getDriver();

    }


    public void assertEqualTwoValues(String elementActualText, String expectedValue) {
        try {
            Assert.assertEquals(elementActualText,expectedValue);
            System.out.println("Assertion passed.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed! Actual value: " + elementActualText + ", Expected value: " + expectedValue);
            throw e; // Re-throw the assertion error to mark the test as failed
        }
    }

    public void assertTheElementVisibility(WebElement element){
        basePage.waitForElement(element);
        Assert.assertTrue(element.isDisplayed());
    }

    public void assertURLs(List<String> expectedURLs) {
        String currentURL = driver.getCurrentUrl();
        boolean isURLMatching = expectedURLs.stream().anyMatch(currentURL::contains);

        if (!isURLMatching) {
            String assertionMessage = "Current URL: " + currentURL + ", Expected URLs: " + expectedURLs;
            Assert.fail(assertionMessage);
        }
    }

    public void assertURL(String expectedURL) {
        basePage.waitForURLToChange(expectedURL);
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.contains(expectedURL)) {
            String assertionMessage = "Current URL: " + currentURL + ", Expected URL: " + expectedURL;
            Assert.fail(assertionMessage);
        }
    }


    public void AssertATextNotPresentInList(List<String> strings, String string) {
        try {
            Assert.assertTrue(!strings.contains(string));
            System.out.println("Assertion passed.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed! Actual value: " + strings + ", Expected value: " + string);
            throw e; // Re-throw the assertion error to mark the test as failed
        }

    }


    public void AssertATextPresentInList(List<String> strings, String string) {
        try {
            Assert.assertTrue(strings.contains(string));
            System.out.println("Assertion passed.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed! Actual value: " + strings + ", Expected value: " + string);
            throw e; // Re-throw the assertion error to mark the test as failed
        }

    }

    public void AssertATextPresentInString(String actualString, String expected) {
        try {
            Assert.assertTrue(actualString.contains(expected));
            System.out.println("Assertion passed.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed! Actual value: " + actualString + ", Expected value: " + expected);
            throw e; // Re-throw the assertion error to mark the test as failed
        }

    }


}
