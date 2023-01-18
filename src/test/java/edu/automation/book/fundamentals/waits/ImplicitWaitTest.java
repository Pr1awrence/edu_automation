package edu.automation.book.fundamentals.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ImplicitWaitTest {
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
    void testImplicitWait() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // If the element is not shown, there will be an error (write 5 sec instead of 10)
        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }
}
