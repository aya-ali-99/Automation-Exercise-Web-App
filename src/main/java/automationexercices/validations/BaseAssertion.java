package automationexercices.validations;

import automationexercices.FileUtils;
import automationexercices.utils.WaitManager;
import automationexercices.utils.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementActions;

    protected BaseAssertion() {

    }

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);

    public BaseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element is not visible: " + locator);
    }

    public void isElementNotVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                return false;
            } catch (Exception e) {
                return true;
            }
        });
        assertTrue(flag, "Element is visible: " + locator);
    }

    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

    //verify that file exists
    public void assertFileExists(String fileName, String message) {
        waitManager.fluentWait().until(
                d -> FileUtils.isFileExists(fileName)
        );
        assertTrue(FileUtils.isFileExists(fileName), message);
    }

    // verify HTML5 validation appears for field
    public void hasValidationError(By locator) {
        WebElement element = driver.findElement(locator);
        boolean isValid = (boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", element);
        assertFalse(isValid, "Field should have validation error: " + locator);
    }

    // verify HTML5 validation message appears
    public void validationMessageAppears(By locator) {
        WebElement element = driver.findElement(locator);
        String validationMessage = element.getAttribute("validationMessage");
        boolean hasMessage = validationMessage != null && !validationMessage.isEmpty();
        assertTrue(hasMessage, "Validation message should appear for field: " + locator);
    }
}
