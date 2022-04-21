package steps;

import factory.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class Hooks {

    public static WebDriver driver;
    public static WebDriverFactory webDriverFactory;

    @Before("not @browser")
    @Given("We can choose any browser")
    public void weCanChooseAnyBrowser() {
        webDriverFactory = new WebDriverFactory();
        System.out.println("WB");
    }

    @Before("not @browser")
    @When("We choose Chrome browser")
    public void weChooseChromeBrowser() {
        driver = webDriverFactory.getWebDriver("Chrome");
        setUpDriverSession();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void setUpDriverSession() {
        driver.manage().window().maximize();

    }

    @Before("not @browser")
    @Then("Chrome browser is opened")
    public void chromeBrowserIsOpened() {
        assertThat(driver, notNullValue());
    }
}

