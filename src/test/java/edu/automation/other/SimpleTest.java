package edu.automation.other;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.*;

public class SimpleTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    //Get simple info using selenium
    @Test
    void testTitle() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();

        assertThat(title).contains("Selenium WebDriver");
    }

    //Simple example of using equals with int
    @Test
    void shouldShowSimpleAssertion() {
        assertThat(1).isEqualTo(1); //assertEquals(1,1) using JUnit
    }
}
