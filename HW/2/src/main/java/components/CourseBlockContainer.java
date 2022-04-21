package components;

import entities.CourseBlockEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Finder;
import utils.IFindData;
import utils.Printer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CourseBlockContainer extends BaseComponent<CourseBlockContainer> {

    private List<CourseBlockEntity> courseBlocksEntities = new ArrayList<>();

    public CourseBlockContainer(WebDriver driver) {
        super(driver);
    }

    String xpathForName = ".//div[contains(@class, 'lessons__new-item-title')]";

    String xpathForStartDate = ".//div[@class = 'lessons__new-item-start'] | .//div[@class = 'lessons__new-item-courses']//following-sibling::div[@class = 'lessons__new-item-time']";

    private String getCourseBlockName(WebElement courseBlock) {
        webElementShouldBeVisible(courseBlock);
        return courseBlock.findElement(By.xpath(xpathForName)).getText().trim();
    }

    private String getCourseBlockStartDate(WebElement courseBlock) {
        webElementShouldBeVisible(courseBlock);
        return courseBlock.findElement(By.xpath(xpathForStartDate)).getText().trim();
    }

    private boolean isDateWithYear(String date) {
        Pattern pattern = Pattern.compile(".*?((\\d{4})(?:\\s*года)).*");
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    private String parseYear(String date) {
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher matcher = pattern.matcher(date);
        return matcher.find() ? matcher.group(1) : null;
    }

    private LocalDate parseStringToDate(String date) {
        String newDate = null;
        String year;

        Pattern pattern = Pattern.compile("(\\d{1,2}\\s*[а-я]+)");
        Matcher matcher = pattern.matcher(date);

        if (matcher.find()) {
            newDate = matcher.group(1);
        }

        year = isDateWithYear(date) ? parseYear(date) : Integer.toString(LocalDate.now().getYear());
        newDate += " " + year;

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter
                        .ofPattern("dd MMMM uuuu")
                        .withLocale(new Locale("ru"));
        return LocalDate.parse(newDate, dateTimeFormatter);
    }

    private List<WebElement> searchCoursesWithDate() {
        List<WebElement> courseStartDates = driver.findElements((By.xpath(xpathForStartDate)));
        IFindData<WebElement> finder = new Finder<>(courseStartDates, (WebElement courseDate) -> courseDate.getText().matches("([^В].*?\\d{1,2}\\s*[а-я]+)"));
        return finder.searchAllElements().stream().map(course -> course.findElement(By.xpath("./../.."))).collect(Collectors.toList());
    }

    private void parseСourseBlocks(List<WebElement> courseBlockWebElems) {
        courseBlocksEntities.clear();

        for (WebElement courseBlockWebElem : courseBlockWebElems) {
            String name = getCourseBlockName(courseBlockWebElem);
            String startDateText = getCourseBlockStartDate(courseBlockWebElem);
            LocalDate startDate = parseStringToDate(startDateText);
            CourseBlockEntity courseBlockEntity = new CourseBlockEntity(name, startDate);
            courseBlocksEntities.add(courseBlockEntity);
        }
    }

    private WebElement searchCourseByName(String courseName) {
        List<WebElement> courseNames = driver.findElements((By.xpath("//div[@class = 'lessons']/a")));
        return courseNames.stream().filter(c -> c.getText().contains(courseName)).findFirst().get();
    }

    public void clickCourseWithName(String courseName) {
        action.moveToElement(searchCourseByName(courseName)).click().build().perform();
    }

    public List<CourseBlockEntity> searchCoursesByDate(LocalDate courseDate) {
        List<WebElement> courses = searchCoursesWithDate();
        parseСourseBlocks(courses);
        IFindData<CourseBlockEntity> finder = new Finder<>(courseBlocksEntities, d -> d.getStartDate().isAfter(courseDate));
        return finder.searchAllElements();
    }

    public void printCourseInfo(List<CourseBlockEntity> courseBlockEntities) {
        Printer<CourseBlockEntity> printer = new Printer<>(courseBlockEntities, f -> System.out.println("Course name is " + f.getName() + ", course date is " + f.getStartDate()));
        printer.print();
    }
}

