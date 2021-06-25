package com.FMK.model;

import java.util.Map;

public class Feature {
    private String name;
    private Map<String,TestCase> testCases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String,TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(Map<String, TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n");
        for (Map.Entry<String, TestCase> datas: testCases.entrySet()) {
            result.append(datas.getValue().toString()).append("\n");
        }
        return result.toString();
    }
}