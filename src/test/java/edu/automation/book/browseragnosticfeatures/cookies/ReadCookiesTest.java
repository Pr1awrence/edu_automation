package edu.automation.book.browseragnosticfeatures.cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadCookiesTest {
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
    void testReadCookies() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(2);

        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");

        // Doesn't affect the test, invoke command to check the cookies in the browser GUI
        driver.findElement(By.id("refresh-cookies")).click();
    }
}
