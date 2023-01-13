package edu.automation.book.browseragnosticfeatures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import static org.assertj.core.api.Assertions.assertThat;


public class ColorPickerTest {
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
    void testColorPicker() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement picker = driver.findElement(By.name("my-colors"));
        String initColor = picker.getAttribute("value");

        Color red = new Color(255, 0, 0, 1);
        // picker.sendKeys(red.asHex()); // alternative way without using js
        String script = String.format("arguments[0].setAttribute('value', '%s');", red.asHex());
        js.executeScript(script, picker);

        String currentColor = picker.getAttribute("value");
        assertThat(currentColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(currentColor)).isEqualTo(red);
    }
}
