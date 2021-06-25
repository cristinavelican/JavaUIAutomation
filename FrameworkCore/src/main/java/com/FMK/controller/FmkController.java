package com.FMK.controller;

import com.FMK.config.ConfigReader;
import com.FMK.data.FeatureRepositoryImpl;
import com.FMK.data.FeatureRepository;
import com.FMK.model.Feature;
import java.io.IOException;
import java.util.Map;

import static com.FMK.config.Constant.*;

public class FmkController {

    private ConfigReader readConfig;
    private FeatureRepository featureRepository;

    public FmkController() {
        readConfig = new ConfigReader(PROJECT_PATH + "\\Configuration\\config.properties");
        featureRepository = new FeatureRepositoryImpl();
    }

    public String getBaseUrl() {
        return readConfig.getProperty(BASE_URL);
    }

    public Map<String, Feature> getFeatures() throws IOException {
      return featureRepository.getFeatures(PROJECT_PATH + readConfig.getProperty(DATA_PATH));
    }

}
