package tests;

import org.testng.annotations.Test;
import pages.CourseBlocksPage;

public class UITest extends BaseTest {
    private final String URL = "https://otus.ru/";

    @Test
    public void MyFirstAutotest() {
        CourseBlocksPage otusPage = new CourseBlocksPage(driver);
        driver.get(URL);
        otusPage.waitUntilPageIsLoaded(wait, URL);
        otusPage.clickCourseWithName("Linux", actions);
        otusPage.clickCourseWithMaxStartDate(actions);
        otusPage.clickCourseWithMinStartDate(actions);
    }
}
