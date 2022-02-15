package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage waitUntilPageIsLoaded(WebDriverWait wait, String urlPath) {
        wait.until(ExpectedConditions.urlContains(urlPath));
        return this;
    }
}
