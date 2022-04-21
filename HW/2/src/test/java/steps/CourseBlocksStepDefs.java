package steps;

import components.CourseBlockContainer;
import entities.CourseBlockEntity;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class CourseBlocksStepDefs {
    public WebDriver driver;
    public CourseBlockContainer courseBlock;
    public List<CourseBlockEntity> courseBlockEntities;

    public CourseBlocksStepDefs() {
        driver = Hooks.driver;
    }

    @When("We search course by name {string} and click on it")
    public void weSearchCourseByNameAndClickOnIt(String courseName) {
        courseBlock = new CourseBlockContainer(driver);
        courseBlock.clickCourseWithName(courseName);
    }

    @When("^We search course by date (.+)$")
    public void weSearchCourseByDate(String courseDate) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM uuuu")
                        .withLocale(new Locale("ru"));
        LocalDate date = LocalDate.parse(courseDate, dateTimeFormatter);
        courseBlock = new CourseBlockContainer(driver);
        courseBlockEntities = courseBlock.searchCoursesByDate(date);
    }

    @Then("Course is found after a specified date")
    public void courseIsFoundAfterASpecifiedDate() {
        assertThat(courseBlockEntities, notNullValue());
        courseBlock.printCourseInfo(courseBlockEntities);
    }
}
