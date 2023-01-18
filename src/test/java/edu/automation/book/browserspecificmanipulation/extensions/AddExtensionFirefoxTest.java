package edu.automation.book.browserspecificmanipulation.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddExtensionFirefoxTest {
    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        /* Put the extension package (.crx) in the resources folder */
        Path extension = Paths.get(ClassLoader.getSystemResource("extension_name.crx").toURI());
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(extension.toFile());
        options.setProfile(profile);

        driver = WebDriverManager.firefoxdriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
