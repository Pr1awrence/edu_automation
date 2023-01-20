package edu.automation.book.browserspecificmanipulation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

/*
* docs: https://www.selenium.dev/documentation/webdriver/drivers/options/#proxy
*/

public class ProxyTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        Proxy proxy = new Proxy();
        String proxyStr = "proxy:port"; // The syntax required to specify a proxy is host:port
        proxy.setHttpProxy(proxyStr); // Specifying the proxy is used for HTTP
        proxy.setSslProxy(proxyStr); // Specifying the proxy is used for HTTPS

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // not mandatory, typically required to accept insecure certificates
        options.setProxy(proxy); // equals to "options.setCapability(CapabilityType.PROXY, proxy);"

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testProxy() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
