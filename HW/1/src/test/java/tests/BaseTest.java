package tests;

import enums.DriverType;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.MarkClickListener;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    @BeforeClass
    public static void setupWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeTest() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = new EventFiringWebDriver(webDriverFactory.getWebDriver(DriverType.CHROME));
        driver.register(new MarkClickListener());
        setUpDriverSession();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void setUpDriverSession() {
        driver.manage().window().maximize();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 5);
    }
}
