package com.FMK.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String path){
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
