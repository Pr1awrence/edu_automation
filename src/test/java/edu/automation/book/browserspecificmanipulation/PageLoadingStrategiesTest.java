package edu.automation.book.browserspecificmanipulation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Docs: https://www.selenium.dev/documentation/webdriver/drivers/options/
 * Strategies:
 * normal / ReadyState: complete / Used by default, waits for all resources to download
 * eager / ReadyState: interactive / DOM access is ready, but other resources like images may still be loading
 * none / ReadyState: Any / Does not block WebDriver at all
 */
public class PageLoadingStrategiesTest {
    private final static Logger LOGGER = Logger.getLogger(PageLoadingStrategiesTest.class.getName());
    WebDriver driver;

    PageLoadStrategy pageLoadStrategy;

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        pageLoadStrategy = PageLoadStrategy.NONE;
        options.setPageLoadStrategy(pageLoadStrategy);

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testPageLoad() {
        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration.ofMillis(System.currentTimeMillis() - initMillis);
        /* Use findElements instead because only the initial page is downloaded */
        List<WebElement> image = driver.findElements(By.className("img-fluid"));

        assertThat(image.size()).isEqualTo(0);
        assertThat(driver.getTitle()).contains("");

        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        Object pageLoad = capabilities.getCapability(CapabilityType.PAGE_LOAD_STRATEGY);
        LOGGER.info(String.format("The page took '%d' ms to be loaded using a '%s' strategy in '%s' browser name",
                elapsed.toMillis(), pageLoad, capabilities.getBrowserName()));

        assertThat(pageLoad).isEqualTo(pageLoadStrategy.toString());
    }
}
