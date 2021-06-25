package com.FMK.config;

public class Constant {
    private static String userDir = System.getProperty("user.dir");
    // this constant varies according to the module when we call userDir
    public static final String PROJECT_PATH = userDir.endsWith("TestSelenium")
            ? userDir.substring(0,userDir.lastIndexOf("TestSelenium")) : userDir;
    public static final String BASE_URL = "baseUrl";
    public static final String DATA_PATH = "dataPath";
}
