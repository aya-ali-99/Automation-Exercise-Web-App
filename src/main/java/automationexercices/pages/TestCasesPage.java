package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.utils.dataReader.PropertyReader;
import org.openqa.selenium.By;

public class TestCasesPage {
    private GUIDriver driver;
    public TestCasesPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private final String testCasesEndPoint = "/test_cases";

    // Locators
    private final By testCasesLabel = By.cssSelector(".col-sm-9>h2>b");

    // Actions
    public TestCasesPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + testCasesEndPoint);
        return this;
    }

    // Validations
    public TestCasesPage verifyTestCasesPage(){
        driver.verification()
                .Equals(driver.element().getText(testCasesLabel),
                        "TEST CASES",
                        "Test Cases page is not displayed");
        return this;
    }

}
