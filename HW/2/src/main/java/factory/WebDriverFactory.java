package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {
    private WebDriver driver;
    public WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                return driver = new ChromeDriver();
            case "Firefox":
                return new FirefoxDriver();
            case "Opera":
                return new OperaDriver();
            default:
                throw new RuntimeException("Incorrect name of browser");
        }
    }
}
