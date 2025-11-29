package automationexercises.tests;

import automationexercises.drivers.GUIDriver;
import automationexercises.drivers.WebDriverProvider;
import automationexercises.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;





    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}