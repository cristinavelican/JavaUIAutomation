package com.TCR.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseMethods extends BasePage {

    public BaseMethods(WebDriver driver) {
        super(driver);
    }

    // Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public void navigate(String baseUrl){
        driver.navigate().to(baseUrl);
    }

    // Wait Wrapper Method with own chosen wait timer
    public void waitVisibility(By elementBy, long seconds) {
        driverWait = new WebDriverWait(driver, seconds);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    /**
     * Click on the given By object
     *
     * @param elementBy : By object to click on
     */
    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    /*
     * Hover the mouse over an specified By object
     *
     * @param elementBy : By object to hover on
     */
    public void hover(By elementBy) {
        waitVisibility(elementBy);
        new Actions(driver).moveToElement(driver.findElement(elementBy));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Write the given text in the given By object
     *
     * @param elementBy : By object to write in
     * @param text      : String to write in the elementBy
     */
    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    /**
     * Perform an Enter action
     *
     * @param elementBy : By object to write in
     */
    public void enter(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(Keys.ENTER);
    }

    /**
     * Read the specified By object
     *
     * @param elementBy : By object to read
     * @return String
     */
    public String readText(By elementBy) {
        String text = "";
        waitVisibility(elementBy);
        text = driver.findElement(elementBy).getText();
        return text;
    }


    /**
     * If the chosen element is a textfield, it will clear it of any text.
     *
     * @param elementBy : By object to clear the text from
     */
    public void clearText(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).clear();
    }

    public void dropdownMenuByText(By elementBy, String itemString) {
        Select select = new Select(driver.findElement(elementBy));
        select.selectByVisibleText(itemString);
    }

    /**
     * Select Item from using the By object of the Select
     *
     * @param elementBy : By object linking to the list
     */
    public void dropdownMenuByValue(By elementBy, String itemString) {
        Select select = new Select(driver.findElement(elementBy));
        select.selectByValue(itemString);
    }

    /**
     * Select Item from using the By object of the Select
     *
     * @param elementBy : By object linking to the list
     * @param itemIndex : Index number of the desired option
     */
    public void dropdownMenuByIndex(By elementBy, int itemIndex) {
        Select select = new Select(driver.findElement(elementBy));
        select.selectByIndex(itemIndex);
    }

    /**
     * Get the Web Element object using the By object
     *
     * @param elementBy : By object to get the WebElement object from
     * @return WebElement
     */
    public WebElement getWebElement(By elementBy) {
        return driver.findElement(elementBy);
    }

    /**
     * Get a ArrayList of WebElements using the By object
     *
     * @param elementsBy : By object to get the ArrayList of WebElements from
     * @return List<WebElement>
     */
    public List<WebElement> getWebElements(By elementsBy) {
        List<WebElement> elements = driver.findElements(elementsBy);
        return elements;
    }


    /**
     * perform a specified action to all the WebElements in the given list These
     * actions have been implemented: write, assertEqual
     *
     * @param elements
     * @param action
     * @param parameter
     */
    public void performAction(List<WebElement> elements, String action, String parameter) {
        switch (action) {
            case "write":
                for (WebElement webElement : elements) {
                    webElement.sendKeys(parameter);
                }
            case "assertEqual":
                for (WebElement webElement : elements) {
                    Assert.assertEquals(webElement.getText(), parameter);
                }
                break;
        }
    }

    /**
     * Assert whether the text of an element is equal to an expected text value
     *
     * @param elementBy    : By object to get the text from
     * @param expectedText : the expected text value
     */
    public void assertEquals(By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);
    }

    /**
     * Assert whether an element is present in a page
     *
     * @param elementBy : By object to check the presence for
     */
    public void assertPresence(By elementBy) {
        Assert.assertNotNull(driver.findElement(elementBy));
    }

    /**
     * Assert whether the current URL is the expected one
     *
     * @param expectedUrl : String with the expected URL value
     */
    public void assertUrl(String expectedUrl) {
        if (driver.getCurrentUrl().equalsIgnoreCase(expectedUrl)) {
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        } else {
            Assert.fail("Current URL: (" + driver.getCurrentUrl() + ") is not the same as expected URL");
        }
    }

    /**
     * Assert whether one By element is the same as another By element (check if the
     * selectors get the same object)
     *
     * @param firstElementBy  : First By element
     * @param secondElementBy : Second By element
     */
    public void assertSame(By firstElementBy, By secondElementBy) {
        Assert.assertSame(firstElementBy, secondElementBy);
    }

    /**
     * Assert whether one By element is visable in the current window
     *
     * @param elementBy : By object to check the visibility of
     */
    public void assertIsDisplayed(By elementBy) {
        Assert.assertTrue(driver.findElement(elementBy).isDisplayed());
    }

    /**
     * Assert whether the webElements in an arraylist are order alphabetical
     *
     * @param list : the arraylist containing all your webElements
     */
    public void AssertAlphabetical(ArrayList<WebElement> list) {
        ArrayList<String> listValues = new ArrayList<String>(), listValuesSorted = new ArrayList<String>();
        for (WebElement we : list) {
            listValues.add(we.getText());
        }
        listValuesSorted = listValues;
        Collections.sort(listValuesSorted);

        Assert.assertEquals(listValues, listValuesSorted);
    }
}
