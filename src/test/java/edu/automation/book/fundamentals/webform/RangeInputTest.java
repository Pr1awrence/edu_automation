package edu.automation.book.fundamentals.webform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeInputTest {
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
    void testRangeInput() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement rangeInput = driver.findElement(By.name("my-range"));
        String initInputValue = rangeInput.getAttribute("value");

        // Move arrow to the right from 5 to 10
        for (int i = 0; i < 5; i++) {
            rangeInput.sendKeys(Keys.ARROW_RIGHT);
        }
        String endInputValue = rangeInput.getAttribute("value");

        assertThat(endInputValue).isNotEqualTo(initInputValue);
        assertThat(endInputValue).isEqualTo("10");
    }
}
