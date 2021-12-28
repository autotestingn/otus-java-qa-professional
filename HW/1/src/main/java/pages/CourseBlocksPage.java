package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CourseBlocksPage extends BasePage {

    private ArrayList<CourseBlock> courseBlocks = new ArrayList<>();

    public CourseBlocksPage(EventFiringWebDriver driver) {
        super(driver);
    }

    private void parseСourseBlocks() {
        courseBlocks.clear();
        List<WebElement> courseBlockWebElems = driver.findElements((By.xpath("//div[@class = 'lessons__new-item-container' and not(descendant::div[contains(text(),'О дате старта будет объявлено позже') or contains(text(), 'В ')])]")));
        for (WebElement courseBlockWebElem : courseBlockWebElems) {
            String name = courseBlockWebElem.findElement((By.xpath(".//div[contains(@class, 'lessons__new-item-title')]"))).getText().trim();
            String startDateText = courseBlockWebElem.findElement((By.xpath(".//div[@class = 'lessons__new-item-start'] | .//div[@class = 'lessons__new-item-courses']//following-sibling::div[@class = 'lessons__new-item-time']"))).getText().trim();
            LocalDate startDate = parseStringToDate(startDateText);
            CourseBlock courseBlock = new CourseBlock(name, startDate);
            courseBlocks.add(courseBlock);
        }
    }

    private boolean isDateWithYear(String date) {
        String nextYear = Integer.toString(LocalDate.now().plusYears(1).getYear());
        return date.contains(nextYear);
    }

    private LocalDate parseStringToDate(String date) {
        String newDate = date.trim();
        String currentYear = Integer.toString(LocalDate.now().getYear());
        boolean hasPrefix =  newDate.contains("С ");
        newDate = hasPrefix ? newDate.substring(2) : newDate;

        if (isDateWithYear(date)) {
            newDate = newDate.substring(0, newDate.indexOf(" года"));
        } else {
            newDate = hasPrefix ? newDate : newDate.substring(0, newDate.indexOf(" ", newDate.indexOf(" ") + 1));
            newDate = newDate.concat(" " + currentYear);
        }

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM uuuu")
                        .withLocale(new Locale("ru"));
        return LocalDate.parse(newDate, dateTimeFormatter);
    }


    private WebElement searchCourseByName(String courseName) {
        WebElement courseWebElement = null;
        try {
            List<WebElement> courseNames = driver.findElements((By.xpath("//div[@class = 'lessons']/a")));
            for (WebElement course : courseNames) {
                if (course.getText().contains(courseName)) {
                    courseWebElement = course;
                    break;
                }
            }
            return courseWebElement;
        } catch (Exception e) {
            return courseWebElement;
        }
    }

    private String searchCourseBySortDate(boolean isMin) {
        parseСourseBlocks();
        CourseBlock courseBlock = courseBlocks.stream().reduce(isMin ? CourseBlock::min : CourseBlock::max).get();
        return courseBlock.getName();
    }

    public void clickCourseWithName(String courseName, Actions actions) {
        if (courseName != null) {
            actions.moveToElement(searchCourseByName(courseName)).keyDown(Keys.CONTROL).sendKeys(Keys.TAB).click().build().perform();
        }
    }

    public void clickCourseWithMaxStartDate(Actions actions) {
        actions.moveToElement(searchCourseByName(searchCourseBySortDate(false))).keyDown(Keys.CONTROL).sendKeys(Keys.TAB).click().build().perform();
    }

    public void clickCourseWithMinStartDate(Actions actions) {
        actions.moveToElement(searchCourseByName(searchCourseBySortDate(true))).keyDown(Keys.CONTROL).sendKeys(Keys.TAB).click().build().perform();
    }
}
