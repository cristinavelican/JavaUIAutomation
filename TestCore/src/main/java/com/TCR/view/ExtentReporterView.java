package com.TCR.view;

import com.TCR.base.BaseTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterView extends BaseTest {
    private static ExtentTest extentTest;
    private static ExtentReports extentReports;


    @BeforeSuite
    public static void startTest(){
        // in before suite, we are creating HTML report template, adding basic information to it and load the extent-config.xml file
        extentReports =  new ExtentReports("../Testng-output/reports/SeleniumExtentReport.html",
                true);
        extentReports.addSystemInfo("Host Name", "staf").addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "host");
        extentReports.loadConfig(new File("../Testng-output/custom/extent-config.xml"));
        extentTest = extentReports.startTest("ExtentDemo");

    }

    @BeforeMethod
    public void beforeMethod(Method method){
        // we are collecting the current running test case name
        String className = this.getClass().getSimpleName();
        extentTest = extentReports.startTest(className+"-" + method.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result, Method method) throws Exception {
//In after method we are collecting the test execution status and based on that the information writing to HTML report
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            String screenshotPath = getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.FAIL, "Error Details :- \n" + result.getThrowable().getMessage()
                    + extentTest.addScreenCapture(screenshotPath));
        }
        if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        }
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
        }
    }

    @AfterSuite
    public static void endTest(){
        extentReports.endTest(extentTest);
        extentReports.flush();
    }


    /**

     * To Capture the Screenshot and return the file path to extent report fail
     * cases
     *
     * @param driver
     * @param screenshotName
     * @return
     * @throws IOException
     */

    //driver and screenshotName
    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = "../Testng-output/FailedTestsScreenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

}
