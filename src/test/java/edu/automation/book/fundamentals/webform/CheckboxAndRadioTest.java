package edu.automation.book.fundamentals.webform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxAndRadioTest {
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
    void testCheckboxesAndRadio() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
        checkbox2.click();
        assertThat(checkbox2.isSelected()).isTrue();

        WebElement radio2 = driver.findElement(By.id("my-radio-2"));
        radio2.click();
        assertThat(radio2.isSelected()).isTrue();
    }
}
