# Booking.com Hotel Automation

## Overview

This project automates the hotel search flow on Booking.com using Selenium WebDriver with Java.

## Requirements

* Java 21
* Maven 3.8+
* Google Chrome
* Selenium WebDriver

## Test Scenario

The automation performs the following steps:

1. Open Booking.com.
2. Search for hotels in Singapore.
3. Select the required check-in date.
4. Select the check-out date.
5. Search for available hotels.
6. Print the top 5 hotels with their ratings in the console.
7. Select the second hotel from the search results.

## Project Structure

* `HotelBooking.java` – Page Object class containing locators and page methods.
* `HotelBookingTest.java` – Test class containing the automation flow.
* `pom.xml` – Maven dependencies and project configuration.

## How to Run

1. Clone/download the project.
2. Open the project in Eclipse or IntelliJ IDEA.
3. Make sure Java and Maven are configured.
4. Run `HotelBookingTest.java` as a Java/TestNG test.

Alternatively, from the project root directory, run:

```bash
mvn test
```

## Expected Result

The test should:

* Search for hotels in Singapore.
* Display the top 5 hotels and their ratings in the console.
* Select the second hotel displayed in the search results.
* Complete the automation successfully.

## Test Output Example

```text
1. Hotel Name | Rating: 9.1
2. Hotel Name | Rating: 8.7
3. Hotel Name | Rating: 8.5
4. Hotel Name | Rating: 8.4
5. Hotel Name | Rating: 8.2

Second hotel selected successfully.
```
