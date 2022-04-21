package steps;

import components.TrainingCourseBlockContainer;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.TraningCoursesPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class TrainingCoursesPageDefs {
    public WebDriver driver;
    public TraningCoursesPage traningCoursesPage;

    public TrainingCoursesPageDefs() {
        driver = Hooks.driver;
    }

    @Then("Page {string} is opened")
    public void pageIsOpened(String pageTitle) {
        traningCoursesPage = new TraningCoursesPage(driver);
        traningCoursesPage.pageTitleShouldBe(pageTitle);
    }
}
