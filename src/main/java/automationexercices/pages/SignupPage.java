package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private GUIDriver driver;

    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By titleMr = By.id("uniform-id_gender1");
    private final By titleMrs = By.id("uniform-id_gender2");
    private final By name = By.cssSelector("[data-qa=\"name\"]");
    private final By email = By.cssSelector("[data-qa=\"email\"]");
    private final By password = By.id("password");
    // Birthdate
    private final By day = By.id("days");
    private final By month = By.id("months");
    private final By year = By.id("years");
    // Checkboxes
    private final By newsletter = By.id("newsletter");
    private final By specialOffers = By.id("optin");
    // Personal Information
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa=\"create-account\"]");
    private final By accountCreatedLabel = By.tagName("p");
    private final By continueButton = By.cssSelector("[data-qa=\"continue-button\"]");

    // Actions
    @Step("Select title {title}")
    public SignupPage selectTitle(String title){
        switch(title.toLowerCase()){
            case "mr":
                driver.element().click(titleMr);
                break;
            case "mrs":
                driver.element().click(titleMrs);
                break;
            default:
                throw new IllegalArgumentException("Invalid title: " + title);
        }
        return this;
    }

    @Step("Enter name {username}")
    public SignupPage enterName(String username){
        driver.element().type(name, username);
        return this;
    }

    @Step("Enter email {userEmail}")
    public SignupPage enterEmail(String userEmail){
        driver.element().type(email, userEmail);
        return this;
    }

    @Step("Enter password {userPassword}")
    public SignupPage enterPassword(String userPassword){
        driver.element().type(password, userPassword);
        return this;
    }

    @Step("Select Birthdate {birthDay} | {birthMonth} | {birthYear}")
    public SignupPage selectBirtDate(String birthDay, String birthMonth, String birthYear){
        driver.element().selectFromDropdown(day, birthDay);
        driver.element().selectFromDropdown(month, birthMonth);
        driver.element().selectFromDropdown(year, birthYear);
        return this;
    }

    @Step("Select Newsletter")
    public SignupPage selectNewsletter(){
        driver.element().click(newsletter);
        return this;
    }

    @Step("Select Special Offers")
    public SignupPage selectSpecialOffers(){
        driver.element().click(specialOffers);
        return this;
    }

    @Step("Enter First Name {firstName}")
    public SignupPage enterFirstName(String firstName){
        driver.element().type(this.firstName, firstName);
        return this;
    }

    @Step("Enter Last Name {lastName}")
    public SignupPage enterLastName(String lastName){
        driver.element().type(this.lastName, lastName);
        return this;
    }

    @Step("Enter Company {company}")
    public SignupPage enterCompany(String company){
        driver.element().type(this.company, company);
        return this;
    }

    @Step("Enter Address 1 {address1}")
    public SignupPage enterAddress1(String address1){
        driver.element().type(this.address1, address1);
        return this;
    }

    @Step("Enter Address 2 {address2}")
    public SignupPage enterAddress2(String address2){
        driver.element().type(this.address2, address2);
        return this;
    }

    @Step("Select Country {country}")
    public SignupPage selectCountry(String country){
        driver.element().selectFromDropdown(this.country, country);
        return this;
    }

    @Step("Enter State {state}")
    public SignupPage enterState(String state){
        driver.element().type(this.state, state);
        return this;
    }

    @Step("Enter City {city}")
    public SignupPage enterCity(String city){
        driver.element().type(this.city, city);
        return this;
    }

    @Step("Enter Zipcode {zipcode}")
    public SignupPage enterZipcode(String zipcode){
        driver.element().type(this.zipcode, zipcode);
        return this;
    }

    @Step("Enter Mobile Number {mobileNumber}")
    public SignupPage enterMobileNumber(String mobileNumber){
        driver.element().type(this.mobileNumber, mobileNumber);
        return this;
    }

    @Step("Click Create Account Button")
    public SignupPage clickCreateAccountButton(){
        driver.element().click(createAccountButton);
        return this;
    }

    @Step("Click Continue Button")
    public NavigationBarComponent clickContinueButton(){
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }


    // Validations
    @Step("Verify Account Created Label is visible")
    public SignupPage verifyAccountCreatedLabel(){
        driver.verification().isElementVisible(accountCreatedLabel);
        return this;
    }
}
