package edu.automation.book.browseragnosticfeatures.navigationtargets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
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
    void testFrames() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/frames.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /* Another way to switch to iframe/frame (instead of .frameToBeAvailableAndSwitchToIt())
        * wait.until(ExpectedConditions.presenceOfElementLocated(By.name("frame-body")));
        * driver.switchTo().frame("frame-body");
        * */

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("frame-body")));
        By pName = By.tagName("p");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pName, 0));

        List<WebElement> paragraphs = driver.findElements(pName);
        assertThat(paragraphs).hasSize(20);

        driver.switchTo().defaultContent(); // return to the top level
        driver.switchTo().frame("frame-header"); // switch to another frame
        boolean isLogoDisplayed = driver.findElement(By.xpath("//a[contains(@href, 'selenium')]")).isDisplayed();
        assertThat(isLogoDisplayed).isTrue();
    }
}
