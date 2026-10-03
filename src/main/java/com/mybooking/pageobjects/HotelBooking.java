package com.mybooking.pageobjects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBooking {

//	Variables
	WebDriver driver;
	WebDriverWait wait;

//	Constructor
	public HotelBooking(WebDriver d) {
		this.driver = d;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		PageFactory.initElements(d, this);
	}

//	Page Objects with PageFactory

	@FindBy(id = "searchbox-horizontal-destination-input")
	WebElement srcLocation;

	@FindBy(className = "a9b08830eb")
	WebElement datePicker;

	@FindBy(xpath = "//span[text()='Search']")
	WebElement Searchbtn;

	// Dynamic hotel cards - use By instead of @FindBy(WithoutPageFactory)

	By hotelCardsLocator = By.xpath("//div[contains(@data-testid, 'property-card-container')]");
	
	By hotelNameLocator = By.xpath(".//div[@data-testid='title']");

	By hotelRatingLocator = By.xpath(".//*[@data-testid='review-score']");

//	Actions needs to perform on Page objects

	// Search location
	public void searchlocation(String srcloc) {
		srcLocation.sendKeys(srcloc);
	}

	// Open date picker
	public void openDatePicker() {
		wait.until(ExpectedConditions.elementToBeClickable(datePicker));
		datePicker.click();
	}

	// Select date
	public void selectdate(LocalDate date) {
		String dateValue = date.toString();

		By datelocator = By.xpath("//span[@data-date='" + dateValue + "']");

		WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(datelocator));
		dateElement.click();
	}

//	Click on Search Button
	public void searchBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(Searchbtn));
		Searchbtn.click();
	}

//	Print top 5 hotels
	public void printTop5Hotles() {
		System.out.println("Search Top 5 hotels");

		for (int i = 0; i < 5; i++) {
			try {
				List<WebElement> hotelCards = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelCardsLocator));

				WebElement hotel = hotelCards.get(i);

				String hotelName = hotel.findElement(hotelNameLocator).getText();

				String ratingText = hotel.findElement(hotelRatingLocator).getText();

				String rating = ratingText.split("\n")[1];

				System.out.println((i + 1) + ". " + hotelName + " | Rating: " + rating);

			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				i--;
			}
		}
	}


//	Select second hotel
	public void searchSecondHotel() {
			List<WebElement> hotelCards = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(hotelCardsLocator));

			WebElement secondHotel = hotelCards.get(1);

			String hotelName = secondHotel.findElement(hotelNameLocator).getText();

			System.out.println("**************Print Second Hotel***********************");

			System.out.println("Second hotel is: " + hotelName);

			wait.until(ExpectedConditions.elementToBeClickable(secondHotel));
			secondHotel.click();
		}
	}
