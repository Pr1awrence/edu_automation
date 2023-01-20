package edu.automation.book;

import edu.automation.book.browserspecificmanipulation.PageLoadingStrategiesTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * This feature is supported only in Chromium-based browsers
 */

public class BrowserLogsTest {
    private final static Logger LOGGER = Logger.getLogger(PageLoadingStrategiesTest.class.getName());
    WebDriver driver;

    @BeforeEach
    void setUp() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(ChromeOptions.LOGGING_PREFS, logs); // or EdgeOptions.LOGGING_PREFS

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testBrowserLogs() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        assertThat(browserLogs.getAll()).isNotEmpty();
        browserLogs.forEach(l -> LOGGER.info(l.getMessage()));
    }
}
