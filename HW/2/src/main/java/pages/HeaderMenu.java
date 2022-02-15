package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HeaderMenu {
    private WebDriver driver;

    public HeaderMenu(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement chooseHeaderMenuItem(String menuItemName) {
        List<WebElement> headerMenuItems = driver.findElements(By.xpath("//div[@class = 'header2-menu header2-menu_main']//div[@class = 'header2-menu__item-wrapper']"));
        WebElement headerMenuItem = headerMenuItems.stream().filter(item -> item.getText().equals(menuItemName)).findFirst().orElse(null);;
        Actions actions = new Actions(driver);
        actions.moveToElement(headerMenuItem).build().perform();
        return headerMenuItem;
    }

    public void chooseHeaderSubMenuItem(String menuItemName, String submenuItemName) {
        WebElement headerMenuItem = chooseHeaderMenuItem(menuItemName);
        List<WebElement> headerSubMenuItems = headerMenuItem.findElements(By.xpath("./following-sibling::div//a[contains(@class, 'header2-menu__dropdown-link')]"));
        WebElement headerSubMenuItem = headerSubMenuItems.stream().filter(item -> item.getAttribute("title").equals(submenuItemName)).findFirst().orElse(null);;
        Actions actions = new Actions(driver);
        actions.moveToElement(headerSubMenuItem).click().build().perform();
    }
}