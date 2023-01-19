package edu.automation.book.browserspecificmanipulation.notifications;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * The same setup method will be used by the edge browser
 */

public class NotificationsChromeTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testNotifications() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/notifications.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = String.join("\n",
                "const callback = arguments[arguments.length - 1];",
                "const OldNotify = window.Notification;",
                "function newNotification(title, options) {",
                "    callback(title);",
                "    return new OldNotify(title, options);",
                "}",
                "newNotification.requestPermission = OldNotify.requestPermission.bind(OldNotify);",
                "Object.defineProperty(newNotification, 'permission', {",
                "    get: function() {",
                "        return OldNotify.permission;",
                "    }",
                "});",
                "window.Notification = newNotification;",
                "document.getElementById('notify-me').click();");

        Object notificationTitle = js.executeAsyncScript(script);
        assertThat(notificationTitle).isEqualTo("This is a notification");
    }
}
