package edu.automation.book.fundamentals.webform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

public class DatePickerTest {
    private final static Logger logger = Logger.getLogger(DatePickerTest.class.getName());
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testDatePicker() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();

        //find by text in a th (ex. div[text()='search-string'])
        WebElement monthElement = driver.findElement(By.xpath(
                String.format("//th[contains(text(),'%d')]", currentYear)));
        monthElement.click();

        //click on the left arrow
        WebElement arrowLeft = driver.findElement(RelativeLocator.with(By.tagName("th")).toRightOf(monthElement));
        arrowLeft.click();

        //click on the current month of year
        WebElement monthPastYear = driver.findElement(RelativeLocator.with(By.cssSelector("span[class$=focused]")).below(arrowLeft));
        monthPastYear.click();

        //click on the present day
        WebElement dayElement = driver.findElement(By.xpath(String.format("//td[@class='day' and contains(text(), '%d')]", currentDay)));
        dayElement.click();

        //get the final date on the inout text
        String oneYearBack = datePicker.getAttribute("value");
        logger.info(String.format("Final date in date picker: '%s'", oneYearBack));

        LocalDate previousYear = today.minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        logger.info(String.format("Expected date: '%s'", expectedDate));

        assertThat(oneYearBack).isEqualTo(expectedDate);
    }
}
