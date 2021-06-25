package com.TSN.testcases;

import com.TCR.pages.HomePage;
import com.TSN.datasource.DataToProvide;
import org.testng.annotations.Test;

public class TC_SEARCH_PRODUCT extends DataToProvide {

    @Test
    public void searchValidProduct() {
        new HomePage(driver)
                .goToBaseUrl("http://demowebshop.tricentis.com")
                .searchProduct("book")
                .assertResultsDisplayed("book");

    }


}
