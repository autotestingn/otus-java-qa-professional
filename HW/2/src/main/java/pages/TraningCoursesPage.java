package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TraningCoursesPage extends BasePage {
    private ArrayList<TrainingCourseBlock> trainingCourseBlocks = new ArrayList<>();

    public TraningCoursesPage(WebDriver driver) {
        super(driver);
    }

    private Integer parsePrice(String priceText) {
        String price = priceText.substring(0, priceText.indexOf("₽")).replace(" ", "");
        return Integer.parseInt(price);
    }

    private void parseTrainingCourseBlocks() {
        trainingCourseBlocks.clear();
        List<WebElement> courseTrainingBlocksWebElems =
                driver.findElements(By.xpath("//div[@class = 'lessons']/a"));
        for (WebElement courseTrainingBlocksWebElem : courseTrainingBlocksWebElems) {
            String name = courseTrainingBlocksWebElem.findElement(By.xpath(".//div[contains(@class, 'lessons__new-item-title')]")).getText().trim();
            String priceText = courseTrainingBlocksWebElem.findElement(By.xpath(".//div[contains(@class, 'lessons__new-item-price')]")).getText().trim();
            Integer price = parsePrice(priceText);
            TrainingCourseBlock trainingCourseBlock = new TrainingCourseBlock(name, price);
            trainingCourseBlocks.add(trainingCourseBlock);
        }
    }

    public static TrainingCourseBlock max(ArrayList<TrainingCourseBlock> trainingCourseBlocks) {
        Comparator<TrainingCourseBlock> comparator = Comparator.comparing(TrainingCourseBlock::getPrice);
        TrainingCourseBlock TrainingCourseBlockWithMaxPrice = trainingCourseBlocks.stream().max(comparator).get();
        return TrainingCourseBlockWithMaxPrice;
    }

    public static TrainingCourseBlock min(ArrayList<TrainingCourseBlock> trainingCourseBlocks) {
        Comparator<TrainingCourseBlock> comparator = Comparator.comparing(TrainingCourseBlock::getPrice);
        TrainingCourseBlock TrainingCourseBlockWithMaxPrice = trainingCourseBlocks.stream().min(comparator).get();
        return TrainingCourseBlockWithMaxPrice;
    }

    public TrainingCourseBlock searchCourseWithMaxPrice() {
        parseTrainingCourseBlocks();
        TrainingCourseBlock trainingCourseBlock = max(trainingCourseBlocks);
        System.out.println("Course with name " + trainingCourseBlock.getName() + " is course with max price " + trainingCourseBlock.getPrice());
        return trainingCourseBlock;
    }

    public TrainingCourseBlock searchCourseWithMinPrice() {
        parseTrainingCourseBlocks();
        TrainingCourseBlock trainingCourseBlock = min(trainingCourseBlocks);
        System.out.println("Course with name " + trainingCourseBlock.getName() + " is course with min price " + trainingCourseBlock.getPrice());
        return trainingCourseBlock;
    }
}
