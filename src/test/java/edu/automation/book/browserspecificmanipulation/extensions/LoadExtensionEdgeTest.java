package edu.automation.book.browserspecificmanipulation.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/*
* Chromium-based browsers (Chrome, Edge) also allow loading an extension from its source code (not packaged as a crx file).
* This test setup illustrates this feature.
* It uses JS to change the content of first-level headers (h1 tags) with a custom message.
*/

public class LoadExtensionEdgeTest {
    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extension = Paths.get(ClassLoader.getSystemResource("web-extension").toURI());
        EdgeOptions options = new EdgeOptions();
        /* Specifying the extension path using the --load-extension argument */
        options.addArguments("--load-extension=" + extension.toAbsolutePath());

        driver = WebDriverManager.edgedriver().capabilities(options).create();
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
