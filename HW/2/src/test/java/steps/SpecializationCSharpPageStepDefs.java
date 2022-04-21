package steps;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.CSharpSpecializationPage;

public class SpecializationCSharpPageStepDefs {
    public WebDriver driver;
    public CSharpSpecializationPage cSharpSpecializationPage;

    public SpecializationCSharpPageStepDefs() {
        driver = Hooks.driver;
    }

    @Then("Course's page with title {string} is opened")
    public void coursesPageWithTitleIsOpened(String pageTitle) {
        cSharpSpecializationPage = new CSharpSpecializationPage(driver);
        cSharpSpecializationPage.pageTitleShouldBe(pageTitle);
    }
}
