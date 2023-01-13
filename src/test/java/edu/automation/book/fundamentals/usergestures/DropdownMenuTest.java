package edu.automation.book.fundamentals.usergestures;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * contextClick(element) - right-click on the element
 * build() - generate a composite action containing all previous actions
 * perform() - execute the composite action
 * */

public class DropdownMenuTest {
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
    void testDropdownMenu() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        Actions actions = new Actions(driver);

        WebElement rightClickDropdown = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(rightClickDropdown).build().perform();
        WebElement rightClickContextMenu = driver.findElement(By.id("context-menu-2"));
        assertThat(rightClickContextMenu.isDisplayed()).isTrue();

        WebElement doubleClickDropdown = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(doubleClickDropdown).build().perform();
        WebElement doubleClickContextMenu = driver.findElement(By.id("context-menu-3"));
        assertThat(doubleClickContextMenu.isDisplayed()).isTrue();
    }
}
