package pages;

import org.openqa.selenium.WebDriver;

public class OtusPage extends BasePage {

    private HeaderMenu headerMenu;

    public OtusPage(WebDriver driver) {
        super(driver);
        headerMenu = new HeaderMenu(driver);
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
}
