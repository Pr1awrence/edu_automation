package edu.automation.other;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PractiseInputXpath {
    public static void main(String[] args) throws InterruptedException {
        /* Setup driver */
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");

        /* Click on the search button, after search for 'first script' */
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
        driver.findElement(By.id("docsearch-input")).sendKeys("first script");

        Thread.sleep(500);

        /* Waiting for result container - явное ожидание. Using getText() for example */
        WebElement resultsContainer = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className("DocSearch-Hit-Container")));
        System.out.println(resultsContainer.getText());

        Thread.sleep(500);

        /* Get element by link in results container, then click*/
        WebElement title = driver.findElement(By.xpath("//a[@href='https://www.selenium.dev/documentation/webdriver/getting_started/first_script/#next-steps']"));
        title.click();

        Thread.sleep(500);

        /* Find first header on the page, then scroll to it */
        WebElement header = driver.findElement(By.xpath("//h1[text()='Write your first Selenium script']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(header);
        actions.perform();

        /*Then get some properties */
        String headerMaxWidth = header.getCssValue("max-width");
        String headerUri = header.getAttribute("baseURI");

        System.out.println(headerUri);
        System.out.println(headerMaxWidth);

        Thread.sleep(10000);
        driver.quit();

    }
}
