package edu.automation.other;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragAndDrop {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop");

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        //actions.dragAndDrop(draggable, droppable).perform();

        /* Snippet below does the same thing */
        actions
                .moveToElement(draggable)
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .moveToElement(droppable)
                .perform();
        // Selenium allows you to construct individual action commands assigned to specific inputs
        // and chain them together and call the associated !perform! method to execute them all at once.
        // [GL] I saw that in some cases people use .release().build().perform(), but in Selenium API found only perform()
        // release means отпустить, поэтому тут не участвует, а build просто объединили с perform
    }
}
