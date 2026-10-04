package com.mybooking.testcases;

import java.time.Duration;
import java.time.LocalDate;

//import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mybooking.pageobjects.HotelBooking;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Listeners;
import com.mybooking.listeners.TestListener;

@Listeners(TestListener.class)
public class HotelBookingTest {


	WebDriver driver;
	
	public WebDriver getDriver() {
	    return driver;
	}

	@BeforeMethod
	public void Setup() {
		

//	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://www.booking.com/?auth_success=1");

		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement closePopup = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label,'Dismiss')]")));

			closePopup.click();

			// Wait until the modal disappears
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-bui-trap-root]")));

		} catch (Exception e) {
			System.out.println("Popup not displayed.");
		}
	}

	@Test
	public void TestBookingPage() {
		HotelBooking srcbox = new HotelBooking(driver);

		srcbox.searchlocation("Singapore");

		srcbox.openDatePicker();
		System.out.println("Date picker opened successfully!");

		LocalDate checkin = LocalDate.now().plusWeeks(3);
		srcbox.selectdate(checkin);
		
		System.out.println("Check-in :" + checkin);

		LocalDate checkout = checkin.plusDays(10);
		srcbox.selectdate(checkout);

	
		System.out.println("Check-out :" + checkout);


		srcbox.searchBtn();

		srcbox.printTop5Hotles();

		srcbox.searchSecondHotel();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}