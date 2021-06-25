package com.TSN.datasource;

import com.TCR.view.ExtentReporterView;
import org.testng.annotations.DataProvider;

public class DataToProvide extends ExtentReporterView {

    @DataProvider
    protected static Object[][] data_login() {
        return fetchData("login", "7");
    }

}
