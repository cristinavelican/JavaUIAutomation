package com.TCR.pages;

import com.TCR.base.BaseMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.util.Locale;

public class HomePage extends BaseMethods {

    private By searchBar = By.id("small-searchterms");
    private By productResuls = By.className("product-item");
    private By searchPage = By.xpath("//*[contains(@class,\"search-page\")]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToBaseUrl(String baseUrl){
        navigate(baseUrl);
        return this;
    }

    public HomePage searchProduct(String product){
        writeText(searchBar,product);
        enter(searchBar);
        return this;
    }

    public HomePage assertResultsDisplayed(String product)  {
        waitVisibility(searchPage);
        String productResultText = readText(productResuls).toLowerCase(Locale.ROOT);
        System.out.println(productResultText);
        Assert.assertTrue(productResultText.contains(product));
        return this;
    }



}
