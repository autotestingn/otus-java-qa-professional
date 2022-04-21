package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Finder;
import utils.IFindData;

import java.util.List;

public class HeaderMenu extends BaseComponent<HeaderMenu>{

    @FindBy(xpath = "//div[@class = 'header2-menu header2-menu_main']//div[@class = 'header2-menu__item-wrapper']")
    List<WebElement> headerMenuItems;

    String xpathForSubMenuItem = "./following-sibling::div//a[contains(@class, 'header2-menu__dropdown-link')]";

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public WebElement chooseHeaderMenuItem(String menuItemName) {
        IFindData<WebElement> finder = new Finder<>(headerMenuItems, item -> item.getText().equals(menuItemName));
        WebElement headerMenuItem = finder.searchFirstElement();
        action.moveToElement(headerMenuItem).build().perform();
        return headerMenuItem;
    }

    public WebElement chooseHeaderSubMenuItem(String menuItemName, String submenuItemName) {
        WebElement headerMenuItem = chooseHeaderMenuItem(menuItemName);
        List<WebElement> headerSubMenuItems = headerMenuItem.findElements(By.xpath(xpathForSubMenuItem));
        IFindData<WebElement> finder = new Finder<>(headerSubMenuItems, item -> item.getAttribute("title").equals(submenuItemName));
        WebElement headerSubMenuItem = finder.searchFirstElement();;
        action.moveToElement(headerSubMenuItem).click().build().perform();
        return headerSubMenuItem;
    }
}