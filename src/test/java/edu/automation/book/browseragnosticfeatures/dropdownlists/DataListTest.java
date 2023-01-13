package edu.automation.book.browseragnosticfeatures.dropdownlists;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class DataListTest {
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
    void testDataList() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement dataList = driver.findElement(By.name("my-datalist"));
        dataList.click();

        WebElement option = driver.findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getAttribute("value");
        dataList.sendKeys(optionValue);

        assertThat(dataList.getAttribute("value")).isEqualTo("New York");
    }
}
