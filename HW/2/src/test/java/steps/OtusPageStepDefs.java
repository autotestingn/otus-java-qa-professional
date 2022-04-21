package steps;

import components.HeaderMenu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.OtusPage;

public class OtusPageStepDefs {
    public WebDriver driver;
    public OtusPage otusPage;
    public HeaderMenu headerMenu;

    public OtusPageStepDefs() {
        driver = Hooks.driver;
    }

    @Given("Main page {string} is opened")
    public void mainPageIsOpened(String pageTitle) {
        otusPage = new OtusPage(driver);
        otusPage.open();
        otusPage.pageTitleShouldBe(pageTitle);
    }

    @When("We open menu {string} and choose submenu {string}")
    public void weOpenMenuAndChooseSubmenu(String menuItemName, String submenuItemName) {
        headerMenu = otusPage.getHeaderMenu();
        headerMenu.chooseHeaderSubMenuItem(menuItemName, submenuItemName);
    }
}
