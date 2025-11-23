package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {
    private GUIDriver driver;

    public ContactUsPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private final String contactUsEndPoint = "/contact_us";

    // Locators
    private final By contactUsLabel = By.cssSelector(".col-sm-12>.title");
    private final By nameInput = By.cssSelector("[name = \"name\"]");
    private final By emailInput = By.cssSelector("[name = \"email\"]");
    private final By subjectInput = By.cssSelector("[name = \"subject\"]");
    private final By messageInput = By.cssSelector("[name = \"message\"]");
    private final By submitBtn = By.cssSelector("[name = \"submit\"]");
    private final By chooseFileBtn = By.cssSelector("[name = \"upload_file\"]");
    private final By successMessage = By.cssSelector(".contact-form .alert-success");
    private final By homeBtn = By.cssSelector("#form-section> .btn-success");

    // Actions
    @Step("Navigate to Contact Us page")
    public ContactUsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + contactUsEndPoint);
        return this;
    }

    @Step("Fill Contact Us form")
    public ContactUsPage fillContactUsForm(String name, String email, String subject, String message){
        driver.element().type(nameInput, name);
        driver.element().type(emailInput, email);
        driver.element().type(subjectInput, subject);
        driver.element().type(messageInput, message);
        return this;
    }

    @Step("Upload file in Contact Us page")
    public ContactUsPage uploadFileInContactUsPage(String filePath){
       driver.element().uploadFile(chooseFileBtn, filePath, false);
        return this;
    }

    @Step("Click on submit button")
    public ContactUsPage clickSubmitBtn(){
        driver.element().click(submitBtn);
        driver.alert().acceptAlert();
        return this;
    }

    @Step("Click on home button")
    public ContactUsPage clickHomeBtn(){
        driver.element().click(homeBtn);
        return this;
    }

    // Validations

    @Step("Verify Contact Us page")
    public ContactUsPage verifyContactUsPage(){
        driver.verification().isElementVisible(contactUsLabel);
        return this;
    }

    @Step("Verify success message")
    public ContactUsPage verifySuccessMessage(){
        driver.verification().Equals(driver.element().getText(successMessage)
                ,"Success! Your details have been submitted successfully."
                , "Success message is not displayed");
        return this;
    }

}
