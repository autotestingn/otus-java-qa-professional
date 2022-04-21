package pages;

import components.HeaderMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OtusPage extends BasePage<OtusPage> {
    private static final String PATH_NAME = "";
    private HeaderMenu headerMenu;

    public OtusPage(WebDriver driver) {
        super(driver, PATH_NAME);
        headerMenu = new HeaderMenu(driver);
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
}
