package automationexercises.pages;

import automationexercises.drivers.GUIDriver;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    public NavigationBarComponent navigationBar;
    private GUIDriver driver;
    private final String signupLoginEndPoint = "/login";
    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }

    // Locators
    private final By loginEmail = By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginPassword = By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginButton = By.cssSelector("[data-qa=\"login-button\"]");
    private final By signupName = By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signupEmail = By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signupButton = By.cssSelector("[data-qa=\"signup-button\"]");

    private final By signupLabel = By.cssSelector(".signup-form > h2");
    private final By loginError = By.cssSelector(".login-form  p");
    private final By registerError = By.cssSelector(".signup-form p");

    // Actions
    @Step("Navigate to Register/Login page")
    public SignupLoginPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + signupLoginEndPoint);
        return this;
    }


    @Step("Enter email {email} in login field")
    public SignupLoginPage enterLoginEmail(String email){
        driver.element().type(loginEmail, email);
        return this;
    }

    @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password){
        driver.element().type(loginPassword, password);
        return this;
    }

    @Step("Click login button")
    public SignupLoginPage clickLoginButton(){
        driver.element().click(loginButton);
        return this;
    }

    @Step("Enter name {name} in signup field")
    public SignupLoginPage enterSignupName(String name){
        driver.element().type(signupName, name);
        return this;
    }

    @Step("Enter email {email} in signup field")
    public SignupLoginPage enterSignupEmail(String email){
        driver.element().type(signupEmail, email);
        return this;
    }

    @Step("Click signup button")
    public SignupLoginPage clickSignupButton(){
        driver.element().click(signupButton);
        return this;
    }

    // Validations
    @Step("Verify signup label is visible")
    public SignupLoginPage verifySignupLabelVisible(){
        driver.verification().isElementVisible(signupLabel);
        return this;
    }

    @Step("Verify login error message is {errorExpected}")
    public SignupLoginPage verifyLoginErrorMessage(String errorExpected){
        String errorActual = driver.element().getText(loginError);
        driver.verification().Equals(errorActual, errorExpected, "Login error message is not as expected");
        return this;
    }

    @Step("Verify register error message is {errorExpected}")
    public SignupLoginPage verifyRegisterErrorMessage(String errorExpected){
        String errorActual = driver.element().getText(registerError);
        driver.verification().Equals(errorActual, errorExpected, "Register error message is not as expected");
        return this;
    }

    @Step("Verify Email field validation message appears")
    public SignupLoginPage verifyEmailFieldValidationMessageAppears(){
        driver.verification().validationMessageAppears(signupEmail);
        return this;
    }

    @Step("Verify Name field validation message appears")
    public SignupLoginPage verifyNameFieldValidationMessageAppears(){
        driver.verification().validationMessageAppears(signupName);
        return this;
    }

}
