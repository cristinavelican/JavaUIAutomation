package com.FMK.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestCase {
    private String id;
    private String testStep;
    private Map<String, String[]> testData;
    private Map<Integer, ArrayList<String>> datasInArrays;
    private String expectedResult;
    private String prerequisiteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTestStep(String testStep) {
        this.testStep = testStep;
    }

    public String getTestStep() {
        return testStep;
    }

    public Map<String, String[]> getTestData() {
        return testData;
    }

    public Map<Integer, ArrayList<String>> getDataInArray() {
        return datasInArrays;
    }

    public void addTestData(String key, String[] values) {
        if (testData == null) {
            testData = new HashMap<>();
        }
        testData.putIfAbsent(key, values);
    }

    public void setTestDataFromRaw(String testDataRaw) {
        if (testDataRaw.length() > 1) {
            String[] list = testDataRaw.split(";");
            int row = 0;
            String[] rows = {};
            for (String field : list) {
                String key = field.substring(0, field.indexOf("["));
                String valuesRaw = field.substring(field.indexOf("[") + 1, field.indexOf("]"));
                String[] values = valuesRaw.split(",");
                addTestData(key, values);
                addTestDataInArray(row, values);
            }
        }
    }

    public void addTestDataInArray(int key, String[] values) {
        if(datasInArrays == null) {
            datasInArrays = new HashMap<>();
        }
        for(String s : values) {
            if(!datasInArrays.containsKey(key)){
                datasInArrays.putIfAbsent(key, new ArrayList<>());
            }
            datasInArrays.get(key).add(s);
            ++key;

        }
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setPrerequisiteId(String prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public String getPrerequisiteId() {
        return prerequisiteId;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(id + " - " + testStep + " - " + expectedResult + " - ");
        if (testData == null) {
            result.append(" no test data ");
        } else {

            for (Map.Entry<String, String[]> datas : testData.entrySet()) {
                result.append(datas.getKey()).append(" : ");
                for (String data : datas.getValue()) {
                    result.append(data);
                }
                result.append("\n");
            }
        }
        return result.toString();
    }
}
