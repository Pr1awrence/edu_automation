package edu.automation.book.fundamentals.usergestures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * moveToElement(element) - move mouse cursor to the middle of a given element
 * build() - generate a composite action containing all previous actions
 * perform() - execute the composite action
 * */

public class MouseOverTest {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void quit() {
        driver.quit();
    }

    @Test
    void testMouseOver() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        Actions actions = new Actions(driver);

        List<String> imageNames = Arrays.asList("compass", "calendar", "award", "landscape");
        for (String name : imageNames) {
            String xpath = String.format("//img[@src='img/%s.png']", name);
            WebElement image = driver.findElement(By.xpath(xpath));
            actions.moveToElement(image).build().perform();

            WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("div")).near(image));

            assertThat(caption.getText()).isEqualToIgnoringCase(name);
        }
    }
}
