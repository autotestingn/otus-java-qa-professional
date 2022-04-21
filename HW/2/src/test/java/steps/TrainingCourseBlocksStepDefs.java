package steps;

import components.TrainingCourseBlockContainer;
import entities.TrainingCourseBlockEntity;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.TraningCoursesPage;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class TrainingCourseBlocksStepDefs {
    public WebDriver driver;
    public TrainingCourseBlockEntity trainingCourseBlockEntity;
    public TrainingCourseBlockContainer trainingCourseBlock;

    public TrainingCourseBlocksStepDefs() {
        driver = Hooks.driver;
    }

    @When("We search course by minimum price")
    public void weSearchCourseByMinimumPrice() {
        trainingCourseBlock = new TrainingCourseBlockContainer(driver);
        trainingCourseBlockEntity = trainingCourseBlock.searchCourseWithMinPrice();
    }

    @Then("Courses are found by minimum price")
    public void coursesAreFoundByMinimumPrice() {
        assertThat(trainingCourseBlock, notNullValue());
        trainingCourseBlock.printCourseInfo(Collections.singletonList(trainingCourseBlockEntity));
    }

    @When("We search course by maximum price")
    public void weSearchCourseByMaxPrice() {
        trainingCourseBlock = new TrainingCourseBlockContainer(driver);
        trainingCourseBlockEntity = trainingCourseBlock.searchCourseWithMaxPrice();
    }

    @Then("Courses are found by maximum price")
    public void coursesAreFoundByMaximumPrice() {
        assertThat(trainingCourseBlock, notNullValue());
        trainingCourseBlock.printCourseInfo(Collections.singletonList(trainingCourseBlockEntity));
    }
}
