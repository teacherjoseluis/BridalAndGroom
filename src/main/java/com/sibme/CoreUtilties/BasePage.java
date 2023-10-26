package com.sibme.CoreUtilties;

import com.sibme.init.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.length;
import static org.testng.Reporter.log;


/**
 * Base Page for all pages including admin and sibme pages Add common
 * functionality for all pages here Define all methods for accessing WebElements
 * in this class
 */
public class BasePage {
    DriverFactory driverFactory = new DriverFactory();
    AppiumDriver driver;
    String platform;
    public BasePage(){
        driver = driverFactory.getDriver();
    }


//    AppiumDriver driver = driverFactory.getDriver("iOS");

    /**
     * <h1>Confirm element is displayed on page</h1>
     * <p>
     * Use this method to check if an element is visible on a page
     * </p>
     *
     * @return
     */


    public boolean elementIsDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Element is not found in the DOM
            return false;
        }

    }

    /**
     * <h1>Wait for WebElement to be visible</h1>
     * <p>
     * This method will wait for WebElement to be visible till the time provided
     *
     * @param element , WebDriver wait for the Element
     */
    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * <h1>Wait for WebElement to become invisible</h1>
     * <p>
     * This method will wait for WebElement invisibility till the time provided
     *
     * @param element , WebDriver
     */
    public void waitForElementToBeInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * <h1>Wait for WebElement to be clickable</h1>
     * <p>
     * This method will wait for WebElement to become clickable for the time
     * provided
     *
     * @param element , WebDriver
     */
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * <h1>Element Clear</h1>
     * <p>
     * This method is wait for element and then after clear the WebElement
     *
     * @param element this is WebElement
     */
    public void clearField(WebElement element) {
        waitForElement(element);
        element.clear();
    }

    public void clearValue(WebElement element) {
        waitForElement(element);
        int len = length(element.getAttribute("value").toString());
        for (int i = 0; i < len; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void waitForURLToChange(String pageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(pageName));
    }


    /**
     * <h1>Element Click</h1>
     * <p>
     * This method is wait for element and then after click on web element
     * <p>
     * //	 * @param driver
     * This one is WebDriver
     *
     * @param element this is WebElement
     */
    public void click(WebElement element) {
        if (isPresentAndDisplayed(element)) {
            waitForElement(element);
            waitForElementToBeClickable(element);
            element.click();
        }
        else
            throw new java.util.NoSuchElementException("No element found to click::"+ element);
    }

    public void clickOnAllElementsUsingList(List<WebElement> elements) {
        if (elements.size() != 0) {
            for (Integer i = 1; i <= elements.size() - 1; i++) {
                click(elements.get(i));
            }
        } else
            throw new NullPointerException("Element List size is Zero");
    }

    /**
     * <h1>Send key for WebElement</h1>
     * <p>
     * This method is wait for element and then after send keys to WebElement
     * <p>
     * //	 * @param driver
     * This one is WebDriver
     *
     * @param element this is WebElement
     * @param data    this parameter is use enter data in WebElement
     **/
    public void sendKeys(WebElement element, String data) {
        waitForElement(element);
//        clearValue(element);
        element.sendKeys(data);
    }

    public void waitForFieldToBePopulated(WebElement field, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(field, value));
    }

    public String getText(WebElement element) {
        waitForElement(element);
        return element.getText().trim();
    }

    public void waitForTextToAppear(String textToAppear, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }



    public void clickOnSpecificElementFromListUsingText(List<WebElement> elements, String selectionName) {

        try {
            for (WebElement childElement : elements) {
                if (childElement.getText().trim().equals(selectionName)) {
                    click(childElement);
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            throw new java.util.NoSuchElementException();
        }
    }


    public WebElement getElementUsingAxisUsingText(List<WebElement> parentElement, String MatchingText, String axisPath) {
        WebElement ele = null;
        int index = 1; // Initialize the index counter
        if (isPresentAndDisplayed(parentElement)) {
            for (WebElement element : parentElement) {
                String a = getText(element);
                if (getText(element).equals(MatchingText)) {
                    String customXPath = element.toString().split(" -> xpath: ")[1];
                    // Remove the trailing "]" character from customXPath
                    customXPath = customXPath.substring(0, customXPath.length() - 1);
                    String element_string = "(" + customXPath + ")" + "[" + index + "]" + axisPath;
                    ele = driver.findElement(By.xpath(element_string));
                    break;
                }
                index++; // Increment the index for the next element
            }
        }
        return ele;
    }



    public WebElement getChildElementUsingIndexOfParentElement(List<WebElement> parentElements, List<WebElement> childElements, String parentText) {
        WebElement ele = null;
        //List
        if(isPresentAndDisplayed(parentElements)) {

            for (WebElement element : parentElements) {
                String a = getText(element);
                if (element.getText().equals(parentText)) {
                    String ab = getText(element);
                    waitForElement(element);
                    ele = childElements.get(parentElements.indexOf(element));
                    break;
                }
            }
        }
        else {
            throw new NoSuchElementException("Elements not found::" + parentElements);
        }
        return ele;
    }


    public List<WebElement> getChildElementsMatchingParentText(List<WebElement> parentElements, List<WebElement> childElements, String parentText) {
        List<WebElement> matchingChildElements = new ArrayList<>();

        if (isPresentAndDisplayed(parentElements)) {
            for (WebElement element : parentElements) {
                if (element.getText().equals(parentText)) {
                    waitForElement(element);
                    int parentIndex = parentElements.indexOf(element);
                    if (parentIndex >= 0 && parentIndex < childElements.size()) {
                        for (int i = 0; i < childElements.size(); i++) {
                            if (i == parentIndex) {
                                matchingChildElements.add(childElements.get(i));
                            }
                        }
                    }
                }
            }
            int s=matchingChildElements.size();

            if (matchingChildElements.isEmpty()) {
                throw new NoSuchElementException("Matching child elements not found for parent text: " + parentText);
            }
        } else {
            throw new NoSuchElementException("Parent elements not found or not displayed");
        }

        return matchingChildElements;
    }


    public WebElement getElementFromListUsingText(List<WebElement> mainElement, String matchingString) {
        WebElement ele = null;
        if (isPresentAndDisplayed(mainElement)) {
            for (WebElement element : mainElement) {
                if (getText(element).equals(matchingString)) {
                    ele = element;
                    break;
                }
            }
            if(ele != null){
                return ele;
            }
            else {
                throw new java.util.NoSuchElementException("No Elements found"+ matchingString);
            }

        }
        else {
            throw new NullPointerException("No Elements found"+ mainElement);
        }
    }

    public int getIndexFromListUsingText(List<WebElement> mainElement, String matchingString) {
        if (isPresentAndDisplayed(mainElement)) {
            for (int i = 0; i < mainElement.size(); i++) {
                WebElement element = mainElement.get(i);
                String s=getText(element);
                if (getText(element).equals(matchingString)) {
                    return i;
                }
            }
            throw new java.util.NoSuchElementException("No Elements found: " + matchingString);
        } else {
            throw new NullPointerException("No Elements found in the list");
        }
    }




    public WebElement getElementFromListUsingElementIndex(List<WebElement> mainElements, List<WebElement> childElements, WebElement indexedElement) {
        WebElement ele = null;
        if (isPresentAndDisplayed(childElements)) {
            ele = childElements.get(mainElements.indexOf(indexedElement));
            return ele;
        }
        else {
            throw new NullPointerException("Element not found::"+ childElements);
        }
    }


    public void clickOnSpecificElementFromListUsingTitle(List<WebElement> elements, String title) {

        for (WebElement childElement : elements) {
            if (childElement.getAttribute("title").equals(title)) {
                childElement.click();
                break;
            } else
                throw new NoSuchElementException("No element found");
        }
    }


    public String getAttributeByLocator(WebElement locator, String attributeName) {
        try {
            return locator.getAttribute(attributeName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebElement findAnElementByLocator(String locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        return element;
    }

    public void waitForVisibilityOfElementToHaveText(WebElement locator, String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(locator, text));
    }
    public void waitForAttributeContains(WebElement element, String attributeName, String expectedValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(element, attributeName, expectedValue));
    }

    public List<WebElement> findAllElementByLocator(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
        return elements;
    }

    public void hoverOnAnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public boolean isPresentAndDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.visibilityOf(element));

            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public boolean isPresentAndDisplayed(final List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        for (WebElement element : elements) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                return true; // Return true as soon as one element is found
            } catch (TimeoutException e) {
                System.out.println("This element is not found");
                System.out.println("Element not found");
            }
        }

        return false;
    }

    public void selectDropdownOptionByText(WebElement dropdownElement, String optionText) {
        Select selectDropdown = new Select(dropdownElement);
        selectDropdown.selectByVisibleText(optionText);
    }

    public void dropdownSelectByIndex(Select select, int index) {
        select.selectByIndex(index);
    }

    public void dropdownSelectByValue(Select select, String value) {
        select.selectByValue(value);
    }

    public String getDropdownSelectedValue(Select select) {
        return select.getFirstSelectedOption().getText();
    }


    public void switchWindows(Integer index) {
        try {
            Set<String> allWindows = driver.getWindowHandles();
            String[] windows = allWindows.toArray(new String[allWindows.size()]);
            driver.switchTo().window(windows[index]);
        } catch (Exception e) {
            log("Unexpected issue occurred in switching windows: " + e);
        }

    }

    public String getTextFromClipboard() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String result = "";
        try {
            result = (String) clipboard.getData(DataFlavor.stringFlavor);

        } catch (Exception e) {
            log("Unexpected issue occured in getting text from clip board: " + e);
        }
        return result;
    }


}
