package automationexercises.pages.components;

import automationexercises.drivers.GUIDriver;
import automationexercises.pages.*;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productsButton = By.cssSelector("a[href = \"/products\"]");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By signupLoginButton = By.xpath("//a[.=' Signup / Login']");
    private final By logoutButton = By.xpath("//a[.=' Logout']");
    private final By testCasesButton = By.xpath("//a[.=' Test Cases']");
    private final By deleteAccountButton = By.xpath("//a[.=' Delete Account']");
    private final By apiButton = By.xpath("//a[.=' API Testing']");
    private final By contactUsButton = By.xpath("//a[.=' Contact us']");
    private final By videoTutorialsButton = By.xpath("//a[.=' Video Tutorials']");
    private final By homePageLabel = By.cssSelector("h1 > span");
    private final By userLabel = By.tagName("b");

    // Actions
    @Step("Navigate to the Home Page")
    public NavigationBarComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton(){
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on Products Button")
    public ProductsPage clickOnProductsButton(){
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Logout Button")
    public SignupLoginPage clickOnLogoutButton(){
        driver.element().click(logoutButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton(){
        driver.element().click(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton(){
        driver.element().click(testCasesButton);
        return new TestCasesPage(driver);
    }

    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton(){
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on ContactUs Button")
    public ContactUsPage clickOnContactUsButton(){
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }

    // Validations
    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePage(){
        driver.verification().isElementVisible(homePageLabel);
        return this;
    }

    @Step("Verify User is Logged in")
    public NavigationBarComponent verifyUserLabel(String expectedUser){
        String actualName = driver.element().getText(userLabel);
        LogsManager.info("Verifying user label: " + actualName);
        driver.verification().Equals(actualName, expectedUser, "User label does not match. Expected: " + expectedUser + ", Actual:"+ actualName);
        return this;
    }

    @Step("Verify that user is logged out into Login Page")
    public SignupLoginPage verifyLogoutButtonNotVisible() {
        driver.verification().isElementNotVisible(logoutButton);
        return new SignupLoginPage(driver);
    }
}
