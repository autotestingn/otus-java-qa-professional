package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Waiters;

import static org.testng.Assert.assertNotNull;

public abstract class BaseComponent<T> {
    protected WebDriver driver;
    protected Waiters waiter;
    protected Actions action;

    public BaseComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.waiter = new Waiters(driver);
        this.action = new Actions(driver);
    }

    public T webElementShouldBeVisible(WebElement webElement) {
        assertNotNull(waiter.waitForCondition(ExpectedConditions.visibilityOf(webElement)));
        return (T)this;
    }
}
