package edu.automation.book.fundamentals.webform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.*;

public class TextInputTest {
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
    void testTextInput() {
        String text = "Simple text";
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement input = driver.findElement(By.name("my-text"));
        input.sendKeys(text);
        assertThat(input.getAttribute("value")).isEqualTo(text);

        input.clear();
        assertThat(input.getAttribute("value")).isEmpty();
    }
}
