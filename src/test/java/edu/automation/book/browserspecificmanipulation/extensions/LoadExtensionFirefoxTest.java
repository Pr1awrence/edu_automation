package edu.automation.book.browserspecificmanipulation.extensions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.github.bonigarcia.wdm.WebDriverManager.zipFolder;
import static org.assertj.core.api.Assertions.assertThat;

/*
* As of Selenium 4.1, Firefox allows installing web extensions from its source code -
* using installExtension() method from HasExtensions interface, which FirefoxDriver implements.
*/

public class LoadExtensionFirefoxTest {
    WebDriver driver;

    Path zippedExtension;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extensionFolder = Paths
                .get(ClassLoader.getSystemResource("web-extension").toURI());
        zippedExtension = zipFolder(extensionFolder);

        driver = new FirefoxDriver();
        ((FirefoxDriver) driver).installExtension(zippedExtension, true);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        driver.get("https://www.selenium.dev/");
        WebElement header = driver.findElement(By.tagName("h1"));
        assertThat(header.getText()).isEqualTo("Use Selenium!");
    }
}
