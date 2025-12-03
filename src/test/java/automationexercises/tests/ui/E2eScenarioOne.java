package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.*;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("E2E Scenarios")
@Owner("Aya")
public class E2eScenarioOne extends BaseTest {

    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests

    // Place order scenario 1: register & login before checkout

    @Test(groups = { "scenario_1" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Register new account")
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

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Login to account")
    @Severity(SeverityLevel.CRITICAL)
    public void loginToAccountTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton().navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }


    @Test(groups = { "scenario_1" }, dependsOnMethods = { "loginToAccountTC", "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCartTC() {
        new ProductsPage(driver)
                .navigate()
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryWomen.categoryName"))
                .clickOnWomenTopsChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryWomen.categoryLabel"))
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product2.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .verifyProductDetailsInCart(
                        testData.getJsonData("product2.name"),
                        testData.getJsonData("product2.price"),
                        testData.getJsonData("product2.quantity"),
                        testData.getJsonData("product2.total"));
    }

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "addProductToCartTC", "loginToAccountTC",
            "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Remove product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void removeProductFromCartTC() {
        new CartPage(driver)
                .navigate()
                .removeProductFromCart(testData.getJsonData("product2.name"))
                .verifyProductIsRemovedFromCart(testData.getJsonData("product2.name"));
    }

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "removeProductFromCartTC", "addProductToCartTC",
            "loginToAccountTC", "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Checkout")
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

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "removeProductFromCartTC", "checkoutTC",
            "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Payment")
    @Severity(SeverityLevel.CRITICAL)
    public void paymentTC() {
        new CheckoutPage(driver)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear"))
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "removeProductFromCartTC", "paymentTC",
            "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Verify invoice file")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyInvoiceFileTC() {
        new PaymentPage(driver)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName"));
    }

    @Test(groups = { "scenario_1" }, dependsOnMethods = { "removeProductFromCartTC", "verifyInvoiceFileTC",
            "paymentTC", "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC" })
    @Story("Place order scenario 1: register & login before checkout")
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
