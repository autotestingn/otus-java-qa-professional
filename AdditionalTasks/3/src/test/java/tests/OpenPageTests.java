package tests;

import org.testng.annotations.Test;

public class OpenPageTests extends BaseTest {

    @Test
    public void openGooglePageTest() {
        driver.get("https://www.google.ru/");
    }

    @Test
    public void openYandexPageTest() {
        driver.get("https://yandex.ru/");
    }

}
