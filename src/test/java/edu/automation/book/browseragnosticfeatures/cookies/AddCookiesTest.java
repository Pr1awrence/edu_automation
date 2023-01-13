package edu.automation.book.browseragnosticfeatures.cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCookiesTest {
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
    void testAddCookies() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        Options options = driver.manage();
        Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName()).getValue();
        assertThat(readValue).isEqualTo(newCookie.getValue());

        // Doesn't affect the test, invoke command to check the cookies in the browser GUI
        driver.findElement(By.id("refresh-cookies")).click();
    }
}
