package edu.automation.book.browserspecificmanipulation.geolocation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeolocationFirefoxTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", true); // Enable geolocation API
        // To grant access to the geolocation data (click on allow btn)
        options.addPreference("geo.prompt.testing", true);
        // To gather data using all available components (such as GPS, WiFi, Bluetooth)
        options.addPreference("geo.provider.use_corelocation", true);

        driver = new FirefoxDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testGeolocation() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/geolocation.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("get-coordinates")).click();
        WebElement coordinates = driver.findElement(By.id("coordinates"));
        wait.until(ExpectedConditions.visibilityOf(coordinates));
    }
}
