package com.mybooking.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mybooking.testcases.HotelBookingTest;
import com.mybooking.utils.ExtentReportManager;
import com.mybooking.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentReportManager.getExtentReports();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
    	
    	System.out.println("========== EXTENT LISTENER STARTED ==========");

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        Object instance = result.getInstance();

        if (instance instanceof HotelBookingTest) {

            WebDriver driver =
                    ((HotelBookingTest) instance).getDriver();

            if (driver != null) {

                String screenshot =
                        ScreenshotUtils.captureScreenshot(
                                driver,
                                result.getMethod().getMethodName()
                        );

                if (screenshot != null) {

                    try {

                        test.get().addScreenCaptureFromPath(screenshot);

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test skipped.");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}