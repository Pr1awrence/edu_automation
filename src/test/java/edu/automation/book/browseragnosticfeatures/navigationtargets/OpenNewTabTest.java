package edu.automation.book.browseragnosticfeatures.navigationtargets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenNewTabTest {
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
    void testOpenNewTab() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles().size()).isEqualTo(2);

        driver.close(); // closing current window
        driver.switchTo().window(initHandle); // switch to previous tab
        assertThat(driver.getWindowHandles().size()).isEqualTo(1);

        boolean isLinkDisplayed = driver.findElement(By.xpath("//a[contains(@href, 'github')]")).isDisplayed();
        assertThat(isLinkDisplayed).isTrue();
    }
}
