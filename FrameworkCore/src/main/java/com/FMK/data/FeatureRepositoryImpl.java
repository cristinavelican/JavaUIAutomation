package com.FMK.data;

import com.FMK.model.Feature;
import com.FMK.model.TestCase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FeatureRepositoryImpl implements FeatureRepository {

    public Feature getFeature(String name, String path) throws IOException {

        // Read excel file
        FileInputStream inputStream = new FileInputStream(new File(path));

        // Get the workbook instance for excel file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
//        XSSFSheet sheet = workbook.getSheetAt(0);

        // Get sheet from the workbook with feature name
        XSSFSheet sheet = workbook.getSheet(name);

        //Create an instance of a Feature
        Feature feature = new Feature();

        // Create an array of TestCases
        Map<String, TestCase> testCases = new HashMap<>();

        //Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        // pass the title
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            TestCase testCase = new TestCase();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellType();

                switch (cell.getColumnIndex()) {

                    case 0: // ID
                        if (cellType == CellType.STRING) {
                            testCase.setId(cell.getStringCellValue());
                        } else if (cellType == CellType.NUMERIC) {
                            testCase.setId(String.valueOf((int) cell.getNumericCellValue()));
                        }
                        break;

                    case 1: // TestStep
                        testCase.setTestStep(cell.getStringCellValue());
                        break;

                    case 2: // TestData
                        testCase.setTestDataFromRaw(cell.getStringCellValue());
                        break;

                    case 3: // ExpectedResult
                        testCase.setExpectedResult(cell.getStringCellValue());
                        break;

                    case 4: // PrerequisiteID
                        if (cellType == CellType.STRING) {
                        testCase.setPrerequisiteId(cell.getStringCellValue());
                        } else if (cellType == CellType.NUMERIC){
                            testCase.setPrerequisiteId(String.valueOf((int)cell.getNumericCellValue()));
                        }
                        break;
                }
            }
            testCases.putIfAbsent(testCase.getId(),testCase);
        }
        feature.setName(name);
        feature.setTestCases(testCases);
        return feature;
    }

    @Override
    public Map<String, Feature> getFeatures(String path) throws IOException {

        // Read excel file
        FileInputStream inputStream = new FileInputStream(new File(path));

        // Get the workbook instance for excel file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Create a Map for features
        Map<String, Feature> features = new HashMap<>();

        // Get sheets from the workbook
        for (int i = 0; i < workbook.getNumberOfSheets(); i++)
        {
            XSSFSheet sheet = workbook.getSheetAt(i);
            Feature feature = getFeature(sheet.getSheetName(),path);
            features.putIfAbsent(sheet.getSheetName(),feature);
        }
        return features;
    }
}
