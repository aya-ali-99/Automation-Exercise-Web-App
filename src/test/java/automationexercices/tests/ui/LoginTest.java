package automationexercices.tests.ui;

import api.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.SignupLoginPage;
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
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class LoginTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests
    @Description("Verify user can login with valid data")
    @Test
    public void validLoginTC(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("loginName"),
                testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                testData.getJsonData("loginPassword"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"))
                .verifyUserIsCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("loginPassword"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("loginName"));

        new UserManagementAPI().deleteUserAccount(
                testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                testData.getJsonData("loginPassword"))
                .verifyUserIsDeletedSuccessfully();
    }

    @Description("Verify user can't login with invalid email")
    @Test
    public void inValidLoginUsingInvalidEmailTC(){
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("loginName"),
                        testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                        testData.getJsonData("loginPassword"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserIsCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("loginEmail")+ "@gmail.com")
                .enterLoginPassword(testData.getJsonData("loginPassword"))
                .clickLoginButton()
                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                        testData.getJsonData("loginPassword"))
                .verifyUserIsDeletedSuccessfully();
    }

    @Description("Verify user can't login with invalid password")
    @Test
    public void inValidLoginUsingInvalidPasswordTC(){
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("loginName"),
                        testData.getJsonData("loginEmail")+ timeStamp +"@gmail.com",
                        testData.getJsonData("loginPassword"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserIsCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("loginEmail")+ "@gmail.com")
                .enterLoginPassword(testData.getJsonData("loginPassword") + timeStamp)
                .clickLoginButton()
                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("loginEmail")+ timeStamp+"@gmail.com",
                        testData.getJsonData("loginPassword"))
                .verifyUserIsDeletedSuccessfully();
    }


    // Configurations
    @BeforeClass
    public void preCondition(){
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
