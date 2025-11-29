package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.CartPage;
import automationexercises.pages.CheckoutPage;
import automationexercises.pages.ProductsPage;
import automationexercises.pages.SignupLoginPage;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise ")
@Feature("UI Payment Management")
@Story("Payment Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class PaymentTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests

    @Test
    @Description("Register new account")
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


    @Test(dependsOnMethods = {"registerNewAccountTC"})
    @Description("Login to account")
    public void loginToAccountTC(){
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }


    @Test(dependsOnMethods = {"loginToAccountTC", "registerNewAccountTC"})
    @Description("Add product to cart")
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


    @Test(dependsOnMethods = {"addProductToCartTC", "loginToAccountTC",
            "registerNewAccountTC"})
    @Description("Checkout")
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


    @Test(dependsOnMethods = {"checkoutTC", "addProductToCartTC",
            "loginToAccountTC", "registerNewAccountTC"})
    @Description("Payment")
    public void paymentTC(){
        new CheckoutPage(driver)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear")
                )
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }


    @Test(dependsOnMethods = {"paymentTC", "checkoutTC",
            "addProductToCartTC", "loginToAccountTC",
            "registerNewAccountTC"})
    @Description("Delete account")
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
