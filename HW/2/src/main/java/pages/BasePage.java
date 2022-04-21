package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.Waiters;

public abstract class BasePage<T> {
    protected WebDriver driver;
    protected Waiters waiter;
    protected Actions action;

    private String pathName;
    private static final String HOSTNAME = System.getProperty("webdriver.base.url");

    public BasePage(WebDriver driver, String pathName) {
        this.driver = driver;
        this.pathName = pathName;
        this.waiter = new Waiters(driver);
        this.action = new Actions(driver);
    }

    public T open() {
        driver.get(HOSTNAME + (T)this.pathName);
        System.out.println(this.getClass().getCanonicalName());
        return (T)this;
    }

    public T pageTitleShouldBe(String pageTitle) {
        assert(driver.getTitle().equals(pageTitle));
        return (T)this;
    }
}
