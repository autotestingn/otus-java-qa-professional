package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CustomAction extends Actions {

    public CustomAction(WebDriver driver) {
        super(driver);
    }

    public Actions click(WebDriver driver, WebElement target) {

        try {
            System.out.println("Before click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '3px solid red'", target);
            return super.click(target);
        }
        finally {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '0px solid red'", target);
            System.out.println("After click");
        }
    }
}
