package edu.automation.book.browseragnosticfeatures.eventlisteners;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static org.assertj.core.api.Assertions.assertThat;

public class EventListenerTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        MyEventListener listener = new MyEventListener();
        WebDriver originalDriver = WebDriverManager.chromedriver().create();
        driver = new EventFiringDecorator<>(listener).decorate(originalDriver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testEventListener() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
        /* Click on a web link to change the page. Resulting two screenshots should be different */
        driver.findElement(By.linkText("Web form")).click();
    }
}
