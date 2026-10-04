package com.mybooking.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

        	File reportDirectory = new File("reports");

        	if (!reportDirectory.exists()) {
        	    reportDirectory.mkdirs();
        	}

        	ExtentSparkReporter spark =
        	        new ExtentSparkReporter("reports/ExtentReport.html");

            spark.config().setDocumentTitle("Hotel Booking Automation Report");
            spark.config().setReportName("Hotel Booking Test Execution");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "Booking.com");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Build Tool", "Maven");
            extent.setSystemInfo("CI", "Jenkins");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}
