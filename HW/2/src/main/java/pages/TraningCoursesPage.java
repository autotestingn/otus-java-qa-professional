package pages;

import org.openqa.selenium.WebDriver;

public class TraningCoursesPage extends BasePage<TraningCoursesPage> {
    private static final String PATH_NAME = "online/";

    public TraningCoursesPage(WebDriver driver) {
        super(driver, PATH_NAME);
    }
}
