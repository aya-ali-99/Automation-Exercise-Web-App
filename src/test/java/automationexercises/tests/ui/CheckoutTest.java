package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.*;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Automation Exercise")
@Feature("Checkout Management")
@Story("Checkout Flow")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CheckoutTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();

        // Tests

        @Test
        @Story("Checkout Flow")
        @Description("Verify user can register with valid data")
        @Severity(SeverityLevel.CRITICAL)
        public void registerNewAccountTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("name"),
                                (testData.getJsonData("email") + timeStamp + "@gmail.com"),
                                testData.getJsonData("password"),
                                testData.getJsonData("titleFemale"),
                                testData.getJsonData("day"),
                                testData.getJsonData("month"),
                                testData.getJsonData("year"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"),
                                testData.getJsonData("companyName"),
                                testData.getJsonData("address1"),
                                testData.getJsonData("address2"),
                                testData.getJsonData("country"),
                                testData.getJsonData("zipcode"),
                                testData.getJsonData("state"),
                                testData.getJsonData("city"),
                                testData.getJsonData("mobileNumber"))
                                .verifyUserIsCreatedSuccessfully();
        }

        @Test(dependsOnMethods = { "registerNewAccountTC" })
        @Story("Checkout Flow")
        @Description("Verify user can login with valid data")
        @Severity(SeverityLevel.CRITICAL)
        public void loginToAccountTC() {
                new SignupLoginPage(driver).navigate()
                                .enterLoginEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("password"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("name"));
        }

        @Test(dependsOnMethods = { "loginToAccountTC", "registerNewAccountTC" })
        @Story("Checkout Flow")
        @Description("Verify user can add product to cart")
        @Severity(SeverityLevel.CRITICAL)
        public void addProductToCartTC() {
                new ProductsPage(driver)
                                .navigate()
                                .clickOnAddToCart(testData.getJsonData("product.name"))
                                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                                .clickOnViewCart()
                                .verifyProductDetailsInCart(
                                                testData.getJsonData("product.name"),
                                                testData.getJsonData("product.price"),
                                                testData.getJsonData("product.quantity"),
                                                testData.getJsonData("product.total"));
        }

        @Test(dependsOnMethods = { "addProductToCartTC", "loginToAccountTC",
                        "registerNewAccountTC" })
        @Story("Checkout Flow")
        @Description("Verify user can checkout with login")
        @Severity(SeverityLevel.CRITICAL)
        public void checkoutTC() {
                new CartPage(driver)
                                .navigate()
                                .clickOnProceedToCheckoutButton()
                                .verifyDeliveryAddress(
                                                testData.getJsonData("titleFemale"),
                                                testData.getJsonData("firstName"),
                                                testData.getJsonData("lastName"),
                                                testData.getJsonData("companyName"),
                                                testData.getJsonData("address1"),
                                                testData.getJsonData("address2"),
                                                testData.getJsonData("city"),
                                                testData.getJsonData("state"),
                                                testData.getJsonData("zipcode"),
                                                testData.getJsonData("country"),
                                                testData.getJsonData("mobileNumber"))
                                .verifyBillingAddress(
                                                testData.getJsonData("titleFemale"),
                                                testData.getJsonData("firstName"),
                                                testData.getJsonData("lastName"),
                                                testData.getJsonData("companyName"),
                                                testData.getJsonData("address1"),
                                                testData.getJsonData("address2"),
                                                testData.getJsonData("city"),
                                                testData.getJsonData("state"),
                                                testData.getJsonData("zipcode"),
                                                testData.getJsonData("country"),
                                                testData.getJsonData("mobileNumber"));

        }

        @Test(dependsOnMethods = { "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC" })
        @Story("Checkout Flow")
        @Description("Delete account")
        @Severity(SeverityLevel.CRITICAL)
        public void deleteAccountTC() {
                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("email") + timeStamp + "@gmail.com",
                                testData.getJsonData("password"))
                                .verifyUserIsDeletedSuccessfully();
        }

        // Configurations
        @BeforeClass
        public void setUp() {
                testData = new JsonReader("checkout-data");
                driver = new GUIDriver();
                new NavigationBarComponent(driver).navigate();
        }

        @AfterClass
        public void tearDown() {
                driver.quitDriver();
        }
}
