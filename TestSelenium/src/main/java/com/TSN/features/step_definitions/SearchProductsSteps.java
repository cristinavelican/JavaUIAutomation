package com.TSN.features.step_definitions;

import com.TCR.base.BaseTest;
import com.TCR.pages.HomePage;
import com.TSN.datasource.DataToProvide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SearchProductsSteps extends BaseTest {

    WebDriver driver = new FirefoxDriver();
    HomePage homePage = new HomePage(driver);
    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
       homePage.navigate("http://demowebshop.tricentis.com");
    }

    @When("I want to search for a product {string}")
    public void i_want_to_search_for_a_product(String product) {
       homePage.searchProduct(product);

    }
    @Then("The results displayed should be of the {string} category")
    public void the_results_displayed_should_be_of_the_category(String product) {
       homePage.assertResultsDisplayed(product);
    }


}
