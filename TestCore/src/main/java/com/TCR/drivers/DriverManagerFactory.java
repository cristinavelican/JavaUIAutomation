package com.TCR.drivers;

import com.TCR.drivers.driverbrowsers.ChromeDriverManager;
import com.TCR.drivers.driverbrowsers.FirefoxDriverManager;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverType driverType) {
        DriverManager driverManager;

        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}
