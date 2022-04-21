package components;

import entities.TrainingCourseBlockEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Finder;
import utils.IFindData;
import utils.Printer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrainingCourseBlockContainer extends BaseComponent<CourseBlockContainer>{
    private List<TrainingCourseBlockEntity> trainingCourseBlockEntities = new ArrayList<>();
    String xpathForCourse = "//div[@class = 'lessons']/a";
    String xpathForName = ".//div[contains(@class, 'lessons__new-item-title')]";
    String xpathForPrice = ".//div[contains(@class, 'lessons__new-item-price')]";

    public TrainingCourseBlockContainer(WebDriver driver) {
        super(driver);
    }

    private Integer parsePrice(String priceText) {
        String price = priceText.substring(0, priceText.indexOf("₽")).replace(" ", "");
        return Integer.parseInt(price);
    }

    private String getCourseBlockName(WebElement courseBlock) {
        webElementShouldBeVisible(courseBlock);
        return courseBlock.findElement(By.xpath(xpathForName)).getText().trim();
    }

    private String getCourseBlockStartDate(WebElement courseBlock) {
        webElementShouldBeVisible(courseBlock);
        return courseBlock.findElement(By.xpath(xpathForPrice)).getText().trim();
    }
    private void parseTrainingCourseBlocks(List<WebElement> courseTrainingBlocksWebElems) {
        trainingCourseBlockEntities.clear();

        for (WebElement courseTrainingBlocksWebElem : courseTrainingBlocksWebElems) {
            String name = getCourseBlockName(courseTrainingBlocksWebElem);
            String priceText = getCourseBlockStartDate(courseTrainingBlocksWebElem);
            Integer price = parsePrice(priceText);
            TrainingCourseBlockEntity trainingCourseBlockEntity = new TrainingCourseBlockEntity(name, price);
            trainingCourseBlockEntities.add(trainingCourseBlockEntity);
        }
    }

    public TrainingCourseBlockEntity searchCourseWithMaxPrice() {
        List<WebElement> courses = driver.findElements(By.xpath(xpathForCourse));
        parseTrainingCourseBlocks(courses);
        IFindData<TrainingCourseBlockEntity> finder = new Finder<>(trainingCourseBlockEntities, Comparator.comparing(TrainingCourseBlockEntity::getPrice));
        return finder.searchMaxElement();
    }

    public TrainingCourseBlockEntity searchCourseWithMinPrice() {
        List<WebElement> courses = driver.findElements(By.xpath(xpathForCourse));
        parseTrainingCourseBlocks(courses);
        IFindData<TrainingCourseBlockEntity> finder = new Finder<>(trainingCourseBlockEntities, Comparator.comparing(TrainingCourseBlockEntity::getPrice));
        return finder.searchMinElement();
    }

    public void printCourseInfo(List<TrainingCourseBlockEntity> trainingCourseBlockEntities) {
        Printer<TrainingCourseBlockEntity> printer = new Printer<>(trainingCourseBlockEntities, f -> System.out.println("Course name is " + f.getName() + ", course price is " + f.getPrice()));
        printer.print();
    }
}
