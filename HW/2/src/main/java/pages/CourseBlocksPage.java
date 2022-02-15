package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CourseBlocksPage extends BasePage {

    private ArrayList<CourseBlock> courseBlocks = new ArrayList<>();

    public CourseBlocksPage(WebDriver driver) {
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
            List<WebElement> courseNames = driver.findElements((By.xpath("//div[@class = 'lessons']/a")));
            return courseNames.stream().filter(c -> c.getText().contains(courseName)).findFirst().get();
    }

    public void clickCourseWithName(String courseName, Actions actions) {
        actions.moveToElement(searchCourseByName(courseName)).click().build().perform();
    }

    public List<CourseBlock> searchCoursesByDate(LocalDate courseDate) {
        parseСourseBlocks();
        List<CourseBlock> foundCourseBlocks = courseBlocks.stream().filter(d -> d.getStartDate().isAfter(courseDate)).collect(Collectors.toList());
        foundCourseBlocks.stream().forEach(f -> System.out.println("Course name is " + f.getName() + ", course date is " + f.getStartDate()));
        return foundCourseBlocks;
    }
}

