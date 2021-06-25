package com.TCR.pages;

import com.TCR.base.BaseMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DemoPage extends BaseMethods {

    private By titleDemo = By.xpath("//*[@id=\"root\"]/div/div/main/div[1]/div/h1");

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    public void assertCorrectPage(String expectedUrl) {
        waitVisibility(titleDemo);
        assertUrl(expectedUrl);
    }
}
