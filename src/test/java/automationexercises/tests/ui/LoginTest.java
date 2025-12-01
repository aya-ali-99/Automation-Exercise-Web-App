package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.SignupLoginPage;
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
@Story("Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class LoginTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();

        // Tests

        @Test
        @Story("Valid User Login")
        @Description("Verify user can login with valid data")
        @Severity(SeverityLevel.CRITICAL)
        public void validLoginTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(driver).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("loginName"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Login")
        @Description("Verify user can't login with invalid email")
        @Severity(SeverityLevel.CRITICAL)
        public void inValidLoginUsingInvalidEmailTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(driver).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton()
                                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Login")
        @Description("Verify user can't login with invalid password")
        @Severity(SeverityLevel.CRITICAL)
        public void inValidLoginUsingInvalidPasswordTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(driver).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword") + timeStamp)
                                .clickLoginButton()
                                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("User Logout")
        @Description("Verify user can logout")
        @Severity(SeverityLevel.CRITICAL)
        public void logoutTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();

                new SignupLoginPage(driver).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("loginName"))
                                .clickOnLogoutButton().navigationBar
                                .verifyLogoutButtonNotVisible()
                                .verifySignupLabelVisible();

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();

        }

        // Configurations
        @BeforeClass
        public void preCondition() {
                testData = new JsonReader("login-data");
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
