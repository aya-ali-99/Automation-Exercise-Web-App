package automationexercices.tests.ui;


import api.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.CartPage;
import automationexercices.pages.ProductDetailsPage;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.SignupLoginPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Automation Exercise ")
@Feature("UI Checkout Management")
@Story("Checkout Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CheckoutTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests
    @Description("Register new account")
    @Test
    public void registerNewAccountTC(){
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        (testData.getJsonData("email")+ timeStamp +"@gmail.com"),
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

    @Description("Login to account")
    @Test(dependsOnMethods = {"registerNewAccountTC"})
    public void loginToAccountTC(){
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }

    @Description("Add product to cart")
    @Test(dependsOnMethods = {"loginToAccountTC", "registerNewAccountTC"})
    public void addProductToCartTC(){
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

    @Description("Checkout")
    @Test(dependsOnMethods = {"addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    public void checkoutTC(){
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
                        testData.getJsonData("mobileNumber")
                )
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
                        testData.getJsonData("mobileNumber")
                );

    }

    @Description("Delete account")
    @Test(dependsOnMethods = {"checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    public void deleteAccountTC(){
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email")+ timeStamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }

    // Configurations
    @BeforeClass
    public void setUp(){
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
