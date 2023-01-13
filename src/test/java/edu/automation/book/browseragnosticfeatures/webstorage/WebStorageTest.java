package edu.automation.book.browseragnosticfeatures.webstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static org.assertj.core.api.Assertions.assertThat;

public class WebStorageTest {
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
    void testWebStorage() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
        WebStorage webStorage = (WebStorage) driver;

        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem("language", "English");
        assertThat(localStorage.size()).isEqualTo(1);

        String langItem = localStorage.getItem("language");
        assertThat(langItem).isEqualTo("English");

        SessionStorage sessionStorage = webStorage.getSessionStorage();
        sessionStorage.setItem("new element", "new value");
        assertThat(sessionStorage.size()).isEqualTo(3);

        String lastNameItem = sessionStorage.getItem("lastname");
        assertThat(lastNameItem).isEqualTo("Doe");
    }
}
