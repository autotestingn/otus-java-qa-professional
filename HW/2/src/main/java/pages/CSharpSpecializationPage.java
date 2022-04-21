package pages;

import org.openqa.selenium.WebDriver;

public class CSharpSpecializationPage extends BasePage<CSharpSpecializationPage> {
    public static final String pathName = "lessons/c-sharp-specialization/";

    public CSharpSpecializationPage(WebDriver driver) {
        super(driver, pathName);
    }
}
