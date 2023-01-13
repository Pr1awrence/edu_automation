package edu.automation.book.browseragnosticfeatures.dialogboxes;

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

import static org.assertj.core.api.Assertions.assertThat;

public class ModalTest {
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
    void testModal() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("my-modal")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
        WebElement modalBody = driver.findElement(By.className("modal-body"));
        assertThat(modalBody.getText()).isEqualTo("This is the modal body");

        WebElement closeBtn = driver.findElement(By.xpath("//button[text() = 'Close']"));
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
    }
}
