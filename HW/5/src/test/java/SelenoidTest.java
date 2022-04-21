import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SelenoidTest {


    WebDriver driver;

   @Before
    public void setup() {
        System.out.println("Test fixtures");
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", Boolean.parseBoolean(System.getProperty("selenoidEnableVnc")));
       //selenoidOptions.put("enableVNC", true);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", System.getProperty("browserName"));
        capabilities.setCapability("browserVersion", System.getProperty("browserVersion"));
        capabilities.setCapability("selenoid:options", selenoidOptions);
        try {
            driver = new RemoteWebDriver(
                    URI.create("http://127.0.0.1:4445/wd/hub").toURL(),
                    capabilities
            );
        } catch (Exception ignored) {

        }
    }
    @After
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void testConnectToSelenoid() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid2() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid3() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }
    @Test
    public void testConnectToSelenoid4() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid5() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid6() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid7() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @Test
    public void testConnectToSelenoid8() throws InterruptedException {
        driver.get("https://google.com");
    }

    public void testConnectToSelenoid9() throws InterruptedException {
        driver.get("https://google.com");
    }

    @Test
    public void testConnectToSelenoid10() throws InterruptedException {
        driver.get("https://google.com");
    }
}


