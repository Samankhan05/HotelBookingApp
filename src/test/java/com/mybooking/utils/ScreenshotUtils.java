package com.mybooking.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {

            File source =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File destination =
                    new File("reports/screenshots/" + testName + ".png");

            destination.getParentFile().mkdirs();

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destination.getAbsolutePath();

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}