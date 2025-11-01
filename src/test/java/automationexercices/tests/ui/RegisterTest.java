package automationexercices.tests.ui;

import api.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.SignupLoginPage;
import automationexercices.pages.SignupPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise ")
@Feature("UI User Management")
@Story("User Register")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class RegisterTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests
    @Description("Verify user can sign up with valid data")
    @Test
    public void validSignUpTC(){
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("signupName"))
                .enterSignupEmail(testData.getJsonData("signupEmail")+ timeStamp+"@gmail.com")
                .clickSignupButton();
        new SignupPage(driver)
                .enterPassword(testData.getJsonData("signupPassword"))
                .selectBirtDate(testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"))
                .selectNewsletter()
                .selectSpecialOffers()
                .selectTitle(testData.getJsonData("titleFemale"))
                .enterFirstName(testData.getJsonData("firstName"))
                .enterLastName(testData.getJsonData("lastName"))
                .enterCompany(testData.getJsonData("company"))
                .enterAddress1(testData.getJsonData("address1"))
                .enterAddress2(testData.getJsonData("address2"))
                .selectCountry(testData.getJsonData("country"))
                .enterState(testData.getJsonData("state"))
                .enterCity(testData.getJsonData("city"))
                .enterZipcode(testData.getJsonData("zipcode"))
                .enterMobileNumber(testData.getJsonData("mobileNumber"))
                .clickCreateAccountButton()
                .verifyAccountCreatedLabel();

        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                        testData.getJsonData("loginPassword"))
                .verifyUserIsDeletedSuccessfully();
    }

    @Description("Verify user can't sign up with existing email")
    @Test
    public void verifyErrorMsgWhenAccountCreatedBefore(){
        // Precondition: Create a user account
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("signupName"),
                        (testData.getJsonData("signupEmail")+ timeStamp+"@gmail.com"),
                        testData.getJsonData("signupPassword"),
                        testData.getJsonData("titleFemale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("mobileNumber"))
                .verifyUserIsCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("signupName"))
                .enterSignupEmail(testData.getJsonData("signupEmail")+ timeStamp+"@gmail.com")
                .clickSignupButton()
                .verifyRegisterErrorMessage(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                        testData.getJsonData("loginPassword"))
                .verifyUserIsDeletedSuccessfully();
    }

    // Configurations
    @BeforeClass
    public void preCondition(){
        testData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void setup() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
