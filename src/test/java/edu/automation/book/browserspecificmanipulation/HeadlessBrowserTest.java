package edu.automation.book.browserspecificmanipulation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

public class HeadlessBrowserTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        /*
         * You can use here 4 approaches:
         * 1. constructor (used in the code)
         * 2. a builder provided by the Selenium WebDriver API - RemoteWebDriver.builder()
         * driver = RemoteWebDriver.builder().oneOf(options).build();
         * 3. WebDriverManager builder
         * driver = WebDriverManager.firefoxdriver().capabilities(options).create();
         * 4. Selenium Jupiter with the annotation @Arguments
         * @ExtendWith(SeleniumJupiter.class) <-- annotation before class
         * class ...
         * @Test
         * void testHeadless(@Arguments("--headless") ChromeDriver driver) {...}
         */
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testHeadlessBrowser() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
