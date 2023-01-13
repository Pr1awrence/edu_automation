package edu.automation.book.fundamentals.webform;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class FileInputTest {
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
    void testFileInput() throws IOException {
        String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(initUrl);

        WebElement inputFile = driver.findElement(By.name("my-file"));

        // Creating a temp file and send it to the input
        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        String fileName = tempFile.toAbsolutePath().toString();
        inputFile.sendKeys(fileName);

        // When the form is submitted, the URL of the page changes as well as the content of the page
        driver.findElement(By.tagName("form")).submit();
        assertThat(driver.getCurrentUrl()).isNotEqualTo(initUrl);
    }
}
