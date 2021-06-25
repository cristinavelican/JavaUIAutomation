package com.TCR.base;

import com.FMK.config.Constant;
import com.FMK.controller.FmkController;
import com.FMK.model.Feature;
import com.TCR.drivers.DriverManager;
import com.TCR.drivers.DriverManagerFactory;
import com.TCR.drivers.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BaseTest {
    protected DriverManager driverManager;
    protected WebDriver driver;
    protected String baseUrl;

    // DataProvider requires static keyword
    // static because cannot be referenced from a static context
    private static FmkController fmkController;
    private static Map<String, Feature> features;

    protected static Object[][] fetchData(String featureName, String id) {
        Feature feature = features.get(featureName);
        Map<Integer, ArrayList<String>> datas = feature.getTestCases().get(id).getDataInArray();
        return datas.values()
                .stream()
                .map(ArrayList::toArray)
                .toArray(Object[][]::new);
    }


    @BeforeClass
    public void setUp() throws IOException {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
        driver = driverManager.getDriver();
        fmkController = new FmkController();
        features = fmkController.getFeatures();
        baseUrl = fmkController.getBaseUrl();

    }

    @AfterClass
    public void tearDown() {
        driverManager.quitWebDriver();
    }

}
