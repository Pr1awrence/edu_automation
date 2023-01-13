package edu.automation.other;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;


public class NewWindowsAndTabs {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        Thread.sleep(2000);
        String originalWindow = driver.getWindowHandle(); //Store the ID of the original window

        /*<----- Selenium 4 and later versions ----->*/
        /*https://www.selenium.dev/documentation/webdriver/interactions/windows*/

        driver.switchTo().newWindow(WindowType.TAB); // Opens a new window and switches to new !TAB!
        driver.navigate().to("https://www.google.com/"); // Opens google homepage in the newly opened tab
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(originalWindow); //After closing tab you need to switch to another tab
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click(); // Check that we switched
        driver.findElement(By.id("docsearch-input")).sendKeys("new tab!!");
        Thread.sleep(2000);

        driver.switchTo().newWindow(WindowType.WINDOW); // Opens a new window and switches to new !WINDOW!
        driver.navigate().to("https://maps.google.com");
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(2000);
        // Так как предыдущее окно открыто, а мы уже нажимали на кнопку search, то просто вбиваем доп текст
        driver.findElement(By.id("docsearch-input")).sendKeys("new window!!");

        Thread.sleep(2000);
        driver.quit();

        // Old workaround with JSExecutor
        // You have to switch manually to tab (driver.switchTo().window(tabs.get(1));)
        // ((JavascriptExecutor) driver).executeScript("window.open()");
    }
}
