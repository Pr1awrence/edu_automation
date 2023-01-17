package edu.automation.book.browserspecificmanipulation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InstallWebExtensionTest {
    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        /* Put the extension package (.crx) in the resources folder */
        Path extension = Paths.get(ClassLoader.getSystemResource("extension_name.crx").toURI());
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(extension.toFile());

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }
}
