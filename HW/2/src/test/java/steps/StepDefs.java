package steps;

import enums.DriverType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class StepDefs {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public WebDriverFactory webDriverFactory;
    public static final String MAIN_PAGE_URL = "https://otus.ru/";
    public static final String TRAINING_COURSES_PAGE_URL = "https://otus.ru/online/";
    public static final String CSHARP_SPECIALIZATION_PAGE_URL ="https://otus.ru/lessons/c-sharp-specialization/";
    public OtusPage otusPage;
    public TrainingCourseBlock trainingCourseBlock;
    public CourseBlocksPage courseBlocksPage;
    public TraningCoursesPage trainingCoursePage;
    public List<CourseBlock> courseBlocks;

    @BeforeAll
    @Given("We can choose any browser")
    public static void weCanChooseAnyBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @Before("not @browser")
    @When("We choose Chrome browser")
    public void weChooseChromeBrowser() {
        webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getWebDriver(DriverType.CHROME);
        setUpDriverSession();
    }

    @After()
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

    @Then("Chrome browser is opened")
    public void chromeBrowserIsOpened() {
        assertThat(driver, notNullValue());
    }

    @Given("We are on page with all courses")
    public void weAreOnPageWithAllCourses() {
        otusPage = new OtusPage(driver);
        driver.get(MAIN_PAGE_URL);
        otusPage.waitUntilPageIsLoaded(wait, MAIN_PAGE_URL);
    }

    @When("We search course by name {string} and click on it")
    public void weSearchCourseByNameAndClickOnIt(String courseName) {
        courseBlocksPage = new CourseBlocksPage(driver);
        courseBlocksPage.clickCourseWithName(courseName, actions);
    }

    @Then("Course's site is opened")
    public void coursesSiteIsOpened() {
        courseBlocksPage.waitUntilPageIsLoaded(wait, CSHARP_SPECIALIZATION_PAGE_URL);
        assertThat(driver.getCurrentUrl(), startsWith(CSHARP_SPECIALIZATION_PAGE_URL));
    }

    @When("^We search course by date (.+)$")
    public void weSearchCourseByDate(String courseDate) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM uuuu")
                        .withLocale(new Locale("ru"));
        LocalDate date = LocalDate.parse(courseDate, dateTimeFormatter);
        courseBlocksPage = new CourseBlocksPage(driver);
        courseBlocks = courseBlocksPage.searchCoursesByDate(date);
    }

    @Then("Course is found after a specified date")
    public void courseIsFoundAfterASpecifiedDate() {
        assertThat(courseBlocks, notNullValue());
    }

    @When("We open menu {string} and choose submenu {string}")
    public void weOpenMenuAndChooseSubmenu(String menuItemName, String submenuItemName) {
        HeaderMenu headerMenu = otusPage.getHeaderMenu();
        headerMenu.chooseHeaderSubMenuItem(menuItemName, submenuItemName);
    }

    @Then("Page with training courses is opened")
    public void pageWithTrainingCoursesIsOpened() {
        assertThat(driver.getCurrentUrl(), startsWith(TRAINING_COURSES_PAGE_URL));
    }

    @When("We search course by minimum price")
    public void weSearchCourseByMinimumPrice() {
        trainingCoursePage = new TraningCoursesPage(driver);
        trainingCourseBlock = trainingCoursePage.searchCourseWithMinPrice();
    }

    @Then("Courses are found by minimum price")
    public void coursesAreFoundByMinimumPrice() {
        assertThat(trainingCourseBlock, notNullValue());
    }

    @When("We search course by maximum price")
    public void weSearchCourseByMaximumPrice() {
        trainingCoursePage = new TraningCoursesPage(driver);
        trainingCourseBlock = trainingCoursePage.searchCourseWithMaxPrice();
    }

    @Then("Courses are found by maximum price")
    public void coursesAreFoundByMaximumPrice() {
        {
            assertThat(trainingCourseBlock, notNullValue());
        }
    }
}
