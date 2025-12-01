package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.SignupLoginPage;
import automationexercises.pages.SignupPage;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("User Management")
@Story("Registration")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class RegisterTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();

        // Tests

        @Test
        @Story("Valid User Register")
        @Description("Verify user can sign up with valid data")
        @Severity(SeverityLevel.CRITICAL)
        public void validSignUpTC() {
                new SignupLoginPage(driver).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
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
                                testData.getJsonData("signupEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("signupPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Register")
        @Description("Verify user can't sign up with existing email")
        @Severity(SeverityLevel.CRITICAL)
        public void verifyErrorMsgWhenAccountCreatedBefore() {
                // Precondition: Create a user account
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("signupName"),
                                (testData.getJsonData("signupEmail") + timeStamp + "@gmail.com"),
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
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
                                .clickSignupButton()
                                .verifyRegisterErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("signupEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("signupPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Register")
        @Description("Verify user can't sign up with empty email")
        @Severity(SeverityLevel.CRITICAL)
        public void invalidSignUpWithEmptyEmailTC() {
                new SignupLoginPage(driver).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("InvalidData.emptyEmail"))
                                .clickSignupButton()
                                .verifyEmailFieldValidationMessageAppears();
        }

        @Test
        @Story("Invalid User Register")
        @Description("Verify user can't sign up with empty name")
        @Severity(SeverityLevel.CRITICAL)
        public void invalidSignUpWithEmptyNameTC() {
                new SignupLoginPage(driver).navigate()
                                .enterSignupName(testData.getJsonData("InvalidData.emptyName"))
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
                                .clickSignupButton()
                                .verifyNameFieldValidationMessageAppears();
        }

        @Test
        @Story("Invalid User Register")
        @Description("Verify user can't sign up with wrong email")
        @Severity(SeverityLevel.CRITICAL)
        public void invalidSignUpWithWrongEmailTC() {
                new SignupLoginPage(driver).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("InvalidData.wrongEmail"))
                                .clickSignupButton()
                                .verifyEmailFieldValidationMessageAppears();
        }

        // Configurations
        @BeforeClass
        public void preCondition() {
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
