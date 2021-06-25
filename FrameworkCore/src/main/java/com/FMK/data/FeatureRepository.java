package com.FMK.data;

import com.FMK.model.Feature;
import java.io.IOException;
import java.util.Map;

public interface FeatureRepository {

    Feature getFeature(String name, String path) throws IOException;

    Map<String, Feature> getFeatures(String path) throws IOException;
}
